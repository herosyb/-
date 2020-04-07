$(document).ready(function(){
	//在pageId对应的对象相关元素上，绑定点击事件
	$("#pageId").on('click','.pre,.next,.first,.last',jumpToPage);
});
//设置分页
function setPagination(pageObject){
 //1.初始化总页数
 $(".pageCount").html("总页数("+pageObject.pageCount+")");
 //2.初始化当前页的页码
 $(".pageCurrent").html("当前页("+pageObject.pageCurrent+")");
 //3.在pageId对应的对象上绑定总页数
 //data函数用于以key/value的方式在对象上绑定数据
 $("#pageId").data("pageCount",pageObject.pageCount);
 //4.在pageId对象的对象上绑定当前页面值
 $("#pageId").data("pageCurrent",pageObject.pageCurrent);
}

//定义一个函数,通过此函数实现页面的跳转
function jumpToPage(){
	//获得点击对象上class属性对应的值,根据此值
	//判定具体点击的是哪个对象(例如上一页,下一页)
	var clazz=$(this).attr("class");//pre
	//获得pageId对象上绑定的pageCurrent对应的值
	var pageCurrent=$('#pageId').data("pageCurrent");//2
	//获得pageId对象上绑定的pageCount对应的值
	var pageCount=$('#pageId').data("pageCount")//3
	//根据class属性的值判断点击的是否是上一页
	//点击上一页  当前页：2  1
	if(clazz=='pre'&&pageCurrent>1){
		pageCurrent--;//1
	}
	//判断点击的是否是下一页
	if(clazz=="next"&&pageCurrent<pageCount){
		pageCurrent++;//3
	}
	//判断点击的对象是否是首页
	if(clazz=="first"){
		pageCurrent=1;//1
	}
	//判定点击的对象是否是尾页
	if(clazz=="last"){
		pageCurrent=pageCount;//3
	}
	//重写绑定pageCurrent的值,
	$('#pageId').data("pageCurrent",pageCurrent);
	//重新执行查询操作(根据pageCurrent的值)
	findAllProject();
}









