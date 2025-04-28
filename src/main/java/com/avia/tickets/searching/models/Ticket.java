package com.avia.tickets.searching.models;

import java.sql.Date;
import java.time.OffsetDateTime;

public class Ticket extends BaseModel {
    private String flightNumber;
    private String link;
    private String originAirport;
    private String destinationAirport;
    private OffsetDateTime departureAt;
    private String airline;
    private String destination;
    private OffsetDateTime returnAt;
    private String origin;
    private int price;
    private int returnTransfers;
    private int duration; // Время в пути
    private int durationTo; // Время в пути до
    private int durationBack; // Время в пути обратно
    private int transfers;
    private long forRequest;

    // Конструктор
    public Ticket(String flightNumber, String link, String originAirport, String destinationAirport,
                  OffsetDateTime departureAt, String airline, String destination,
                  OffsetDateTime returnAt, String origin, int price,
                  int returnTransfers, int transfers, long forRequest) {
        this.flightNumber = flightNumber;
        this.link = link;
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
        this.departureAt = departureAt;
        this.airline = airline;
        this.destination = destination;
        this.returnAt = returnAt;
        this.origin = origin;
        this.price = price;
        this.returnTransfers = returnTransfers;
        this.transfers = transfers;
        this.forRequest = forRequest;
    }

    public long getForRequest() {
        return forRequest;
    }

    public void setForRequest(long forRequest) {
        this.forRequest = forRequest;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getOriginAirport() {
        return originAirport;
    }

    public void setOriginAirport(String originAirport) {
        this.originAirport = originAirport;
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(String destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public OffsetDateTime getDepartureAt() {
        return departureAt;
    }

    public void setDepartureAt(OffsetDateTime departureAt) {
        this.departureAt = departureAt;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public OffsetDateTime getReturnAt() {
        return returnAt;
    }

    public void setReturnAt(OffsetDateTime returnAt) {
        this.returnAt = returnAt;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getReturnTransfers() {
        return returnTransfers;
    }

    public void setReturnTransfers(int returnTransfers) {
        this.returnTransfers = returnTransfers;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDurationTo() {
        return durationTo;
    }

    public void setDurationTo(int durationTo) {
        this.durationTo = durationTo;
    }

    public int getDurationBack() {
        return durationBack;
    }

    public void setDurationBack(int durationBack) {
        this.durationBack = durationBack;
    }

    public int getTransfers() {
        return transfers;
    }

    public void setTransfers(int transfers) {
        this.transfers = transfers;
    }

}
