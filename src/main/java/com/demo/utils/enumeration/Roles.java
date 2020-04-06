package com.demo.utils.enumeration;

public enum Roles {
    SYSTEM_ADMIN("SYSTEM_ADMIN"),
    BOOK_ADMIN("BOOK_ADMIN"),
    BORROWER("BOOK_ADMIN");

    private final String role;

    Roles(String role) {
        this.role = role;
    }


    public String value() {
        return this.role;
    }
}
