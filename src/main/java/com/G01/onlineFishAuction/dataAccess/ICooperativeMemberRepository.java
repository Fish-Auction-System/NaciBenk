package com.G01.onlineFishAuction.dataAccess;

import java.util.List;

import com.G01.onlineFishAuction.entities.CooperativeMember;
import com.G01.onlineFishAuction.exceptions.CodeNotFoundException;

public interface ICooperativeMemberRepository {
	List<CooperativeMember> getAll();
	CooperativeMember getMember(String username);
	void recordMember(CooperativeMember member,String code) throws CodeNotFoundException;
	void delete(CooperativeMember member);

}
