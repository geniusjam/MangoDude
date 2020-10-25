package cf.jammy.mangodude.entity;

import cf.jammy.mangodude.MangoDude;
import processing.core.PImage;

public class ImageEntity extends Entity {
    protected PImage img;

    public ImageEntity(boolean f, MangoDude g, PImage img, float x, float y) {
        super(f, g);
        this.img = img;
        this.x = x;
        this.y = y;
    }

    @Override
    protected void render(float x, float y) {
        getGame().image(img, x, y);
    }

    public PImage getImg() {
        return img;
    }

    public void setImg(PImage image) {
        img = image;
    }
}
