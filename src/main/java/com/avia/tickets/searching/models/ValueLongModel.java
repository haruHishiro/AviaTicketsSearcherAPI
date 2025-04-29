/*
 * Model for processing 64bit fields
 * Developers: k.d.panov@gmail.com
 */


package com.avia.tickets.searching.models;

public final class ValueLongModel extends BaseModel {
    private long value;

    public ValueLongModel(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
