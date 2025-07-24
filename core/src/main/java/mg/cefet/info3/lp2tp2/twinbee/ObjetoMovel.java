package mg.cefet.info3.lp2tp2.twinbee;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class ObjetoMovel {
    protected Sprite sprite;

    public ObjetoMovel(Texture texture) {
        sprite = new Sprite(texture);
        sprite.setSize(Main.viewport.getWorldWidth() * texture.getWidth() / (Main.viewport.getScreenWidth() * 1.5f),
        Main.viewport.getWorldWidth() * texture.getHeight() / (Main.viewport.getScreenHeight() * 1.5f));
        sprite.setCenter(sprite.getWidth()/2, sprite.getHeight()/2);
        sprite.setPosition(0, 0);
    }

    public abstract void mover();

    public void draw() {
        Main.batch.draw(sprite, sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }

    public void dispose(){
        sprite.getTexture().dispose();
    }
}
