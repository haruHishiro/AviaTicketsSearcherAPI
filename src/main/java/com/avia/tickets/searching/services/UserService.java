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

    public boolean isActiveUser(long userId, boolean isInternalId) throws SQLException {
        if (isInternalId) {
            return pdb.isActiveUserViaInternalId(userId);
        }
        return pdb.isActiveUserViaTelegramId(userId);
    }

    public boolean disableUser(long userId, boolean isInternalId) throws SQLException {
        if (isInternalId) {
            return pdb.disableUserViaInternalId(userId);
        }
        return pdb.disableUserViaTelegramId(userId);
    }

    public boolean enableUser(long userId, boolean isInternalId) throws SQLException {
        if (isInternalId) {
            return pdb.enableUserViaInternalId(userId);
        }
        return pdb.enableUserViaTelegramId(userId);
    }

    public void createUser(long telegramId) throws SQLException {
        pdb.createUser(telegramId);
    }

    public boolean isUserExist(long userId, boolean isInternalId) throws SQLException {
        if (isInternalId) {
            return pdb.isUserExistViaInternalId(userId);
        }
        return pdb.isUserExistViaTelegramId(userId);
    }

    public long getInternalIdViaTelegramId(long telegramId) throws SQLException {
        return pdb.getInternalIdViaTelegramId(telegramId);
    }

    public long getTelegramIdViaInternalId(long internalId) throws SQLException {
        return pdb.getTelegramIdViaInternalId(internalId);
    }

}
