package mg.cefet.info3.lp2tp2.twinbee;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * First screen of the application. Displayed after the application is created.
 */
public class FirstScreen implements Screen {
    private float tempo = 0;
    private Fundo fundo;
    private Player player;
    private Inimigo inimigo;
    private ArrayList<Tiro> tiros;

    @Override
    public void show() {
        fundo = new Fundo();
        player = new Player(new Texture("Texturas/aviao.png"));
        inimigo = new Inimigo(new Texture("Texturas/c418.png"));
        tiros = new ArrayList<>();
        // Prepare your screen here.
    }

    @Override
    public void render(float delta) {
        logic();
        draw();
        // Draw your screen here. "delta" is the time since last render in seconds.
    }

    private void logic() {
        inimigo.mover();
        player.mover();
        tempo += Gdx.graphics.getDeltaTime();
        if (tempo >= Gdx.graphics.getDeltaTime() * 5) {
            player.atirar(tiros); 
            tempo = 0;
        }
        for (Tiro tiro : tiros) {
            tiro.mover();
        }
        fundo.mover();
    }

    private void draw() {
        ScreenUtils.clear(Color.BLACK);
        Main.batch.begin();
        Main.batch.setProjectionMatrix(Main.viewport.getCamera().combined);
        fundo.draw();
        inimigo.draw();
        for (Tiro tiro : tiros) {
            tiro.draw();
        }
        player.draw();
        Main.batch.end();
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