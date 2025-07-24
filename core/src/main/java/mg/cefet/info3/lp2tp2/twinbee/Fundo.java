package mg.cefet.info3.lp2tp2.twinbee;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Fundo {
    private Texture backgroundTexture;
    private Sprite backgroundSprite;
    private float worldWidth;
    private float worldHeight;
    private float fundoSpeed;
    private boolean mover;

    public Fundo() {
        if (Main.numeroFase == 1) {
            backgroundTexture = new Texture("Texturas/FundoPrimeiraFase.png");
        }
        if (Main.numeroFase == 2) {
            backgroundTexture = new Texture("Texturas/FundoSegundaFase.png");
        }

        backgroundSprite = new Sprite(backgroundTexture);
        worldWidth = Main.viewport.getWorldWidth();
        worldHeight = Main.viewport.getWorldHeight();
        fundoSpeed = -0.5f;
        mover = true;
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
        else {
            mover = false;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            backgroundSprite.translateY(fundoSpeed * 1);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            backgroundSprite.translateY(fundoSpeed * -1);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.F)) {
            Main.numeroFase = 2;
        }

    }

    public void dispose() {
        // Destroy screen's assets here.
        if (backgroundTexture != null) {
            backgroundTexture.dispose();
        }
    }

    public Texture getBackgroundTexture() {
        return backgroundTexture;
    }

    public Sprite getBackgroundSprite() {
        return backgroundSprite;
    }

    public float getWorldWidth() {
        return worldWidth;
    }

    public float getWorldHeight() {
        return worldHeight;
    }

    public float getFundoSpeed() {
        return fundoSpeed;
    }

    public boolean isMover() {
        return mover;
    }
}
