package com.neuesoft.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import  com.neuesoft.blog.annotation.Controller;
import  com.neuesoft.blog.annotation.RequestMapping;
import  com.neuesoft.blog.common.JsonTool;
import  com.neuesoft.blog.common.WriterTool;
import  com.neuesoft.blog.service.IndexService;

@Controller
public class IndexController {
	
	IndexService indexService = new IndexService();
	@RequestMapping("/dashbord.do")
	public void getCount(HttpServletRequest req,HttpServletResponse resp) { 		 
		WriterTool.write(resp, JsonTool.turnToJson(indexService.getArticleCount()));
	}

}

	 
	
	
	

