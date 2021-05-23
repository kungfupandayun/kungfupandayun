package Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class FenetreIdentification extends Fenetre {

    private JTextField emailIdentification;
    private JPasswordField mdpIdentification;
    private BoutonValiderIdentification boutonValiderIdentification;
    private BoutonRetourIdentification boutonRetourIdentification;

    public FenetreIdentification() {
        emailIdentification = new JTextField("Email");
        emailIdentification.setPreferredSize(new Dimension(150, 30));
        this.add(emailIdentification);

        mdpIdentification = new JPasswordField("Mot de passe");
        mdpIdentification.setPreferredSize(new Dimension(150, 30));
        this.add(mdpIdentification);

        boutonValiderIdentification = new BoutonValiderIdentification("Valider");
        this.add(boutonValiderIdentification);

        boutonRetourIdentification = new BoutonRetourIdentification("Retour");
        this.add(boutonRetourIdentification);

    }

    public void TraiteBoutonRetourIdentification(ActionListener a) {
        boutonRetourIdentification.addActionListener(a);
    }

    public void TraiteBoutonValiderIdentification(ActionListener a) {
        boutonValiderIdentification.addActionListener(a);
    }

    public String getEmail() {
        //System.out.println(emailIdentification.getText());
        return emailIdentification.getText();

    }

    public String getMdP() {
        //System.out.println(mdpIdentification.getPassword());
        return String.copyValueOf(mdpIdentification.getPassword()) ;
    }
}
