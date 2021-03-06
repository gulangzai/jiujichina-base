﻿package com.lanbao.base;

import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Element; 
import com.lanbao.utils.Pub;

public class BaseVO extends Hashtable implements Serializable {
	
	
	public static final int OP_STRING = 0x01;
	public static final int OP_DATE = 0x02;
	public static final int OP_INT = 0x04;
	public static final int OP_DOUBLE = 0x08;
	public static final int OP_BLOB = 0x10;
	public static final int OP_NCHAR = 0x20;
	public static final int OP_CLOB = 0x40;
	public static final int OP_DETAIL_LIST=0x80;

	public static final int TP_PK = 0x010000;
	public static final int TP_SEQUENCE = 0x020000;
	public static final int TP_PARTITION = 0x040000;
	public static final int TP_NORMAL = 0x000000;

	private static final String EXT_SEQUENCE = "_S_!@#";
	private static final String EXT_CHANGED = "_C_$%*";
	private static final String EXT_DIC = "_D_)(*";
	private static final String EXT_ZZJG = "_Z_&^%";
	private static final String EXT_XZJG = "_X_%$#";
	private static final String EXT_YHID = "_Y_#@!";
	private static final String EXT_DATE = "_D_#^(";
	private static final String EXT_JLX = "_J_(*^";
	private static final String EXT_QDZ = "_Q_*^)";
	private static final String EXT_TRANSLATER      = "__TRANSLATER*^)";
	private static final String EXT_TABLENAME      = "__TABLENAME*^)";
	private static final String EXT_CODEFIELD      = "__CODEFIELD*^)";
	private static final String EXT_VALUEFIELD      = "__VALUEFIELD*^)";
    private static final String EXT_THOUSANDS = "_THOUSANDS%&#!(";
    private static final String EXT_SJBH = "_SJ^%$#";

	 

	public static final String SIMPLE_NAME = "1";
	public static final String FULL_NAME = "2";

	public BaseVO() {
		super(10);
		fieldMap = new Hashtable(10);
		fieldExtern = new Hashtable(10);
	}

	public BaseVO(int n) {
		super(n);
		fieldMap = new Hashtable(n);
		fieldExtern = new Hashtable(n);
	}

	private Hashtable fieldMap;
	private String tableName;
	private String owner;
	private Hashtable fieldExtern;
	private Connection conn = null;
	
	public void setConnection (Connection conn){
		this.conn = conn;
	}
	public Connection getConnection(){
		return this.conn;
	}

	public void setChanged(String fieldName) {
		if (fieldName != null)
			this.fieldExtern.put(fieldName.toUpperCase() + this.EXT_CHANGED,
					fieldName);
	}

	public void setChanged() {
		Enumeration enumer = this.fieldMap.keys();
		if (enumer != null) {
			while (enumer.hasMoreElements()) {
				String key = (String) enumer.nextElement();
				this.fieldExtern.put(key.toUpperCase() + this.EXT_CHANGED, key);
			}
		}
	}

	public void setUnChanged(String fieldName) {
		if (fieldName != null)
			this.fieldExtern.remove(fieldName.toUpperCase() + this.EXT_CHANGED);
	}

	public void setUnChanged() {
		Enumeration enumer = this.fieldMap.keys();
		if (enumer != null) {
			while (enumer.hasMoreElements()) {
				String key = (String) enumer.nextElement();
				this.fieldExtern.remove(key + this.EXT_CHANGED);
			}
		}
	}

	public Enumeration getFields() {
		return this.fieldMap.keys();
	}

	public String[] getPartitionFields() {
		Enumeration enumer = this.fieldMap.keys();
		ArrayList list = new ArrayList(3);
		while (enumer.hasMoreElements()) {
			String key = (String) enumer.nextElement();
			if (this.isPartition(key))
				list.add(key);
		}
		String[] res = new String[list.size()];
		return (String[]) list.toArray(res);
	}

	 

	public void setVOTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getVOTableName() {
		return this.tableName;
	}

	public void setVOOwner(String owner) {
		this.owner = owner;
	}

	public String getVOOwner() {
		return this.owner;
	}

