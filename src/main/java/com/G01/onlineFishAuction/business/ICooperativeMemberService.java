package com.G01.onlineFishAuction.business;
import com.G01.onlineFishAuction.entities.*;
import com.G01.onlineFishAuction.exceptions.UsernameAlreadyInUse;

public interface ICooperativeMemberService {
	void addFish();
	void registerFisherman(Fisherman fisherman) throws UsernameAlreadyInUse;
	//Fish getUnsoldFish();
	//void addFish(Fish fish);
	//changeFishStatus(String status);
}
