<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title><dec:title default="Trang chủ" /></title>

    <!-- css -->
    <%-- <link href="<c:url value='/template/web/css/bootstrap.css' />" rel="stylesheet" type="text/css" media="all"/> --%>
    <link href="<c:url value='/template/web/css/style1.css' />" rel="stylesheet" type="text/css"/>
    <%-- <link href="<c:url value='/template/web/css/style.css' />" rel="stylesheet" type="text/css" media="all"/> --%>		
	<script type="text/javascript" src="<c:url value='/template/web/js1/cufon-yui.js' />"></script>
    <script type="text/javascript" src="<c:url value='/template/web/js1/arial.js' />"></script>
    <script type="text/javascript" src="<c:url value='/template/web/js1/cuf_run.js' />"></script>
<!-- 	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<!--     <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
    <script src="<c:url value='/template/paging/jquery.twbsPagination.js' />"></script>

</head>
<body>
<div class="main">
	<!-- header -->
	<div class="header">
    <%@ include file="/common/web/header.jsp" %>
    </div>
    <!-- header -->
    
    
    <div class="body">
    	<dec:body/>
    </div>

	<!-- footer -->
	<div class="footer">
	<%@ include file="/common/web/footer.jsp" %>
	</div>
	<!-- footer -->

</div>
</body>
</html>