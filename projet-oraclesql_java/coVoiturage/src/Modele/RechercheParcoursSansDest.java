package Modele;

import Exceptions.NoAccountException;
import JDBC.*;
import static JDBC.ConnectJDBC.conn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Recherche parcours sans Destination.
 */
public class RechercheParcoursSansDest extends AbstractRechercheParcours {

    public RechercheParcoursSansDest(String villeDépart, double longitudeDépart,
                                    double latitudeDépart, String date,
                                    Compte compte)  { 
        this.villeDépart =villeDépart;
        this.longitudeDépart =longitudeDépart;
        this.latitudeDépart =latitudeDépart;
        this.date=date;
        this.compte=compte;
        listSuggestion=new ArrayList<>();
        this.getSuggestions();
        // Maybe this is not necessary because appelJDBC is being called in the constructor of suggestion
        //calcul du prix, distance,heure arrivée...
//        for(Suggestion s: listSuggestion){
//            s.appelJDBC();
//        }
    }
    
    /**
     * Chercher sur les tronçons qui ont le même lieu et date de départ dans la table Tronçon.
     * Ensuite, selon ces "têtes" trouvé , chercher les possibles destinations.
     */
    public void getSuggestions() {
        
        String SQL1 = "SELECT t1.id_trajet,id_tronçon,place_dispo_depart from Tronçon t1 "
                + "INNER JOIN Trajet t2 "
                + "ON t1.id_trajet=t2.id_trajet "
                + "WHERE ville_depart=? "
                + "AND long_depart =? "
                + "AND lat_depart=? "
                + "AND date_trajet=?";

        String SQL2 = "SELECT id_tronçon from Tronçon "
                + "WHERE id_tronçon>? and id_trajet=?";

        String SQL3 = "SELECT COUNT(id_trajet) from  PARCOURSTRONÇON "
                + "WHERE id_trajet=? and id_tronçon=?";
        
        if(conn==null) {
           ConnectJDBC c = new ConnectJDBC();
        }
        try {
            // Creation de la requete
            PreparedStatement stmt = conn.prepareStatement(SQL1); 
            PreparedStatement stmt1 = conn.prepareStatement(SQL2); 
            PreparedStatement stmt2 = conn.prepareStatement(SQL3);

            stmt.setString(1, villeDépart);
            stmt.setDouble(2, longitudeDépart);
            stmt.setDouble(3, latitudeDépart);
            stmt.setString(4,date);
            ResultSet rset = stmt.executeQuery();

       
            while(rset.next()){
                int idTrajet=rset.getInt(1);
                int idDébutTronçon=rset.getInt(2);
                int placeDispoDépart=rset.getInt(3);
                // Tester si le départ de tronçon a des place en dispo pour amener le passenger
                    // par selecter le meme id tronçon dans le BD
                if(testPlaceEnDispo(idTrajet,idDébutTronçon,placeDispoDépart,stmt2)){
                    Suggestion p=new Suggestion(idTrajet,idDébutTronçon,idDébutTronçon);
                    listSuggestion.add(p);
                    // A partir de l'id tronçon de départ, chercher les possibles destinations.
                    getArrivee(idTrajet,idDébutTronçon,placeDispoDépart,stmt1,stmt2);
                }
            }

            // Fermeture
            rset.close();
            stmt.close();
            stmt1.close();
            stmt2.close();
            conn.commit();
        
        } catch (SQLException e) {
            System.err.print("[SelectTronçonDepart]Recherche parcours échoué!");
            e.printStackTrace(System.err);
        }
    }
    
    /**
     * 
     * @param idDebutTronçon
     * Tester les possibile parcours après sa tête
     * Arreter la recherche dès qu'une tronçon n'a pas de place disponible 
     * @throws SQLException 
     */
    public void getArrivee(int idTrajet,int idDebutTronçon ,int placeDispoDepart,
                            PreparedStatement stmt1,PreparedStatement stmt2) throws SQLException{
        stmt1.setInt(1,idDebutTronçon);
        stmt1.setInt(2,idTrajet);          
        ResultSet rset = stmt1.executeQuery();
       
        //Récuperer tous les destinations possibles à partir de id_tronçon départ
          // La recherche est arreté tout de suite si un tronçon peut pas prendre le passenger
        int stop=0;
        while(rset.next()&& stop==0){
            int IdFinTronçon=rset.getInt(1);
            if(testPlaceEnDispo(idTrajet,idDebutTronçon,placeDispoDepart,stmt2)){
                Suggestion p=new Suggestion(idTrajet,idDebutTronçon,IdFinTronçon);
                listSuggestion.add(p);
            } else {
                stop = 1;
            }
        }                
        rset.close();
    }
    
    /**
     * @return calcul s'il reste des places disponible dans le vehicule
     * en faisant COUNT(IdTronçon) dans le BD.
     * @throws SQLException 
     */
    public boolean testPlaceEnDispo(int idTrajet,int idTronçon,int placeDispoDepart,
                                    PreparedStatement stmt1 ) throws SQLException {

        boolean placeDisponible = false;
        stmt1.setInt(1, idTrajet);
        stmt1.setInt(2, idTronçon);
        ResultSet rset1 = stmt1.executeQuery();
        if (rset1.next()) {
            if (rset1.getInt(1) < placeDispoDepart) {
                //System.out.println("placeDispoDepart"+placeDispoDepart);
                //System.out.println("places"+rset1.getInt(1));
                placeDisponible = true;
            }
        }
        rset1.close();
        return placeDisponible;
    }
}
