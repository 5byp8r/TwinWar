package mg.cefet.info3.lp2tp2.twinbee;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;

public class FuncoesDeMovimentacao {
    private int tipoMovimento;
    private Vector2 pontoA, pontoB;

    public FuncoesDeMovimentacao(int tipoMovimento){
        this.tipoMovimento = tipoMovimento;
        
        if (tipoMovimento == 0) {
            Random rand = new Random();
            int a, b;
            do {
                a = rand.nextInt(6) + 1;
                b = rand.nextInt(6) + 1;
            } while (a - b < 4 && a - b > -4);
            pontoA = new Vector2(a, 9);
            pontoB = new Vector2(b, -1);
        }
    }

    public float pegaPosicao(float posicao) {
        switch (tipoMovimento) {
        case 1:
        return (posicao*posicao - 2*posicao + 26)/10;
        case 2:
        return (posicao*posicao - 8*posicao + 41)/10;
        case 3:
        return (posicao*posicao - 2*posicao + 17)/8;
        default:
        return ((posicao - pontoB.y) * (pontoA.x - pontoB.x)) / (pontoA.y - pontoB.y) + pontoB.x;
        }
    }
}