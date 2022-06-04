package com.G01.onlineFishAuction.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "fish")
public class Fish {
    @Id
    @Column(name = "id", nullable = false)
    private String id;
    @Column(name = "type", nullable = false)
    private String type;
    @Column(name = "status", nullable = false)
    private String status;
    @Column(name = "weight")
    private float weight;
    @Column(name = "price")
    private float price;
    @Column(name = "fishermanid", nullable = false)
    private String fishermanid;
    @Column(name="auctionid",nullable = false)
    private int auctionid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
