var columns = [
{
field : 'selectItem',
radio : true
},
{
title : '分类id',
field : 'id',//一定要和后台返回的数据中map中的key一一对应
visible : false,
align : 'center',
valign : 'middle',
width : '80px'
},
{
title : '分类名称',
field : 'name',
align : 'center',
valign : 'middle',
sortable : true,
width : '180px'
},
{
title : '上级分类',
field : 'parentName',
align : 'center',
valign : 'middle',
sortable : true,
width : '180px'
},
{
title : '排序号',
field : 'sort',
align : 'center',
valign : 'middle',
sortable : true,
width : '100px'
}];



$(document).ready(function(){
	//给删除按钮绑定事件
	$("#formHead").on("click",".btn-delete",deleteType);
	//给新增按钮绑定点击事件
	$("#formHead").on("click",".btn-add",loadEditPage);
	
	findAllProductType();
	
});
//加载编辑页面
function loadEditPage(){
	var url = "productType/editUI";
	$(".content").load(url,function(){
		//设置标题
		$(".page-title").html("添加产品分类");
	});
}

//删除分类
function deleteType(){
	//获取选中的id值
	var id = getSeletedId();
	//根据选中的id值去删除分类信息
	if(!id){
		alert("请先选择要删除的分类信息！");
		return ;
	}
	var url = "productType/deleteType";
	var params = {"id":id}; 
	$.post(url,params,function(result){
		if(result.state==1){
			alert(result.message);//删除成功
			//重新查询
			findAllProductType();
		}else{
			alert(result.message);
		}
	});
}
//获取选中的id值
function getSeletedId(){
	//获取选中的记录，返回的是数组
	var selections = $("#typeTable").bootstrapTreeTable("getSelections");
	if(selections.length==0){//没有选中
		return;
	}
	//获取选中的id
	return selections[0].id;
	
}
//查询所有数据
function findAllProductType(){
	var tableId = "typeTable";
	//访问服务端的url地址
	var url = "productType/findAllProductType";
	var table = new TreeTable(tableId, url, columns);//
	table.setExpandColumn(2);//设置默认展开列，传入的是下标，从0开始
	//初始化table对象
	table.init();//发起异步请求获取数据，更新页面
	
}





















