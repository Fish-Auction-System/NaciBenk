package com.G01.onlineFishAuction.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="fisherman")
public class Fisherman implements IUser{
	@Id
	@Column(name="username")
	String username;
	@Column(name="password")
	String password;
	@Column(name="mail")
	String mail;
	@Column(name="iban")
	String iban;

	
	
	public Fisherman(String username, String password, String mail, String iban) {
		super();
		this.username = username;
		this.password = password;
		this.mail = mail;
		this.iban = iban;
	}
	public Fisherman() {
		
	}
	
	
	public String getMail() {
		return mail;
	}


	public String getIban() {
		return iban;
	}




	@Override
	public String getUsername() {
		return username;
	}
	@Override
	public String getPassword() {
		return password;
	}
}
