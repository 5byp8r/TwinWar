package mg.cefet.info3.lp2tp2.twinbee;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class Menu implements Screen{

    Main game;
    Texture backgroundTexture;

    public Menu(Main game) {
        this.game = game; 
    }
    public void startGame(){
        if (Gdx.input.isKeyPressed(Keys.ENTER)){
            game.setScreen(new FirstScreen());
            dispose();
        }
    }

    @Override
    public void render(float delta) {
        startGame();

        ScreenUtils.clear(Color.BLACK);
        Main.batch.begin();
        Main.batch.setProjectionMatrix(Main.viewport.getCamera().combined);
        game.batch.draw(backgroundTexture, 0, 0, game.viewport.getWorldWidth(), game.viewport.getWorldHeight());
        Main.batch.end();
    }

    @Override
    public void show() {
        backgroundTexture = new Texture("Texturas/AviaoGuerra.jpg");
    }

    @Override
    public void resize(int width, int height) {
        // If the window is minimized on a desktop (LWJGL3) platform, width and height
        // are 0, which causes problems.
        // In that case, we don't resize anything, and wait for the window to be a
        // normal size before updating.
        if (width <= 0 || height <= 0){
            return;
        }
            
        if (width > 0 && height > 0) {
            Main.viewport.update(width, height, true);
        }
        // Resize your screen here. The parameters represent the new window size.
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
