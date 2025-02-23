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

    public ArrayList<Offer> getUserOffersViaTelegramId(long telegramId) throws SQLException {
        return pdb.getUserOffersViaTelegramId(telegramId);
    }

    public ArrayList<Offer> getUserOffersViaInternalId(long internalId) throws SQLException {
        return pdb.getUserOffersViaInternalId(internalId);
    }

    public ArrayList<Offer> getUserActiveOffersViaTelegramId(long telegramId) throws SQLException {
        return pdb.getUserActiveOffersViaTelegramId(telegramId);
    }

    public ArrayList<Offer> getUserActiveOffersViaInternalId(long internalId) throws SQLException {
        return pdb.getUserActiveOffersViaInternalId(internalId);
    }

    public ArrayList<Offer> getUserNonActiveOffersViaTelegramId(long telegramId) throws SQLException {
        return pdb.getUserNonActiveOffersViaTelegramId(telegramId);
    }

    public ArrayList<Offer> getUserNonActiveOffersViaInternalId(long internalId) throws SQLException {
        return pdb.getUserNonActiveOffersViaInternalId(internalId);
    }

    public boolean addOffer(long id, boolean isInternalUserId, Offer offer) throws SQLException {
        if (isInternalUserId) {
            return pdb.addOffer(id, offer);
        } else {
            return pdb.addOffer(pdb.getInternalIdViaTelegramId(id), offer);
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
