package com.topnews.controller.admin.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.topnews.model.CategoryModel;
import com.topnews.model.UserModel;
import com.topnews.service.ICategoryService;
import com.topnews.utils.HttpUtil;
import com.topnews.utils.SessionUtils;

@WebServlet(urlPatterns = {"/api-admin-category"})
public class CategoryAPI extends HttpServlet{

	private static final long serialVersionUID = 2286520892580118783L;
	@Inject
	private ICategoryService categoryService;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");//set Tiếng Việt cho data(json) gởi về từ client
		response.setContentType("application/json");//set kiểu json cho data respone từ server lên client 
		//convert từ data json sang news model
		CategoryModel categoryModel = HttpUtil.of(request.getReader()).toModel(CategoryModel.class);
		categoryModel.setCreatedBy(((UserModel) SessionUtils.getInstance().getValue(request, "USERMODEL")).getUserName());
		categoryModel = categoryService.save(categoryModel);
//		System.out.println(newModel);
		mapper.writeValue(response.getOutputStream(), categoryModel);
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");//set Tiếng Việt cho data(json) gởi về từ client
		response.setContentType("application/json");//set kiểu json cho data respone từ server lên client 
		CategoryModel updateCategory= HttpUtil.of(request.getReader()).toModel(CategoryModel.class);
		updateCategory.setModifiedBy(((UserModel) SessionUtils.getInstance().getValue(request, "USERMODEL")).getUserName());
		updateCategory = categoryService.update(updateCategory);
		mapper.writeValue(response.getOutputStream(), updateCategory);
	}
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");//set Tiếng Việt cho data(json) gởi về từ client
		response.setContentType("application/json");//set kiểu json cho data respone từ server lên client 
		CategoryModel categoryModel = HttpUtil.of(request.getReader()).toModel(CategoryModel.class);
		categoryService.delete(categoryModel.getIds());
		mapper.writeValue(response.getOutputStream(), "{}");
	}
	
}
