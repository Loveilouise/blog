package com.neuesoft.blog.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class JDBCTool {
	private static   Connection conn;
	private  static  final String  DRIVER="com.mysql.jdbc.Driver";
	private  static  final  String  URL="jdbc:mysql://localhost:3306/blog";
    private   static  final  String  USER_NAME="root";
    private   static  final  String  PASSWORD="123";
     static{
    	 try{   
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }
     
     public   static Connection getConnection(){
    	 if(conn==null){
    		 try {
				conn=DriverManager.getConnection(URL,USER_NAME,PASSWORD);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	 }
    	 return conn;
     }
     
     public static void execute(String sql){
		try {
			getConnection();
	    	Statement st;
			st = conn.createStatement();
			st.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
     }
     public static ResultSet executeQuery(String sql){
    	 getConnection();
    	 ResultSet rs=null;
    	 try {
			rs=conn.createStatement().executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 return   rs;
     }
     
}
