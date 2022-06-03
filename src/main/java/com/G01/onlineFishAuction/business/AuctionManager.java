package com.G01.onlineFishAuction.business;

import com.G01.onlineFishAuction.dataAccess.IAuctionRepository;
import com.G01.onlineFishAuction.entities.Auction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuctionManager implements IAuctionService {


    private IAuctionRepository auctionRepository;

    @Autowired
    public AuctionManager(IAuctionRepository iAuctionRepository) {
        this.auctionRepository = iAuctionRepository;
    }

    @Override
    @Transactional
    public void add(Auction auction) {
        auctionRepository.addAuction(auction);
    }

    @Override
    @Transactional
    public List<Auction> getAll() {
        return auctionRepository.getAll();
    }

    @Override
    @Transactional
    public boolean isAuctionExists(int idName){
        return auctionRepository.isAuctionExists(idName);
    }


}
