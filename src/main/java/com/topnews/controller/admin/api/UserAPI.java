package com.topnews.controller.admin.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.topnews.model.UserModel;
import com.topnews.service.IUserService;
import com.topnews.utils.HttpUtil;
import com.topnews.utils.SessionUtils;

@WebServlet(urlPatterns = {"/api-admin-user"})
public class UserAPI extends HttpServlet{

	private static final long serialVersionUID = 2286520892580118783L;
	@Inject
	private IUserService userService;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");//set Tiếng Việt cho data(json) gởi về từ client
		response.setContentType("application/json");//set kiểu json cho data respone từ server lên client 
		//convert từ data json sang news model
		UserModel userModel = HttpUtil.of(request.getReader()).toModel(UserModel.class);
		userModel.setCreatedBy(((UserModel) SessionUtils.getInstance().getValue(request, "USERMODEL")).getUserName());
		userModel = userService.save(userModel);
//		System.out.println(newModel);
		mapper.writeValue(response.getOutputStream(), userModel);
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");//set Tiếng Việt cho data(json) gởi về từ client
		response.setContentType("application/json");//set kiểu json cho data respone từ server lên client 
		UserModel updateUser = HttpUtil.of(request.getReader()).toModel(UserModel.class);
		updateUser.setModifiedBy(((UserModel) SessionUtils.getInstance().getValue(request, "USERMODEL")).getUserName());
		updateUser = userService.update(updateUser);
		mapper.writeValue(response.getOutputStream(), updateUser);
	}
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");//set Tiếng Việt cho data(json) gởi về từ client
		response.setContentType("application/json");//set kiểu json cho data respone từ server lên client 
		UserModel userModel = HttpUtil.of(request.getReader()).toModel(UserModel.class);
		userService.delete(userModel.getIds());
		mapper.writeValue(response.getOutputStream(), "{}");
	}
	
}
