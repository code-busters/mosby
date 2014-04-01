package main.java.com.mosby.view.web.filters.event;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.mosby.controller.dao.ReflectionDao;
import main.java.com.mosby.controller.services.ReadGenericObjectService;
import main.java.com.mosby.model.Event;
import main.java.com.mosby.model.EventCategory;
import main.java.com.mosby.model.EventType;
import main.java.com.mosby.model.PromoCode;
import main.java.com.mosby.utils.ValidatorUtils;

@WebFilter("/CreatePromoCodeFilter")
public class CreatePromoCodeFilter implements Filter {

	public CreatePromoCodeFilter() {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		if (request.getMethod().equals("POST")) {

			String[] idPromoCodesArray = request
					.getParameterValues("promo_codes_id");
			ValidatorUtils<PromoCode> validatorUtils = null;

			if (!request.getParameter("language").equals("en")
					&& !request.getParameter("language").equals("uk")) {
				validatorUtils = new ValidatorUtils<>(PromoCode.class, "en");
			} else {
				validatorUtils = new ValidatorUtils<>(PromoCode.class,
						request.getParameter("language"));
			}
			if (!(idPromoCodesArray[0].equals(""))) {
				for (String currInt : idPromoCodesArray) {

					int currentId = Integer.parseInt(currInt);
					int discount = 0, maxNumber = 0;
					String code = request.getParameter("promo_code_code_"
							+ currentId);
					try {
						discount = Integer.parseInt(request
								.getParameter("promo_code_discount_"
										+ currentId));
						maxNumber = Integer.parseInt(request
								.getParameter("promo_code_quantity_"
										+ currentId));
					} catch (NumberFormatException e) {
						validatorUtils.inputNumber();
					}
					String promoCodeDescription = request
							.getParameter("promo_code_description_" + currentId);

					PromoCode promoCode = new PromoCode(code, discount,
							promoCodeDescription, maxNumber);
					try {
						validatorUtils.validate(promoCode);
					} catch (NoSuchMethodException | SecurityException
							| IllegalAccessException | IllegalArgumentException
							| InvocationTargetException e) {
						
						e.printStackTrace();
					}
				}
			}

			if (validatorUtils.getErrors().isEmpty() == false) {

				request.setAttribute("errors", validatorUtils.getErrors());
				request.getRequestDispatcher("/pages/createEvent.jsp").forward(
						request, response);
			} else {
				chain.doFilter(request, response);
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
