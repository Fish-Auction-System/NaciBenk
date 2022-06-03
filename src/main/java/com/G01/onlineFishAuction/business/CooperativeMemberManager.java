package com.G01.onlineFishAuction.business;

import com.G01.onlineFishAuction.dataAccess.IAuctionRepository;
import com.G01.onlineFishAuction.entities.Fish;
import com.G01.onlineFishAuction.exceptions.FishermanAuctionNotExists;
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
	private IAuctionRepository auctionRepository;
	@Autowired
	public CooperativeMemberManager(IFishRepository fishRepository,
									IFishermanRepository fishermanRepository,
									IAuctionRepository auctionRepository) {
		super();
		this.fishRepository = fishRepository;
		this.fishermanRepository = fishermanRepository;
		this.auctionRepository = auctionRepository;
	}
	public CooperativeMemberManager() {
		
	}

	@Override
	public void addFish(Fish fish) throws FishermanAuctionNotExists {
		String fishermanId = fish.getFishermanId();
		int auctionId = fish.getAuctionId();
		ArrayList<String> newList = getFishermanIds();
		if (newList.contains(fishermanId)){
			if (auctionRepository.isAuctionExists(auctionId)){
				fishRepository.recordFish(fish);
				return;
			}
			throw new FishermanAuctionNotExists("Auction does not exists!");
		}
		throw new FishermanAuctionNotExists("Fisherman does not exists!");

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

	private ArrayList<String> getFishermanIds(){
		Iterator<Fisherman> newFishermanDb = fishermanRepository.getAll().iterator();
		ArrayList<String> newArrayList = new ArrayList<>();
		while (newFishermanDb.hasNext()){
			newArrayList.add(newFishermanDb.next().getUsername());

		}
		return newArrayList;
	}
}
