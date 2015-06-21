<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<style type="text/css">
 #createNewProdect{
 	overflow:hidden !important;
 }
 
 .k-button.k-upload-button{
 	margin-top:0;
 }
</style>
<div class="container bs-docs-container" style="margin-top:20px" id="shopListDiv">
	 	<form class="form-horizontal" id="createForm">
			<div class="row" style="margin-top:5px;">
				<label for="name" class="col-md-2 control-label">名称：</label>
				<div class="col-md-3">
					<input type="text" class="form-control" id="shopNames" name='shopInfo.name' placeholder='商户名称'  data-len="15">
				</div>
				
				<label for="name" class="col-md-2 control-label">微信号：</label>
				<div class="col-md-3">
					<input type="text" class="form-control" id="wechart" name='shopInfo.wechart' placeholder='微信号' data-len="30">
				</div>
			</div>
		
			<div class="row" style="margin-top:5px;">
				<label for="wechart" class="col-md-2 control-label">一级单位</label>
				<div class="col-md-3">
					<input type="text" class="form-control" id="firstCode" name="shopInfo.firstCode" placeholder="一级行政单位">
				</div>
				
				<label for="wechart" class="col-md-2 control-label">二级单位</label>
				<div class="col-md-3">
					<input type="text" class="form-control" id="secondCode" name="shopInfo.secCode" placeholder="二级行政单位">
				</div>
			</div>
		
			<div  class="row" style="margin-top:5px;">
				<label for="name" class="col-md-2 control-label">经度：</label>
				<div class="col-md-3">
					<input type="text" class="form-control" id="longitude" name='shopInfo.longitude' placeholder='经度' data-len="3:4" data-type="n4">
				</div>
				
				<label for="name" class="col-md-2 control-label">纬度：</label>
				<div class="col-md-3">
					<input type="text" class="form-control" id="latitude" name='shopInfo.latitude' placeholder='纬度' data-len="3:4" data-type="number">
				</div>
			</div>
		</form>
		
		<div class="row"  style="margin-top:15px">
			<div class="col-md-10" style="text-align:right">
				<a class="btn btn-primary" onclick="submitShop()"><span class="fa fa-save"></span>确定</a>
			</div>
			<div class="col-md-1">
				<a class="btn btn-primary" onclick="goBack()"><span class="fa fa-cancel"></span>取消</a>
			</div>
		</div>
	</div>

<script type="text/javascript">	
	function goBack(){
		$("#createShopWindow").html("");
		$("#createShopWindow").hide();
		$("#shopListDiv").show();
	}
	
	function submitShop(){
		$.ajax({
			type:"POST",
			url:"/shop/create",
			data:$("#createForm").serialize(),
			success:function(data){
				if(data.succeed){
					alert("保存成功");
				}
			},
			error:function(data){
				console.log(data);
				alert("保存失败！");
			}
		})
	}
</script>