package Vue;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import static java.lang.Integer.parseInt;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

public class FenetreAjoutVehicule extends Fenetre {
    private JTextField immatriculation;
    private JTextField marque;
    private JTextField modele;
    private JTextField energie;
    private JTextField puissance;
    private JTextField placesAjoutVehicule;
    private BoutonRetourAjoutVehicule boutonRetourAjoutVehicule;
    private BoutonValiderAjoutVehicule boutonValiderAjoutVehicule;
    
    public FenetreAjoutVehicule(){
        
    immatriculation = new JTextField("Immatriculation");
    immatriculation.setPreferredSize(new Dimension(150, 30));
    this.add(immatriculation); immatriculation.setPreferredSize(new Dimension(150, 30));
    
    marque = new JTextField("Marque");
    marque.setPreferredSize(new Dimension(150, 30));
    this.add(marque);
    
    modele = new JTextField("Modèle");
    modele.setPreferredSize(new Dimension(150, 30));
    this.add(modele);
    
    energie = new JTextField("Energie");
    energie.setPreferredSize(new Dimension(150, 30));
    this.add(energie);
    
    puissance = new JFormattedTextField("Puissance");
    puissance.setPreferredSize(new Dimension(150, 30));
    this.add(puissance);
    
    placesAjoutVehicule = new JTextField("Nombre de places");
    placesAjoutVehicule .setPreferredSize(new Dimension(150, 30));
    this.add(placesAjoutVehicule);
    
    boutonValiderAjoutVehicule = new BoutonValiderAjoutVehicule("Valider");
    //boutonValiderAjoutVehicule.addActionListener(this);
    this.add(boutonValiderAjoutVehicule);
    
    boutonRetourAjoutVehicule = new BoutonRetourAjoutVehicule("Retour");
    //boutonRetourAjoutVehicule.addActionListener(this);
    this.add(boutonRetourAjoutVehicule);
    }
    
    
        
    public void TraiteBoutonValiderAjoutVehicule(ActionListener a){
        boutonValiderAjoutVehicule.addActionListener(a);
    }
    
    public void TraiteBoutonRetourAjoutVehicule(ActionListener a){
        boutonRetourAjoutVehicule.addActionListener(a);
    }
    
    public String getImmatriculation(){
        System.out.println(immatriculation.getText());
        return immatriculation.getText();   
    }
    
    public String getMarque(){
        System.out.println(marque.getText());
        return marque.getText();   
    }
        
    public String getModele(){
        System.out.println(modele.getText());
        return modele.getText();   
    }
            
    public String getEnergie(){
        System.out.println(energie.getText());
        return energie.getText();   
    }
                
    public int getPuissance(){
        System.out.println(puissance.getText());
        return parseInt(puissance.getText());   
    }
                    
    public int getPlacesAjoutVehicule(){
        System.out.println(placesAjoutVehicule.getText());
        return parseInt(placesAjoutVehicule.getText());   
    }
    
    
}
