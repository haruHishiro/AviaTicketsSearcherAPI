package com.avia.tickets.searching.models;

import java.util.Date;

public class Offer extends BaseModel {
    long internalId;
    long telegramId;
    String departurePointType;
    String destinationPointType;
    String departurePointName;
    String destinationPointName;
    Date startDate;
    Date endDate;
    boolean withLuggage;
    int ticketMaxCost;

    public class OfferBuilder {
        private OfferBuilder() {
        }

        public OfferBuilder setInternalId(long internalId){
            Offer.this.internalId = internalId;
            return this;
        }

        public OfferBuilder setTelegramId(long telegramId){
            Offer.this.telegramId = telegramId;
            return this;
        }

        public OfferBuilder setDeparturePointType(String departurePointType){
            Offer.this.departurePointType = departurePointType;
            return this;
        }

        public OfferBuilder setDestinationPointType(String destinationPointType){
            Offer.this.destinationPointType = destinationPointType;
            return this;
        }

        public OfferBuilder setDeparturePointName(String departurePointName){
            Offer.this.departurePointName = departurePointName;
            return this;
        }

        public OfferBuilder setDestinationPointName(String destinationPointName){
            Offer.this.destinationPointName = destinationPointName;
            return this;
        }

        public OfferBuilder setStartDate(Date startDate){
            Offer.this.startDate = startDate;
            return this;
        }

        public OfferBuilder setEndDate(Date endDate){
            Offer.this.endDate = endDate;
            return this;
        }

        public OfferBuilder setWithLuggage(boolean withLuggage){
            Offer.this.withLuggage = withLuggage;
            return this;
        }

        public OfferBuilder setTicketMaxCost(int ticketMaxCost){
            Offer.this.ticketMaxCost = ticketMaxCost;
            return this;
        }

        public Offer build() {
            return Offer.this;
        }
    }

    public long getInternalId() {
        return internalId;
    }

    public long getTelegramId() {
        return telegramId;
    }

    public String getDeparturePointType() {
        return departurePointType;
    }

    public String getDestinationPointType() {
        return destinationPointType;
    }

    public String getDeparturePointName() {
        return departurePointName;
    }

    public String getDestinationPointName() {
        return destinationPointName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public boolean isWithLuggage() {
        return withLuggage;
    }

    public int getTicketMaxCost() {
        return ticketMaxCost;
    }


}
