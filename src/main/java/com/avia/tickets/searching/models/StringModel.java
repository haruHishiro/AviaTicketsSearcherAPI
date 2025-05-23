/*
 * Model for processing string fields
 * Developers: k.d.panov@gmail.com
 */
package com.avia.tickets.searching.models;

public class StringModel extends BaseModel{
    String value;

    public StringModel(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

