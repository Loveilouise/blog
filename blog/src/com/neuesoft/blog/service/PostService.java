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
			rs.setMsg("�����б�ɹ�");
		} catch (Exception e) {
			rs.setCode(Resource.ERROR);
			rs.setMsg("���ط����б�ʧ��");
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
		      rs.setMsg("���۳ɹ�");
		   }catch(Exception e){
			   e.printStackTrace();
			   rs.setCode(Resource.ERROR);
			      rs.setMsg("����ʧ��");
		   }
		    return  rs;
	   }
		 
	public Result showById(String aid) {
		Result rs=new Result();
		System.out.println("aaa");
		 try {
				rs.setData(postDao.queryById(aid));
				rs.setCode(Resource.SUCCESS);
				rs.setMsg("���ط�����Ϣ�ɹ�");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				rs.setCode(Resource.ERROR);
				rs.setMsg("���ط�����Ϣʧ��");
			} 
			  return  rs;
	}
}
