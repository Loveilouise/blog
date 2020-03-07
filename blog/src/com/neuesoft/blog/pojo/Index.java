package com.neuesoft.blog.pojo;

import java.util.ArrayList;

public class Index {
	private String acounts;
	private String ccounts;
	private String fcounts;
	
	private ArrayList<Comment> clist;
	private ArrayList<Article> alist;
	
	public Index() {
		
	}

	public Index(String acounts, String ccounts, String fcounts) {
		super();
		this.acounts = acounts;
		this.ccounts = ccounts;
		this.fcounts = fcounts;
		
	}

	public String getAcounts() {
		return acounts;
	}

	public void setAcounts(String acounts) {
		this.acounts = acounts;
	}

	public String getCcounts() {
		return ccounts;
	}

	public void setCcounts(String ccounts) {
		this.ccounts = ccounts;
	}

	public String getFcounts() {
		return fcounts;
	}

	public void setFcounts(String fcounts) {
		this.fcounts = fcounts;
	}

	




	

	public ArrayList<Comment> getClist() {
		return clist;
	}

	public void setClist(ArrayList<Comment> clist) {
		this.clist = clist;
	}

	public ArrayList<Article> getAlist() {
		return alist;
	}

	public void setAlist(ArrayList<Article> alist) {
		this.alist = alist;
	}

	@Override
	public String toString() {
		return "Index [acounts=" + acounts + ", ccounts=" + ccounts + ", fcounts=" + fcounts + ", clist=" + clist
				+ ", alist=" + alist + "]";
	}
	
	
}
