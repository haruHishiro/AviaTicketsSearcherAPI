/*
 * Service for processing user information
 * Developers: Panov K.D. ;
 * Last change: february 2025
 */

package com.avia.tickets.searching.services;


import com.avia.tickets.searching.DB.PDB;
import org.springframework.stereotype.Service;

import java.sql.SQLException;


@Service
public class UserService {
    private PDB pdb = new PDB();

    public boolean isActiveUserViaTelegramId(long telegramId) throws SQLException {
        return pdb.isActiveUserViaTelegramId(telegramId);
    }

    public boolean isActiveUserViaInternalId(long internalId) throws SQLException {
        return pdb.isActiveUserViaInternalId(internalId);
    }

    public boolean disableUserViaTelegramId(long telegramId) throws SQLException {
        return pdb.disableUserViaTelegramId(telegramId);
    }

    public boolean disableUserViaInternalId(long internalId) throws SQLException {
        return pdb.disableUserViaInternalId(internalId);
    }

    public boolean enableUserViaTelegramId(long telegramId) throws SQLException {
        return pdb.enableUserViaTelegramId(telegramId);
    }

    public boolean enableUserViaInternalId(long internalId) throws SQLException {
        return pdb.enableUserViaInternalId(internalId);
    }

    public boolean createUser(long telegramId) throws SQLException {
        return pdb.createUser(telegramId);
    }

    public boolean isUserExistViaTelegramId(long telegramId) throws SQLException {
        return pdb.isUserExistViaTelegramId(telegramId);
    }

    public boolean isUserExistViaInternalId(long internalId) throws SQLException {
        return pdb.isUserExistViaInternalId(internalId);
    }

    public long getInternalIdViaTelegramId(long telegramId) throws SQLException {
        return pdb.getInternalIdViaTelegramId(telegramId);
    }

    public long getTelegramIdViaInternalId(long internalId) throws SQLException {
        return pdb.getTelegramIdViaInternalId(internalId);
    }

}
