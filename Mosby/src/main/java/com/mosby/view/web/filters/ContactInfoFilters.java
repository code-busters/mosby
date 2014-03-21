package main.java.com.mosby.view.web.filters;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import javax.servlet.http.Part;

import main.java.com.mosby.controller.dao.ReflectionDao;
import main.java.com.mosby.model.User;
import main.java.com.mosby.utils.FileUploadUtils;
import main.java.com.mosby.utils.ValidatorUtils;

@WebFilter("/ContactInfoFilters")
public class ContactInfoFilters implements Filter {

	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final String USER_IMAGE_PATH = "media\\images\\users";

	public ContactInfoFilters() {
		// TODO Auto-generated constructor stub
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);

		if (session.getAttribute("user") != null) {
			if (request.getMethod().equals("POST")) {
				System.out.println("validate....");
				ReflectionDao<User> usersDao = new ReflectionDao<>(
						(Class<User>) User.class);

				User sessionUser = (User) session.getAttribute("user");

				int id = sessionUser.getId();
				String firstName = request.getParameter("first_name");
				String lastName = request.getParameter("last_name");
				String email = sessionUser.getEmail();
				String password = sessionUser.getPassword();
				System.out.println(password);
				double credits = sessionUser.getCredits();
				boolean admin = sessionUser.isAdmin();
				String userImage = sessionUser.getImage();
				String country = request.getParameter("country");
				String city = request.getParameter("city");
				Date birthDate = null;
				if (request.getParameter("birthday") != null) {
					try {
						birthDate = new SimpleDateFormat(DATE_FORMAT)
								.parse(request.getParameter("birthday"));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				String site = request.getParameter("website");
				String about = request.getParameter("about");
				String authenticationCode = sessionUser.getAuthenticationCode();
				boolean active = sessionUser.isActive();

				User user = new User(id, firstName, lastName, email, password, "",
						credits, admin, userImage, country, city, birthDate,
						site, about, authenticationCode, active);
				ValidatorUtils<User> validatorUtils = new ValidatorUtils<>(
						(Class<User>) user.getClass(), "en");
				try {
					user = validatorUtils.validate(user);
				} catch (NoSuchMethodException | SecurityException
						| IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(validatorUtils.getErrors());
				if (user == null
						|| validatorUtils.getErrors().isEmpty() == false) {

					request.setAttribute("errors", validatorUtils.getErrors());
					System.out.println(validatorUtils.getErrors());
					request.getRequestDispatcher("/pages/signUp.jsp").forward(
							request, response);
				} else {
					chain.doFilter(request, response);
				}
			} else if (request.getMethod().equals("GET")) {
				chain.doFilter(request, response);
			} else {
				request.getRequestDispatcher("/pages/signUp.jsp").forward(
						request, response);
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/index");
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
