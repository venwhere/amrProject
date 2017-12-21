<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<jsp:include page="/pages/plugins/include_javascript_head.jsp" />
<script type="text/javascript" src="js/pages/res/res_list.js"></script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<!-- 导入头部标题栏内容 -->
		<jsp:include page="/pages/plugins/include_title_head.jsp" />
		<!-- 导入左边菜单项 -->
		<jsp:include page="/pages/plugins/include_menu_item.jsp" />
		<div class="content-wrapper">
			<!-- 此处编写需要显示的页面 -->
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<div class="box-header">
							<h3 class="box-title"><strong>办公用品列表</strong></h3>
						</div>
						<jsp:include page="/pages/plugins/split_page_plugin_search.jsp"/>
						<!-- /.box-header -->
						<div class="box-body table-responsive no-padding">
							<table class="table table-hover">
								<tr>
									<th>名称</th>
									<th>购入时间</th>
									<th>库存量</th>
									<th>状态</th>
									<th>是否需要返还</th>
									<th>操作</th>
								</tr>
								<c:forEach items="${allRess}" var="res">
									<tr>
										<td><img src="upload/res/${res.photo}" class="img" style="width:30px;"> ${res.title}</td>
										<td><fmt:formatDate value="${res.indate}"/></td>
										<td>${res.amount}</td>
										<td>${res.amount > 0 ? "可领取" : "不可领取"}</td>
										<td>${res.rflag == 1? "需要归还" : "不需要归还"}</td>
										<td>
											<button id="addBut-${res.rid}" class="btn btn-primary btn-xs">加入领取清单</button>
											<c:if test="${emp.dept.did == 3}">
												<button id="appendBut-${res.rid}" class="btn btn-warning btn-xs">追加购入</button>
											</c:if>
										</td>
									</tr>
								</c:forEach>
							</table>
						</div>
						<!-- /.box-body -->
						<jsp:include page="/pages/plugins/split_page_plugin_bar.jsp"/>
						<jsp:include page="/pages/plugins/include_alert.jsp"/>
					</div>
					<!-- /.box -->
				</div>
			</div>
		</div>
		<!-- 导入公司尾部认证信息 -->
		<jsp:include page="/pages/plugins/include_title_foot.jsp" />
		<!-- 导入右边工具设置栏 -->
		<jsp:include page="/pages/plugins/include_menu_sidebar.jsp" />
		<div class="control-sidebar-bg"></div>
	</div>
	<jsp:include page="/pages/plugins/include_javascript_foot.jsp" />
</body>
</html>
