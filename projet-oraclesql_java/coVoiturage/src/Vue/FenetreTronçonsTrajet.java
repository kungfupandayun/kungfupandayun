package Vue;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;

import Modele.TronçonInfos;

/**
 * Fenetre pour la suivi d'un trajet
 */
public class FenetreTronçonsTrajet extends Fenetre {
	  private ArrayList<BoutonTronçon> tronçons;
	  private HashSet<TronçonInfos> tronçons_infos;
	  private BoutonRetourTronçons boutonRetourTronçons;
	  
		public FenetreTronçonsTrajet() {
			tronçons=new ArrayList<BoutonTronçon>();
			this.tronçons_infos=new HashSet<TronçonInfos>();

			boutonRetourTronçons = new BoutonRetourTronçons("Retour");
	        this.add(boutonRetourTronçons);

		}
	    public void TraiteBoutonsTrajet(ActionListener a){
	    	System.out.println("action before for");
	    	System.out.println(tronçons.size());
	    	for(BoutonTronçon boutonTronçon : tronçons) {
	    		boutonTronçon.addActionListener(a);
	    	}    	
	    }
	    public void TraiteBoutonRetourAffichageTrajet(ActionListener a){
	    	boutonRetourTronçons.addActionListener(a);
	    }
	  
	    public void setTronçons(HashSet<TronçonInfos> tronçons_infos) {
	    	this.tronçons_infos.clear();
	    	for (BoutonTronçon boutonTronçon : tronçons) {
	    		this.remove(boutonTronçon);  
	    	}
			for(TronçonInfos tronçon_infos : tronçons_infos ) {
				if(this.tronçons_infos.contains(tronçon_infos)==false) {
					System.out.println(this.tronçons_infos.contains(tronçon_infos));
					System.out.println(tronçon_infos.getIdTronçon()+" "+tronçon_infos.getIdTrajet());

					BoutonTronçon boutonTronçon=new BoutonTronçon(tronçon_infos.getVilleDépart()+" -> "+tronçon_infos.getVilleArrivée());
					boutonTronçon.setActionCommand(tronçon_infos.getIdTronçon());
					tronçons.add(boutonTronçon);
					this.tronçons_infos.add(tronçon_infos);
			        this.add(boutonTronçon);
				}
			}
	    }
	    

}
