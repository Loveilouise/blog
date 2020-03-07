package com.neuesoft.blog.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import  com.neuesoft.blog.common.JDBCTool;
import  com.neuesoft.blog.pojo.Article;
import  com.neuesoft.blog.pojo.Comment;
import  com.neuesoft.blog.pojo.Index;

public class IndexDao {
	
	/**
	 * 查询数据库中全部的文章数
	 * @return
	 * @throws SQLException
	 */
	public Index getArticleCount() throws SQLException {	
		//编写查询的SQL
		String sql = "select count(*) as a from article where status=1";
		String sql2="select count(*) as c from comment where status=1";
		String sql3="select count(*) as f from fl";
		String sql4 = "select * from comment where status=1";
		String sql5 = "select * from article where status=1";
 		
		//执行文章数量查询语句
		ResultSet rs = JDBCTool.executeQuery(sql);
		
		Index index = new Index();
		if(rs.next()) {			
			index.setAcounts(rs.getInt("a")+"");			 
		}
		
		//评论数量查询
		ResultSet rs2 = JDBCTool.executeQuery(sql2);		 
		if(rs2.next()) {			
			index.setCcounts(rs2.getInt("c")+"");			 
		}
		
		//附件数量查询
		ResultSet rs3 = JDBCTool.executeQuery(sql3);		 
		if(rs3.next()) {			
			index.setFcounts(rs3.getInt("f")+"");			 
		}
		
		
		//获取数据库文章表中数据
		ResultSet  rs4=JDBCTool.executeQuery(sql4);		 
		 	ArrayList<Comment> list=new  ArrayList<Comment>();
		while(rs4.next())
		{
			Comment  obj=new Comment();
	    	obj.setAuthor(rs4.getString("author"));
	    	obj.setCreated(rs4.getString("created"));
	    	obj.setContent(rs4.getString("content"));
	    	list.add(obj);
		}
		
		index.setClist(list);
		
		
		//获取数据库评论表中数据
		ResultSet  rs5=JDBCTool.executeQuery(sql5);		 
		 	ArrayList<Article> list2 = new  ArrayList<Article>();
		while(rs5.next())
		{
			 Article  obj2 =new Article();
			 obj2.setAid(rs5.getInt("aid"));
	    	obj2.setTitle(rs5.getString("title"));
	    	list2.add(obj2);
		}
		
		index.setAlist(list2);
		
		return index;
	}
	 
}
