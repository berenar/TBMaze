/**
 * La classe Laberint es tracta de la classe contenidora dels elements que
 * treballen conjuntament per formar un sol element.
 * Es defineix com un Panell que manté dins un array bidimensional de casselles
 * que posteriorment amb la inseció de la fitxa de moviment i de sortida donaràn
 * la capacitat de jugabilitat.
 * Com atributs principals trobam el costat de la casella, que donarà la
 * distribució espacial al panell i el nombre de files i columnes que deixaran
 * preparat a l'element Laberint per al seu desenvolupament.
 */
package projectefinallaberint;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.Random;
import javax.swing.JPanel;

/**
 * @authors Toni Boutaour, Bernat Pericàs
 * @DNIs 43202227Q, 43212796M
 */
public class Laberint extends JPanel {

    //Cream estilLab com un objecte Tema, que és un array d'objectes per tal de
    //tenir una bona organització d'aquestos.
    public static Tema estilLab = new Tema(Color.BLACK, new Color(0, 0, 139),
            "imatges/pacman.png", "imatges/ghost.png");

    private static final int COSTAT = 50;
    public static int files, columnes;
    private int sortidaY, sortidaX;
    private Casella[][] arrLab;
    private Casella casellafinal;
    private String nomLab = "laberints/Maze1.txt";
    private FitxerIn f;
    private int moviments = 0;

    /**
     * Constructor que crita a la creació d'aquest.
     */
    public Laberint() {
        PintaLaberint();

    }

    /**
     * Mètode principal, crea un array bidimensional que donara forma al tauler
     * pricipal del joc, format de la unió dels elements casella. Aquest mètode
     * crea a partir de la lectura del fitxer corresponent al laberint triat la
     * impressió de les seves característiques. Hem valorat llegir la linea i,
     * com sabem la longitud d'aquest ficar-ho completament dins un array que
     * controlarem totalment.
     *
     * Se ha realitzat el tractament dels error necesaris per a la millor
     * experiència possible per l'usuari.
     */
    public void PintaLaberint() {
        //Llegir nombre de files i columnes
        try {
            //Crear fitxer a partir del nom.
            f = new FitxerIn(nomLab);
            Laberint.files = Integer.parseInt(f.getBr().readLine());
            Laberint.columnes = Integer.parseInt(f.getBr().readLine());
        } catch (IOException | NumberFormatException ex) {
            System.out.println("Error llegint les files i/o columnes");
        }
        System.out.println("#files:" + files + " #columnes:" + columnes);
        //Cream array de Caselles
        arrLab = new Casella[files][columnes];
        int y = 0;
        for (int i = 0; i < files; i++) {
            int x = 0;
            char[] Array = null;

            try {
                Array = LiniadeBits(columnes, f.getBr().readLine());
            } catch (IOException ex) {
                System.out.println("Error llegint una linia del fitxer");
            }

            int contador = 0;
            for (int j = 0; j < columnes; j++) {
                Rectangle2D.Float r = new Rectangle2D.Float(x, y, COSTAT,
                        COSTAT);

                boolean nord = cercaU(Array, contador);
                contador++;
                boolean east = cercaU(Array, contador);
                contador++;
                boolean south = cercaU(Array, contador);
                contador++;
                boolean west = cercaU(Array, contador);
                contador++;

                arrLab[i][j] = new Casella(r, x, y, nord, east, south, west,
                        estilLab.getColorFons());
                x += COSTAT;
            }
            y += COSTAT;
        }
        //LLegir posició sortida
        try {
            //llegir fila(EIX -OY)
            this.sortidaY = Integer.parseInt(f.getBr().readLine());
            //llegir columna(EIX -OX)
            this.sortidaX = Integer.parseInt(f.getBr().readLine());
            casellafinal = arrLab[sortidaY][sortidaX - 1];
        } catch (IOException ex) {
            System.out.println("Error llegint la posició de la sortida");
            System.out.println(ex.getLocalizedMessage());
            Missatge mis = new Missatge("Error llegint el fitxer");
        }

        System.out.println("sortidaFILA: " + sortidaY
                + " sortidaCOLUMNA: " + sortidaX);

        f.tancarFitxer();
    }

    /**
     * Aquest mètode modifica el nom del laberint que s'usa per realitzar el
     * pintat d'aquest.
     *
     * @param g
     */
    public void changeLab(String g) {
        nomLab = g;//Actualitza nom laberint
        PintaLaberint(); //Tornar a dibuixar
        repaint();
    }

    /**
     * Realitza la conversió d'un string a un Array de caràcters.
     *
     * @param columnes
     * @param s
     * @return
     */
    public static char[] LiniadeBits(int columnes, String s) {
        char[] Array = s.toCharArray();
        return Array;
    }

    /**
     * Mètod que retorna true en trobar 1 a la cadena de caràcters passats per
     * paràmetre.
     *
     * @param erre
     * @param i
     * @return
     */
    public static boolean cercaU(char[] erre, int i) {
        return erre[i] == '1';
    }

