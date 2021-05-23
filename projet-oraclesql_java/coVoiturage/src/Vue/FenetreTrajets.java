package Vue;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;


import Modele.TrajetInfos;

public class FenetreTrajets extends Fenetre {
	
	  private ArrayList<BoutonTrajet> trajets;
	  private ArrayList<String> trajets_ids;
	  private BoutonRetourAffichageTrajet boutonRetourAffichageTrajet;
	  private HashSet<TrajetInfos> trajets_infos;
	  
	public FenetreTrajets() {
		trajets=new ArrayList<BoutonTrajet>();
		this.trajets_ids=new ArrayList<String>();
		this.trajets_infos=new HashSet<TrajetInfos>();

		boutonRetourAffichageTrajet = new BoutonRetourAffichageTrajet("Retour");
        this.add(boutonRetourAffichageTrajet);

	}
    public void TraiteBoutonsTrajet(ActionListener a){
    	//System.out.println("action before for");
    	//System.out.println(trajets.size());
    	for(BoutonTrajet boutonTrajet : trajets) {
    		boutonTrajet.addActionListener(a);
    	}    	
    }
    public void TraiteBoutonRetourAffichageTrajet(ActionListener a){
    	boutonRetourAffichageTrajet.addActionListener(a);
    }
    
    public void setTrajets(HashSet<TrajetInfos> trajets_infos) {
		for(TrajetInfos trajet_info : trajets_infos ) {
			if(this.trajets_infos.contains(trajet_info)==false) {
				BoutonTrajet boutonTrajet=new BoutonTrajet(trajet_info.getDate()+" "+trajet_info.getHeure());
				boutonTrajet.setActionCommand(trajet_info.getIdTrajet());
				trajets.add(boutonTrajet);
				this.trajets_infos.add(trajet_info);
				//this.trajets_ids.add(trajet_id);
		        this.add(boutonTrajet);
			}
		}
    }

}
