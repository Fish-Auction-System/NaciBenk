package com.G01.onlineFishAuction.DTO;

import com.G01.onlineFishAuction.entities.Fish;
import com.G01.onlineFishAuction.entities.Sale;

public class SaleFeedback {
    private Sale sale;
    private Fish fish;

    public SaleFeedback(Sale sale, Fish fish) {
        this.sale = sale;
        this.fish = fish;
    }

    public SaleFeedback() {
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Fish getFish() {
        return fish;
    }

    public void setFish(Fish fish) {
        this.fish = fish;
    }
}
