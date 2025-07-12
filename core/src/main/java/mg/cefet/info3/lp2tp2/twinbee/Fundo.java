package mg.cefet.info3.lp2tp2.twinbee;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Fundo {
    private SpriteBatch spriteBatch;
    private Texture backgroundTexture;
    private Sprite backgroundSprite;
    private float worldWidth;
    private float worldHeight;
    private float fundoSpeed;

    public Fundo() {
        spriteBatch = new SpriteBatch();
        backgroundTexture = new Texture("Texturas/FundoPrimeiraFase.png");
        backgroundSprite = new Sprite(backgroundTexture);

        worldWidth = Main.viewport.getWorldWidth();
        worldHeight = Main.viewport.getWorldHeight();
        fundoSpeed = -0.005f;
        drawInicial();
    }

    private void drawInicial() {
        Main.viewport.apply();
        spriteBatch.setProjectionMatrix(Main.viewport.getCamera().combined);
        spriteBatch.begin();
        spriteBatch.draw(backgroundSprite, 0, 8, worldWidth, worldHeight);
        spriteBatch.end();
    }

    public void draw() {
        Main.viewport.apply();
        spriteBatch.setProjectionMatrix(Main.viewport.getCamera().combined);
        spriteBatch.begin();
        spriteBatch.draw(backgroundSprite, backgroundSprite.getX(), backgroundSprite.getY(), worldWidth,
                worldHeight * 3);

        spriteBatch.end();
    }

    public void mover() {

        if (!(backgroundSprite.getY() <= -16)) {
           backgroundSprite.translateY(fundoSpeed);
        } if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
        {
            backgroundSprite.translateY(fundoSpeed * 10);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP))
        {
            backgroundSprite.translateY(fundoSpeed * 10 * -1);
        }
         

    }

    public void dispose() {
        // Destroy screen's assets here.
        if (spriteBatch != null) {
            spriteBatch.dispose();
        }
        if (backgroundTexture != null) {
            backgroundTexture.dispose();
        }
    }
}
