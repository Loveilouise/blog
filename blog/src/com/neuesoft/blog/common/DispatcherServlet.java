package com.neuesoft.blog.common;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuesoft.blog.annotation.Controller;
import com.neuesoft.blog.annotation.RequestMapping;


public class DispatcherServlet extends HttpServlet {

	//servlet生命周期 init service destroy
	//Servlet初始化走init
	//所有请求都走service get或post请求都走
	
	HashMap<String,Class> map = new HashMap<String,Class>();
	@Override
	public void init() throws ServletException {
		String pack="com.neuesoft.blog.controller";
		Set<Class<?>> set = ScanUtil.getClasses(pack);
		for (Class<?> c : set) {
			if(c.isAnnotationPresent(Controller.class)){
				Method[] methods = c.getMethods();
				for (Method m : methods) {
					if(m.isAnnotationPresent(RequestMapping.class)){
						RequestMapping obj = m.getAnnotation(RequestMapping.class);
						map.put(obj.value(),c);
						System.out.println(obj.value()+" "+c);
					}
				}
			}
		}							
	}
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = resolveUri(req);
		if(map.containsKey(uri)){
			Class c = map.get(uri);
			Method[] methods = c.getMethods();
			for (Method m : methods) {
				if(m.isAnnotationPresent(RequestMapping.class)){
					RequestMapping anno = m.getAnnotation(RequestMapping.class);
					if(anno.value().equals(uri)){
						try {
							m.invoke(c.newInstance(),req,resp);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}		
		}
	}
		
	
	
	public String resolveUri(HttpServletRequest request){
		String uri = request.getRequestURI();
		uri = uri.replace("/blog","");
		return uri;
	}
}
