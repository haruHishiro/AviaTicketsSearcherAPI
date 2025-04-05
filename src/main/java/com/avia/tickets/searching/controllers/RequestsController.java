/*
 * Controller for processing of logic of work with user requests
 * Developers: Panov K.D. ;
 * Last change: february 2025
 */

package com.avia.tickets.searching.controllers;


import com.avia.tickets.searching.models.Request;
import com.avia.tickets.searching.models.RequestsList;
import com.avia.tickets.searching.models.ValueBooleanModel;
import com.avia.tickets.searching.response.Response;
import com.avia.tickets.searching.services.RequestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.sql.SQLException;


@RestController
@RequestMapping("/api/v1/requests")
public class RequestsController {

    private final RequestsService requestsService;

    @Autowired
    public RequestsController(RequestsService requestsService) {
        this.requestsService = requestsService;
    }

    @GetMapping("/getUserRequests")
    public Response getUserRequests(@RequestParam long userId, @RequestParam boolean isInternalId) {
        Response response;
        try {
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("success")
                    .setResponseBody(new RequestsList(requestsService.getUserRequests(userId, isInternalId)))
                    .build();
            System.out.println("[LOG] [getUserRequests] [SUCCESS]");
        } catch (SQLException e) {
            System.out.println(e);
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("error")
                    .build();
            System.out.println("[LOG] [getUserRequests] [ERROR] [userId] " + userId + " [isInternalId] " + isInternalId);
        }
        return response;
    }

    @GetMapping("/getUserActiveRequests")
    public Response getUserActiveRequests(@RequestParam long userId, @RequestParam boolean isInternalId) {
        Response response;
        try {
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("success")
                    .setResponseBody(new RequestsList(requestsService.getUserActiveRequests(userId, isInternalId)))
                    .build();
            System.out.println("[LOG] [getUserActiveRequests] [SUCCESS]");
        } catch (SQLException e) {
            System.out.println(e);
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("error")
                    .build();
            System.out.println("[LOG] [getUserActiveRequests] [ERROR] [userId] " + userId + " [isInternalId] " + isInternalId);
        }
        return response;
    }

    @GetMapping("/getUserNonActiveRequests")
    public Response getUserNonActiveRequests(@RequestParam long userId, @RequestParam boolean isInternalId) {
        Response response = new Response();
        try {
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("success")
                    .setResponseBody(new RequestsList(requestsService.getUserNonActiveRequests(userId, isInternalId)))
                    .build();
            System.out.println("[LOG] [getUserNonActiveRequests] [SUCCESS]");
        } catch (SQLException e) {
            System.out.println(e);
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("error")
                    .build();
            System.out.println("[LOG] [getUserNonActiveRequests] [ERROR] [userId] " + userId + " [isInternalId] " + isInternalId);
        }
        return response;
    }

    @GetMapping("/addRequest")
    public Response addRequest(@RequestParam long userId, @RequestParam boolean isInternalId, @RequestParam String departureCity,
                               @RequestParam String destinationCity, @RequestParam Date startDate, @RequestParam Date endDate,
                               @RequestParam boolean withLuggage, @RequestParam int ticketMaxCost, @RequestParam short changesCount,
                               @RequestParam String destinationCountry, @RequestParam String departureCountry, @RequestParam boolean isDirect) {
        Response response;
        try {
            Request request;
            request = Request.builder()
                    .setDestinationPointCountryName(destinationCountry)
                    .setDeparturePointCountryName(departureCountry)
                    .setDeparturePointCityName(departureCity)
                    .setDestinationPointCityName(destinationCity)
                    .setStartDate(startDate)
                    .setEndDate(endDate)
                    .setWithLuggage(withLuggage)
                    .setTicketMaxCost(ticketMaxCost)
                    .setChangesCount(changesCount)
                    .setIsDirect(isDirect)
                    .build();

            requestsService.addRequest(userId, isInternalId, request);
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("success")
                    .build();
            System.out.println("[LOG] [addRequest] [SUCCESS]");
        } catch (SQLException e) {
            System.out.println(e);
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("error")
                    .build();
            System.out.println("[LOG] [addRequest] [ERROR] [userId] " + userId + " [isInternalId] " + isInternalId +
                    " [departureCity] " + departureCity + " [destinationCity] " + destinationCity + " [startDate] " + startDate +
                    " [endDate] " + startDate + " [withLuggage] " + withLuggage + " [ticketMaxCost] " + ticketMaxCost +
                    " [changesCount] " + changesCount + " [destinationCountry] " + destinationCountry +
                    " [departureCountry] " + departureCountry + " [isDirect] " + isDirect);
        }
        return response;
    }

    @GetMapping("/disableRequest")
    public Response disableRequest(@RequestParam long requestId) {
        Response response;
        try {
            requestsService.disableRequest(requestId);
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("success")
                    .build();
            System.out.println("[LOG] [disableRequest] [SUCCESS]");
        } catch (SQLException e) {
            System.out.println(e);
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("error")
                    .build();
            System.out.println("[LOG] [disableRequest] [ERROR] [requestId] " + requestId);
        }
        return response;
    }

    @GetMapping("/enableRequest")
    public Response enableRequest(@RequestParam long requestId) {
        Response response;
        try {
            requestsService.enableRequest(requestId);
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("success")
                    .build();
            System.out.println("[LOG] [enableRequest] [SUCCESS]");
        } catch (SQLException e) {
            System.out.println(e);
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("error")
                    .build();
            System.out.println("[LOG] [enableRequest] [ERROR] [requestId] " + requestId);
        }
        return response;
    }

    @GetMapping("/isActiveRequest")
    public Response isActiveRequest(@RequestParam long offerId) {
        Response response;
        try {
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("success")
                    .setResponseBody(new ValueBooleanModel(requestsService.isActiveRequest(offerId)))
                    .build();
            System.out.println("[LOG] [isActiveRequest] [SUCCESS]");
        } catch (SQLException e) {
            System.out.println(e);
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("error")
                    .build();
            System.out.println("[LOG] [isActiveRequest] [ERROR] [offerId] " + offerId);
        }
        return response;
    }
}
