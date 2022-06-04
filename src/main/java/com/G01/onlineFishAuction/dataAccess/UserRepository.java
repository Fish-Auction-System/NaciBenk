package com.G01.onlineFishAuction.dataAccess;

//import java.util.List;

import javax.persistence.EntityManager;

import com.G01.onlineFishAuction.exceptions.UsernameNotFoundException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.G01.onlineFishAuction.entities.*;

@Repository
public class UserRepository implements IUserRepository{
	private EntityManager entityManager;

	@Autowired
	public UserRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public String checkLoginData(String username, String password) throws UsernameNotFoundException{
		Session session  = entityManager.unwrap(Session.class);
		IUser user;
		if((user= session.get(CooperativeHead.class, username)) != null) {
			if (user.getPassword().equals(password)) {
				return "CooperativeHead";
			}
			return null;
		}

		else if((user = session.get(Customer.class,username)) != null) {
			if(user.getPassword().equals(password)){
				return "Customer";
			}
			return null;
		}
		else if((user = session.get(CooperativeMember.class,username)) != null) {
			if(user.getPassword().equals(password)){
				return "cooperativeMember";
			}
			return null;
		}
		else if((user = session.get(Fisherman.class,username)) != null) {
			if(user.getPassword().equals(password)){
				return "fisherman";
			}
			return null;

		}
		throw new  UsernameNotFoundException();

	}





}
