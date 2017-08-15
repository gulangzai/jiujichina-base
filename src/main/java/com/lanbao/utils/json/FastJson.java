package com.lanbao.utils.json;

import com.alibaba.fastjson.JSON;

public class FastJson {
	
	public static String praseObject(int size ,Object obj){ 
		String dd = JSON.toJSONString(obj);
		String str = "{\"total\":"+size+",\"rows\":"+dd+"}"; 
		return str;
	}
}
