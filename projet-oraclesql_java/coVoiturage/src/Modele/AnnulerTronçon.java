package Modele;

import static JDBC.ConnectJDBC.conn;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import JDBC.ConnectJDBC;
import JDBC.JDBC;

public class AnnulerTronçon implements JDBC{
	private String id_tronçon;
	private String id_trajet;
	public AnnulerTronçon(String id_tronçon, String id_trajet) {
		this.id_tronçon=id_tronçon;
		this.id_trajet=id_trajet;
		this.appelJDBC();
	}
	
	
	@Override
    public void appelJDBC() {
        if(conn==null){
            ConnectJDBC c=new ConnectJDBC();
        }
        try {
                // Execution des requetes et affichage des résultats
                String SQL="Delete from Tronçon "
                            + "WHERE id_tronçon=? AND id_trajet=? ";

                PreparedStatement stmt = conn.prepareStatement(SQL);

                stmt.setInt(1,Integer.parseInt(id_tronçon));
                stmt.setInt(2,Integer.parseInt(id_trajet));


                // execute the preparedstatement
                stmt.execute();


                // Fermeture 
     
                stmt.close();
                conn.commit();

        } catch (SQLException e) {
                System.err.println("[ConfirmerArrivée]La confirmation de l'arrivée du tronçon n'est pas enregistrée");
                e.printStackTrace();
        }
	}
}
