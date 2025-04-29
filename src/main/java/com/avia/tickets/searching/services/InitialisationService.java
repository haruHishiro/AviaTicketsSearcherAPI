/*
 * Service for initialisation database with iata codes
 * Developers: k.d.panov@gmail.com
 */
package com.avia.tickets.searching.services;

import com.avia.tickets.searching.DB.PDB;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class InitialisationService {
    PDB pdb = new PDB();

    public void addCityIATACode(String cityName, String iataCode, String countryCode) throws SQLException {
        pdb.addCityIataCode(cityName, iataCode, countryCode);
    }

    public void addCountryIATACode(String countryName, String iataCode) throws SQLException {
        pdb.addCountryIataCode(countryName, iataCode);
    }
}
