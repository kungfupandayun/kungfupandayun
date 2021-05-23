package Vue;

import Modele.PossedeVehicule;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JFrame;


public class FenetreChoisirVehicule extends Fenetre {   

 
  private BoutonValiderChoisirVehicule boutonValiderChoisirVehicule;
  private BoutonRetourChoisirVehicule boutonRetourChoisirVehicule;
  
  
  public FenetreChoisirVehicule(){
      // create a new frame 
        //f = new JFrame("frame");  
      
      
       boutonValiderChoisirVehicule = new BoutonValiderChoisirVehicule("Valider");
       this.add(boutonValiderChoisirVehicule);
	  
       boutonRetourChoisirVehicule = new BoutonRetourChoisirVehicule("Retour");
        this.add(boutonRetourChoisirVehicule);
	 
       

    
        
  }
  
    public void TraiteBoutonValiderChoisirVehicule(ActionListener a){
        boutonValiderChoisirVehicule.addActionListener(a);
  }
    
    public void TraiteBoutonRetourChoisirVehicule(ActionListener a){
        boutonRetourChoisirVehicule.addActionListener(a);
  }
  
    
  
  
  
  
  
    

}
