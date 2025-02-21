/*
 * Model for processing true/false responses
 * Developers: Panov K.D. ;
 * Last change: february 2025
 */

package com.avia.tickets.searching.models;


public final class BooleanModel extends BaseModel {
    private boolean value;

    public BooleanModel(boolean value) {
        this.value = value;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
