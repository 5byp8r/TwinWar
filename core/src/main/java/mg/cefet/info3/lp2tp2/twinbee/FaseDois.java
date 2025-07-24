package mg.cefet.info3.lp2tp2.twinbee;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

import mg.cefet.info3.lp2tp2.twinbee.Inimigo.TipoInimigo;

public class FaseDois implements Screen {
    public static Main game;
    private float tempoTiro = 0, tempoSpawn = 0;
    private Fundo fundo;
    private Player player;
    private ArrayList<Atirador> atiradores;
    private boolean chefeAtivo = false;

    public FaseDois (Main game, Player player) {
        this.game = game; 
        this.player = player;
    }

    @Override
    public void show() {
        fundo = new Fundo();
        player = new Player(new Texture("Texturas/jogador.png"), game);

        atiradores = new ArrayList<>();

        Inimigo.criarInimigo(atiradores, player.sprite, TipoInimigo.DINAMICO);
        Torre.criarTorres(atiradores, 100);
    }

    @Override
    public void render(float delta) {
        logic();
        draw();
    }

    private void logic() {
        tempoSpawn += Gdx.graphics.getDeltaTime();
        if (atiradores.isEmpty() && !fundo.isMover() && !chefeAtivo) {
            for (int i = 0; i < 5; i++) {
                Inimigo.criarInimigo(atiradores, player.sprite, TipoInimigo.CHEFE);
            }
            chefeAtivo = true;
        }
        else if (fundo.isMover() && !chefeAtivo && tempoSpawn >= Gdx.graphics.getDeltaTime() * 60 * 10) {
            Inimigo.criarInimigo(atiradores, player.sprite, new Random().nextInt(2) == 0 ? TipoInimigo.DINAMICO : TipoInimigo.ESTATICO);
            tempoSpawn = 0;
        }

        if (player.vida == 0){
            game.setScreen(new TelaGameOver(game));
            
        }

        player.mover();
        for (Atirador atirador : atiradores) {
            atirador.mover();
        }

        GerenciadorDeColisao.checarColisao(atiradores, player);

        tempoTiro += Gdx.graphics.getDeltaTime();
        if (tempoTiro >= Gdx.graphics.getDeltaTime() * 30) {
            player.atirar();
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
        player.showStats();
        Main.batch.end();
    }

    @Override
    public void resize(int width, int height) {

        if (width <= 0 || height <= 0)
            return;

        if (width > 0 && height > 0) {
            Main.viewport.update(width, height, true);
        }

    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
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