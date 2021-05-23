package Vue;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
 
public class FenetrePrincipale extends Fenetre {
    private BoutonInscription boutonInscription;
    private BoutonIdentification boutonIdentification;
    private BoutonProposeTrajet boutonProposeTrajet;
    private BoutonAjoutVehicule boutonAjoutVehicule;
    private BoutonRechargementSolde boutonRechargementSolde;
    private BoutonTrajet boutonTrajet;
    private BoutonParcours boutonParcours;
    private BoutonRechercheParcours  boutonRechercheParcours;


    public FenetrePrincipale(){
    boutonInscription = new BoutonInscription("Inscription");
    this.add(boutonInscription);
    
    boutonIdentification = new BoutonIdentification("Identification");
    this.add(boutonIdentification);
    
    boutonProposeTrajet = new BoutonProposeTrajet("Proposer un trajet");
    this.add(boutonProposeTrajet);
    
    boutonRechercheParcours=new BoutonRechercheParcours("Recherche un Parcours");
    this.add(boutonRechercheParcours);
    
    boutonAjoutVehicule = new BoutonAjoutVehicule("Ajouter un véhicule");
    this.add(boutonAjoutVehicule);
    
    boutonRechargementSolde = new BoutonRechargementSolde("Recharger Solde");
    this.add(boutonRechargementSolde);
    
    boutonTrajet = new BoutonTrajet("Trajet");
    this.add(boutonTrajet);
    
    boutonParcours = new BoutonParcours("Parcours");
    this.add(boutonParcours);
    

    }
	
    
     public void TraiteBoutonInscription(ActionListener a){
        boutonInscription.addActionListener(a);
    }
     
        
     public void TraiteBoutonIdentification(ActionListener a){
        boutonIdentification.addActionListener(a);
    }
     
    public void TraiteBoutonProposeTrajet(ActionListener a){
        boutonProposeTrajet.addActionListener(a);
    }
    
     public void TraiteBoutonAjoutVehicule(ActionListener a){
        boutonAjoutVehicule.addActionListener(a);
    }
     
     public void TraiteBoutonRechargementSolde(ActionListener a){
    	 boutonRechargementSolde.addActionListener(a);
     }
     
     public void TraiteBoutonTrajet(ActionListener a){
    	 boutonTrajet.addActionListener(a);
     }
     
     public void TraiteBoutonParcours(ActionListener a){
    	 boutonParcours.addActionListener(a);
     }
     
     public void TraiteBoutonRechercheParcours(ActionListener a){
    	 boutonRechercheParcours.addActionListener(a);
     }
     
}
