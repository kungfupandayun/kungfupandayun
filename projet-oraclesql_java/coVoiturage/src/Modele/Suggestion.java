package Modele;
import JDBC.*;
import static JDBC.ConnectJDBC.conn;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import static java.util.Calendar.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * La class Suggestion contient une suggestion de idTrajet, debut tronçon et la fin de tronçon
 * La classe RechecrcheParcours contient une liste de Suggestion
 */
public class Suggestion implements JDBC {
    
    Suggestion sa;
    Suggestion sb;
    

    /**
     * withStop is true only if we have 2 trajets A->B and B->C with 1 stop in between
     */
    private boolean withStop;

    private  int idTrajet;
    private  int idTronçonStart;
    private  int idTronçonEnd;

    private double distance;
    private int dureeTrajet;
    protected String heureTrajet;
    private String placeDépart;
    private String placeArriveé;
    private String placeStop;
//    private String dateTrajet;
    private String heureDépart;
    private String heureArrivée;
    private String immat;
    private double prix;
    private String marque;
    private String modele;
    private String energieUtilisee;
    protected Calendar cal;

    /**
     * Initialize a suggetions based on one trajet with multiple tronçons from tronçon1 to tronçon2
     *
     * @param idTrajet
     * @param idTronçonstart
     * @param idTronçonend
     */
    public Suggestion(int idTrajet, int idTronçonStart, int idTronçonEnd){
        this.idTrajet = idTrajet;
        this.idTronçonStart = idTronçonStart;
        this.idTronçonEnd = idTronçonEnd;
        this.appelJDBC();
        withStop=false;
    }
    
    public Suggestion(int idTrajet1, int idTronçonStart1, int idTronçonEnd1,
                                    int idTrajet2, int idTronçonStart2, int idTronçonEnd2)
    {
        sa=new Suggestion(idTrajet1, idTronçonStart1, idTronçonEnd1);
        sb=new Suggestion(idTrajet2, idTronçonStart2, idTronçonEnd2);

        this.withStop = true;
//        this.dateTrajet = sa.getDateTrajet();
        this.distance = sa.getDistance() + sb.getDistance();
        this.dureeTrajet = sa.getDureeTrajet() + sb.getDureeTrajet();
        this.prix = sa.getPrix() + sb.getPrix();
        this.placeDépart = sa.getPlaceDépart();
        this.placeArriveé = sb.getPlaceArriveé();
        this.placeStop = sa.getPlaceArriveé();
        this.heureDépart=sa.getHeureDépart();
        this.heureArrivée=sb.getHeureArrivée();
        this.modele = sa.getModele() + " | " + sb.getModele();
        this.marque = sa.getMarque() + " | " + sb.getMarque();
        this.energieUtilisee = sa.getEnergieUtilisee() + " | " + sb.getEnergieUtilisee();
    }
    


    /**
     * Appel les methodes de SQL pour faire le calcul de distance, heure arrivee, vehicule utilisé
     * et prix.
     */
    @Override
    public void appelJDBC() {
        if(conn==null){
            ConnectJDBC c=new ConnectJDBC();
        }
        try {
            cal = Calendar.getInstance();

            fetchDepartEtArrivee();
            fetchDistanceAndDuration();
            fetchHeureDepartTrajet();
            fetchHeureDepartTronçon();
            fetchHeureArrivee();
            fetchVoiture();
            fetchPrice();

            conn.commit();
        } catch (SQLException e) {
            System.err.println("[Suggestion]Des info sur la suggestion n'est pas trouvé!");
        }
    }


    /**
     * Fetch the distance and duration of all possible tronçons from DB
     *
     * @throws SQLException
     */
    private void fetchDepartEtArrivee() throws SQLException {
        PreparedStatement statement;

        statement = conn.prepareStatement(
                "SELECT id_tronçon, ville_depart, ville_arrivee from Tronçon " +
                        "WHERE id_trajet=? AND (id_tronçon=? " +
                        "OR id_tronçon=?)"
        );

        statement.setInt(1, idTrajet);
        statement.setInt(2, idTronçonStart);
        statement.setInt(3, idTronçonEnd);

        ResultSet results = statement.executeQuery();

        while (results.next()) {
            //System.out.println("----- Start "  + idTrajet + " " + idTronçonStart + " " + idTronçonEnd);
            //System.out.println("Got results: " + results.getInt(1) + results.getString(2) + results.getString(3));
            if (results.getInt(1) == idTronçonStart) {
                this.placeDépart = results.getString(2);
                //System.out.println("Start: "  + results.getString(2) + placeDépart );
            }
            if (results.getInt(1) == idTronçonEnd) {
                this.placeArriveé = results.getString(3);
                //System.out.println("End: "  + results.getString(3) + placeArriveé );

            }
        }

        results.close();
        statement.close();
    }

