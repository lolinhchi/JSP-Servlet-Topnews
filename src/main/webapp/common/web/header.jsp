<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="header_resize">
	<div class="logo">
		<h1>
			<a href='<c:url value="/trang-chu"/>'><span>topnews</span>.com<br />
				<small>Tin tức nhanh chóng, tin cậy</small>
			</a>
		</h1>
	</div>
	<div class="clr"></div>
	<div class="menu">
		<ul>
			<li><a href='<c:url value="/trang-chu"/>' class="active"><span>Trang
						chủ</span></a></li>
			<c:if test="${not empty USERMODEL }">
				<li><a href="#"><span>${USERMODEL.fullName}</span></a></li>
				<li><a href='<c:url value="/thoat?action=logout"/>'><span>Thoát</span></a></li>
			</c:if>
			<c:if test="${empty USERMODEL }">
				<li><a href='<c:url value="/dang-nhap?action=login"/>'><span>Đăng
							nhập</span></a></li>
			</c:if>
		</ul>
	</div>
	<div class="clr"></div>
</div>