package cf.jammy.mangodude.entity;

import cf.jammy.mangodude.MangoDude;
import processing.core.PImage;

public class ImageButton extends ImageEntity implements Clickable {

    public ImageButton(boolean f, MangoDude g, PImage img, float x, float y) {
        super(f, g, img, x, y);
    }

    @Override
    protected void render(float x, float y) {
        getGame().noStroke();
        getGame().fill(30, 30, 30, 50);
        getGame().rect(x, y, img.width, img.height);
        super.render(x, y);
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
