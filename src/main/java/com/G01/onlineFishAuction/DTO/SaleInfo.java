package com.G01.onlineFishAuction.DTO;


import com.G01.onlineFishAuction.entities.Fish;
import org.springframework.stereotype.Component;

@Component
public class SaleInfo {
    private Fish fish;
    private float price ;
    private String buyer;

    public SaleInfo(Fish fish, float price, String buyer) {

        this.fish = fish;
        this.price = price;
        this.buyer = buyer;
    }
    public SaleInfo(SaleInfo other) {

        this.fish = other.getFish();
        this.price = other.getPrice();
        this.buyer = other.getBuyer();
    }

    public SaleInfo() {

    }

    public Fish getFish() {
        return fish;
    }

    public void setFish(Fish fish) {
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

    @Override
    public String toString() {
        return "SaleInfo{" +
                "fish=" + fish +
                ", price=" + price +
                ", buyer='" + buyer + '\'' +
                '}';
    }
}
