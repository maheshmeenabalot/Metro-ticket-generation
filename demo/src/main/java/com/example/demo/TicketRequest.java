package com.example.demo;

public class TicketRequest {
    private String startStation;
    private String endStation;

    public TicketRequest() {}

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
}
