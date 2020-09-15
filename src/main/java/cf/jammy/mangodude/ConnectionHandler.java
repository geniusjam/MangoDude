package cf.jammy.mangodude;

import io.socket.client.IO;
import io.socket.client.Socket;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import processing.core.PApplet;

import javax.swing.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionHandler {

    private static final String base = "http://mangodude.herokuapp.com";

    private static final CloseableHttpClient httpClient = HttpClients.createDefault();

    public static String signUp(String username, String password) throws Exception {
        HttpPost post = new HttpPost(base + "/signup");

        // add request parameter, form parameters
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("username", username));
        urlParameters.add(new BasicNameValuePair("password", password));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        try (CloseableHttpResponse response = httpClient.execute(post)) {
            return EntityUtils.toString(response.getEntity());
        }
    }

    private Socket socket;
    private JSONObject user;

    public ConnectionHandler(final String username, final String password) {
        try {
            socket = IO.socket(base);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println("Connecting...");
        socket.on(Socket.EVENT_CONNECT, (Object... args) -> {
            System.out.println("Connected.");
            try {
                socket.emit("auth",
                        new JSONObject().put("username", username).put("password", password));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }).on("authed", (Object... args) -> {
            System.out.println("Authorization complete.");
            user = (JSONObject) args[0];
            Main.start();
        }).on("authfail", (Object... args) -> {
            JOptionPane.showMessageDialog(null, "Invalid login details!");
            if(Main.loginFrame == null) Main.loginFrame = new LoginFrame();
            else Main.loginFrame.setVisible(true);
        }).on(Socket.EVENT_DISCONNECT, (Object... args) -> {
            JOptionPane.showMessageDialog(null, "Lost connection.");
            System.exit(0);
        });
        socket.connect();
    }

    public void sendCoords(float x, float y) throws JSONException {
        socket.emit("", new JSONObject().put("x", x).put("y", y));
    }
}
