package com.G01.onlineFishAuction.dataAccess;

import com.G01.onlineFishAuction.entities.Auction;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Repository
public class HibernateAuctionRepository implements  IAuctionRepository{
    private EntityManager entityManager;

    @Autowired
    public HibernateAuctionRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void addAuction(Auction auction) {
        Session session  = entityManager.unwrap(Session.class);
        session.saveOrUpdate(auction);
    }

    @Override
    @Transactional
    public List<Auction> getAll() {
        Session session  = entityManager.unwrap(Session.class);
        List<Auction> auctions = session.createQuery("from Auction", Auction.class).getResultList();
        return auctions;
    }

    @Override
    @Transactional
    public boolean isAuctionExists(int idName){
        Iterator<Auction> newAuction=getAll().iterator();
        ArrayList<Integer> idList=new ArrayList<>();
        while (newAuction.hasNext()){
            idList.add(newAuction.next().getId());
        }
        return idList.contains(idName);
    }


    @Override
    @Transactional
    public List<Auction> getLastFive(){
        Session session  = entityManager.unwrap(Session.class);
        List<Auction> all = session.createQuery("from Auction",Auction.class).getResultList();
        Collections.sort(all);
        List<Auction> toReturn = new ArrayList<>();
        toReturn.add(all.get(0));
        toReturn.add(all.get(1));
        toReturn.add(all.get(2));
        toReturn.add(all.get(3));
        toReturn.add(all.get(4));
        return toReturn;
    }

    @Override
    @Transactional
    public Auction getById(int id){
        Session session  = entityManager.unwrap(Session.class);
        return session.find(Auction.class,id);
    }




}
