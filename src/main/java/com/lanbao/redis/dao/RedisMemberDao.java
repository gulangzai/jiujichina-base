package com.lanbao.redis.dao;

import com.lanbao.redis.vo.MemberT;

public interface RedisMemberDao {
	
	public void save(MemberT memberT);

	public MemberT read(String id);

	public void delete(String id);
}
