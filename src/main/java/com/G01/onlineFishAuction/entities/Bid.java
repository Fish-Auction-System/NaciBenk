package com.G01.onlineFishAuction.entities;


import org.springframework.stereotype.Component;

import javax.persistence.Entity;

@Component
public class Bid {
    private String customer;
    private float bid;
    private int fish;

    public Bid(String customer, float bid, int fish) {
        this.customer = customer;
        this.bid = bid;
        this.fish = fish;
    }

    public Bid() {
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public float getBid() {
        return bid;
    }

    public void setBid(float bid) {
        this.bid = bid;
    }

    public int getFish() {
        return fish;
    }

    public void setFish(int fish) {
        this.fish = fish;
    }
}
