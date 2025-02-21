package com.avia.tickets.searching.models;

import com.avia.tickets.searching.response.Response;

public class User {
    private long internalId;
    private long telegramId;
    private boolean isActive;

    public static UserBuilder builder() {
        return new User().new UserBuilder();
    }

    public class UserBuilder {
        private UserBuilder() {
        }

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
}
