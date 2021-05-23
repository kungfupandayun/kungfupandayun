
package Modele;

public class TrajetInfos {
	private String id_trajet;
	private String date;
	private String heure;

	
	public TrajetInfos(String id_trajet,String date,String heure) {
		this.id_trajet=id_trajet;
		this.date=date;
		this.heure=heure;

	}
	
	public String getIdTrajet() {
		return id_trajet;
	}
	public String getDate() {
		return date;
	}
	public String getHeure() {
		return heure;
	}

	
    @Override
    public boolean equals(Object o) {
        if(o instanceof TrajetInfos) {
        	TrajetInfos p = (TrajetInfos)o;
            if(id_trajet.equals(p.id_trajet) && date.equals(p.date) && heure.equals(p.heure) ) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        for(int i = 0; i < id_trajet.length(); i++) {
            hashCode += (int)id_trajet.charAt(i);
        }
        
        return hashCode;
    }
}
