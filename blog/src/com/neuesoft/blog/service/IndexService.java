package com.neuesoft.blog.service;

import java.sql.SQLException;

import  com.neuesoft.blog.common.Resource;
import  com.neuesoft.blog.common.Result;
import  com.neuesoft.blog.dao.IndexDao;

public class IndexService {

	IndexDao indexDao = new IndexDao();
	
	public Result getArticleCount() {		 
		//���������
		Result rs = new Result();
		
		//��������Ϊ �ܵ�������
		try {			
			//���ý��������
			rs.setData(indexDao.getArticleCount());
			//System.out.println(indexDao.getArticleCount().getAlist().get(1).getTitle()+indexDao.getArticleCount().getClist().get(1).getAuthor());
			rs.setCode(Resource.SUCCESS);
			rs.setMsg("��ѯ�ɹ�");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			rs.setCode(Resource.ERROR);
			rs.setMsg("��ѯʧ��");
			e.printStackTrace();
		}
		
		return rs;
	}
	
	
}
