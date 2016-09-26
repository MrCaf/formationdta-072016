package fr.pizzeria.admin.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebFilter(urlPatterns = { "/*" }, description = "Login filter")
public class LoginFilter implements Filter {

	private FilterConfig config = null;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;
		config.getServletContext().log("LoginFilter initialized");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httprResponse = (HttpServletResponse) response;
		
		if(httpRequest.getSession(false) != null){
			if(httpRequest.getSession().getAttribute("userOk") != null || httpRequest.getRequestURL().toString().contains("/login")){
				chain.doFilter(request, response);
			}
			else{
				httprResponse.sendRedirect(httpRequest.getContextPath() + "/login?url=" + httpRequest.getServletPath());
			}
		}
		else{
			if(httpRequest.getRequestURL().toString().contains("/login")){
				chain.doFilter(request, response);
			}
			else{
				httprResponse.sendRedirect(httpRequest.getContextPath() + "/login?url=" + httpRequest.getServletPath());
			}
			
		}
	}

	@Override
	public void destroy() {
		
	}

}
