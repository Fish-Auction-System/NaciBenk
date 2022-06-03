package com.G01.onlineFishAuction.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="auction")
public class Auction {
    @Id
    @Column(name="id")
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="date")
    private float date;
    @Column(name="quota")
    private int quota;


    public Auction(String name, float date, int id, int quota) {
        this.name = name;
        this.date = date;
        this.id = id;
        this.quota = quota;
    }
    public Auction(){

    }

    public String getName() {
        return name;
    }

    public float getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public int getQuota() {
        return quota;
    }
}
