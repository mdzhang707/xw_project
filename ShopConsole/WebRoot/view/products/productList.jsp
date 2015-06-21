<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="/view/commons/layout.jsp" />
<title>商品管理</title>
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a href="/shop/shopList" class="navbar-brand hidden-sm">商户管理</a>
			</div>
			<div class="navbar-header">
				<a href="/shop/productList" class="navbar-brand hidden-sm">商品管理</a>
			</div>
			<div class="navbar-header">
				<a href="#" class="navbar-brand hidden-sm">系统参数设置</a>
			</div>
		</div>
	</div>

 	<div class="container bs-docs-container" style="margin-top:20px" id="shopListDiv">
	 	<form class="form-horizontal" id="selectForm">
			<div class="form-group">
				<label for="name" class="col-sm-1 control-label">名称</label>
				<div class="col-sm-3">
					<input type="text" class="form-control"  name="product.name" placeholder="商品名称(模糊查询)">
				</div>
				
				<label for="name" class="col-sm-1 control-label">商家</label>
				<div class="col-sm-3">
					<input type="text" class="form-control"  name="product.shop" placeholder="商家">
				</div>
				
				<label for="name" class="col-sm-1 control-label">库存</label>
				<div class="col-sm-2">
					<input type="text" class="form-control"  name="product.count" placeholder="库存">
				</div>
				
				<div class="col-sm-1" >
					<a class="btn btn-primary" id="button"><span></span>查询</a>
				</div>
			</div>
		</form>
		
		<div id="productListGrid" ></div>
 	</div>
 	
 	<div id="editProductWindow" style="display: none"></div>
 	
 	<div id="createProductWindow" style="display: none"></div>
</body>

<script id="createTemplate" type="text/x-kendo-template">
		<a class="k-button" onclick="createNewProduct()">添加</a>
</script>

<script type="text/javascript">

$("#productListGrid").kendoGrid({
	toolbar:[{ template: kendo.template($("#createTemplate").html()) }],
    selectable: "multiple cell",
    allowCopy: true,
    pageable:false,
    columns: [
  	        { field: "name" ,title:"名称"},
	      	{field:"type",title:"类型"},
	      	{field:"showPrice",title:"商城价"},
	      	{field:"salesPrice",title:"卖价"},
	      	{field:"origPrice",title:"原价"},
	      	{field:"salesVolume",title:"销量"},
	      	{field:"count",title:"库存"},
	        {field:"uuid",title:"操作",template: "<a data-id='#: uuid #' role='edit'> 编辑 </a>&nbsp;&nbsp;&nbsp;&nbsp;<a data-id='#: uuid #' role='delete'> 删除</a>"}
    ],
    dataBound:function(){
    	$("a[role='edit']").off("click").click(function(){
    		var uid=$(this).data("id");
    		$.ajax({
    			type:"POST",
				url:"/product/editproduct",
				data:{uid:uid},
				success:function(data){
					$("#productListDiv").hide();
					$("#editProductWindow").html(data);
					$("#editProductWindow").show();
				},
				error:function(data){
					console.log(data);
					alert("error");
				}
    		});
    	});
    	
    	$("a[role='delete']").off("click").click(function(){
    		var uid=$(this).data("id");
    		$.ajax({
    			type:"POST",
				url:"/product/delete",
				data:	uid,
				success:function(data){
					ShowInfo("操作成功！");
					$("#button").click();
				},
				error:function(data){
					console.log(data);
					ShowError("操作失败！");
				}
    	});
    });
  	 }
   });
	$("#button").kendoButton({
		btn_query : true,
		click:function(){
			$.ajax({
				type:"POST",
				url:"/product/getProductList",
				data:$("#createForm").serialize(),
				success:function(data){
					$("#productListGrid").data("kendoGrid").setDataSource(new kendo.data.DataSource({data: data}));
				},
				error:function(data){
					console.log(data);
					alert("error");
				}
			});
		}
	});
	
	function createNewProduct(){
		$.ajax({
			url:"/product/createProduct",
			type:"GET",
			success:function(data){
				$("#productListDiv").hide();
				$("#createProductWindow").html(data);
				$("#createProductWindow").show();
			},
			error:function(data){
				console.log(data);
				alert("Error");
			}
		});
	}
</script>
</html>