    /**
     * Fetch the distance and duration of all possible tronçons from DB
     *
     * @throws SQLException
     */
    private void fetchDistanceAndDuration() throws SQLException {
        PreparedStatement statement;

        statement = conn.prepareStatement(
                "SELECT SUM(dist_parcourue), SUM(duree_tronçon) from Tronçon " +
                        "WHERE id_trajet=? AND id_tronçon>=? " +
                        "AND id_tronçon<=?"
        );

        statement.setInt(1, idTrajet);
        statement.setInt(2, idTronçonStart);
        statement.setInt(3, idTronçonEnd);

        ResultSet results = statement.executeQuery();

        if (results.next()) {
            this.distance = results.getDouble(1);
            this.dureeTrajet = results.getInt(2);
        } else {
            System.err.println("Error getting 'distance/duree' of 'trajet' " + idTrajet);
        }

        cal.add(MINUTE, getDureeTrajet());


        results.close();
        statement.close();
    }

    /**
     * Fetch the heure depart of all possible trajets from DB
     *
     * @throws SQLException
     */
    private void fetchHeureDepartTrajet() throws SQLException {

        PreparedStatement statement = conn.prepareStatement("SELECT temps_trajet from Trajet "
                + "WHERE id_trajet=? ");

        statement.setInt(1, idTrajet);

        ResultSet results = statement.executeQuery();

        if (results.next()) {
            this.heureTrajet = results.getTimestamp(1).toString().replace(".0","");
            ////System.out.println(heureTrajet);
        } else {
            System.err.println("Error getting 'heure' of 'trajet '" + idTrajet);
        }



        results.close();
        statement.close();

        String dateheure =heureTrajet;
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date strDate = null;
        try {
            strDate = ft.parse(dateheure);
        } catch (ParseException ex) {
            Logger.getLogger(Suggestion.class.getName()).log(Level.SEVERE, null, ex);
        }

        cal.setTime(strDate);
    }

    /**
     * Fetch the heure depart of all possible tronçons from DB
     *
     * @throws SQLException
     */
    private void fetchHeureDepartTronçon() throws SQLException {

        PreparedStatement statement = conn.prepareStatement("SELECT SUM(duree_tronçon) from Tronçon "
                + "WHERE id_trajet=? AND id_tronçon<?");

        statement.setInt(1, idTrajet);
        statement.setInt(2, idTronçonStart );

        ResultSet results = statement.executeQuery();

        int dureeAvantDepart = 0;
        if (results.next()) {
            dureeAvantDepart = results.getInt(1);
        } else {
            System.err.println("Error getting 'heure' and 'date' of 'troncon' " + idTronçonStart);
        }

        cal.add(MINUTE, dureeAvantDepart);
        SimpleDateFormat ft = new SimpleDateFormat("HH:mm yyyy-MM-dd");
        ft.setCalendar(cal);

        this.heureDépart = ft.format(cal.getTime());

        ////System.out.println("heureDépart"+this.heureDépart);
        results.close();
        statement.close();
    }

    /**
     * Fetch the heure arrivee
     */
    private void fetchHeureArrivee() {
            
            cal.add(MINUTE, getDureeTrajet());
            SimpleDateFormat ft = new SimpleDateFormat("HH:mm yyyy-MM-dd");
            ft.setCalendar(cal);
            this.heureArrivée = ft.format(cal.getTime());

    }

    /**
     * Fetch the cars (immatriculation) of all possible trajets from DB
     *
     * @throws SQLException
     */
    private void fetchVoiture() throws SQLException {

        PreparedStatement statement = conn.prepareStatement("SELECT immatriculation FROM ConducteurVehiculeTrajet "
                + "WHERE id_trajet=?");

        statement.setInt(1, idTrajet);

        ResultSet results = statement.executeQuery();

        if(results.next()){
            immat = results.getString(1);
        } else {
            System.err.println("Error getting 'voiture' of 'trajet' " + idTrajet);
        }


        results.close();
        statement.close();
    }

    /**
     * Fetch the price based on energie utilisee, puiss fiscale, et distance
     *
     * @throws SQLException
     */
    private void fetchPrice() throws SQLException {
        // Coût kilométrique = Puissance fiscale    0,10 €
        // valant 1 pour l’essence , 1,5 pour le diesel et 0,5 pour l’électrique
        PreparedStatement statement = conn.prepareStatement("SELECT energie_utilisee,puiss_fiscale,marque,modele from Vehicule "
                + "WHERE IMMATRICULATION=? ");

        statement.setString(1, getImmat());

        ResultSet results = statement.executeQuery();

        if (results.next()) {
            EnergieUtilisee energieUtilisee = EnergieUtilisee.valueOf(results.getString(1));
            int puissFiscale = results.getInt(2);
            this.energieUtilisee = energieUtilisee.toString();
            this.marque = results.getString(3);
            this.modele = results.getString(4);
            prix = calculatePrice(getDistance(), energieUtilisee, puissFiscale);
        } else {
            System.err.println("Error calculating 'prix' of 'trajet' " + idTrajet);
        }

        results.close();
        statement.close();
    }

