package com.lanbao.redis.dao;

import org.springframework.data.redis.core.RedisTemplate;

public interface RedisBaseDao<T> {
	/**
	 * 将对象保存至内存
	 * @param t
	 */
	public void put(String key,T t,Class<T>cls);

	/**
	 * 从内存中读取单个对象
	 * @param t
	 * @param id
	 * @return
	 */
	public T get(String key,Class<T> cls);

	void set(String key, T t);

	T get(String key);

	public void setT(RedisTemplate redisTemplate);

	void delete(String key);
	
}
