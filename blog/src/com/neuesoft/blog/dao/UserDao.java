package com.neuesoft.blog.dao;

import java.sql.ResultSet;

import com.neuesoft.blog.common.JDBCTool;
import com.neuesoft.blog.pojo.User;

public class UserDao {
   public  User  queryByUserName(String username) throws Exception{
	   ResultSet rs=JDBCTool.executeQuery("select  * from user  where  username='"+username+"'");
	   User user=null;
	   if(rs.next()){
		   user=new User();
		   user.setUsername(rs.getString("username"));
		   user.setPassword(rs.getString("password"));
		   user.setUid(rs.getInt("uid"));
	   }
	   return user;
   }
}
