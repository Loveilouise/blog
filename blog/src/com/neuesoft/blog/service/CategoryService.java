package com.neuesoft.blog.service;

import com.neuesoft.blog.common.Pagion;
import com.neuesoft.blog.common.Resource;
import com.neuesoft.blog.common.Result;
import com.neuesoft.blog.dao.CategoryDao;

public class CategoryService {
   private  CategoryDao  cateDao=new  CategoryDao();
   
   public   Result   backCategoriesByPagion(String currentPage){
	    Result  rs=new Result();
	    try {
	    	
			 rs.setData(cateDao.queryAll(currentPage));
			 rs.setCode(Resource.SUCCESS);
			 Pagion  page=new Pagion();
			 page.setCurrentPage(currentPage);
			 page.setPageTotal(cateDao.queryPageTotal());
			 rs.setPage(page);
			 rs.setMsg("返回分类列表成功");
		} catch (Exception e) {
			rs.setCode(Resource.ERROR);
			rs.setMsg("返回分类列表失败");
			e.printStackTrace();
		}
	    return  rs;
   }
   
   
   public   Result   removeById(String cid){
	   Result  rs=new Result();
	   try{
		  if(cateDao.judgeById(cid)==false){ 
			cateDao.deleById(cid);
	      rs.setCode(Resource.SUCCESS);
	      rs.setMsg("删除成功");
		  }
		  else{
			  rs.setCode(Resource.USER_ERROR);
		      rs.setMsg("有文章选择了此分类，不能删除");
		  }
	   }catch(Exception e){
		   e.printStackTrace();
		   rs.setCode(Resource.ERROR);
		      rs.setMsg("删除失败");
	   }
	    return  rs;
   }
      
   
   public Result   backById(String cid){
	   Result  rs=new  Result();
	   try {  

		rs.setData(cateDao.queryById(cid));
		rs.setCode(Resource.SUCCESS);
		rs.setMsg("返回分类信息成功");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		rs.setCode(Resource.ERROR);
		rs.setMsg("返回分类信息失败");
	} 
	  return  rs;
   }
   
   public   Result   updateById(String cid,String name,String description){
	   Result  rs=new Result();
	   try{
	      cateDao.upById(cid,name,description);
	      rs.setData(cateDao.queryById(cid));
	      rs.setCode(Resource.SUCCESS);
	      rs.setMsg("更新成功");
	   }catch(Exception e){
		   e.printStackTrace();
		   rs.setCode(Resource.ERROR);
		      rs.setMsg("更新失败");
	   }
	    return  rs;
   }
   
   public   Result   addCate(String cname){
	   Result  rs=new Result();
	   try{
		   if(cname!="") {
	      cateDao.addC(cname);
	      rs.setCode(Resource.SUCCESS);
	      rs.setMsg("更新成功");
		   }else {
			   rs.setCode(Resource.USER_ERROR);
			      rs.setMsg("分类名称不能为空");
		   }
	   }catch(Exception e){
		   e.printStackTrace();
		   rs.setCode(Resource.ERROR);
		      rs.setMsg("更新失败");
	   }
	    return  rs;
   }
   
}
