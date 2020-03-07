package com.neuesoft.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuesoft.blog.annotation.Controller;
import com.neuesoft.blog.annotation.RequestMapping;
import com.neuesoft.blog.common.JsonTool;
import com.neuesoft.blog.common.WriterTool;
import com.neuesoft.blog.service.CategoryService;

@Controller
public class CategoryController {
	private  CategoryService  cateService=new  CategoryService();
	@RequestMapping("/getCategoryList.do")
	public  void  getCategoryList (HttpServletRequest req,HttpServletResponse resp){
		String  currentPage=req.getParameter("currentPage");
		WriterTool.write(resp, JsonTool.turnToJson(cateService.backCategoriesByPagion(currentPage)));
	}
	
	@RequestMapping("/deleteCategory.do")
	public void   deleteCategory(HttpServletRequest req,HttpServletResponse resp){
		String  cid=req.getParameter("cid");
		WriterTool.write(resp, JsonTool.turnToJson(cateService.removeById(cid)));
	}
	@RequestMapping("/showCategory.do")
	public void  showCategory(HttpServletRequest req,HttpServletResponse resp){
		String cid=req.getParameter("cid");
		 WriterTool.write(resp, JsonTool.turnToJson(cateService.backById(cid)));
	}
	
	@RequestMapping("/updateCategory.do")
	public void  updateCategory(HttpServletRequest req,HttpServletResponse resp){
		String cid=req.getParameter("cid");
		String name=req.getParameter("name");
		String description=req.getParameter("description");
		 WriterTool.write(resp, JsonTool.turnToJson(cateService.updateById(cid,name,description)));
	}
	
	@RequestMapping("/addCategory.do")
	public void  addCategory(HttpServletRequest req,HttpServletResponse resp){
		String cname=req.getParameter("cname");
		 WriterTool.write(resp, JsonTool.turnToJson(cateService.addCate(cname)));
	}
	
}
