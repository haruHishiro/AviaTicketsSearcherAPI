/*
 * Service for processing user requests
 * Developers: k.d.panov@gmail.com
 */

package com.avia.tickets.searching.services;


import com.avia.tickets.searching.DB.PDB;
import com.avia.tickets.searching.models.City;
import com.avia.tickets.searching.models.Country;
import com.avia.tickets.searching.models.Request;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;


@Service
public class RequestsService {
    PDB pdb = new PDB();

    public ArrayList<City> getCities() throws SQLException {
        return pdb.getCities();
    }

    public ArrayList<Country> getCountries() throws SQLException {
        return pdb.getCountries();
    }

    public ArrayList<Request> getUserRequests(long userId, boolean isInternalId) throws SQLException {
        if (isInternalId) {
            return pdb.getUserRequestsViaInternalId(userId);
        } else {
            return pdb.getUserRequestsViaInternalId(pdb.getInternalIdViaTelegramId(userId));
        }
    }

    public ArrayList<Request> getUserActiveRequests(long userId, boolean isInternalId) throws SQLException {
        if (isInternalId) {
            return pdb.getUserActiveRequestsViaInternalId(userId);
        } else {
            return pdb.getUserActiveRequestsViaInternalId(pdb.getInternalIdViaTelegramId(userId));
        }
    }

    public ArrayList<Request> getUserNonActiveRequests(long userId, boolean isInternalId) throws SQLException {
        if (isInternalId) {
            return pdb.getUserNonActiveRequestsViaInternalId(userId);
        } else {
            return pdb.getUserNonActiveRequestsViaInternalId(pdb.getInternalIdViaTelegramId(userId));
        }
    }

    public void addRequest(long userId, boolean isInternalUserId, Request request) throws SQLException {
        if (isInternalUserId) {
            pdb.addRequest(userId, request);
        } else {
            //System.out.println(pdb.getInternalIdViaTelegramId(userId));
            pdb.addRequest(pdb.getInternalIdViaTelegramId(userId), request);
        }
    }

    public void disableRequest(long offerId) throws SQLException {
        pdb.disableRequest(offerId);
    }

    public void enableRequest(long offerId) throws SQLException {
        pdb.enableRequest(offerId);
    }

    public boolean isActiveRequest(long offerId) throws SQLException {
        return pdb.isActiveRequest(offerId);
    }


    public ArrayList<Request> getAllRequests() throws SQLException {
        return pdb.getRequests();
    }

    public ArrayList<Request> getActiveRequests() throws SQLException {
        return pdb.getActiveRequests();
    }

    public long getOwnerTelegramIdViaRequestId(long requestId) throws SQLException {
        return pdb.getTelegramIdViaInternalId(pdb.getOwnerInternalIdViaRequestId(requestId));
    }

    public boolean checkCityExistenceForCountry(String countryName, String cityName) throws SQLException {
        long countryIATA = pdb.getIATAIdForCountryViaCountryName(countryName);
        long cityIATA = pdb.getIATAIdForCityViaCityName(cityName, pdb.getCountryIATACodeViaId(countryIATA));
        if (countryIATA == 0 || cityIATA == 0) {
            return false;
        }
        return true;
    }
}
