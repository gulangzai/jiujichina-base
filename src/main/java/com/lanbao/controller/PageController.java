/*
 * PageController.java  v1.00  2014-4-26
 * Peoject	wndjssg
 * Copyright (c) 2014 Wisdragon
 *
 * Filename	:	PageController.java  v1.00 2014-4-26
 * Project	: 	wndjssg
 * Copyight	:	Copyright (c) 2014 Wisdragon
 */
package com.lanbao.controller;

import java.io.File;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
 

/**
 * 页面跳转.<br>
 * 
 * 屏蔽Url 统一以管理.
 * 
 * @author <a href="mailto:genliang.jiang@wisdragon.com">蒋根亮</a>
 * @version v1.00
 * @since 1.00 2014-4-26
 * 
 */
@Controller 
@RequestMapping("/pageController")
public class PageController {

	
 
	@RequestMapping(value = "/{bse}/{filename:[\\w.\\-]+}")
	public ModelAndView pageview_auth(HttpServletRequest request, @PathVariable String bse,
			@PathVariable String filename,ModelMap modeDriven) {
		ModelAndView mv =  new ModelAndView(); 
		String fileName = "index"; 
			try { 
				filename = filename.substring(0,filename.indexOf("."));
				fileName =  bse  + File.separator + filename;
				mv.setViewName(fileName);
			} catch (Exception e) {	
				e.printStackTrace();
			} 
		return mv;
	}  
	
	@RequestMapping(value = "/{bse}/{func}/{filename:[\\w.\\-]+}")
	public ModelAndView pageview_authSecond(HttpServletRequest request, @PathVariable String bse,@PathVariable String func,
			@PathVariable String filename,ModelMap modeDriven) {
		ModelAndView mv =  new ModelAndView(); 
		String fileName = "index"; 
			try { 
				filename = filename.substring(0,filename.indexOf("."));
				fileName =  bse  + File.separator +func+File.separator+ filename;
				mv.setViewName(fileName);
			} catch (Exception e) {	
				e.printStackTrace();
			} 
		return mv;
	}
	 
}
