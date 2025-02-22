/*
 * Service for processing user information
 * Developers: Panov K.D. ;
 * Last change: february 2025
 */

package com.avia.tickets.searching.services;


import com.avia.tickets.searching.DB.PDB;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private PDB pdb = new PDB();

    public boolean isActiveUserViaTelegramId(long telegramId) {
        return pdb.isActiveUserViaTelegramId(telegramId);
    }

    public boolean isActiveUserViaInternalId(long internalId) {
        return pdb.isActiveUserViaInternalId(internalId);
    }

    public boolean disableUserViaTelegramId(long telegramId) {
        return pdb.disableUserViaTelegramId(telegramId);
    }

    public boolean disableUserViaInternalId(long internalId) {
        return pdb.disableUserViaInternalId(internalId);
    }

    public boolean enableUserViaTelegramId(long telegramId) {
        return pdb.enableUserViaTelegramId(telegramId);
    }

    public boolean enableUserViaInternalId(long internalId) {
        return pdb.enableUserViaInternalId(internalId);
    }

    public boolean createUser(long telegramId) {
        return pdb.createUser(telegramId);
    }

    public boolean isUserExistViaTelegramId(long telegramId) {
        return pdb.isUserExistViaTelegramId(telegramId);
    }

    public boolean isUserExistViaInternalId(long internalId) {
        return pdb.isUserExistViaInternalId(internalId);
    }

    public long getInternalIdViaTelegramId(long telegramId) {
        return pdb.getInternalIdViaTelegramId(telegramId);
    }

    public long getTelegramIdViaInternalId(long internalId) {
        return pdb.getTelegramIdViaInternalId(internalId);
    }

}
