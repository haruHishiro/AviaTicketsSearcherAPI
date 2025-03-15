package com.avia.tickets.searching.models;

import java.util.ArrayList;

public class OffersList extends BaseModel{
    private ArrayList<Offer> offers;

    public OffersList(ArrayList<Offer> offers) {
        this.offers = offers;
    }

    public ArrayList<Offer> getOffers() {
        return offers;
    }

    public void setOffers(ArrayList<Offer> offers) {
        this.offers = offers;
    }
}
