/*
 * Class for work with PostgreSQL database
 * Developers: Panov K.D. ;
 * Last change: february 2025
 */

package com.avia.tickets.searching.DB;

import com.avia.tickets.searching.models.Offer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
//import java.util.Date;
import java.sql.*;

public class PDB {
    /*
     * =====================================================================================
     *                               General description START
     * =====================================================================================
     */

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
     * +==========+
     * |  offers  | <-- Table Name
     * +==========+
     * |    id    | <-- Offer identifier
     * +----------+
     * |  user id | <-- Owner id (user internal id)
     * +----------+
     * | isActive | <-- If offer allowed / disallowed to parse / scrap || True - allowed; False - disallowed
     * +----------+
     * | dep type | <-- Departure point type id
     * +----------+
     * | dst type | <-- Destination point type id
     * +----------+
     * |  dep id  | <-- Departure point internal id
     * +----------+
     * |  dst id  | <-- Destination point internal id
     * +----------+
     * |  st date | <-- Date of dispatch
     * +----------+
     * | end date | <-- Return ticket date
     * +----------+
     * |  luggage | <-- is luggage need || True - need; False - not need
     * +----------+
     * | max cost | <-- ticket max cost
     * +==========+
     *
     */

    /*
     * =====================================================================================
     *                               General description END
     * =====================================================================================
     */

    /*
     * =====================================================================================
     *                               User service section START
     * =====================================================================================
     */

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/AirBase";
    private static final String USER = "postgres";
    private static final String PASS = "toor";

    private static final String SELECT_ISACTIVE_USER_VIA_TELEGRAM_ID = "SELECT isActive FROM users WHERE telegramId = ?";

    //connect to database
    public Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }


    public boolean isActiveUserViaTelegramId(long telegramId) throws SQLException {
        //TODO the method should return state about "isActive" field from table users for user with this telegram id

        Connection connection = connect();
        PreparedStatement prst_for_telegramid = connection.prepareStatement(SELECT_ISACTIVE_USER_VIA_TELEGRAM_ID);
        prst_for_telegramid.setLong(1, telegramId);
        ResultSet rs = prst_for_telegramid.executeQuery();
        return rs.getBoolean("isActive");
    }

    public boolean isActiveUserViaInternalId(long internalId) throws SQLException {
        //TODO the method should return state about "isActive" field from table users for user with this internal id
        return internalId == 1;
    }

    public boolean disableUserViaTelegramId(long telegramId) throws SQLException {
        //TODO the method should change state of "isActive" field from table users to false for user with this telegram id
        return false;
    }

    public boolean disableUserViaInternalId(long internalId) throws SQLException {
        //TODO the method should change state of "isActive" field from table users to false for user with this internal id
        return false;
    }

    public boolean enableUserViaTelegramId(long telegramId) throws SQLException {
        //TODO the method should change state of "isActive" field from table users to true for user with this telegram id
        return false;
    }

    public boolean enableUserViaInternalId(long internalId) throws SQLException {
        //TODO the method should change state of "isActive" field from table users to true for user with this internal id
        return false;
    }

    public boolean createUser(long telegramId) throws SQLException {
        //TODO the method should create a new user with this telegram id, if user with this telegram id is not already exist
        return false;
    }

    public boolean isUserExistViaTelegramId(long telegramId) throws SQLException {
        //TODO the method should return the state of existence of the user with the given telegram id
        return telegramId == 1;
    }

    public boolean isUserExistViaInternalId(long internalId) throws SQLException {
        //TODO the method should return the state of existence of the user with the given internal id
        return internalId == 1;
    }

    public long getInternalIdViaTelegramId(long telegramId) throws SQLException {
        //TODO the method should access the database and get the internal id via the telegram identifier
        return 1;
    }

    public long getTelegramIdViaInternalId(long internalId) throws SQLException {
        //TODO the method should access the database and get the telegram id via the internal identifier
        return 1;
    }

    /*
     * =====================================================================================
     *                               User service section END
     * =====================================================================================
     */

    /*
     * =====================================================================================
     *                               Offer service section START
     * =====================================================================================
     */

    public ArrayList<Offer> getUserOffersViaTelegramId(long telegramId) throws SQLException {
        //TODO the method should return list of all offers of user with the passed telegram identifier
        return new ArrayList<Offer>();
    }

    public ArrayList<Offer> getUserOffersViaInternalId(long internalId) throws SQLException {
        //TODO the method should return list of all offers of user with the passed internal identifier
        return new ArrayList<Offer>();
    }

    public ArrayList<Offer> getUserActiveOffersViaTelegramId(long telegramId) throws SQLException {
        //TODO the method should return list of active offers of user with the passed telegram identifier
        return new ArrayList<Offer>();
    }

    public ArrayList<Offer> getUserActiveOffersViaInternalId(long internalId) throws SQLException {
        //TODO the method should return list of active offers of user with the passed internal identifier
        return new ArrayList<Offer>();
    }

    public ArrayList<Offer> getUserNonActiveOffersViaTelegramId(long telegramId) throws SQLException {
        //TODO the method should return list of non active offers of user with the passed telegram identifier
        return new ArrayList<Offer>();
    }

    public ArrayList<Offer> getUserNonActiveOffersViaInternalId(long internalId) throws SQLException {
        //TODO the method should return list of non active offers of user with the passed internal identifier
        return new ArrayList<Offer>();
    }

    public boolean addOffer(long internalId, Offer offerFields) throws SQLException {
        //TODO the method should create a new offer for user with passed internal identifier if offer with the passed fields for this user not exist
        return false;
    }

    public boolean disableOffer(long offerId) throws SQLException {
        //TODO the method should set field isActive to false of offer with the passed identifier
        return false;
    }

    public boolean enableOffer(long offerId) throws SQLException {
        //TODO the method should set field isActive to true of offer with the passed identifier
        return false;
    }

    public boolean isActiveOffer(long offerId) throws SQLException {
        //TODO the method should return the state of the "isActive" field from table "offers" of offer with the passed identifier
        return false;
    }

    /*
     * =====================================================================================
     *                               Offer service section END
     * =====================================================================================
     */

}
