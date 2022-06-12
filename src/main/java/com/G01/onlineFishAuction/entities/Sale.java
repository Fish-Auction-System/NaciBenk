package com.G01.onlineFishAuction.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sale")
public class Sale {
    @Id
    @Column(name="id")
    private int id ;

    @Column(name="fish")
    private int fish;

    @Column(name="price")
    private float price;

    @Column(name="buyer")
    private String buyer;

    @Column(name="auction")
    private int auction;

    @Column(name="status")
    private int status;



    public Sale(int id, int fish, float price, String buyer, int auction, int status) {
        this.id = id;
        this.fish = fish;
        this.price = price;
        this.buyer = buyer;
        this.auction = auction;
        this.status = status;

    }

    public Sale() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFish() {
        return fish;
    }

    public void setFish(int fish) {
        this.fish = fish;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public int getAuction() {
        return auction;
    }

    public void setAuction(int auction) {
        this.auction = auction;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }



    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", fish=" + fish +
                ", price=" + price +
                ", buyer='" + buyer + '\'' +
                '}';
    }
}
