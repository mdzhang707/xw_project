<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div id="createNewProdect" >
		<div class="row" style="margin-top:5px">
				<label for="wechart" class="col-md-2 control-label" style="padding-top:7px;text-align:right">名称</label>
				<div class="col-md-3">
					<input type="text" class="form-control" id="prodectName"  style="box-sizing: border-box;" placeholder="名称">
				</div>
				
				<label for="wechart" class="col-md-1 control-label" style="padding-top:7px;text-align:right">类型</label>
				<div class="col-md-3">
					<input type="text" class="form-control" id="prodectType" name="secondCode" style="box-sizing: border-box;"placeholder="类型">
				</div>
			</div>
			<div class="row" style="margin-top:5px">
				<label for="wechart" class="col-md-2 control-label" style="padding-top:7px;text-align:right">原价</label>
				<div class="col-md-3">
					<input type="text" class="form-control" id="origPrice" style="box-sizing: border-box;" placeholder="商家提供价格">
				</div>
				
				<label for="wechart" class="col-md-1 control-label" style="padding-top:7px;text-align:right">商城价</label>
				<div class="col-md-3">
					<input type="text" class="form-control" id="showPrice" style="box-sizing: border-box;" placeholder="商城显示的原价">
				</div>
			</div>
			
			<div class="row" style="margin-top:5px">
				<label for="wechart" class="col-md-2 control-label" style="padding-top:7px;text-align:right">售价</label>
				<div class="col-md-3">
					<input type="text" class="form-control" id="salesPrice" style="box-sizing: border-box;" placeholder="用户需要支付的价格">
				</div>
				
				<label for="wechart" class="col-md-1 control-label" style="padding-top:7px;text-align:right">库存</label>
				<div class="col-md-3">
					<input type="text" class="form-control" id="count"  style="box-sizing: border-box;" placeholder="库存量">
				</div>
			</div>
			
			<div class="row" style="margin-top:5px">
				<label for="wechart" class="col-md-2 control-label" style="padding-top:7px;text-align:right">图片</label>
				<div class="col-md-6">
					<input type="text" class="form-control" id="img"  style="box-sizing: border-box;" placeholder="用户需要支付的价格">
				</div>
				<div class="col-md-1">
					<input type="file" name="files" id="chooseImg"  text="选择"/>
				</div>
			</div>
	</div>
	
	<!-- 	<div id="attrList">
		<div class="row" style="margin-top:5px">
				<label class="col-md-1 control-label" style="padding-top:7px;text-align:right">属性名称</label>
				<div class="col-md-1">
					<input type="text" class="form-control" id="attributesName" style="box-sizing: border-box;" placeholder="属性名称">
				</div>
				
				<label class="col-md-1 control-label" style="padding-top:7px;text-align:right">属性值</label>
				<div class="col-md-5">
					<input type="text" class="form-control" id="attributesValue" style="box-sizing: border-box;" placeholder="属性值，以逗号分隔">
				</div>
			</div>
			<div role="attributes"></div>
	</div> -->