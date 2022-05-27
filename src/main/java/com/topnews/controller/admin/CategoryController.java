package com.topnews.controller.admin;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.topnews.constant.SystemConstant;
import com.topnews.model.CategoryModel;
import com.topnews.service.ICategoryService;
import com.topnews.utils.FormUtils;

@WebServlet(urlPatterns = {"/admin-category"})
public class CategoryController extends HttpServlet{
	
	private static final long serialVersionUID = -2569117786200532290L;
	
	
	@Inject
	private ICategoryService categoryService;
	ResourceBundle resourceBundle = ResourceBundle.getBundle("message");
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategoryModel model = FormUtils.toModel(CategoryModel.class, request);
		String view = "";
		if(model.getType().equals(SystemConstant.LIST)) {
			model.setListResult(categoryService.findAll());
			view ="/views/admin/category/list.jsp";
			
		}else if(model.getType().equals(SystemConstant.EDIT)){
			if(model.getId() != null) {
				model = categoryService.findOne(model.getId());
			}
			view = "/views/admin/category/edit.jsp";
		}
		if(request.getParameter("message") != null) {
			String message = request.getParameter("message");
			String alert = "";
			if(message.equals("insert_success")) {
				alert = "success";
			}
			if(message.equals("update_success")) {
				alert = "success";
			}
			if(message.equals("delete_success")) {
				alert = "success";
			}
			if(message.equals("error_system")) {
				alert = "danger";
			}
			request.setAttribute("message", resourceBundle.getString(message));
			request.setAttribute("alert", alert);
			
		}
		request.setAttribute(SystemConstant.MODEL, model);
		request.setAttribute("categories", categoryService.findAll());
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
