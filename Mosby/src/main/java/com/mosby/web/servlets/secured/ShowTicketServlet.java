package main.java.com.mosby.web.servlets.secured;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.DocumentException;

import main.java.com.mosby.controller.dao.ReflectionDao;
import main.java.com.mosby.model.Ticket;
import main.java.com.mosby.model.User;
import main.java.com.mosby.utils.TicketGenerator;

@WebServlet("/showTicket")
public class ShowTicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShowTicketServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/pdf");
		OutputStream out = response.getOutputStream();
		User user = (User) request.getSession().getAttribute("user");
		Ticket ticket = (Ticket) new ReflectionDao<>(Ticket.class).selectObjects(3, "id", request.getAttribute("ticketId").toString(), "user_ref", user.getId());
		try {
			TicketGenerator ticketGenerator = new TicketGenerator(out, getServletContext().getRealPath(""));
			ticketGenerator.createTickets(ticket);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
