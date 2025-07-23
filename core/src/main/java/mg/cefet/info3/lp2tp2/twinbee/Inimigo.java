package mg.cefet.info3.lp2tp2.twinbee;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Inimigo extends ObjetoMovel {
    private FuncoesDeMovimentacao movimento;
    private Sprite playerSprite;
    private float y = 8;
    private float x;

    public Inimigo(Texture texture, Sprite playerSprite) {
        super(texture);
        this.playerSprite = playerSprite;
        sprite.setPosition(Main.viewport.getWorldWidth() / 2, Main.viewport.getWorldHeight());
        movimento = new FuncoesDeMovimentacao(0, new Vector2(playerSprite.getBoundingRectangle().getX(), playerSprite.getBoundingRectangle().getY()));
    }

    @Override
    public void mover() {
        y -= 3 * Gdx.graphics.getDeltaTime();
        if (y < 0) {
            y = 8;
            movimento = new FuncoesDeMovimentacao(0, new Vector2(playerSprite.getBoundingRectangle().getX(), playerSprite.getBoundingRectangle().getY()));
        }
        x = movimento.pegaPosicao(y);

        sprite.setY(y);
        sprite.setX(x);
    }
}
