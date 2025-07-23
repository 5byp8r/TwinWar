package mg.cefet.info3.lp2tp2.twinbee;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    public static FitViewport viewport;
    public static SpriteBatch batch;
    public static int numeroFase;
    
    @Override
    public void create() {
        numeroFase = 1;
        batch = new SpriteBatch();
        viewport = new FitViewport(8, 8);
        viewport.setScreenSize(800, 800);
        batch.setProjectionMatrix(Main.viewport.getCamera().combined);
        setScreen(new Menu(this));
        
    }

    @Override
    public void dispose() {
        batch.dispose();
        super.dispose();
    }
}