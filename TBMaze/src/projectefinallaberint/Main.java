/**
 * Aquesta classe es l'executable del projecte, s'inicia aqui i tots els
 * elements de la part "jugable" del projecte estan situats aquí.
 * Es tracta, llavors, de la classe que uneix tots les altres.
 */
package projectefinallaberint;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

/**
 * @authors Toni Boutaour, Bernat Pericàs
 * @DNIs 43202227Q, 43212796M
 */
public class Main extends JFrame implements KeyListener {

    private final Laberint lab;

    private JMenuBar jmbarLab;
    private JMenu jmenuJoc, jmenuMode;
    private JMenuItem jmItemSortir, jmItemReiniciar, jmItemModeBillar,
            jmItemModePacman, jmItemEscollir, jmItemHelp, jmItemModePokemon;

    static int filaLab = (int) Math.floor(Math.random() * (15 + 1));
    static int columnaLab = (int) Math.floor(Math.random() * 10);

    /**
     * El constructor del main s'encarrega de l'inici del joc, possa a punt els
     * diferents elements de l'entorn gràfic amb la cridada a initsComponents i
     * la colocació de les fitxes.
     */
    public Main() {
        super("TBMaze-Edició limitada");
        initComponents();
        lab = new Laberint();
        this.addKeyListener(this);

        lab.coloca(Fitxa.fitxa, filaLab, columnaLab);
        // com que la sortida està indicada     
        //una posició enllà respecte a les columnes,s'ha de tenir en compte.
        lab.coloca(Fitxa.sortida, lab.getSortidaX(), lab.getSortidaY() - 1);

        this.getContentPane().add(lab);
        this.setSize(lab.getPreferredSize());
        this.setResizable(false);
        this.pack();
        setLocationRelativeTo(null);//la finestra surt al centre
        this.setDefaultCloseOperation(Main.EXIT_ON_CLOSE);
    }

