//页面加载完成执行函数
 $(function(){
	 //在查询按钮上绑定点击事件
	 $("#queryFormId").on("click",".btn-search",doQueryObject);
	 
 	//调用
 	findAllProject();
 });
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

 	$.getJSON(url,params,function(result){
		//console.log(result);
		//将数据显示在table中的tbody中
		setTableBodyRows(result.list);//取出map中key为list的值，就是当前页数据
		//设置分页信息
		setPagination(result.pageObject);//取出map中key为pageObject的值，就是分页数据
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