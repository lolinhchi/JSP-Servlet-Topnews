package com.topnews.controller.admin.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.topnews.model.NewsModel;
import com.topnews.model.UserModel;
import com.topnews.service.INewService;
import com.topnews.utils.HttpUtil;
import com.topnews.utils.SessionUtils;

@WebServlet(urlPatterns = {"/api-admin-news"})
public class NewsAPI extends HttpServlet{

	private static final long serialVersionUID = 2286520892580118783L;
	@Inject
	private INewService newService;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");//set Tiếng Việt cho data(json) gởi về từ client
		response.setContentType("application/json");//set kiểu json cho data respone từ server lên client 
		//convert từ data json sang news model
		NewsModel newModel = HttpUtil.of(request.getReader()).toModel(NewsModel.class);
		newModel.setCreatedBy(((UserModel) SessionUtils.getInstance().getValue(request, "USERMODEL")).getUserName());
		newModel = newService.save(newModel);
//		System.out.println(newModel);
		mapper.writeValue(response.getOutputStream(), newModel);
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");//set Tiếng Việt cho data(json) gởi về từ client
		response.setContentType("application/json");//set kiểu json cho data respone từ server lên client 
		NewsModel updateNew = HttpUtil.of(request.getReader()).toModel(NewsModel.class);
		updateNew.setModifiedBy(((UserModel) SessionUtils.getInstance().getValue(request, "USERMODEL")).getUserName());
		updateNew = newService.update(updateNew);
		mapper.writeValue(response.getOutputStream(), updateNew);
	}
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");//set Tiếng Việt cho data(json) gởi về từ client
		response.setContentType("application/json");//set kiểu json cho data respone từ server lên client 
		NewsModel newModel = HttpUtil.of(request.getReader()).toModel(NewsModel.class);
		newService.delete(newModel.getIds());
		mapper.writeValue(response.getOutputStream(), "{}");
	}
	
}
