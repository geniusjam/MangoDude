package cf.jammy.mangodude;

import processing.core.PApplet;
import processing.core.PImage;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.prefs.Preferences;

public class Main {
    public static Preferences prefs = Preferences.userNodeForPackage(Main.class);
    private static ConnectionHandler connectionHandler;
    public static LoginFrame loginFrame;

    public static void main(String[] args){
        String username = prefs.get("username", null);
        String password = prefs.get("password", null);
        if(username == null || password == null) {
            loginFrame = new LoginFrame();
            return;
        }
        connect(username, password);
    }

    public static void connect(String username, String password) {
        connectionHandler = new ConnectionHandler(username, password);
    }

    public static PImage getImg(String path) {
        try {
            ClassLoader classLoader = Main.class.getClassLoader();
            return new PImage(ImageIO.read(classLoader.getResourceAsStream(path)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void start() {
        PApplet.main("cf.jammy.mangodude.MangoDude");
    }

}
