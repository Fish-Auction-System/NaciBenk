package com.G01.onlineFishAuction.exceptions;

public class UsernameAlreadyInUse extends Exception {
    public UsernameAlreadyInUse() {
    }

    public UsernameAlreadyInUse(String message) {
        super(message);
    }
}
