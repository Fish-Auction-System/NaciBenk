package com.G01.onlineFishAuction.dataAccess;

import com.G01.onlineFishAuction.entities.Fish;
import com.G01.onlineFishAuction.entities.Fisherman;
import com.G01.onlineFishAuction.entities.Sale;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
public class HibernateSaleRepository implements ISaleRepository{
    private EntityManager entityManager;
    private IFishRepositoryForSale fishRepositoryForSale;

    @Autowired
    public HibernateSaleRepository(EntityManager entityManager , IFishRepositoryForSale fishRepositoryForSale) {
        this.entityManager = entityManager;
        this.fishRepositoryForSale = fishRepositoryForSale;
    }



    @Override
    @Transactional
    public Sale addSale(Sale sale) {
        Session session  = entityManager.unwrap(Session.class);
        session.saveOrUpdate(sale);
        return sale;
    }

    @Override
    @Transactional
    public Sale removeSale(Sale sale) {
        return null;
    }

    @Override
    @Transactional
    public Sale updateSale(Sale sale) {
        return null;
    }

    @Override
    @Transactional
    public List<Sale> getAll() {
        Session session  = entityManager.unwrap(Session.class);
        String hql = "from Sale";
        List<Sale> sales = session.createQuery(hql,Sale.class).getResultList();
        return sales;
    }

    @Override
    @Transactional
    public Sale getById(int id) {
        Session session  = entityManager.unwrap(Session.class);
        Sale sale = session.get(Sale.class,id);
        return sale;
    }

    @Override
    public List<Sale> getByCustomer(String customer) {
        Session session  = entityManager.unwrap(Session.class);
        String hql = "from Sale where buyer=" + "'" +customer+"'";
        List<Sale> sales = session.createQuery(hql,Sale.class).getResultList();
        return sales;
    }

    @Override
    public List<Sale> getByFisherman(String fisherman) {
        Session session  = entityManager.unwrap(Session.class);
        List<Fish> fish = fishRepositoryForSale.getAllFishForFisherman(fisherman);
        List<Sale> sales = new ArrayList<>();
        for(Fish token : fish){
            int id = token.getId();
            String hql = "from Sale where fish=" + id;
            List<Sale> sale = session.createQuery(hql,Sale.class).getResultList();
            sales.addAll(sale);
        }

        return sales;
    }

    @Override
    public List<Sale> getByAuction(int id) {
        Session session  = entityManager.unwrap(Session.class);
        String hql = "from Sale where id=" + id;
        List<Sale> sales = session.createQuery(hql,Sale.class).getResultList();
        return sales;
    }
}
