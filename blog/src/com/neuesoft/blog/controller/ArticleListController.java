package com.neuesoft.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuesoft.blog.annotation.Controller;
import com.neuesoft.blog.annotation.RequestMapping;
import com.neuesoft.blog.common.JsonTool;
import com.neuesoft.blog.common.WriterTool;
import com.neuesoft.blog.service.ArticleListService;

@Controller
public class ArticleListController {

	private ArticleListService articleListService=new ArticleListService();
	
	@RequestMapping("/articlelist.do")
	public void getArticleList(HttpServletRequest req,HttpServletResponse resp){
		String currentPage=req.getParameter("currentPage");
		WriterTool.write(resp, JsonTool.turnToJson(articleListService.backArticlesByPagion(currentPage)));
	}
	
	@RequestMapping("/deleteByAid.do")
	public void deleteArticle(HttpServletRequest req,HttpServletResponse resp) {
		String aid=req.getParameter("aid");
		WriterTool.write(resp, JsonTool.turnToJson(articleListService.removeById(aid)));
	}
	
	@RequestMapping("/getCategoryByAId.do")
	public void showCategoryModel(HttpServletRequest req,HttpServletResponse resp) {
		String aid=req.getParameter("aid");
		WriterTool.write(resp,JsonTool.turnToJson(articleListService.backById(aid)));
	}
	
	
	
	
}
