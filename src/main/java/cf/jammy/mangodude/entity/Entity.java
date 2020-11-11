package cf.jammy.mangodude.entity;

import cf.jammy.mangodude.MangoDude;

public abstract class Entity {
    private boolean isFixed;
    private boolean isVisible = true;
    private MangoDude game;

    protected float x = 0;
    protected float y = 0;

    public Entity(boolean f, MangoDude g) {
        isFixed = f;
        game = g;
        game.entities.add(this);
    }

    public void show() {
        if(!isVisible) return;

        if(isFixed) render(x ,y);
        else render(-game.getCameraX() - game.width / 2 + x, game.getCameraY() - game.height / 2 + y);
    }

    public abstract void update();

    protected abstract void render(float x, float y);

    public abstract boolean isTouching(float x, float y, float w, float h);

    public boolean isFixed() {
        return isFixed;
    }

    public MangoDude getGame() {
        return game;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
