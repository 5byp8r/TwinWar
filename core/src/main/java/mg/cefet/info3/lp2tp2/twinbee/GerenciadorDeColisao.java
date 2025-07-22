package mg.cefet.info3.lp2tp2.twinbee;

import java.util.ArrayList;

public class GerenciadorDeColisao {
    public static void colisao(ArrayList<ObjetoMovel> moveis) {
        for (int i = 0; i < moveis.size(); i++) {
            for (int j = 0; j < moveis.size(); j++) {
                if (i != j) {
                    if (moveis.get(i).sprite.getBoundingRectangle().overlaps(moveis.get(j).sprite.getBoundingRectangle())) {
                        moveis.set(j, null);
                        moveis.remove(j);
                    }
                }
            }
        }
    }
}
