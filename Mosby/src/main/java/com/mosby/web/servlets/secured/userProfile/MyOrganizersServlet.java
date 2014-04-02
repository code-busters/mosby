package main.java.com.mosby.web.servlets.secured.userProfile;

import main.java.com.mosby.controller.services.OrganizerService;
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

	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
    	User sessionUser = (User) session.getAttribute("user");
    	int userId = sessionUser.getId();
    	if (!(request.getParameter("delete") == null)){
    		new OrganizerService().delete(sessionUser, Integer.parseInt(request.getParameter("delete")));
    	}
    	List <Organizer> organizersList = new ReadGenericObjectService<>(Organizer.class).readListByField("user_ref", userId);
    	request.setAttribute("organizers", organizersList);
    	request.getRequestDispatcher("/pages/userProfile/myOrganizers.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}
