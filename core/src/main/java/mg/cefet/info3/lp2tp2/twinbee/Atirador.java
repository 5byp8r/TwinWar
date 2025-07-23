package mg.cefet.info3.lp2tp2.twinbee;

import com.badlogic.gdx.graphics.Texture;

public abstract class Atirador extends ObjetoMovel {
    protected Tiro[] tiros;
    protected int proximoTiro;

    public Atirador(Texture texture, int numTiros) {
        super(texture);

        tiros = new Tiro[numTiros];
        for (int i = 0; i < tiros.length; i++) {
            tiros[i] = new Tiro(new Texture("Texturas/Tiro.png"), 20, 8);
        }
        proximoTiro = 0;
    }

    public void atirar() {
        Tiro tiro = tiros[proximoTiro];
        if (!tiro.isDisparado()) {
            tiro.sprite.setPosition(sprite.getX() + sprite.getWidth() / 2 - tiro.sprite.getWidth() / 2,
                    sprite.getY());
            tiro.setDisparado(true);
            proximoTiro = (proximoTiro + 1) % tiros.length;
        }
    }

    public void draw() {
        Main.batch.draw(sprite, sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        for (Tiro tiro : tiros) {
            if (tiro != null) {
                tiro.draw();
            }
        }
    }

    public Tiro[] getTiros() {
        return tiros;
    }

    public void setTiros(Tiro[] tiros) {
        this.tiros = tiros;
    }
}
