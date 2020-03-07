package com.neuesoft.blog.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.neuesoft.blog.common.JDBCTool;
import com.neuesoft.blog.common.Resource;
import com.neuesoft.blog.pojo.Article;
import com.neuesoft.blog.pojo.Category;

public class ArticleListDao {

	public ArrayList<Article> queryAll(String currentPage) throws Exception{
		int p=Integer.parseInt(currentPage);
		String sql="select * from article limit "+(p-1)*Resource.PAGETOTAL+","+Resource.PAGETOTAL ;
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
	
	public String queryPageTotal() throws Exception {
		String sql="select  count(*) as c from article";
		ResultSet rs=JDBCTool.executeQuery(sql);
		int total=0;
		if(rs.next()) {
			total=rs.getInt("c");
		}
		int result=total/Resource.PAGETOTAL;
		if(total%Resource.PAGETOTAL>0) {
			result=result+1;
		}
		return result+"";
	}
	
	public void deleById(String aid) {
		String sql="delete  from  article  where  aid="+aid;
		JDBCTool.execute(sql);
	}
	
	public ArrayList<Category> queryById(String aid) throws Exception {
		String sql="select * from category,category_article where category.cid=category_article.cid and category_article.aid="+aid;
		//String sql="select * from article where aid="+aid;
		ResultSet rs=JDBCTool.executeQuery(sql);
		ArrayList<Category> list=new  ArrayList<Category>();
		Category obj=null;
		while(rs.next()) {
			obj=new Category();
			obj.setName(rs.getString("name"));
			list.add(obj);
		}
		return list;
	}
	
	
}
