package com.neuesoft.blog.service;

import java.util.ArrayList;

import com.neuesoft.blog.common.Resource;
import com.neuesoft.blog.common.Result;
import com.neuesoft.blog.dao.FileUploadDao;
import com.neuesoft.blog.pojo.FileUpload;

/**
 * 2020.1.14 �ļ�����ҵ����
 * 
 * @author wangyu
 *
 */
public class FileUploadService {
	private FileUploadDao fileUpload = new FileUploadDao();

	public Result backUploadResult(Boolean status,FileUpload ff) {
		Result rs = new Result();
		rs.setData("");
		if (status) {
			if(ff.getKeyname()!=null&&ff.getHash()!=null) {
				try {
					fileUpload.insertFile(ff);
					rs.setCode(Resource.SUCCESS);
					rs.setMsg("�ϴ��ɹ�");
				} catch (Exception e) {
					rs.setCode(Resource.ERROR);
					rs.setMsg("�ϴ�ʧ��");
				}
			}
			else {
				rs.setCode(Resource.ERROR);
				rs.setMsg("�ϴ�ʧ��");
			}
		} else {
			rs.setCode(Resource.ERROR);
			rs.setMsg("�ϴ�ʧ��");
		}
		return rs;
	}
	
	public Result queryAll() {
		Result rs = new Result();
		try {
			ArrayList<FileUpload> fileList = fileUpload.queryAll();
			rs.setCode(Resource.SUCCESS);
			rs.setData(fileList);
			rs.setMsg("��ѯ�ɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			rs.setCode(Resource.ERROR);
			rs.setMsg("��ѯʧ��");
		}
		return rs;
	}
	
	public Result removeById(String fid) {
		Result rs = new Result();
		try {
			fileUpload.deleById(fid);
			rs.setCode(Resource.SUCCESS);
			rs.setMsg("ɾ���ɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			rs.setCode(Resource.ERROR);
			rs.setMsg("ɾ��ʧ��");
		}
		return rs;
	}


}
