package Modele;

import JDBC.*;
import static JDBC.ConnectJDBC.conn;
import java.sql.*;
import Exceptions.NoAccountException;

/**
 * Création d'un compte ou login.
 */
public class Compte implements JDBC{
    private  String email;
    private  String nom;
    private  String prenom;
    private  String ville;
    private  String MdP;
    private  double solde;
    
   
    
    public Compte(String email,String nom,String prenom,String ville, 
                                            String MdP,double solde){
        
        this.email=email;
        this.nom=nom;
        this.prenom=prenom;
        this.ville=ville;
        this.MdP=MdP;
        this.solde=solde;
        this.appelJDBC();
    }
    
    public Compte(String email,String MdP) throws NoAccountException
    {
     
        this.email=email;
        this.MdP=MdP;
           this.login();
 
    }

 
    

    
    public void login () throws NoAccountException{
        if(conn==null){
            ConnectJDBC c=new ConnectJDBC();
        }
        try {
            
            // Creation de la requete
            PreparedStatement stmt = conn.prepareStatement("SELECT * from Utilisateur "
                                                        + "WHERE Email LIKE ? and MdP=?"); 
            stmt.setString(1, email);       
            stmt.setString(2, MdP);             

            // Affichage du résultat
            ResultSet rset = stmt.executeQuery();

            if(rset.next()){
                //only one element
                this.setNom(rset.getString (2)); 
                this.setPrenom(rset.getString(3)); 
                this.setVille(rset.getString(4));
                this.setSolde(rset.getFloat(6));       
            }else{
                System.err.println("Login echec");
                throw new NoAccountException();
            }

            // Fermeture 
            rset.close();
            stmt.close();
            conn.commit();
            
          } catch (SQLException e) {
            this.email=null;
            System.err.println("[SelectCompte]Login n'est pas enregistré!");
            //e.printStackTrace();
        
        }
    }
    
    @Override
    public String toString(){
    return ("email="+getEmail()
           +"\nnom="+getNom()
           +"\nprenom="+getPrenom()
           +"\nville="+getVille()
           +"\nMdP="+getMdP()
           +"\nsolde="+getSolde());
    }
    
   /**
     * Inserer un compte dans BD.
     */
    @Override
    public void appelJDBC() {
        if(conn==null){
            ConnectJDBC c=new ConnectJDBC();
        }
        try {
            String SQL="insert into utilisateur(email,nom,prenom,ville,mdp,solde) values (?,?,?,?,?,?)";
            //System.out.print(SQL);
            // Execution des requetes et affichage des résultats
            PreparedStatement pstmt = conn.prepareStatement(SQL);                                
            pstmt.setString(1, getEmail());
            pstmt.setString(2, getNom());
            pstmt.setString(3, getPrenom());
            pstmt.setString(4, getVille());
            pstmt.setString(5, getMdP());
            pstmt.setDouble(6, getSolde());

            // execute the preparedstatement
            pstmt.execute();
            pstmt.close();
            conn.commit();
        } catch (SQLException e) {
            this.email=null;
            System.err.println("[InsertCompte]Compte n'est pas enregistré!");
            e.printStackTrace();
        }
    
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @return the ville
     */
    public String getVille() {
        return ville;
    }

    /**
     * @return the MdP
     */
    public String getMdP() {
        return MdP;
    }

    /**
     * @return the solde
     */
    public double getSolde() {
        return solde;
    }

    /**
     * @param aEmail the email to set
     */
    public void setEmail(String aEmail) {
        email = aEmail;
    }

    /**
     * @param aNom the nom to set
     */
    public void setNom(String aNom) {
        nom = aNom;
    }

    /**
     * @param aPrenom the prenom to set
     */
    public void setPrenom(String aPrenom) {
        prenom = aPrenom;
    }

    /**
     * @param aVille the ville to set
     */
    public void setVille(String aVille) {
        ville = aVille;
    }

    /**
     * @param aMdP the MdP to set
     */
    public void setMdP(String aMdP) {
        MdP = aMdP;
    }

    /**
     * @param aSolde the solde to set
     */
    public void setSolde(double aSolde) {
        solde = aSolde;
    }

    
    
}
