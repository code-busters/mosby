package main.java.com.mosby.view.web.servlets;

import main.java.com.mosby.utils.FileUploadUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet("/createEvent")
@MultipartConfig
public class CreateEventServlet extends HttpServlet {
    private static Logger log = Logger.getLogger(CreateEventServlet.class);
    private static final String EVENT_BACKGROUND_PATH = "media\\images\\events\\background";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/pages/createEvent.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        Image uploading
        String imageName = "default.png";
        Part filePart = request.getPart("event_background");
        try {
            String contentType = filePart.getContentType();
            if (contentType.startsWith("image")) {
                File image = FileUploadUtils.uploadFile(this, EVENT_BACKGROUND_PATH, filePart);
                imageName = FileUploadUtils.getFilename(image);
            }
        } catch (Exception e) {
            log.error(e);
        }

    }
}