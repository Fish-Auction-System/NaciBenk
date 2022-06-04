package com.G01.onlineFishAuction.business;

import java.util.ArrayList;
import java.util.Date;

import com.G01.onlineFishAuction.dataAccess.ICodeRepository;
import com.G01.onlineFishAuction.exceptions.UsernameAlreadyInUse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.G01.onlineFishAuction.entities.Auction;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CooperativeHeadManager implements ICooperativeHeadService {
	private ICodeRepository iCodeRepository;

	@Autowired
	public CooperativeHeadManager(ICodeRepository iCodeRepository) {
		this.iCodeRepository = iCodeRepository;
	}

	@Override
	public Auction scheduleAuction(Date date, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public String createCode() throws UsernameAlreadyInUse {

		return iCodeRepository.generateAndRecord();

	}


}
