package com.G01.onlineFishAuction.restApi;


import com.G01.onlineFishAuction.DTO.SaleFeedback;
import com.G01.onlineFishAuction.business.ISaleService;
import com.G01.onlineFishAuction.entities.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/sale")
public class SaleController {
    private ISaleService saleService;

    @Autowired
    public SaleController(ISaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping("/all")
    public List<Sale> getAll(){
        return saleService.getAll();
    }

    @GetMapping("/get/fisherman/{fisherman}")
    public List<SaleFeedback> getSalesForFisherman(@PathVariable String fisherman){
        return saleService.getByFisherman(fisherman);
    }




}
