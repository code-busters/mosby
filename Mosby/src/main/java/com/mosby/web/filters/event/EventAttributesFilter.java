package main.java.com.mosby.web.filters.event;

import main.java.com.mosby.controller.services.ReadGenericObjectService;
import main.java.com.mosby.model.EventCategory;
import main.java.com.mosby.model.EventType;
import main.java.com.mosby.model.Organizer;
import main.java.com.mosby.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebFilter("/EventAttributesFilter")
public class EventAttributesFilter implements Filter {

    @SuppressWarnings("unchecked")
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);

        List<EventType> listEventTypes = new ReadGenericObjectService<>(EventType.class).readList();
        session.setAttribute("eventTypes", listEventTypes);

        List<EventCategory> listEventCategories = new ReadGenericObjectService<>(EventCategory.class).readList();
        session.setAttribute("eventCategories", listEventCategories);

        User sessionUser = (User) session.getAttribute("user");
        int userId = sessionUser.getId();
        List<Organizer> organizersList = new ReadGenericObjectService<>(Organizer.class).readListByField("user_ref", userId);
        session.setAttribute("organizers", organizersList);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }
}
