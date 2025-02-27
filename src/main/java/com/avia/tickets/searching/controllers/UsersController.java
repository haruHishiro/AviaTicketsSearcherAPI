/*
 * Controller for processing user information
 * Developers: Panov K.D. ;
 * Last change: february 2025
 */

package com.avia.tickets.searching.controllers;


import com.avia.tickets.searching.models.ValueBooleanModel;
import com.avia.tickets.searching.models.ValueLongModel;
import com.avia.tickets.searching.response.Response;
import com.avia.tickets.searching.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;


@RestController
@RequestMapping("/api/v1/users")
public class UsersController {
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/isActiveUser")
    public Response isActiveUser(@RequestParam long userId, @RequestParam boolean isInternalId) {
        //TODO logs to console
        Response response = new Response();

        try {
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("method in develop")
                    .setResponseBody(new ValueBooleanModel(userService.isActiveUser(userId, isInternalId)))
                    .build();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return response;
    }

    @GetMapping("/disableUser")
    public Response disableUser(@RequestParam long userId, @RequestParam boolean isInternalId) {
        //TODO logs to console
        Response response = new Response();

        try {
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("method in develop")
                    .setResponseBody(new ValueBooleanModel(userService.disableUser(userId, isInternalId)))
                    .build();
        } catch (SQLException e) {
            System.out.println(e);
        }


        return response;
    }

    @GetMapping("/enableUser")
    public Response enableUser(@RequestParam long userId, @RequestParam boolean isInternalId) {
        //TODO logs to console
        Response response = new Response();

        try {
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("method in develop")
                    .setResponseBody(new ValueBooleanModel(userService.enableUser(userId, isInternalId)))
                    .build();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return response;
    }

    @GetMapping("/createUser")
    public Response createUser(@RequestParam long telegramId) {
        //TODO logs to console
        Response response = new Response();
        try {
            userService.createUser(telegramId);
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("method in develop")
                    .setResponseBody(new ValueBooleanModel(true))
                    .build();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return response;
    }

    @GetMapping("/isUserExist")
    public Response isUserExist(@RequestParam long userId, @RequestParam boolean isInternalId) {
        //TODO logs to console
        Response response = new Response();

        try {
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("method in develop")
                    .setResponseBody(new ValueBooleanModel(userService.isUserExist(userId, isInternalId)))
                    .build();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return response;
    }

    @GetMapping("/getInternalUserIdViaTelegramId")
    public Response getInternalUserIdViaTelegramId(@RequestParam long telegramId) {
        //TODO logs to console
        Response response = new Response();
        try {
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("method in develop")
                    .setResponseBody(new ValueLongModel(userService.getInternalIdViaTelegramId(telegramId)))
                    .build();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return response;
    }

    @GetMapping("/getTelegramIdViaInternalUserId")
    public Response getTelegramIdViaInternalUserId(@RequestParam long internalId) {
        //TODO logs to console
        Response response = new Response();
        try {
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("method in develop")
                    .setResponseBody(new ValueLongModel(userService.getTelegramIdViaInternalId(internalId)))
                    .build();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return response;
    }
}