	public void bindFieldToSequence(String fieldName, String sequenceName) {
		if (fieldExtern == null)
			fieldExtern = new Hashtable(10);
		fieldExtern.put(fieldName.toUpperCase() + this.EXT_SEQUENCE,
				sequenceName);
	}

	public void bindFieldToDic(String fieldName, String dicName) {
		if (fieldExtern == null)
			fieldExtern = new Hashtable(10);
		fieldExtern.put(fieldName.toUpperCase() + this.EXT_DIC, dicName);
	}
	public void bindFieldToTranslater(String fieldName,String tableName,String codeField,String valueField){
    	if(fieldExtern == null)
            fieldExtern = new Hashtable(10);
        fieldExtern.put(fieldName.toUpperCase()+this.EXT_TRANSLATER,fieldName);
        fieldExtern.put(fieldName.toUpperCase()+this.EXT_TABLENAME,tableName);
        fieldExtern.put(fieldName.toUpperCase()+this.EXT_CODEFIELD,codeField);
        fieldExtern.put(fieldName.toUpperCase()+this.EXT_VALUEFIELD,valueField);
    }
	
	public boolean isBindDic(String fieldName) {
		return this.fieldExtern.get(fieldName.toUpperCase() + this.EXT_DIC) != null;
	}
	public boolean isBindSjbh(String fieldName) {
		return this.fieldExtern.get(fieldName.toUpperCase() + this.EXT_SJBH) != null;
	}

	public String getBindDic(String fieldName) {
		return (String) this.fieldExtern.get(fieldName.toUpperCase()
				+ this.EXT_DIC);
	}

	public boolean isBindOrgDept(String fieldName) {
		return this.fieldExtern.get(fieldName.toUpperCase() + this.EXT_ZZJG) != null;
	}
	public boolean isBindThousand(String fieldName) {
		return this.fieldExtern.get(fieldName.toUpperCase() + this.EXT_THOUSANDS) != null;
	}

	public boolean isBindOrgXzjg(String fieldName) {
		return this.fieldExtern.get(fieldName.toUpperCase() + this.EXT_XZJG) != null;
	}
	public boolean isBindFieldToTranslater(String fieldName){
    	return this.fieldExtern.get(fieldName.toUpperCase()+this.EXT_TRANSLATER) != null;	
	}
	
	public boolean isBindJlx(String fieldName) {
		return this.fieldExtern.get(fieldName.toUpperCase() + this.EXT_JLX) != null;
	}

	public boolean isBindQdz(String fieldName) {
		return this.fieldExtern.get(fieldName.toUpperCase() + this.EXT_QDZ) != null;
	}

	public String getBindJlx(String fieldName) {
		return (String) this.fieldExtern.get(fieldName.toUpperCase()
				+ this.EXT_JLX);
	}

	public String getBindQdz(String fieldName) {
		return (String) this.fieldExtern.get(fieldName.toUpperCase()
				+ this.EXT_QDZ);
	}

	public String getBindOrgDept(String fieldName) {
		return (String) this.fieldExtern.get(fieldName.toUpperCase()
				+ this.EXT_ZZJG);
	}
	public String getBindSjbh(String fieldName) {
		return (String) this.fieldExtern.get(fieldName.toUpperCase()
				+ this.EXT_SJBH);
	}

	public String getBindOrgXzjg(String fieldName) {
		return (String) this.fieldExtern.get(fieldName.toUpperCase()
				+ this.EXT_XZJG);
	}
    public String getBindFieldToTranslaterTableName(String fieldName){
   	   return (String) this.fieldExtern.get(fieldName.toUpperCase()+this.EXT_TABLENAME);
     }
    public String getBindFieldToTranslaterCodeField(String fieldName){
    	   return (String) this.fieldExtern.get(fieldName.toUpperCase()+this.EXT_CODEFIELD);
      }
    public String getBindFieldToTranslaterValueField(String fieldName){
 	   return (String) this.fieldExtern.get(fieldName.toUpperCase()+this.EXT_VALUEFIELD);
   }
    