    /**
     * initComponents s'encarrega de la gestió de tots els elements de l'entorn
     * gràfic de la barra al JFrame.
     */
    private void initComponents() {
        jmbarLab = new JMenuBar();
        jmenuJoc = new JMenu();
        jmenuMode = new JMenu();
        jmItemReiniciar = new JMenuItem();
        jmItemSortir = new JMenuItem();
        jmItemModeBillar = new JMenuItem();
        jmItemModePacman = new JMenuItem();
        jmItemModePokemon = new JMenuItem();
        jmItemEscollir = new JMenuItem();
        jmItemHelp = new JMenuItem();

        this.getContentPane().add(jmbarLab);
        jmItemReiniciar.setText("Reiniciar (tecla R)");
        jmItemReiniciar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jmItemReiniciaActionPerformed(evt);
            }
        });

        jmItemSortir.setText("Sortir (tecla Supr)");
        jmItemSortir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jmItemSortirActionPerformed(evt);
            }
        });

        jmItemModeBillar.setText("Mode Billar");
        jmItemModeBillar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jmItemModeBillarActionPerformed(evt);
            }
        });

        jmItemModePacman.setText("Mode Pacman");
        jmItemModePacman.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jmItemModePacmanActionPerformed(evt);
            }
        });

        jmItemModePokemon.setText("Mode Pokemon");
        jmItemModePokemon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jmItemModePokemonActionPerformed(evt);
            }
        });

        jmItemHelp.setText("Ajuda (tecla H)");
        jmItemHelp.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jmItemHelpActionPerformed(evt);
            }
        });

        jmItemEscollir.setText("Escollir (tecla C)");
        jmItemEscollir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                jmItemEscollirActionPerformed(evt);
            }
        });

        jmenuJoc.setText("Opcions");
        jmenuMode.setText("Canviar tema");

        jmenuJoc.add(jmItemReiniciar);
        jmenuJoc.add(jmItemSortir);
        jmenuJoc.add(jmItemEscollir);
        jmenuJoc.add(jmItemHelp);

        jmenuMode.add(jmItemModeBillar);
        jmenuMode.add(jmItemModePacman);
        jmenuMode.add(jmItemModePokemon);

        jmbarLab.add(jmenuJoc);
        jmbarLab.add(jmenuMode);
        jmbarLab.setBounds(0, 0, 492, 36);

        setJMenuBar(jmbarLab);
    }

    /*
    *Tots els actionPerformedEncarregats de realitzar una tasca al laberint:
     */
    private void jmItemSortirActionPerformed(ActionEvent evt) {
        System.exit(0);
    }

    private void jmItemModeBillarActionPerformed(ActionEvent evt) {
        lab.canviarTema(new Color(0, 102, 0), new Color(208, 167, 6),
                "imatges/blanca.png", "imatges/8.png");
    }

    private void jmItemModePacmanActionPerformed(ActionEvent evt) {
        lab.canviarTema(Color.BLACK, new Color(0, 0, 139),
                "imatges/pacman.png", "imatges/ghost.png");
    }

    private void jmItemModePokemonActionPerformed(ActionEvent evt) {
        lab.canviarTema(Color.yellow, Color.black,
                "imatges/pokeball.png", "imatges/pikachu.png");
    }

    private void jmItemReiniciaActionPerformed(ActionEvent evt) {
        //Tornar a posar la fitxa a una posició random.
        lab.reiniciarLaberint();
    }

    private void jmItemHelpActionPerformed(ActionEvent evt) {
        MostraAjuda();
    }

    private void jmItemEscollirActionPerformed(ActionEvent evt) {
        EscollirLaberint();
    }

    /**
     * Realització de tota la part de jugabilitat: segons la pulsació de les
     * diferents tecles asignades per al moviment es realitza aquest.
     *
     * @param ke event de teclat
     */
    @Override
    public void keyPressed(KeyEvent ke) {
        try {
            switch (ke.getKeyCode()) {
                case KeyEvent.VK_W:
                    System.out.println("W");
                    if (!lab.muradaSuperior(filaLab, columnaLab)) {
                        System.out.println("Pot pujar");
                        lab.llevaFitxa(filaLab, columnaLab);
                        filaLab = filaLab - 1;
                        lab.coloca(Fitxa.fitxa, filaLab, columnaLab);
                        repaint();
                        lab.incrementarMoviments();
                        System.out.println(lab.getMoviments());
                    } else {
                        System.out.println("No pot pujar");
                    }
                    if (lab.esSortida(filaLab, columnaLab)) {
                        Finalitza();
                    }
                    break;

                case KeyEvent.VK_D:
                    System.out.println("D");
                    if (!lab.muradaDreta(filaLab, columnaLab)) {
                        System.out.println("Pot anar a la dreta");
                        lab.llevaFitxa(filaLab, columnaLab);
                        columnaLab = columnaLab + 1;
                        lab.coloca(Fitxa.fitxa, filaLab, columnaLab);
                        repaint();
                        lab.incrementarMoviments();
                        System.out.println(lab.getMoviments());
                    } else {
                        System.out.println("No pot anar a la dreta");
                    }
                    if (lab.esSortida(filaLab, columnaLab)) {
                        Finalitza();
                    }
                    break;

                case KeyEvent.VK_S:
                    System.out.println("S");
                    if (!lab.muradaInferior(filaLab, columnaLab)) {
                        System.out.println("Pot baixar");
                        lab.llevaFitxa(filaLab, columnaLab);
                        filaLab = filaLab + 1;
                        lab.coloca(Fitxa.fitxa, filaLab, columnaLab);
                        repaint();
                        lab.incrementarMoviments();
                        System.out.println(lab.getMoviments());
                    } else {
                        System.out.println("No pot baixar");
                    }
                    if (lab.esSortida(filaLab, columnaLab)) {
                        Finalitza();
                    }
                    break;

                case KeyEvent.VK_A:
                    System.out.println("A");
                    if (!lab.muradaEsquerra(filaLab, columnaLab)) {
                        System.out.println("Pot anar a l'esquerra");
                        lab.llevaFitxa(filaLab, columnaLab);
                        columnaLab = columnaLab - 1;
                        lab.coloca(Fitxa.fitxa, filaLab, columnaLab);
                        repaint();
                        lab.incrementarMoviments();
                        System.out.println(lab.getMoviments());
                    } else {
                        System.out.println("No pot anar a l'esquerra");
                    }
                    if (lab.esSortida(filaLab, columnaLab)) {
                        Finalitza();
                    }
                    break;

                case KeyEvent.VK_R:
                    lab.reiniciarLaberint();
                    break;
                case KeyEvent.VK_DELETE:
                    Laberint.tancarLaberint();
                    break;
                case KeyEvent.VK_H:
                    MostraAjuda();
                    break;
                case KeyEvent.VK_C:
                    EscollirLaberint();
                    break;
            }
        } catch (Exception e) {
            lab.incrementarMoviments();
        }
    }

    /**
     * Sense utilitat, però el mètode s'ha d'implementar obligatòriament.
     *
     * @param key event de teclat
     */
    @Override
    public void keyReleased(KeyEvent key) {
    }

    /**
     * Sense utilitat, però el mètode s'ha d'implementar obligatòriament.
     *
     * @param ke event de teclat
     */
    @Override
    public void keyTyped(KeyEvent ke) {
    }

    private void MostraAjuda() {
        JOptionPane.showMessageDialog(lab, //Finestra relativa al JFrame lab.
                "Benvingut al gran laberint.\n"
                + "Els següents controls defineixen el moviment de la fitxa:\n"
                + "A: Moviment a l'esquerra.\n"
                + "W: Moviment cap amunt.\n"
                + "D: Moviment a la dreta.\n"
                + "S: Moviment cap avall.\n"
                + "El joc consisteix en arribar en el menor nombre \nde"
                + " moviments a la sortida.\n \n"
                + "Molta sort!\n",
                "Panell auxiliar per a l'ajut de l'usuari", //Capçalera
                JOptionPane.INFORMATION_MESSAGE);           //Icona
    }

    /**
     * Mètode que s'encarrega del tractament de l'elecció d'un nou laberint.
     * Aquest realitza la operació d'apertura d'un fileChooser que serà la
     * interfície per agafar un nou element Laberint.
     *
     * S'ha tractat l'error d'elecció un fitxer que no és correcte emmagatzemant
     * un un string la direcció del laberint actual i realitzar un repintat
     * d'aquest en el moment que l'usuari no agafi un laberint vàlid.
     */
    private void EscollirLaberint() {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(System.getProperty("user.dir")
                + "/laberints"));
        String s;
        //String auxiliar que conte el nom del laberint actual.
        String aux = lab.getNomLab();
        try {
            int chooseraux = chooser.showOpenDialog(this);
            if (chooseraux == JFileChooser.APPROVE_OPTION) {
                s = chooser.getSelectedFile().getCanonicalPath();
                lab.changeLab(s);
                lab.reiniciarLaberint();
            }
            //Si apareix cap error, se reestableix el lab anterior.
        } catch (IOException | NullPointerException |
                ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(lab, //Finestra relativa al JFrame lab
                    "Disculpi,\n"
                    + "el fitxer no compleix les propietats necesaries per a "
                    + "ser tractat.",
                    "Error a l'execució", //Capçalera
                    JOptionPane.INFORMATION_MESSAGE);
            lab.changeLab(aux);
            lab.reiniciarLaberint();
        }
    }

    /**
     * Mètode que realitza l'execució del missatge de finalitzat del joc.
     */
    public void Finalitza() {
        Missatge miss = new Missatge("Has acabat en "
                + lab.getMoviments() + " moviments");
        lab.canviarLaberint();
        lab.coloca(Fitxa.sortida, lab.getSortidaX(),
                lab.getSortidaY() - 1);
    }

    /**
     * Inicia el programa
     *
     * @param args
     */
    public static void main(String[] args) {
        Main inici = new Main();
        inici.setVisible(true);
    }
}
