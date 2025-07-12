package mg.cefet.info3.lp2tp2.twinbee;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Inimigo extends ObjetoMovel {
    public Inimigo(Texture texture) {
        super(texture);
        sprite.setPosition(Main.viewport.getWorldWidth() / 2, Main.viewport.getWorldHeight());
    }

    @Override
    public void mover() {
        sprite.translateY(-2 * Gdx.graphics.getDeltaTime());
    }
}
