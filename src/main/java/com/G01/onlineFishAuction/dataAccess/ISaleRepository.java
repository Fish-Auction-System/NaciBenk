package com.G01.onlineFishAuction.dataAccess;


import com.G01.onlineFishAuction.entities.Sale;

import java.util.List;

public interface ISaleRepository {
    Sale addSale(Sale sale);
    Sale removeSale(Sale sale);
    Sale updateSale(Sale sale);
    List<Sale> getAll();
    Sale getById(int id);
    List<Sale> getByCustomer(String customer);
    List<Sale> getByFisherman(String fisherman);
    List<Sale> getByAuction(int auctionId);

}
