
import JDBC.*;
import static Modele.EnergieUtilisee.Essence;
import Modele.*;
import static Modele.Etat_Tronçon_Conducteur.EnAttente;

/**
 *
 * Inserer un tronçon simple.
 */
public class TestInsertTronçon {
     public static void main(String[] args) throws Exception {
   /*  public Tronçon(int id_tronçon,String ville_départ,double longitude_départ,double latitude_départ,
			String ville_arrivée,double longitude_arrivée,double latitude_arrivée, int durée_tronçon,int id_trajet,
			Etat_Tronçon_Conducteur etat_tronçon_cond)*/
        Tronçon t= new Tronçon(1,"Paris", 48.8566,2.3522,"Strasbourg", 51.5074,0.1278,300,
			EnAttente); 
    }
}
