$(document).ready(function(){
   $("#uploadFormId")
   .on("click",".btn-upload",doUpload)
   .on("click",".btn-down",doDownload)
   findAttachments();
});
//下载函数
function doDownload(){
	//从tr对象中取出id数据
	var id=$(this).parent().parent().data("id");
	var url="attachment/download?id="+id;
	document.location.href=url;
}
function findAttachments(){
	var url="attachment/findAttachments";
	$.getJSON(url,function(result){
		if(result.state==1){
			setTableBodyRows(result.data);
		}else{
			alert(result.message);
		}
	});
}
function setTableBodyRows(list){
	var tBody=$("#tbodyId");tBody.empty();
	for(var i in list){
		var tr=$("<tr></tr>");
		//给tr绑定了id数据
		tr.data("id",list[i].id);
		tr.append("<td>"+list[i].title+"</td>");
		tr.append("<td>"+list[i].fileName+"</td>");
		tr.append("<td>"+list[i].contentType+"</td>");
		tr.append('<td><button type="button" class="btn btn-default btn-down">下载</button></td>')
	    tBody.append(tr);
	}
}

/*点击文件上传按钮执行此函数*/
function doUpload(){
	//异步提交表单($.ajaxSubmit为异步提交表单)
	//使用此函数时需要在页面引入(jquery.form.js )
	$("#uploadFormId").ajaxSubmit({
		type:"post",
		url:"attachment/upload",
		dataType:"json",
		success:function(result){
			alert(result.message);
			findAttachments();
		}
	});
	//$("#uploadFormId").resetForm();
	return false;//防止表单重复提交的一种方式
}