	public boolean isBindOrgInfo(String fieldName) {
		return this.fieldExtern.get(fieldName.toUpperCase() + this.EXT_XZJG) != null;
	}

	public String getBindOrgInfo(String fieldName) {
		return (String) this.fieldExtern.get(fieldName.toUpperCase()
				+ this.EXT_XZJG);
	}

	public boolean isBindUserid(String fieldName) {
		return this.fieldExtern.get(fieldName.toUpperCase() + this.EXT_YHID) != null;
	}

	public boolean isSetDateFormat(String fieldName) {
		return this.fieldExtern.get(fieldName.toUpperCase() + this.EXT_DATE) != null;
	}

	public String getBindDateFormat(String fieldName) {
		return (String) this.fieldExtern.get(fieldName.toUpperCase()
				+ this.EXT_DATE);
	}

	public void bindFieldToJlx(String fieldName) {
		fieldExtern.put(fieldName.toUpperCase() + this.EXT_JLX, this.FULL_NAME);
	}
	public void bindFieldToSjbh(String fieldName) {
		fieldExtern.put(fieldName.toUpperCase() + this.EXT_SJBH, this.FULL_NAME);
	}

	public void bindFieldToQdz(String fieldName) {
		fieldExtern.put(fieldName.toUpperCase() + this.EXT_QDZ, this.FULL_NAME);
	}

	public void bindFieldToOrgDept(String fieldName) {
		fieldExtern
				.put(fieldName.toUpperCase() + this.EXT_ZZJG, this.FULL_NAME);
	}

	public void bindFieldToOrgDept(String fieldName, String type) {
		if (fieldExtern == null)
			fieldExtern = new Hashtable(10);
		fieldExtern.put(fieldName.toUpperCase() + this.EXT_ZZJG, type);
	}

	public void bindFieldToOrgXzjg(String fieldName) {
		fieldExtern
				.put(fieldName.toUpperCase() + this.EXT_XZJG, this.FULL_NAME);
	}

	public void bindFieldToOrgXzjg(String fieldName, String type) {
		if (fieldExtern == null)
			fieldExtern = new Hashtable(10);
		fieldExtern.put(fieldName.toUpperCase() + this.EXT_XZJG, type);
	}

	public void bindFieldToOrgInfo(String fieldName) {
		fieldExtern
				.put(fieldName.toUpperCase() + this.EXT_XZJG, this.FULL_NAME);
	}

	public void bindFieldToOrgInfo(String fieldName, String type) {
		if (fieldExtern == null)
			fieldExtern = new Hashtable(10);
		fieldExtern.put(fieldName.toUpperCase() + this.EXT_XZJG, type);
	}

	public void bindFieldToUserid(String fieldName) {
		if (fieldExtern == null)
			fieldExtern = new Hashtable(10);
		fieldExtern.put(fieldName.toUpperCase() + this.EXT_YHID, fieldName);
	}
	public void bindFieldToThousand(String fieldName) {
		if (fieldExtern == null)
			fieldExtern = new Hashtable(10);
		fieldExtern.put(fieldName.toUpperCase() + this.EXT_THOUSANDS, fieldName);
	}

	public void setFieldDateFormat(String fieldName, String format) {
		if (fieldExtern == null)
			fieldExtern = new Hashtable(10);
		fieldExtern.put(fieldName.toUpperCase() + this.EXT_DATE, format);
	}

	public void addField(String fieldName, int style) {
		fieldMap.put(fieldName.toUpperCase(), new Integer(style));
	}

	public void addField(String fieldName) {
		addField(fieldName.toUpperCase(), TP_NORMAL | OP_STRING);
	}

	public void setInternal(String fieldName, Object obj) {
		if (fieldMap.containsKey(fieldName.toUpperCase())) {
			this.fieldExtern.put(fieldName.toUpperCase() + this.EXT_CHANGED,
					fieldName);
			if (obj != null)
				this.put(fieldName.toUpperCase(), obj);
			else
				this.remove(fieldName.toUpperCase());
		}
	}

	public boolean isValidField(String fieldName) {
		return this.fieldMap.containsKey(fieldName);
	}

	public Object getInternal(String fieldName) {
		return this.get(fieldName.toUpperCase());
	}

