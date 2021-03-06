package com.G01.onlineFishAuction.restApi;

import com.G01.onlineFishAuction.DTO.AuctionStatus;
import com.G01.onlineFishAuction.DTO.SaleInfo;
import com.G01.onlineFishAuction.business.IAuctionService;
import com.G01.onlineFishAuction.entities.*;
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

    @PutMapping("start/{auctionId}")
    public ResponseEntity<Auction> startAuction(@PathVariable int auctionId){
        Auction auction = iAuctionService.start(auctionId);
        if(auction==null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(auction, HttpStatus.OK);
    }

    @PutMapping("join/{auctionId}/{username}")
    public ResponseEntity<Auction> joinAuction(@PathVariable int auctionId, @PathVariable String username){
        Auction auction = iAuctionService.join(username, auctionId);
        if(auction==null){
            System.out.println("auction is null");
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

    @PutMapping("next/sale")
    public  ResponseEntity<SaleInfo> getNextSale(){
        SaleInfo sale;
        if ( (sale= iAuctionService.nextFish())==null){
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(sale,HttpStatus.OK);
    }

    @GetMapping("get/sale")
    public  ResponseEntity<SaleInfo> getSaleInfo(){
        SaleInfo sale;
        if ( (sale= iAuctionService.getSaleInfo())==null){
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(sale,HttpStatus.OK);
    }

    @GetMapping("get/next/sale")
    public ResponseEntity<SaleInfo> getNextSaleInfo(){
        SaleInfo saleInfo;
        if((saleInfo = iAuctionService.getNextSale()) == null){
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);

        }
        return new ResponseEntity<>(saleInfo,HttpStatus.OK);
    }


    @PutMapping("make/bid/{customer}/{bid}")
    public ResponseEntity<Float> makeBid(@PathVariable String customer, @PathVariable float bid){
        Bid newBid = iAuctionService.makeBid(bid,customer);
        if(newBid == null){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }else if(newBid.getBid() != bid){
            return new ResponseEntity<>(bid,HttpStatus.CONFLICT);

        }else{
            return new ResponseEntity<>(bid,HttpStatus.OK);
        }
    }

    @GetMapping("get/fish/{auctionId}")
    public ResponseEntity<List<Fish>> getFish(@PathVariable int auctionId){
        return new ResponseEntity<>(iAuctionService.getAllFish(auctionId),HttpStatus.OK);

    }

    @GetMapping("get/current/info")
    public String getInfo(){
        return iAuctionService.toString();
    }

    @PutMapping("close/sale")
    public ResponseEntity<SaleInfo> closeSale(){
        SaleInfo saleInfo = iAuctionService.closeSale();
        if(saleInfo==null){
            return new ResponseEntity<>(null,HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(saleInfo,HttpStatus.OK);
    }

    @GetMapping("get/current/status")
    public AuctionStatus getStatus(){
        return  iAuctionService.getStatus();
    }











}
