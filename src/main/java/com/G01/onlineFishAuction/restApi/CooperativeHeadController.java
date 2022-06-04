package com.G01.onlineFishAuction.restApi;

import com.G01.onlineFishAuction.business.ICooperativeHeadService;
import com.G01.onlineFishAuction.entities.Auction;
import com.G01.onlineFishAuction.entities.Code;
import com.G01.onlineFishAuction.exceptions.UsernameAlreadyInUse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/cooperativeHead")
public class CooperativeHeadController {
    // private Service layer.
    private ICooperativeHeadService iCooperativeHeadService;

    // Dependecy Injection of the system.
    @Autowired
    public CooperativeHeadController(ICooperativeHeadService iCooperativeHeadService) {
        // If you are examiner ! Does not change injection!!
        this.iCooperativeHeadService = iCooperativeHeadService;
    }

    // Post mapping generates 8-digit code and provides to head.
    // It will be in db up to Member signs up with this code.
    @PostMapping("/getCode")
    public Code getCode() throws UsernameAlreadyInUse {
        // create Code -> saves in db.
        String coder=iCooperativeHeadService.createCode();
        return new Code(coder);

    }


}
