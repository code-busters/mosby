package main.java.com.mosby.web.servlets.secured.userProfile;

import main.java.com.mosby.controller.services.ApiService;
import main.java.com.mosby.controller.services.ReadGenericObjectService;
import main.java.com.mosby.model.Api;
import main.java.com.mosby.model.Organizer;
import main.java.com.mosby.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/apiAccess")
public class ApiAccessServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ApiService apiService;

    @Override
    public void init() throws ServletException {
        apiService = new ApiService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("delete") != null) {
            apiService.deleteKey(request);
        }
        HttpSession session = request.getSession(false);
        User sessionUser = (User) session.getAttribute("user");
        int userId = sessionUser.getId();
        List<Organizer> organizersList = new ReadGenericObjectService<>(Organizer.class).readListByField("user_ref", userId);
        List<Api> apiList = new ArrayList<>();
        for (Organizer organizer : organizersList) {
            List<Api> tempApiList = new ReadGenericObjectService<>(Api.class).readListByField("organizer_ref", organizer.getId());
            apiList.addAll(tempApiList);
        }

        request.setAttribute("organizers", organizersList);
        request.setAttribute("keys", apiList);
        request.getRequestDispatcher("/pages/userProfile/apiAccess.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("submit").equals("Generate")) {
            String key = apiService.generateKey(request);
            request.setAttribute("generatedKey", key);
        } else {
            request.removeAttribute("generatedKey");
        }
        doGet(request, response);
    }
}
