package com.lanbao.redis.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;


import com.lanbao.redis.dao.RedisBaseDao;

@Repository("redisBaseTDao")
public class RedisBaseTDaoImpl<T> implements RedisBaseDao<T> {
	
	@Autowired
	private RedisTemplate<String, T> redisTemplate;

	@SuppressWarnings("unchecked")
	@Override
	public void put(String key, final T t, Class<T> cls) {
		redisTemplate.opsForHash().put(cls.getName(), key, t);
	}
	
	@Override
	public void set(String key,T t){
		redisTemplate.opsForValue().set(key,t);
	}

	@Override
	public T get(String key, Class<T> cls) {
		return (T) redisTemplate.opsForHash().get(cls.getName(), key);
	}
	
	@Override
	public T get(String key){  
		 return redisTemplate.opsForValue().get(key);
	}

 
	@Override
	public void delete(String key){
		redisTemplate.delete(key);
	}

	@Override
	public void setT(RedisTemplate redisTemplate) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
