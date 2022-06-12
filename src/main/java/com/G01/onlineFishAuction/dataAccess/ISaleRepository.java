package com.G01.onlineFishAuction.dataAccess;


import com.G01.onlineFishAuction.DTO.SaleFeedback;
import com.G01.onlineFishAuction.entities.Sale;

import java.util.List;

public interface ISaleRepository {
    Sale addSale(Sale sale);
    Sale removeSale(Sale sale);
    Sale updateSale(Sale sale);
    List<Sale> getAll();
    Sale getById(int id);
    List<SaleFeedback> getByCustomer(String customer);
    List<SaleFeedback> getByFisherman(String fisherman);
    List<Sale> getByAuction(int auctionId);

}
