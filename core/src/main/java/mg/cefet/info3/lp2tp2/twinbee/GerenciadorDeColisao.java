package mg.cefet.info3.lp2tp2.twinbee;

import java.util.ArrayList;
import java.util.Iterator;

public class GerenciadorDeColisao {
    public static void checarColisao(ArrayList<Atirador> atiradores, Player player) {
        Iterator<Atirador> iterator = atiradores.iterator();
        while (iterator.hasNext()) {
            Atirador movel = iterator.next();
            
            if (player.sprite.getBoundingRectangle().overlaps(movel.sprite.getBoundingRectangle())) {
                iterator.remove();
                System.out.println("Dano tomado");
            }

            for (Tiro tiro : movel.getTiros()) {
                if (tiro != null && tiro.sprite.getBoundingRectangle().overlaps(player.sprite.getBoundingRectangle())) {
                    System.out.println("Dano tomado");
                    tiro.sprite.setPosition(10, 0);
                    break;
                }
            }

            for (Tiro tiro : player.getTiros()) {
                if (tiro != null && tiro.sprite.getBoundingRectangle().overlaps(movel.sprite.getBoundingRectangle())) {
                    iterator.remove();
                    tiro.sprite.setX(15);
                    System.out.println("Móvel morto");
                    break;
                }
            }
        }
    }
}
