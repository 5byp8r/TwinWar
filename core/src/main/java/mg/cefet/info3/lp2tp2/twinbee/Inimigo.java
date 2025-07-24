package mg.cefet.info3.lp2tp2.twinbee;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Inimigo extends Atirador {
    enum TipoInimigo {
        ESTATICO(3,1),
        DINAMICO(5,1),
        CHEFE(1,10);

        public int quantidade;
        public int vidas;

        TipoInimigo(int quantidade, int vidas) {
            this.quantidade = quantidade;
            this.vidas = vidas;
        }
    }

    private MovimentacaoInimigo movimento;
    private Sprite playerSprite;
    private Vector2 posicao;

    public Inimigo(Texture texture, Sprite playerSprite, TipoInimigo tipo, int ordem, Integer random) {
        super(texture, 10);
        this.playerSprite = playerSprite;
        posicao = new Vector2(0, 9 + ordem);
        movimento = new MovimentacaoInimigo(tipo,
                new Vector2(playerSprite.getBoundingRectangle().getX(), playerSprite.getBoundingRectangle().getY()),
                posicao, ordem, random);
    }

    @Override
    public void mover() {
        movimento.verificacaoDinamica(
                new Vector2(playerSprite.getBoundingRectangle().getX(), playerSprite.getBoundingRectangle().getY()));
        movimento.pegaPosicao();
        sprite.setPosition(posicao.x, posicao.y);

        for (Tiro tiro : tiros) {
            if (tiro != null) {
                tiro.mover(-10);
            }
        }
    }

    @Override
    public void atirar() {
        if (sprite.getY() < 7)
            super.atirar();
    }

    public static void criarInimigo(ArrayList<Atirador> atiradores, Sprite playerSprite, TipoInimigo tipo) {
        Texture texturaInimigo;
        switch (tipo) {
            case DINAMICO:
                texturaInimigo = new Texture("Texturas/inimigoDinamico.png");
                break;
            case ESTATICO:
                texturaInimigo = new Texture("Texturas/inimigoEstatico.png");
                break;
            case CHEFE:
                texturaInimigo = new Texture("Texturas/chefe.png");
                break;
            default: 
                texturaInimigo = null;
                break;
        }

        Integer random = new Random().nextInt(6) + 1;

        for (int i = 0; i < tipo.quantidade; i++) {
            atiradores.add(new Inimigo(texturaInimigo, playerSprite, tipo, i, random));
        }
    }
}
