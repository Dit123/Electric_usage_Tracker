package com.electricitytracker.services;

import com.electricitytracker.models.Appliance;
import com.electricitytracker.models.User;
import com.electricitytracker.repositories.ApplianceRepository;
import com.electricitytracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ApplianceService {

    @Autowired
    private ApplianceRepository applianceRepository;

    @Autowired
    private UserRepository userRepository;

    public Appliance addAppliance(Long userId, String name, double powerUsage, double usageTime, int days) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            return null; // Return null if user is not found
        }

        Appliance appliance = new Appliance(name, powerUsage, usageTime, days, user.get());
        return applianceRepository.save(appliance);
    }

    public List<Appliance> getAppliancesByUser(Long userId) {
        return applianceRepository.findByUserId(userId);
    }

    public String deleteAppliance(Long id) {
        Optional<Appliance> appliance = applianceRepository.findById(id);
        if (appliance.isEmpty()) {
            return "Appliance not found";
        }
        applianceRepository.deleteById(id);
        return "Appliance deleted successfully";
    }
}
