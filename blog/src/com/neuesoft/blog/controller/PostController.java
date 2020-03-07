package com.neuesoft.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuesoft.blog.annotation.Controller;
import com.neuesoft.blog.annotation.RequestMapping;
import com.neuesoft.blog.common.JsonTool;
import com.neuesoft.blog.common.WriterTool;
import com.neuesoft.blog.service.PostService;

@Controller
public class PostController {

	PostService postService=new PostService();
	
	
	@RequestMapping("/bacc.do")
	public void showArticle(HttpServletRequest req,HttpServletResponse resp) {
		String aid=req.getParameter("aid");
		System.out.println("bbb");
		WriterTool.write(resp,JsonTool.turnToJson(postService.showById(aid)));
	}
	
	@RequestMapping("/subComment.do")
	public void subComment(HttpServletRequest req,HttpServletResponse resp)
	{
		String aid = req.getParameter("aid");
		String content = req.getParameter("content");
		String author = req.getParameter("author");
		WriterTool.write(resp, JsonTool.turnToJson(postService.insertComment(aid,content,author)));
		
	}
	
	@RequestMapping("/commentlist.do")
	public void getCommentList(HttpServletRequest req,HttpServletResponse resp) {
		String aid=req.getParameter("aid");
		String currentPage=req.getParameter("currentPage");
		WriterTool.write(resp,JsonTool.turnToJson(postService.backCommentsByPagion(aid,currentPage)));
	}
	
}
