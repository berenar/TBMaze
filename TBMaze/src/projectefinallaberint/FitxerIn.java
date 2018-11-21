/**
 * Classe que emmagatzema el tractament dels fitxers d'entrada.
 * Aqui es poden veure tots els elements de cració i tancament de fitxers.
 */
package projectefinallaberint;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @authors Toni Boutaour, Bernat Pericàs
 * @DNIs 43202227Q, 43212796M
 */
public class FitxerIn {

    private FileReader fr;
    private BufferedReader br;

    /**
     * Mètode que obre els fluxes d'entrada per un fitxer
     *
     * @param nom del fitxer
     */
    public FitxerIn(String nom) {
        try {
            fr = new FileReader(nom);
            br = new BufferedReader(fr);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    /**
     * Metode per a tancar els fluxes d'entrada
     */
    public void tancarFitxer() {
        try {
            fr.close();
            br.close();
        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    /**
     * Get del br
     *
     * @return BufferedReader del fitxer
     */
    public BufferedReader getBr() {
        return br;
    }
}
