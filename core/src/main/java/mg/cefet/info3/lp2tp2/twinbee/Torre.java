package mg.cefet.info3.lp2tp2.twinbee;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Torre extends Atirador {
    public static Random random;
    private static Iterator<Torre> iterator;

    public Torre() {
        super(new Texture("Texturas/Torre.png"), 1);
        random = new Random();
        if (Main.numeroFase == 1) {
            sprite.setPosition(random.nextFloat(4) + 3, random.nextFloat(10) + 8);
        }
        if (Main.numeroFase == 2) {
            sprite.setPosition(random.nextFloat(8), random.nextFloat(16) + 8);
        }
    }

    public static void mover(ArrayList<Torre> torres, boolean isFundoMovendo) {
        for (Torre torre : torres) {
            torre.mover();
        }
        updateTorre(torres, isFundoMovendo);
    }

    public static void criarTorres(ArrayList<Atirador> torres, int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            torres.add(new Torre());
        }
    }

    public static void draw(ArrayList<Torre> torres) {
        for (Torre torre : torres) {
            torre.draw();
        }
    }

    // private static void posicionarTorres(ArrayList<Torre> torres) {
    //     for (Torre torre : torres) {
    //         if (Main.numeroFase == 1) {
    //             torre.sprite.setPosition(random.nextFloat(4) + 3, random.nextFloat(10) + 8);
    //         }
    //         if (Main.numeroFase == 2) {
    //             torre.sprite.setPosition(random.nextFloat(8), random.nextFloat(16) + 8);
    //         }
    //     }
    // }

    public static void atirar(ArrayList<Torre> torres, boolean isFundoMovendo) {
        for (Torre torre : torres) {
            //System.out.println();
            torre.atirar();
        }
    }

    public static void updateTorre(ArrayList<Torre> torres, boolean isFundoMovendo) {
        iterator = torres.iterator();
        while (iterator.hasNext()) {
            Torre torre = iterator.next();

            if (torre.sprite.getY() <= -2) {
                iterator.remove();
            }

            if (!isFundoMovendo && Main.numeroFase != 2) {
                iterator.remove();
                System.out.println("Torre deletada");
            }
        }
    }

    @Override
    public void mover() {
        sprite.translateY(-0.5f * Gdx.graphics.getDeltaTime());
        for (Tiro tiro : tiros) {
            if (tiro != null) {
                tiro.mover(-10);
            }
        }
    }

    @Override
    public void atirar() {
        if (sprite.getY() < 7)
            super.atirar();
    }

    public static Random getRandom() {
        return random;
    }

    public static void setRandom(Random random) {
        Torre.random = random;
    }

    public static Iterator<Torre> getIterator() {
        return iterator;
    }

    public static void setIterator(Iterator<Torre> iterator) {
        Torre.iterator = iterator;
    }
}
