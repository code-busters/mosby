package main.java.com.mosby.view.web.servlets.userProfile;

import main.java.com.mosby.controller.services.ApiService;
import main.java.com.mosby.controller.services.ReadGenericObjectService;
import main.java.com.mosby.model.Api;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/apiAccess")
public class ApiAccessServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
//        User sessionUser = (User) session.getAttribute("user");
//        int userId = sessionUser.getId();
//        List<Organizer> organizersList = new ReadGenericObjectService<Organizer>((Class<Organizer>) new Organizer().getClass()).readListByField("user_ref", userId);
//        List<Api> apiList = new ArrayList<>();
//        for (Organizer organizer : organizersList) {
//            List<Api> tempApiList = new ReadGenericObjectService<>((Class<Api>) new Api().getClass()).readListByField("organizer_ref", organizer.getId());
//            Collections.copy(apiList, tempApiList);
//        }

        // there is bug
        List<Api> apiList = new ReadGenericObjectService<>((Class<Api>) new Api().getClass()).readListByField("organizer_ref", 5);

        request.setAttribute("keys", apiList);
        request.getRequestDispatcher("/pages/userProfile/apiAccess.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (request.getParameter("submit").equals("Generate")) {
            ApiService apiService = new ApiService();
            String key = apiService.generateKey(request, this);
            request.setAttribute("generatedKey", key);
        } else {
            request.removeAttribute("generatedKey");
        }
        doGet(request, response);
    }
}
