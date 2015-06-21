package com.manage.fileUtil;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Connection;

import net.sf.json.JSONObject;

/**
 * 数据库操作
 * @author mdzhang
 * 
 */
public class SqlHelp {
	/**
	 * 数据库连接
	 * @return
	 */
	public static final Connection getConnection() {
		Map<String, String> map = PropertiesLoad.loadData("url,user,password,driver");
		try {
			Class.forName(map.get("driver"));
			Connection con = DriverManager.getConnection(map.get("url"), map.get("user"), map.get("password"));
			return con;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 返回数据库连接对象
	 * @return
	 */
	public static final Statement getStatement() {
		try {
			Connection con = getConnection();
			Statement state = con.createStatement();
			return state;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 通过查询语句将查询结果转换成对应的实体
	 * @param sql
	 *  查询语句
	 * @param object
	 *  需要转换成的对象
	 * @return
	 *  查询出的结果
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> coverSelectObject(String sql,Class<?> object) {
		try {
			Statement state = getStatement();
            Field[] fields = object.getDeclaredFields();
            for(Field field:fields){
            	field.setAccessible(true);
            }
            List<T> result=new ArrayList<T>();
			ResultSet rs = state.executeQuery(sql);
			while (null != rs && rs.next()) {
				Map<String,Object>map=new HashMap<String, Object>();
				for(Field field:fields){
					String column=field.getName();
					Class<?> type=field.getType();
				    if(type.getName().equals("int")){
						int value=rs.getInt(column);
						map.put(column, value);
					}else if(type.getName().equals("Date")){
						Date value=rs.getDate(column);
						map.put(column, value);
					}else if(type.getName().equals("float")){
						float value=rs.getFloat(column);
						map.put(column, value);
					}else{
						String value = rs.getString(column);
						map.put(column, value);
					}
				}
				result.add((T)JSONObject.toBean(JSONObject.fromObject(map), object));
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	/**
	 * 判断内容内容是否为空、空格、空字符串
	 * @param str
	 * @return
	 */
	public static boolean IsNullOrBlack(String str) {
		boolean result = false;
		if (null == str) {
			result = true;
		}else if("".equals(str)){
			result = true;
		}else if ("".equals(str.trim())) {
			result = true;
		}else if("".equals(str.replace(" ", ""))){
			result=true;
		}
		return result;
	}
}