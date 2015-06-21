package com.manage.fileUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


public class PropertiesLoad {
     public static Map<String,String> loadData(String keys){
    	 List<String> keyInputs=new ArrayList<String>();
    	 Properties prop = new Properties();   
         InputStream in = Object.class.getResourceAsStream("/config.properties");   
         try {   
             prop.load(in);  
             Map<String,String> map=new HashMap<String, String>();
             for(String key:keyInputs){
            	 String value= prop.getProperty(key.toUpperCase()).trim();  
            	 map.put(key, value);
             }
             return map;
         } catch (IOException e) {   
             e.printStackTrace();   
             return new HashMap<String,String>();
         }   
     }
}
