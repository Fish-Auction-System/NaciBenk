package com.G01.onlineFishAuction.restApi;

import com.G01.onlineFishAuction.business.IAuctionService;
import com.G01.onlineFishAuction.entities.Auction;
import org.springframework.beans.factory.annotation.Autowired;
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

    // adding to db generally indicates to use post mapping.
    @PostMapping("/add")
    public void addAuction(@RequestBody Auction auction){
        // Adding auction to the system.
        iAuctionService.add(auction);
    }
}
