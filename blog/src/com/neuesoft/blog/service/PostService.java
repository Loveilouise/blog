package com.neuesoft.blog.service;

import com.neuesoft.blog.common.Pagion;
import com.neuesoft.blog.common.Resource;
import com.neuesoft.blog.common.Result;
import com.neuesoft.blog.dao.PostDao;

public class PostService {
	
	private PostDao postDao=new PostDao();
	
	public Result backCommentsByPagion(String aid,String currentPage) {
		Result rs=new Result();
		try {
			rs.setData(postDao.queryAllById(aid,currentPage));
			rs.setCode(Resource.SUCCESS);
			Pagion page=new Pagion();
			page.setCurrentPage(currentPage);
			page.setPageTotal(postDao.queryPageTotal());
			rs.setPage(page);
			rs.setMsg("返回列表成功");
		} catch (Exception e) {
			rs.setCode(Resource.ERROR);
			rs.setMsg("返回分类列表失败");
			e.printStackTrace();
		}
		return rs;	
	}
	
	public Result insertComment(String aid,String content,String author)
	 {
		
		 Result  rs=new Result();
		   try{
			  postDao.intCommentByaid(aid,content,author);
		      rs.setCode(Resource.SUCCESS);
		      rs.setMsg("评论成功");
		   }catch(Exception e){
			   e.printStackTrace();
			   rs.setCode(Resource.ERROR);
			      rs.setMsg("评论失败");
		   }
		    return  rs;
	   }
		 
	public Result showById(String aid) {
		Result rs=new Result();
		System.out.println("aaa");
		 try {
				rs.setData(postDao.queryById(aid));
				rs.setCode(Resource.SUCCESS);
				rs.setMsg("返回分类信息成功");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				rs.setCode(Resource.ERROR);
				rs.setMsg("返回分类信息失败");
			} 
			  return  rs;
	}
}
