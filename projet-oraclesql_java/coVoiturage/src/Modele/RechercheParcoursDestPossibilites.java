
package Modele;

import java.util.Date;

/**
 *
 * Classe pour rassemble tous les informations des possibles parcours
 */
public class RechercheParcoursDestPossibilites {
    private int idTrajet;
    private int idTronçon;
    private int placeDispoDépart;
    private Date temps_trajet;
    
    public RechercheParcoursDestPossibilites(int idTrajet,int idTronçon,int placeDispoDépart,
                                            Date temps_trajet)
    {
        this.idTrajet=idTrajet;
        this.idTronçon=idTronçon;
        this.placeDispoDépart=placeDispoDépart;
        this.temps_trajet=temps_trajet;
    }
    
    public String toString(){
        String line="idTrajet=>"+idTrajet+"\n"
                    +"idDébutTronçon=>"+idTronçon+"\n"
                    +"placeDispoDépart=>"+placeDispoDépart+"\n"
                    +"temps_trajet=>"+temps_trajet.toString()+"\n";
        return line;
    }
    
    public int getIdTrajet(){
        return idTrajet;
    }
    
    
    public int getIdTronçon(){
        return idTronçon;
    }
    
    public int getPlaceDispo(){
        return placeDispoDépart;
    }
}
