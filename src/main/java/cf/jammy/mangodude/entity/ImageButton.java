package cf.jammy.mangodude.entity;

import cf.jammy.mangodude.MangoDude;
import processing.core.PImage;

public class ImageButton extends Entity implements Clickable {
    private PImage img;

    public ImageButton(boolean f, MangoDude g, PImage img, float x, float y) {
        super(f, g);
        this.img = img;
        this.x = x;
        this.y = y;
    }

    @Override
    protected void render(float x, float y) {
        getGame().noStroke();
        getGame().fill(0x2d2d2d);
        getGame().rect(x, y, img.width, img.height);
        getGame().image(img, x, y);
    }

    public boolean isTouching(float x, float y) {
        return x > this.x && x < this.x + img.width &&
                y > this.y && y < this.y + img.height;
    }

    public boolean isDisabled() {
        return false;
    }

    public void onClick() {}
}
