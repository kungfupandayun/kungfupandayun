package Vue;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import static java.lang.Double.parseDouble;
import javax.swing.JTextField;

public class FenetreRechargementSolde extends Fenetre  {
	
    private JTextField montant;
    private BoutonValiderRechargement boutonValiderRechargement;
    private BoutonRetourRechargement boutonRetourRechargement;
    
    public FenetreRechargementSolde() {
    	montant = new JTextField("montant");
    	montant.setPreferredSize(new Dimension(150, 30));
    	this.add(montant);
    	
    	boutonValiderRechargement = new BoutonValiderRechargement("Valider");
        this.add(boutonValiderRechargement);
        
        boutonRetourRechargement = new BoutonRetourRechargement("Retour");
        this.add(boutonRetourRechargement);
        
    }
    
    public void TraiteBoutonValiderRechargement(ActionListener a){
    	boutonValiderRechargement.addActionListener(a);
    }
    
    public void TraiteBoutonRetourRechargement(ActionListener a){
    	boutonRetourRechargement.addActionListener(a);
    }
    
    public double getMontant(){
        System.out.println(montant.getText());
        //return  montant.getText(); 
        return parseDouble(montant.getText());
    }
}