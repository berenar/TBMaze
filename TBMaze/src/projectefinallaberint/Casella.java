/**
 * Classe que presenta totes les característiques tant físiques com lógiques de
 * la casella.
 * Aquesta casella es tracta d'un quadrat pintat segons es desitja i que, a més
 * conté o no les parets corresponents que constitueixen el laberint.
 * Cal destacar la inserció d'un objecte fitxa que permetrà la interacció entre
 * aquest dos elements i la capacitat de moviment de la fitxa principal a partir
 * del booleà "ocupada".
 */
package projectefinallaberint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 * @authors Toni Boutaour, Bernat Pericàs
 * @dnis 43202227Q, 43212796M
 */
public class Casella {

    private final Rectangle2D.Float rect;
    private final Color col;
    private boolean ocupada;
    private Fitxa fitxa;

    private final boolean nord, est, sud, oest;

    private final int x, y;
    private final int costat = Laberint.getCostat();

    /**
     * Constructor encarregat de definir tots els atributs d'aquesta clase.
     *
     * @param r rectangle
     * @param x coordenada x
     * @param y coordenada y
     * @param nord paret adalt
     * @param est paret dreta
     * @param sud paret abaix
     * @param oest paret esquerra
     * @param c
     */
    public Casella(Rectangle2D.Float r, int x, int y,
            boolean nord, boolean est, boolean sud, boolean oest, Color c) {
        this.rect = r;
        this.x = x;
        this.y = y;
        this.nord = nord;
        this.est = est;
        this.sud = sud;
        this.oest = oest;
        this.fitxa = null;
        this.col = c;
    }

    /**
     * Aquest PaintComponent s'encarrega de pintar tant el fons com les linies
     * que conformen el laberint. La inserció o no de la paret ve donada per els
     * atributs de posició (Nord, est,sud,oest).
     *
     * @param g objecte grafic
     */
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.col);
        g2d.fill(this.rect);
//---------------PINTAR LINIES---------------------------------
        g2d.setColor(Laberint.estilLab.getColorParet());
        g2d.setStroke(new BasicStroke(10));
        if (nord == true) {
            g2d.drawLine(x, y, x + costat, y);
        }
        if (est == true) {
            g2d.drawLine(x + costat, y, x + costat, y + costat);
        }
        if (sud == true) {
            g2d.drawLine(x, y + costat, x + costat, y + costat);
        }
        if (oest == true) {
            g2d.drawLine(x, y, x, y + costat);
        }
        if (this.ocupada) {
            this.fitxa.paintComponent(g, x, y);
        }
    }

    //Getters i setters dels diferents atributs de la classe.
    public void setFitxa(Fitxa f) {
        this.fitxa = f;
        this.ocupada = true;
    }

    public void foraFitxa() {
        this.ocupada = false;
    }

    public boolean muradaNord() {
        return this.nord;
    }

    public boolean muradaEst() {
        return this.est;
    }

    public boolean muradaSud() {
        return this.sud;
    }

    public boolean muradaOest() {
        return this.oest;
    }
}
