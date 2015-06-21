package com.manage.configs;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.JspRender;
import com.jfinal.render.ViewType;
import com.manage.controller.*;
import com.manage.model.*;

public class WeShopConfig extends JFinalConfig{

	@SuppressWarnings("deprecation")
	@Override
	public void configConstant(Constants me) {
		me.setDevMode(true);
		me.setViewType(ViewType.JSP);
		JspRender.setSupportActiveRecord(false);
	}

	@Override
	public void configHandler(Handlers me) {
		
	}

	@Override
	public void configInterceptor(Interceptors me) {
		
	}

	@Override
	public void configPlugin(Plugins me) {
		loadPropertyFile("config.properties");
		C3p0Plugin c3p0Plugin = new C3p0Plugin(getProperty("URL"),getProperty("USERNAME"), getProperty("PASSWORD"));
		me.add(c3p0Plugin);
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		me.add(arp);
		arp.addMapping("adInfo",  "iid", AdInfo.class);
		arp.addMapping("cheapproduct",  "iid", CheapProduct.class);
		arp.addMapping("citycode", "iid",  CityCode.class);
		arp.addMapping("orderinfo", "iid",  OrderInfo.class);
		arp.addMapping("orderinfodetail",  "iid", OrderInfoDetail.class);
		arp.addMapping("product",  "iid", Product.class);
		arp.addMapping("profitreturn",  "iid", ProfitReturn.class);
		arp.addMapping("profitreturnsetting",  "iid", ProfitReturnSetting.class);
		arp.addMapping("salespaytask", "iid",  SalesPayTask.class);
		arp.addMapping("shopcomments",  "iid", ShopComments.class);
		arp.addMapping("shopinfo", "iid",  ShopInfo.class);
		arp.addMapping("shoptypedic",  "iid", ShopTypeDic.class);
		arp.addMapping("specialactivity",  "iid", SpecialActivity.class);
		arp.addMapping("userinfo",  "iid", UserInfo.class);
	}

	@Override
	public void configRoute(Routes me) {
		me.add("/dashBoard",DashBoardController.class);
		me.add("/shop",ShopController.class);
		me.add("/ad",AdInfoController.class);
		me.add("/cheapProduct",CheapProductController.class);
		me.add("/cityCode",CityCodeController.class);
		me.add("/order",OrderController.class);
		me.add("/product",ProductController.class);
		me.add("/profitReturnSetting",ProfitReturnSettingController.class);
		me.add("/comment", ShopCommentsController.class);
		me.add("/type", ShopTypeDicController.class);
		me.add("/specialActivity",SpecialActivityController.class);
	}
}
