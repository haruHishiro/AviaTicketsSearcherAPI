/*
 * Class for processing responses
 * Developers: Panov K.D. ;
 * Last change: february 2025
 */

package com.avia.tickets.searching.response;


import com.avia.tickets.searching.models.BaseModel;


public class Response {
    private int code;
    private String status;
    private String description;
    private BaseModel body;

    public static ResponseBuilder builder() {
        return new Response().new ResponseBuilder();
    }

    public class ResponseBuilder {
        private ResponseBuilder() {
        }

        public ResponseBuilder setCode(int code) {
            Response.this.code = code;
            return this;
        }

        public ResponseBuilder setStatus(String status) {
            Response.this.status = status;
            return this;
        }

        public ResponseBuilder setDescription(String description) {
            Response.this.description = description;
            return this;
        }

        public ResponseBuilder setResponseBody(BaseModel model) {
            Response.this.body = model;
            return this;
        }

        public Response build() {
            return Response.this;
        }
    }

    public int getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public BaseModel getBody() {
        return body;
    }
}
