package cf.jammy.mangodude.entity;


public interface Clickable {

    boolean isTouching(float x, float y);

    void onClick();

    boolean isDisabled();
}
