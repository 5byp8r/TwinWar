package mg.cefet.info3.lp2tp2.twinbee;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Tiro extends ObjetoMovel {
    public Tiro(Texture texture, float x, float y) {
        super(texture);
        sprite.setPosition(x, y);
    }

    public void mover(float velocidadeTiro) {
        sprite.translateY(velocidadeTiro * Gdx.graphics.getDeltaTime());
        if (sprite.getY() <= -2 || sprite.getY() >=  8) {
            sprite.setPosition(sprite.getX() + 10, sprite.getY() + 10);
        }
    }

    @Override
    public void mover() {
    }
}
