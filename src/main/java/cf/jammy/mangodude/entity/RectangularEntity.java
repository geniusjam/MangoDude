package cf.jammy.mangodude.entity;

import cf.jammy.mangodude.MangoDude;

public abstract class RectangularEntity extends Entity {
    public RectangularEntity(boolean f, MangoDude g) {
        super(f, g);
    }

    public boolean isTouching(float x, float y, float w, float h) {
        return x < this.x + getWidth() &&
                x + w > this.x &&
                y < this.y + getHeight() &&
                y + h > this.y;
    }

    public abstract float getWidth();

    public abstract float getHeight();
}
