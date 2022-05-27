<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-user"/>
<c:url var ="NewURL" value="/admin-user"/>
<html>
<head>
    <title>Chỉnh sửa người dùng</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <!-- <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script> -->
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Trang chủ</a>
                </li>
                <li class="active">Chỉnh sửa người dùng</li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                        <c:if test="${not empty message}">
									<div class="alert alert-${alert}">
  										${message}
									</div>
						</c:if>
                        <form id="formSubmit">
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Tên tài khoản</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="userName" name="userName" value="${model.userName}"/>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Mật khẩu</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="password" name="password" value="${model.password }"/>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Họ và Tên</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="fullName" name="fullName" value="${model.fullName }"/>
                                </div>
                            </div>
                            <br/>
                            <br/>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right">Trạng thái (1-active, 0-unactive)</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="status" name="status" value="${model.status}" />
								</div>
							</div>
							<br />
							<br />
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right">Phân quyền (1-admin, 2-user)</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="roleId" name="roleId" value="${model.roleId}" />
								</div>
							</div>
							<br />
							<br />
							<div class="form-group">
                                <div class="col-sm-12">
                                    <c:if test="${not empty model.id}">
                                        <input type="button" class="btn btn-white btn-warning btn-bold" value="Cập nhật người dùng" id="btnAddOrUpdateUser"/>
                                    </c:if>
                                    <c:if test="${empty model.id}">
                                        <input type="button" class="btn btn-white btn-warning btn-bold" value="Thêm người dùng" id="btnAddOrUpdateUser"/>
                                    </c:if>
                                </div>
                            </div>
                            <input type="hidden" value="${model.id}" id="id" name="id"/>
                        </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
	$('#btnAddOrUpdateUser').click(function (e){
        //tranh submit nham url hien tai (admin-new?type=edit)
        e.preventDefault();
        // var title = $('#title').val();
        // var categoryCode = $('#categoryCode').val();
        // var shortDescription = $('#shortDescription').val();
        // var content = $('#content').val();
        // var thumbnail = $('#thumbnail').val();
        var data = {};
        //get tat ca gia tri dua theo name
        var formData = $('#formSubmit').serializeArray();
        $.each(formData, function(i, v){
            data[""+v.name+""] = v.value;
        });
		var id = $('#id').val();
		if(id == ""){
			addUser(data);
		}else{
			updateUser(data);
		}
    });
	function addUser(data){
        $.ajax({
        	url: '${APIurl}',
            type: 'POST',
            //client tra ve server
            contentType: 'application/json',
            data: JSON.stringify(data), //convert javascript object sang json
            //server tra len client
            dataType: 'json',
            success: function (result){
            	window.location.href = '${NewURL}?type=edit&id='+result.id+'&message=insert_success';
            },
            error: function (error){
            	window.location.href = '${NewURL}?type=list&maxPageItem=5&page=1&message=error_system';
            }
        });
    }
    function updateUser(data){
        $.ajax({
            url: '$(APIurl)',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result){
            	window.location.href = "${NewURL}?type=edit&id="+result.id+"&message=update_success";
            },
            error: function (error){
            	window.location.href = '${NewURL}?type=list&maxPageItem=5&page=1&message=error_system';
            }
        });
    }
</script>
</body>
</html>
