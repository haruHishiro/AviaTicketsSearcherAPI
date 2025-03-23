package com.avia.tickets.searching.models;

import java.util.Date;

public class Request extends BaseModel {
    private long requestId;
    private long userInternalId;
    private boolean isActive;
    private String departurePointCountryName;
    private String destinationPointCountryName;
    private String departurePointName;
    private String destinationPointName;
    private Date startDate;
    private Date endDate;
    private boolean withLuggage;
    private int ticketMaxCost;
    private short changesCount;

    public static RequestBuilder builder() {
        return new Request().new RequestBuilder();
    }

    public class RequestBuilder {
        private RequestBuilder() {
        }

        public RequestBuilder setRequestId(long offerId) {
            Request.this.requestId = offerId;
            return this;
        }

        public RequestBuilder setUserInternalId(long userInternalId) {
            Request.this.userInternalId = userInternalId;
            return this;
        }

        public RequestBuilder setIsActive(boolean isActive) {
            Request.this.isActive = isActive;
            return this;
        }

        public RequestBuilder setDeparturePointCountryName(String departurePointCountryName) {
            Request.this.departurePointCountryName = departurePointCountryName;
            return this;
        }

        public RequestBuilder setDestinationPointCountryName(String destinationPointCountryName) {
            Request.this.destinationPointCountryName = destinationPointCountryName;
            return this;
        }

        public RequestBuilder setDeparturePointName(String departurePointName) {
            Request.this.departurePointName = departurePointName;
            return this;
        }

        public RequestBuilder setDestinationPointName(String destinationPointName) {
            Request.this.destinationPointName = destinationPointName;
            return this;
        }

        public RequestBuilder setStartDate(Date startDate) {
            Request.this.startDate = startDate;
            return this;
        }

        public RequestBuilder setEndDate(Date endDate) {
            Request.this.endDate = endDate;
            return this;
        }

        public RequestBuilder setWithLuggage(boolean withLuggage) {
            Request.this.withLuggage = withLuggage;
            return this;
        }

        public RequestBuilder setTicketMaxCost(int ticketMaxCost) {
            Request.this.ticketMaxCost = ticketMaxCost;
            return this;
        }

        public RequestBuilder setChangesCount(short changesCount) {
            Request.this.changesCount = changesCount;
            return this;
        }

        public Request build() {
            return Request.this;
        }
    }

    public long getRequestId() {
        return requestId;
    }

    public long getUserInternalId() {
        return userInternalId;
    }

    public boolean isActive() {
        return isActive;
    }

    public String getDeparturePointCountryName() {
        return departurePointCountryName;
    }

    public String getDestinationPointCountryName() {
        return destinationPointCountryName;
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
