package com.avia.tickets.searching.models;

import java.util.ArrayList;

public class TicketsList extends BaseModel {
    private ArrayList<Ticket> tickets;

    public TicketsList(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }
}
