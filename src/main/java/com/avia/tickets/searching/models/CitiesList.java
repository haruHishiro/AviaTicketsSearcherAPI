package com.avia.tickets.searching.models;

import java.util.ArrayList;

public class CitiesList extends BaseModel {
    private ArrayList<City> cities = new ArrayList<>();

    public CitiesList(ArrayList<City> cities) {
        this.cities = cities;
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }
}
