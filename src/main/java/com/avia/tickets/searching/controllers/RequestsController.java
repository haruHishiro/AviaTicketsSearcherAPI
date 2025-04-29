/*
 * Controller for processing of logic of work with user requests
 * Developers: k.d.panov@gmail.com
 */

package com.avia.tickets.searching.controllers;


import com.avia.tickets.searching.models.*;
import com.avia.tickets.searching.response.Response;
import com.avia.tickets.searching.services.RequestsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;


@RestController
@RequestMapping("/api/v1/requests")
public class RequestsController {

    private final RequestsService requestsService;

    @Autowired
    public RequestsController(RequestsService requestsService) {
        this.requestsService = requestsService;
    }

    @GetMapping("/getUserRequests")
    public Response getUserRequests(@RequestParam long userId, @RequestParam boolean isInternalId,
                                    HttpServletRequest httpServletRequest) {
        Response response;
        try {
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("success")
                    .setResponseBody(new RequestsList(requestsService.getUserRequests(userId, isInternalId)))
                    .build();
            System.out.println("[IP] " + httpServletRequest.getRemoteAddr() + " [LOG] [getUserRequests] [SUCCESS]");
        } catch (SQLException e) {
            System.out.println(e);
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("error")
                    .build();
            System.out.println("\u001B[31m" + "[IP] " + httpServletRequest.getRemoteAddr() +
                    " [LOG] [getUserRequests] [ERROR] [userId] " + userId + " [isInternalId] " + isInternalId + "\u001B[0m");
        }
        return response;
    }

    @GetMapping("/getAllRequests")
    public Response getAllRequests(HttpServletRequest httpServletRequest) {
        Response response;
        try {
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("success")
                    .setResponseBody(new RequestsList(requestsService.getAllRequests()))
                    .build();
            System.out.println("[IP] " + httpServletRequest.getRemoteAddr() + " [LOG] [getUserRequests] [SUCCESS]");
        } catch (SQLException e) {
            System.out.println(e);
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("error")
                    .build();
            System.out.println("\u001B[31m" + "[IP] " + httpServletRequest.getRemoteAddr() + " [LOG] [getUserRequests] [ERROR]" + "\u001B[0m");
        }
        return response;
    }

    @GetMapping("/getActiveRequests")
    public Response getActiveRequests(HttpServletRequest httpServletRequest) {
        Response response;
        try {
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("success")
                    .setResponseBody(new RequestsList(requestsService.getActiveRequests()))
                    .build();
            System.out.println("[IP] " + httpServletRequest.getRemoteAddr() + " [LOG] [getActiveRequests] [SUCCESS]");
        } catch (SQLException e) {
            System.out.println(e);
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("error")
                    .build();
            System.out.println("\u001B[31m" + "[IP] " + httpServletRequest.getRemoteAddr() + " [LOG] [getUserRequests] [ERROR]" + "\u001B[0m");
        }
        return response;
    }

    @GetMapping("/getUserActiveRequests")
    public Response getUserActiveRequests(@RequestParam long userId, @RequestParam boolean isInternalId,
                                          HttpServletRequest httpServletRequest) {
        Response response;
        try {
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("success")
                    .setResponseBody(new RequestsList(requestsService.getUserActiveRequests(userId, isInternalId)))
                    .build();
            System.out.println("[IP] " + httpServletRequest.getRemoteAddr() + " [LOG] [getUserActiveRequests] [SUCCESS]");
        } catch (SQLException e) {
            System.out.println(e);
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("error")
                    .build();
            System.out.println("\u001B[31m" + "[IP] " + httpServletRequest.getRemoteAddr() +
                    " [LOG] [getUserActiveRequests] [ERROR] [userId] " + userId + " [isInternalId] " + isInternalId + "\u001B[0m");
        }
        return response;
    }

