package Modele;

import static JDBC.ConnectJDBC.conn;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import JDBC.ConnectJDBC;
import JDBC.JDBC;

public class ConfirmerDescente implements JDBC{
	private String id_tronçon;
	private String id_trajet;
	private String id_parcours;
	public ConfirmerDescente(String id_tronçon,String id_trajet,String id_parcours) {
		this.id_tronçon=id_tronçon;
		this.id_trajet=id_trajet;
		this.id_parcours=id_parcours;
		this.appelJDBC();

	}
	
	
	@Override
    public void appelJDBC() {
        if(conn==null){
            ConnectJDBC c=new ConnectJDBC();
        }
        try {
                // Execution des requetes et affichage des résultats
                String SQL="UPDATE ParcoursTronçon set etat_tronçon_passenger= 'Descente' "
                            + "WHERE id_parcours=? AND id_tronçon=? AND id_trajet=? ";

                PreparedStatement stmt = conn.prepareStatement(SQL);
                stmt.setString(1,id_parcours);
                stmt.setString(2,id_tronçon);
                stmt.setString(3,id_trajet);


                // execute the preparedstatement
                stmt.execute();


                // Fermeture 
     
                stmt.close();
                conn.commit();

        } catch (SQLException e) {
                System.err.println("[ConfirmerDescente]La confirmation de descente du tronçon n'est pas enregistrée");
                e.printStackTrace();
        }
	}
}
