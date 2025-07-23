package mg.cefet.info3.lp2tp2.twinbee;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;

public class Menu implements Screen{

    public static Main game;
    Texture backgroundTexture;
    private Stage stage;
    private Skin skin;
    private TextButton button;

    public Menu(Main game) {
        this.game = game; 
    }

    public void button() {
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        button = new TextButton("Start Game", skin);
        button.setSize(200, 50);
        button.setPosition(Gdx.graphics.getWidth() / 2f - 100, Gdx.graphics.getHeight() - 700);

        button.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new FirstScreen());
                dispose();
            }
        });

        stage.addActor(button);
    }

    @Override
    public void render(float delta) {
        logic();
        ScreenUtils.clear(Color.BLACK);
        Main.batch.begin();
        Main.batch.setProjectionMatrix(Main.viewport.getCamera().combined);
        game.batch.draw(backgroundTexture, 0, 0, game.viewport.getWorldWidth(), game.viewport.getWorldHeight());
        Main.batch.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        button();
    }

    private void logic(){
        if (Controllers.getCurrent() == null ? false : (Controllers.getCurrent().getButton(Controllers.getCurrent().getMapping().buttonStart))) {
            for (com.badlogic.gdx.scenes.scene2d.EventListener listener : button.getListeners()) {
                ((ClickListener)listener).clicked(null, 0, 0);
            }
        }
    }

    @Override
    public void show() {
        backgroundTexture = new Texture("Texturas/AviaoGuerra.jpg");
        stage = new Stage();
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
