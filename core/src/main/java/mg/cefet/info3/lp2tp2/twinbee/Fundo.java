package mg.cefet.info3.lp2tp2.twinbee;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Fundo {
    private Texture backgroundTexture;
    private Sprite backgroundSprite;
    private float worldWidth;
    private float worldHeight;
    private float fundoSpeed;

    public Fundo() {
        backgroundTexture = new Texture("Texturas/FundoPrimeiraFase.png");
        backgroundSprite = new Sprite(backgroundTexture);

        worldWidth = Main.viewport.getWorldWidth();
        worldHeight = Main.viewport.getWorldHeight();
        fundoSpeed = -0.5f;
        drawInicial();
    }

    private void drawInicial() {
        Main.batch.begin();
        Main.batch.setProjectionMatrix(Main.viewport.getCamera().combined);
        Main.viewport.apply();
        Main.batch.draw(backgroundSprite, 0, 8, worldWidth, worldHeight);
        Main.batch.end();
    }

    public void draw() {
        Main.viewport.apply();
        Main.batch.draw(backgroundSprite, backgroundSprite.getX(), backgroundSprite.getY(), worldWidth,
                worldHeight * 3);
    }

    public void mover() {

        if (!(backgroundSprite.getY() <= -16)) {
           backgroundSprite.translateY(-0.5f * Gdx.graphics.getDeltaTime());
        } 
        // if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
        // {
        //     backgroundSprite.translateY(fundoSpeed * 10);
        // }

        // if (Gdx.input.isKeyPressed(Input.Keys.UP))
        // {
        //     backgroundSprite.translateY(fundoSpeed * 10 * -1);
        // }
         

    }

    public void dispose() {
        // Destroy screen's assets here.
        if (backgroundTexture != null) {
            backgroundTexture.dispose();
        }
    }
}
