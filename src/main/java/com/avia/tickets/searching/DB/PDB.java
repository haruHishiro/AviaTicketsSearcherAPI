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
    private static final String url = "jdbc:postgresql://localhost/AviaTicketsSearcher";
    private static final String user = "postgres";
    private static final String password = "hasker1234";


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
            "start_date, end_date, is_need_luggage, changes_count, ticket_max_cost, is_direct)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String ENABLE_REQUEST_VIA_ID = "UPDATE requests SET is_active = true WHERE telegramId = ?";
    private static final String DISABLE_REQUEST_VIA_ID = "UPDATE requests SET is_active = false WHERE telegramId = ?";
    private static final String IS_ACTIVE_REQUEST_VIA_ID = "SELECT is_active FROM requests WHERE id = ?";
    private static final String GET_COUNTRY_IATA_CODE_VIA_ID = "SELECT iata_code FROM iata_codes_country WHERE id = ?";
    private static final String GET_CITY_IATA_CODE_VIA_ID = "SELECT iata_code FROM iata_codes_city WHERE id = ?";
    private static final String GET_IATA_ID_FOR_COUNTRY_VIA_COUNTRY_NAME = "SELECT id FROM iata_codes_country WHERE country_name = ?";
    private static final String GET_IATA_ID_FOR_CITY_VIA_CITY_NAME = "SELECT id FROM iata_codes_city WHERE city_name = ? AND country_code = ?";

    /*
     * =====================================================================================
     *                               User service section START
     * =====================================================================================
     */


    public boolean isActiveUserViaTelegramId(long telegramId) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_IS_ACTIVE_USER_VIA_TELEGRAM_ID)) {
            preparedStatement.setLong(1, telegramId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getBoolean("is_active");
            } else {
                return Boolean.parseBoolean(null);
            }
        }
    }

    public boolean isActiveUserViaInternalId(long internalId) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_IS_ACTIVE_USER_VIA_INTERNAL_ID)) {
            preparedStatement.setLong(1, internalId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getBoolean("is_active");
            } else {
                return Boolean.parseBoolean(null);
            }
        }
    }

    public boolean disableUserViaTelegramId(long telegramId) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(DISABLE_USER_VIA_INTERNAL_ID)) {
            preparedStatement.setLong(1, telegramId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getBoolean("is_active");
            } else {
                return Boolean.parseBoolean(null);
            }
        }
    }

    public boolean disableUserViaInternalId(long internalId) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(DISABLE_USER_VIA_INTERNAL_ID)) {
            preparedStatement.setLong(1, internalId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getBoolean("is_active");
            } else {
                return Boolean.parseBoolean(null);
            }
        }
    }

    public boolean enableUserViaTelegramId(long telegramId) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(ENABLE_USER_VIA_INTERNAL_ID)) {
            preparedStatement.setLong(1, telegramId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getBoolean("is_active");
            } else {
                return Boolean.parseBoolean(null);
            }
        }
    }

    public boolean enableUserViaInternalId(long internalId) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(ENABLE_USER_VIA_INTERNAL_ID)) {
            preparedStatement.setLong(1, internalId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getBoolean("is_active");
            } else {
                return Boolean.parseBoolean(null);
            }
        }
    }

    public void createUser(long telegramId) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER)) {
            preparedStatement.setLong(1, telegramId);
            preparedStatement.executeUpdate();
        }
    }

    public boolean isUserExistViaTelegramId(long telegramId) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_USER_IN_DB)) {
            preparedStatement.setLong(1, telegramId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }
    }

    public boolean isUserExistViaInternalId(long internalId) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_USER_IN_DB)) {
            preparedStatement.setLong(1, internalId);

            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        }
    }

    public long getInternalIdViaTelegramId(long telegramId) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(GET_INTERNAL_ID)) {
            preparedStatement.setLong(1, telegramId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong("id");
            } else {
                return Long.parseLong(null);
            }
        }
    }

    public long getTelegramIdViaInternalId(long internalId) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(GET_INTERNAL_ID)) {
            preparedStatement.setLong(1, internalId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong("id");
            } else {
                return Long.parseLong(null);
            }
        }
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

    public String getCityIATACodeViaId(long id) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(GET_CITY_IATA_CODE_VIA_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("iata_code");
            } else {
                return null;
            }
        }
    }

    public String getCountryIATACodeViaId(long id) throws SQLException{
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(GET_COUNTRY_IATA_CODE_VIA_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return resultSet.getString("iata_code");
            } else {
                return null;
            }
        }
    }

    public long getIATAIdForCountryViaCountryName(String countryName) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(GET_COUNTRY_IATA_CODE_VIA_ID)) {
            preparedStatement.setString(1, countryName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong("id");
            } else {
                return Long.parseLong(null);
            }
        }
    }

    public long getIATAIdForCityViaCityName(String city, String countryCode) throws SQLException{
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(GET_IATA_ID_FOR_CITY_VIA_CITY_NAME)) {
            preparedStatement.setString(1, city);
            preparedStatement.setString(2, countryCode);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong("id");
            } else {
                return Long.parseLong(null);
            }
        }
    }

    public ArrayList<Request> getUserRequestsViaInternalId(long internalId) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_ALL_REQUESTS)) {
            preparedStatement.setLong(1, internalId);
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<Request> requests = new ArrayList<>();
            Request request;
            while (resultSet.next()) {
                String departurePointCityIATA = getCityIATACodeViaId(resultSet.getLong("departure_point_id"));
                String destinationPointCityIATA = getCityIATACodeViaId(resultSet.getLong("destination_point_id"));
                String departurePointCountryIATA = getCountryIATACodeViaId(resultSet.getLong("departure_point_country_id"));
                String destinationPointCountryIATA = getCountryIATACodeViaId(resultSet.getLong("destination_point_country_id"));
                request = Request.builder()
                        .setRequestId(resultSet.getLong("id"))
                        .setIsActive(resultSet.getBoolean("is_active"))
                        .setDeparturePointCountryName(departurePointCountryIATA)
                        .setDestinationPointCountryName(destinationPointCountryIATA)
                        .setDeparturePointName(departurePointCityIATA)
                        .setDestinationPointName(destinationPointCityIATA)
                        .setStartDate(resultSet.getDate("start_date"))
                        .setEndDate(resultSet.getDate("end_date"))
                        .setWithLuggage(resultSet.getBoolean("is_need_luggage"))
                        .setTicketMaxCost(resultSet.getInt("ticket_max_cost"))
                        .setChangesCount(resultSet.getShort("changes_count"))
                        .setIsDirect(resultSet.getBoolean("is_direct"))
                        .build();
                requests.add(request);
            }

            return requests;
        }
    }

    public ArrayList<Request> getUserActiveRequestsViaInternalId(long internalId) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_ACTIVE_REQUESTS)) {
            preparedStatement.setLong(1, internalId);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("here1");
            ArrayList<Request> requests = new ArrayList<>();
            Request request;
            while (resultSet.next()) {
                long dept_id = resultSet.getLong("departure_point_id");
                System.out.println(dept_id);
                String departurePointCityIATA = getCityIATACodeViaId(dept_id);
                String destinationPointCityIATA = getCityIATACodeViaId(resultSet.getLong("destination_point_id"));
                String departurePointCountryIATA = getCountryIATACodeViaId(resultSet.getLong("departure_point_country_id"));
                String destinationPointCountryIATA = getCountryIATACodeViaId(resultSet.getLong("destination_point_country_id"));

                request = Request.builder()
                        .setRequestId(resultSet.getLong("id"))
                        .setIsActive(resultSet.getBoolean("is_active"))
                        .setDeparturePointCountryName(departurePointCountryIATA)
                        .setDestinationPointCountryName(destinationPointCountryIATA)
                        .setDeparturePointName(departurePointCityIATA)
                        .setDestinationPointName(destinationPointCityIATA)
                        .setStartDate(resultSet.getDate("start_date"))
                        .setEndDate(resultSet.getDate("end_date"))
                        .setWithLuggage(resultSet.getBoolean("is_need_luggage"))
                        .setTicketMaxCost(resultSet.getInt("ticket_max_cost"))
                        .setChangesCount(resultSet.getShort("changes_count"))
                        .setIsDirect(resultSet.getBoolean("is_direct"))
                        .build();
                requests.add(request);
            }

            return requests;
        }
    }

    public ArrayList<Request> getUserNonActiveRequestsViaInternalId(long internalId) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_NON_ACTIVE_REQUESTS)) {
            preparedStatement.setLong(1, internalId);
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<Request> requests = new ArrayList<>();
            Request request;
            while (resultSet.next()) {
                String departurePointCityIATA = getCityIATACodeViaId(resultSet.getLong("departure_point_id"));
                String destinationPointCityIATA = getCityIATACodeViaId(resultSet.getLong("destination_point_id"));
                String departurePointCountryIATA = getCountryIATACodeViaId(resultSet.getLong("departure_point_country_id"));
                String destinationPointCountryIATA = getCountryIATACodeViaId(resultSet.getLong("destination_point_country_id"));
                request = Request.builder()
                        .setRequestId(resultSet.getLong("id"))
                        .setIsActive(resultSet.getBoolean("is_active"))
                        .setDeparturePointCountryName(departurePointCountryIATA)
                        .setDestinationPointCountryName(destinationPointCountryIATA)
                        .setDeparturePointName(departurePointCityIATA)
                        .setDestinationPointName(destinationPointCityIATA)
                        .setStartDate(resultSet.getDate("start_date"))
                        .setEndDate(resultSet.getDate("end_date"))
                        .setWithLuggage(resultSet.getBoolean("is_need_luggage"))
                        .setTicketMaxCost(resultSet.getInt("ticket_max_cost"))
                        .setChangesCount(resultSet.getShort("changes_count"))
                        .setIsDirect(resultSet.getBoolean("is_direct"))
                        .build();
                requests.add(request);
            }

            return requests;
        }
    }

    public ArrayList<Request> getRequests() throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_REQUESTS)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<Request> requests = new ArrayList<>();
            Request request;
            while (resultSet.next()) {
                String departurePointCityIATA = getCityIATACodeViaId(resultSet.getLong("departure_point_id"));
                String destinationPointCityIATA = getCityIATACodeViaId(resultSet.getLong("destination_point_id"));
                String departurePointCountryIATA = getCountryIATACodeViaId(resultSet.getLong("departure_point_country_id"));
                String destinationPointCountryIATA = getCountryIATACodeViaId(resultSet.getLong("destination_point_country_id"));
                request = Request.builder()
                        .setRequestId(resultSet.getLong("id"))
                        .setIsActive(resultSet.getBoolean("is_active"))
                        .setDeparturePointCountryName(departurePointCountryIATA)
                        .setDestinationPointCountryName(destinationPointCountryIATA)
                        .setDeparturePointName(departurePointCityIATA)
                        .setDestinationPointName(destinationPointCityIATA)
                        .setStartDate(resultSet.getDate("start_date"))
                        .setEndDate(resultSet.getDate("end_date"))
                        .setWithLuggage(resultSet.getBoolean("is_need_luggage"))
                        .setTicketMaxCost(resultSet.getInt("ticket_max_cost"))
                        .setChangesCount(resultSet.getShort("changes_count"))
                        .setIsDirect(resultSet.getBoolean("is_direct"))
                        .build();
                requests.add(request);
            }

            return requests;
        }
    }

    public ArrayList<Request> getActiveRequests() throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ACTIVE_REQUESTS)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<Request> requests = new ArrayList<>();
            Request request;
            while (resultSet.next()) {
                String departurePointCityIATA = getCityIATACodeViaId(resultSet.getLong("departure_point_id"));
                String destinationPointCityIATA = getCityIATACodeViaId(resultSet.getLong("destination_point_id"));
                String departurePointCountryIATA = getCountryIATACodeViaId(resultSet.getLong("departure_point_country_id"));
                String destinationPointCountryIATA = getCountryIATACodeViaId(resultSet.getLong("destination_point_country_id"));
                request = Request.builder()
                        .setRequestId(resultSet.getLong("id"))
                        .setIsActive(resultSet.getBoolean("is_active"))
                        .setDeparturePointCountryName(departurePointCountryIATA)
                        .setDestinationPointCountryName(destinationPointCountryIATA)
                        .setDeparturePointName(departurePointCityIATA)
                        .setDestinationPointName(destinationPointCityIATA)
                        .setStartDate(resultSet.getDate("start_date"))
                        .setEndDate(resultSet.getDate("end_date"))
                        .setWithLuggage(resultSet.getBoolean("is_need_luggage"))
                        .setTicketMaxCost(resultSet.getInt("ticket_max_cost"))
                        .setChangesCount(resultSet.getShort("changes_count"))
                        .setIsDirect(resultSet.getBoolean("is_direct"))
                        .build();
                requests.add(request);
            }

            return requests;
        }
    }

    public ArrayList<Request> getNonActiveRequests() throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(GET_NON_ACTIVE_REQUESTS)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<Request> requests = new ArrayList<>();
            Request request;
            while (resultSet.next()) {
                String departurePointCityIATA = getCityIATACodeViaId(resultSet.getLong("departure_point_id"));
                String destinationPointCityIATA = getCityIATACodeViaId(resultSet.getLong("destination_point_id"));
                String departurePointCountryIATA = getCountryIATACodeViaId(resultSet.getLong("departure_point_country_id"));
                String destinationPointCountryIATA = getCountryIATACodeViaId(resultSet.getLong("destination_point_country_id"));
                request = Request.builder()
                        .setRequestId(resultSet.getLong("id"))
                        .setIsActive(resultSet.getBoolean("is_active"))
                        .setDeparturePointCountryName(departurePointCountryIATA)
                        .setDestinationPointCountryName(destinationPointCountryIATA)
                        .setDeparturePointName(departurePointCityIATA)
                        .setDestinationPointName(destinationPointCityIATA)
                        .setStartDate(resultSet.getDate("start_date"))
                        .setEndDate(resultSet.getDate("end_date"))
                        .setWithLuggage(resultSet.getBoolean("is_need_luggage"))
                        .setTicketMaxCost(resultSet.getInt("ticket_max_cost"))
                        .setChangesCount(resultSet.getShort("changes_count"))
                        .setIsDirect(resultSet.getBoolean("is_direct"))
                        .build();
                requests.add(request);
            }

            return requests;
        }
    }

    public void addRequest(long internalId, Request requestFields) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_REQUEST)) {
            long departurePointCountryIATA = getIATAIdForCountryViaCountryName(requestFields.getDeparturePointCountryName());
            long destinationPointCountryIATA = getIATAIdForCountryViaCountryName(requestFields.getDestinationPointCountryName());
            long departurePointCityIATA = getIATAIdForCityViaCityName(requestFields.getDeparturePointName(), getCityIATACodeViaId(destinationPointCountryIATA));
            long destinationPointCityIATA = getIATAIdForCityViaCityName(requestFields.getDestinationPointName(), getCityIATACodeViaId(destinationPointCountryIATA));

            preparedStatement.setBoolean(1, requestFields.isActive());
            preparedStatement.setLong(2, requestFields.getUserInternalId());
            preparedStatement.setLong(3, departurePointCountryIATA);
            preparedStatement.setLong(4, destinationPointCountryIATA);
            preparedStatement.setLong(5, departurePointCityIATA);
            preparedStatement.setLong(6, destinationPointCityIATA);
            preparedStatement.setDate(7, requestFields.getStartDate());
            preparedStatement.setDate(8, requestFields.getEndDate());
            preparedStatement.setBoolean(9, requestFields.isWithLuggage());
            preparedStatement.setShort(10, requestFields.getChangesCount());
            preparedStatement.setInt(11, requestFields.getChangesCount());
            preparedStatement.setBoolean(12, requestFields.isDirect());
            preparedStatement.executeQuery();
        }
    }

    public boolean disableRequest(long requestId) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(DISABLE_REQUEST_VIA_ID)) {
            preparedStatement.setLong(1, requestId);
            preparedStatement.executeQuery();
            return true;
        }
    }

    public boolean enableRequest(long requestId) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(ENABLE_REQUEST_VIA_ID)) {
            preparedStatement.setLong(1, requestId);
            preparedStatement.executeQuery();
            return true;
        }
    }

    public boolean isActiveRequest(long requestId) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(IS_ACTIVE_REQUEST_VIA_ID)) {
            preparedStatement.setLong(1, requestId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }
    }

    /*
     * =====================================================================================
     *                               Request service section END
     * =====================================================================================
     */

}
