package com.avia.tickets.searching.models;

import java.util.Date;

public class Offer extends BaseModel {
    private long offerId;
    private long internalId;
    private boolean isActive;
    private String departurePointType;
    private String destinationPointType;
    private String departurePointName;
    private String destinationPointName;
    private Date startDate;
    private Date endDate;
    private boolean withLuggage;
    private int ticketMaxCost;

    public class OfferBuilder {
        private OfferBuilder() {
        }

        public OfferBuilder setOfferId(long offerId){
            Offer.this.offerId = offerId;
            return this;
        }
        public OfferBuilder setInternalId(long internalId){
            Offer.this.internalId = internalId;
            return this;
        }

        public OfferBuilder setIsActive(boolean isActive){
            Offer.this.isActive = isActive;
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

    public long getOfferId() {
        return offerId;
    }

    public long getInternalId() {
        return internalId;
    }

    public boolean isActive() {
        return isActive;
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
