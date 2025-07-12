package mg.cefet.info3.lp2tp2.twinbee;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * First screen of the application. Displayed after the application is created.
 */
public class FirstScreen implements Screen {
    private Fundo fundo;

    @Override
    public void show() {
        fundo = new Fundo();
        // Prepare your screen here.
    }

    @Override
    public void render(float delta) {
        logic();
        draw();
        // Draw your screen here. "delta" is the time since last render in seconds.
    }

    private void logic() {
        fundo.mover();

    }

    private void draw() {
        ScreenUtils.clear(Color.BLACK);
        fundo.draw();
    }

    @Override
    public void resize(int width, int height) {
        // If the window is minimized on a desktop (LWJGL3) platform, width and height
        // are 0, which causes problems.
        // In that case, we don't resize anything, and wait for the window to be a
        // normal size before updating.
        if (width <= 0 || height <= 0)
            return;

        if (width > 0 && height > 0) {
            Main.viewport.update(width, height, true);
        }

        // Resize your screen here. The parameters represent the new window size.
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        // Destroy screen's assets here.
    }
}