package com.lanbao.redis.vo;

import java.io.Serializable;
import java.util.List;

public class RedisGoodsInFirstCateogryTVo implements Serializable {

	private List<GoodsT> glist;

	public List<GoodsT> getGlist() {
		return glist;
	}

	public void setGlist(List<GoodsT> glist) {
		this.glist = glist;
	}

}
