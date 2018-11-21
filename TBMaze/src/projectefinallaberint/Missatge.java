/*
Classe usada per a mostrar missatges per pantalla en forma de finestres
emergents, és un Jdialog.
 */
package projectefinallaberint;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * @authors Toni Boutaour, Bernat Pericàs
 * @DNIs 43202227Q, 43212796M
 */
class Missatge extends JDialog {

    private JLabel jlbGuanyador;
    private JButton jbtOk;
//    private JButton jbtNewLab;
    private final String text;

    /**
     * Constructor de missatges
     *
     * @param text String que es mostrarà per pantalla
     */
    public Missatge(String text) {
        this.text = text;
        initComponents();
        this.pack();
        this.setLocationRelativeTo(this);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);

        jbtOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }

    public void initComponents() {
        JPanel JpEtiqueta = new JPanel();
        jlbGuanyador = new JLabel(text, SwingConstants.CENTER);
        jlbGuanyador.setFont(new Font("arial", 1, 30));
        JpEtiqueta.setLayout(new BorderLayout());
        JpEtiqueta.add(jlbGuanyador, BorderLayout.NORTH);

        JPanel JpBotons = new JPanel();
        JpBotons.setLayout(new FlowLayout());
        jbtOk = new JButton("Ok");
        jbtOk.setPreferredSize(new Dimension(100, 50));

        JpBotons.add(jbtOk);

        JpEtiqueta.add(JpBotons, BorderLayout.SOUTH);
        getContentPane().add(JpEtiqueta);
    }
}
