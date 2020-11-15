package cf.jammy.mangodude.entity;

import cf.jammy.mangodude.MangoDude;

public class Controller extends Entity implements KeyboardListener {
    private Player player;

    public Controller(MangoDude g, Player p) {
        super(true, g);
        this.player = p;
    }

    @Override
    public void onKeyUp(char key, int keyCode) {
        if(key == 'd') {
            player.xspeed = 0;
        } else if(key == 'a') {
            player.xspeed = 0;
        } else if(key == 'w') {
            player.yspeed = 0;
        }
    }

    @Override
    public void onKeyDown(char key, int keyCode) {
        if(key == 'd') {
            player.xspeed = 3;
        } else if(key == 'a') {
            player.xspeed = -3;
        } else if(key == 'w') {
            //TODO: code jumping
            player.yspeed = -2;
        }
    }

    public void update() {}

    protected void render(float x, float y) {}

    public boolean isTouching(float x, float y, float w, float h) { return true; }
}
