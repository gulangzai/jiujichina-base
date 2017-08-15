package com.lanbao.redis.vo;

import java.io.Serializable;
import java.util.List;

public class RedisSecondGoodsCateogryTVo implements Serializable {

	private List<GoodsCategoryT> goodsCategoryTs;

	public List<GoodsCategoryT> getGoodsCategoryTs() {
		return goodsCategoryTs;
	}

	public void setGoodsCategoryTs(List<GoodsCategoryT> goodsCategoryTs) {
		this.goodsCategoryTs = goodsCategoryTs;
	}

	
}
