package mg.cefet.info3.lp2tp2.twinbee;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class Sons {
    static Music musica = Gdx.audio.newMusic(Gdx.files.internal("Sons/musica.mp3"));

    Sound explosao = Gdx.audio.newSound(Gdx.files.internal("Sons/Explosion.mp3"));

    Sound bossMorrendo = Gdx.audio.newSound(Gdx.files.internal("Sons/BossDeath.mp3"));

    Sound somTiro = Gdx.audio.newSound(Gdx.files.internal("Sons/Tiro.mp3"));

    public static void startMusic(){
        musica.play();
    }

    public void somDeExplosao(){
        explosao.play();
    }

    public void somDoBossMorrendo(){
        bossMorrendo.play();
    }

    public void somDeTiro(){
        somTiro.play();
    }

    public static void paraMusica(){
        musica.stop();
    }
}
