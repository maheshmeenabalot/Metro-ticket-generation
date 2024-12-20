package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.core.io.ClassPathResource;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    private final Map<String, Integer> stationPrices = new HashMap<>();

    @PostConstruct
    public void loadStations() {
        try {
            // Load the JSON file from the classpath
            ObjectMapper mapper = new ObjectMapper();
            ClassPathResource resource = new ClassPathResource("stations.json");
            Map<String, Object> jsonData = mapper.readValue(resource.getInputStream(), Map.class);

            Map<String, Map<String, Object>> stations = (Map<String, Map<String, Object>>) jsonData.get("stations");
            for (Map.Entry<String, Map<String, Object>> entry : stations.entrySet()) {
                String station = entry.getKey();
                int price = (int) entry.getValue().getOrDefault("price", 0);
                stationPrices.put(station, price);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load stations from JSON file", e);
        }
    }

    public Ticket generateTicket(TicketRequest request) {
        validateStation(request.getStartStation());
        validateStation(request.getEndStation());

        String ticketId = UUID.randomUUID().toString();
        LocalDateTime expiryTime = LocalDateTime.now().plusHours(18);
        int price = Math.abs(stationPrices.get(request.getStartStation()) - stationPrices.get(request.getEndStation()));

        Ticket ticket = new Ticket(ticketId, request.getStartStation(), request.getEndStation(), price, expiryTime);
        return ticketRepository.save(ticket);
    }

    public String useTicket(String ticketId, String station, String action) {
        validateStation(station);

        Ticket ticket = ticketRepository.findById(ticketId).orElse(null);
        if (ticket == null) {
            return "Invalid Ticket ID";
        }

        if (LocalDateTime.now().isAfter(ticket.getExpiryTime())) {
            return "Ticket has expired.";
        }

        String normalizedAction = action.trim().toLowerCase();
        if (normalizedAction.equals("entry")) {
            if (ticket.isUsedForEntry()) {
                return "Ticket already used for entry.";
            }
            ticket.setUsedForEntry(true);
            ticket.setEntryStation(station);
        } else if (normalizedAction.equals("exit")) {
            if (!ticket.isUsedForEntry()) {
                return "Ticket must be used for entry before exit.";
            }
            if (ticket.isUsedForExit()) {
                return "Ticket already used for exit.";
            }
            ticket.setUsedForExit(true);
            ticket.setExitStation(station);
        } else {
            return "Invalid action.";
        }

        ticketRepository.save(ticket);
        return "Action recorded.";
    }

    private void validateStation(String station) {
        if (!stationPrices.containsKey(station)) {
            throw new InvalidStationException("Invalid station: " + station);
        }
    }
}
