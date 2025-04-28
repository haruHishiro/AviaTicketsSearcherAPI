/*
 * Service for processing parser queries
 * Developers: Panov K.D. ;
 * Last change: february 2025
 */

package com.avia.tickets.searching.services;


import com.avia.tickets.searching.models.Ticket;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class ParserService {
    private String token = "";
    private ArrayList<Ticket> tickets = new ArrayList<>();

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void clearTickets(){
        tickets = new ArrayList<>();
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }
}
