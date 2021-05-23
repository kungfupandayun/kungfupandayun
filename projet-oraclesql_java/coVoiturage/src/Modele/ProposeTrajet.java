package Modele;

import Exceptions.*;
import JDBC.*;
import static JDBC.ConnectJDBC.conn;
import java.util.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import static java.util.Calendar.MINUTE;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  Classe à appeler pour propose un trajet.
 */
public class ProposeTrajet implements JDBC{
	private final Compte compte ;
	private final Vehicule vehicule;
	private final Trajet trajet;
        private final ArrayList<Tronçon> listTronçons;
        
        
    public ProposeTrajet(Compte compte , Vehicule vehicule, Trajet trajet ) throws Exception{
            //Affectuation aux variables privées
            this.compte=compte;
            this.vehicule=vehicule;
            this.trajet=trajet;
            this.listTronçons=trajet.getTronçons();
            
            /*
              verifier compte.email, vehicule.immatriculation et tronçon n'est pas null
              avant de faire une proposition
              sinon le trajet est abandoné
            */
            if(compte.getEmail()==null) throw new NoAccountException();
            if(!VerifieContraintVehicule()) throw new NoVehiculeException();
            if(listTronçons.isEmpty()) throw new NoTronçonException();
            if(! VerifieContraintTemps()) throw new TimeProblemeException();
            
            //inserer le trajet à la BD, ainsi tous les tronçons et la relation avec compte et vehicule  
            trajet.AddTrajetTronçon();
            this.appelJDBC();
            System.out.println("La proposition de trajet est reussit!");

    
	}
    /**
     * Inserer la relation entre Vehicule, Utilisateur et Trajet dans la BD.
     */ 
    @Override
    public void appelJDBC() {
        if(conn==null){
            ConnectJDBC c=new ConnectJDBC();
        }
        try {
                // Execution des requetes et affichage des résultats
                String SQL="INSERT INTO ConducteurVehiculeTrajet "
                            + "(email,immatriculation,id_trajet) "
                            + "VALUES (?,?,?)";

                PreparedStatement stmt = conn.prepareStatement(SQL);

                stmt.setString(1,compte.getEmail());
                stmt.setString(2,vehicule.getImmatriculation());
                stmt.setInt(3, trajet.getId_trajet());

                // execute the preparedstatement
                stmt.execute();


                // Fermeture 
     
                stmt.close();
                conn.commit();

        } catch (SQLException e) {
                System.err.println("[ProposeTrajet]La proposition de trajet n'est pas enregistrée");
                e.printStackTrace();
        }
    }

    
    /**
     * Verifier si l'heure proposé est passé
     * @return true time valid
     */
    public boolean VerifieContraintTemps(){
            String dateheure=trajet.getDate()+" "+trajet.getHeure();
            Calendar cal = Calendar.getInstance();
            Calendar cal1 = Calendar.getInstance();
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            java.util.Date strDate = null;
            java.util.Date currDate =cal1.getTime();
            String curr_date=ft.format(currDate);
        try {
            strDate = ft.parse(dateheure);
            currDate=ft.parse(curr_date);
        } catch (ParseException ex) {
            Logger.getLogger(Suggestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        cal.setTime(strDate);
        cal1.setTime(currDate);

        if(cal.compareTo(cal1)<=0){
            return false;
        }else{
            return true;
        }
    }
    
    /**
    * Verifier si le vehicule proposé est en disposition dans ce créneau
    * @return true valide vehicule
 *  */    
    public boolean VerifieContraintVehicule(){
        boolean valide=true;
        if(conn==null){
            ConnectJDBC c=new ConnectJDBC();
        }
        try {
                // Execution des requetes et affichage des résultats
                String SQL="SELECT temps_trajet,Trajet.id_trajet  \n" +
                            "FROM ConducteurVehiculeTrajet  \n" +
                            "FULL OUTER JOIN Trajet ON ConducteurVehiculeTrajet.id_trajet=Trajet.id_trajet \n" +
                            "WHERE immatriculation=? ";
                
                String SQL1="SELECT SUM(duree_tronçon) \n" +
                            "FROM Tronçon \n" +
                            "WHERE id_trajet=? ";

                PreparedStatement stmt = conn.prepareStatement(SQL);

                stmt.setString(1,vehicule.getImmatriculation());


                // execute the preparedstatement
               ResultSet results = stmt.executeQuery();
                
               while(results.next()&& valide){ 
                    //récuperer la durée du possible trajet
                    PreparedStatement stmt1 = conn.prepareStatement(SQL1);
                    stmt1.setInt(1,results.getInt(2));
                    ResultSet results1 = stmt1.executeQuery();
                    int dureeTrajet=0;
                    if(results1.next()){
                        dureeTrajet=results1.getInt(1);
                        
                    }
                    

                   
                     Calendar notreDepart=getCalendar( trajet.getDate()+" "+trajet.getHeure(), 0);
                     Calendar notreArrivee = getCalendar(trajet.getDate()+" "+trajet.getHeure(), trajet.getDureeTrajet());
                     Calendar heurePossibleDépart= getCalendar(results.getTimestamp(1).toString().replace(".0",""), 0);
                     Calendar heurePossibleArrivé= getCalendar(results.getTimestamp(1).toString().replace(".0",""), dureeTrajet);
                      

                     
                    // Si le départ qu'on a proposé est entre le créneau déjà réservée , affiche problème
                    if(((notreDepart.compareTo(heurePossibleDépart)>=0) && 
                            (notreDepart.compareTo(heurePossibleArrivé)<=0 )) ||
                               ((notreArrivee.compareTo(heurePossibleDépart)>=0) && 
                                    (notreArrivee.compareTo(heurePossibleArrivé)<=0 )))
                    {
                        valide=false;
                         //System.out.println("hello");
                    }
                   
               }

                // Fermeture 
     
                stmt.close();
                conn.commit();

        } catch (SQLException e) {
                System.err.println("[ProposeTrajet]La proposition de trajet n'est pas enregistrée");
                e.printStackTrace();
        }
        return valide;
    } 
    
    
    /**
     * Transformer la date et  l'heure extrait de SQL au format Calendar
     * @param dateheure
     * @param dureeTrajet
     * @return calendar
     */
     public Calendar getCalendar(String dateheure, int dureeTrajet){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        java.util.Date strDate = null;
        try {
            strDate = ft.parse(dateheure);
        } catch (ParseException ex) {
            Logger.getLogger(Suggestion.class.getName()).log(Level.SEVERE, null, ex);
        }    
        cal.setTime(strDate);
        cal.add(MINUTE,dureeTrajet);
        //System.out.println(cal.toString());
        return cal;
    }

}
