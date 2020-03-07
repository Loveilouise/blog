package com.neuesoft.blog.dao;

import java.util.ArrayList;

import com.neuesoft.blog.pojo.Article;
import com.neuesoft.blog.pojo.Category;
import com.neuesoft.blog.pojo.Comment;

public class Post {

	private Article article;
	private Comment comment;
	private ArrayList<Category> categorylist;

	public ArrayList<Category> getCategorylist() {
		return categorylist;
	}
	public void setCategorylist(ArrayList<Category> categorylist) {
		this.categorylist = categorylist;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	/*public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}*/
	
	
}
