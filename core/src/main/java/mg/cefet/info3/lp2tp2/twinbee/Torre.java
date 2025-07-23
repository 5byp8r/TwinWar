package mg.cefet.info3.lp2tp2.twinbee;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Torre extends ObjetoMovel {
    static Random random;
    private static Iterator<Torre> iterator;
    private Tiro tiro;

    public Torre() {
        super(new Texture("Texturas/Torre.png"));
        random = new Random();
        tiro = new Tiro(new Texture("Texturas/Tiro.png"), 20, 8);
    }

    public static void mover(ArrayList<Torre> torres, boolean isFundoMovendo, Player player, Tiro[] tiros) {
        for (Torre torre : torres) {
            torre.sprite.translateY(-0.5f * Gdx.graphics.getDeltaTime());
            torre.tiro.mover(-10);
        }
        updateTiro(torres, isFundoMovendo, player);
        updateTorre(torres, isFundoMovendo, tiros);
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
            torre.sprite.setPosition(random.nextFloat(4) + 3, random.nextFloat(10) + 10);
        }
    }

    private static void updateTiro(ArrayList<Torre> torres, boolean isFundoMovendo, Player player) {
        for (Torre torre : torres) {
            if (torre.sprite.getY() < 7 || torre.sprite.getY() < -2) {
                torre.tiro.sprite.setX(torre.sprite.getX());
            }
            if (torre.tiro.sprite.getBoundingRectangle().overlaps(player.sprite.getBoundingRectangle())) {
                System.out.println("Dano tomado");
            }
        }
    }

    private static void updateTorre(ArrayList<Torre> torres, boolean isFundoMovendo, Tiro[] tiros) {
        iterator = torres.iterator();
        while (iterator.hasNext()) {
            Torre torre = iterator.next();

            if (torre.sprite.getY() <= -2) {
                iterator.remove();
            }
            if (!isFundoMovendo) {
                iterator.remove();
                System.out.println("Torre deletada");
            }
            for (Tiro tiro : tiros) {
                if (tiro == null ? false : (tiro.sprite.getBoundingRectangle().overlaps(torre.sprite.getBoundingRectangle()))) {
                    iterator.remove();
                    tiro.sprite.setX(15);
                    System.out.println("Torre morta");
                    break;
                }
            }
        }
    }

    @Override
    public void mover() {
    }
}
