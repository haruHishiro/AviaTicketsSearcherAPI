/*
 * Model for processing long values in responses
 * Developers: Panov K.D. ;
 * Last change: february 2025
 */


package com.avia.tickets.searching.models;

public class ValueLong extends BaseModel {
    private long value;

    public ValueLong(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
