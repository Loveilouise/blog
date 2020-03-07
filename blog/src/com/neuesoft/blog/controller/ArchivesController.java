package com.neuesoft.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuesoft.blog.annotation.Controller;
import com.neuesoft.blog.annotation.RequestMapping;
import com.neuesoft.blog.common.JsonTool;
import com.neuesoft.blog.common.WriterTool;

@Controller
public class ArchivesController {

	@RequestMapping("/archives.do")
	public void  getCategoryByCId(HttpServletRequest req,HttpServletResponse resp){
		//WriterTool.write(resp, JsonTool.turnToJson(arService.backcateByCId()));
	}
	
}
