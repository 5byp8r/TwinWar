package mg.cefet.info3.lp2tp2.twinbee;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    public static FitViewport viewport;
    @Override
    public void create() {
        viewport = new FitViewport(8, 8);
        setScreen(new FirstScreen());
    }
}