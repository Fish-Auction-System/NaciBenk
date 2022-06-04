package com.G01.onlineFishAuction.business;

import com.G01.onlineFishAuction.entities.Auction;
import com.G01.onlineFishAuction.entities.Customer;
import com.G01.onlineFishAuction.entities.Fish;
import com.G01.onlineFishAuction.entities.SaleInfo;

import java.util.List;

public interface IAuctionService {
    public void add(Auction auction);
    public List<Auction> getAll();
    public boolean isAuctionExists(int idName);
    public List<Fish> getAllFish(int auctionId);
    public List<Auction> getLastFive();
    public Auction start(int auctionId);
    public Auction join(String username,int auctionId);
    Auction getCurrent();
    public SaleInfo nextFish();
    public SaleInfo getSaleInfo();
    public void finishAuction();

}
