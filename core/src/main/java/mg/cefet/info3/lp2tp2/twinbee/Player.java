package mg.cefet.info3.lp2tp2.twinbee;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;

public class Player extends ObjetoMovel {
    public Player(Texture texture) {
        super(texture);
    }

    @Override
    public void mover() {
        //Controller manete = Controllers.getCurrent();

        if (Gdx.input.isKeyPressed(Keys.LEFT)){
            //    || Controllers.getCurrent().getAxis(Controllers.getCurrent().getMapping().axisLeftX) < 0)
            sprite.translateX(-3 * Gdx.graphics.getDeltaTime());
        }
            
        if (Gdx.input.isKeyPressed(Keys.RIGHT)){
            //|| Controllers.getCurrent().getAxis(Controllers.getCurrent().getMapping().axisLeftX) > 0)
            sprite.translateX(3 * Gdx.graphics.getDeltaTime());
        }
                
        if (Gdx.input.isKeyPressed(Keys.DOWN)){
            //    || Controllers.getCurrent().getAxis(Controllers.getCurrent().getMapping().axisLeftY) > 0.5)
            sprite.translateY(-3 * Gdx.graphics.getDeltaTime());
        }
            
        if (Gdx.input.isKeyPressed(Keys.UP)){
            //    || Controllers.getCurrent().getAxis(Controllers.getCurrent().getMapping().axisLeftY) < 0)
            sprite.translateY(3 * Gdx.graphics.getDeltaTime());
        }
            

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

    public void atirar(ArrayList<Tiro> tiros) {
        if (Gdx.input.isKeyPressed(Keys.SPACE)){
            //    || Controllers.getCurrent().getButton(Controllers.getCurrent().getMapping().buttonA))
            tiros.add(new Tiro(new Texture("Texturas/lula.jpg"), sprite.getX(), sprite.getY()));
        }
            
    }
}
