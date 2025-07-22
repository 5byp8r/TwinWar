package mg.cefet.info3.lp2tp2.twinbee;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Inimigo extends ObjetoMovel {
    private FuncoesDeMovimentacao movimento;
    private float y = 8;
    private float x;

    public Inimigo(Texture texture) {
        super(texture);
        sprite.setPosition(Main.viewport.getWorldWidth() / 2, Main.viewport.getWorldHeight());
        movimento = new FuncoesDeMovimentacao(0);
    }

    @Override
    public void mover() {
        y -= 3 * Gdx.graphics.getDeltaTime();
        if (y < 0) {
            y = 8;
            movimento = new FuncoesDeMovimentacao(new Random().nextInt(3));
        }
        x = movimento.pegaPosicao(y);

        sprite.setY(y);
        sprite.setX(x);
    }
}
