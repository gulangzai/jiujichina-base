package com.lanbao.utils.log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
 

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject; 

@Component
public class Logger {
	
	 
	//public LoggerDao<com.lanbao.logger.vo.Logger> loggerDao;
 
	org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Logger.class.getName()); 
       
    /** 
     * 记录日志  
     */  
    public Object record(ProceedingJoinPoint pjp){   
        try {   
            //operName,方法名  
            String methodName = pjp.getSignature().getName();    
            //operParams,方法参数列表  
            StringBuffer sb = new StringBuffer();
            Object[] args = pjp.getArgs();    
            for(int i=0;i<args.length;i++){ 
            	Object obj = args[i]; 
            	if(obj instanceof String){
            		sb.append(JSON.toJSONString(obj)); 
            	}  
            }
          
            //调用目标对象的方法  
            long time = System.currentTimeMillis();
            Object ret = pjp.proceed();
            String sReturn = null;
            time = System.currentTimeMillis() - time;
            if(ret!=null){
            	//sReturn = JSON.toJSONString(ret);
        	  try{
                  sReturn = net.sf.json.JSONObject.fromObject(ret).toString();
               }catch(net.sf.json.JSONException e){
               	sReturn = ret.toString();
               	//e.printStackTrace();
               } 
            } 
            log.info("方法["+methodName+"],参数["+sb.toString()+"]"+"返回值["+sReturn+"]"+"用时:[" + time + "]ms"); 
           /* com.lanbao.logger.vo.Logger logger = new  com.lanbao.logger.vo.Logger();
            logger.put("methodName", methodName);
            logger.put("parameter", sb.toString());	
            logger.put("sReturn", sReturn);
            logger.put("time", String.valueOf(time));  */
           // loggerDao.save(logger,"Logger"); 
            
            return ret ;  
        } catch (Throwable e) {  
        	e.printStackTrace();
           // log.setOperResult("failure") ;  
           // log.setResultMsg(e.getMessage());  
        }finally{ 
            //logService.saveEntity(log);//保存日志的service方法  
        }  
        return null ;  
    } 
    
    /*private static void getMethod(requestJson obj,StringBuffer sb) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
    	Method methlist[] =requestJson.class.getDeclaredMethods();
    	for (int i = 0; i < methlist.length; i++) {
			Method method = methlist[i];
			System.out.println("参数名："+method.getName());
			String methodName = method.getName();
			if(methodName.startsWith("get")){
				Object value = method.invoke(obj, new Object[] {});   
				if(value!=null){ 
				  sb.append(value.toString());
				  System.out.println(value.toString());
				}
			} 
		}
    }*/
    
    private void getField(Object obj) {
		  java.lang.reflect.Field[] fields = obj.getClass().getDeclaredFields();
		  for(java.lang.reflect.Field f:fields){ 
		    System.out.println(getFieldValueByName(f.getName(),obj));
		  }
   }
    
    private Object getFieldValueByName(String fieldName, Object o) {  
    	 Object value= null;
        try {    
            String firstLetter = fieldName.substring(0, 1).toUpperCase();    
            String getter = "get" + firstLetter + fieldName.substring(1);    
            Method method = o.getClass().getMethod(getter, new Class[] {});    
            value = method.invoke(o, new Object[] {});     
        } catch (Exception e) {    
            //log.error(e.getMessage(),e); 
        }   
        return value;    
    }  
    
    public static void main(String[] args) {
    	//getMethod(null);
	}
	    
}
