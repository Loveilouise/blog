package com.neuesoft.blog.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.neuesoft.blog.common.JDBCTool;
import com.neuesoft.blog.common.Resource;
import com.neuesoft.blog.pojo.Article;

public class ArchivesDao {

	public ArrayList<Article> queryAll() throws Exception{
		String sql="select * from article ";
		ResultSet rs=JDBCTool.executeQuery(sql);
		ArrayList<Article> list=new ArrayList<Article>();
		Article obj=null;
		while(rs.next()) {
			obj=new Article();
			obj.setAid(rs.getInt("aid"));
			obj.setTitle(rs.getString("title"));
			obj.setCreated(rs.getString("created"));
			obj.setHits(rs.getInt("hits"));
			obj.setContent(rs.getString("content"));
			obj.setIntro(rs.getString("intro"));
			obj.setStatus(rs.getString("status"));
			obj.setAllow_comments(rs.getString("allow_comments"));
			obj.setModified(rs.getString("modified"));	
			list.add(obj);
		}
		return list;
	}
	
}
