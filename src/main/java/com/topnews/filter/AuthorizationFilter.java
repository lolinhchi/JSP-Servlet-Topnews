package com.topnews.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.topnews.constant.SystemConstant;
import com.topnews.model.UserModel;
import com.topnews.utils.SessionUtils;

public class AuthorizationFilter implements Filter {

	private ServletContext context;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.context = filterConfig.getServletContext();
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse respone = (HttpServletResponse) servletResponse;
		String url = request.getRequestURI();
		if(url.startsWith("/admin")) {
			UserModel model = (UserModel) SessionUtils.getInstance().getValue(request, "USERMODEL");
			if(model != null) {
				if( model.getRole().getCode().equals(SystemConstant.ADMIN_ROLE)) {
					filterChain.doFilter(servletRequest, servletResponse);
				}else if(model.getRole().getCode().equals(SystemConstant.USER_ROLE)) {
					respone.sendRedirect(request.getContextPath()+"/dang-nhap?action=login&message=not_permission&alert=danger");
				}
			}else {
				respone.sendRedirect(request.getContextPath()+"/dang-nhap?action=login&message=not_login&alert=danger");
			}
		}else {
			filterChain.doFilter(servletRequest, servletResponse);
		}
	}

	@Override
	public void destroy() {
		
	}

}
