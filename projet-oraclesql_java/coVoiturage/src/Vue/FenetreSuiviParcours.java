package Vue;

import java.awt.event.ActionListener;

public class FenetreSuiviParcours extends Fenetre {
    private Bouton boutonConfirmerMontée;
    private Bouton boutonConfirmerDescente;
    private Bouton boutonRetourConfirmationTronçon;
    private BoutonPaiement boutonPaiement;
    private String id_trajet;
    private String id_tronçon;


    public FenetreSuiviParcours() {
    	boutonConfirmerMontée = new Bouton("Confirmer Montée");
        this.add(boutonConfirmerMontée);
        
        boutonConfirmerDescente = new Bouton("Confirmer Descente");
        this.add(boutonConfirmerDescente);
        
        boutonPaiement=new BoutonPaiement("Payer");
        this.add(boutonPaiement);
        
        boutonRetourConfirmationTronçon=new BoutonRetourConfirmationTronçon("Retour");
        this.add(boutonRetourConfirmationTronçon);
        
        
    }
    
    public void TraiteBoutonConfirmerMontée(ActionListener a){
    	boutonConfirmerMontée.addActionListener(a);
    }
    
    public void TraiteBoutonConfirmerDescente(ActionListener a){
    	boutonConfirmerDescente.addActionListener(a);
    }
    
    public void TraiteBoutonPaiement(ActionListener a){
    	boutonPaiement.addActionListener(a);
    }
    
    public void TraiteBoutonRetourConfirmationTronçon(ActionListener a){
    	boutonRetourConfirmationTronçon.addActionListener(a);
    }
}
