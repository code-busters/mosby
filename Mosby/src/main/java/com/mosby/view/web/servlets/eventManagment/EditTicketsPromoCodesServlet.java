package main.java.com.mosby.view.web.servlets.eventManagment;

import main.java.com.mosby.controller.services.ReadGenericObjectService;
import main.java.com.mosby.controller.services.EventService;
import main.java.com.mosby.model.Event;
import main.java.com.mosby.model.PromoCode;
import main.java.com.mosby.model.TicketInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/editTicketsPromoCodes")
@MultipartConfig
public class EditTicketsPromoCodesServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("eventId") != null) {
            int eventId = Integer.parseInt(request.getParameter("eventId"));
            Event event = new ReadGenericObjectService<Event>((Class<Event>) new Event().getClass()).readById(eventId); 
            List <TicketInfo> ticketsInfoList = new ReadGenericObjectService<TicketInfo>((Class<TicketInfo>) new TicketInfo().getClass()).readListByField("event_ref", (Integer)eventId);
            List <PromoCode> promoCodesList = new ReadGenericObjectService<PromoCode>((Class<PromoCode>) new PromoCode().getClass()).readListByField("event_ref", (Integer)eventId);
            request.setAttribute("event", event);
            request.setAttribute("ticketsInfo", ticketsInfoList);
            request.setAttribute("promoCodes", promoCodesList);
            request.getRequestDispatcher("/pages/eventManagement/editTicketsPromoCodes.jsp").forward(request, response);
        } else {
            response.sendRedirect("index");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if (request.getParameter("eventId") != null) {
    		int eventId = Integer.parseInt(request.getParameter("eventId"));
    		EventService eventService = new EventService();
    		eventService.updateTicketsInfo(request);
    		eventService.updatePromoCodes(request);
    		response.sendRedirect("/Mosby/editTicketsPromoCodes?eventId=" + eventId);
    	} else {
    		response.sendRedirect("index");
    	}
    }

}
