package cf.jammy.mangodude.entity;

import cf.jammy.mangodude.MangoDude;
import processing.core.PImage;

public class ImageEntity extends RectangularEntity {
    protected PImage img;

    public ImageEntity(boolean f, MangoDude g, PImage img, float x, float y) {
        super(f, g);
        this.img = img;
        this.x = x;
        this.y = y;
    }

    public void update() { }

    protected void render(float x, float y) {
        getGame().image(img, x, y);
    }

    public float getWidth() {
        return img.width;
    }

    public float getHeight() {
        return img.height;
    }

    public PImage getImg() {
        return img;
    }

    public void setImg(PImage image) {
        img = image;
    }
}
