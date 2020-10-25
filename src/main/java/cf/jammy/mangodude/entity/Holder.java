package cf.jammy.mangodude.entity;

import cf.jammy.mangodude.MangoDude;
import processing.core.PConstants;
import processing.core.PImage;

public class Holder extends Entity {
    private PImage img;
    public int value = 0;

    public Holder(boolean f, MangoDude g, PImage img) {
        super(f, g);
        this.img = img;
    }

    @Override
    public void render(float x, float y) {
        getGame().noStroke();
        getGame().ellipseMode(PConstants.CENTER);
        getGame().fill(20, 20, 200);
        getGame().ellipse(x + img.width/2, y + img.height/2, img.width, img.height);
        getGame().rect(x + img.width/2, y, img.width * 2.5f, img.height);
        getGame().ellipse(x + img.width/2 + img.width*2.5f, y + img.height/2, img.width, img.height);
        getGame().image(img, x + img.width*2.5f, y);
        getGame().fill(255, 255, 255);
        getGame().textSize(img.height);
        getGame().text(value + "", x + img.width/2, y + img.height*0.85f);
    }

    public float calculateWidth() {
        return img.width + img.width * 2.5f + img.width;
    }

    public void setX(float x) {
        this.x = x;
    }
}