	public int getFieldStyle(String fieldName) {
		return ((Integer) this.fieldMap.get(fieldName.toUpperCase()))
				.intValue() & 0xffff0000;
	}

	public int getFieldType(String fieldName) {
		return ((Integer) this.fieldMap.get(fieldName.toUpperCase()))
				.intValue() & 0x0000ffff;
	}

	public boolean isBlob(String fieldName) {
		return (((Integer) this.fieldMap.get(fieldName.toUpperCase()))
				.intValue() & this.OP_BLOB) > 0;
	}

	public boolean isClob(String fieldName) {
		return (((Integer) this.fieldMap.get(fieldName.toUpperCase()))
				.intValue() & this.OP_CLOB) > 0;
	}
	
	public boolean isDetailList(String fieldName) {
		return (((Integer) this.fieldMap.get(fieldName.toUpperCase()))
				.intValue()& this.OP_DETAIL_LIST)>0;
	}

	public boolean isPartition(String fieldName) {
		return (((Integer) this.fieldMap.get(fieldName.toUpperCase()))
				.intValue() & this.TP_PARTITION) > 0;
	}

	public boolean isNchar(String fieldName) {
		return (((Integer) this.fieldMap.get(fieldName.toUpperCase()))
				.intValue() & this.OP_NCHAR) > 0;
	}

	public boolean isBindSequence(String fieldName) {
		return this.fieldExtern
				.get(fieldName.toUpperCase() + this.EXT_SEQUENCE) != null;
	}

	public String getBindSequence(String fieldName) {
		return (String) this.fieldExtern.get(fieldName.toUpperCase()
				+ this.EXT_SEQUENCE);
	}

	public boolean isChanged(String fieldName) {
		return this.fieldExtern.get(fieldName.toUpperCase() + this.EXT_CHANGED) != null;
	}
	
	public boolean isDate(String fieldName) {
		return (((Integer) this.fieldMap.get(fieldName.toUpperCase()))
				.intValue() & this.OP_DATE) > 0;
	}

 
	public JSONArray doInitJson(String initJson)
	{
		JSONObject response = JSONObject.fromObject(initJson);
		String response_txt = response.getString("response");
		JSONObject data = JSONObject.fromObject(response_txt);
		String data_txt = data.getString("data"); 
		JSONArray jsonArray = (JSONArray) JSONSerializer.toJSON(data_txt); 
		return jsonArray;
	}
	
	
	public static boolean isGoodJson(String json_txt) {
		if (StringUtils.isBlank(json_txt)) {
			return false;
		}
		try {
			JSONObject jsonObject = JSONObject.fromObject( json_txt ); 
			return true;
		} catch (JSONException e) { 
			return false;
		}
	}
	
