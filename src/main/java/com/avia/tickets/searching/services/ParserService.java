/*
 * Service for processing parser queries
 * Developers: k.d.panov@gmail.com
 */
package com.avia.tickets.searching.services;


import com.avia.tickets.searching.models.Ticket;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Service
public class ParserService {
    private Map<String, String> tokens = new HashMap<>(); // Site:TokenForSite
    private ArrayList<Ticket> tickets = new ArrayList<>();

    public String getToken(String site) {
        return tokens.get(site);
    }

    public void setToken(String site, String tokenForSite) {
        this.tokens.put(site,tokenForSite);
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
