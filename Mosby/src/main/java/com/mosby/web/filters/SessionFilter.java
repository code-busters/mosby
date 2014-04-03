package main.java.com.mosby.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/SessionFilter")
public class SessionFilter implements Filter {

	public SessionFilter() {
		
	}

	public void destroy() {
		
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);

		if (request.getMethod().equals("GET")) {
			if (session == null) {
				session = request.getSession();
			}
		}
		chain.doFilter(request, response);

	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
