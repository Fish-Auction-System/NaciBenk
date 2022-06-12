package com.G01.onlineFishAuction.dataAccess;

import com.G01.onlineFishAuction.entities.Fish;

import java.util.List;

public interface IFishRepositoryForSale  {
    List<Fish> getAllFishForFisherman(String fisherman);
}
