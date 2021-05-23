

package Modele;

public class ParcoursInfos {
	private String id_parcours;
	private String date;
	private String heure;

	
	public ParcoursInfos(String id_parcours,String date,String heure) {
		this.id_parcours=id_parcours;
		this.date=date;
		this.heure=heure;

	}
	
	public String getIdParcours() {
		return id_parcours;
	}
	public String getDate() {
		return date;
	}
	public String getHeure() {
		return heure;
	}

	
    @Override
    public boolean equals(Object o) {
        if(o instanceof ParcoursInfos) {
        	ParcoursInfos p = (ParcoursInfos)o;
            if(id_parcours.equals(p.id_parcours) && date.equals(p.date) && heure.equals(p.heure) ) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        for(int i = 0; i < id_parcours.length(); i++) {
            hashCode += (int)id_parcours.charAt(i);
        }
        return hashCode;
    }
}
