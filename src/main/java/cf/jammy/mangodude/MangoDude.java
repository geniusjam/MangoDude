package cf.jammy.mangodude;

import cf.jammy.mangodude.entity.Clickable;
import cf.jammy.mangodude.entity.Entity;
import cf.jammy.mangodude.entity.Floor;
import cf.jammy.mangodude.entity.ImageButton;
import cf.jammy.mangodude.entity.TextEntity;
import processing.awt.PSurfaceAWT;
import processing.core.PApplet;
import processing.core.PImage;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class MangoDude extends PApplet {

    public List<Entity> entities = new LinkedList<>();

    private float cameraX;
    private float cameraY;

    public void settings(){
        size(1000, 700);

        setCameraCenter(0, 0);
    }

    public void setup(){
        new TextEntity(true, this, "MangoDude", 0, 25)
                .center(textWidth("MangoDude"))
                .setColor(255);

        new ImageButton(true, this, Main.getImg("logout.png"), 0, 0) {
            @Override
            public void onClick() {
                Main.prefs.remove("username");
                Main.prefs.remove("password");

                if(Main.loginFrame == null) Main.loginFrame = new LoginFrame();
                else Main.loginFrame.setVisible(true);

                Frame frame = ((PSurfaceAWT.SmoothCanvas) (surface).getNative()).getFrame();
                frame.dispose();
            }
        };

        PImage floorImg = Main.getImg("floor.png");
        new Floor(this, floorImg, 0, height - floorImg.height);
    }

    public void mouseClicked(){
        for(Entity e : entities)
            if(e instanceof Clickable && ((Clickable) e).isTouching(mouseX, mouseY))
                ((Clickable) e).onClick();
    }

    public void keyPressed(){
        if(keyCode == LEFT)  {
            cameraX -= 10;
        }
        else if(keyCode == RIGHT) {
            cameraX += 10;
        }
        else if(keyCode == UP) {
            cameraY -= 10;
        }
        else if(keyCode == DOWN) {
            cameraY += 10;
        }
    }

    public void draw(){
        background(0x87, 0xCE, 0xEB);
        fill(0, 0, 0);

        for(Entity entity : entities) {
            if(entity.isTouching(cameraX, cameraY, width, height)) entity.update();
        }

        for(Entity entity : entities) {
            if(entity.isTouching(cameraX, cameraY, width, height)) entity.show();
        }
    }

    private void setCameraCenter(float x, float y) {
        cameraX = x-width/2;
        cameraY = y+height/2;
    }

    public float getCameraX() {
        return cameraX;
    }

    public float getCameraY() {
        return cameraY;
    }
}