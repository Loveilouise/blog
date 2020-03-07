package com.neuesoft.blog.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.neuesoft.blog.common.JDBCTool;
import com.neuesoft.blog.common.Resource;
import com.neuesoft.blog.pojo.Comment;

public class CommentDao {
	public ArrayList<Comment> queryAll(String currentPage) throws Exception{
		int p=Integer.parseInt(currentPage);
		ArrayList<Comment> list=new ArrayList<Comment>();
		String  sql="select  * from   comment   limit  "+(p-1)*Resource.PAGETOTAL+","+Resource.PAGETOTAL ;
	    ResultSet  rs=JDBCTool.executeQuery(sql);
	    Comment obj=null;
	    while(rs.next()) {
	    	obj=new Comment();
	    	obj.setCid(rs.getInt("cid"));
	    	obj.setAuthor(rs.getString("author"));
	    	obj.setContent(rs.getString("content"));
	    	obj.setCreated(rs.getString("created"));
	    	obj.setIp(rs.getString("ip"));
	    	obj.setStatus(rs.getString("status"));
	    	obj.setAid(rs.getInt("aid"));
	    	list.add(obj);
	    }
		return list;
	}
	
	public    String  queryPageTotal() throws  Exception{
		String  sql="select  count(*) as c from   comment";
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
	
	public  void  deleById(String cid){
		String  sql="delete  from  comment  where  cid="+cid;
		JDBCTool.execute(sql);
	}
	
	public void updateById(String cid,String status) {
		if(status=="0") {//未审核
			status="1";//通过
		}
		else if(status=="1"){//通过
			status="2";//拒绝
		}else if(status=="2"){
			status="1";
		}
		System.out.println(status);
		String sql1="alter table comment AUTO_INCREMENT=1";
		JDBCTool.execute(sql1);
		String  sql="update  comment set status='"+status+"'"+"where  cid="+cid;
		JDBCTool.execute(sql);
	}

}
