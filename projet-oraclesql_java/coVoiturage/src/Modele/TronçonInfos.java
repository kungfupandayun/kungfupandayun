package Modele;

public class TronçonInfos {
	private String id_tronçon;
	private String id_trajet;
	private String ville_depart;
	private String ville_arrivee;

	
	public TronçonInfos(String id_tronçon,String id_trajet,String ville_depart,String ville_arrivee) {
		this.id_tronçon=id_tronçon;
		this.id_trajet=id_trajet;
		this.ville_depart=ville_depart;
		this.ville_arrivee=ville_arrivee;

	}
	
	public String getIdTronçon() {
		return id_tronçon;
	}
	public String getIdTrajet() {
		return id_trajet;
	}
	public String getVilleDépart() {
		return ville_depart;
	}
	public String getVilleArrivée() {
		return ville_arrivee;
	}

    @Override
    public boolean equals(Object o) {
        if(o instanceof TronçonInfos) {
        	TronçonInfos p = (TronçonInfos)o;
            if(id_tronçon.equals(p.id_tronçon) && id_trajet.equals(p.id_trajet) && ville_depart.equals(p.ville_depart) && ville_arrivee.equals(p.ville_arrivee) ) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        for(int i = 0; i < id_tronçon.length(); i++) {
            hashCode += (int)id_tronçon.charAt(i);
        }
        for(int i = 0; i < id_trajet.length(); i++) {
            hashCode += (int)id_trajet.charAt(i);
        }

        return hashCode;
    }
}
