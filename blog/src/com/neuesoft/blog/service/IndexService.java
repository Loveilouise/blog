package com.neuesoft.blog.service;

import java.sql.SQLException;

import  com.neuesoft.blog.common.Resource;
import  com.neuesoft.blog.common.Result;
import  com.neuesoft.blog.dao.IndexDao;

public class IndexService {

	IndexDao indexDao = new IndexDao();
	
	public Result getArticleCount() {		 
		//创建结果集
		Result rs = new Result();
		
		//设置数据为 总的文章数
		try {			
			//设置结果集属性
			rs.setData(indexDao.getArticleCount());
			//System.out.println(indexDao.getArticleCount().getAlist().get(1).getTitle()+indexDao.getArticleCount().getClist().get(1).getAuthor());
			rs.setCode(Resource.SUCCESS);
			rs.setMsg("查询成功");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			rs.setCode(Resource.ERROR);
			rs.setMsg("查询失败");
			e.printStackTrace();
		}
		
		return rs;
	}
	
	
}
