package com.G01.onlineFishAuction.restApi;

import com.G01.onlineFishAuction.business.IAuctionService;
import com.G01.onlineFishAuction.entities.Auction;
import com.G01.onlineFishAuction.entities.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


@EnableScheduling
@EnableAsync
@CrossOrigin("*")
@RestController
@RequestMapping("/api/auction")
public class AuctionController {

    int countdowntimer = 100;
    boolean booler = false;
    // Private Service layer.
    private IAuctionService iAuctionService;

    // dependecy Injection of the system.
    @Autowired
    public AuctionController(IAuctionService iAuctionService) {
        // Do not change it if you are examiner of this code.
        this.iAuctionService = iAuctionService;
    }

    // Fetchin all auctions by http get mapping.
    @GetMapping("/fetchAuctions")
    public List<Auction> getAllAuctions() {
        // This returns list object from service layer.
        return iAuctionService.getAll();
    }

    @GetMapping("/get/post")
    public List<Auction> getPostAuctions() {
        return iAuctionService.getLastFive();
    }

    // adding to db generally indicates to use post mapping.
    @PostMapping("/add")
    public void addAuction(@RequestBody Auction auction) {
        // Adding auction to the system.
        iAuctionService.add(auction);
    }

    @PutMapping("start/{auctionId}")
    public ResponseEntity<Auction> startAuction(@PathVariable int auctionId) {
        Auction auction = iAuctionService.start(auctionId);
        if (auction == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(auction, HttpStatus.OK);
    }

    @PostMapping("join/{auctionId}")
    public ResponseEntity<Auction> joinAuction(@PathVariable int auctionId, @RequestBody Customer customer) {
        Auction auction = iAuctionService.join(customer, auctionId);
        if (auction == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(auction, HttpStatus.OK);
    }

    @GetMapping("get/current")
    public ResponseEntity<Auction> getCurrent() {
        Auction auction = iAuctionService.getCurrent();
        if (auction == null) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(iAuctionService.getCurrent(), HttpStatus.OK);
    }

    @Async
    @Scheduled(fixedRate = 1000)
    public void scheduleFixedRateTaskAsync() throws InterruptedException {
        if (booler) {
            System.out.println("Fixed rate task async - " + System.currentTimeMillis() / 1000);
            Thread.sleep(2000);
            countdowntimer = countdowntimer - 1;
        }

    }

    @GetMapping(value = "/stopTimer")
    public void startSchedule() throws InterruptedException {
        booler = false;
        countdowntimer = 100;
    }

    @GetMapping(value = "/startTimer")
    public void stopSchedule() throws InterruptedException {
        booler = true;
        scheduleFixedRateTaskAsync();
    }

    @GetMapping(value = "/getTimer")
    public int getCountdowntimer() {
        return countdowntimer;
    }

    @GetMapping(value = "/resetTimer")
    public void resetCountdownTimer() {
        countdowntimer = 100;
    }

}
