package mg.cefet.info3.lp2tp2.twinbee;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import mg.cefet.info3.lp2tp2.twinbee.Inimigo.TipoInimigo;

public class MovimentacaoInimigo {
    private TipoInimigo tipoMovimento;
    private Vector2 pontoRandom, pontoPlayer, posicaoInimigo;
    private int desvio, ordem, direcao;

    public MovimentacaoInimigo(TipoInimigo tipoMovimento, Vector2 posicaoPlayer, Vector2 posicaoInimigo, int ordem,
            Integer random) {
        this.tipoMovimento = tipoMovimento;
        this.posicaoInimigo = posicaoInimigo;
        this.ordem = ordem;
        desvio = 0;
        direcao = 1;
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
                } else {
                    posicaoInimigo.x += 3 * Gdx.graphics.getDeltaTime() * desvio;
                    posicaoInimigo.y = ((posicaoInimigo.x - pontoPlayer.x) * (pontoRandom.y - pontoPlayer.y))
                            / (pontoRandom.x - pontoPlayer.x) + pontoPlayer.y;
                }
                break;
            case ESTATICO:
                posicaoInimigo.y -= 3 * Gdx.graphics.getDeltaTime();
                switch (ordem) {
                    case 0:
                        posicaoInimigo.x = 1;
                        break;
                    case 1:
                        posicaoInimigo.x = 3;
                        break;
                    case 2:
                        posicaoInimigo.x = 5;
                        break;
                    case 3:
                        posicaoInimigo.x = 7;
                        break;
                    default:
                        break;
                }
                break;
            case CHEFE:
                if (posicaoInimigo.x >= 6.28) {
                    direcao = -1;
                }
                else if (posicaoInimigo.x <= 0) {
                    direcao = 1;
                }
                posicaoInimigo.x += direcao * 3 * Gdx.graphics.getDeltaTime();
                posicaoInimigo.y = -direcao * MathUtils.sin(-posicaoInimigo.x) + 5;
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