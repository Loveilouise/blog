package com.neuesoft.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuesoft.blog.annotation.Controller;
import com.neuesoft.blog.annotation.RequestMapping;
import com.neuesoft.blog.common.JsonTool;
import com.neuesoft.blog.common.WriterTool;
import com.neuesoft.blog.service.CommentService;

@Controller
public class CommentController {
	private CommentService commentService=new CommentService();
	
	@RequestMapping("/getCommentList.do")
	public  void  getCategoryList (HttpServletRequest req,HttpServletResponse resp){
		String  currentPage=req.getParameter("currentPage");
		WriterTool.write(resp, JsonTool.turnToJson(commentService.backCommentsByPagion(currentPage)));
	}
	
	@RequestMapping("/updateStatus.do")
	public void updateComment(HttpServletRequest req,HttpServletResponse resp) {
		String cid=req.getParameter("cid");
		String status=req.getParameter("status");
		WriterTool.write(resp, JsonTool.turnToJson(commentService.updateById(cid, status)));
	}
	
	@RequestMapping("/deleteComment.do")
	public void deleteComment(HttpServletRequest req,HttpServletResponse resp) {
		String cid=req.getParameter("cid");
		WriterTool.write(resp, JsonTool.turnToJson(commentService.removeById(cid)));
	}
}
