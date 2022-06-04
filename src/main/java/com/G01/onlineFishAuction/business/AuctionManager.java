package com.G01.onlineFishAuction.business;

import com.G01.onlineFishAuction.dataAccess.IAuctionRepository;
import com.G01.onlineFishAuction.dataAccess.IFishRepository;
import com.G01.onlineFishAuction.entities.Auction;
import com.G01.onlineFishAuction.entities.Customer;
import com.G01.onlineFishAuction.entities.Fish;
import com.G01.onlineFishAuction.entities.SaleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AuctionManager implements IAuctionService {


    private IFishRepository fishRepository;
    private IAuctionRepository auctionRepository;
    private Auction currentAuction;
    private List<Fish> fish = new ArrayList<>();
    private List<String> customers = new ArrayList<>();
    private int currentFish;
    @Autowired
    private SaleInfo saleInfo = null;



    @Autowired
    public AuctionManager(IAuctionRepository AuctionRepository, IFishRepository fishRepository) {
        this.auctionRepository = AuctionRepository;
        this.fishRepository = fishRepository;
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

    @Override
    @Transactional
    public List<Fish> getAllFish(int auctionId){
        return fishRepository.getAllFishForAuction(auctionId);
    }

    @Override
    @Transactional
    public List<Auction> getLastFive() {
        return auctionRepository.getLastFive();
    }

    @Override
    @Transactional
    public Auction start(int auctionId){
        Auction auction = auctionRepository.getById(auctionId);
        Date date = new Date();
        Timestamp timestamp2 = new Timestamp(date.getTime());
        float time  = (auction.getDate() - timestamp2.getTime()*1000);
        if (time<=0){
            if(auction.getIs_finished()==0){
                currentAuction = auction;
                fish= fishRepository.getAllFishForAuction(auction.getId());
                currentFish = 0;
                return auction;
            }
        }
        return null;
    }

    @Override

    public Auction join(String  username,int auctionId){
        if (currentAuction!=null){
            if(currentAuction.getId()==auctionId){
                if (currentAuction.getQuota() < customers.size()){
                    customers.add(username);
                    return currentAuction;
                }
                return null;
            }
            return null;
        }
        return null;

    }

    @Override

    public Auction getCurrent(){
        return currentAuction;
    }

    @Override
    public SaleInfo nextFish(){
        Fish tokenFish = null;
        if(currentFish<fish.size()){
            tokenFish = fish.remove(currentFish);
            currentFish++;
            saleInfo.setFish(tokenFish);
            saleInfo.setPrice(tokenFish.getPrice());
            saleInfo.setBuyer(null);
            return saleInfo;
        }
        else{
            finishAuction();
            return null;
        }
    }


    @Override
    public SaleInfo getSaleInfo(){
        return saleInfo;
    }

    @Override
    public void finishAuction(){
        fish.clear();
        saleInfo = null;
        currentFish = 1;
        auctionRepository.finishAuction(currentAuction);
        currentAuction = null;
        customers.clear();


    }


}
