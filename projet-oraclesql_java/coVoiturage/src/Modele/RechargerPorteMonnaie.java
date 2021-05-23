package Modele;

import static JDBC.ConnectJDBC.conn;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import JDBC.ConnectJDBC;
import JDBC.JDBC;

public class RechargerPorteMonnaie implements JDBC{
	
	private Compte compte;
	private double montant;
	
	public RechargerPorteMonnaie(Compte compte , double montant) {
		this.compte=compte;
		this.montant=montant;
		this.appelJDBC();
	}
	
	
    @Override
    public void appelJDBC() {
        if(conn==null){
            ConnectJDBC c=new ConnectJDBC();
        }
        try {
            String SQL="UPDATE Utilisateur set solde=? "
                    + "WHERE email=? ";

            // Execution des requetes et affichage des résultats
            PreparedStatement stmt = conn.prepareStatement(SQL);                                
            stmt.setDouble(1, compte.getSolde()+montant);
            stmt.setString(2, compte.getEmail());

            // execute the preparedstatement
            stmt.execute();
            stmt.close();
            conn.commit();
        } catch (SQLException e) {
            System.err.println("[RechargerPorteMonnaie]Le rechargement du porte-monnaie n'est pas enregistré!");
            e.printStackTrace();
        }
    
    }
	
}
