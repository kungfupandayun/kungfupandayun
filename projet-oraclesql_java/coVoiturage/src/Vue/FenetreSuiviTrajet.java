package Vue;

import java.awt.event.ActionListener;

public class FenetreSuiviTrajet extends Fenetre {
    private BoutonConfirmerDépart boutonConfirmerDépart;
    private BoutonConfirmerArrivée boutonConfirmerArrivée;
    private BoutonRetourConfirmationTronçon boutonRetourConfirmationTronçon;
    private BoutonAnnulerTronçon boutonAnnulerTronçon;
    private String id_trajet;
    private String id_tronçon;


    public FenetreSuiviTrajet() {
    	boutonConfirmerDépart = new BoutonConfirmerDépart("Confirmer Départ");
        this.add(boutonConfirmerDépart);
        
        boutonConfirmerArrivée = new BoutonConfirmerArrivée("Confirmer Arrivée");
        this.add(boutonConfirmerArrivée);
        
        boutonAnnulerTronçon=new BoutonAnnulerTronçon("Annuler");
        this.add(boutonAnnulerTronçon);
        
        boutonRetourConfirmationTronçon=new BoutonRetourConfirmationTronçon("Retour");
        this.add(boutonRetourConfirmationTronçon);
        
        
    }
    
    public void TraiteBoutonConfirmerDépart(ActionListener a){
    	boutonConfirmerDépart.addActionListener(a);
    }
    
    public void TraiteBoutonConfirmerArrivée(ActionListener a){
    	boutonConfirmerArrivée.addActionListener(a);
    }
    
    public void TraiteBoutonAnnulerTronçon(ActionListener a){
    	boutonAnnulerTronçon.addActionListener(a);
    }
    
    public void TraiteBoutonRetourConfirmationTronçon(ActionListener a){
    	boutonRetourConfirmationTronçon.addActionListener(a);
    }
    
    public void setId_trajet(String id_trajet) {
    	this.id_trajet=id_trajet;
    }
    public void setId_tronçon(String id_tronçon) {
    	this.id_tronçon=id_tronçon;
    }
}
