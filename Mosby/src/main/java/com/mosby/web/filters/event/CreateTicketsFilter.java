package main.java.com.mosby.web.filters.event;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/CreateTicketsFilter")
public class CreateTicketsFilter implements Filter {

	public CreateTicketsFilter() {
		// TODO Auto-generated constructor stub
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		System.out.println("createTicketFilter");

//		if (request.getMethod().equals("POST")) {
//			System.out.println("validate....");
//
//			String[] idTicketsArray = request.getParameterValues("ticket_id");
//
//			if (idTicketsArray.length != 0){
//				ReflectionDao<TicketInfo> ticketInfoDao = new ReflectionDao<>(TicketInfo.class);
//				for (String currInt : idTicketsArray){
//					int currentId = Integer.parseInt(currInt);
//					String type;
//					String ticketInfoName = request.getParameter("event_ticket_name_" + currentId);
//					String ticketDescription = request.getParameter("ticket_description_" + currentId);
//					int maxNumber = Integer.parseInt(request.getParameter("event_ticket_quantity_" + currentId));
//					String stringPrice = request.getParameter("event_ticket_price_" + currentId);
//
//					SimpleDateFormat parseDate = new SimpleDateFormat("yyyy-MM-dd hh:mm");
//					String startTimestamp = request.getParameter("start_date") + " " + request.getParameter("start_time");
//					String endTimestamp = request.getParameter("end_date") + " " + request.getParameter("end_time");
//					Timestamp start = null;
//					Timestamp end = null;
//					try {
//						start = new Timestamp(parseDate.parse(startTimestamp).getTime());
//						end = new Timestamp(parseDate.parse(endTimestamp).getTime());
//					} catch (ParseException e) {
//						e.printStackTrace();
//					}
//					int price;
//					if (stringPrice == null){
//						type = "Free";
//						price = 0;
//					}
//					else {
//						type = "Paid";
//						price = Integer.parseInt(request.getParameter("event_ticket_price_" + currentId));
//					}
//					TicketInfo ticketInfo = new TicketInfo(ticketInfoName, type, ticketDescription, maxNumber, price, startDate, startTime, endDate, endTime);
//
//					ticketInfoDao.insertObjects(ticketInfo);
//					System.out.println(ticketInfo);
//				}
//			}
//
//			try {
//				validatorUtils.validate(event);
//			} catch (NoSuchMethodException | SecurityException
//					| IllegalAccessException | IllegalArgumentException
//					| InvocationTargetException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//			if (event == null || validatorUtils.getErrors().isEmpty() == false) {
//
//				request.setAttribute("errors", validatorUtils.getErrors());
//				System.out.println(validatorUtils.getErrors());
//				request.getRequestDispatcher("/pages/createEvent.jsp").forward(
//						request, response);
//			} else {
//				chain.doFilter(request, response);
//			}
//		} else {
//			chain.doFilter(request, response);
//		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
