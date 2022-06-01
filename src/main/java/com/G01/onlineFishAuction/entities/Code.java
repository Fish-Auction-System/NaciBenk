package com.G01.onlineFishAuction.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="code")
public class Code {
	@Id
	@Column(name="membercode")
	String membercode;

	public Code(String memberCode) {
		super();
		this.membercode = memberCode;
	}
	
	public Code() {
		
	}

	public String getMemberCode() {
		return membercode;
	}
	
	
}
