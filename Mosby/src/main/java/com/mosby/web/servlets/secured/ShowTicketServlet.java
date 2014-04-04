package main.java.com.mosby.web.servlets.secured;

import com.itextpdf.text.DocumentException;
import main.java.com.mosby.controller.dao.ReflectionDao;
import main.java.com.mosby.model.Ticket;
import main.java.com.mosby.model.User;
import main.java.com.mosby.utils.TicketGenerator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/showTicket")
public class ShowTicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(ShowTicketServlet.class);
       
    public ShowTicketServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/pdf");
		OutputStream out = response.getOutputStream();
		User user = (User) request.getSession().getAttribute("user");
		Ticket ticket = new ReflectionDao<>(Ticket.class).selectObjects(4, "id=", request.getParameter("id").toString(), "user_ref=", user.getId()).get(0);
		try {
			TicketGenerator ticketGenerator = new TicketGenerator(out, getServletContext().getRealPath(""));
			ticketGenerator.createTickets(ticket);
		} catch (DocumentException e) {
            e.printStackTrace();
			log.error(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
