package mg.cefet.info3.lp2tp2.twinbee;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

import mg.cefet.info3.lp2tp2.twinbee.Inimigo.TipoInimigo;

/**
 * First screen of the application. Displayed after the application is created.
 */
public class FirstScreen implements Screen {
    private float tempo = 0;
    private Fundo fundo;
    private Player player;
    // private Inimigo inimigo;
    // private ArrayList<Torre> torres;
    private ArrayList<Atirador> atiradores;

    @Override
    public void show() {
        fundo = new Fundo();
        player = new Player(new Texture("Texturas/jogador.png"));

        // inimigo = new Inimigo(new Texture("Texturas/inimigo.png"), player.sprite);
        // torres = new ArrayList<>();

        atiradores = new ArrayList<>();
        
        Inimigo.criarInimigo(atiradores, player.sprite, TipoInimigo.DINAMICO);
        Torre.criarTorres(atiradores, 5);
        // atiradores.addAll(torres);
    }

    @Override
    public void render(float delta) {
        logic();
        draw();
        // Draw your screen here. "delta" is the time since last render in seconds.
    }

    private void logic() {
        player.mover();
        for (Atirador atirador : atiradores) {
            atirador.mover();
        }

        // Torre.mover(torres, fundo.isMover());

        // GerenciadorDeColisao.checarColisaoTorre(torres, player);
        // GerenciadorDeColisao.checarColisaoInimigo(inimigo, player);

        GerenciadorDeColisao.checarColisao(atiradores, player);

        tempo += Gdx.graphics.getDeltaTime();
        if (tempo >= Gdx.graphics.getDeltaTime() * 30) {
            player.atirar();
            // inimigo.atirar();
            // Torre.atirar(torres, fundo.isMover());
            for (Atirador atirador : atiradores) {
                atirador.atirar();
            }
            tempo = 0;
        }
        Tiro.updateTiros(player.getTiros());
        fundo.mover();
        if (Main.numeroFase == 2) {
            Menu.game.setScreen(new SecondScreen());
        }
    }

    private void draw() {
        ScreenUtils.clear(Color.BLACK);
        Main.batch.begin();
        Main.batch.setProjectionMatrix(Main.viewport.getCamera().combined);
        fundo.draw();
        Tiro.drawTiros(player.getTiros());
        for (Atirador atirador : atiradores) {
            atirador.draw();
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