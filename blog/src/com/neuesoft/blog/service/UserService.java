package com.neuesoft.blog.service;

import com.neuesoft.blog.common.Resource;
import com.neuesoft.blog.common.Result;
import com.neuesoft.blog.dao.UserDao;
import com.neuesoft.blog.pojo.User;

public class UserService {
	private  UserDao  userDao=new UserDao();
	
	public Result checkVerify(String answer,String verifyCode){
		Result  rs=new  Result();
    	if(answer.equals(verifyCode)){
    		rs.setMsg("验证码正确");
    		rs.setCode(Resource.SUCCESS);
        }else{
    	 rs.setMsg("验证码错误");
		 rs.setCode(Resource.USER_ERROR);
        }
    	return rs;
	}
	
	public   User  loadUserByName(String  username) throws Exception{
		  return   userDao.queryByUserName(username);
	   }
	   
	   public  Result login(String username,String password){
		   User  user=null;
		   Result  rs=new  Result();
		   try {
			user=loadUserByName(username);
			if(user.getPassword().equals(password)){
				rs.setCode(Resource.SUCCESS);
				rs.setMsg("登录成功");
			}else{
				rs.setCode(Resource.USER_ERROR);
				rs.setMsg("登录失败，用户名或密码错误");
			}
		   } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rs.setCode(Resource.ERROR);
			rs.setMsg("登录失败 ");
		   }
		   return  rs;
	   }
	   
	   
	   public  Result logout(){
		   Result  rs=new  Result();
			try {
				rs.setCode(Resource.SUCCESS);
				rs.setMsg("注销成功");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				rs.setCode(Resource.ERROR);
				rs.setMsg("注销失败");
			}
		    return  rs;
	   }
	
}
