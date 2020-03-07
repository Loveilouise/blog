package com.neuesoft.blog.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.neuesoft.blog.annotation.Controller;
import com.neuesoft.blog.annotation.RequestMapping;
import com.neuesoft.blog.common.JsonTool;
import com.neuesoft.blog.common.WriterTool;
import com.neuesoft.blog.pojo.FileUpload;
import com.neuesoft.blog.service.FileUploadService;
/**
 * 2020.1.14 �ļ����������
 * @author wangyu
 *
 */
@Controller
public class FileUploadController {
	private  FileUploadService  fileUploadService=new  FileUploadService();
	@RequestMapping("/deleteFile.do")
	public  void  deleteFile (HttpServletRequest req,HttpServletResponse resp){
		String fid = req.getParameter("fid");
		WriterTool.write(resp, JsonTool.turnToJson(fileUploadService.removeById(fid)));
	}
	@RequestMapping("/getFileList.do")
	public  void  getFileList (HttpServletRequest req,HttpServletResponse resp){
		WriterTool.write(resp, JsonTool.turnToJson(fileUploadService.queryAll()));
	}
	
	@RequestMapping("/fileUpload.do")
	public  void  getCategoryList (HttpServletRequest request,HttpServletResponse response){
		FileUpload ff = new FileUpload();
		String filename = null;
        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            // �����ϴ��ļ��Ĵ�С����Ϊ1M
            factory.setSizeThreshold(1024 * 1024);
             
            List items = null;
            try {
                items = upload.parseRequest(request);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
  
            Iterator iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                if (!item.isFormField()) {
  
                    // ����ʱ�������ͷ���ļ�
                    filename = System.currentTimeMillis() + ".jpg";
                    String photoFolder = request.getServletContext().getRealPath("/")+"uploaded";
                    File f = new File(photoFolder, filename);
                    f.getParentFile().mkdirs();
                    
                    // ͨ��item.getInputStream()��ȡ������ϴ����ļ���������
                    InputStream is = item.getInputStream();
                    String imgUrl = request.getScheme() //��ǰ����ʹ�õ�Э��
                    	    +"://" + request.getServerName()//��������ַ 
                    	    + ":" + request.getServerPort() //�˿ں� 
                    	    +"/blog/uploaded/"+filename;
                    System.out.println(imgUrl);
                    ff.setKeyname(filename);
                    ff.setHash(imgUrl);
                    // �����ļ�
                    FileOutputStream fos = new FileOutputStream(f);
                    byte b[] = new byte[1024 * 1024];
                    int length = 0;
                    while (-1 != (length = is.read(b))) {
                        fos.write(b, 0, length);
                    }
                    fos.close();
  
                } else {
                    System.out.println(item.getFieldName());
                    String value = item.getString();
                    value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
                    System.out.println(value);
                }
            }
             
        } catch (Exception e) {
        	WriterTool.write(response, JsonTool.turnToJson(fileUploadService.backUploadResult(false,null)));
            e.printStackTrace();
        }
		WriterTool.write(response, JsonTool.turnToJson(fileUploadService.backUploadResult(true,ff)));
	}
	
		
	
	
}
