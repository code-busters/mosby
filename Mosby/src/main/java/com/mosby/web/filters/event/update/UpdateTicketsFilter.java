package main.java.com.mosby.web.filters.event.update;

import main.java.com.mosby.model.Event;
import main.java.com.mosby.model.TicketInfo;
import main.java.com.mosby.utils.ValidatorUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebFilter("/UpdateTicketsFilter")
public class UpdateTicketsFilter implements Filter {

	public UpdateTicketsFilter() {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		if (request.getMethod().equals("POST")) {

			String[] idTicketsArray = request.getParameterValues("ticket_id");
			TicketInfo ticketInfo = null;
			ValidatorUtils<TicketInfo> validatorUtils = null;

			if (!request.getParameter("language").equals("en")
					&& !request.getParameter("language").equals("uk")) {
				validatorUtils = new ValidatorUtils<>(TicketInfo.class, "en");
			} else {
				validatorUtils = new ValidatorUtils<>(TicketInfo.class,
						request.getParameter("language"));
			}

			if (idTicketsArray != null && idTicketsArray.length != 0) {

				for (String currInt : idTicketsArray) {
					int currentId = Integer.parseInt(currInt);
					String type;
					String ticketInfoName = request
							.getParameter("event_ticket_name_" + currentId);
					String ticketDescription = request
							.getParameter("ticket_description_" + currentId);
					int maxNumber = 1;
					try {
						maxNumber = Integer.parseInt(request
								.getParameter("event_ticket_quantity_"
										+ currentId));
					} catch (NumberFormatException e) {
						validatorUtils.inputNumber();
					}
					String stringPrice = request
							.getParameter("event_ticket_price_" + currentId);

					SimpleDateFormat parseDate = new SimpleDateFormat(
							"yyyy-MM-dd hh:mm");
					String startTimestamp = request
							.getParameter("ticket_start_date_" + currentId)
							+ " "
							+ request.getParameter("ticket_start_time_"
									+ currentId);
					String endTimestamp = request
							.getParameter("ticket_end_date_" + currentId)
							+ " "
							+ request.getParameter("ticket_end_time_"
									+ currentId);
					Timestamp start = null;
					Timestamp end = null;
					try {
						start = new Timestamp(parseDate.parse(startTimestamp)
								.getTime());
						end = new Timestamp(parseDate.parse(endTimestamp)
								.getTime());
					} catch (ParseException e) {}
					int price = 0;
					if (stringPrice.equals("Free")) {
						type = "Free";
						price = 0;
					} else if (stringPrice.equals("Donation")) {
						type = "Donation";
						price = 0;
					} else {
						type = "Paid";
						try {
							price = Integer.parseInt(request
									.getParameter("event_ticket_price_"
											+ currentId));
						} catch (NumberFormatException e) {
							validatorUtils.inputNumber();
						}
					}
					ticketInfo = new TicketInfo(ticketInfoName, type,
							ticketDescription, maxNumber, price, start, end);

					try {
						validatorUtils.validate(ticketInfo, "ticket",
								ticketInfoName);
					} catch (NoSuchMethodException | SecurityException
							| IllegalAccessException | IllegalArgumentException
							| InvocationTargetException e) {
						e.printStackTrace();
					}

				}
			} else {
				//validatorUtils.selectTicket();
			}

			if (validatorUtils.getErrors().isEmpty() == false) {

				request.setAttribute("errors", validatorUtils.getErrors());
				request.getRequestDispatcher("/pages/eventManagement/editTicketsPromoCodes.jsp").forward(
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
