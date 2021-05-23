
package Modele;

import JDBC.ConnectJDBC;
import static JDBC.ConnectJDBC.conn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import static java.util.Calendar.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Verifier chaque couple de tronçon possible de départ et d'arrival
 * notamment les contraints de la distance et temps.
 */
public class RechecheParcoursDestCalculSugg extends AbstractRechercheParcours{
    private ArrayList<RechercheParcoursDestPossibilites> possibleDepart ;
    private ArrayList<RechercheParcoursDestPossibilites> possibleArrivee ;
    private ArrayList<Suggestion> suggestionAvecArret;

    public RechecheParcoursDestCalculSugg( ArrayList<RechercheParcoursDestPossibilites> possibleDepart,
                                            ArrayList<RechercheParcoursDestPossibilites> possibleArrivee)
    {
        this.possibleDepart=possibleDepart;
        this.possibleArrivee=possibleArrivee;
        suggestionAvecArret=new ArrayList<> ();
    }

    public String toString(){
        String line="POSSIBLEDEPART:\n";
        for(RechercheParcoursDestPossibilites p:possibleDepart)
        {    line+="possibleDepart\n ";
            line+= p.toString();
        }

        line+="POSSIBLEARRIVE\n";
        for(RechercheParcoursDestPossibilites p:possibleArrivee)
        {   line+="possibleArrive\n ";
            line+= p.toString();
        }
        return line;
    }

    /**
     * Chercher les parcours possibles sans changement de vehicule
     *  cad: idtrajet de départ == idtrajet d'arrivee
     *       idtronçon de depart <= idtronçon d'arrivee
     */
    public void getSuggestion(){
        for(RechercheParcoursDestPossibilites i:possibleDepart){
            for(RechercheParcoursDestPossibilites j:possibleArrivee){
                //si les deux tronçon appartient le meme id trajet
                if((i.getIdTrajet()==j.getIdTrajet())){
                    if( i.getIdTronçon()<=j.getIdTronçon()){
                        if(VerifiePlaceRestant(i.getIdTrajet(),i.getIdTronçon(),j.getIdTronçon()
                                            ,i.getPlaceDispo())){
                            Suggestion p=new Suggestion(i.getIdTrajet(),i.getIdTronçon(),j.getIdTronçon());
                            listSuggestion.add(p);
                        }
                    }
                }else{
                    //sinon cherche un point commun entre deux trajet
                    CherchePointCommun(i.getIdTrajet(),i.getIdTronçon(),i.getPlaceDispo(),
                                        j.getIdTrajet(),j.getIdTronçon(),j.getPlaceDispo());
                }
            }
        }


            if(!suggestionAvecArret.isEmpty()){
                for(Suggestion j: suggestionAvecArret){
                    listSuggestion.add(j);
                }
            }
    }



    /**
     * Chercher les points commun entre 2 trajets possibles
     * @param idTrajet1
     * @param idTronçonDep
     * @param placeDispo1
     * @param idTrajet2
     * @param idTronçonArr
     * @param placeDispo2 
     */
    public void CherchePointCommun(int idTrajet1,int idTronçonDep,int placeDispo1,
                                    int idTrajet2,int idTronçonArr,int placeDispo2){

        String SQL1 = "SELECT DISTINCT t1.id_tronçon, t2.id_tronçon " +
                "FROM " + "Tronçon t1,  Tronçon t2  "
                + "WHERE t1.id_trajet=?"
                + "AND t2.id_trajet=? "
                + "AND t1.long_arrivee<=t2.long_depart+0.01"
                + "AND t1.long_arrivee>=t2.long_depart-0.01"
                + "AND t1.lat_arrivee<=t2.lat_depart+0.01"
                + "AND t1.lat_arrivee>=t2.lat_depart-0.01"
                + "AND t1.id_tronçon>=?"
                + "AND t2.id_tronçon<=?";

        if(conn==null) {
           ConnectJDBC c = new ConnectJDBC();
        }
        try {
            // Creation de la requete
            PreparedStatement stmt = conn.prepareStatement(SQL1);

            stmt.setInt(1, idTrajet1);
            stmt.setInt(2, idTrajet2);
            stmt.setInt(3, idTronçonDep);
            stmt.setInt(4,idTronçonArr);
            ResultSet rset = stmt.executeQuery();


            while(rset.next()){
                int idCommunTron1=rset.getInt(1);
                int idCommunTron2=rset.getInt(2);

                // verifier le contraint de temps,place disponible et place entre deux point commun
                if(VerifieLheure(idTrajet1,idTronçonDep,idCommunTron1,idTrajet2,idCommunTron2,idTronçonArr)&&
                    VerifiePlaceRestant(idTrajet1,idTronçonDep,idCommunTron1,placeDispo1) &&
                        VerifiePlaceRestant(idTrajet2,idTronçonArr,idCommunTron2,placeDispo2)){

                    Suggestion p=new Suggestion(idTrajet1,idTronçonDep,idCommunTron1,
                                                                            idTrajet2,idCommunTron2,idTronçonArr);
                    suggestionAvecArret.add(p);

                }
            }

            // Fermeture
            rset.close();
            stmt.close();
            conn.commit();

        } catch (SQLException e) {
            System.err.print("[SelectTronçonDepart]Recherche parcours échoué!");
            e.printStackTrace(System.err);
        }
    }

