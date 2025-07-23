package mg.cefet.info3.lp2tp2.twinbee;

import java.util.ArrayList;
import java.util.Iterator;

public class GerenciadorDeColisao {
    // public static void colisao(ArrayList<ObjetoMovel> moveis) {
    // for (int i = 0; i < moveis.size(); i++) {
    // for (int j = 0; j < moveis.size(); j++) {
    // if (i != j) {
    // if
    // (moveis.get(i).sprite.getBoundingRectangle().overlaps(moveis.get(j).sprite.getBoundingRectangle()))
    // {
    // moveis.set(j, null);
    // moveis.remove(j);
    // }
    // }
    // }
    // }
    // }

    public static void checarColisaoTorre(ArrayList<Torre> torres, Player player) {
        Iterator<Torre> iterator = torres.iterator();
        while (iterator.hasNext()) {
            Torre torre = iterator.next();

            if (torre.getTiro().sprite.getBoundingRectangle().overlaps(player.sprite.getBoundingRectangle())) {
                System.out.println("Dano tomado");
                torre.getTiro().sprite.setPosition(10, 0);
            }

            for (Tiro tiro : player.getTiros()) {
                if (tiro != null && tiro.sprite.getBoundingRectangle().overlaps(torre.sprite.getBoundingRectangle())) {
                    iterator.remove();
                    tiro.sprite.setX(15);
                    System.out.println("Torre morta");
                    break;
                }
            }
        }
    }

    public static void checarColisaoInimigo(Inimigo inimigo, Player player) {

        if (player.sprite.getBoundingRectangle().overlaps(inimigo.sprite.getBoundingRectangle())) {
            System.out.println("Dano tomado pelo inimigo");
            inimigo.sprite.setPosition(10, 0);
        }
        for (Tiro tiro : player.getTiros()) {
            if (tiro != null && tiro.sprite.getBoundingRectangle().overlaps(inimigo.sprite.getBoundingRectangle())) {
                tiro.sprite.setX(15);
                System.out.println("Inimigo morto");
                inimigo.sprite.setPosition(10, 0);
                break;
            }
        }
    }
}
