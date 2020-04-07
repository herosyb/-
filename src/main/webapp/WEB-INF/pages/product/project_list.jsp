<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>
<%-- <script type="text/javascript" src="${basePath}/tzms/product/project_list.js"></script> --%>
 <script type="text/javascript" src="${basePath}/tzms/common/page.js"></script>
  <script>
//页面加载完成执行函数
 $(function(){
	 //在查询按钮上绑定点击事件
	 $("#queryFormId").on("click",".btn-search",doQueryObject);
	 $("#queryFormId").on("click",".btn-invalid,.btn-valid",updateStatusById);
	 //在添加按钮上绑定点击事件
	  $("#queryFormId").on("click",".btn-add",loadEditPage);
	 //在修改按钮上绑定点击时间
	$("#queryFormId").on("click",".btn-info",loadEditPage);
 	//调用
 	findAllProject();
 });
 function loadEditPage(){
	 //发送显示编辑页面请求
	 var url = "project/editUI";
	 var title;
	 if($(this).hasClass("btn-add")){//添加
		 title = "添加项目信息";
	 }else if($(this).hasClass("btn-info")){
		 title = "修改项目信息";
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
	 //console.log("status="+status);
	 //console.log("ids="+ids);
	 //发起异步请求，更新数据
	 var url = "project/updateStatusById";
	 var params = {"status":status,"ids":ids};
	 $.post(url,params,function(result){
		 if(result.state==1){
			 //成功
			 alert(result.message);
			 //console.log(result);
			 findAllProject();
		 }else{
			 //console.log(result.message);
			 alert(result.message);
		 }
	 });
	 
 }
 
 //点击查询按钮时执行的函数
 function doQueryObject(){
	 //初始化当前页码数据
	 $("pageId").data("pageCurrent",1);
	 //根据条件查询数据
	 findAllProject();
 }
 
 
 //加载项目信息函数
 function findAllProject(){
 	//异步请求
// 	var url = "project/findAllProject";
 	var url = "project/findPageObject";
// 	$.ajax({
// 		url:url,
// 		type:"get",
// 		dateType:"json",
// 		success:function(){
// 		}
// 	});
	var pageCurrent = $("#pageId").data("pageCurrent");
	if(!pageCurrent){//没有获取到当前页数据
		pageCurrent = 1;//默认取出第一页数据
	}
    var params = {"pageCurrent":pageCurrent};//当前页
    params.name = $("#searchNameId").val();
    params.status = $("#searchValidId").val();
    
    //console.log(params);
 	$.getJSON(url,params,function(result){//result已经变成了jsonresult
		//console.log(result);
		//将数据显示在table中的tbody中
		//setTableBodyRows(result.list);//取出map中key为list的值，就是当前页数据
		//设置分页信息
		//setPagination(result.pageObject);//取出map中key为pageObject的值，就是分页数据
		if(result.state==1){
			setTableBodyRows(result.data.list);
			setPagination(result.data.pageObject);
		}else{
			alert(result.message);
		}
 	});
 }
 function setTableBodyRows(result){
	 //1.先获取tbody对象
	 var tbody = $("#tbodyId");
	 tbody.empty();
	 //2.循环数据result
	/*  for(var i=0;i<result.length;i++){
	 } */
	 for(var i in result){
		//3.创建一个tr对象
		var tr = $("<tr></tr>")
		//给tr绑定id值
		tr.data("id",result[i].id);
		// var td = $("<td></td>");
		// td.append(result[i].id);
		 //4.创建每行的td对象（一行有多个）
		  //5.在td对象内部填充查询出来的具体数据 
		var td = "<td><input type='checkbox' name='checkId' value="+result[i].id+"></td>"
				+"<td>"+result[i].code+"</td>"
				+"<td>"+result[i].name+"</td>"
				/* +"<td>"+new Date(result[i].beginDate).toLocaleDateString()+"</td>"
				+"<td>"+new Date(result[i].endDate).toLocaleDateString()+"</td>" */
				+"<td>"+result[i].beginDate+"</td>"
				+"<td>"+result[i].endDate+"</td>"
				+"<td>"+(result[i].status==1?"有效":"无效")+"</td>"
				+"<td><input type='button' value='修改' class='btn btn-info'></td>";
		//6.将创建出来的td添加到tr对象中
		tr.append(td); 
		//7.将创建出来的tr添加tbody中
		tbody.append(tr);
	 }
 }
 </script>
 <!-- 表单 -->
	<div class="container">
	   <!-- 页面导航 -->
	   <div class="page-header">
			<div class="page-title" style="padding-bottom: 5px">
				<ol class="breadcrumb">
				  <li class="active">项目信息管理</li>
				</ol>
			</div>
			<div class="page-stats"></div>
		</div>
		<form method="post" id="queryFormId">
		    <!-- 查询表单 -->
			<div class="row page-search">
			 <div class="col-md-12">
				<ul class="list-unstyled list-inline">
					<li><input type="text" id="searchNameId" class="form-control"placeholder="项目名称"></li>
					<li><select id="searchValidId" class="form-control">
							<option value="">选择状态</option>
							<option value="1">启用</option>
							<option value="0">禁用</option>
					</select></li>
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
							<th>项目编码</th>
							<th>项目名称</th>
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