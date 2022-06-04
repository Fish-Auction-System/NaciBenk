package com.G01.onlineFishAuction.dataAccess;

import com.G01.onlineFishAuction.exceptions.UsernameNotFoundException;

public interface IUserRepository {
	String checkLoginData(String username, String password) throws UsernameNotFoundException;
}