    @GetMapping("/getUserNonActiveRequests")
    public Response getUserNonActiveRequests(@RequestParam long userId, @RequestParam boolean isInternalId,
                                             HttpServletRequest httpServletRequest) {
        Response response = new Response();
        try {
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("success")
                    .setResponseBody(new RequestsList(requestsService.getUserNonActiveRequests(userId, isInternalId)))
                    .build();
            System.out.println("[IP] " + httpServletRequest.getRemoteAddr() + " [LOG] [getUserNonActiveRequests] [SUCCESS]");
        } catch (SQLException e) {
            System.out.println(e);
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("error")
                    .build();
            System.out.println("\u001B[31m" + "[IP] " + httpServletRequest.getRemoteAddr() +
                    " [LOG] [getUserNonActiveRequests] [ERROR] [userId] " + userId + " [isInternalId] " + isInternalId + "\u001B[0m");
        }
        return response;
    }

    @GetMapping("/addRequest")
    public Response addRequest(@RequestParam long userId, @RequestParam boolean isInternalId, @RequestParam String departureCity,
                               @RequestParam String destinationCity, @RequestParam String startDate, @RequestParam String endDate,
                               @RequestParam boolean withLuggage, @RequestParam int ticketMaxCost, @RequestParam short changesCount,
                               @RequestParam String destinationCountry, @RequestParam String departureCountry, @RequestParam boolean isDirect,
                               HttpServletRequest httpServletRequest) {
        Response response;
        try {
            Request request;
            request = Request.builder()
                    .setDestinationPointCountryName(destinationCountry)
                    .setDeparturePointCountryName(departureCountry)
                    .setDeparturePointCityName(departureCity)
                    .setDestinationPointCityName(destinationCity)
                    .setStartDate(Date.valueOf(startDate))
                    .setEndDate(Date.valueOf(endDate))
                    .setWithLuggage(withLuggage)
                    .setTicketMaxCost(ticketMaxCost)
                    .setChangesCount(changesCount)
                    .setIsDirect(isDirect)
                    .build();

            //System.out.println("userId: " + userId);
            requestsService.addRequest(userId, isInternalId, request);
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("success")
                    .build();
            System.out.println("[IP] " + httpServletRequest.getRemoteAddr() + " [LOG] [addRequest] [SUCCESS] [userId] " + userId + " [isInternalId] " + isInternalId +
                    " [departureCity] " + departureCity + " [destinationCity] " + destinationCity + " [startDate] " + startDate +
                    " [endDate] " + startDate + " [withLuggage] " + withLuggage + " [ticketMaxCost] " + ticketMaxCost +
                    " [changesCount] " + changesCount + " [destinationCountry] " + destinationCountry +
                    " [departureCountry] " + departureCountry + " [isDirect] " + isDirect);
        } catch (SQLException e) {
            System.out.println(e);
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("error")
                    .build();
            System.out.println("\u001B[31m" + "[IP] " + httpServletRequest.getRemoteAddr() + " [LOG] [addRequest] [ERROR] [userId] " + userId + " [isInternalId] " + isInternalId +
                    " [departureCity] " + departureCity + " [destinationCity] " + destinationCity + " [startDate] " + startDate +
                    " [endDate] " + startDate + " [withLuggage] " + withLuggage + " [ticketMaxCost] " + ticketMaxCost +
                    " [changesCount] " + changesCount + " [destinationCountry] " + destinationCountry +
                    " [departureCountry] " + departureCountry + " [isDirect] " + isDirect + "\u001B[0m");
        }
        return response;
    }

    @GetMapping("/disableRequest")
    public Response disableRequest(@RequestParam long requestId, HttpServletRequest httpServletRequest) {
        Response response;
        try {
            requestsService.disableRequest(requestId);
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("success")
                    .build();
            System.out.println("[IP] " + httpServletRequest.getRemoteAddr() + " [LOG] [disableRequest] [SUCCESS]");
        } catch (SQLException e) {
            System.out.println(e);
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("error")
                    .build();
            System.out.println("\u001B[31m" + "[IP] " + httpServletRequest.getRemoteAddr() +
                    " [LOG] [disableRequest] [ERROR] [requestId] " + requestId + "\u001B[0m");
        }
        return response;
    }

