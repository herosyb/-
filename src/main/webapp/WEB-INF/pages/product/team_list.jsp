<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>
<script type="text/javascript" src="${basePath}/tzms/product/team_list.js"></script>
<script>
$(document).ready(function(){
	$("#queryFormId").on("click",".btn-search",doQueryObject);
	$("#queryFormId").on("click",".btn-add",loadEditPage);
	//在修改按钮上绑定点击事件
	$("#queryFormId").on("click",".btn-update",loadEditPage);
	$("#queryFormId").on("click",".btn-invalid,.btn-valid",updateStatusById);
});
//禁用或启用项目信息
function updateStatusById(){
	 //获取当前点击的按钮到底是禁用还是启用
	//var clazz = $(this).attr("class");//得到当前点击对象的class属性
	//if(clazz=="btn btn-primary btn-invalid"){}
	//获取点击的button对象，根据点击对象的不同来设置不同的状态值
	var status ;
	 if($(this).hasClass("btn-invalid")){//禁用
		 //设置状态值
		 status = 0;
	 }
	 if($(this).hasClass("btn-valid")){//启用
		 status = 1;
	 }
	 //获取选中的复选框的id值
	 var ids = "";//1,2,3
	 $("#tbodyId input[name='checkId']").each(function(){
		 //获取到所有的复选框 进行遍历，
		 //判断哪个复选框被选中了
		 if($(this).prop("checked")){//如果if判断为true，代表当前遍历到的复选框被选中了
			 if(ids==""){
				 ids += $(this).val();//ids=1
			 }else{
				 ids +=","+$(this).val();//ids=1,2
			 }
		 }
	 })
	 
	 if(ids==""){
		 alert("请至少选择一条记录");
		 return ;
	 }
	 console.log("status="+status);
	 console.log("ids="+ids);
	 //发起异步请求，更新数据
	 var url = "team/updateStatusById";
	 var params = {"status":status,"ids":ids};
	 $.post(url,params,function(result){
		 if(result.state==1){
			 //成功
			 alert(result.message);
			 findAllObject();
		 }else{
			 alert(result.message);
		 }
	 });
	 
}
function loadEditPage(){
	 //发送显示编辑页面请求
	 var url = "team/edit";
	 var title;
	 if($(this).hasClass("btn-add")){//添加
		 title = "添加团信息";
	 }else if($(this).hasClass("btn-update")){
		 title = "修改团信息";
		 //把要获取的修改记录id绑定到弹框对象中
		 //绑定id的目的，是为了要根据弹框中的id值做判断，判断当前是新增操作还是修改操作
		//从tr中取出id值
		var idValue = $(this).parent().parent().data("id");//key=id
		$("#modal-dialog").data("id",idValue);
		//alert(idValue);
	 }
	 //在弹框中异步加载显示编辑页面
	 $("#modal-dialog .modal-body").load(url,function(){
		 $("#myModalLabel").html(title);
		 //页面加载完成之后显示弹框,方法传入show代表显示，如果传入hide代表隐藏
		 $("#modal-dialog").modal("show");
	 });
}
function doQueryObject(){
	 //$("#pageId").data("pageCurrent",1);
	 //点击查询按钮拿到用户输入name传到后台控制器中执行查询代码
     var name=$("#searchNameId").val();
	 if(!name){
		 findAllObject();	 
 }
	 findAllObjectByName(name);
}
function  findAllObjectByName(name){
	 var url = "team/findTeamByName";
	 var params = {'name':name};
	 $.getJSON(url,params,function(result){
		console.log(result);
		setTableBodyRows(result);
		 
	 })
 
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
		//var a;			
		var tds = "<td><input type='checkbox' name='checkId' value="+result[i].id+"></td>"
				+"<td>"+result[i].name+"</td>"
				+"<td>"+result[i].projectId+"</td>" 
				+"<td>"+result[i].createdTime+"</td>"
				+"<td>"+result[i].modifiedTime+"</td>"
				+"<td>"+(result[i].status?"有效":"无效")+"</td>"		
				//+"<td><input type='button' class='btn btn-warning btn-update' value='修改' ></td>";
				//a =$('#tbodyId .btn-update');
				//console.log(a);
				//console.log(result[i].status);
+(result[i].status?"<td><input type='button' class='btn btn-warning btn-update' value='修改' ></td>":
"<td><input type='button' class='btn btn-warning btn-update' value='修改' disabled='disabled'></td>")							
		//将td追加到tr对象
		//var a = $('#tbodyId .btn-update');
		//console.log(a);	
		tr.append(tds);
		//将tr追加到tbody对象
		tBody.append(tr);
	}
}
</script>
 <!-- 表单 -->
	<div class="container">
	   <!-- 页面导航 -->
	   <div class="page-header">
			<div class="page-title" style="padding-bottom: 5px">
				<ol class="breadcrumb">
				  <li class="active">团信息管理</li>
				</ol>
			</div>
			<div class="page-stats"></div>
		</div>
		<form method="post" id="queryFormId">
		    <!-- 查询表单 -->
			<div class="row page-search">
			 <div class="col-md-12">
				<ul class="list-unstyled list-inline">
					<li>
					 <input type="text" id="searchNameId" class="form-control"placeholder="项目名称">
					</li>
					<li class='O1'><button type="button" class="btn btn-primary btn-search" >查询</button></li>
					<li class='O2'><button type="button" class="btn btn-primary btn-add">添加</button></li>
					<li class='O3'><button type="button" class="btn btn-primary btn-invalid">禁用</button></li>
					<li class='O4'><button type="button" class="btn btn-primary btn-valid">启用</button></li>
				</ul>
			  </div>
			</div>
			<!-- 列表显示内容 -->
			<div class="row col-md-12">
				<table class="table table-bordered">
					<thead>
						<tr>
						   <th>选择</th>
							<th>团名称</th>
							<th>所属项目</th>
							<th>开始时间</th>
							<th>结束时间</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<!-- ajax异步获得,并将数据填充到tbody中 -->
					<tbody id="tbodyId">
					</tbody>
				</table>
          <%@include file="../common/page.jsp" %>
			</div>
		</form>
	</div>  