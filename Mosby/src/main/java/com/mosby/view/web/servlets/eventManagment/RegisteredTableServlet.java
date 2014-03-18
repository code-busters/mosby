package main.java.com.mosby.view.web.servlets.eventManagment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registeredTable")
public class RegisteredTableServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/pages/eventManagement/registeredTable.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        String[] users = request.getParameterValues("checked_tickets");
        if (request.getParameter("delete") != null) {
            deleteUsers(request, users);
        } else if (request.getParameter("update") != null) {
            updateUsers(request, users);
        } else if (request.getParameter("save") != null) {
            saveUsers(request, users);
        }
    }

    public void deleteUsers(HttpServletRequest request, String[] users) throws ServletException, IOException {

    }

    public void updateUsers(HttpServletRequest request, String[] users) throws ServletException, IOException {

    }

    public void saveUsers(HttpServletRequest request, String[] users) throws ServletException, IOException {

    }
}
