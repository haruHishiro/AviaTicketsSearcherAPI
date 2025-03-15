package com.avia.tickets.searching.models;

import java.util.Date;

public class Offer extends BaseModel {
    private long offerId;
    private long userInternalId;
    private boolean isActive;
    private String departurePointName;
    private String destinationPointName;
    private Date startDate;
    private Date endDate;
    private boolean withLuggage;
    private int ticketMaxCost;
    private short changesCount;

    public static OfferBuilder builder() {
        return new Offer().new OfferBuilder();
    }

    public class OfferBuilder {
        private OfferBuilder() {
        }

        public OfferBuilder setOfferId(long offerId){
            Offer.this.offerId = offerId;
            return this;
        }
        public OfferBuilder setUserInternalId(long userInternalId){
            Offer.this.userInternalId = userInternalId;
            return this;
        }

        public OfferBuilder setIsActive(boolean isActive){
            Offer.this.isActive = isActive;
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

        public OfferBuilder setChangesCount(short changesCount) {
            Offer.this.changesCount = changesCount;
            return this;
        }

        public Offer build() {
            return Offer.this;
        }
    }

    public long getOfferId() {
        return offerId;
    }

    public long getUserInternalId() {
        return userInternalId;
    }

    public boolean isActive() {
        return isActive;
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

    public short getChangesCount() {
        return changesCount;
    }
}
