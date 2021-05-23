package Modele;

import JDBC.ConnectJDBC;
import java.sql.*;
import java.util.ArrayList;

import static JDBC.ConnectJDBC.conn;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  Recherche parcours avec Destination.
 */
public class RechercheParcoursDest extends AbstractRechercheParcours{
    
    private String villeArr;
    private double longitudeArr;
    private double latitudeArr;
    private double positionMaxRange;
    private Compte compte;
    private ArrayList<RechercheParcoursDestPossibilites> possibleDepart ;
    private ArrayList<RechercheParcoursDestPossibilites> possibleArrivee ;
    
    public RechercheParcoursDest(String villeDépart, double longitudeDépart,
                                 double latitudeDépart, String date,
                                 String villeArr, double longitudeArr,
                                 double latitudeArr,Compte c) {

        this.villeDépart = villeDépart;
        this.longitudeDépart = longitudeDépart;
        this.latitudeDépart = latitudeDépart;
        this.date = date;
        this.villeArr = villeArr;
        this.longitudeArr = longitudeArr;
        this.latitudeArr = latitudeArr;
        this.positionMaxRange = 100.0;
        this.listSuggestion = new ArrayList<>();
        this.compte=c;
        possibleDepart =new ArrayList<>();
        possibleArrivee =new ArrayList<>();
        this.getIDdepart();
        this.getIDarrivee();
        System.out.print("possibleDepart" + possibleDepart);
        System.out.print("possibleArrivee" + possibleArrivee);
        RechecheParcoursDestCalculSugg calSug=new RechecheParcoursDestCalculSugg(possibleDepart, possibleArrivee);
        calSug.getSuggestion();
        
    }
   
    /**
     * Récuperer tous les tronçons possibles du départ
     */
    public void getIDdepart(){
         String SQL1 = "SELECT t1.id_trajet,id_tronçon,place_dispo_depart,temps_trajet from Tronçon t1 "
                + "FULL OUTER JOIN Trajet t2 "
                + "ON t1.id_trajet=t2.id_trajet "
                + "WHERE ville_depart=? "
                + "AND long_depart =? "
                + "AND lat_depart=? "
                + "AND date_trajet=?";
         
          if(conn==null) {
           ConnectJDBC c = new ConnectJDBC();
        }
        try {
            // Creation de la requete
            PreparedStatement stmt = conn.prepareStatement(SQL1); 

            stmt.setString(1, villeDépart);
            stmt.setDouble(2, longitudeDépart);
            stmt.setDouble(3, latitudeDépart);
            stmt.setString(4,date);
            ResultSet rset = stmt.executeQuery();

       
            while(rset.next()){
                int idTrajet=rset.getInt(1);
                int idTronçon=rset.getInt(2);
                int placeDispoDépart=rset.getInt(3);
                String temps_trajet = rset.getTimestamp(4).toString().replace(".0","");

                //System.out.print(temps_trajet);
                String dateheure = temps_trajet;
                SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
                java.util.Date strDate = null;
             try {
                 strDate = ft.parse(dateheure);
            } catch (ParseException ex) {
                Logger.getLogger(Suggestion.class.getName()).log(Level.SEVERE, null, ex);
            }
                possibleDepart.add(new RechercheParcoursDestPossibilites(idTrajet,idTronçon,
                                    placeDispoDépart,strDate));
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
     * Récuperer tous les tronçons possibles de l'arrivée
     */
        public void getIDarrivee(){
         String SQL1 = "SELECT t1.id_trajet,id_tronçon,place_dispo_depart,temps_trajet from Tronçon t1 "
                + "FULL OUTER JOIN Trajet t2 "
                + "ON t1.id_trajet=t2.id_trajet "
                + "WHERE ville_arrivee=? "
                + "AND long_arrivee =? "
                + "AND lat_arrivee=? ";
         
          if(conn==null) {
           ConnectJDBC c = new ConnectJDBC();
        }
        try {
             // Creation de la requete
            PreparedStatement stmt = conn.prepareStatement(SQL1); 

            stmt.setString(1, villeArr);
            stmt.setDouble(2, longitudeArr);
            stmt.setDouble(3, latitudeArr);
            ResultSet rset = stmt.executeQuery();

       
            while(rset.next()){
                int idTrajet=rset.getInt(1);
                int idTronçon=rset.getInt(2);
                int placeDispoDépart=rset.getInt(3);
                String temps_trajet = rset.getTimestamp(4).toString().replace(".0","");

                String dateheure = temps_trajet;
                SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                java.util.Date strDate = null;
             try {
                 strDate = ft.parse(dateheure);
                 possibleArrivee.add(new RechercheParcoursDestPossibilites(idTrajet,idTronçon,
                                    placeDispoDépart,strDate));
                } catch (ParseException ex) {
                    Logger.getLogger(Suggestion.class.getName()).log(Level.SEVERE, null, ex);
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

}