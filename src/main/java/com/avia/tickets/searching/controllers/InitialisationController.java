package com.avia.tickets.searching.controllers;

import com.avia.tickets.searching.models.StringModel;
import com.avia.tickets.searching.response.Response;
import com.avia.tickets.searching.services.InitialisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/v1/init")
public class InitialisationController {
    private final InitialisationService initialisationService;

    @Autowired
    public InitialisationController(InitialisationService initialisationService) {
        this.initialisationService = initialisationService;
    }

    @GetMapping("/addCityIATACode")
    public Response addCityIATACode(@RequestParam String cityName, @RequestParam String iataCode, @RequestParam String countryCode) {
        Response response;
        try {
            initialisationService.addCityIATACode(cityName, iataCode, countryCode);
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("success")
                    .build();
        } catch (SQLException e) {
            System.out.println(e);
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("error")
                    .build();
        }


        return response;
    }

    @GetMapping("/addCountryIATACode")
    public Response addCountryIATACode(@RequestParam String countryName, @RequestParam String iataCode) {
        Response response;
        try {
            initialisationService.addCountryIATACode(countryName, iataCode);
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("success")
                    .build();
        } catch (SQLException e) {
            System.out.println(e);
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("error")
                    .build();
        }
        return response;
    }
}
