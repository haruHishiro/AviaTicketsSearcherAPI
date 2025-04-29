/*
 * Model for processing countries
 * Developers: k.d.panov@gmail.com
 */
package com.avia.tickets.searching.models;

import java.util.ArrayList;

public class CountriesList extends BaseModel{
    private ArrayList<Country> countries = new ArrayList<>();

    public CountriesList(ArrayList<Country> countries) {
        this.countries = countries;
    }

    public ArrayList<Country> getCountries() {
        return countries;
    }

    public void setCountries(ArrayList<Country> countries) {
        this.countries = countries;
    }
}
