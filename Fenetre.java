import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Fenetre extends JFrame {

    private final JLabel lab_personnaliser = new JLabel("Personnalisation du mot de passe :");
    private final JLabel chiffre_dans_mdp = new JLabel("Chiffre  :");
    private final JLabel lab_taille_mdp = new JLabel("Taille (en caractères) :");
    private final JLabel carac_speciaux_dans_mdp = new JLabel("Caractères spéciaux :");

    private final JCheckBox rep_chiffre_oui = new JCheckBox("Oui");
    private final JCheckBox rep_chiffre_non = new JCheckBox("Non");
    private final JCheckBox rep_carac_speciaux_oui = new JCheckBox("Oui");
    private final JCheckBox rep_carac_speciaux_non = new JCheckBox("Non");

    private final JButton bout_generer = new JButton("Générer");
    private final JButton bout_enregistrer = new JButton("Enregistrer");

    private final ButtonGroup groupe_chiffre = new ButtonGroup();
    private final ButtonGroup groupe_carac_speciaux = new ButtonGroup();

    private final JComboBox taille_mdp = new JComboBox();
    private final JTextPane mdp_genere = new JTextPane();
    private final JFileChooser file_chooser = new JFileChooser();
    private final JPanel container = new JPanel();
    private final ActionsListener actionslistener = new ActionsListener();

    public Fenetre() {
        this.setVisible(true);
        this.setTitle("JGenerator");
        this.setSize(240, 245);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setIconImage(ImageIconToImage(new ImageIcon(getClass().getResource("images/icone.png"))));
        this.add(container, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        init_container();
        init_button_group();
        init_file_chooser();
    }

    public static void main(String[] args) {
        new Fenetre();
    }

    private void init_container() {
        container.add(lab_personnaliser);
        lab_personnaliser.setFont(new Font("Arial", Font.PLAIN, 12));
        container.add(chiffre_dans_mdp);
        chiffre_dans_mdp.setFont(new Font("Arial", Font.PLAIN, 12));
        container.add(rep_chiffre_oui);
        rep_chiffre_oui.setFont(new Font("Arial", Font.PLAIN, 12));
        container.add(rep_chiffre_non);
        rep_chiffre_non.setFont(new Font("Arial", Font.PLAIN, 12));
        container.add(carac_speciaux_dans_mdp);
        carac_speciaux_dans_mdp.setFont(new Font("Arial", Font.PLAIN, 12));
        container.add(rep_carac_speciaux_oui);
        rep_carac_speciaux_oui.setFont(new Font("Arial", Font.PLAIN, 12));
        container.add(rep_carac_speciaux_non);
        rep_carac_speciaux_non.setFont(new Font("Arial", Font.PLAIN, 12));
        container.add(lab_taille_mdp);
        lab_taille_mdp.setFont(new Font("Arial", Font.PLAIN, 12));
        container.add(taille_mdp);
        taille_mdp.setFont(new Font("Arial", Font.PLAIN, 12));
        taille_mdp.addItem("12");
        taille_mdp.addItem("15");
        taille_mdp.addItem("18");
        taille_mdp.addItem("21");
        taille_mdp.addItem("24");
        taille_mdp.addItem("27");
        taille_mdp.addItem("30");
        container.add(bout_generer);
        bout_generer.setFont(new Font("Arial", Font.PLAIN, 12));
        bout_generer.addActionListener(actionslistener);
        container.add(mdp_genere);
        mdp_genere.setPreferredSize(new Dimension(150, 40));
        mdp_genere.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        mdp_genere.setEditable(false);
        container.add(bout_enregistrer);
        bout_enregistrer.setFont(new Font("Arial", Font.PLAIN, 12));
        bout_enregistrer.setToolTipText("Enregistrer le mdp dans un fichier");
        bout_enregistrer.addActionListener(actionslistener);
    }

    private void init_button_group() {
        groupe_chiffre.add(rep_chiffre_oui);
        groupe_chiffre.add(rep_chiffre_non);
        rep_chiffre_oui.setSelected(true);
        groupe_carac_speciaux.add(rep_carac_speciaux_oui);
        groupe_carac_speciaux.add(rep_carac_speciaux_non);
        rep_carac_speciaux_oui.setSelected(true);
    }

    private void init_file_chooser() {
        file_chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        file_chooser.addChoosableFileFilter(new FileNameExtensionFilter("Fichiers .txt", "txt"));
    }

    private Image ImageIconToImage(ImageIcon imageicon) {
        if (imageicon instanceof ImageIcon) {
            return imageicon.getImage();
        } else {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gd = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gd.getDefaultConfiguration();
            BufferedImage image = gc.createCompatibleImage(imageicon.getIconWidth(), imageicon.getIconHeight());
            Graphics2D g = image.createGraphics();
            imageicon.paintIcon(null, g, 0, 0);
            g.dispose();
            return image;
        }
    }

    private boolean getChoixChiffre() {
        return (rep_chiffre_oui.isSelected() == true);
    }

    private boolean getChoixCaracSpeciaux() {
        return (rep_carac_speciaux_oui.isSelected() == true);
    }

    class ActionsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == bout_generer) {
                int taille = 0;
                if (taille_mdp.getSelectedItem() == "12") {
                    taille = 12;
                } else if (taille_mdp.getSelectedItem() == "15") {
                    taille = 15;
                } else if (taille_mdp.getSelectedItem() == "18") {
                    taille = 18;
                } else if (taille_mdp.getSelectedItem() == "21") {
                    taille = 21;
                } else if (taille_mdp.getSelectedItem() == "24") {
                    taille = 24;
                } else if (taille_mdp.getSelectedItem() == "27") {
                    taille = 27;
                } else if (taille_mdp.getSelectedItem() == "30") {
                    taille = 30;
                }

                char[] mdp = GenerateurMDP.getMDP(taille, getChoixChiffre(), getChoixCaracSpeciaux());
                mdp_genere.setText(new String(mdp));
            } else if (e.getSource() == bout_enregistrer && mdp_genere.getText().length() > 0) {
                int choix = file_chooser.showSaveDialog(null);
                if (choix == JFileChooser.APPROVE_OPTION) {
                    String file = file_chooser.getSelectedFile().toString() + ".txt";
                    FileOutputStream writer = null;

                    try {
                        writer = new FileOutputStream(new File(file));
                    } catch (FileNotFoundException a) {
                    }

                    try {
                        FileWriter lu = new FileWriter(file);
                        BufferedWriter out = new BufferedWriter(lu);
                        out.write(mdp_genere.getText());
                        out.close();
                    } catch (IOException b) {
                        b.printStackTrace();
                    }
                }
            }
        }
    }
}
