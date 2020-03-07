package com.neuesoft.blog.service;


import java.sql.SQLException;

import com.neuesoft.blog.common.Pagion;
import com.neuesoft.blog.common.Resource;
import com.neuesoft.blog.common.Result;
import com.neuesoft.blog.dao.ArticleDao;
import com.neuesoft.blog.pojo.Article;
import com.neuesoft.blog.pojo.Category;
import com.neuesoft.blog.pojo.Category_article;

public class ArticleService {

	private  ArticleDao  arDao=new  ArticleDao();
	
	//发布文章时获取分类信息
	public Result   backcateByCId(){
		   Result  rs=new  Result();
		   try {
			rs.setData(arDao.querynameBycId());
			rs.setCode(Resource.SUCCESS);
			rs.setMsg("返回信息成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rs.setCode(Resource.ERROR);
			rs.setMsg("返回信息失败");
		} 
		  return  rs;
	   }
	//添加文章
	public Result addAtricle(String aid,Article article,String[] catelist){
		   Result  rs=new Result();
		   try{
			  arDao.addAtricle(aid,article,catelist);
		      rs.setCode(Resource.SUCCESS);
		      rs.setMsg("保存成功");
		   }catch(Exception e){
			   e.printStackTrace();
			   rs.setCode(Resource.ERROR);
			   rs.setMsg("保存失败");
		   }
		    return  rs;
	   }
	//编辑文章时传回内容
	public Result   backByaId(String aid){
		   Result  rs=new  Result();
		   try {
			rs.setData(arDao.queryByaId(aid));
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
	
	public Result addCate(String title,String[] category)
	   {
		   Result rs = new Result();
		   try {
			   arDao.insertName(title,category);
			   rs.setCode(Resource.SUCCESS);
			   rs.setMsg("插入草稿成功");	  
		} catch (Exception e) {
			e.printStackTrace();
			rs.setCode(Resource.ERROR);
			rs.setMsg("插入分类信息失败");
		}
		   return rs;
	   }
	
	   public Result UnSelectCategory(String aid)
	   {
		   Result rs = new Result();
		   try {
			   rs.setData(arDao.UnSelect(aid));
			   rs.setCode(Resource.SUCCESS);
			   rs.setMsg("插入草稿成功");	  
		} catch (Exception e) {
			e.printStackTrace();
			rs.setCode(Resource.ERROR);
			rs.setMsg("插入分类信息失败");
		}
		   return rs;
	   }
	   
	   public Result getArticleList(String currentPage){
			Result rs = new Result();
			try {
				rs.setData(arDao.selectAllArticle(currentPage));
				rs.setCode(Resource.SUCCESS);
				rs.setMsg("查询成功");
				//创建分页对象
				Pagion page = new Pagion();
				//设置分页的当前也和总页数
				page.setCurrentPage(currentPage);
				page.setPageTotal(arDao.getTotalPage());
				rs.setPage(page);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				rs.setCode(Resource.ERROR);
				rs.setMsg("查询失败");
			}
			return rs;
		}
}
