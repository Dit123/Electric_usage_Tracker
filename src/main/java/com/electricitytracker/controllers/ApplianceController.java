package com.electricitytracker.controllers;

import com.electricitytracker.models.Appliance;
import com.electricitytracker.repositories.ApplianceRepository;
import com.electricitytracker.services.ApplianceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appliances")
public class ApplianceController {

    @Autowired
    private ApplianceService applianceService;

    @Autowired
    private ApplianceRepository applianceRepository; 

    @PostMapping("/add")
    public Appliance addAppliance(@RequestParam Long userId,
                                  @RequestParam String name,
                                  @RequestParam double powerUsage,
                                  @RequestParam double usageTime,
                                  @RequestParam int days) {
        return applianceService.addAppliance(userId, name, powerUsage, usageTime, days);
    }

    @GetMapping("/user/{userId}")
    public List<Appliance> getAppliances(@PathVariable Long userId) {
        return applianceService.getAppliancesByUser(userId);
    }

    @DeleteMapping("/{id}")
    public String deleteAppliance(@PathVariable Long id) {
        return applianceService.deleteAppliance(id);
    }

     @PostMapping("/calculate")
    public double calculateCost(@RequestBody Appliance appliance) {
        return (appliance.getPowerUsage() * appliance.getUsageTime() * 50) / 1000;
    }

    @GetMapping("/history/{userId}")
    public List<Appliance> getHistory(@PathVariable Long userId) {
        return applianceService.getAppliancesByUser(userId); 
    }
}

