package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // Allow requests from the frontend
@RequestMapping("/api/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping("/generate")
    public Ticket generateTicket(@RequestBody TicketRequest request) {
        return ticketService.generateTicket(request);
    }

    @PostMapping("/use/{ticketId}")
    public String useTicket(@PathVariable String ticketId, @RequestParam String station, @RequestParam String action) {
        return ticketService.useTicket(ticketId, station, action);
    }
}
