$(document).ready(function(){
	//绑定点击事件
	$("#modal-dialog").on("click",".ok",saveOrUpdate);
	//在弹框隐藏时，移除事件，防止数据多次进行提交
	$("#modal-dialog").on("hidden.bs.modal",function(){
		//当弹框被隐藏时执行的函数
		$("#modal-dialog").off("click",".ok");
		//在隐藏弹框时移除弹框中绑定的id值
		$("#modal-dialog").removeData("id");
	})
	
	var id = $("#modal-dialog").data("id");
	//因为修改和新增用的是同一个页面，只能通过当前id来判断到底是要新增操作还是修改操作
	if(id){//如果id有值，代表要做修改操作，根据id去查询当前这条数据
		findProjectById(id);
	}
	
})
//根据id执行查询操作
function findProjectById(id){
	var url = "project/findProjectById";
	var params = {"id":id};
	$.getJSON(url,params,function(result){
		if(result.state==1){//成功
			//给弹框初始化数据
			initFormData(result.data);
		}else{
			alert(result.message);
		}
	});
}
//初始化弹框中加载的表单数据
function initFormData(project){//相当于后台返回的对象数据
	$("#nameId").val(project.name);
	$("#codeId").val(project.code);
	$("#beginDateId").val(project.beginDate);
	$("#endDateId").val(project.endDate);
	$("#remarksId").val(project.remarks);
	$("input[type='radio']").each(function(){
		//禁用 0 --->0
		if($(this).val()==project.status){//判断项目的状态值
			//设置选中状态
			$(this).prop("checked",true);//选中
		}
	})
	
}

//添加或者修改项目数据
function saveOrUpdate(){
	//验证表单数据是否为空
	if(!$("#editFormId").valid()){//表单为空则返回false
		return;
	}  
	//获取表单数据
	var params = {
		name:$("#nameId").val(),
		code:$("#codeId").val(),
		beginDate:$("#beginDateId").val(),	
		endDate:$("#endDateId").val(),		
		remarks:$("#remarksId").val(),
		status:$("input[type='radio']:checked").val()
	};
//	console.log(params);
	//异步提交数据
	var saveUrl = "project/saveProject";
	var updateUrl = "project/updateProject";
	//通过从弹框中取出id值判断当前是新增操作还是修改
	var id = $("#modal-dialog").data("id");
	var url = id?updateUrl:saveUrl;
	//如果是修改操作，就需要往参数对象上添加id参数传递到后台
	if(id){
		params.id=id;
	}
	$.post(url,params,function(result){
		 if(result.state==1){//成功
			 //关闭弹框
			 $("#modal-dialog").modal("hide");
			 alert(result.message);
			 //重新查询
			 findAllProject();
		 }else{
			 alert(result.message);
		 }
	});
	
}