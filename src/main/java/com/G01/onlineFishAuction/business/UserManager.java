package com.G01.onlineFishAuction.business;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import com.G01.onlineFishAuction.entities.*;
import com.G01.onlineFishAuction.exceptions.CodeNotFoundException;
import com.G01.onlineFishAuction.exceptions.UsernameAlreadyInUse;
import com.G01.onlineFishAuction.exceptions.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.G01.onlineFishAuction.dataAccess.ICooperativeHeadRepository;
import com.G01.onlineFishAuction.dataAccess.ICooperativeMemberRepository;
import com.G01.onlineFishAuction.dataAccess.ICustomerRepository;
import com.G01.onlineFishAuction.dataAccess.IFishermanRepository;
import com.G01.onlineFishAuction.dataAccess.IUserRepository;

@Service
public class UserManager implements IUserService {

	ICustomerRepository customerRepository;
	IUserRepository userRepository;
	ICooperativeMemberRepository cooperativeMemberRepository;
	ICooperativeHeadRepository cooperativeHeadRepository;
	IFishermanRepository fishermanRepository;
	
	
	@Autowired
	public UserManager(ICustomerRepository customerRepository, IUserRepository userRepository,
			ICooperativeMemberRepository cooperativeMemberRepository,
			ICooperativeHeadRepository cooperativeHeadRepository, IFishermanRepository fishermanRepository) {
		//super();
		this.customerRepository = customerRepository;
		this.userRepository = userRepository;
		this.cooperativeMemberRepository = cooperativeMemberRepository;
		this.cooperativeHeadRepository = cooperativeHeadRepository;
		this.fishermanRepository = fishermanRepository;
	}
	
	

	@Override
	@Transactional
	public String login(String username, String password) throws UsernameNotFoundException {
		return userRepository.checkLoginData(username, password);
		
	}
	
	@Override
	@Transactional
	public void customerRegister(Customer newCustomer) throws UsernameAlreadyInUse {
		Iterator<Customer> getAllcustomers = getAllCustomers().iterator();
		ArrayList<String> usernames = new ArrayList<>();
		while (getAllcustomers.hasNext()){
			usernames.add(getAllcustomers.next().getUsername());
		}

		if (usernames.contains(newCustomer.getUsername())){
			throw new UsernameAlreadyInUse("Username already in use!!");
		}else{
			customerRepository.recordCustomer(newCustomer);
		}

	}

	@Override
	@Transactional
	public void cooperativeMemberRegister(CooperativeMember newMember,String code) throws CodeNotFoundException, UsernameAlreadyInUse {
		Iterator<CooperativeMember> memberIterator = getAllMembers().iterator();
		ArrayList<String> usernames = new ArrayList<>();
		while (memberIterator.hasNext()){
			usernames.add(memberIterator.next().getUsername());
		}
		if (usernames.contains(newMember.getUsername())){
			throw new UsernameAlreadyInUse("Username already in use!!");
		}
		else {
			cooperativeMemberRepository.recordMember(newMember,code);
		}
		
	}

	@Override
	@Transactional
	public void deleteCustomer(Customer customer) {
		customerRepository.deleteCustomer(customer);
		
	}

	@Override
	@Transactional
	public void deleteCooperativeMember(CooperativeMember cooperativeMember) {
		cooperativeMemberRepository.delete(cooperativeMember);
		
	}



	@Override
	@Transactional
	public List<Customer> getAllCustomers() {
		return customerRepository.getAll();
	}


	@Override
	@Transactional
	public List<CooperativeMember> getAllMembers() {
		return cooperativeMemberRepository.getAll();
	}



	@Override
	@Transactional
	public List<CooperativeHead> getAdmin() {
		return cooperativeHeadRepository.get();
	}



	@Override
	@Transactional
	public List<Fisherman> getFisherman() {
		return fishermanRepository.getAll();
	}



	@Override
	@Transactional
	public void deleteFisherman(Fisherman fisherman) {
		fishermanRepository.deleteFisherman(fisherman);
		
	}

}
