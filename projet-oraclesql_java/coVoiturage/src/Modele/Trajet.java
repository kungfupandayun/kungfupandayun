package Modele;
import Exceptions.*;
import JDBC.*;
import static JDBC.ConnectJDBC.conn;
import static Modele.Etat_Tronçon_Conducteur.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import static java.util.Calendar.MINUTE;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
*   Instancier un nouveau trajet, ainsi des tronçons.
*   Appeler @methode ajouteTronçon pour ajouter un tronçon dans un trajet.
*/
public class Trajet extends AbstractTrajet implements JDBC{
	private ArrayList<Tronçon> tronçons;
        private int comptTronçon; 
        private int dureeTrajet=0;
       
        
	public Trajet(int nbPlacesDisponibles,String date, String heure) {
		this.nbPlacesDisponibles = nbPlacesDisponibles;
		this.date= date;
		this.heure=heure;
		this.tronçons= new ArrayList<>(); 
                this.comptTronçon=0;
	}

    /** 
     * Ajoute un tronçon dans la BD.
     */
    public void ajouteTronçon(String ville_départ,double longitude_départ,double latitude_départ,
                              String ville_arrivée,double longitude_arrivée,double latitude_arrivée,int durée_tronçon,
                              Etat_Tronçon_Conducteur etat_tronçon_cond){
        comptTronçon++;
        Tronçon t=new  Tronçon( comptTronçon, ville_départ, longitude_départ, latitude_départ,
			 ville_arrivée, longitude_arrivée, latitude_arrivée, durée_tronçon,
			 etat_tronçon_cond);
        
        tronçons.add(t);
    }
    

    /**
     * Inserer un nouveau trajet dans la BD.
     */
    @Override
    public void appelJDBC() {
        if(conn==null){
            ConnectJDBC c=new ConnectJDBC();
        }
        try {
                // Execution des requetes et affichage des résultats

                String SQL="INSERT INTO Trajet (id_trajet,date_trajet,heure_trajet,temps_trajet,place_dispo_depart) "
                            + "VALUES (indIDTrajet.nextval,?,?,TO_DATE(?,'YYYY-MM-DD HH24:MI'),?)";
                PreparedStatement stmt=conn.prepareStatement(SQL,Statement.RETURN_GENERATED_KEYS);
               
                stmt.setString(1,date );
                stmt.setString(2,heure);
                stmt.setString(3,date+" "+heure);
                stmt.setInt(4,nbPlacesDisponibles);
                

                // execute the preparedstatement
                stmt.execute();

                String STMT="select indIDTrajet.currval from Dual";
                // Creation de la requete
                  Statement stmt1 =conn.createStatement();

                  // Execution de la requete
                 ResultSet rset = stmt1.executeQuery(STMT);

               // Affichage du resultat
               
               while (rset.next()) {
                   id_trajet=rset.getInt(1);
                   //set id trajet pour tous les tronçons
                    for (Tronçon tron : tronçons) {
                        tron.setId_trajet(rset.getInt(1));
                    }
               }
                 stmt1.close();
                 stmt.close();
                 conn.commit();
        } catch (SQLException e) {
            System.err.println("[InsertTrajet]Trajet n'est pas enregistré!");
            e.printStackTrace();
        }

    }
    
    
    public  ArrayList<Tronçon> getTronçons(){
        return this.tronçons;
    }
   
    /**
     * Inserer un trajet et des tronçons sans verifier contraint vehicule et compte associés
     * Fonction créée pour faire le test
     * @param trajet
     * @throws Exception
     */
    public void AddTrajetTronçon(){
            this.appelJDBC();         
            for (Tronçon t : tronçons) {
                t.appelJDBC();
            } 
    }
    
    
    
    /**
     *  Transformer la date d'arivée et l'heure d'arrivée en format Calendar
     * @return Calendar
     */
      public int getDureeTrajet(){
        for (Tronçon t : tronçons) {
                dureeTrajet+=t.getDurée_tronçon();
            } 
      
        return dureeTrajet;
    }
    
  
}
