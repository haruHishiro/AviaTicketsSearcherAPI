/*
 * Class for work with PostgreSQL database
 * Developers: Panov K.D. ;
 * Last change: february 2025
 */

package com.avia.tickets.searching.DB;

import com.avia.tickets.searching.models.Request;

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
     * |    id    | <-- Request identifier
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

    private static final String DB_URL = "jdbc:postgresql://127.0.0.1/5432";
    private static final String USER = "postgres";
    private static final String PASS = "pass";

    private static final String SELECT_IS_ACTIVE_USER_VIA_TELEGRAM_ID = "SELECT is_active FROM users WHERE telegram_id = ?";
    private static final String SELECT_IS_ACTIVE_USER_VIA_INTERNAL_ID = "SELECT is_active FROM users WHERE id = ?";
    private static final String DISABLE_USER_VIA_TELEGRAM_ID = "UPDATE users SET is_active = false WHERE telegram_id = ?";
    private static final String DISABLE_USER_VIA_INTERNAL_ID = "UPDATE users SET is_active = false WHERE id = ?";
    private static final String ENABLE_USER_VIA_TELEGRAM_ID = "UPDATE users SET is_active = true WHERE telegram_id = ?";
    private static final String ENABLE_USER_VIA_INTERNAL_ID = "UPDATE users SET is_active = true WHERE id = ?";
    private static final String CHECK_USER_IN_DB = "SELECT * FROM users WHERE telegram_id = ?";
    private static final String CREATE_USER = "INSERT INTO users (telegram_id, is_active) VALUES (?, true)";
    private static final String CHECK_USER_IN_DB_VIA_ID = "SELECT * FROM users WHERE id = ?";
    private static final String GET_INTERNAL_ID = "SELECT id FROM users WHERE telegram_id = ?";
    private static final String GET_TELEGRAM_ID = "SELECT telegram_id FROM users WHERE id = ?";
    private static final String GET_ACTIVE_REQUESTS = "SELECT * FROM requests WHERE is_active = true";
    private static final String GET_NON_ACTIVE_REQUESTS = "SELECT * FROM requests WHERE is_active = false";
    private static final String GET_ALL_REQUESTS = "SELECT * FROM requests";
    private static final String GET_USER_ACTIVE_REQUESTS = "SELECT * FROM requests WHERE is_active = true and user_internal_id = ?";
    private static final String GET_USER_NON_ACTIVE_REQUESTS = "SELECT * FROM requests WHERE is_active = false and user_internal_id = ?";
    private static final String GET_USER_ALL_REQUESTS = "SELECT * FROM requests where user_internal_id = ?";
    private static final String INSERT_REQUEST = "INSERT INTO requests " +
            "(is_active, user_internal_id , departure_point_country_id, destination_point_country_id, departure_point_id, destination_point_id, " +
            "start_date, end_date, is_need_luggage, changes_count, ticket_max_cost)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String ENABLE_REQUEST_VIA_ID = "UPDATE requests SET is_active = true WHERE telegramId = ?";
    private static final String DISABLE_REQUEST_VIA_ID = "UPDATE requests SET is_active = false WHERE telegramId = ?";
    private static final String IS_ACTIVE_REQUEST_VIA_ID = "SELECT is_active FROM requests WHERE id = ?";
    private static final String GET_COUNTRY_IATA_CODE_VIA_ID = "SELECT iata_code FROM iata_codes_country WHERE id = ?";
    private static final String GET_CITY_IATA_CODE_VIA_ID = "SELECT iata_code FROM iata_codes_city WHERE id = ?";
    private static final String GET_IATA_ID_FOR_COUNTRY_VIA_COUNTRY_NAME = "SELECT id FROM iata_codes_country WHERE country_name = ?";
    private static final String GET_IATA_ID_FOR_CITY_VIA_CITY_NAME = "SELECT id FROM iata_codes_city WHERE city_name = ? AND country_code = ?";


    //connect
    public Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    /*
     * =====================================================================================
     *                               User service section START
     * =====================================================================================
     */


    public boolean isActiveUserViaTelegramId(long telegramId) throws SQLException {
        //TODO the method should return state about "isActive" field from table users for user with this telegram id

        Connection connection = connect();
        PreparedStatement prst_for_telegramid = connection.prepareStatement(SELECT_IS_ACTIVE_USER_VIA_TELEGRAM_ID);
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

    public void createUser(long telegramId) throws SQLException {
        //TODO the method should create a new user with this telegram id, if user with this telegram id is not already exist
        Connection connection = connect();

        PreparedStatement create_user = connection.prepareStatement(CREATE_USER);
        create_user.setLong(1, telegramId);
        create_user.executeQuery();
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
        PreparedStatement get_internal_id = connection.prepareStatement(GET_INTERNAL_ID);
        get_internal_id.setLong(1, telegramId);
        ResultSet rs = get_internal_id.executeQuery();
        return rs.getLong("id");
    }

    public long getTelegramIdViaInternalId(long internalId) throws SQLException {
        //TODO the method should access the database and get the telegram id via the internal identifier
        Connection connection = connect();
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
     *                               Request service section START
     * =====================================================================================
     */

    public String getCityIATACodeViaId(long id) throws SQLException{
        Connection connection = connect();
        PreparedStatement ps = connection.prepareStatement(GET_CITY_IATA_CODE_VIA_ID);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        return rs.getString("iata_code");
    }

    public String getCountryIATACodeViaId(long id) throws SQLException{
        Connection connection = connect();
        PreparedStatement ps = connection.prepareStatement(GET_COUNTRY_IATA_CODE_VIA_ID);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        return rs.getString("iata_code");
    }

    public long getIATAIdForCountryViaCountryName(String countryName) throws SQLException{
        Connection connection = connect();
        PreparedStatement ps = connection.prepareStatement(GET_IATA_ID_FOR_COUNTRY_VIA_COUNTRY_NAME);
        ps.setString(1, countryName);
        ResultSet rs = ps.executeQuery();
        return rs.getLong("id");
    }

    public long getIATAIdForCityViaCityName(String city, String countryCode) throws SQLException{
        Connection connection = connect();
        PreparedStatement ps = connection.prepareStatement(GET_IATA_ID_FOR_CITY_VIA_CITY_NAME);
        ps.setString(1, city);
        ps.setString(2, countryCode);
        ResultSet rs = ps.executeQuery();
        return rs.getLong("id");
    }

    public ArrayList<Request> getUserRequestsViaInternalId(long internalId) throws SQLException {
        //TODO the method should return list of all offers of user with the passed internal identifier
        Connection connection = connect();
        PreparedStatement ps = connection.prepareStatement(GET_USER_ALL_REQUESTS);
        ps.setLong(1, internalId);
        ResultSet rs = ps.executeQuery();

        ArrayList<Request> requests = new ArrayList<>();
        Request request;
        while (rs.next()) {
            String departurePointCityIATA = getCityIATACodeViaId(rs.getLong("departure_point_id"));
            String destinationPointCityIATA = getCityIATACodeViaId(rs.getLong("destination_point_id"));
            String departurePointCountryIATA = getCountryIATACodeViaId(rs.getLong("departure_point_country_id"));
            String destinationPointCountryIATA = getCountryIATACodeViaId(rs.getLong("destination_point_country_id"));
            request = Request.builder()
                    .setRequestId(rs.getLong("id"))
                    .setIsActive(rs.getBoolean("is_active"))
                    .setDeparturePointCountryName(departurePointCountryIATA)
                    .setDestinationPointCountryName(destinationPointCountryIATA)
                    .setDeparturePointName(departurePointCityIATA)
                    .setDestinationPointName(destinationPointCityIATA)
                    .setStartDate(rs.getDate("start_date"))
                    .setEndDate(rs.getDate("end_date"))
                    .setWithLuggage(rs.getBoolean("is_need_luggage"))
                    .setTicketMaxCost(rs.getInt("ticket_max_cost"))
                    .setChangesCount(rs.getShort("changes_count"))
                    .build();
            requests.add(request);
        }

        return requests;
    }

    public ArrayList<Request> getUserActiveRequestsViaInternalId(long internalId) throws SQLException {
        //TODO the method should return list of active offers of user with the passed internal identifier
        Connection connection = connect();
        PreparedStatement ps = connection.prepareStatement(GET_USER_ACTIVE_REQUESTS);
        ps.setLong(1, internalId);
        ResultSet rs = ps.executeQuery();

        ArrayList<Request> requests = new ArrayList<>();
        Request request;
        while (rs.next()) {
            String departurePointCityIATA = getCityIATACodeViaId(rs.getLong("departure_point_id"));
            String destinationPointCityIATA = getCityIATACodeViaId(rs.getLong("destination_point_id"));
            String departurePointCountryIATA = getCountryIATACodeViaId(rs.getLong("departure_point_country_id"));
            String destinationPointCountryIATA = getCountryIATACodeViaId(rs.getLong("destination_point_country_id"));
            request = Request.builder()
                    .setRequestId(rs.getLong("id"))
                    .setIsActive(rs.getBoolean("is_active"))
                    .setDeparturePointCountryName(departurePointCountryIATA)
                    .setDestinationPointCountryName(destinationPointCountryIATA)
                    .setDeparturePointName(departurePointCityIATA)
                    .setDestinationPointName(destinationPointCityIATA)
                    .setStartDate(rs.getDate("start_date"))
                    .setEndDate(rs.getDate("end_date"))
                    .setWithLuggage(rs.getBoolean("is_need_luggage"))
                    .setTicketMaxCost(rs.getInt("ticket_max_cost"))
                    .setChangesCount(rs.getShort("changes_count"))
                    .build();
            requests.add(request);
        }

        return requests;
    }

    public ArrayList<Request> getUserNonActiveRequestsViaInternalId(long internalId) throws SQLException {
        //TODO the method should return list of non active offers of user with the passed internal identifier
        Connection connection = connect();
        PreparedStatement ps = connection.prepareStatement(GET_USER_NON_ACTIVE_REQUESTS);
        ps.setLong(1, internalId);
        ResultSet rs = ps.executeQuery();

        ArrayList<Request> requests = new ArrayList<>();
        Request request;
        while (rs.next()) {
            String departurePointCityIATA = getCityIATACodeViaId(rs.getLong("departure_point_id"));
            String destinationPointCityIATA = getCityIATACodeViaId(rs.getLong("destination_point_id"));
            String departurePointCountryIATA = getCountryIATACodeViaId(rs.getLong("departure_point_country_id"));
            String destinationPointCountryIATA = getCountryIATACodeViaId(rs.getLong("destination_point_country_id"));
            request = Request.builder()
                    .setRequestId(rs.getLong("id"))
                    .setIsActive(rs.getBoolean("is_active"))
                    .setDeparturePointCountryName(departurePointCountryIATA)
                    .setDestinationPointCountryName(destinationPointCountryIATA)
                    .setDeparturePointName(departurePointCityIATA)
                    .setDestinationPointName(destinationPointCityIATA)
                    .setStartDate(rs.getDate("start_date"))
                    .setEndDate(rs.getDate("end_date"))
                    .setWithLuggage(rs.getBoolean("is_need_luggage"))
                    .setTicketMaxCost(rs.getInt("ticket_max_cost"))
                    .setChangesCount(rs.getShort("changes_count"))
                    .build();
            requests.add(request);
        }

        return requests;
    }

    public ArrayList<Request> getRequests() throws SQLException {
        //TODO the method should return list of all offers of user with the passed internal identifier
        Connection connection = connect();
        PreparedStatement ps = connection.prepareStatement(GET_ALL_REQUESTS);
        ResultSet rs = ps.executeQuery();

        ArrayList<Request> requests = new ArrayList<>();
        Request request;
        while (rs.next()) {
            String departurePointCityIATA = getCityIATACodeViaId(rs.getLong("departure_point_id"));
            String destinationPointCityIATA = getCityIATACodeViaId(rs.getLong("destination_point_id"));
            String departurePointCountryIATA = getCountryIATACodeViaId(rs.getLong("departure_point_country_id"));
            String destinationPointCountryIATA = getCountryIATACodeViaId(rs.getLong("destination_point_country_id"));
            request = Request.builder()
                    .setRequestId(rs.getLong("id"))
                    .setIsActive(rs.getBoolean("is_active"))
                    .setDeparturePointCountryName(departurePointCountryIATA)
                    .setDestinationPointCountryName(destinationPointCountryIATA)
                    .setDeparturePointName(departurePointCityIATA)
                    .setDestinationPointName(destinationPointCityIATA)
                    .setStartDate(rs.getDate("start_date"))
                    .setEndDate(rs.getDate("end_date"))
                    .setWithLuggage(rs.getBoolean("is_need_luggage"))
                    .setTicketMaxCost(rs.getInt("ticket_max_cost"))
                    .setChangesCount(rs.getShort("changes_count"))
                    .build();
            requests.add(request);
        }

        return requests;
    }

    public ArrayList<Request> getActiveRequests() throws SQLException {
        //TODO the method should return list of active offers of user with the passed internal identifier
        Connection connection = connect();
        PreparedStatement ps = connection.prepareStatement(GET_ACTIVE_REQUESTS);
        ResultSet rs = ps.executeQuery();

        ArrayList<Request> requests = new ArrayList<>();
        Request request;
        while (rs.next()) {
            String departurePointCityIATA = getCityIATACodeViaId(rs.getLong("departure_point_id"));
            String destinationPointCityIATA = getCityIATACodeViaId(rs.getLong("destination_point_id"));
            String departurePointCountryIATA = getCountryIATACodeViaId(rs.getLong("departure_point_country_id"));
            String destinationPointCountryIATA = getCountryIATACodeViaId(rs.getLong("destination_point_country_id"));
            request = Request.builder()
                    .setRequestId(rs.getLong("id"))
                    .setIsActive(rs.getBoolean("is_active"))
                    .setDeparturePointCountryName(departurePointCountryIATA)
                    .setDestinationPointCountryName(destinationPointCountryIATA)
                    .setDeparturePointName(departurePointCityIATA)
                    .setDestinationPointName(destinationPointCityIATA)
                    .setStartDate(rs.getDate("start_date"))
                    .setEndDate(rs.getDate("end_date"))
                    .setWithLuggage(rs.getBoolean("is_need_luggage"))
                    .setTicketMaxCost(rs.getInt("ticket_max_cost"))
                    .setChangesCount(rs.getShort("changes_count"))
                    .build();
            requests.add(request);
        }

        return requests;
    }

    public ArrayList<Request> getNonActiveRequests() throws SQLException {
        //TODO the method should return list of non active offers of user with the passed internal identifier
        Connection connection = connect();
        PreparedStatement ps = connection.prepareStatement(GET_NON_ACTIVE_REQUESTS);
        ResultSet rs = ps.executeQuery();

        ArrayList<Request> requests = new ArrayList<>();
        Request request;
        while (rs.next()) {
            String departurePointCityIATA = getCityIATACodeViaId(rs.getLong("departure_point_id"));
            String destinationPointCityIATA = getCityIATACodeViaId(rs.getLong("destination_point_id"));
            String departurePointCountryIATA = getCountryIATACodeViaId(rs.getLong("departure_point_country_id"));
            String destinationPointCountryIATA = getCountryIATACodeViaId(rs.getLong("destination_point_country_id"));
            request = Request.builder()
                    .setRequestId(rs.getLong("id"))
                    .setIsActive(rs.getBoolean("is_active"))
                    .setDeparturePointCountryName(departurePointCountryIATA)
                    .setDestinationPointCountryName(destinationPointCountryIATA)
                    .setDeparturePointName(departurePointCityIATA)
                    .setDestinationPointName(destinationPointCityIATA)
                    .setStartDate(rs.getDate("start_date"))
                    .setEndDate(rs.getDate("end_date"))
                    .setWithLuggage(rs.getBoolean("is_need_luggage"))
                    .setTicketMaxCost(rs.getInt("ticket_max_cost"))
                    .setChangesCount(rs.getShort("changes_count"))
                    .build();
            requests.add(request);
        }

        return requests;
    }

    public boolean addRequest(long internalId, Request requestFields) throws SQLException {
        //TODO the method should create a new offer for user with passed internal identifier if offer with the passed fields for this user not exist
        Connection connection = connect();

        long departurePointCountryIATA = getIATAIdForCountryViaCountryName(requestFields.getDeparturePointCountryName());
        long destinationPointCountryIATA = getIATAIdForCountryViaCountryName(requestFields.getDestinationPointCountryName());
        long departurePointCityIATA = getIATAIdForCityViaCityName(requestFields.getDeparturePointName(), getCityIATACodeViaId(destinationPointCountryIATA));
        long destinationPointCityIATA = getIATAIdForCityViaCityName(requestFields.getDestinationPointName(), getCityIATACodeViaId(destinationPointCountryIATA));

        PreparedStatement ps = connection.prepareStatement(DISABLE_REQUEST_VIA_ID);
        ps.setBoolean(1, requestFields.isActive());
        ps.setLong(2, requestFields.getUserInternalId());
        ps.setLong(3, departurePointCountryIATA);
        ps.setLong(4, destinationPointCountryIATA);
        ps.setLong(5, departurePointCityIATA);
        ps.setLong(6, destinationPointCityIATA);
        ps.setDate(7, requestFields.getStartDate());
        ps.setDate(8, requestFields.getEndDate());
        ps.setBoolean(9, requestFields.isWithLuggage());
        ps.setShort(10, requestFields.getChangesCount());
        ps.setInt(11, requestFields.getChangesCount());
        ps.executeQuery();

        return true;
    }

    public boolean disableRequest(long requestId) throws SQLException {
        //TODO the method should set field isActive to false of offer with the passed identifier
        Connection connection = connect();

        PreparedStatement ps = connection.prepareStatement(DISABLE_REQUEST_VIA_ID);
        ps.setLong(1, requestId);
        ps.executeQuery();
        return true;
    }

    public boolean enableRequest(long requestId) throws SQLException {
        //TODO the method should set field isActive to true of offer with the passed identifier
        Connection connection = connect();

        PreparedStatement ps = connection.prepareStatement(ENABLE_REQUEST_VIA_ID);
        ps.setLong(1, requestId);
        ps.executeQuery();
        return true;
    }

    public boolean isActiveRequest(long requestId) throws SQLException {
        //TODO the method should return the state of the "isActive" field from table "offers" of offer with the passed identifier
        Connection connection = connect();

        PreparedStatement ps = connection.prepareStatement(IS_ACTIVE_REQUEST_VIA_ID);
        ps.setLong(1, requestId);
        ResultSet check = ps.executeQuery();

        return check.next();
    }

    /*
     * =====================================================================================
     *                               Request service section END
     * =====================================================================================
     */

}
