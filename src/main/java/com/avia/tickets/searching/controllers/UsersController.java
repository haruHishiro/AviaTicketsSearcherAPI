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


@RestController
@RequestMapping("/api/v1/users")
public class UsersController {
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/isActiveUser")
    public Response isActiveUser(@RequestParam long id, @RequestParam boolean isInternalId) {
        //TODO try-catch or rewrite getter function into service
        //TODO logs to console
        Response response;

        if(isInternalId) {
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("method in develop")
                    .setResponseBody(new ValueBooleanModel(userService.isActiveUserViaInternalId(id)))
                    .build();
        } else {
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("method in develop")
                    .setResponseBody(new ValueBooleanModel(userService.isActiveUserViaTelegramId(id)))
                    .build();
        }

        return response;
    }

    @GetMapping("/disableUser")
    public Response disableUser(@RequestParam long id, @RequestParam boolean isInternalId) {
        //TODO try-catch or rewrite getter function into service
        //TODO logs to console
        Response response;

        if(isInternalId){
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("method in develop")
                    .setResponseBody(new ValueBooleanModel(userService.disableUserViaInternalId(id)))
                    .build();
        } else {
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("method in develop")
                    .setResponseBody(new ValueBooleanModel(userService.disableUserViaTelegramId(id)))
                    .build();
        }

        return response;
    }

    @GetMapping("/enableUser")
    public Response enableUser(@RequestParam long id, @RequestParam boolean isInternalId) {
        //TODO try-catch or rewrite getter function into service
        //TODO logs to console
        Response response;

        if(isInternalId){
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("method in develop")
                    .setResponseBody(new ValueBooleanModel(userService.enableUserViaInternalId(id)))
                    .build();
        } else {
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("method in develop")
                    .setResponseBody(new ValueBooleanModel(userService.enableUserViaTelegramId(id)))
                    .build();
        }

        return response;
    }

    @GetMapping("/createUser")
    public Response createUser(@RequestParam long telegramId) {
        //TODO try-catch or rewrite getter function into service
        //TODO logs to console
        Response response = Response.builder()
                .setCode(200)
                .setStatus("OK")
                .setDescription("method in develop")
                .setResponseBody(new ValueBooleanModel(userService.createUser(telegramId)))
                .build();
        return response;
    }

    @GetMapping("/isUserExist")
    public Response isUserExist(@RequestParam long id, @RequestParam boolean isInternalId) {
        //TODO try-catch or rewrite getter function into service
        //TODO logs to console
        Response response;

        if(isInternalId){
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("method in develop")
                    .setResponseBody(new ValueBooleanModel(userService.isUserExistViaInternalId(id)))
                    .build();
        } else {
            response = Response.builder()
                    .setCode(200)
                    .setStatus("OK")
                    .setDescription("method in develop")
                    .setResponseBody(new ValueBooleanModel(userService.isUserExistViaTelegramId(id)))
                    .build();
        }

        return response;
    }

    @GetMapping("/getInternalUserIdViaTelegramId")
    public Response getInternalUserIdViaTelegramId(@RequestParam long telegramId) {
        //TODO try-catch or rewrite getter function into service
        //TODO logs to console
        Response response = Response.builder()
                .setCode(200)
                .setStatus("OK")
                .setDescription("method in develop")
                .setResponseBody(new ValueLongModel(userService.getInternalIdViaTelegramId(telegramId)))
                .build();
        return response;
    }

    @GetMapping("/getTelegramIdViaInternalUserId")
    public Response getTelegramIdViaInternalUserId(@RequestParam long internalId) {
        //TODO try-catch or rewrite getter function into service
        //TODO logs to console
        Response response = Response.builder()
                .setCode(200)
                .setStatus("OK")
                .setDescription("method in develop")
                .setResponseBody(new ValueLongModel(userService.getTelegramIdViaInternalId(internalId)))
                .build();
        return response;
    }
}
