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
<script type="text/javascript" src="js/pages/dept/dept_list.js"></script>
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
							<h3 class="box-title"><strong>办公用品购买清单</strong></h3>
						</div>
						<!-- /.box-header -->
						<div class="box-body table-responsive no-padding">
							<table class="table table-hover">
								<tr>
									<th style="width:60%">申请标题</th>
									<th style="width:20%">申请日期</th>
									<th style="width:10%">花费总额</th>
									<th style="width:10%">状态</th>
								</tr>
								<c:forEach items="${allPurchases}" var="purchase">
									<tr>
										<td><a href="pages/purchase/show5.action?pid=${purchase.pid}">${purchase.title}</a></td>
										<td><fmt:formatDate value="${purchase.pubdate}" dateStyle="default" type="both"/></td>
										<td>￥${purchase.total}</td>
										<td>
											<c:if test="${purchase.status == 0}">
												<span class="text-warning">等待审核</span>
											</c:if>
											<c:if test="${purchase.status == 1}">
												<span class="text-success">审核通过</span>
											</c:if>
											<c:if test="${purchase.status == 2}">
												<span class="text-danager">审核拒绝</span>
											</c:if>
										</td>
									</tr>
								</c:forEach>
							</table>
						</div>
						<!-- /.box-body -->
						<jsp:include page="/pages/plugins/split_page_plugin_bar.jsp"/>
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
