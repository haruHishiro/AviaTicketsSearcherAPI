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

    private static final String DB_URL = "jdbc:postgresql://127.0.0.1/5432";
    private static final String USER = "postgres";
    private static final String PASS = "pass";

    //connect
    public Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    private static final String SELECT_ISACTIVE_USER_VIA_TELEGRAM_ID = "SELECT isActive FROM users WHERE telegramId = ?";
    private static final String SELECT_IS_ACTIVE_USER_VIA_INTERNAL_ID = "SELECT isActive FROM users WHERE id = ?";
    private static final String DISABLE_USER_VIA_TELEGRAM_ID = "UPDATE users SET isActive = false WHERE telegramId = ?";
    private static final String DISABLE_USER_VIA_INTERNAL_ID = "UPDATE users SET isActive = false WHERE id = ?";
    private static final String ENABLE_USER_VIA_TELEGRAM_ID = "UPDATE users SET isActive = true WHERE telegramId = ?";
    private static final String ENABLE_USER_VIA_INTERNAL_ID = "UPDATE users SET isActive = true WHERE id = ?";
    private static final String CHECK_USER_IN_DB = "SELECT * FROM users WHERE telegramId = ?";
    private static final String CREATE_USER = "INSERT INTO users (telegramId, isActive) VALUES (?, true)";
    private static final String CHECK_USER_IN_DB_VIA_ID = "SELECT * FROM users WHERE id = ?";
    private static final String GET_INTERNAL_ID = "SELECT id FROM users WHERE telegramId = ?";

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

        Connection connection = connect();
        PreparedStatement prst_for_telegramid = connection.prepareStatement(SELECT_IS_ACTIVE_USER_VIA_INTERNAL_ID);
        prst_for_telegramid.setLong(1, internalId);
        ResultSet rs = prst_for_telegramid.executeQuery();
        return rs.getBoolean("isActive");
    }

    public boolean disableUserViaTelegramId(long telegramId) throws SQLException {
        //TODO the method should change state of "isActive" field from table users to false for user with this telegram id
        Connection connection = connect();
        PreparedStatement prst_for_telegramid = connection.prepareStatement(DISABLE_USER_VIA_TELEGRAM_ID);
        prst_for_telegramid.setLong(1, telegramId);
        ResultSet rs = prst_for_telegramid.executeQuery();
        return rs.getBoolean("isActive");
    }

    public boolean disableUserViaInternalId(long internalId) throws SQLException {
        //TODO the method should change state of "isActive" field from table users to false for user with this internal id

        Connection connection = connect();
        PreparedStatement prst_for_telegramid = connection.prepareStatement(DISABLE_USER_VIA_INTERNAL_ID);
        prst_for_telegramid.setLong(1, internalId);
        ResultSet rs = prst_for_telegramid.executeQuery();
        return rs.getBoolean("isActive");
    }

    public boolean enableUserViaTelegramId(long telegramId) throws SQLException {
        //TODO the method should change state of "isActive" field from table users to true for user with this telegram id

        Connection connection = connect();
        PreparedStatement prst_for_telegramid = connection.prepareStatement(ENABLE_USER_VIA_TELEGRAM_ID);
        prst_for_telegramid.setLong(1, telegramId);
        ResultSet rs = prst_for_telegramid.executeQuery();
        return rs.getBoolean("isActive");
    }

    public boolean enableUserViaInternalId(long internalId) throws SQLException {
        //TODO the method should change state of "isActive" field from table users to true for user with this internal id

        Connection connection = connect();
        PreparedStatement prst_for_telegramid = connection.prepareStatement(ENABLE_USER_VIA_INTERNAL_ID);
        prst_for_telegramid.setLong(1, internalId);
        ResultSet rs = prst_for_telegramid.executeQuery();
        return rs.getBoolean("isActive");
    }

    public boolean createUser(long telegramId) throws SQLException {
        //TODO the method should create a new user with this telegram id, if user with this telegram id is not already exist

        /* String CHECK_USER_IN_DB = "SELECT * FROM users WHERE telegramId = ?";
        String CREATE_USER = "INSERT INTO users (telegramId, isActive) VALUES (?, true)";*/

        Connection connection = connect();

        PreparedStatement check_user = connection.prepareStatement(CHECK_USER_IN_DB);
        check_user.setLong(1, telegramId);
        ResultSet check = check_user.executeQuery();

        //check.next is okk
        if (!check.next()) {
            PreparedStatement create_user = connection.prepareStatement(CREATE_USER);
            create_user.setLong(1, telegramId);
            create_user.executeQuery();
            return true;
        } else {
            return false;
        }
    }

    public boolean isUserExistViaTelegramId(long telegramId) throws SQLException {
        //TODO the method should return the state of existence of the user with the given telegram id
        Connection connection = connect();

        PreparedStatement check_user = connection.prepareStatement(CHECK_USER_IN_DB);
        check_user.setLong(1, telegramId);
        ResultSet check = check_user.executeQuery();
        return !check.next();
    }

    public boolean isUserExistViaInternalId(long internalId) throws SQLException {
        //TODO the method should return the state of existence of the user with the given internal id
        Connection connection = connect();

        PreparedStatement check_user_id = connection.prepareStatement(CHECK_USER_IN_DB_VIA_ID);
        check_user_id.setLong(1, internalId);
        ResultSet check = check_user_id.executeQuery();

        return !check.next();
    }

    public long getInternalIdViaTelegramId(long telegramId) throws SQLException {
        //TODO the method should access the database and get the internal id via the telegram identifier
        Connection connection = connect();
        //String GET_INTERNAL_ID = "SELECT id FROM users WHERE telegramId = ?";
        PreparedStatement get_internal_id = connection.prepareStatement(GET_INTERNAL_ID);
        get_internal_id.setLong(1, telegramId);
        ResultSet rs = get_internal_id.executeQuery();
        return rs.getLong("id");

    }

    public long getTelegramIdViaInternalId(long internalId) throws SQLException {
        //TODO the method should access the database and get the telegram id via the internal identifier
        Connection connection = connect();
        String GET_TELEGRAM_ID = "SELECT telegramId FROM users WHERE id = ?";
        PreparedStatement get_telegram_id = connection.prepareStatement(GET_TELEGRAM_ID);
        get_telegram_id.setLong(1, internalId);
        ResultSet rs = get_telegram_id.executeQuery();
        return rs.getLong("telegramId");
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
