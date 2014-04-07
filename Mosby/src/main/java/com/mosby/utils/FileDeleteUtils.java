package main.java.com.mosby.utils;

import java.io.File;

import javax.servlet.http.HttpServlet;

public class FileDeleteUtils {

	public static void deletePicture(HttpServlet servlet, String picturePath, String pictureName) {
		if (!(pictureName.equals("default.jpg") || pictureName.equals("default.png"))) {
			String path = servlet.getServletContext().getRealPath("") + "\\" 
					+ picturePath + "\\" + pictureName;
			new File(path).delete();
		}
	}
	
}
