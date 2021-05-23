package Modele;

import Exceptions.*;
import JDBC.*;
import static JDBC.ConnectJDBC.conn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *  Choisir et Enregistrer un parcours choisi par un utilisateur.
 *  @param i dans le constructeur est le numero de parcours dans la liste de suggestion.
 */
public final class Parcours extends AbstractRechercheParcours implements JDBC{

    private int id_parcours;
    private Compte compte;
    private Suggestion selectedSuggestion;

    public Parcours(Compte c, int i)throws Exception {
            this.compte=c;
            this.selectedSuggestion = listSuggestion.get(i);
            if(c.getEmail()==null) throw new NoAccountException();
            if(listSuggestion.isEmpty()) throw new NoTronçonException();
            this.appelJDBC();
            this.InsertParcoursUtilisateur();
            this.InsertParcoursTronçon();
    }
        
    /**
     * 
     */
    @Override
    public void appelJDBC() {
        if(conn==null){
            ConnectJDBC connexion=new ConnectJDBC();
        }
        try {
            
            // Execution des requetes et affichage des résultats
            String SQL="INSERT INTO Parcours (id_parcours,dateheure_départ) "
                        + "VALUES (indParcours.nextval,TO_DATE(?,'HH24:MI YYYY-MM-DD')) ";

             // Creation de la requete
            PreparedStatement stmt=conn.prepareStatement(SQL,Statement.RETURN_GENERATED_KEYS);
            //String dateheure=dateDépart;

            stmt.setString(1, selectedSuggestion.getHeureDépart());
  

            // execute the preparedstatement
            stmt.execute();
            
                String STMT="SELECT indParcours.currval from Dual";
               // Creation de la requete
                Statement stmt1 =conn.createStatement();
                

               // Execution de la requete
               ResultSet rset1 = stmt1.executeQuery(STMT);

               // Affichage du resultat
               System.out.println("Results:");
               while (rset1.next()) {
                   this.id_parcours=rset1.getInt(1);
               }
              // System.out.println();
                        
            // execute the preparedstatement
            stmt1.close();
            rset1.close();
            stmt.close();
            conn.commit();
            
        } catch (SQLException e) {
            System.err.println("[InsertParcours]Parcours n'est pas enregistré!");
            e.printStackTrace();
        }
 
    }
    

    public void InsertParcoursUtilisateur() {
        if(conn==null){
            ConnectJDBC connexion=new ConnectJDBC();
        }
        try {
            // Execution des requetes et affichage des résultats
            String SQL="INSERT INTO UtilisateurParcours "
                        + "(id_parcours,email)"
                        + "VALUES (?,?) ";

            PreparedStatement stmt = conn.prepareStatement(SQL);

            stmt.setInt(1, this.id_parcours);
            stmt.setString(2, compte.getEmail());

            // execute the preparedstatement
            stmt.execute();
            stmt.close();
            conn.commit();
            
        } catch (SQLException e) {
            System.err.println("[InsertParcoursUtilisateur]Relation ParcoursUtilisateur n'est pas enregistré!");
            e.printStackTrace();
        }

    }
    
        public void InsertParcoursTronçon(){
           if(conn==null){
            ConnectJDBC connexion=new ConnectJDBC();
        }
        try {
            
            // Execution des requetes et affichage des résultats
            String SQL="INSERT INTO ParcoursTronçon "
                        + "(id_parcours,id_trajet,id_tronçon,etat_tronçon_passenger)"
                        + "VALUES (?,?,?,?)";

            PreparedStatement stmt = conn.prepareStatement(SQL);

            if (selectedSuggestion.isWithStop()) {
                for(int i = selectedSuggestion.sa.getIdTronçonStart(); i<=selectedSuggestion.sa.getIdTronçonEnd(); i++) {
                    stmt.setInt(1, this.id_parcours);
                    stmt.setInt(2, this.selectedSuggestion.sa.getIdTrajet() );
                    stmt.setInt(3, i);
                    stmt.setString(4, "EnAttente");
                    stmt.addBatch();
                }
                for(int i = selectedSuggestion.sb.getIdTronçonStart(); i<=selectedSuggestion.sb.getIdTronçonEnd(); i++) {
                    stmt.setInt(1, this.id_parcours);
                    stmt.setInt(2, this.selectedSuggestion.sb.getIdTrajet() );
                    stmt.setInt(3, i);
                    stmt.setString(4, "EnAttente");
                    stmt.addBatch();
                }

            } else {
                for(int i = selectedSuggestion.getIdTronçonStart(); i<=selectedSuggestion.getIdTronçonEnd(); i++) {
                    stmt.setInt(1, this.id_parcours);
                    stmt.setInt(2, this.selectedSuggestion.getIdTrajet() );
                    stmt.setInt(3, i);
                    stmt.setString(4, "EnAttente");
                    stmt.addBatch();
                }
            }

            stmt.executeBatch();
            // execute the preparedstatement
           
            stmt.close();
            conn.commit();
            
        } catch (SQLException e) {
            System.err.println("[InsertParcoursUtilisateur]Relation ParcoursUtilisateur n'est pas enregistré!");
            e.printStackTrace();
        }

        
         
     }
        
        public String toString(){
            return "Parcours choisi=>"+id_parcours+"\n"
                    + selectedSuggestion.toString();
                    
        }
        public int getId_parcours() {
        	return id_parcours;
        }
        
}
