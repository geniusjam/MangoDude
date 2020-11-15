package cf.jammy.mangodude.entity;

import cf.jammy.mangodude.MangoDude;
import processing.core.PImage;

public class Floor extends ImageEntity {
    public Floor(MangoDude game, PImage img, float x, float y) { super(false, game, img, x, y); }

    protected void render(float x, float y) {
        super.render(this.x, y);

        int w = getImg().width;
        while(w < getGame().width) {
            super.render(this.x + w, y);
            w += getImg().width;
        }
    }
}
