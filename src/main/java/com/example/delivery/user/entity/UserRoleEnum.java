package com.example.delivery.user.entity;

public enum UserRoleEnum {
    CUSTOMER(Authority.CUSTOMER),
    OWNER(Authority.OWNER);

    private final String authority;

    UserRoleEnum(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    public static class Authority {
        public static final String CUSTOMER = "ROLE_CUSTOMER";
        public static final String OWNER = "ROLE_OWNER";
    }
}