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
import com.topnews.model.NewsModel;
import com.topnews.paging.PageRequest;
import com.topnews.paging.Pageble;
import com.topnews.service.ICategoryService;
import com.topnews.service.INewService;
import com.topnews.sort.Sorter;
import com.topnews.utils.FormUtils;

@WebServlet(urlPatterns = {"/admin-new"})
public class NewController extends HttpServlet{
	
	private static final long serialVersionUID = -2569117786200532290L;
	
	@Inject
	private INewService newService;
	
	@Inject
	private ICategoryService categoryService;
	ResourceBundle resourceBundle = ResourceBundle.getBundle("message");
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NewsModel model = FormUtils.toModel(NewsModel.class, request);
		String view = "";
		/*String pageStr = request.getParameter("page");
		String maxPageItemStr = request.getParameter("maxPageItem");
		if(pageStr != null) {
			model.setPage(Integer.parseInt(pageStr));
		}else {
			model.setPage(1);
		}
		if(maxPageItemStr != null) {
			model.setMaxPageItem(Integer.parseInt(maxPageItemStr));
		}*/
		/*Integer offset = (model.getPage() - 1)* model.getMaxPageItem();*/
		if(model.getType().equals(SystemConstant.LIST)) {
			Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(),
					new Sorter(model.getSortName(), model.getSortBy()));
			model.setListResult(newService.findAll(pageble));
			model.setTotalItem(newService.getTotalItem());
			model.setTotalPage((int) Math.ceil((double) model.getTotalItem()/model.getMaxPageItem()) );
			view ="/views/admin/new/list.jsp";
			
		}else if(model.getType().equals(SystemConstant.EDIT)){
			if(model.getId() != null) {
				model = newService.findOne(model.getId());
			}
			request.setAttribute("categories", categoryService.findAll());
			view = "/views/admin/new/edit.jsp";
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
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
