package mg.cefet.info3.lp2tp2.twinbee;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Tiro extends ObjetoMovel {
    private boolean disparado;
    public Tiro(Texture texture, float x, float y) {
        super(texture);
        sprite.setPosition(x, y);
        disparado = false;
    }

    public void mover(float velocidadeTiro) {
        sprite.translateY(velocidadeTiro * Gdx.graphics.getDeltaTime());
        if (sprite.getY() <= -2 || sprite.getY() >= 8) {
            sprite.setPosition(sprite.getX() + 10, sprite.getY() + 10);
            disparado = false;
        }
    }

    @Override
    public void mover() {
    }

    public static void updateTiros(Tiro[] tiros) {
        for (Tiro tiro : tiros) {
            if (tiro != null) {
                tiro.mover(10);
            }
        }
    }

    public static void drawTiros(Tiro[] tiros) {
        for (Tiro tiro : tiros) {
            if (tiro != null) {
                tiro.draw();
            }
        }
    }

    public boolean isDisparado() {
        return disparado;
    }

    public void setDisparado(boolean disparado) {
        this.disparado = disparado;
    }
    
}
