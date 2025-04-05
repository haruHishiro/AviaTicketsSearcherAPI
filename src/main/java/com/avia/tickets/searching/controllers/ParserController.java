/*
 * Controller for processing parser queries
 * Developers: Panov K.D. ;
 * Last change: february 2025
 */

package com.avia.tickets.searching.controllers;


import com.avia.tickets.searching.models.StringModel;
import com.avia.tickets.searching.models.ValueBooleanModel;
import com.avia.tickets.searching.response.Response;
import com.avia.tickets.searching.services.ParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/parser")
public class ParserController {
    private final ParserService parserService;

    @Autowired
    public ParserController(ParserService parserService) {
        this.parserService = parserService;
    }

    @GetMapping("/getToken")
    public Response getToken() {
        Response response;
        response = Response.builder()
                .setCode(200)
                .setStatus("OK")
                .setDescription("success")
                .setResponseBody(new StringModel(parserService.getToken()))
                .build();
        return response;
    }

    @GetMapping("/setToken")
    public Response setToken(@RequestParam String token) {
        parserService.setToken(token);
        Response response;
        response = Response.builder()
                .setCode(200)
                .setStatus("OK")
                .setDescription("success")
                .setResponseBody(new StringModel("success"))
                .build();
        return response;
    }

    @GetMapping("/getTickets")
    public Response getTickets() {
        //TODO
        Response response = new Response();
        response = Response.builder()
                .setCode(200)
                .setStatus("OK")
                .setDescription("method in develop")
                .build();
        return response;
    }

    @GetMapping("/addTicket")
    public Response addTicket() {
        //TODO
        Response response = new Response();
        response = Response.builder()
                .setCode(200)
                .setStatus("OK")
                .setDescription("method in develop")
                .build();
        return response;
    }

}
