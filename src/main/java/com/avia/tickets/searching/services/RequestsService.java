/*
 * Service for processing of logic of work with user requests
 * Developers: Panov K.D. ;
 * Last change: february 2025
 */

package com.avia.tickets.searching.services;


import com.avia.tickets.searching.DB.PDB;
import com.avia.tickets.searching.models.Request;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;


@Service
public class RequestsService {
    PDB pdb = new PDB();

    public ArrayList<Request> getUserRequests(long userId, boolean isInternalId) throws SQLException {
        if(isInternalId) {
            return pdb.getUserRequestsViaInternalId(userId);
        } else {
            return pdb.getUserRequestsViaTelegramId(userId);
        }
    }

    public ArrayList<Request> getUserActiveRequests(long userId, boolean isInternalId) throws SQLException {
        if(isInternalId) {
            return pdb.getUserActiveRequestsViaInternalId(userId);
        } else {
            return pdb.getUserActiveRequestsViaTelegramId(userId);
        }
    }

    public ArrayList<Request> getUserNonActiveRequests(long userId, boolean isInternalId) throws SQLException {
        if(isInternalId) {
            return pdb.getUserNonActiveRequestsViaInternalId(userId);
        } else {
            return pdb.getUserNonActiveRequestsViaTelegramId(userId);
        }
    }

    public boolean addRequest(long userId, boolean isInternalUserId, Request request) throws SQLException {
        if (isInternalUserId) {
            return pdb.addRequest(userId, request);
        } else {
            return pdb.addRequest(pdb.getInternalIdViaTelegramId(userId), request);
        }
    }

    public boolean disableRequest(long offerId) throws SQLException {
        return pdb.disableRequests(offerId);
    }

    public boolean enableRequest(long offerId) throws SQLException {
        return pdb.enableRequests(offerId);
    }

    public boolean isActiveRequest(long offerId) throws SQLException {
        return pdb.isActiveRequests(offerId);
    }
}
