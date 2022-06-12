package com.G01.onlineFishAuction.business;

import com.G01.onlineFishAuction.dataAccess.ISaleRepository;
import com.G01.onlineFishAuction.entities.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SaleManager implements ISaleService{
    private ISaleRepository saleRepository;

    @Autowired
    public SaleManager(ISaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    @Transactional
    public Sale addSale(Sale sale) {
        return saleRepository.addSale(sale);
    }

    @Override
    @Transactional
    public Sale removeSale(Sale sale) {
        return saleRepository.removeSale(sale);
    }

    @Override
    @Transactional
    public Sale updateSale(Sale sale) {
        return saleRepository.updateSale(sale);
    }

    @Override
    @Transactional
    public List<Sale> getAll() {
        return saleRepository.getAll();
    }

    @Override
    @Transactional
    public Sale getById(int id) {
        return saleRepository.getById(id);
    }

    @Override
    @Transactional
    public List<Sale> getByCustomer(String customer) {
        return saleRepository.getByCustomer(customer);
    }

    @Override
    @Transactional
    public List<Sale> getByFisherman(String fisherman) {
        return saleRepository.getByFisherman(fisherman);
    }

    @Override
    @Transactional
    public List<Sale> getByAuction(int auctionId) {
        return saleRepository.getByAuction(auctionId);
    }
}
