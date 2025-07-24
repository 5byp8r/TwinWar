package mg.cefet.info3.lp2tp2.twinbee;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

import mg.cefet.info3.lp2tp2.twinbee.Inimigo.TipoInimigo;

/**
 * First screen of the application. Displayed after the application is created.
 */
public class FaseDois implements Screen {
    public static Main game;
    private float tempoTiro = 0, tempoSpawn = 0;
    private Fundo fundo;
    private Player player;
    // private Inimigo inimigo;
    // private ArrayList<Torre> torres;
    private ArrayList<Atirador> atiradores;
    private boolean chefeAtivo = false;

    public FaseDois (Main game) {
        this.game = game; 
    }

    @Override
    public void show() {
        fundo = new Fundo();
        player = new Player(new Texture("Texturas/jogador.png"));

        // inimigo = new Inimigo(new Texture("Texturas/inimigo.png"), player.sprite);
        // torres = new ArrayList<>();

        atiradores = new ArrayList<>();

        Inimigo.criarInimigo(atiradores, player.sprite, TipoInimigo.DINAMICO);
        Torre.criarTorres(atiradores, 100);
        // atiradores.addAll(torres);
    }

    @Override
    public void render(float delta) {
        logic();
        draw();
        // Draw your screen here. "delta" is the time since last render in seconds.
    }

    private void logic() {
        tempoSpawn += Gdx.graphics.getDeltaTime();
        if (atiradores.size() == 0 && !fundo.isMover() && !chefeAtivo) {
            for (int i = 0; i < 5; i++) {
                Inimigo.criarInimigo(atiradores, player.sprite, TipoInimigo.CHEFE);
            }
            chefeAtivo = true;
        }
        else if (fundo.isMover() && !chefeAtivo && tempoSpawn >= Gdx.graphics.getDeltaTime() * 60 * 10) {
            Inimigo.criarInimigo(atiradores, player.sprite, new Random().nextInt(2) == 0 ? TipoInimigo.DINAMICO : TipoInimigo.ESTATICO);
            tempoSpawn = 0;
        }

        player.mover();
        for (Atirador atirador : atiradores) {
            atirador.mover();
        }

        // Torre.mover(torres, fundo.isMover());

        // GerenciadorDeColisao.checarColisaoTorre(torres, player);
        // GerenciadorDeColisao.checarColisaoInimigo(inimigo, player);

        GerenciadorDeColisao.checarColisao(atiradores, player);

        tempoTiro += Gdx.graphics.getDeltaTime();
        if (tempoTiro >= Gdx.graphics.getDeltaTime() * 30) {
            player.atirar();
            // inimigo.atirar();
            // Torre.atirar(torres, fundo.isMover());
            for (Atirador atirador : atiradores) {
                atirador.atirar();
            }
            tempoTiro = 0;
        }
        Tiro.updateTiros(player.getTiros());
        fundo.mover();
        if ((atiradores.size() == 0 && chefeAtivo) || Main.numeroFase == 1) {
            Main.numeroFase = 1;
            Menu.game.setScreen(new Menu(game));
            dispose();
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
        if (fundo != null) {
            fundo.dispose();
        }
        if (player != null) {
            player.dispose();
        }
        if (atiradores != null) {
            for (Atirador atirador : atiradores) {
                atirador.dispose();
            }
        }
    }
}