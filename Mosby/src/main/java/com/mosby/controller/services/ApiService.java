package main.java.com.mosby.controller.services;

import main.java.com.mosby.controller.dao.ReflectionDao;
import main.java.com.mosby.model.Api;
import main.java.com.mosby.model.Organizer;
import main.java.com.mosby.utils.EncryptionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

public class ApiService {
    private ReflectionDao<Api> apiDao;

    public ApiService() {
        apiDao = new ReflectionDao<>(Api.class);
    }

    public String generateKey(HttpServletRequest request) {
        String uniqueKey = UUID.randomUUID().toString();
        String key = EncryptionUtils.createHash(uniqueKey,8);

        String name = request.getParameter("name");
        int organizerId = Integer.parseInt(request.getParameter("organizer"));
        Organizer organizer = new ReadGenericObjectService<>(Organizer.class).readById(organizerId);
        Date timeOfCreation = new Date();

        Api api = new Api(organizer, name, key, timeOfCreation);
        apiDao.insertObjects(api);
        return key;
    }

    public void deleteKey(HttpServletRequest request) {
        int apiId = Integer.parseInt(request.getParameter("delete"));
        Api api = new ReadGenericObjectService<>(Api.class).readById(apiId);
        apiDao.deleteObjects(api);
    }
}
