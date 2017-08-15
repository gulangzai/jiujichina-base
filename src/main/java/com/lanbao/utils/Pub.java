package com.lanbao.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.naming.Context;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;


import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**
 * 公用函数类
 */
public class Pub {

	public static String username = "weblogic";

	public static String password = "weblogic";

	public static String ejb_url = "t3://localhost:7001";

	public static String ini_ctx = "weblogic.jndi.T3InitialContextFactory";

	public static Context ctx = null;

	public static Properties properties = null;

	public static final String PREFIX = "base";

	public static final int MAX_DEFAULT_CONDITIONS = 16;

	public static int toInt(String s) {
		return (int) Pub.toNum(s);
	}

	public static double toNum(String s) {
		try {
			return Double.parseDouble(s.replaceAll(",", ""));
		} catch (Exception e) {
			// e.printStackTrace(System.out);
		}
		return 0;
	}
	
	public static boolean empty(String s) {
		if (s == null || s.equals(""))
			return true;
		else
			return false;
	}
	
	public static java.util.Date toDate(String exp, String str) { // yyyy-MM-dd
		// HH:mm:ss
		try {
		return new java.text.SimpleDateFormat(exp).parse(str);
		} catch (Exception e) {
		return null;
		}
    }
	
	public static byte[] fromBase64(String p_Str) throws IOException {
		byte[] byteBuffer = new BASE64Decoder().decodeBuffer(p_Str);
		return byteBuffer;
	}
	 
	public static String getDate(String s) {
		if (Pub.empty(s))
			return "";
		return new
		SimpleDateFormat(s).format(Calendar.getInstance().getTime()).toString(); 
	}
	 
	public static String getDate(String exp, java.util.Date date) {
		if (date == null)
			return "";
		return new SimpleDateFormat(exp).format(date).toString();
	}
	
	public static String toBase64(byte[] p_Buf) {
		return (new BASE64Encoder()).encode(p_Buf).replaceAll("\\s", "");
	}
	
	public static String getDictValueByCode(String dict, String code) { 
		return null;
	}
	
	/**
	 * 数字转换为千分位函数
	 * @param 1000000
	 * @return 1000,000
	 */
	public static String NumberToThousand (String n){
		double d ;
		
		try {
			 d = Double.parseDouble(n);
		} catch (NumberFormatException e) {
			return n;
		}
		DecimalFormat df = new DecimalFormat("#,###.###");
		String m = df.format(d);
		return m;
	}
	
	/**
	 * 补小数点后两位
	 * @param n
	 * @return
	 */
	public static String NumberAddDec(String n){
		String m = "";
		if(!Pub.empty(n)){
			if(n.indexOf(".")>-1){
				StringTokenizer toke = new StringTokenizer(n, ".");
				ArrayList a = new ArrayList();
				while (toke.hasMoreTokens()) {
					String ss = (String) toke.nextElement();
					a.add(ss);
					
				}
				String t = (String)a.get(1);
				if(t.length()<2){
					int k = Integer.parseInt(t)+100;
					m = (String)a.get(0)+"."+(String)a.get(1)+"0";
				}else{
					m = n;
				}
				
			}else{
				return n+".00";
			}
		}
		return m;
		
	}
	
	public static void writeMessage(HttpServletResponse response, String str,
			String encode) throws Exception {
		OutputStream os = response.getOutputStream();
		if (Pub.empty(encode))
			os.write(str.getBytes());
		else
			os.write(str.getBytes(encode));
		os.flush();
		os.close();
	}
	
	public static void writeXmlDocument(HttpServletResponse response,
			Document doc, String encode) throws Exception {
		writeMessage(response, doc.asXML(), encode);
	}
	
	public static void writeMessage(HttpServletResponse response, String message)
			throws Exception {
		writeMessage(response, message, "UTF-8");
	}
	
	
	public static void main(String args[]) { 
		
	}
}