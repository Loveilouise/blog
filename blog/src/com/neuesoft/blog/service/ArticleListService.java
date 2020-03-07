package com.neuesoft.blog.service;

import com.neuesoft.blog.common.Pagion;
import com.neuesoft.blog.common.Resource;
import com.neuesoft.blog.common.Result;
import com.neuesoft.blog.dao.ArticleListDao;

public class ArticleListService {

	private ArticleListDao articleListDao=new ArticleListDao();
	
	public Result backArticlesByPagion(String currentPage) {
		Result rs=new Result();
		try {
			rs.setData(articleListDao.queryAll(currentPage));
			rs.setCode(Resource.SUCCESS);
			Pagion page=new Pagion();
			page.setCurrentPage(currentPage);
			page.setPageTotal(articleListDao.queryPageTotal());
			rs.setPage(page);
			rs.setMsg("�����б�ɹ�");
		} catch (Exception e) {
			rs.setCode(Resource.ERROR);
			rs.setMsg("���ط����б�ʧ��");
			e.printStackTrace();
		}
		return rs;	
	}
	
	public Result removeById(String aid) {
		Result rs=new Result();
		try {
			articleListDao.deleById(aid);
			rs.setCode(Resource.SUCCESS);
			rs.setMsg("ɾ���ɹ�");
		}
		catch(Exception e){
			   e.printStackTrace();
			   rs.setCode(Resource.ERROR);
			   rs.setMsg("ɾ��ʧ��");
		   }
		return rs;
	}
	
	public Result backById(String aid) {
		Result rs=new Result();
		 try {
				rs.setData(articleListDao.queryById(aid));
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
