package cf.jammy.mangodude;

import cf.jammy.mangodude.entity.Clickable;
import cf.jammy.mangodude.entity.Controller;
import cf.jammy.mangodude.entity.Entity;
import cf.jammy.mangodude.entity.Floor;
import cf.jammy.mangodude.entity.Holder;
import cf.jammy.mangodude.entity.ImageButton;
import cf.jammy.mangodude.entity.KeyboardListener;
import cf.jammy.mangodude.entity.Player;
import cf.jammy.mangodude.entity.TextEntity;
import processing.awt.PSurfaceAWT;
import processing.core.PApplet;
import processing.core.PImage;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class MangoDude extends PApplet {

    public List<Entity> entities = new LinkedList<>();

    private Player self = null;

    private float cameraX;
    private float cameraY;

    private Holder mangoes;

    public void settings(){
        size(1000, 700);

        setCameraCenter(0, 0);
    }

    public void setup(){
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

        self = new Player(false, this, width/2 - Player.img.width/2, height - floorImg.height - Player.img.height) {
            @Override
            public void render(float x, float y) {
                super.render(x, y);
                if(x <= 20) {
                    cameraX -= 50;
                } else if(x >= width - 20) {
                    cameraX += 50;
                }
            }
        };

        new Controller(this, self);

        new TextEntity(true, this, "x: y:", 0, 30) {
            @Override
            protected void render(float x, float y) {
                this.setText("x: " + self.getX() + " y: " + self.getY());
                super.render(width - textWidth(getText())/2, y);
            }
        }.setColor(0x000000);

        mangoes = new Holder(true, this, Main.getImg("mango.png"));
        mangoes.setX(width / 2 - mangoes.calculateWidth() / 2);
        mangoes.value = 20;
    }

    public void mouseClicked(){
        for(Entity e : entities)
            if(e instanceof Clickable && ((Clickable) e).isTouching(mouseX, mouseY) && !((Clickable) e).isDisabled())
                ((Clickable) e).onClick();
    }

    public void keyReleased(){
        for(Entity e : entities)
            if(e instanceof KeyboardListener)
                ((KeyboardListener) e).onKeyUp(key, keyCode);
    }

    public void keyPressed(){
        for(Entity e : entities)
            if(e instanceof KeyboardListener)
                ((KeyboardListener) e).onKeyDown(key, keyCode);
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
            entity.update();
        }

        for(Entity entity : entities) {
            if(entity.isFixed() || entity.isTouching(cameraX, cameraY, width, height)) entity.show();
        }
    }

    private void setCameraCenter(float x, float y) {
        cameraX = x - width/2;
        cameraY = y + height/2;
    }

    public float getCameraX() {
        return cameraX;
    }

    public float getCameraY() {
        return cameraY;
    }
}