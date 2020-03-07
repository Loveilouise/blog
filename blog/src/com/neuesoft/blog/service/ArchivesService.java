package com.neuesoft.blog.service;

import com.neuesoft.blog.common.Pagion;
import com.neuesoft.blog.common.Resource;
import com.neuesoft.blog.common.Result;
import com.neuesoft.blog.dao.ArchivesDao;

public class ArchivesService {
	
	 ArchivesDao archivesDao=new ArchivesDao();
	 
	public Result backArticlesByData() {
		Result rs=new Result();
		try {
			rs.setData(archivesDao.queryAll());
			rs.setCode(Resource.SUCCESS);
			rs.setMsg("返回列表成功");
		} catch (Exception e) {
			rs.setCode(Resource.ERROR);
			rs.setMsg("返回分类列表失败");
			e.printStackTrace();
		}
		return rs;	
	}
}
