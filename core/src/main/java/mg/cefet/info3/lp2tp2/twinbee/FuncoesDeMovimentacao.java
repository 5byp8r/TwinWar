package mg.cefet.info3.lp2tp2.twinbee;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;

public class FuncoesDeMovimentacao {
    private int tipoMovimento;
    private Vector2 pontoA, pontoB;

    public FuncoesDeMovimentacao(int tipoMovimento, Vector2 posPlayer){
        this.tipoMovimento = tipoMovimento;
        
        if (tipoMovimento == 0) {           
            pontoA = new Vector2(new Random().nextInt(6) + 1, 9);
            pontoB = posPlayer.cpy();
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