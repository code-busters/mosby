package main.java.com.mosby.web.filters.event;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.mosby.model.PromoCode;
import main.java.com.mosby.utils.ValidatorUtils;

import org.apache.log4j.Logger;

@WebFilter("/PromoCodeFilter")
public class PromoCodeFilter implements Filter {

	private static Logger log = Logger.getLogger(PromoCodeFilter.class);
	
	public PromoCodeFilter() {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		if (request.getMethod().equals("POST")) {

	        String[] idPromoCodesArray = request.getParameterValues("promo_code_id");
			
			ValidatorUtils<PromoCode> validatorUtils = null;

			if (!request.getParameter("language").equals("en") && !request.getParameter("language").equals("uk")) {
				validatorUtils = new ValidatorUtils<>(PromoCode.class, "en");
			} else {
				validatorUtils = new ValidatorUtils<>(PromoCode.class, request.getParameter("language"));
			}
			if (idPromoCodesArray != null && idPromoCodesArray.length != 0) {
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
						validatorUtils.validate(promoCode, "promoCode", code);
					} catch (NoSuchMethodException | SecurityException
							| IllegalAccessException | IllegalArgumentException
							| InvocationTargetException e) {

						log.error(e);
					}
				}
			}

			if (validatorUtils.getErrors().isEmpty() == false) {

				request.setAttribute("errors", validatorUtils.getErrors());
				request.setAttribute("event_name", request.getParameter("event_name"));
				request.setAttribute("start_date", request.getParameter("start_date"));
				request.setAttribute("start_time", request.getParameter("start_time"));
				request.setAttribute("end_date", request.getParameter("end_date"));
				request.setAttribute("end_time", request.getParameter("end_time"));
				request.setAttribute("event_category", request.getParameter("event_category"));
				request.setAttribute("event_type", request.getParameter("event_type"));
				request.setAttribute("event_description", request.getParameter("event_description"));
				request.setAttribute("event_location", request.getParameter("event_location"));
				request.setAttribute("privacy_event", request.getParameter("privacy_event"));
				request.setAttribute("organizer", request.getParameter("organizer"));
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
