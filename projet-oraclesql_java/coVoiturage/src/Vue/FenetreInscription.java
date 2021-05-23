package Vue;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import javax.swing.JPasswordField;

import javax.swing.JPanel;
import javax.swing.JTextField;
 
public class FenetreInscription extends Fenetre { 
  private JTextField emailInscription;
  private JTextField nomInscription;
  private JTextField prenomInscription;
  private JTextField villeInscription;
  private JPasswordField mdpInscription;
  private BoutonRetourInscription boutonRetourInscription;
  private BoutonValider boutonValiderInscription;

    
    public FenetreInscription(){  
        emailInscription = new JTextField("Email");
        emailInscription.setPreferredSize(new Dimension(150, 30));
        this.add(emailInscription);

        nomInscription = new JTextField("Nom");
        nomInscription.setPreferredSize(new Dimension(150, 30));
        this.add(nomInscription);

        prenomInscription = new JTextField("Prenom");
        prenomInscription.setPreferredSize(new Dimension(150, 30));
        this.add(prenomInscription);

        villeInscription = new JTextField("Ville");
        villeInscription.setPreferredSize(new Dimension(150, 30));
        this.add(villeInscription);

        mdpInscription = new JPasswordField("Mot de passe");
        mdpInscription.setPreferredSize(new Dimension(150, 30));
        this.add(mdpInscription);

        boutonValiderInscription = new BoutonValider("Valider");
        this.add(boutonValiderInscription);

        boutonRetourInscription = new BoutonRetourInscription("Retour");
        this.add(boutonRetourInscription);
    }
    
    
    public void TraiteBoutonValiderInscription(ActionListener a){
        boutonValiderInscription.addActionListener(a);
    }
    
    public void TraiteBoutonRetourInscription(ActionListener a){
        boutonRetourInscription.addActionListener(a);
    }
    
    public String getEmail(){
        System.out.println(emailInscription.getText());
        return emailInscription.getText();
        
    }
    
    public String getNom(){
         System.out.println(nomInscription.getText());
        return nomInscription.getText();
    }
    
        
    public String getPrenom(){
        System.out.println(prenomInscription.getText());
        return prenomInscription.getText();
    }
    
        
    public String getVille(){
        System.out.println(villeInscription.getText());
        return  villeInscription.getText();
    }
    
        
    public String getMdP(){
        System.out.println(mdpInscription.getPassword());
        return String.copyValueOf(mdpInscription.getPassword());
    }
    
    
    
    
    
    
 
}