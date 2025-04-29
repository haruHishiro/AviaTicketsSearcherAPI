/*
 * Countries descriptor
 * Developers: k.d.panov@gmail.com
 */
package com.avia.tickets.searching.models;

public class Country {
    private String countryName;

    private String countryIATA;

    public Country(String countryName, String countryIATA) {
        this.countryName = countryName;
        this.countryIATA = countryIATA;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryIATA() {
        return countryIATA;
    }

    public void setCountryIATA(String countryIATA) {
        this.countryIATA = countryIATA;
    }
}
