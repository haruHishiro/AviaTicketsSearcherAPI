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
        Response response;
        try {
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("success")
                    .setResponseBody(new ValueBooleanModel(userService.isActiveUser(userId, isInternalId)))
                    .build();
            System.out.println("[LOG] [isActiveUser] [SUCCESS]");
        } catch (SQLException e) {
            System.out.println(e);
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("error")
                    .build();
            System.out.println("[LOG] [isActiveUser] [ERROR] [userId] " + userId + " [isInternalId] " + isInternalId);
        }

        return response;
    }

    @GetMapping("/disableUser")
    public Response disableUser(@RequestParam long userId, @RequestParam boolean isInternalId) {
        Response response;
        try {
            userService.disableUser(userId, isInternalId);
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("success")
                    .build();
            System.out.println("[LOG] [disableUser] [SUCCESS]");
        } catch (SQLException e) {
            System.out.println(e);
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("error")
                    .build();
            System.out.println("[LOG] [disableUser] [ERROR] [userId] " + userId + " [isInternalId] " + isInternalId);
        }
        return response;
    }

    @GetMapping("/enableUser")
    public Response enableUser(@RequestParam long userId, @RequestParam boolean isInternalId) {
        Response response;
        try {
            userService.enableUser(userId, isInternalId);
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("success")
                    .build();
            System.out.println("[LOG] [enableUser] [SUCCESS]");
        } catch (SQLException e) {
            System.out.println(e);
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("success")
                    .build();
            System.out.println("[LOG] [enableUser] [ERROR] [userId] " + userId + " [isInternalId] " + isInternalId);
        }
        return response;
    }

    @GetMapping("/createUser")
    public Response createUser(@RequestParam long telegramId) {
        Response response;
        try {
            userService.createUser(telegramId);
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("success")
                    .build();
            System.out.println("[LOG] [createUser] [SUCCESS]");
        } catch (SQLException e) {
            System.out.println(e);
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("error")
                    .build();
            System.out.println("[LOG] [createUser] [ERROR] [telegramId] " + telegramId);
        }
        return response;
    }

    @GetMapping("/isUserExist")
    public Response isUserExist(@RequestParam long userId, @RequestParam boolean isInternalId) {
        Response response;
        try {
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("success")
                    .setResponseBody(new ValueBooleanModel(userService.isUserExist(userId, isInternalId)))
                    .build();
            System.out.println("[LOG] [isUserExist] [SUCCESS]");
        } catch (SQLException e) {
            System.out.println(e);
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("error")
                    .build();
            System.out.println("[LOG] [isUserExist] [ERROR] [userId] " + userId + " [isInternalId] " + isInternalId);
        }
        return response;
    }

    @GetMapping("/getInternalUserIdViaTelegramId")
    public Response getInternalUserIdViaTelegramId(@RequestParam long telegramId) {
        Response response = new Response();
        try {
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("success")
                    .setResponseBody(new ValueLongModel(userService.getInternalIdViaTelegramId(telegramId)))
                    .build();
            System.out.println("[LOG] [getInternalUserIdViaTelegramId] [SUCCESS]");
        } catch (SQLException e) {
            System.out.println(e);
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("error")
                    .build();
            System.out.println("[LOG] [getInternalUserIdViaTelegramId] [ERROR] [telegramId] " + telegramId);
        }
        return response;
    }

    @GetMapping("/getTelegramIdViaInternalUserId")
    public Response getTelegramIdViaInternalUserId(@RequestParam long internalId) {
        Response response = new Response();
        try {
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("success")
                    .setResponseBody(new ValueLongModel(userService.getTelegramIdViaInternalId(internalId)))
                    .build();
            System.out.println("[LOG] [getTelegramIdViaInternalUserId] [SUCCESS]");
        } catch (SQLException e) {
            System.out.println(e);
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("error")
                    .build();
            System.out.println("[LOG] [getTelegramIdViaInternalUserId] [ERROR] [internalId] " + internalId);
        }
        return response;
    }
}
