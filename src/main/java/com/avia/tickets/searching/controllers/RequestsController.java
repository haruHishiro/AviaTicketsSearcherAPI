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
        Response response = new Response();
        try {
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("method in develop")
                    .setResponseBody(new RequestsList(requestsService.getUserRequests(userId, isInternalId)))
                    .build();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return response;
    }

    @GetMapping("/getUserActiveRequests")
    public Response getUserActiveRequests(@RequestParam long userId, @RequestParam boolean isInternalId) {
        Response response = new Response();
        try {
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("method in develop")
                    .setResponseBody(new RequestsList(requestsService.getUserActiveRequests(userId, isInternalId)))
                    .build();
        } catch (SQLException e) {
            System.out.println(e);
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
                    .setDescription("method in develop")
                    .setResponseBody(new RequestsList(requestsService.getUserNonActiveRequests(userId, isInternalId)))
                    .build();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return response;
    }

    @GetMapping("/addRequest")
    public Response addRequest(@RequestParam long userId, @RequestParam boolean isInternalId, @RequestParam String departure,
                               @RequestParam String destination, @RequestParam Date startDate, @RequestParam Date endDate,
                               @RequestParam boolean withLuggage, @RequestParam int ticketMaxCost, @RequestParam short changesCount,
                               @RequestParam String destinationCountry, @RequestParam String departureCountry, @RequestParam boolean isDirect) {
        Response response = new Response();
        try {
            Request request;
            request = Request.builder()
                    .setDestinationPointCountryName(destinationCountry)
                    .setDeparturePointCountryName(departureCountry)
                    .setDeparturePointName(departure)
                    .setDestinationPointName(destination)
                    .setStartDate(startDate)
                    .setEndDate(endDate)
                    .setWithLuggage(withLuggage)
                    .setTicketMaxCost(ticketMaxCost)
                    .setChangesCount(changesCount)
                    .setIsDirect(isDirect)
                    .build();

            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("method in develop")
                    .setResponseBody(new ValueBooleanModel(requestsService.addRequest(userId, isInternalId, request)) {
                    })
                    .build();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return response;
    }

    @GetMapping("/disableRequest")
    public Response disableRequest(@RequestParam long requestId) {
        Response response = new Response();
        try {
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("method in develop")
                    .setResponseBody(new ValueBooleanModel(requestsService.disableRequest(requestId)))
                    .build();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return response;
    }

    @GetMapping("/enableRequest")
    public Response enableRequest(@RequestParam long requestId) {
        Response response = new Response();
        try {
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("method in develop")
                    .setResponseBody(new ValueBooleanModel(requestsService.enableRequest(requestId)))
                    .build();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return response;
    }

    @GetMapping("/isActiveRequest")
    public Response isActiveRequest(@RequestParam long offerId) {
        Response response = new Response();
        try {
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("method in develop")
                    .setResponseBody(new ValueBooleanModel(requestsService.isActiveRequest(offerId)))
                    .build();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return response;
    }
}
