package com.G01.onlineFishAuction.dataAccess;

import com.G01.onlineFishAuction.entities.Fish;

import java.util.List;

public interface IFishRepository extends IFishRepositoryForSale {
    public void recordFish(Fish fish);
    public void deleteFish(Fish fish);
    public List<Fish> getAll();
    public Fish getFish(String id);
    public List<Fish> getAllFishForAuction(int id);

}
