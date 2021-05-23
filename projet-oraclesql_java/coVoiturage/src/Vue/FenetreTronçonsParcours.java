package Vue;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;

import Modele.TronçonParcoursInfos;
/**
 * Fenetre pour la suivi d'un parcours
 */
public class FenetreTronçonsParcours extends Fenetre {
	  private ArrayList<BoutonTronçon> tronçons;
	  private HashSet<TronçonParcoursInfos> tronçons_trajets_infos;
	  private BoutonRetourTronçons boutonRetourTronçons;
	  
		public FenetreTronçonsParcours() {
			tronçons=new ArrayList<BoutonTronçon>();
			this.tronçons_trajets_infos=new HashSet<TronçonParcoursInfos>();

			boutonRetourTronçons = new BoutonRetourTronçons("Retour");
	        this.add(boutonRetourTronçons);

		}
	    public void TraiteBoutonsTronçonsParcours(ActionListener a){
	    	//System.out.println("action before for");
	    	//System.out.println(tronçons.size());
	    	for(BoutonTronçon boutonTronçon : tronçons) {
	    		boutonTronçon.addActionListener(a);
	    	}    	
	    }
	    public void TraiteBoutonRetourAffichageTronçons(ActionListener a){
	    	boutonRetourTronçons.addActionListener(a);
	    }
	  
	    public void setTronçonsParcours(HashSet<TronçonParcoursInfos> tronçons_trajets_infos) {
	    	this.tronçons_trajets_infos.clear();
	    	for (BoutonTronçon boutonTronçon : tronçons) {
	    		this.remove(boutonTronçon);  
	    	}
			for(TronçonParcoursInfos tronçons_trajet_infos : tronçons_trajets_infos ) {
				if(this.tronçons_trajets_infos.contains(tronçons_trajet_infos)==false) {
					System.out.println("setTronçonsParcours");
					BoutonTronçon boutonTronçon=new BoutonTronçon(tronçons_trajet_infos.getVilleDépart()+"->"+tronçons_trajet_infos.getVilleArrivée());
					boutonTronçon.setActionCommand(tronçons_trajet_infos.getIdTrajet()+" "+tronçons_trajet_infos.getIdTronçon());
					tronçons.add(boutonTronçon);
					this.tronçons_trajets_infos.add(tronçons_trajet_infos);
			        this.add(boutonTronçon);
				}
			}
	    }

}
