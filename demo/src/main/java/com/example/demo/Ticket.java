package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Ticket {
    @Id
    private String id;
    private String startStation;
    private String endStation;
    private int price;
    private LocalDateTime expiryTime;
    private boolean usedForEntry;
    private boolean usedForExit;
    private String entryStation;
    private String exitStation;

    // Default Constructor
    public Ticket() {
    }

    // Parameterized Constructor
    public Ticket(String id, String startStation, String endStation, int price, LocalDateTime expiryTime) {
        this.id = id;
        this.startStation = startStation;
        this.endStation = endStation;
        this.price = price;
        this.expiryTime = expiryTime;
        this.usedForEntry = false;
        this.usedForExit = false;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(LocalDateTime expiryTime) {
        this.expiryTime = expiryTime;
    }

    public boolean isUsedForEntry() {
        return usedForEntry;
    }

    public void setUsedForEntry(boolean usedForEntry) {
        this.usedForEntry = usedForEntry;
    }

    public boolean isUsedForExit() {
        return usedForExit;
    }

    public void setUsedForExit(boolean usedForExit) {
        this.usedForExit = usedForExit;
    }

    public String getEntryStation() {
        return entryStation;
    }

    public void setEntryStation(String entryStation) {
        this.entryStation = entryStation;
    }

    public String getExitStation() {
        return exitStation;
    }

    public void setExitStation(String exitStation) {
        this.exitStation = exitStation;
    }
}
