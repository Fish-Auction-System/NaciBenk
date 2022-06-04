package com.G01.onlineFishAuction.restApi;


import com.G01.onlineFishAuction.business.IAuctionService;
import com.G01.onlineFishAuction.business.ICooperativeMemberService;
import com.G01.onlineFishAuction.entities.Fish;
import com.G01.onlineFishAuction.exceptions.FishermanAuctionNotExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/cooperativeMember")
public class CooperativeMemberController {
    ICooperativeMemberService memberService;
    IAuctionService auctionService;

    @Autowired
    public CooperativeMemberController(ICooperativeMemberService memberService, IAuctionService auctionService) {
        this.memberService = memberService;
        this.auctionService = auctionService;
    }

    @PostMapping("/add-fish")
    public ResponseEntity<String> addFishToAuction(@RequestBody Fish addingFishJson){
        try {
            memberService.addFish(addingFishJson);
            return new ResponseEntity<>("Added successfully", HttpStatus.OK);
        }catch (FishermanAuctionNotExists e) {
            e.printStackTrace();
            return new ResponseEntity<>("Wrong fields !!",HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/getfish/{id}")
    public List<Fish> getAll(@PathVariable int id){
        return  auctionService.getAllFish(id);
    }




}
