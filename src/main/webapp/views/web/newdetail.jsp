<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<div class="body_resize">
	<div class="left">
		<div class="item">${model.content}</div>
	</div>
	<div class="right">
		<h2>Danh mục</h2>
		<ul>
			<c:forEach var='item' items="${categories}">
				<c:url var="theloai" value="/the-loai">
					<c:param name="categoryCode" value="${item.code}" />
				</c:url>
				<li><a href='${theloai}'>${item.name}</a></li>
			</c:forEach>

		</ul>
		<br/>
		<br/>
		<h2>Tin tức khác</h2>
		<c:forEach var="item" items="${modelRelate.listResult}">
			<div class="item">
				<h2>
					<c:url var="chitiet" value="/chi-tiet">
						<c:param name="id" value="${item.id}" />
					</c:url>
					<a href='${chitiet}' title="">${item.title}</a>
				</h2>
				<img src="${item.thumbNail}" alt="" width="210" height="145" />
				<div class="clr"></div>
			</div>
		</c:forEach>

	</div>
	<div class="clr"></div>
</div>
<!-- /.row -->