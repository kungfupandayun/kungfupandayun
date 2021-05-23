package Vue;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;

import Modele.ParcoursInfos;

public class FenetreParcours extends Fenetre {
	  private ArrayList<BoutonParcours> parcours;
	  private HashSet<ParcoursInfos> parcours_infos;
	  private BoutonRetourAffichageParcours boutonRetourAffichageParcours;

	  
	public FenetreParcours() {
		parcours=new ArrayList<BoutonParcours>();
		this.parcours_infos=new HashSet<ParcoursInfos>();

		boutonRetourAffichageParcours = new BoutonRetourAffichageParcours("Retour");
        this.add(boutonRetourAffichageParcours);

	}
  public void TraiteBoutonsParcours(ActionListener a){
  	System.out.println("action before for");
  	System.out.println(parcours.size());
  	for(BoutonParcours boutonParcours : parcours) {
  		boutonParcours.addActionListener(a);
  	}    	
  }
  public void TraiteBoutonRetourAffichageParcours(ActionListener a){
	  boutonRetourAffichageParcours.addActionListener(a);
  }
  
  public void setParcours(HashSet<ParcoursInfos> parcours_infos) {
		for(ParcoursInfos parcours_info : parcours_infos ) {
			if(this.parcours_infos.contains(parcours_info)==false) {
				BoutonParcours boutonParcours=new BoutonParcours(parcours_info.getDate()+" "+parcours_info.getHeure());
				boutonParcours.setActionCommand(parcours_info.getIdParcours());
				parcours.add(boutonParcours);
				this.parcours_infos.add(parcours_info);
		        this.add(boutonParcours);
			}
		}
  }

}
