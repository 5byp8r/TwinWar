package mg.cefet.info3.lp2tp2.twinbee;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.Texture;

public class Player extends ObjetoMovel {
    private Tiro[] tiros;

    public Player(Texture texture) {
        super(texture);
        tiros = new Tiro[10];
        for (int i = 0; i < tiros.length; i++) {
            tiros[i] = new Tiro(new Texture("Texturas/Tiro.png"), 20, 8);
        }
    }

    @Override
    public void mover() {
        Controller manete = Controllers.getCurrent();

        if (Gdx.input.isKeyPressed(Keys.LEFT) || (manete == null ? false
                : (manete.getAxis(manete.getMapping().axisLeftX) < 0)))
            sprite.translateX(-3 * Gdx.graphics.getDeltaTime());

        if (Gdx.input.isKeyPressed(Keys.RIGHT) || (manete == null ? false
                : (manete.getAxis(manete.getMapping().axisLeftX) > 0)))
            sprite.translateX(3 * Gdx.graphics.getDeltaTime());

        if (Gdx.input.isKeyPressed(Keys.DOWN) || (manete == null ? false
                : (manete.getAxis(manete.getMapping().axisLeftY) > 0.5)))
            sprite.translateY(-3 * Gdx.graphics.getDeltaTime());

        if (Gdx.input.isKeyPressed(Keys.UP) || (manete == null ? false
                : (manete.getAxis(manete.getMapping().axisLeftY) < 0)))
            sprite.translateY(3 * Gdx.graphics.getDeltaTime());

        // make sure the bucket stays within the screen bounds
        if (sprite.getX() < 0)
            sprite.setX(0);
        if (sprite.getX() > Main.viewport.getWorldWidth() - sprite.getWidth())
            sprite.setX(Main.viewport.getWorldWidth() - sprite.getWidth());
        if (sprite.getY() < 0)
            sprite.setY(0);
        if (sprite.getY() > Main.viewport.getWorldHeight() - sprite.getHeight())
            sprite.setY(Main.viewport.getWorldHeight() - sprite.getHeight());
    }

    public void atirar() {
        if (Gdx.input.isKeyPressed(Keys.SPACE) || (Controllers.getCurrent() == null ? false
                : (Controllers.getCurrent().getButton(Controllers.getCurrent().getMapping().buttonA)))) {
            for (Tiro tiro : tiros) {
                if (tiro != null) {
                    tiro.sprite.setPosition(sprite.getX() + sprite.getWidth()/2 - tiro.sprite.getWidth()/2, sprite.getY());
                }
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
