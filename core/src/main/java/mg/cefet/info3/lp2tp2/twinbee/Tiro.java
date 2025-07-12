package mg.cefet.info3.lp2tp2.twinbee;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Tiro extends ObjetoMovel {
    public Tiro(Texture texture, float x, float y) {
        super(texture);
        sprite.setPosition(x, y);
    }

    @Override
    public void mover() {
        sprite.translateY(10 * Gdx.graphics.getDeltaTime());
    }
}
