/*
 * Model for processing user requests
 * Developers: k.d.panov@gmail.com
 */
package com.avia.tickets.searching.models;

import java.util.ArrayList;

public class RequestsList extends BaseModel{
    private ArrayList<Request> requests;

    public RequestsList(ArrayList<Request> requests) {
        this.requests = requests;
    }

    public ArrayList<Request> getRequests() {
        return requests;
    }

    public void setRequests(ArrayList<Request> requests) {
        this.requests = requests;
    }
}
