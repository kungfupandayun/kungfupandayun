package Vue;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;

public class FenetreEchec extends Fenetre {
	private Fenetre nextPage; // nextPage est la page qu'il faut afficher après avoir appuyer sur le bouton valider
        private BoutonOkEchec boutonOkEchec;
        public FenetreEchec(){
               boutonOkEchec = new BoutonOkEchec("OK");
               this.add(boutonOkEchec);
        }
        
	public Fenetre getNextPage() {
		return nextPage;
	}

	public void setNextPage(Fenetre lastPage) {
		this.nextPage = lastPage;
	}
        
                
        public void TraiteBoutonOkEchec(ActionListener a){
            boutonOkEchec.addActionListener(a);
        }
}
