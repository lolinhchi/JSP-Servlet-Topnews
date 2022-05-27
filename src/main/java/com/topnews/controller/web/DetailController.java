package com.topnews.controller.web;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.topnews.constant.SystemConstant;
import com.topnews.model.NewsModel;
import com.topnews.service.ICategoryService;
import com.topnews.service.INewService;
import com.topnews.utils.FormUtils;

@WebServlet(urlPatterns = {"/chi-tiet"})
public class DetailController extends HttpServlet {

	private static final long serialVersionUID = 6019275745338163137L;

	@Inject
	private INewService newService;
	@Inject
	private ICategoryService categoryService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		NewsModel model = FormUtils.toModel(NewsModel.class, request);
		String id = request.getParameter("id");
		if(id != null) {
			model.setId((long) Integer.parseInt(id));
		}
		model = newService.findOne(model.getId());
		NewsModel modelRelate = new NewsModel();
		modelRelate.setListResult(newService.findTop(2));
		request.setAttribute(SystemConstant.MODEL, model);
		request.setAttribute("categories", categoryService.findAll());
		request.setAttribute("modelRelate", modelRelate);
		RequestDispatcher rd = request.getRequestDispatcher("/views/web/newdetail.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
