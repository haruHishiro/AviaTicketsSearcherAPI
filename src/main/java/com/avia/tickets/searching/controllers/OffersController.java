/*
 * Controller for processing of logic of work with user requests
 * Developers: Panov K.D. ;
 * Last change: february 2025
 */

package com.avia.tickets.searching.controllers;


import com.avia.tickets.searching.models.BaseModel;
import com.avia.tickets.searching.models.Offer;
import com.avia.tickets.searching.models.OffersList;
import com.avia.tickets.searching.models.ValueBooleanModel;
import com.avia.tickets.searching.response.Response;
import com.avia.tickets.searching.services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.sql.SQLException;


@RestController
@RequestMapping("/api/v1/offers")
public class OffersController {

    private final OfferService offerService;

    @Autowired
    public OffersController(OfferService offerService){
        this.offerService = offerService;
    }

    @GetMapping("/getUserOffers")
    public Response getUserOffers(@RequestParam long userId, @RequestParam boolean isInternalId){
        Response response = new Response();
        try {
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("method in develop")
                    .setResponseBody(new OffersList(offerService.getUserOffers(userId, isInternalId)))
                    .build();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return response;
    }

    @GetMapping("/getUserActiveOffers")
    public Response getUserActiveOffers(@RequestParam long userId, @RequestParam boolean isInternalId){
        Response response = new Response();
        try {
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("method in develop")
                    .setResponseBody(new OffersList(offerService.getUserActiveOffers(userId, isInternalId)))
                    .build();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return response;
    }

    @GetMapping("/getUserNonActiveOffers")
    public Response getUserNonActiveOffers(@RequestParam long userId, @RequestParam boolean isInternalId){
        Response response = new Response();
        try {
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("method in develop")
                    .setResponseBody(new OffersList(offerService.getUserNonActiveOffers(userId, isInternalId)))
                    .build();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return response;
    }

    @GetMapping("/addOffer")
    public Response addOffer(@RequestParam long userId, @RequestParam boolean isInternalId, @RequestParam String departure,
                             @RequestParam String destination, @RequestParam Date startDate, @RequestParam Date endDate,
                             @RequestParam boolean withLuggage, @RequestParam int ticketMaxCost, @RequestParam short changesCount){
        Response response = new Response();
        try {
            Offer offer = Offer.builder()
                    .setDeparturePointName(departure)
                    .setDestinationPointName(destination)
                    .setStartDate(startDate)
                    .setEndDate(endDate)
                    .setWithLuggage(withLuggage)
                    .setTicketMaxCost(ticketMaxCost)
                    .setChangesCount(changesCount)
                    .build();;

            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("method in develop")
                    .setResponseBody(new ValueBooleanModel(offerService.addOffer(userId, isInternalId, offer)) {
                    })
                    .build();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return response;
    }

    @GetMapping("/disableOffer")
    public Response disableOffer(@RequestParam long offerId){
        Response response = new Response();
        try {
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("method in develop")
                    .setResponseBody(new ValueBooleanModel(offerService.disableOffer(offerId)))
                    .build();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return response;
    }

    @GetMapping("/enableOffer")
    public Response enableOffer(@RequestParam long offerId){
        Response response = new Response();
        try {
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("method in develop")
                    .setResponseBody(new ValueBooleanModel(offerService.enableOffer(offerId)))
                    .build();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return response;
    }

    @GetMapping("/isActiveOffer")
    public Response isActiveOffer(@RequestParam long offerId){
        Response response = new Response();
        try {
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("method in develop")
                    .setResponseBody(new ValueBooleanModel(offerService.isActiveOffer(offerId)))
                    .build();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return response;
    }
}
