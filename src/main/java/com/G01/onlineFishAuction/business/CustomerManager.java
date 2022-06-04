package com.G01.onlineFishAuction.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerManager implements ICustomerService {

	IAuctionService auctionService;

	@Autowired
	public CustomerManager(IAuctionService auctionService) {
		this.auctionService = auctionService;
	}

	public CustomerManager() {
	}

	@Override
	public void joinAuction(String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void makeBid(float amount, String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void makePayment() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pay() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterTheCart(String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterPurchaseHistory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterPaymentInfo() {
		// TODO Auto-generated method stub
		
	}


}
