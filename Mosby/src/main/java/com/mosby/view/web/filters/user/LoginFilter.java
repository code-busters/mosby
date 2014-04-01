package main.java.com.mosby.view.web.filters.user;

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
import javax.servlet.http.HttpSession;

@WebFilter("/LoginFilter")
public class LoginFilter implements Filter {

	public LoginFilter() {
		// TODO Auto-generated constructor stub
	}

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);

		if (session.getAttribute("user") == null) {
			chain.doFilter(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/index");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
