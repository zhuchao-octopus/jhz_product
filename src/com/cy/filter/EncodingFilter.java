package com.cy.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 过滤
 * @author zhucy
 *
 */
public class EncodingFilter implements Filter{
	
	private FilterConfig config;
	private String encoding ="";
	
	public void destroy() {
		
	}
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		chain.doFilter(request, response);
		
		response.setContentType("text/html;charset=utf-8");
		
		
	}
	public void init(FilterConfig filterConfig) throws ServletException {
		this.config =filterConfig;
		String s =filterConfig.getInitParameter("encoding");
		if(s!=null){
			this.encoding=s;
		}
		
	}

}
