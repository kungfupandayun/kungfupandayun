package Vue;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;

public class FenetreSucces extends Fenetre {   
	private Fenetre nextPage; // nextPage est la page qu'il faut afficher après avoir appuyer sur le bouton valider
         private BoutonOkSucces boutonOkSucces;
        public FenetreSucces(){
           boutonOkSucces = new BoutonOkSucces("OK");
            this.add(boutonOkSucces);
        } 
	public Fenetre getNextPage() {
		return nextPage;
	}

	public void setNextPage(Fenetre lastPage) {
		this.nextPage = lastPage;
	}
        
        public void TraiteBoutonOkSucces(ActionListener a){
            boutonOkSucces.addActionListener(a);
        }
}