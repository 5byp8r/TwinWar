package mg.cefet.info3.lp2tp2.twinbee;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Player extends Atirador {

    private int score;
    final BitmapFont font;
    Main game;

    public Player(Texture texture, Main game) {
        super(texture, 10);
        sprite.setPosition((Main.viewport.getWorldWidth() / 2) - 0.5f,2);
        this.game = game;
        this.vida = 5;
        font = new BitmapFont();
		font.setUseIntegerPositions(false);
		font.getData().setScale(game.viewport.getWorldHeight() / Gdx.graphics.getHeight());
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

        if (sprite.getX() < 0)
            sprite.setX(0);
        if (sprite.getX() > Main.viewport.getWorldWidth() - sprite.getWidth())
            sprite.setX(Main.viewport.getWorldWidth() - sprite.getWidth());
        if (sprite.getY() < 0)
            sprite.setY(0);
        if (sprite.getY() > Main.viewport.getWorldHeight() - sprite.getHeight())
            sprite.setY(Main.viewport.getWorldHeight() - sprite.getHeight());
    }

    @Override
    public void atirar() {
        if (Gdx.input.isKeyPressed(Keys.SPACE) || (Controllers.getCurrent() == null ? false
                : (Controllers.getCurrent().getButton(Controllers.getCurrent().getMapping().buttonA)))) {
            super.atirar();
        }
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score){
        this.score += score;
    }

    public int getVida(){
        return vida;
    }

    public void showStats(){
        font.setColor(Color.PURPLE);
        font.draw(game.batch,"Vida: " + this.getVida() + "\nScore: " + this.getScore(), 0, 8);
    }

}
