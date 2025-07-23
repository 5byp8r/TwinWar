package mg.cefet.info3.lp2tp2.twinbee;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Torre extends ObjetoMovel {
    public static Random random;
    private static Iterator<Torre> iterator;
    private Tiro tiro;

    public Torre() {
        super(new Texture("Texturas/Torre.png"));
        random = new Random();
        tiro = new Tiro(new Texture("Texturas/Tiro.png"), 20, 8);
    }

    public static void mover(ArrayList<Torre> torres, boolean isFundoMovendo) {
        for (Torre torre : torres) {
            torre.sprite.translateY(-0.5f * Gdx.graphics.getDeltaTime());
            torre.tiro.mover(-10);
        }
        updateTorre(torres, isFundoMovendo);
    }

    public static void criarTorres(ArrayList<Torre> torres, int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            torres.add(new Torre());
        }
        posicionarTorres(torres);
    }

    public static void draw(ArrayList<Torre> torres) {
        for (Torre torre : torres) {
            Main.batch.draw(torre.sprite, torre.sprite.getX(), torre.sprite.getY(), torre.sprite.getWidth(),
                    torre.sprite.getHeight());
            torre.tiro.draw();
        }
    }

    private static void posicionarTorres(ArrayList<Torre> torres) {
        for (Torre torre : torres) {
            if (Main.numeroFase == 1) {
                torre.sprite.setPosition(random.nextFloat(4) + 3, random.nextFloat(10) + 8);
            }
            if (Main.numeroFase == 2) {
                torre.sprite.setPosition(random.nextFloat(8), random.nextFloat(16) + 8);
            }

        }
    }

    public static void atirar(ArrayList<Torre> torres, boolean isFundoMovendo) {
        for (Torre torre : torres) {
            System.out.println();
            if (torre.sprite.getY() < 7) {
                if (!torre.tiro.isDisparado()) {
                    torre.tiro.sprite.setPosition(
                            torre.sprite.getX() + torre.sprite.getWidth() / 2 - torre.tiro.sprite.getWidth() / 2,
                            torre.sprite.getY());
                    torre.tiro.setDisparado(true);
                }
            }
        }
    }

    private static void updateTorre(ArrayList<Torre> torres, boolean isFundoMovendo) {
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

    public Tiro getTiro() {
        return tiro;
    }

    public void setTiro(Tiro tiro) {
        this.tiro = tiro;
    }
}
