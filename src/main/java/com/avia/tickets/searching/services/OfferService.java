/*
 * Service for processing of logic of work with user requests
 * Developers: Panov K.D. ;
 * Last change: february 2025
 */

package com.avia.tickets.searching.services;


import com.avia.tickets.searching.DB.PDB;
import com.avia.tickets.searching.models.Offer;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;


@Service
public class OfferService {
    PDB pdb = new PDB();

    public ArrayList<Offer> getUserOffers(long userId, boolean isInternalId) throws SQLException {
        if(isInternalId) {
            return pdb.getUserOffersViaInternalId(userId);
        } else {
            return pdb.getUserOffersViaTelegramId(userId);
        }
    }

    public ArrayList<Offer> getUserActiveOffers(long userId, boolean isInternalId) throws SQLException {
        if(isInternalId) {
            return pdb.getUserActiveOffersViaInternalId(userId);
        } else {
            return pdb.getUserActiveOffersViaTelegramId(userId);
        }
    }

    public ArrayList<Offer> getUserNonActiveOffers(long userId, boolean isInternalId) throws SQLException {
        if(isInternalId) {
            return pdb.getUserNonActiveOffersViaInternalId(userId);
        } else {
            return pdb.getUserNonActiveOffersViaTelegramId(userId);
        }
    }

    public boolean addOffer(long userId, boolean isInternalUserId, Offer offer) throws SQLException {
        if (isInternalUserId) {
            return pdb.addOffer(userId, offer);
        } else {
            return pdb.addOffer(pdb.getInternalIdViaTelegramId(userId), offer);
        }
    }

    public boolean disableOffer(long offerId) throws SQLException {
        return pdb.disableOffer(offerId);
    }

    public boolean enableOffer(long offerId) throws SQLException {
        return pdb.enableOffer(offerId);
    }

    public boolean isActiveOffer(long offerId) throws SQLException {
        return pdb.isActiveOffer(offerId);
    }
}
