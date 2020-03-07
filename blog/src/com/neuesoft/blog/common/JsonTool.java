package com.neuesoft.blog.common;
import java.util.ArrayList;
import com.google.gson.Gson;
 

public class JsonTool {
 
   public   static   String   turnToJson(Object obj){
	   Gson gson = new Gson();
	   return  gson.toJson(obj);
   }
   
   
   
}
