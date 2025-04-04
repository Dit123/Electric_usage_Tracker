package com.electricitytracker.models;

import jakarta.persistence.*;

@Entity
public class Appliance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double powerUsage;
    private double usageTime;
    private int days;
    private double cost;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Appliance() {}

    public Appliance(String name, double powerUsage, double usageTime, int days, User user) {
        this.name = name;
        this.powerUsage = powerUsage;
        this.usageTime = usageTime;
        this.days = days;
        this.user = user;
        this.cost = calculateCost();
    }

    public double calculateCost() {
        double costPerKWh = 50.0;
        return ((powerUsage * usageTime * days) / 1000) * costPerKWh;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPowerUsage() { return powerUsage; }
    public void setPowerUsage(double powerUsage) { this.powerUsage = powerUsage; }

    public double getUsageTime() { return usageTime; }
    public void setUsageTime(double usageTime) { this.usageTime = usageTime; }

    public int getDays() { return days; }
    public void setDays(int days) { this.days = days; }

    public double getCost() { return cost; }
    public void setCost(double cost) { this.cost = cost; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
