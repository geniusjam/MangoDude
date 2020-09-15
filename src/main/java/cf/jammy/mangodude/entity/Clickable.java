package cf.jammy.mangodude.entity;

import cf.jammy.mangodude.MangoDude;

public class Clickable extends Entity {
    private boolean isDisabled = false;

    public Clickable(boolean f, MangoDude g) {
        super(f, g);
    }

    public boolean isTouching(float x, float y) {
        return true;
    }

    public void onClick(){}

    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }
}
