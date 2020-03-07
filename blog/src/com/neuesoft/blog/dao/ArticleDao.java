package com.neuesoft.blog.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.neuesoft.blog.common.JDBCTool;
import com.neuesoft.blog.common.Resource;
import com.neuesoft.blog.pojo.Article;
import com.neuesoft.blog.pojo.Category;
import com.neuesoft.blog.pojo.Category_article;
import com.neuesoft.blog.pojo.Post;

public class ArticleDao {
	public ArrayList<Category> querynameBycId() throws Exception{
		String  sql="select * from  category";
		ResultSet  rs=JDBCTool.executeQuery(sql);
		ArrayList<Category> list=new  ArrayList<Category>();
		Category  obj=null;
		while(rs.next()){
			obj=new Category();
	    	obj.setCid(rs.getInt("cid"));
	    	obj.setName(rs.getString("name"));
	    	list.add(obj);
		}
		return list;
	}
	
	//发布文章
	public  void  addAtricle(String aid,Article article,String[] catelist) throws Exception {
		String allow_comments=article.getAllow_comments();
		String content=article.getContent();
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String created=sdf.format(new Date());
		String intro=article.getIntro();
		String title=article.getTitle();
		String status=article.getStatus();
		String sql1="alter table article AUTO_INCREMENT=1";
		JDBCTool.execute(sql1);
		int aid1=Integer.parseInt(aid);
		if(aid1==-1) {
			String sql= "insert into article (title,content,intro,status,allow_comments,created) values ('"+title+"','"+content
					+"','"+intro+"','"+status+"','"+allow_comments+"','"+created+"')";
			JDBCTool.execute(sql);
		}else {
			SimpleDateFormat sdf1= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String modified=sdf1.format(new Date());
			String sql11= "update article set title='"+title+"',intro='"+intro+"',content='"+content+"',status='"+status+"',allow_comments='"+allow_comments+"',modified='"+modified+"' where  aid="+aid1;
			int cid=0;
			String sql="delete  from category_article where aid="+aid;
			JDBCTool.execute(sql);
			for (int i=0;i<catelist.length;i++) {
				cid=Integer.parseInt(catelist[i]);
				String sql4 = "insert into category_article (cid,aid) values ('"+cid+"','"+aid+"')";
				JDBCTool.execute(sql4);		
			}
			JDBCTool.execute(sql11);
		}
		
	}
	//编辑文章时获取内容
	public 	Post queryByaId(String aid) throws Exception{
		String  sql="select * from  article  where aid="+aid ;
		Post post=new Post();
		ResultSet  rs=JDBCTool.executeQuery(sql);
		Article  obj=null;
		if(rs.next()){
			obj=new Article();
	    	obj.setAid(rs.getInt("aid"));
	    	obj.setTitle(rs.getString("title"));
	    	obj.setIntro(rs.getString("intro"));
	    	obj.setContent(rs.getString("content"));
	    	obj.setAllow_comments(rs.getString("allow_comments"));
		}
		post.setArticle(obj);
		String sql2="select * from category,category_article where category.cid=category_article.cid and category_article.aid="+aid;
		ResultSet  rs2=JDBCTool.executeQuery(sql2);
		ArrayList<Category> categorylist=new  ArrayList<Category>();
		Category category = null;
		 while(rs2.next()){
			 category=new Category();
			 //category.setDescription(rs2.getString("description"));
			 category.setCid(rs2.getInt("cid"));
			 category.setName(rs2.getString("name"));
			 System.out.println(category.getName());
			 categorylist.add(category);
			 
		 }
		 post.setCategorylist(categorylist);	
		 return post;
	}
	public void insertName(String title,String[] category) throws Exception
	{
		String sql2 = "select aid from article where title = '"+title+"'";
		ResultSet rs=JDBCTool.executeQuery(sql2);
		int aid=0;
		try {
			if(rs.next()){
				aid=rs.getInt("aid");
				System.out.println("aaaaaaa"+aid);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int cid=0;
		for(int i=0;i<category.length;i++) {
			cid=Integer.parseInt(category[i]);
			//String sql="select aid from category_article where aid"+aid+" and cid= "+cid;
			String sql4 = "insert into category_article (cid,aid) values ('"+cid+"','"+aid+"')";
			JDBCTool.execute(sql4);
			System.out.println(cid+"打印");
		}
		
	}
	
	public  ArrayList<Category>  UnSelect(String aid) throws Exception {
		String sql= "select * from category where  cid not in(select cid from category_article where aid="+aid+" )";
		ResultSet rs=JDBCTool.executeQuery(sql);
		ArrayList<Category> list=new ArrayList<Category>();
		Category obj=null;
		 while(rs.next()){
			 obj=new Category();
			 //category.setDescription(rs2.getString("description"));
			 //category.setCid(rs2.getInt("cid"));
			 obj.setCid(rs.getInt("cid"));
			 obj.setName(rs.getString("name"));
			 System.out.println(obj.getName());
			 list.add(obj);
			 
		 }
		 return list;
	}
	
	
	public ArrayList<Article> selectAllArticle(String currentPage) throws SQLException{
			int p = Integer.parseInt(currentPage);
			ArrayList<Article> articles = new ArrayList<Article>();
			String sql = "select * from article limit "+(p-1)*Resource.PAGETOTAL+","+Resource.PAGETOTAL;
			//System.out.println(sql);
			ResultSet rs = JDBCTool.executeQuery(sql);
			while(rs.next()){
				Article article = new Article();
				if(rs.getString("status").equals("1")) {
				article.setAid(rs.getInt("aid"));
				article.setAllow_comments(rs.getString("allow_comments"));
				article.setContent(rs.getString("content"));
				article.setCreated(rs.getString("created"));
				article.setHits(rs.getInt("hits"));
				article.setIntro(rs.getString("intro"));
				article.setModified(rs.getString("modified"));
				article.setStatus(rs.getString("status"));
				article.setTitle(rs.getString("title"));
				articles.add(article);
				}
			}
			return articles;
	}
	public String getTotalPage() throws SQLException{
		String sql = "select count(*) as c from article";
		//System.out.println(sql);
		ResultSet rs = JDBCTool.executeQuery(sql);
		int total = 0;
		if(rs.next()){
			total=rs.getInt("c");
			//System.out.println("total:"+total);
		}
		int result = total/Resource.PAGETOTAL;
		if(total%Resource.PAGETOTAL > 0){
			result++;
		}
		return result+"";
	}
}
