package com.neuesoft.blog.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.neuesoft.blog.common.JDBCTool;
import com.neuesoft.blog.common.Resource;
import com.neuesoft.blog.pojo.Category;
import com.neuesoft.blog.pojo.Comment;
import com.neuesoft.blog.pojo.Index;

public class CategoryDao {

	public  ArrayList<Category>   queryAll(String  currentPage) throws  Exception{
		int p=Integer.parseInt(currentPage);
	    //limit   a,b
		//b   对应数字五   a    1  0   2 5  3 10  4  15
		String  sql="select  * from   category   limit  "+(p-1)*Resource.PAGETOTAL+","+Resource.PAGETOTAL ;
	    ResultSet  rs=JDBCTool.executeQuery(sql);
	    ArrayList<Category> list=new  ArrayList<Category>();
	    Category  obj=null;
	    while(rs.next()){
	    	obj=new Category();
	    	obj.setCid(rs.getInt("cid"));
	    	obj.setName(rs.getString("name"));
	    	obj.setDescription(rs.getString("description"));
	    	list.add(obj);
	    }
	    return  list;
	}
	
	public    String  queryPageTotal() throws  Exception{
		String  sql="select  count(*) as c from   category";
		ResultSet rs=JDBCTool.executeQuery(sql);
		int total=0;
		if(rs.next()){
			total=rs.getInt("c");
		}
		int result=total/Resource.PAGETOTAL;
		if(total%Resource.PAGETOTAL>0){
			result=result+1;
		}
		return result+"";
		
	}
	
	public boolean judgeById(String cid) throws Exception{
		String sql="select aid from category_article where cid="+cid;
		ResultSet rs=JDBCTool.executeQuery(sql);
		if(rs.next()){//有文章选择了此分类，就不删
			return true;
		}else{
			return false;
		}
	}
	
	public  void  deleById(String cid){
			String  sql="delete  from  category  where  cid="+cid;
			JDBCTool.execute(sql);
	}
	
	
	public  Category  queryById(String cid) throws Exception{
		String  sql="select  * from  category  where  cid="+cid;
		ResultSet  rs=JDBCTool.executeQuery(sql);
		Category  obj=null;
		if(rs.next()){
			obj=new Category();
	    	obj.setCid(rs.getInt("cid"));
	    	obj.setName(rs.getString("name"));
	    	obj.setDescription(rs.getString("description"));
		}
		return obj;
	}
	 
	public  void  upById(String cid,String name,String description) throws Exception{
		String  sql="update category set name='"+name+"',description='"+description+"' where  cid="+cid;
		JDBCTool.execute(sql);
	
	}
	
	public  void  addC(String cname) throws Exception{
		String sql2="ALTER TABLE category MODIFY name VARCHAR(512) NOT NULL";
		JDBCTool.execute(sql2);
		String sql1="alter table category AUTO_INCREMENT=1";
		JDBCTool.execute(sql1);
		String  sql="INSERT INTO category(name,description) VALUES ('"+cname+"','')";
		JDBCTool.execute(sql);
	
	}
	
}
