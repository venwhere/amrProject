<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<script type="text/javascript" src="js/pages/res/res_audit.js"></script> 
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
							<h3 class="box-title"><strong>员工领取记录</strong></h3>
						</div>
						<!-- /.box-header -->
						<div class="box-body table-responsive no-padding">
							<table class="table table-hover">
								<tr>
									<th class="text-center"><strong>商品名称</strong></th>
									<th class="text-center"><strong>领取日期</strong></th>
									<th class="text-center"><strong>申请个数</strong></th>
									<th class="text-center"><strong>库存量</strong></th>
									<th class="text-center"><strong>状态</strong></th>
									<th class="text-center"><strong>是否归还？</strong></th>
									<th class="text-center"><strong>操作</strong></th>
								</tr>
								<c:forEach items="${allTakes}" var="take">
									<tr>
										<td class="text-center"><img src="upload/res/${resMap[take.res.rid].photo}" class="img" style="width:30px;"> ${resMap[take.res.rid].title}</a>
										</td>
										<td class="text-center"><fmt:formatDate value="${take.gdate}"/></td>
										<td class="text-center">${take.amount }</td>
										<td class="text-center">${resMap[take.res.rid].amount}</td>
										<td class="text-center"><span id="audit-${take.tkid}">
											<c:if test="${take.status == 0}">
												<span class="text-warning">等待审核</span>
											</c:if>
											<c:if test="${take.status == 1}">
												<span class="text-success">审核通过</span>
											</c:if>
											<c:if test="${take.status == 2}">
												<span class="text-danger">审核拒绝</span>
											</c:if>
											<c:if test="${take.status == 3}">
												<span class="text-info">等待归还</span>
											</c:if>
											<c:if test="${take.status == 4}">
												<span class="text-primary">已归还</span>
											</c:if>
											</span>
										</td>
										<td class="text-center">
											<c:if test="${resMap[take.res.rid].rflag == 1}">
												<c:if test="${take.rdate == null}">
													<span class="text-danger">未归还</span>												
												</c:if>
												<c:if test="${take.rdate != null}">
													<span class="text-info">已归还</span>												
												</c:if>
											</c:if>
											<c:if test="${resMap[take.res.rid].rflag == 0}">
												<span class="text-info">不需要归还</span>
											</c:if>
										</td>
										<td class="text-center">
											<span id="statusDiv-${take.tkid}">
												<c:if test="${take.status==0}">
													<button class="btn btn-primary btn-xs" id="passBut-${take.tkid}">审核通过</button>
													<button class="btn btn-danger btn-xs" id="refBut-${take.tkid}">审核拒绝</button>
												</c:if>
												<c:if test="${take.status == 3}">
													<button class="btn btn-success btn-xs" id="getBut-${take.tkid}">用品接收</button>
												</c:if>
											</span>
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
