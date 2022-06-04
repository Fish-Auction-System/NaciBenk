package com.G01.onlineFishAuction.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Timer {

    private boolean booler = false;
    private IAuctionService auctionService;
    private int countdowntimer = 7;



    public Timer( IAuctionService auctionService) {

        this.auctionService = auctionService;

    }

    public Timer() {
    }

    @Async
    @Scheduled(fixedRate = 1000)
    public void scheduleFixedRateTaskAsync() throws InterruptedException {
        if (booler) {
            System.out.println("Fixed rate task async - " + System.currentTimeMillis() / 1000);
            Thread.sleep(2000);
            countdowntimer = countdowntimer - 1;
            if(countdowntimer==0){
                booler=false;
                countdowntimer=7;
                auctionService.closeSale();
            }
        }

    }

    public void reset(){
        countdowntimer=7;
    }
    public void start(){
        booler=true;
    }
    public void stop(){
        booler = false;

    }
    public int getSeconds(){
        return countdowntimer;
    }

}
