package com.neuesoft.blog.service;

import com.neuesoft.blog.common.Pagion;
import com.neuesoft.blog.common.Resource;
import com.neuesoft.blog.common.Result;
import com.neuesoft.blog.dao.CommentDao;

public class CommentService {
	private CommentDao commentDao=new CommentDao();
	
	public   Result   backCommentsByPagion(String currentPage){
	    Result  rs=new Result();
	    try {
			 rs.setData(commentDao.queryAll(currentPage));
			 rs.setCode(Resource.SUCCESS);
			 Pagion  page=new Pagion();
			 page.setCurrentPage(currentPage);
			 page.setPageTotal(commentDao.queryPageTotal());
			 rs.setPage(page);
			 rs.setMsg("返回评论列表成功");
		} catch (Exception e) {
			rs.setCode(Resource.ERROR);
			rs.setMsg("返回评论列表失败");
			e.printStackTrace();
		}
	    return  rs;
   }
	
	public   Result   removeById(String cid){
		   Result  rs=new Result();
		   try{
		      commentDao.deleById(cid);
		      rs.setCode(Resource.SUCCESS);
		      rs.setMsg("删除成功");
		   }catch(Exception e){
			   e.printStackTrace();
			   rs.setCode(Resource.ERROR);
			   rs.setMsg("删除失败");
		   }
		    return  rs;
	   }
	
	public Result updateById(String cid,String status) {
		Result  rs=new Result();
		try {
			commentDao.updateById(cid, status);
			rs.setCode(Resource.SUCCESS);
			rs.setMsg("更新成功");
		}catch(Exception e) {
			e.printStackTrace();
			rs.setCode(Resource.ERROR);
			rs.setMsg("更新失败");
		}
		return rs;
		
	}

}
