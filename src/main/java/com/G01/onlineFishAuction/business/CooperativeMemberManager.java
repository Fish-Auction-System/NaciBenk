package com.G01.onlineFishAuction.business;

import com.G01.onlineFishAuction.entities.CooperativeMember;
import com.G01.onlineFishAuction.exceptions.UsernameAlreadyInUse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.G01.onlineFishAuction.dataAccess.IFishRepository;
import com.G01.onlineFishAuction.dataAccess.IFishermanRepository;
import com.G01.onlineFishAuction.entities.Fisherman;

import java.util.ArrayList;
import java.util.Iterator;

@Service
public class CooperativeMemberManager implements ICooperativeMemberService{
	private IFishRepository fishRepository;
	//ISaleRepository saleRepository;
	private IFishermanRepository fishermanRepository;
	@Autowired
	public CooperativeMemberManager(IFishRepository fishRepository, IFishermanRepository fishermanRepository) {
		super();
		this.fishRepository = fishRepository;
		this.fishermanRepository = fishermanRepository;
	}
	public CooperativeMemberManager() {
		
	}

	@Override
	public void addFish() {
		
		
	}

	@Override
	@Transactional
	public void registerFisherman(Fisherman fisherman) throws UsernameAlreadyInUse {

		Iterator<Fisherman> memberIterator = fishermanRepository.getAll().iterator();
		ArrayList<String> usernames = new ArrayList<>();
		while (memberIterator.hasNext()){
			usernames.add(memberIterator.next().getUsername());
		}
		if (usernames.contains(fisherman.getUsername())){
			throw new UsernameAlreadyInUse("User Already Exists!");
		}
		else{
			fishermanRepository.addFisherman(fisherman);
		}
	}

}