	public boolean setValueFromJson(JSONObject jsonObject) throws Exception {

		List<Object> arr = jsonObject.names();
		Collection<Object> arra = jsonObject.values();// 获取值
		for (Object name : arr) {
			String key = name.toString().toUpperCase();
			if (!this.fieldMap.containsKey(key))
				continue;
			String value = jsonObject.getString(name.toString());
			String code ="";
			String format = "";
			if(this.isGoodJson(value))
			{
				JSONObject value_txt = JSONObject.fromObject(value);
				List<Object> arr_value = value_txt.names();
				for (Object value_name : arr_value) {
					String value_key = value_name.toString();
					if("code".equalsIgnoreCase(value_key))
					{
						value = value_txt.getString(value_key.toString());
						break;
					}
					if("dateformat".equalsIgnoreCase(value_key))
					{
						value = value_txt.getString("value");
						break;
					}
				}
			}
			this.setChanged(key);
			if (this.isBlob(key)) {
				if (!Pub.empty(value))
					this.setInternal(key, Pub.fromBase64(value));
			}
			else if (this.isClob(key)) {
				if (!Pub.empty(value))
					// this.setInternal(key,Pub.fromBase64(field.getText()));
					this.setInternal(key, value);
			} else if (this.isDate(key)) {
				if (!Pub.empty(value)) {
					String val = value.replaceAll("-", "")
							.replaceAll("_", "").replaceAll(":", "")
							.replaceAll("/", "").replaceAll(" ", "").trim()
							+ "00000000000000";
					this.setInternal(key,
							Pub.toDate("yyyyMMddHHmmss", val.substring(0, 14)));
				}
			} else
				this.setInternal(key, value);
			
			
			// System.out.println(name.toString());
		} 
	return true;
}
	

public String getRowJson() throws Exception{
	
	String json = "{\"response\":{\"data\":["+getRowJsonSingle()+"]}}";
		
	return json;
} 


public String getRowJsonSingle() throws Exception {
	
	String json = "{";
	Enumeration enumer = getFields();
	while (enumer.hasMoreElements()) {
		String key = (String) enumer.nextElement();
		String value = "";
		json +=  "\""+key.toUpperCase()+"\":";
		if (isDate(key)) {
			Date date = (Date) this.getInternal(key);
			if (date != null) {
				value = Pub.getDate("yyyyMMddHHmmss", date);
				if (isSetDateFormat(key)) {
					String sv = Pub.getDate(this.getBindDateFormat(key),
							date);
					//日期型返回sv值2013-07-31格式的字符串，_SV同样返回2013-07-31串
					json += "\""+sv+"\",\""+key.toUpperCase()+"_SV\":\""+sv+"\"";
				} else {
					json += "\""+value+"\"";
				}
			}
			else{
				json += "\"\"";
			}
		} else if (isBlob(key)) {
			byte[] barr = (byte[]) this.getInternal(key);
			if (barr != null) {
				value = Pub.toBase64(barr);
			}
		}
		else if (isClob(key)) {
			try {
				byte[] arr = (byte[]) this.getInternal(key);
				if (arr != null) {
					value = new String(arr, "GBK");
				}
			} catch (ClassCastException cce) {
				value = (String) this.getInternal(key);
			}

			if (value == null)
				value = "";
		} else // if(isString(key) || isInt(key) || isNchar(key))
		{
			value = (String) this.getInternal(key);
			if (value == null)
				value = "";
			if (isBindDic(key)) {
				String[] dics = value.split(",");
				if (dics.length == 1) {
					String strval = Pub.getDictValueByCode(
							this.getBindDic(key), value);
					if (strval == null){
						strval = "";
						json +="\""+value+"\"";
					}else{
					json += "\""+value+"\",\""+key.toUpperCase()+"_SV\":\""+strval+"\"";
					}
				} else {
					String sv = "";
					for (int i = 0; i < dics.length; i++) {
						String strval = Pub.getDictValueByCode(
								this.getBindDic(key), dics[i]);
						if(strval !=null){
						  sv = sv + strval;
						  if (i < dics.length - 1) {
						 	sv = sv + ",";
						  }
						}
					}
					if(!Pub.empty(sv)){

						json += "\""+value+"\",\""+key.toUpperCase()+"_SV\":\""+sv+"\"";
					}else{
						json += "\""+value+"\"";
					}
				}
			}  else if (isBindThousand(key)) {
				String strval = Pub.NumberToThousand(value);
				if(!Pub.empty(strval)){
					strval = Pub.NumberAddDec(strval);
				}
				if (strval != null&&!"".equals(strval)){
					json += "\""+value+"\",\""+key.toUpperCase()+"_SV\":\""+strval+"\"";
				}else{
					json += "\""+value+"\"";
				}
			}
			else{
				//add by cbl
				if(!Pub.empty(value))
				{
					value = BaseDAO.EncodeJsString(value);
				}
				json +="\""+value+"\"";
			}
		} 
		json += ",";
		
	}
	
	json = json.substring(0,json.length()-1);
	
	json +="}";
	//处理取出的字符串
//	json = BaseDAO.stringToJson(json);//处理返回前台的特殊符号
	return json;
}

	
public boolean isEmpty(String fieldName) {
	if (this.get(fieldName.toUpperCase()) instanceof java.lang.String) {
		return Pub.empty((String) this.get(fieldName.toUpperCase()));
	}
	return this.get(fieldName.toUpperCase()) == null;
}

	
 
}