package com.G01.onlineFishAuction.business;

import com.G01.onlineFishAuction.DTO.AuctionStatus;
import com.G01.onlineFishAuction.DTO.SaleInfo;
import com.G01.onlineFishAuction.dataAccess.IAuctionRepository;
import com.G01.onlineFishAuction.dataAccess.ICustomerRepository;
import com.G01.onlineFishAuction.dataAccess.IFishRepository;
import com.G01.onlineFishAuction.dataAccess.ISaleRepository;
import com.G01.onlineFishAuction.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AuctionManager implements IAuctionService {



    private Timer timer;
    private IFishRepository fishRepository;
    private IAuctionRepository auctionRepository;
    private ISaleRepository saleRepository;
    private ICustomerRepository customerRepository;
    private Fish lastFish;
    private Auction currentAuction;
    private List<Fish> fishForSecondRound = new ArrayList<>();
    private List<Fish> fish = new ArrayList<>();
    private List<String> customers = new ArrayList<>();
    private int currentFish;
    private int round;
    private SaleInfo saleInfoBackup;
    private boolean isFinished;
    @Autowired
    private Bid currentBid;
    @Autowired
    private SaleInfo saleInfo;



    @Autowired
    public AuctionManager(IAuctionRepository AuctionRepository, IFishRepository fishRepository, ISaleRepository saleRepository, ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.auctionRepository = AuctionRepository;
        this.fishRepository = fishRepository;
        this.saleRepository = saleRepository;
        this.isFinished = false;
        this.round = 0;
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
        if(auction.getIs_finished()==1){
            return null;
        }
        Date date = new Date();
        Timestamp timestamp2 = new Timestamp(date.getTime());

        float time  = (auction.getDate() - timestamp2.getTime()*1000);
        System.out.printf("the current timestamp is %d\n and the auction's timestamp is %f\n so the time diff is %f\n",timestamp2.getTime(), auction.getDate(),time);
        if (time<=0){
            if(auction.getIs_finished()==0){
                fish= fishRepository.getAllFishForAuction(auction.getId());
                if(fish.isEmpty()){
                    return null;
                }
                currentAuction = auction;
                currentFish = 0;
                round =1;
                saleInfo=null;
                currentBid=null;
                lastFish = null;
                isFinished = false;
                timer = new Timer(this);
                try{
                    timer.scheduleFixedRateTaskAsync();
                    System.out.println("timer has been initiated");
                }catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                timer.stop();
                return auction;
            }
        }
        return null;
    }

    @Override
    @Transactional
    public Auction join(String  username,int auctionId){
        if (currentAuction!=null){
            if(currentAuction.getId()==auctionId){
                if(isFinished){
                    return null;
                }
                if (currentAuction.getQuota() > customers.size()){
                    /*
                    if(!customerRepository.isCustomerExists(username) || !){
                        System.out.println("username is not valid");
                        return null;
                    }

                    */
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
        if (currentAuction==null){
            return null;
        }
        if(!fish.isEmpty()){
            round =1;
            return setSale(1);
        }
        else{
            if(!fishForSecondRound.isEmpty()){
                round=2;
                return setSale(2);
            }else{
                finishAuction();
                return null;
            }
        }
    }



    @Override
    public SaleInfo getSaleInfo(){
        if(currentAuction == null){
            return null;
        }
        return saleInfo;
    }

    @Override
    public void finishAuction(){


        isFinished = true;
        fish.clear();
        fishForSecondRound.clear();
        round = 0;
        saleInfo = null;
        currentBid = null;
        //değiştirildi
        saleInfoBackup = null;
        //değiştirildi
        currentFish = 0;
        auctionRepository.finishAuction(currentAuction);
        isFinished = true;
        customers.clear();
        timer.stop();



    }

    @Override
    public Bid makeBid(float amount, String customer){
        if(saleInfo==null){
            return null;
        }else{
            if(!customers.contains(customer)){
                System.out.println("customer has not been joined to the auction");
                return null;
            }
            if(currentBid.getBid() > amount){
                return currentBid;
            }else{

                currentBid.setCustomer(customer);
                currentBid.setBid(amount);
                saleInfo.setBuyer(customer);
                saleInfo.setPrice(amount);
                timer.reset();
                return currentBid;
            }
        }
    }


    @Override
    @Transactional
    public SaleInfo closeSale(){
        if(lastFish==null){
            System.out.println("last fish cannot be null!!");
            return null;
        }

        if(saleInfo.getPrice()==lastFish.getPrice()){
            //fish is not sold
            switch (round){
                case 0:
                    System.out.println("invalid round status!!");
                    break;
                case 1:
                    fishForSecondRound.add(lastFish);
                    break;
                case 2:
                    break;
            }

        }else{
            Sale sale = new Sale(0, lastFish.getId(), saleInfo.getPrice(), saleInfo.getBuyer(),currentAuction.getId(), 0);
            System.out.println(sale.toString());
            saleRepository.addSale(sale);

        }
        saleInfoBackup = new SaleInfo(saleInfo);
        if(fish.isEmpty() && fishForSecondRound.isEmpty()){
            finishAuction();
        }else{
            saleInfo = null;
            currentBid = null;
            timer.stop();
        }
        return saleInfoBackup;
    }

    @Override
    public SaleInfo getNextSale(){
        if(isFinished || currentAuction==null){
            return null;
        }
        if(fish.isEmpty() && fishForSecondRound.isEmpty()){
            System.out.println("expectation failed lists must be not empty if auction is not finished!!");
            return null;
        }
        int nextIndex = 0;
        //değiştirildi
        Fish tokenFish = null;
        if(!fish.isEmpty()){
            round =1;
        }else{
            round = 2;
        }

        if(round==1){
            tokenFish = fish.get(nextIndex);
        }else if(round==2){
            tokenFish = fishForSecondRound.get(nextIndex);
        }
        //değiştirildi
        SaleInfo saleInfo = new SaleInfo();
        saleInfo.setFish(tokenFish);
        saleInfo.setPrice(tokenFish.getPrice());
        saleInfo.setBuyer(null);
        return saleInfo;
    }

    @Override
    public AuctionStatus getStatus() {
        boolean isAuctionStarted = currentAuction != null && isFinished==false;
        boolean isSaleClosed = isAuctionStarted && saleInfo==null;
        boolean isAuctionFinished = isFinished;
        boolean isFirstSaleWait = isAuctionStarted && saleInfoBackup==null && saleInfo==null;
        AuctionStatus auctionStatus = new AuctionStatus(isFirstSaleWait, isAuctionFinished, isAuctionStarted, isSaleClosed, saleInfoBackup, saleInfo, getNextSale());
        return auctionStatus;
    }

    @Override
    public String toString() {
        return "AuctionManager{" +
                "timer=" + timer +
                ", fishRepository=" + fishRepository +
                ", auctionRepository=" + auctionRepository +
                ", currentAuction=" + (currentAuction==null ? null : currentAuction.toString()) +
                ", fish=" + fish +
                ", second round fish="+ fishForSecondRound +
                ", customers=" + customers +
                ", currentFish=" + currentFish +
                ", currentBid=" + (currentBid==null ? null : currentBid.toString()) +
                ", saleInfo=" + (saleInfo==null ? null : saleInfo.toString()) +
                '}';
    }

    private SaleInfo setSale(int round){
        List<Fish> fishList = null;
        if(round==1){
            fishList = fish;
        }else if(round==2){
            fishList = fishForSecondRound;
        }else{
            System.out.println("invalid round number!!");
            return null;
        }
        saleInfo= new SaleInfo();
        currentBid = new Bid();
        lastFish = fishList.remove(0);
        saleInfo.setFish(lastFish);
        saleInfo.setPrice(lastFish.getPrice());
        saleInfo.setBuyer(null);
        currentBid.setBid(lastFish.getPrice());
        currentBid.setFish(lastFish.getId());
        currentBid.setCustomer("");

        timer.start();
        timer.reset();
        return saleInfo;
    }


}
