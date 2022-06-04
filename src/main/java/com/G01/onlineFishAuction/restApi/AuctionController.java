package com.G01.onlineFishAuction.restApi;

import com.G01.onlineFishAuction.business.IAuctionService;
import com.G01.onlineFishAuction.entities.Auction;
import com.G01.onlineFishAuction.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auction")
public class AuctionController {

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
    public List<Auction> getAllAuctions(){
        // This returns list object from service layer.
        return iAuctionService.getAll();
    }

    @GetMapping("/get/post")
    public List<Auction> getPostAuctions(){
        return iAuctionService.getLastFive();
    }

    // adding to db generally indicates to use post mapping.
    @PostMapping("/add")
    public void addAuction(@RequestBody Auction auction){
        // Adding auction to the system.
        iAuctionService.add(auction);
    }

    @PutMapping("start/{auctionID}")
    public ResponseEntity<Auction> startAuction(@PathVariable int auctionId){
        Auction auction = iAuctionService.start(auctionId);
        if(auction==null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(auction, HttpStatus.OK);
    }

    @PutMapping("join/{auctionID}")
    public ResponseEntity<Auction> joinAuction(@PathVariable int auctionId, @RequestBody Customer customer){
        Auction auction = iAuctionService.join(customer, auctionId);
        if(auction==null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(auction, HttpStatus.OK);
    }

    @GetMapping("get/current")
    public ResponseEntity<Auction> getCurrent(){
        Auction auction = iAuctionService.getCurrent();
        if (auction==null){
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(iAuctionService.getCurrent(),HttpStatus.OK);
    }
}