    /**
     * Helper function to calculate the price based on energie utilisee, puiss fiscale, et distance
     *
     * @param distance
     * @param energie
     * @param puissFiscale
     * @return
     */
    private double calculatePrice(Double distance, EnergieUtilisee energie, int puissFiscale) {
        double coeff=0;
        switch(energie){
            case Essence:
                coeff = 1;
                break;
            case Diesel :
                coeff = 1.5;
                break;
            case Electrique:
                coeff = 0.5;
                break;
        }
        return puissFiscale * coeff * 0.10 * distance;
    }
 

    /**
     * @return the idTrajet
     */
    public int getIdTrajet() {
        return idTrajet;
    }

    /**
     * @return the idTronçon1
     */
    public int getIdTronçonStart() {
        return idTronçonStart;
    }

//    /**
//     * @return the idTrajet2
//     */
//    public int getIdTrajet2() {
//        return idTrajet2;
//    }

    /**
     * @return the idTronçon2
     */
    public int getIdTronçonEnd() {
        return idTronçonEnd;
    }

    /**
     * @return the distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * @return the dureeTrajet
     */
    public int getDureeTrajet() {
        return dureeTrajet;
    }


    /**
     * @return the heureDépart
     */
    public String getHeureDépart() {
        return heureDépart;
    }

    /**
     * @return the heureArrivée
     */
    public String getHeureArrivée() {
        return heureArrivée;
    }

    /**
     * @return the immat
     */
    public String getImmat() {
        return immat;
    }

    /**
     * @return the prix
     */
    public double getPrix() {
        return prix;
    }

    public String getPlaceDépart() {
        return placeDépart;
    }

    public String getPlaceArriveé() {
        return placeArriveé;
    }

    public String getPlaceStop() {
       
        return placeStop;
    }

    public String getMarque() {
        return marque;
    }

    public String getModele() {
        return modele;
    }

    public String getEnergieUtilisee() {
        return energieUtilisee;
    }

    /**
     * @return the withStop
     */
    public boolean isWithStop() {
        return withStop;
    }
    
       
    public String toString(){
        String line=null;
        if(!withStop){
            line ="idTrajet=>"+idTrajet+
                "\nidTronçon départ=>"+idTronçonStart+
                "\nidTronçon arrivé=>"+idTronçonEnd+
                "\nplace départ=>"+getPlaceDépart()+
                "\nplace arrivé=>"+getPlaceArriveé()+
                "\ndistance=>"+ getDistance() +
                "\ndureeTrajet=>"+ getDureeTrajet() +
                "\nheureDépart=>"+ getHeureDépart() +
                "\nheureArrivée=>"+ getHeureArrivée() +
                "\nimmatriculation=>"+ getImmat() +
                "\nprix=>"+ getPrix() ;
        }else{
            line= "idTrajet a=>"+sa.getIdTrajet()+
                "\nidTronçon départ a=>"+sa.getIdTronçonStart()+
                "\nidTronçon arrivé a=>"+sa.getIdTronçonEnd()+
                "\nimmatriculation a=>"+ sa.getImmat() +
                "\nheureDépart a=>"+ sa.getHeureDépart() +
                "\nheureArrivée a=>"+ sa.getHeureArrivée() +
                "\n-------Changement Vehicule------"+
                "\nidTrajet b=>"+sb.getIdTrajet()+
                "\nidTronçon départ b=>"+sb.getIdTronçonStart()+
                "\nidTronçon arrivé b=>"+sb.getIdTronçonEnd()+
                "\nimmatriculation b=>"+ sb.getImmat() +
                "\nheureDépart b=>"+ sb.getHeureDépart() +
                "\nheureArrivée b=>"+ sb.getHeureArrivée() +
                "\nplace départ=>"+getPlaceDépart()+
                "\nplace arrivé=>"+getPlaceArriveé()+
                "\nplace stop=>"+getPlaceStop() +
                "\ndistance totale=>"+ getDistance() +
                "\ndureeTrajet=>"+ getDureeTrajet() +
                "\nprix total=>"+ getPrix() ;
        }

          return line;      
    }


        
}
        
        


         

            