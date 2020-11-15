package cf.jammy.mangodude.entity;

import cf.jammy.mangodude.Main;
import cf.jammy.mangodude.MangoDude;
import processing.core.PImage;

public class Player extends RectangularEntity {
    public static final PImage img = Main.getImg("BillyWithHat.png");
    public static final PImage imgLeft = img.copy();
    protected float xspeed = 0;
    protected float yspeed = 0;
    private boolean lookingLeft = false;

    static {
        for(int x = 0; x < img.width; x++) {
            for(int y = 0; y < img.height; y++) {
                imgLeft.set(x, y, img.get(img.width - x - 1, y));
            }
        }
    }

    public Player(boolean f, MangoDude game, float x, float y) {
        super(f, game);
        this.x = x;
        this.y = y;
    }

    public void update() {
        this.x += xspeed;
        this.y += yspeed;

        if(xspeed < 0) lookingLeft = true;
        if(xspeed > 0) lookingLeft = false;
    }

    public void render(float x, float y) {
        if(lookingLeft)
            getGame().image(imgLeft, x, y);
        else
            getGame().image(img, x, y);
    }

    public boolean isTouching(float x, float y, float w, float h) {
        //TODO: implement rectangle collision
        return true;
    }

    public float getWidth() {
        return img.width;
    }

    public float getHeight() {
        return img.height;
    }
}
