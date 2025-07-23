package mg.cefet.info3.lp2tp2.twinbee;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import mg.cefet.info3.lp2tp2.twinbee.Inimigo.TipoInimigo;

public class MovimentacaoInimigo {
    private TipoInimigo tipoMovimento;
    private Vector2 pontoRandom, pontoPlayer, posicaoInimigo;
    private int desvio;

    public MovimentacaoInimigo(TipoInimigo tipoMovimento, Vector2 posicaoPlayer, Vector2 posicaoInimigo, int ordem, Integer random) {
        this.tipoMovimento = tipoMovimento;
        this.posicaoInimigo = posicaoInimigo;
        desvio = 0;
        if (tipoMovimento == TipoInimigo.DINAMICO) {
            pontoRandom = new Vector2(random, 9 + ordem);
            atualizaPosicaoPlayer(posicaoPlayer);
        }
    }

    public void pegaPosicao() {
        switch (tipoMovimento) {
            case DINAMICO:
                if (desvio == 0) {
                    posicaoInimigo.y -= 3 * Gdx.graphics.getDeltaTime();
                    posicaoInimigo.x = ((posicaoInimigo.y - pontoPlayer.y) * (pontoRandom.x - pontoPlayer.x))
                            / (pontoRandom.y - pontoPlayer.y) + pontoPlayer.x;
                }
                else {
                    posicaoInimigo.x += 3 * Gdx.graphics.getDeltaTime() * desvio;
                    posicaoInimigo.y = ((posicaoInimigo.x - pontoPlayer.x) * (pontoRandom.y - pontoPlayer.y))
                            / (pontoRandom.x - pontoPlayer.x) + pontoPlayer.y;
                }
                break;
            default:
                break;
        }

    }

    public void atualizaPosicaoPlayer(Vector2 posPlayer) {
        pontoPlayer = posPlayer.cpy();
    }

    public void verificacaoDinamica(Vector2 posPlayer) {
        if (desvio == 0 && posPlayer.y > posicaoInimigo.y) {
            pontoRandom = posicaoInimigo.cpy();
            atualizaPosicaoPlayer(posPlayer);
            if (posPlayer.x >= posicaoInimigo.x) {
                desvio = 1;
            } else {
                desvio = -1;
            }
        }
    }
}