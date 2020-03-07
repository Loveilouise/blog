package com.neuesoft.blog.dao;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.neuesoft.blog.common.JDBCTool;
import com.neuesoft.blog.pojo.Post;
import com.neuesoft.blog.common.Resource;
import com.neuesoft.blog.pojo.Article;
import com.neuesoft.blog.pojo.Category;
import com.neuesoft.blog.pojo.Comment;

public class PostDao {
	
	public ArrayList<Comment> queryAllById(String aid,String currentPage) throws Exception{
		int p=Integer.parseInt(currentPage);
		String sql="select * from comment where aid="+aid+" limit "+(p-1)*Resource.PAGETOTAL+","+Resource.PAGETOTAL ;
		ResultSet rs=JDBCTool.executeQuery(sql);
		ArrayList<Comment> list=new ArrayList<Comment>();
		Comment obj=null;
		while(rs.next()) {
			obj=new Comment();
			if(rs.getString("status").equals("1")) {
			obj.setCid(rs.getInt("cid"));
	    	obj.setAuthor(rs.getString("author"));
	    	obj.setContent(rs.getString("content"));
	    	System.out.println(obj.getContent());
	    	obj.setCreated(rs.getString("created"));
	    	obj.setIp(rs.getString("ip"));
	    	obj.setStatus(rs.getString("status"));
	    	obj.setAid(rs.getInt("aid"));
			list.add(obj);
			}
		}
		return list;
	}
	
	public String queryPageTotal() throws Exception {
		String sql="select  count(*) as c from comment";
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

	public Post queryById(String aid) throws Exception {
		String sql1="select * from article where aid="+aid;
		Post post=new Post();
		ResultSet rs=JDBCTool.executeQuery(sql1);
		Article article=null;
		if(rs.next()) {
			article=new Article();
			article.setAid(rs.getInt("aid"));
			article.setTitle(rs.getString("title"));
			article.setCreated(rs.getString("created"));
			article.setAllow_comments(rs.getString("allow_comments"));
			article.setContent(rs.getString("content"));
			System.out.println(article.getTitle());
		}
		post.setArticle(article);
		
		String sql2="select * from category,category_article where category.cid=category_article.cid and category_article.aid="+aid;
		ResultSet  rs2=JDBCTool.executeQuery(sql2);
		ArrayList<Category> categorylist=new  ArrayList<Category>();
		Category category = null;
		 while(rs2.next()){
			 category=new Category();
			 //category.setDescription(rs2.getString("description"));
			 //category.setCid(rs2.getInt("cid"));
			 category.setName(rs2.getString("name"));
			 System.out.println(category.getName());
			 categorylist.add(category);
			 
		 }
		 post.setCategorylist(categorylist);	
	  return post;
		  
	}
	
	public void intCommentByaid(String aid,String content,String author) throws UnknownHostException 
	{
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");

		 String created=sdf.format(new Date());
		 InetAddress IP = InetAddress.getByName("DESKTOP-2CV13KH");  
		 String ip=(""+IP).substring(16);
		 String sql1="alter table comment AUTO_INCREMENT=1";
			JDBCTool.execute(sql1);
		 String sql = "insert into comment (aid,content,author,ip,created) values ('"+aid+"','"+content
				+"','"+author+"','"+ip+"','"+created+"')";
		 JDBCTool.execute(sql);
	}

	
	
}
