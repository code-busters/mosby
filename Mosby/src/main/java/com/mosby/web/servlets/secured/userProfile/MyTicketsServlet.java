package main.java.com.mosby.web.servlets.secured.userProfile;

import main.java.com.mosby.controller.services.ReadGenericObjectService;
import main.java.com.mosby.model.Ticket;
import main.java.com.mosby.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/myTickets")
public class MyTicketsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User sessionUser = (User) session.getAttribute("user");
        int userId = sessionUser.getId();
        List<Ticket> ticketList = new ReadGenericObjectService<>(Ticket.class).readListByField("user_ref=", userId);
        request.setAttribute("tickets", ticketList);
        request.getRequestDispatcher("/pages/userProfile/myTickets.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
