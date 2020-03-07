package com.neuesoft.blog.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.neuesoft.blog.common.JDBCTool;
import com.neuesoft.blog.common.Resource;
import com.neuesoft.blog.pojo.Category;
import com.neuesoft.blog.pojo.FileUpload;

/**
 * 2020.1.14 文件管理Dao类
 * 
 * @author wangyu
 *
 */
public class FileUploadDao {

	public ArrayList<FileUpload> queryAll() throws Exception {
		String sql = "select  * from   fl";
		ResultSet rs = JDBCTool.executeQuery(sql);
		ArrayList<FileUpload> list = new ArrayList<FileUpload>();
		FileUpload obj = null;
		while (rs.next()) {
			obj = new FileUpload();
			obj.setUpload_id(rs.getInt("upload_id"));
			obj.setKeyname(rs.getString("keyname"));
			obj.setHash(rs.getString("hash"));
			list.add(obj);
		}
		return list;
	}

	public void deleById(String fid) {
		String sql = "delete  from  fl  where  upload_id=" + fid;
		JDBCTool.execute(sql);
	}

	public void insertFile(FileUpload file) throws Exception {
		String keyname = file.getKeyname();
		String hash = file.getHash();
		String sql = "insert into fl (keyname,hash) values (KEYNAME,HASH)";
		sql = sql.replace("KEYNAME","'"+ keyname+"'").replace("HASH", "'"+ hash+"'");
		JDBCTool.execute(sql);
	}

}
