package cf.jammy.mangodude.entity;

import cf.jammy.mangodude.MangoDude;

public class TextEntity extends RectangularEntity {
    private String text;
    private int color = 0xFFFFFF;

    private static int textSize = 16;

    public TextEntity(boolean f, MangoDude g, String text, float x, float y) {
        super(f, g);
        this.text = text;
        this.x = x;
        this.y = y;
    }

    public void update() {}

    protected void render(float x, float y) {
        getGame().fill(color);
        getGame().textSize(textSize);
        getGame().text(text, x, y);
    }

    public float getWidth() {
        return getGame().textWidth(text);
    }

    public float getHeight() {
        return textSize;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TextEntity center(float w) {
        x = getGame().width / 2 - w / 2;
        return this; //for chaining
    }

    public int getColor() {
        return color;
    }

    public TextEntity setColor(int c) {
        color = c;
        return this; //for chaining
    }
}