    @GetMapping("/enableRequest")
    public Response enableRequest(@RequestParam long requestId, HttpServletRequest httpServletRequest) {
        Response response;
        try {
            requestsService.enableRequest(requestId);
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("success")
                    .build();
            System.out.println("[IP] " + httpServletRequest.getRemoteAddr() + " [LOG] [enableRequest] [SUCCESS]");
        } catch (SQLException e) {
            System.out.println(e);
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("error")
                    .build();
            System.out.println("\u001B[31m" + "[IP] " + httpServletRequest.getRemoteAddr() + " [LOG] [enableRequest] [ERROR] [requestId] " + requestId);
        }
        return response;
    }

    @GetMapping("/isActiveRequest")
    public Response isActiveRequest(@RequestParam long offerId, HttpServletRequest httpServletRequest) {
        Response response;
        try {
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("success")
                    .setResponseBody(new ValueBooleanModel(requestsService.isActiveRequest(offerId)))
                    .build();
            System.out.println("[IP] " + httpServletRequest.getRemoteAddr() + " [LOG] [isActiveRequest] [SUCCESS]");
        } catch (SQLException e) {
            System.out.println(e);
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("error")
                    .build();
            System.out.println("\u001B[31m" + "[IP] " + httpServletRequest.getRemoteAddr() +
                    " [LOG] [isActiveRequest] [ERROR] [offerId] " + offerId + "\u001B[0m");
        }
        return response;
    }

    @GetMapping("/getCities")
    public Response getCities(HttpServletRequest httpServletRequest) {
        Response response;
        try {
            ArrayList<City> cities = requestsService.getCities();
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("success")
                    .setResponseBody(new CitiesList(cities))
                    .build();
            System.out.println("[IP] " + httpServletRequest.getRemoteAddr() + " [LOG] [getCities] [SUCCESS] [ELEMENTS NUMBER] " + cities.size());
        } catch (SQLException e) {
            System.out.println(e);
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("error")
                    .build();
            System.out.println("\u001B[31m" + "[IP] " + httpServletRequest.getRemoteAddr() +
                    " [LOG] [getCities] [ERROR]" + "\u001B[0m");
        }
        return response;
    }

    @GetMapping("/getCountries")
    public Response getCountries(HttpServletRequest httpServletRequest) {
        Response response;
        try {
            ArrayList<Country> countries = requestsService.getCountries();
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("success")
                    .setResponseBody(new CountriesList(countries))
                    .build();
            System.out.println("[IP] " + httpServletRequest.getRemoteAddr() + " [LOG] [getCountries] [SUCCESS] [ELEMENTS NUMBER] " + countries.size());
        } catch (SQLException e) {
            System.out.println(e);
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("error")
                    .build();
            System.out.println("\u001B[31m" + "[IP] " + httpServletRequest.getRemoteAddr() + " [LOG] [getCountries] [ERROR]" + "\u001B[0m");
        }
        return response;
    }

    @GetMapping("/getOwnerTelegramIdViaRequestId")
    public Response getOwnerTelegramIdViaRequestId(@RequestParam long requestId,
                                                   HttpServletRequest httpServletRequest) {
        Response response;
        try {
            long userId = requestsService.getOwnerTelegramIdViaRequestId(requestId);
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("success")
                    .setResponseBody(new ValueLongModel(userId))
                    .build();
            System.out.println("[IP] " + httpServletRequest.getRemoteAddr() +
                    " [LOG] [getOwnerTelegramIdViaRequestId] [SUCCESS] [requestId] " + requestId + " [userId] " + userId);
        } catch (SQLException e) {
            System.out.println(e);
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("error")
                    .build();
            System.out.println("\u001B[31m" + "[IP] " + httpServletRequest.getRemoteAddr() +
                    " [LOG] [getOwnerTelegramIdViaRequestId] [ERROR] [requestId] " + requestId + "\u001B[0m");
        }
        return response;
    }
}
