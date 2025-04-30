/*
 * Controller for processing parser queries
 * Developers: k.d.panov@gmail.com
 */

package com.avia.tickets.searching.controllers;


import com.avia.tickets.searching.models.StringModel;
import com.avia.tickets.searching.models.Ticket;
import com.avia.tickets.searching.models.TicketsList;
import com.avia.tickets.searching.models.ValueBooleanModel;
import com.avia.tickets.searching.response.Response;
import com.avia.tickets.searching.services.ParserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;


@RestController
@RequestMapping("/api/v1/parser")
public class ParserController {
    private final ParserService parserService;

    @Autowired
    public ParserController(ParserService parserService) {
        this.parserService = parserService;
    }

    @GetMapping("/getToken")
    public Response getToken(@RequestParam String site,
            HttpServletRequest httpServletRequest) {
        Response response;
        response = Response.builder()
                .setCode(200)
                .setStatus("OK")
                .setDescription("success")
                .setResponseBody(new StringModel(parserService.getToken(site)))
                .build();
        System.out.println("[IP] " + httpServletRequest.getRemoteAddr() +
                " [LOG] [setToken] [SUCCESS] [site] " + site);
        return response;
    }

    @GetMapping("/setToken")
    public Response setToken(@RequestParam String site, @RequestParam String token, HttpServletRequest httpServletRequest) {
        parserService.setToken(site, token);
        Response response;
        response = Response.builder()
                .setCode(200)
                .setStatus("OK")
                .setDescription("success")
                .setResponseBody(new StringModel("success"))
                .build();
        System.out.println("[IP] " + httpServletRequest.getRemoteAddr() +
                " [LOG] [setToken] [SUCCESS] [site] " + site + " [token] " + token);
        return response;
    }

    @GetMapping("/getTickets")
    public Response getTickets(HttpServletRequest httpServletRequest) {
        Response response;
        response = Response.builder()
                .setCode(200)
                .setStatus("OK")
                .setDescription("success")
                .setResponseBody(new TicketsList(parserService.getTickets()))
                .build();
        System.out.println("[IP] " + httpServletRequest.getRemoteAddr() + " [LOG] [getTickets] [SUCCESS]");
        return response;
    }

    @GetMapping("/addTicket")
    public Response addTicket(
            @RequestParam String flight_number,
            @RequestParam String link,
            @RequestParam String origin_airport,
            @RequestParam String destination_airport,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departure_at,
            @RequestParam String airline,
            @RequestParam String destination,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime return_at,
            @RequestParam String origin,
            @RequestParam int price,
            @RequestParam int return_transfers,
            @RequestParam int transfers,
            @RequestParam long forRequest,
            HttpServletRequest httpServletRequest) {

        OffsetDateTime departureOffset = departure_at.atOffset(ZoneOffset.UTC);
        OffsetDateTime returnOffset = return_at.atOffset(ZoneOffset.UTC);

        // Создание объекта Ticket
        Ticket ticket = new Ticket(
                flight_number,
                link,
                origin_airport,
                destination_airport,
                departureOffset,
                airline,
                destination,
                returnOffset,
                origin,
                price,
                return_transfers, // количество пересадок на обратном пути
                transfers, // количество пересадок на прямом рейсе
                forRequest
        );

        parserService.addTicket(ticket);

        Response response = Response.builder()
                .setCode(200)
                .setStatus("OK")
                .setDescription("success")
                .build();

        System.out.println("[IP] " + httpServletRequest.getRemoteAddr() + " [LOG] [addTicket] [SUCCESS]");
        return response;
    }

    @GetMapping("/clearTickets")
    public Response clearTickets(HttpServletRequest httpServletRequest) {
        Response response;
        parserService.clearTickets();
        response = Response.builder()
                .setCode(200)
                .setStatus("OK")
                .setDescription("success")
                .build();
        System.out.println("[IP] " + httpServletRequest.getRemoteAddr() + " [LOG] [clearTickets] [SUCCESS]");
        return response;
    }

}
