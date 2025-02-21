/*
 * Model for processing users in responses
 * Developers: Panov K.D. ;
 * Last change: february 2025
 */

package com.avia.tickets.searching.models;


public class User {
    private long internalId;
    private long telegramId;
    private boolean isActive;

    public static UserBuilder builder() {
        return new User().new UserBuilder();
    }

    public class UserBuilder {
        private UserBuilder() { }

        public UserBuilder setStatus(long internalId) {
            User.this.internalId = internalId;
            return this;
        }

        public UserBuilder setDescription(long telegramId) {
            User.this.telegramId = telegramId;
            return this;
        }

        public UserBuilder setResponseBody(boolean isActive) {
            User.this.isActive = isActive;
            return this;
        }

        public User build() {
            return User.this;
        }
    }

    public long getInternalId() {
        return internalId;
    }

    public long getTelegramId() {
        return telegramId;
    }

    public boolean isActive() {
        return isActive;
    }
}
