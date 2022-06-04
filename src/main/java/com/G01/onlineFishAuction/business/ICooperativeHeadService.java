package com.G01.onlineFishAuction.business;

import java.util.Date;

import com.G01.onlineFishAuction.entities.Auction;
import com.G01.onlineFishAuction.exceptions.UsernameAlreadyInUse;

public interface ICooperativeHeadService {
	Auction scheduleAuction(Date date,String name);
	String createCode() throws UsernameAlreadyInUse;
}
