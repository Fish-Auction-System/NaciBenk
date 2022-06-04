package com.G01.onlineFishAuction.business;

import com.G01.onlineFishAuction.entities.Auction;
import com.G01.onlineFishAuction.entities.Customer;
import com.G01.onlineFishAuction.entities.Fish;

import java.util.List;

public interface IAuctionService {
    public void add(Auction auction);
    public List<Auction> getAll();
    public boolean isAuctionExists(int idName);
    public List<Fish> getAllFish(int auctionId);
    public List<Auction> getLastFive();
    public Auction start(int auctionId);
    public Auction join(Customer customer,int auctionId);
    Auction getCurrent();
}
