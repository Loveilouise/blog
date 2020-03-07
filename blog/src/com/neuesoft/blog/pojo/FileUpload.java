package com.neuesoft.blog.pojo;

import java.io.Serializable;
/**
 * 2020.1.14 文件管理javabean
 * 
 * @author wangyu
 *
 */
public class FileUpload implements Serializable{
	private Integer upload_id;
	private String keyname;
	private String hash;
	
	public Integer getUpload_id() {
		return upload_id;
	}

	public void setUpload_id(Integer upload_id) {
		this.upload_id = upload_id;
	}
	
	public String getKeyname() {
		return keyname;
	}

	public void setKeyname(String keyname) {
		this.keyname = keyname;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	@Override
	public String toString() {
		return "file [keyname=" + keyname + ", hash=" + hash + "]";
	}
	
}
