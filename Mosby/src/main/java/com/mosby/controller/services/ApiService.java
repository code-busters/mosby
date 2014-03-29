package main.java.com.mosby.controller.services;

import main.java.com.mosby.controller.dao.ReflectionDao;
import main.java.com.mosby.model.Api;
import main.java.com.mosby.model.Organizer;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.UUID;

public class ApiService {
	private static Logger log = Logger.getLogger(ApiService.class);

    public String generateKey(HttpServletRequest request, HttpServlet servlet)  {
        UUID key = UUID.randomUUID();

        String name = request.getParameter("name");
        int organizerId = Integer.parseInt(request.getParameter("organizer"));
        Organizer organizer = new ReadGenericObjectService<Organizer>((Class<Organizer>) new Organizer().getClass()).readById(organizerId);
        Date timeOfCreation = new Date();

        Api api = new Api(organizer, name, key.toString(), timeOfCreation);

        ReflectionDao<Api> apiDao = new ReflectionDao<>((Class<Api>) Api.class);
        apiDao.insertObjects(api);

        return key.toString();
    }
}
