package Modele;
import JDBC.*;
import static JDBC.ConnectJDBC.conn;
import java.sql.*;

/**
 * Classe vehicule.
 * Création d'un nouveau id de vehicule ou choisir un vehicule.
 * @author eliasc
 */
public class Vehicule implements JDBC{
    private  String immatriculation;
    private  String marque;
    private  String modele;
    private  EnergieUtilisee energieUtilisee;
    private  int puissFiscale;
    private  int nbPlace;

    public Vehicule(String immatriculation, String marque, String modele, 
                    EnergieUtilisee energieUtilisee, int puissFiscale, int nbPlace) 
    {
        this.immatriculation=immatriculation;
        this.marque=marque;
        this.modele=modele;
        this.energieUtilisee=energieUtilisee;
        this.puissFiscale=puissFiscale;
        this.nbPlace=nbPlace;
        this.appelJDBC();
    }
    
    
    public Vehicule(String immatriculation)
    {
        this.immatriculation=immatriculation;
        this.choisiVehicule ();
    }
    

    
    public void choisiVehicule (){
        if(conn==null){
            ConnectJDBC c=new ConnectJDBC();
        }
        try {
            // Creation de la requete
            PreparedStatement stmt = conn.prepareStatement("SELECT * from Vehicule "
                                                        + "WHERE immatriculation=?"); 
            stmt.setString(1, getImmatriculation());       
                        

            // Affichage du résultat
            ResultSet rset = stmt.executeQuery();

            if(rset.next()){
            this.marque=rset.getString (2);
            this.modele=rset.getString (3);
            this.energieUtilisee=EnergieUtilisee.valueOf(rset.getString(4));
            this.puissFiscale=rset.getInt (5);
            this.nbPlace=rset.getInt (6);    
            }else{
                System.err.println("Vehicule pas trouvé");
                setImmatriculation(null);
            }

            // Fermeture 
            rset.close();
            stmt.close();
            conn.commit();
            
          } catch (SQLException e) {
              setImmatriculation(null);
            System.err.println("[SelectVehicule]Vehicule n'est pas chosi!");
            e.printStackTrace();
        
        }
    }

     /**
     * Création d'un nouveau id vehicule dans BD.
     */
    @Override
    public void appelJDBC() {
        if(conn==null){
            ConnectJDBC c=new ConnectJDBC();
        }
        try {
            // Execution des requetes et affichage des résultats
            String SQL="INSERT INTO Vehicule "
                    + "(immatriculation, marque, modele, energie_utilisee, puiss_fiscale, nb_place)"
                    + "VALUES (?,?,?,?,?,?)";

            PreparedStatement stmt = conn.prepareStatement(SQL);

            stmt.setString(1, getImmatriculation());
            stmt.setString(2, getMarque());
            stmt.setString(3, getModele());
            stmt.setString(4, getEnergieUtilisee().toString());
            stmt.setString(5, Integer.toString(getPuissFiscale()));
            stmt.setString(6, Integer.toString(getNbPlace()));

            // execute the preparedstatement
            stmt.execute();
            
            // Fermetures
            stmt.close();
            conn.commit();

        } catch (SQLException e) {
            setImmatriculation(null);
            System.err.println("[Vehicule]Le vehicule n'est pas enregistré!");
            e.printStackTrace();
        }
    }

    /**
     * @return the immatriculation
     */
    public  String getImmatriculation() {
        return immatriculation;
    }

    /**
     * @return the marque
     */
    public  String getMarque() {
        return marque;
    }

    /**
     * @return the modele
     */
    public  String getModele() {
        return modele;
    }

    /**
     * @return the energieUtilisee
     */
    public  EnergieUtilisee getEnergieUtilisee() {
        return energieUtilisee;
    }

    /**
     * @return the puissFiscale
     */
    public int getPuissFiscale() {
        return puissFiscale;
    }

    /**
     * @return the nbPlace
     */
    public int getNbPlace() {
        return nbPlace;
    }

    /**
     * @param immatriculation the immatriculation to set
     */
    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }
    
    
    
    


}
