package com.manage.model;

import java.util.Date;
import java.util.UUID;

import com.jfinal.plugin.activerecord.Model;

public class ModelUtil {
	public static final boolean softDelete(Model<?> model){
		model.set("deleted", 1).set("deleted_at", new Date());
		return model.update();
	}
	
	public static final void generateUUID(Model<?> model){
		ModelUtil.generateUUID(model, "uuid");		
	}
	
	public static final void generateUUID(Model<?> model, String uuid){
		model.set(uuid, UUID.randomUUID().toString());
	}
}
