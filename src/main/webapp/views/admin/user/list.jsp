<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:url var="APIurl" value="/api-admin-user"/>
<c:url var ="NewURL" value="/admin-user"/>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Danh sách người dùng</title>
</head>

<body>
	<div class="main-content">
		<form action="<c:url value='/admin-user'/>" id="formSubmit"
			method="get">

			<div class="main-content-inner">
				<div class="breadcrumbs ace-save-state" id="breadcrumbs">
					<ul class="breadcrumb">
						<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Trang
								chủ</a></li>
					</ul>
					<!-- /.breadcrumb -->
				</div>
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<c:if test="${not empty message}">
								<div class="alert alert-${alert}">${message}</div>
							</c:if>
							<div class="widget-box table-filter">
								<div class="table-btn-controls">
									<div class="pull-right tableTools-container">
										<div class="dt-buttons btn-overlap btn-group">
											<a flag="info"
												class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
												data-toggle="tooltip" title='Thêm người dùng'
												href='<c:url value="/admin-user?type=edit"/>'> <span>
													<i class="fa fa-plus-circle bigger-110 purple"></i>
											</span>
											</a>
											<button id="btnDelete" type="button"
												class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
												data-toggle="tooltip" title='Xóa người dùng'>
												<span> <i class="fa fa-trash-o bigger-110 pink"></i>
												</span>
											</button>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">							
									<div class="table-responsive">
										<table class="table table-bordered">
											<thead>
												<tr>
													<th><input type="checkbox" id="checkAll" /></th>
													<th>Tên tài khoản</th>
													<th>Mật khẩu</th>
													<th>Thao tác</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="item" items="${model.listResult}">
													<tr>
														<td><input type="checkbox" id="checkbox_${item.id}" value="${item.id}" /></td>
														<td>${item.userName}</td>
														<td>${item.password}</td>
														<td>
																<c:url var="editURL" value="/admin-user">
																	<c:param name="type" value="edit"/>
																	<c:param name="id" value="${item.id}"/>
																</c:url>
																<a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
																   title="Cập nhật người dùng" href='${editURL}'><i class="fa fa-pencil-square-o" aria-hidden="true"></i>
																</a>
															</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
										<ul class="pagination" id="pagination"></ul>
										<input type="hidden" value="" id="page" name="page" /> 
										<input type="hidden" value="" id="maxPageItem" name="maxPageItem" />
										<input type="hidden" value="" id="sortName" name="sortName" /> 
										<input type="hidden" value="" id="sortBy" name="sortBy" /> 
										<input type="hidden" value="" id="type" name="type" /> 
									</div>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>

		</form>
	</div>
	<!-- /.main-content -->
	<script type="text/javascript">
		var totalPages = ${model.totalPage};
		var currentPage = ${model.page};
		var limit = 5;
		$(function() {
			window.pagObj = $('#pagination').twbsPagination({
				totalPages : totalPages,
				visiblePages : 3,
				startPage : currentPage,
				onPageClick : function(event, page) {
					//console.info(page + ' (from options)');
					if (currentPage != page) {
						$('#maxPageItem').val(limit);
						$('#page').val(page);
						$('#sortName').val('username');
						$('#sortBy').val('asc');
						$('#type').val('list');
						$('#formSubmit').submit();
					}

				}
			});
		});
		$('#btnDelete').click(function(){
			var data = {};
			var ids = $('tbody input[type=checkbox]:checked').map(function () {
		            return $(this).val();
		        }).get();
				data['ids'] = ids;
				deleteNew(data);
		});

		function deleteNew(data){
        $.ajax({
        	url: '${APIurl}',
            type: 'DELETE',
            //client tra ve server
            contentType: 'application/json',
            data: JSON.stringify(data), //convert javascript object sang json
            success: function (result){
            	window.location.href = '${NewURL}?type=list&maxPageItem=5&page=1&message=delete_success';
            },
            error: function (error){
            	window.location.href = '${NewURL}?type=list&maxPageItem=5&page=1&message=error_system';
            }
        });
    }
	</script>
</body>

</html>