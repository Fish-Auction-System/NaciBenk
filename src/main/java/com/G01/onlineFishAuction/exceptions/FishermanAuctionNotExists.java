package com.G01.onlineFishAuction.exceptions;

public class FishermanAuctionNotExists extends Exception{
    public FishermanAuctionNotExists() {
    }

    public FishermanAuctionNotExists(String message) {
        super(message);
    }

    public FishermanAuctionNotExists(String message, Throwable cause) {
        super(message, cause);
    }

    public FishermanAuctionNotExists(Throwable cause) {
        super(cause);
    }

    public FishermanAuctionNotExists(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
