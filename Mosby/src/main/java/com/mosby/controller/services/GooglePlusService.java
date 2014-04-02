package main.java.com.mosby.controller.services;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.client.util.ArrayMap;
import com.google.api.services.plus.Plus;
import com.google.api.services.plus.model.Person;
import main.java.com.mosby.controller.dao.ReflectionDao;
import main.java.com.mosby.model.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;

public class GooglePlusService {
    private static final Logger log = Logger.getLogger(GooglePlusService.class);
    private static final HttpTransport TRANSPORT = new NetHttpTransport();
    private static final JacksonFactory JSON_FACTORY = new JacksonFactory();
    private static GoogleClientSecrets clientSecrets;
    private static String CLIENT_ID;
    private static String CLIENT_SECRET;

    public GooglePlusService() {
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("/client_secrets.json");
            Reader reader = new InputStreamReader(inputStream);
            clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, reader);
            CLIENT_ID = clientSecrets.getWeb().getClientId();
            CLIENT_SECRET = clientSecrets.getWeb().getClientSecret();
        } catch (IOException e) {
            throw new Error("No client_secrets.json found", e);
        }
    }

    public String getClientId() {
        return CLIENT_ID;
    }

    public User readData(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("state");
        User user = new User();
        try {
            ByteArrayOutputStream resultStream = new ByteArrayOutputStream();
            getContent(request.getInputStream(), resultStream);
            String code = new String(resultStream.toByteArray(), "UTF-8");

            GoogleTokenResponse tokenResponse =
                    new GoogleAuthorizationCodeTokenRequest(TRANSPORT, JSON_FACTORY,
                            CLIENT_ID, CLIENT_SECRET, code, "postmessage").execute();

            String tokenData = tokenResponse.toString();
            request.getSession().setAttribute("token", tokenData);

            GoogleCredential credential = new GoogleCredential.Builder()
                    .setJsonFactory(JSON_FACTORY)
                    .setTransport(TRANSPORT)
                    .setClientSecrets(CLIENT_ID, CLIENT_SECRET).build()
                    .setFromTokenResponse(JSON_FACTORY.fromString(
                            tokenData, GoogleTokenResponse.class));

            Plus service = new Plus.Builder(TRANSPORT, JSON_FACTORY, credential)
                    .setApplicationName("Mosby")
                    .build();

            Person mePerson = service.people().get("me").execute();
            String firstName = mePerson.getName().getGivenName();
            String lastName = mePerson.getName().getFamilyName();
            String tempImgUrl = mePerson.getImage().getUrl();
            int lastIndex = tempImgUrl.lastIndexOf("?sz");
            String imgUrl = tempImgUrl.substring(0, lastIndex);
            String googlePlusUrl = mePerson.getUrl();
            ArrayList<ArrayMap> emails = (ArrayList<ArrayMap>) mePerson.get("emails");
            String email = (String) emails.get(0).getValue(0);

            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setSite(googlePlusUrl);
            user.setImage(imgUrl);
            user.setActive(true);

        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            log.error(e);
        }
        User temp = new UserService().socialSignUpUser(user);
        ReflectionDao<User> usersDao = new ReflectionDao<>(User.class);
        return (temp != null) ? temp : usersDao.selectObjects("email", user.getEmail()).get(0);
    }

    private static void getContent(InputStream inputStream, ByteArrayOutputStream outputStream)
            throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        int readChar;
        while ((readChar = reader.read()) != -1) {
            outputStream.write(readChar);
        }
        reader.close();
    }

    public void disconnect(HttpServletRequest request, HttpServletResponse response) {
        String tokenData = (String) request.getSession().getAttribute("token");
        try {
            GoogleCredential credential = new GoogleCredential.Builder()
                    .setJsonFactory(JSON_FACTORY)
                    .setTransport(TRANSPORT)
                    .setClientSecrets(CLIENT_ID, CLIENT_SECRET).build()
                    .setFromTokenResponse(JSON_FACTORY.fromString(
                            tokenData, GoogleTokenResponse.class));
            TRANSPORT.createRequestFactory()
                    .buildGetRequest(new GenericUrl(
                            String.format(
                                    "https://accounts.google.com/o/oauth2/revoke?token=%s",
                                    credential.getAccessToken()))).execute();
            request.getSession().removeAttribute("token");
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            log.error(e);
        }
    }
}
