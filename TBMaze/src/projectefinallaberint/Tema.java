/**
 * Aquesta classe se realitza per tenir d'una forma organitzada l'estil del
 * laberint de manera que tots els elements d'estil estan emmagatzemats dins un
 * ARRAY i realitzar d'una forma més compacta l'edició de l'estil del laberint.
 */
package projectefinallaberint;

import java.awt.Color;

/**
 * @authors Toni Boutaour, Bernat Pericàs
 * @DNIs 43202227Q, 43212796M
 */
public class Tema {

    private final Object[] estil = new Object[4];

    /**
     * Constructor de temes
     *
     * @param cF Color del Fons
     * @param cP Color de la Paret
     * @param fm nom de la Fitxa de Moviment
     * @param fS nom de la Fitxa de Sortida
     */
    public Tema(Color cF, Color cP, String fm, String fS) {
        estil[0] = (Object) cF;
        estil[1] = cP;
        estil[2] = fm;
        estil[3] = fS;
    }

    //Getters i setters necessaris
    
    public Color getColorFons() {
        return (Color) estil[0];
    }

    public Color getColorParet() {
        return (Color) estil[1];
    }

    public String getFitxaMoviment() {
        return (String) estil[2];
    }

    public String getFitxaSortida() {
        return (String) estil[3];
    }

    public void setColorFons(Color cF) {
        this.estil[0] = cF;
    }

    public void setColorParet(Color cP) {
        this.estil[1] = cP;
    }

    public void setFitxaMoviment(String s) {
        this.estil[2] = s;
    }

    public void setFitxaSortida(String s) {
        this.estil[3] = s;
    }
}
