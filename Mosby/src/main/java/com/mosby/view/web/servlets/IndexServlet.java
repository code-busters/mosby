package main.java.com.mosby.view.web.servlets;

import main.java.com.mosby.controller.services.MainService;
import main.java.com.mosby.controller.services.ReadEventService;
import main.java.com.mosby.model.BaseUserInfo;
import main.java.com.mosby.model.EventCategorie;
import main.java.com.mosby.model.EventType;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static Logger log = Logger.getLogger(IndexServlet.class);
	private MainService mainService = new MainService();

	public IndexServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (request.getParameter("logout") != null) {
            session = request.getSession();
            BaseUserInfo user = (BaseUserInfo) session.getAttribute("baseUserInfo");
            log.info("Logged out: " + user.getFirstName() + " " + user.getLastName());
            session.removeAttribute("baseUserInfo");
            request.getRequestDispatcher("/pages/index.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/pages/index.jsp").forward(request, response);
        }
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}
