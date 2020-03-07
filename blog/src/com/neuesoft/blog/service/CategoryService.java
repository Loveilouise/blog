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
			 rs.setMsg("���ط����б�ɹ�");
		} catch (Exception e) {
			rs.setCode(Resource.ERROR);
			rs.setMsg("���ط����б�ʧ��");
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
	      rs.setMsg("ɾ���ɹ�");
		  }
		  else{
			  rs.setCode(Resource.USER_ERROR);
		      rs.setMsg("������ѡ���˴˷��࣬����ɾ��");
		  }
	   }catch(Exception e){
		   e.printStackTrace();
		   rs.setCode(Resource.ERROR);
		      rs.setMsg("ɾ��ʧ��");
	   }
	    return  rs;
   }
      
   
   public Result   backById(String cid){
	   Result  rs=new  Result();
	   try {  

		rs.setData(cateDao.queryById(cid));
		rs.setCode(Resource.SUCCESS);
		rs.setMsg("���ط�����Ϣ�ɹ�");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		rs.setCode(Resource.ERROR);
		rs.setMsg("���ط�����Ϣʧ��");
	} 
	  return  rs;
   }
   
   public   Result   updateById(String cid,String name,String description){
	   Result  rs=new Result();
	   try{
	      cateDao.upById(cid,name,description);
	      rs.setData(cateDao.queryById(cid));
	      rs.setCode(Resource.SUCCESS);
	      rs.setMsg("���³ɹ�");
	   }catch(Exception e){
		   e.printStackTrace();
		   rs.setCode(Resource.ERROR);
		      rs.setMsg("����ʧ��");
	   }
	    return  rs;
   }
   
   public   Result   addCate(String cname){
	   Result  rs=new Result();
	   try{
		   if(cname!="") {
	      cateDao.addC(cname);
	      rs.setCode(Resource.SUCCESS);
	      rs.setMsg("���³ɹ�");
		   }else {
			   rs.setCode(Resource.USER_ERROR);
			      rs.setMsg("�������Ʋ���Ϊ��");
		   }
	   }catch(Exception e){
		   e.printStackTrace();
		   rs.setCode(Resource.ERROR);
		      rs.setMsg("����ʧ��");
	   }
	    return  rs;
   }
   
}
