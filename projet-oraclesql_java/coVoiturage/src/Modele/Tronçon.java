package Modele;

import JDBC.*;
import static JDBC.ConnectJDBC.conn;
import java.sql.*;

/**
* Classe Tronçon.
* @author souhatibi
*/
public class Tronçon extends AbstractTrajet implements JDBC{
	private static ConnectJDBC connexion;
	private int id_tronçon;
	private String ville_départ;
	private double longitude_départ;
	private double latitude_départ;
	private String ville_arrivée;
	private double longitude_arrivée;
	private double latitude_arrivée;
	private int  durée_tronçon; //en minute
	private int temps_attente; //en minutes 
	private double distance_parcourue; //en km
	private Etat_Tronçon_Conducteur etat_tronçon_conducteur;
	
	static int R= 6378137; //rayon de la terre en km
	
	public Tronçon(int id_tronçon,String ville_départ,double longitude_départ,double latitude_départ,
			String ville_arrivée,double longitude_arrivée,double latitude_arrivée, int durée_tronçon,
			Etat_Tronçon_Conducteur etat_tronçon_cond) {
		this.id_tronçon=id_tronçon;
		this.ville_départ=ville_départ;
		this.longitude_départ=longitude_départ;
		this.latitude_départ=latitude_départ;
		this.ville_arrivée=ville_arrivée;
		this.longitude_arrivée=longitude_arrivée;
		this.latitude_arrivée=latitude_arrivée;
		this.durée_tronçon=durée_tronçon;
	    this.distance_parcourue = R * Math.acos( Math.sin(latitude_départ) * Math.sin(latitude_arrivée) + Math.cos(longitude_arrivée - longitude_départ) 
	    * Math.cos(latitude_départ) * Math.cos(latitude_arrivée));
	    this.etat_tronçon_conducteur=etat_tronçon_cond;
	    this.temps_attente=-1; //valeur par défaut s'il n'y a de temps d'attente précisée
	}

        /**
         * @return info sur un tronçon.
         */
        @Override
        public String toString(){
            return "id_tronçon=>"+id_tronçon+
                   "\nville_départ=>"+ville_départ+
                   "\nville_arrivée=>"+ville_arrivée+
                   "\ndurée_tronçon=>"+getDurée_tronçon()+
                   "\ntemps_attente=>"+temps_attente+
                   "\ndistance_parcourue=>"+distance_parcourue+
                   "\netat_tronçon_conducteur"+etat_tronçon_conducteur;
        }
        
    
    /**
     * Inserer un tronçon dans la BD.
     */
    @Override
    public void appelJDBC() {
        if(conn==null){
            ConnectJDBC c=new ConnectJDBC();
        }
        try {
                // Execution des requetes et affichage des résultats
                String SQL="INSERT INTO Tronçon "
                   + "(id_tronçon,long_depart,lat_depart,ville_depart,long_arrivee,lat_arrivee,ville_arrivee,"
                   + "dist_parcourue,duree_tronçon,etat_tronçon_conducteur,id_trajet) "
                   + "VALUES (?,?,?,?,?,?,?,?,?,?,?)";

                PreparedStatement stmt = conn.prepareStatement(SQL);
	
                stmt.setInt(1, id_tronçon);
                stmt.setDouble(2, longitude_départ);
                stmt.setDouble(3,latitude_départ);
                stmt.setString(4, ville_départ);
                stmt.setDouble(5, longitude_arrivée);
                stmt.setDouble(6, latitude_arrivée);
                stmt.setString(7, ville_arrivée);
                stmt.setDouble(8, distance_parcourue);
                stmt.setInt(9, getDurée_tronçon());
                stmt.setString(10, etat_tronçon_conducteur.toString());
                stmt.setInt(11, id_trajet);
                // execute the preparedstatement
                stmt.execute();


                // Fermetures
                stmt.close();
                conn.commit();

        } catch (SQLException e) {
            System.err.println("[InsertTronçon]Tronçon n'est pas enregistré!");
            e.printStackTrace(System.err);
        }
    }
    /**
     * @return the id_tronçon
     */
    public int getId_tronçon() {
        return id_tronçon;
    }

    /**
     * @return the durée_tronçon
     */
    public int getDurée_tronçon() {
        return durée_tronçon;
    }
    
    
    
    
}
	
