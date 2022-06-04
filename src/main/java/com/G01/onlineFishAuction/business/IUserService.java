package com.G01.onlineFishAuction.business;

import com.G01.onlineFishAuction.entities.CooperativeHead;
import com.G01.onlineFishAuction.entities.CooperativeMember;
import com.G01.onlineFishAuction.entities.Customer;
import com.G01.onlineFishAuction.entities.Fisherman;
import com.G01.onlineFishAuction.exceptions.CodeNotFoundException;
import com.G01.onlineFishAuction.exceptions.UsernameAlreadyInUse;
import com.G01.onlineFishAuction.exceptions.UsernameNotFoundException;

import java.sql.SQLException;
import java.util.List;
public interface IUserService {
	String login(String username, String password) throws UsernameNotFoundException;
	void customerRegister(Customer newCustomer) throws SQLException, UsernameAlreadyInUse;
	void cooperativeMemberRegister(CooperativeMember newMember,String code) throws CodeNotFoundException, UsernameAlreadyInUse;
	void deleteCustomer(Customer customer);
	void deleteCooperativeMember(CooperativeMember cooperativeMember);
	void deleteFisherman(Fisherman fisherman);
	List<Customer> getAllCustomers();
	List<CooperativeMember> getAllMembers();
	List<CooperativeHead> getAdmin();
	public List<Fisherman> getFisherman();
}
