package com.example.demo;

import java.util.HashMap;
import java.util.Map;

public class StationData {
    private final Map<String, Integer> stationPrices = new HashMap<>();

    public StationData() {
        stationPrices.put("A", 0);
        stationPrices.put("B", 5);
        stationPrices.put("C", 15);
        stationPrices.put("D", 50);
    }

    public int getPrice(String station) {
        return stationPrices.getOrDefault(station, -1);
    }
}
