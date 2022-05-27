package com.topnews.controller.web;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.topnews.model.UserModel;
import com.topnews.service.ICategoryService;
import com.topnews.service.INewService;
import com.topnews.service.IUserService;
import com.topnews.utils.FormUtils;
import com.topnews.utils.SessionUtils;

@WebServlet(urlPatterns = {"/trang-chu", "/dang-nhap", "/thoat"})
public class HomeController extends HttpServlet {
	
	private static final long serialVersionUID = -3443343345690668692L;
	@Inject
	private ICategoryService categoryService;
	
	@Inject
	private IUserService userService;
	
	@Inject
	private INewService newService;
	
	ResourceBundle resourceBundle = ResourceBundle.getBundle("message");
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
/*		String idNew = request.getParameter("id");*/
		if(action != null && action.equals("login")) {
			String message = request.getParameter("message");
			String alert = request.getParameter("alert");
			if(message != null && alert != null) {
				request.setAttribute("message", resourceBundle.getString(message) );
				request.setAttribute("alert", alert);
			}
			RequestDispatcher rd = request.getRequestDispatcher("/views/login.jsp");
			rd.forward(request, response);
		} else if (action != null && action.equals("logout")) {
			SessionUtils.getInstance().removeValue(request, "USERMODEL");
			//redirect đến controller
			response.sendRedirect(request.getContextPath()+ "/trang-chu");
		}
		/*else if(idNew != null) {
			
			RequestDispatcher rd = request.getRequestDispatcher("/views/web/newdetail.jsp");
			rd.forward(request, response);
		}*/
		else {
			request.setAttribute("news", newService.findTop(10));
			request.setAttribute("categories", categoryService.findAll());
			RequestDispatcher rd = request.getRequestDispatcher("/views/web/home.jsp");
			rd.forward(request, response);
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null && action.equals("login")) {
			UserModel model = FormUtils.toModel(UserModel.class, request);
			model = userService.findByUserNameAndPasswordAndStatus(model.getUserName(), model.getPassword(), 1);
			if (model != null) {
				SessionUtils.getInstance().putValue(request, "USERMODEL", model);
				if (model.getRole().getCode().equals("USER")) {
					response.sendRedirect(request.getContextPath()+"/trang-chu");
				} else if (model.getRole().getCode().equals("ADMIN")) {
					response.sendRedirect(request.getContextPath()+"/admin-home");
				}
			} else {
				response.sendRedirect(request.getContextPath()+"/dang-nhap?action=login&message=user_pass_invalid&alert=danger");
			}
		}
	}
}
