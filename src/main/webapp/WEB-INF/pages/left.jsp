<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- /.search form -->
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu">
      <li class="treeview">
       <c:forEach var="menus" items="${currentUser.menus}">
          <c:if test="${empty menus.parentId }">
        	  <a href="#">
            	<i class="fa fa-files-o"></i>
            		 <span>${menus.name}</span>
           			 <span class="pull-right-container">
             			 <i class="fa fa-angle-left pull-right"></i>
            		</span>
         	 </a>
          </c:if>
          <ul class="treeview-menu">
              <c:if test="${not empty menus.parentId}"> 
            		   <li><a id="${menus.url}" ><i class="fa fa-circle-o"></i>${menus.name}</a></li>
       		  </c:if>
          </ul>
         </c:forEach> 
        </li>  
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>
<script type="text/javascript">

$(".treeview-menu a").click(function(){
	var url = $(this).attr("id");
	$(".content").load(url);
});

/* $('#load-project-id').click(function(){
	var url="project/listUI?t="+Math.random(1000);
	$(".content").load(url);//发送请求后台资源地址，会把响应到的数据放到class=content元素内容中
})
$('#load-team-id').click(function(){
	var url="team/listUI?t="+Math.random(1000);
	$(".content").load(url);
})
$('#load-type-id').click(function(){
	var url="productType/listUI?t="+Math.random(1000);
	$(".content").load(url);
})
$('#load-product-id').click(function(){
	var url="product/listUI?t="+Math.random(1000);
	$(".content").load(url);
})
$('#load-attachment-id').click(function(){
	var url="attachment/listUI?t="+Math.random(1000);
	$(".content").load(url);
})
$('#load-role-id').click(function(){
	var url="role/listUI?t="+Math.random(1000);
	$(".content").load(url);
})
$('#load-menu-id').click(function(){
	var url="menu/listUI?t="+Math.random(1000);
	$(".content").load(url);
})
$('#load-user-id').click(function(){
	var url="user/listUI?t="+Math.random(1000);
	$(".content").load(url);
}) */
</script>