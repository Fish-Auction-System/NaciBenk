package com.G01.onlineFishAuction.dataAccess;

import com.G01.onlineFishAuction.entities.Auction;

import java.util.List;

public interface IAuctionRepository {
    void addAuction(Auction auction);
    List<Auction> getAll();
    boolean isAuctionExists(int auctionId);
    List<Auction> getLastFive();
    Auction getById(int id);
    void finishAuction(Auction auction);
}