    /**
     * Impressió de les caselles una a una fins a completar el panell.
     *
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        try {
            for (int i = 0; i < files; i++) {
                for (int j = 0; j < columnes; j++) {
                    arrLab[i][j].paintComponent(g);
                }
            }
        } catch (NullPointerException ex) {
        }
    }

    /**
     * Mètode de col·locació una fitxa dins una casella.
     *
     * @param s nom de la fitxa
     * @param x coordenada x
     * @param y coordenada y
     */
    public void coloca(String s, int x, int y) {
        arrLab[x][y].setFitxa(new Fitxa(s));
    }

    /**
     * Mètode per a l' extracció de la fitxa.
     *
     * @param x
     * @param y
     */
    public void llevaFitxa(int x, int y) {
        arrLab[x][y].foraFitxa();
    }

    /**
     *
     * @return
     */
    public int getSortidaX() {
        return sortidaY;
    }

    public int getSortidaY() {
        return sortidaX;
    }

    public static int getCostat() {
        return COSTAT;
    }

    public int getFiles() {
        return files;
    }

    public int getColumnes() {
        return columnes;
    }

    /**
     *
     * @return
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(columnes * COSTAT, files * COSTAT);

    }

    /**
     * Mètodes que aporten una abstracció important en la qual no es veu
     * inicialment com es realitza el proces de definir la murada.
     *
     * @param x
     * @param y
     * @return
     */
    public boolean muradaSuperior(int x, int y) {
        return arrLab[x][y].muradaNord();
    }

    public boolean muradaDreta(int x, int y) {
        return arrLab[x][y].muradaEst();
    }

    public boolean muradaInferior(int x, int y) {
        return arrLab[x][y].muradaSud();
    }

    public boolean muradaEsquerra(int x, int y) {
        return arrLab[x][y].muradaOest();
    }

    /**
     * Mètode per a la reinicialització del laberint. Aquest realitza la
     * initzialització de nou de la fitxa a una posició nova aleatòria i el
     * comptador a cero per al nou intent.
     */
    public void reiniciarLaberint() {
        //Crea unes posicions random per a la fitxa
        Random rndFiles = new Random();
        Random rndColumnes = new Random();
        //Comptador de moviments a 0 
        this.moviments = 0;
        //Llevar tant la fitxa com la posició de sortida.(universal per poder 
        //fer servir aquest mètode per canviar de laberint)
        try {
            llevaFitxa(Main.filaLab, Main.columnaLab);
            llevaFitxa(sortidaY, sortidaX - 1);
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
        Main.filaLab = rndFiles.nextInt(files);
        Main.columnaLab = rndColumnes.nextInt(columnes - 1);
        //Possam la fitxa als llocs marcats per els randoms.
        coloca(Fitxa.fitxa, Main.filaLab, Main.columnaLab);
        coloca(Fitxa.sortida, sortidaY, sortidaX - 1);
        repaint();
    }

    /**
     * Una vegada acabat el laberint, es caviarà automàticament, Tenim en compte
     * que pot apareixer el mateix laberint que s'ha fet.
     */
    public void canviarLaberint() {
        Random rnd1 = new Random();
        int i = rnd1.nextInt(2) + 1;
        String s = "laberints/Maze" + i + ".txt";
        changeLab(s);
        reiniciarLaberint();
    }

    /**
     * Tancament del Laberint
     */
    public static void tancarLaberint() {
        System.exit(0);
    }

    /**
     * Increment del comptador de moviments
     */
    public void incrementarMoviments() {
        this.moviments = moviments + 1;
    }

    /**
     * Mètode que retorna un boolean que descriu si s'ha arribat o no a la
     * casella definida com sortida.
     *
     * @param x coordenada x
     * @param y coordenada y
     * @return
     */
    public boolean esSortida(int x, int y) {
        Casella cas = arrLab[x][y];
        return cas == casellafinal;

    }

    /**
     * Implementació de metode que mostra una pantalla amb els moviments fets.
     *
     */
    public void tractamentSortida() {
        Missatge miss = new Missatge("Has acabat en "
                + moviments + " moviments");

        canviarLaberint();
        coloca(Fitxa.sortida, sortidaX,
                sortidaY - 1);
    }

    public void canviarTema(Color colFons, Color colParet, String fitxaMov,
            String fitxaSortida) {
        estilLab.setColorFons(colFons);
        estilLab.setColorParet(colParet);
        estilLab.setFitxaMoviment(fitxaMov);
        estilLab.setFitxaSortida(fitxaSortida);

        llevaFitxa(Main.filaLab, Main.columnaLab);
        coloca(Fitxa.fitxa, Main.filaLab, Main.columnaLab);
        llevaFitxa(sortidaY, sortidaX - 1);
        coloca(Fitxa.fitxa, sortidaY, sortidaX - 1);
        PintaLaberint();
        reiniciarLaberint();
    }

    /*
     * Getters i setters.
     * 
     */
    public int getMoviments() {
        return moviments;
    }

    public String getNomLab() {
        return nomLab;
    }
}
