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
                player.vida--;
                System.out.println("Dano tomado");
            }

            for (Tiro tiro : movel.getTiros()) {
                if (tiro != null && tiro.sprite.getBoundingRectangle().overlaps(player.sprite.getBoundingRectangle())) {
                    System.out.println("Dano tomado");
                    player.vida--;
                    tiro.sprite.setPosition(10, 0);
                    break;
                }
            }

            for (Tiro tiro : player.getTiros()) {
                if (tiro != null && tiro.sprite.getBoundingRectangle().overlaps(movel.sprite.getBoundingRectangle())) {
                    movel.vida--;
                    if (movel.vida <= 0){
                        iterator.remove();
                    }
                    tiro.sprite.setX(15);
                    System.out.println("Móvel morto");
                    break;
                }
            }
            
            if ((movel.sprite.getX() >= 9 || movel.sprite.getX() <= -1 || movel.sprite.getY() <= -1) && movel.sprite.getY() <= 7) {
                iterator.remove();
                System.out.println("Móvel desespawnou");
                System.out.println("Atiradores: " + atiradores.size());
            }
        }
    }
}
