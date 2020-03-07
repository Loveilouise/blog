package com.neuesoft.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuesoft.blog.annotation.Controller;
import com.neuesoft.blog.annotation.RequestMapping;
import com.neuesoft.blog.common.JsonTool;
import com.neuesoft.blog.common.WriterTool;
import com.neuesoft.blog.pojo.Article;
import com.neuesoft.blog.pojo.Category;
import com.neuesoft.blog.service.ArticleService;
@Controller
public class ArticleController {
	private  ArticleService  arService=new  ArticleService();
	Article article = new Article();
	Category category = new Category();
	//发布文章时获取分类便签
	@RequestMapping("/articleCategory.do")
	public void  getCategoryByCId(HttpServletRequest req,HttpServletResponse resp){
		WriterTool.write(resp, JsonTool.turnToJson(arService.backcateByCId()));
	}
	
	//发布文章
	@RequestMapping("/saveArticle.do")
	public void  saveArticle(HttpServletRequest req,HttpServletResponse resp){
		String aid = req.getParameter("aid");
		String title=req.getParameter("title");
		String intro=req.getParameter("intro");
		String content=req.getParameter("content");
		String status=req.getParameter("status");
		String allow_comments=req.getParameter("allow_comments");
		String modified=req.getParameter("modified");
		String created=req.getParameter("created");
		String category = req.getParameter("category");
		String[] catelist = category.split(",");
		System.out.println(aid);
		//article.setAid(aid);
		//article.setCategory(category);
		article.setContent(content);
		article.setIntro(intro);
		article.setTitle(title);
		article.setStatus(status);
		article.setAllow_comments(allow_comments);
		article.setModified(modified);
		article.setCreated(created);
		WriterTool.write(resp, JsonTool.turnToJson(arService.addAtricle(aid,article,catelist)));
	}
	
	//编辑文章时获取内容
	@RequestMapping("/getArticleById.do")
	public void  getArticleById(HttpServletRequest req,HttpServletResponse resp){
		String aid=req.getParameter("aid");
		WriterTool.write(resp, JsonTool.turnToJson(arService.backByaId(aid)));
	}
	
	@RequestMapping("/saveCate.do")
    public void saveCate(HttpServletRequest req,HttpServletResponse resp)
    {
 	   String title = req.getParameter("title");
 	   String name= req.getParameter("category");
 	   String[] category = name.split(",");
 	   WriterTool.write(resp, JsonTool.turnToJson(arService.addCate(title,category)));  
    }
	
	@RequestMapping("/getUnSelectCategory.do")
    public void getUnSelectCategory(HttpServletRequest req,HttpServletResponse resp)
    {
		String aid=req.getParameter("aid");
 	    WriterTool.write(resp, JsonTool.turnToJson(arService.UnSelectCategory(aid)));
    }
	
	
	@RequestMapping("/index.do")
	public void getArticleList(HttpServletRequest req,HttpServletResponse resp){
		String currentPage = req.getParameter("currentPage");
		WriterTool.write(resp, JsonTool.turnToJson(arService.getArticleList(currentPage)));
	}
	
}
