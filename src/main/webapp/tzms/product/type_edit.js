var zTree;
var setting = {
		data : {   
			simpleData : {
				enable : true,//可用
				idKey : "id",  //节点数据中保存唯一标识的属性名称
				pIdKey : "parentId",  //节点数据中保存其父节点唯一标识的属性名称
				rootPId : null  //根节点id
			}
		}
}
$(document).ready(function(){
	//给back按钮绑定事件
	$("#btn-return").click(function(){
		back();
	});
	//给上级分类文本框绑定事件
	$("#editTypeForm").on("click","#parentNameId",loadZtreeNodes);
	$("#typeLayer").on("click",".btn-cancle",hideZtree);
	//给树的确定绑定事件
	$("#typeLayer").on("click",".btn-confirm",setType);
	//给save按钮添加事件
	$("#editTypeForm").on("click",".btn-primary",saveOrUpdateProductType);
});
function saveOrUpdateProductType(){
	if(!$("#editTypeForm").valid()){
		return;
	}
	var params = getEditFormData();
	//获取表单数据
	//保存数据
	var url = "productType/saveProductType";
	$.post(url,params,function(result){
		if(result.state==1){
			alert(result.message);
			//回到列表页面
			back();
		}else{
			alert(result.message);
		}
	});
}
//获取表单函数
function getEditFormData(){
	var params = {
			name : $("#typeNameId").val(),
			parentId :$("#parentNameId").data("parentId"),
			sort:$("#typeSortId").val(),
			remarks : $("#typeNoteId").val()
	};
	return params;
	
}

//设置上级分类信息
function setType(){
	//先获取选中的数据信息
	var nodes = zTree.getSelectedNodes();
	//console.log(nodes[0].id);
	if(nodes.length==0){
		//没有选择
		alert("请任意选择一项分类");
		return;
	}
	//将选中数据信息填充到form表单中
	$("#parentNameId").val(nodes[0].name);
	//在新增数据时需要把上级分类的id传递到后台中去
	$("#parentNameId").data("parentId",nodes[0].id);
	
	//隐藏树
	hideZtree();
}
//隐藏树
function hideZtree(){
	$("#typeLayer").css("display","none");
	
}
//点击编辑页面上的上级分类的表单执行函数
//加载树
function loadZtreeNodes(){
	//树显示
	$("#typeLayer").css("display","block");
	//异步查询树要显示的数据
	var url = "productType/findZtreeNodes";
	$.getJSON(url,function(result){
		if(result.state==1){//正常返回数据 jquery.zTree.js
			zTree = $.fn.zTree.init($("#typeTree"),setting,result.data);
		}else{
			alert(result.message);
		}
	});
}
//返回到产品分类页面
function back(){
	var url="productType/listUI";
	$(".content").load(url);
}
