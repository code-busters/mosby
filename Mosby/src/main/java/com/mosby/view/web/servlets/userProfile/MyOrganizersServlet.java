package main.java.com.mosby.view.web.servlets.userProfile;

import main.java.com.mosby.controller.services.ReadGenericObjectService;
import main.java.com.mosby.model.Organizer;
import main.java.com.mosby.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/myOrganizers")
public class MyOrganizersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
    	User sessionUser = (User) session.getAttribute("user");
    	int userId = sessionUser.getId();
    	List <Organizer> organizersList = new ReadGenericObjectService<Organizer>((Class<Organizer>) new Organizer().getClass()).readListByField("user_ref", (Integer)userId);
    	request.setAttribute("organizers", organizersList);
    	request.getRequestDispatcher("/pages/userProfile/myOrganizers.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}
