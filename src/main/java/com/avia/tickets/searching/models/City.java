/*
 * Cities descriptor
 * Developers: k.d.panov@gmail.com
 */
package com.avia.tickets.searching.models;

public class City extends BaseModel {
    private String cityName;
    private String cityIATA;
    private String countryIATA;

    public City(String cityName, String cityIATA, String countryIATA) {
        this.cityName = cityName;
        this.cityIATA = cityIATA;
        this.countryIATA = countryIATA;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCityIATA() {
        return cityIATA;
    }

    public String getCountryIATA() {
        return countryIATA;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCityIATA(String cityIATA) {
        this.cityIATA = cityIATA;
    }

    public void setCountryIATA(String countryIATA) {
        this.countryIATA = countryIATA;
    }
}
