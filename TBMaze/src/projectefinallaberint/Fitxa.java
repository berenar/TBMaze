/**
 * Classe que conté els elements relacionats amb la fitxa i tots els atributs
 * d'aquesta.
 *´Ës la classe situada al element més baix de la nostra escala de abstracció.
 */
package projectefinallaberint;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * @authors Toni Boutaour, Bernat Pericàs
 * @DNIs 43202227Q, 43212796M
 */
public class Fitxa {

    private int fitxaX = (int) Math.floor((Math.random()) * Laberint.columnes);
    private int fitxaY = (int) Math.floor((Math.random()) * Laberint.files);

    public static String fitxa = Laberint.estilLab.getFitxaMoviment();
    public static String sortida = Laberint.estilLab.getFitxaSortida();

    private BufferedImage imatge;

    /**
     * Constructor que representa la imatge continguda al paràmetre String.
     *
     * @param s nom del fitxer
     */
    public Fitxa(String s) {
        //Actualutzació dels paràmetres SORTIDA i F
        sortida = Laberint.estilLab.getFitxaSortida();
        fitxa = Laberint.estilLab.getFitxaMoviment();
        try {
            imatge = ImageIO.read(new File(s));
        } catch (IOException ex) {
            System.out.println("Hi ha hagut un problema "
                    + "llegint la imatge de la fitxa");
        }
    }

    /**
     * S'ha realitzat la edició pixel a pixel de les fitxes.
     *
     * @param g objecte grafic
     * @param x coordenada x
     * @param y coordenada y
     */
    public void paintComponent(Graphics g, float x, float y) {
        g.drawImage(imatge, (int) x + 3, (int) y + 3, 80 - 35, 80 - 35, null);
    }
}
