/*
 * Class for work with PostgreSQL database
 * Developers: Panov K.D. ;
 * Last change: february 2025
 */

package com.avia.tickets.searching.DB;

public class PDB {

    /*
     *
     * +==========+
     * |   users  | <-- Table Name
     * +==========+
     * |    id    | <-- Internal identifier
     * +----------+
     * |   tg id  | <-- Telegram identifier (external)
     * +----------+
     * | isActive | <-- If user allowed / disallowed messaging with bot || True - allowed; False - disallowed
     * +==========+
     *
     */
    public boolean isActiveUserViaTelegramId(long telegramId) {
        //TODO the method should return state about "isActive" field from table users for user with this telegram id
        return telegramId == 1;
    }

    public boolean isActiveUserViaInternalId(long internalId) {
        //TODO the method should return state about "isActive" field from table users for user with this internal id
        return internalId == 1;
    }

    public boolean disableUserViaTelegramId(long telegramId) {
        //TODO the method should change state of "isActive" field from table users to false for user with this telegram id
        return false;
    }

    public boolean disableUserViaInternalId(long internalId) {
        //TODO the method should change state of "isActive" field from table users to false for user with this internal id
        return false;
    }

    public boolean enableUserViaTelegramId(long telegramId) {
        //TODO the method should change state of "isActive" field from table users to true for user with this telegram id
        return false;
    }

    public boolean enableUserViaInternalId(long internalId) {
        //TODO the method should change state of "isActive" field from table users to true for user with this internal id
        return false;
    }

    public boolean createUser(long telegramId) {
        //TODO the method should create a new user with this telegram id, if user with this telegram id is not already exist
        return false;
    }

    public boolean isUserExistViaTelegramId(long telegramId) {
        //TODO the method should return the state of existence of the user with the given telegram id
        return telegramId == 1;
    }

    public boolean isUserExistViaInternalId(long internalId) {
        //TODO the method should return the state of existence of the user with the given internal id
        return internalId == 1;
    }

    public long getInternalIdViaTelegramId(long telegramId) {
        //TODO the method should access the database and get the internal id via the telegram identifier
        return 1;
    }

    public long getTelegramIdViaInternalId(long internalId) {
        //TODO the method should access the database and get the telegram id via the internal identifier
        return 1;
    }
}