    /**
     * Verifie que le temps d'arrivé de trajet a et temps de départure du trajet est cohérent
     * @param idTrajet1
     * @param idTronçonDep
     * @param idCommunTron1
     * @param idTrajet2
     * @param idCommunTron2
     * @param idTronçonArr
     * @return 
     */
    public boolean VerifieLheure(int idTrajet1, int idTronçonDep, int idCommunTron1, int idTrajet2, int idCommunTron2, int idTronçonArr) {

        boolean valid = false;
        Calendar heureTrajetA = GetLheure(idTrajet1);
        Calendar heureTrajetB = GetLheure(idTrajet2);

        // get the arrival time of first trajet
        heureTrajetA.add(MINUTE, GetDureeDesTronçon(idTrajet1, idCommunTron1));
        // get the departure time of second trajet
        heureTrajetB.add(MINUTE, GetDureeDesTronçon(idTrajet2, idCommunTron2-1));
        if (heureTrajetB.compareTo(heureTrajetA) >= 0) {
            heureTrajetA.add(MINUTE, 60);
            if (heureTrajetB.compareTo(heureTrajetA) <= 0) {
                valid = true;
            }
        }

        return valid;
    }

    public Calendar GetLheure(int idtrajet){
        Calendar c1=null;
        String SQL = "SELECT  temps_trajet from Trajet "
                + " WHERE id_trajet=? ";

        if (conn == null) {
            ConnectJDBC c = new ConnectJDBC();
        }
        try {
            PreparedStatement stmt = conn.prepareStatement(SQL);

            stmt.setInt(1, idtrajet);

            ResultSet rset = stmt.executeQuery();

            if (rset.next()) {
                String dateheure = rset.getTimestamp(1).toString().replace(".0", "");
                c1 = Calendar.getInstance();
                SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                java.util.Date strDate = null;
                try {
                     strDate = ft.parse(dateheure);
                } catch (ParseException ex) {
                    Logger.getLogger(Suggestion.class.getName()).log(Level.SEVERE, null, ex);
                }
                c1.setTime(strDate);
            }

            rset.close();
            stmt.close();

        } catch (SQLException e) {
            System.err.print("[Verifie place]Recherche parcours échoué!");
            e.printStackTrace(System.err);
        }
        return c1;
    }

    /**
     * get la durée entre le premier troçon jusqu'à l'arrivée du trajet a
     * get la durée entre le premier tronçon jusqu'à le départ du trajet b
     * @param idtrajet
     * @param idTronçonDep
     * @return
     */
    int GetDureeDesTronçon(int idtrajet, int idTronçon) {
        String SQL = "SELECT SUM(duree_tronçon) from Tronçon "
                + "WHERE id_trajet=? AND id_tronçon>=1 "
                + "AND id_tronçon<=?";

        int duree = 0;

        if (conn == null) {
            ConnectJDBC c = new ConnectJDBC();
        }
        try {

            PreparedStatement stmt = conn.prepareStatement(SQL);

            stmt.setInt(1, idtrajet);
            stmt.setInt(2, idTronçon);


            ResultSet rset = stmt.executeQuery();
            if(rset.next()){
                duree=rset.getInt(1);
            }

             rset.close();
             stmt.close();

        } catch (SQLException e) {
            System.err.print("[Verifie place]Recherche parcours échoué!");
            e.printStackTrace(System.err);
        }
        return duree;
    }

    /**
     *
     * @param idTrajet
     * @param idTronçonDebut
     * @param idTronçonFin
     * @param placeDispoDepart
     * @return
     */
    public boolean VerifiePlaceRestant(int idTrajet,int idTronçonDebut,int idTronçonFin
                                        ,int placeDispoDepart){
        String SQL3 = "SELECT COUNT(id_trajet) from  PARCOURSTRONÇON "
                + "WHERE id_trajet=? and id_tronçon=?";
        boolean placeDisponible = true;


         if(conn==null) {
           ConnectJDBC c = new ConnectJDBC();
        }
        try {
            PreparedStatement stmt = conn.prepareStatement(SQL3);
            // Verifier si pour chaque troçon de départ jusqu'à la fin a une place en disposition
            for(int idTronçon=idTronçonDebut;((idTronçon<=idTronçonFin) &&(placeDisponible == true));idTronçon++){
            stmt.setInt(1, idTrajet);
            stmt.setInt(2, idTronçon);
            ResultSet rset = stmt.executeQuery();
            if (rset.next()) {
                //System.out.println("placeDispoDepart"+placeDispoDepart);
                //System.out.println("places"+rset.getInt(1));
                if (rset.getInt(1) == placeDispoDepart) {
                    //tous les places sont occupées
                    placeDisponible = false;
                                    

                }
                if (rset.getInt(1) > placeDispoDepart) {
                    System.err.print("Plus de réservation de place que le nb de place en dispo!");
                }
              }
            }

        }
        catch (SQLException e) {
            System.err.print("[Verifie place]Recherche parcours échoué!");
            e.printStackTrace(System.err);
        }
        return placeDisponible;
    }

        /**
     * Transformer la date et  l'heure extrait de SQL au format Calendar
     * @param dateheure
     * @param dureeTrajet
     * @return calendar
     */
     public Calendar getCalendar(String dateheure, int dureeTrajet){

        Calendar c1 = Calendar.getInstance();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        java.util.Date strDate = null;
        try {
            strDate = ft.parse(dateheure);
        } catch (ParseException ex) {
            Logger.getLogger(Suggestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        c1.setTime(strDate);
        c1.add(MINUTE,dureeTrajet);
        //System.out.println(c1.toString());
        return c1;
    }
}