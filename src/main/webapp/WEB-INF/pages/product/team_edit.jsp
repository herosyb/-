<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
//初始化datepicker对象
$('.datepicker').datepicker({
    format: 'yyyy/mm/dd',
    autoclose: true//选中自动关闭<select id="projectId" class="form-control required"></select>
})
$(document).ready(function(){
	$("#editFormId").on("click",".li",getSelectedProjectId);
	$("#modal-dialog").on("click",".ok",saveOrUpdate);
	//在弹框隐藏时，移除事件，防止数据多次进行提交
	$("#modal-dialog").on("hidden.bs.modal",function(){
		//当弹框被隐藏时执行的函数
		$("#modal-dialog").off("click",".ok");
		//在隐藏弹框时移除弹框中绑定的id值
		$("#modal-dialog").removeData("id");
	})
	
	var id = $("#modal-dialog").data("id");
	//alert(id);
	//因为修改和新增用的是同一个页面，只能通过当前id来判断到底是要新增操作还是修改操作
	if(id){//如果id有值，代表要做修改操作，根据id去查询当前这条数据
		findTeamById(id);
	}
	
});
function findTeamById(id){
	//alert(1);
	var url = "team/findTeamById";
	var params = {"id":id};
	$.getJSON(url,params,function(result){
		if(result.state==1){//成功
			//给弹框初始化数据
			//alert(1);
			console.log(result);
			initFormData(result.data);
		}else{
			alert(result.message);
		}
	});
}
//初始化弹框中加载的表单数据
function initFormData(project){//相当于后台返回的对象数据
	//console.log(project);
	$("#nameId").val(project[0].name);
	//console.log(project[0].name);
	$("#projectId").val(project[0].projectId);	
	$("#noteId").val(project[0].remarks);
	//console.log(project.remarks);
	$("input[type='radio']").each(function(){
		//禁用 0 --->0
		if($(this).val()==project[0].status){//判断项目的状态值
			//设置选中状态
			$(this).prop("checked",true);//选中
		}
	})	
}
function saveOrUpdate(){
	//验证表单数据是否为空
	if(!$("#editFormId").valid()){//表单为空则返回false
		return;
	}  
	//获取表单数据
	var params = {
		name:$("#nameId").val(),
		projectId:$("#projectId").val(),				
		remarks:$("#noteId").val(),
		status:$("input[type='radio']:checked").val()
	};
    console.log(params);
	//异步提交数据
	var saveUrl = "team/saveTeam";
	var updateUrl = "team/updateTeam";
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
			 findAllObject();
		 }else{
			 alert(result.message);
		 }
	});
	
}
function findAllObject(){
	var url="team/findTeamAll";
//	var params = {name:$("#searchNameId").val()};
//	var pageCurrent = $("#pageId").data("pageCurrent");
//	if(!pageCurrent){
//		pageCurrent = 1;
//	}
//	params['pageCurrent'] = pageCurrent;

	$.getJSON(url,function(result){
		//console.log(result);
		//将数据显示在table中的tbody中
		setTableBodyRows(result);//取出map中key为list的值，就是当前页数据
		//设置分页信息
//		setPagination(result.pageObject);//取出map中key为pageObject的值，就是分页数据
 	});
//		if(result.state==1){
//			//返回成功
//			//在tbody对应的位置显示数据
//			setTableTbodyRows(result.data.list);
//			//设置分页信息
//			setPagination(result.data.pageObject);
//		}else{
//			alert(result.message);
//		}
//		
//	});
}
function setTableBodyRows(result){
	var tBody = $("#tbodyId");
	//console.log(result);
	tBody.empty();
//	if(list.length==0){
//		var tr =  $("<tr></tr>");
//		var td = "<td colspan='3'>没有对应的数据</td>";
//		tr.append(td);
//		tBody.append(tr);
//	}
	for(var i in result){
		//console.log(result[i].name);
		var tr =  $("<tr></tr>");
		tr.data("id",result[i].id);
		
		var tds = "<td><input type='checkbox' name='checkId' value="+result[i].id+"></td>"
				+"<td>"+result[i].name+"</td>"
				+"<td>"+result[i].projectId+"</td>" 
				+"<td>"+result[i].createdTime+"</td>"
				+"<td>"+result[i].modifiedTime+"</td>"
				+"<td>"+(result[i].status?"有效":"无效")+"</td>"
				+"<td><input type='button' class='btn btn-warning btn-update' value='修改'></td>";
		//将td追加到tr对象
		tr.append(tds);
		//将tr追加到tbody对象
		tBody.append(tr);
	}
}
function getSelectedProjectId(){
	var val=$(this).attr("id");
     //console.log(val);   
	$("#projectId").val(val);	
}

   
</script>
<form  class="form-horizontal" role="form" id="editFormId">
	<div class="form-group">
		<label for="nameId" class="col-sm-2 control-label" >团名称:</label> 
	    <div class="col-sm-10">
			<input type="text" class="form-control required" name="name" id="nameId"  placeholder="请输入名字">
	    </div>
	</div>
	<div class="form-group">
		<label for="project-code" class="col-sm-2 control-label">所属项目:</label> 
		<div class="col-sm-10">
		 <div class="btn-group">
				<button type="button" class="btn btn-default dropdown-toggle" 
						data-toggle="dropdown">
					项目ID<span class="caret"></span>
				</button>
				<input type="text" class="form-control required" name="name" id="projectId"  placeholder="您所选的项目ID">
				<ul class="dropdown-menu" id="0" role="menu">
						<li id="1" class="li"><a href="#">1</a></li>
						<li class="divider"></li>
						<li id="2" class="li"><a href="#">2</a></li>						
						<li class="divider"></li>
						<li id="3" class="li"><a href="#">3</a></li>						
				</ul>
        </div>
	  </div>
	</div>
	<div class="form-group">
         <label class="col-sm-2 control-label"> 有效: </label>
         <div class="col-sm-10">
            <label class="radio-inline"><input  type="radio" name="valid" checked value="1" > 启用</label>
            <label class="radio-inline"><input  type="radio" name="valid" value="0"> 禁用</label>
         </div>
    </div>
	 <div class="form-group">
		<label for="noteId" class="col-sm-2 control-label">备注:</label>
		<div class="col-sm-10">
		<textarea class="form-control" name="note" id="noteId"></textarea>
		</div> 
	 </div>
</form>