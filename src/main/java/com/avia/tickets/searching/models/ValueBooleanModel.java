/*
 * Model for processing true/false fields
 * Developers: k.d.panov@gmail.com
 */

package com.avia.tickets.searching.models;


public class ValueBooleanModel extends BaseModel {
    private boolean value;

    public ValueBooleanModel(boolean value) {
        this.value = value;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
