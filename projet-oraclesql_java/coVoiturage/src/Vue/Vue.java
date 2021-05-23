package Vue;

import static JDBC.ConnectJDBC.conn;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 * La class Vue contient les instanciation de tous les fenetres qu'on a besoin.
 */
public class Vue extends JFrame {

    public FenetrePrincipale fenetrePrincipale;
    public FenetreInscription fenetreInscription;
    public FenetreIdentification fenetreIdentification;
    public FenetreSucces fenetreSucces;
    public FenetreEchec fenetreEchec;
    public FenetreAjoutVehicule fenetreAjoutVehicule;
    public FenetreProposeTrajet fenetreProposeTrajet;
    public FenetreChoisirVehicule fenetreChoisirVehicule;
    public FenetreRechargementSolde fenetreRechargementSolde;
    public FenetreTrajets fenetreTrajets;
    public FenetreSuiviTrajet fenetreSuiviTrajet;
    public FenetreTronçonsTrajet fenetreTronçonsTrajet;
    public FenetreParcours fenetreParcours;
    public FenetreTronçonsParcours fenetreTronçonsParcours;
    public FenetreSuiviParcours fenetreSuiviParcours;
    public FenetreRechercheParcours fenetreRechercheParcours;
    public FenetreSuccesTrajet fenetreSuccesTrajet;
    public FenetreSuccesParcours fenetreSuccesParcours;


    public Vue() {
        this.setTitle("IHM - Index");
        this.setSize(1000, 500);

        /* Place la fenetre au centre de l'écran */
        this.setLocationRelativeTo(null);

        /* Arrete le processus lors de la fermeture de la fenetre */
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.addWindowListener(new WindowAdapter() {

            //windowClosing METHOD WILL BE CALLED WHEN A JFRAME IS CLOSING  
            public void windowClosing(WindowEvent evt) {
                try {
                    System.out.println("Fin de Session");
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Vue.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }

        });

        /* Creation et configuration de la fenetre principale */
        fenetrePrincipale = new FenetrePrincipale();

        /* Creation et configuration de la fenetre inscription*/
        fenetreInscription = new FenetreInscription();

        /* Creation et configuration de la fenetre de succes */
        fenetreSucces = new FenetreSucces();

        /* Creation et configuration de la fenetre d'echec */
        fenetreEchec = new FenetreEchec();

        /* Creation et configuration fenetre ajout vehicule */
        fenetreAjoutVehicule = new FenetreAjoutVehicule();

        /* Creation et configuration de la fenetre iidentification*/
        fenetreIdentification = new FenetreIdentification();

        /* Creation et configuration fenetre de saisi des infos du trajet *//* Creation et configuration fenetre de saisi des infos du trajet */
        fenetreProposeTrajet = new FenetreProposeTrajet();
        
        /* Creation et configuration fenetre de choisir un véhicule */
        fenetreChoisirVehicule = new FenetreChoisirVehicule();
        
        /* Creation et configuration de la fenetre de rechargement de solde*/
        fenetreRechargementSolde= new FenetreRechargementSolde();
        
        /* Creation et configuration de la fenetre afficher les trajets enregistrés sous le compte*/
        fenetreTrajets= new FenetreTrajets();
        
        /* Creation et configuration de la fenetre choisir un tronçon pour un trajet*/
        fenetreSuiviTrajet=new FenetreSuiviTrajet();
        
        /* Creation et configuration de la fenetre choisir un état pour un tronçon*/
        fenetreTronçonsTrajet=new FenetreTronçonsTrajet();
        
        /* Creation et configuration de la fenetre afficher les parcours enregistrés sous le compte*/
        fenetreParcours=new FenetreParcours();
        
        /* Creation et configuration de la fenetre choisir un tronçon pour un parcours*/
        fenetreTronçonsParcours=new FenetreTronçonsParcours();
        
       /* Creation et configuration de la fenetre choisir un état pour un tronçon*/
        fenetreSuiviParcours=new FenetreSuiviParcours();
        
        /* Creation et configuration de la fenetre recherche parcours */
        fenetreRechercheParcours=new FenetreRechercheParcours();
        

        fenetreSuccesTrajet=new FenetreSuccesTrajet();
        		
        fenetreSuccesParcours= new FenetreSuccesParcours();
        this.setContentPane(fenetrePrincipale);
        this.setVisible(true);
    }

    public void setFenetreInscription() {
        this.setTitle("IHM - Inscription"); // change le titre de la fenetre
        this.setContentPane(fenetreInscription); // Remplace la fenetre principale par la fenetre d'inscription
        refreshPage();
    }

    public void setFenetreSucces() {
        this.setTitle("IHM - Succes"); // change le titre de la fenetre
        this.setContentPane(fenetreSucces); // Remplace la fenetre principale par la fenetre d'inscription
        refreshPage();
    }

    public void setFenetreSuccesTrajet() {
        this.setTitle("IHM - Succes"); 
        this.setContentPane(fenetreSuccesTrajet); 
        refreshPage();
    }
    
    public void setFenetreSuccesParcours() {
        this.setTitle("IHM - Succes"); 
        this.setContentPane(fenetreSuccesParcours); 
        refreshPage();
    }
    
    public void setFenetreEchec() {
        this.setTitle("IHM - Echec");
        this.setContentPane(fenetreEchec);
        refreshPage();
    }

    public void setFenetreAjoutVehicule() {
        this.setTitle("IHM - Saisi des informations du véhicules");
        this.setContentPane(fenetreAjoutVehicule);
        refreshPage();
    }

    public void setFenetreAjoutIdentification() {
        this.setTitle("IHM - Identification");
        this.setContentPane(fenetreIdentification);
        refreshPage();
    }

    public void setProposeTrajet() {
        this.setTitle("IHM - Proposition d'un trajet");
        this.setContentPane(fenetreProposeTrajet);
        refreshPage();
    }

    public void setFenetrePrincipale() {
        this.setTitle("IHM - Index");
        this.setContentPane(fenetrePrincipale);
        this.revalidate();
        this.repaint();
    }
    
        public void setFenetreRechercheParcours() {
        this.setTitle("IHM - Recherche Parcours.");
        this.setContentPane(fenetreRechercheParcours);
        this.revalidate();
        this.repaint();
    }

    public void setFenetreRechargementSolde() {
        this.setTitle("IHM - Rechargement de Solde");
        this.setContentPane(fenetreRechargementSolde);
        refreshPage();
    }
    
    public void setFenetreTrajets() {
        this.setTitle("IHM - Vos Trajets");
        this.setContentPane(fenetreTrajets);
        refreshPage();
    }
    
    public void setFenetreSuiviTrajet() {
        this.setTitle("IHM - Suivi Trajet");
        this.setContentPane(fenetreSuiviTrajet);
        refreshPage();
    }
    
    public void setFenetreTronçonsTrajet() {
        this.setTitle("IHM - Tronçons du Trajet");
        this.setContentPane(fenetreTronçonsTrajet);
        refreshPage();
    }
    
    public void setFenetreParcours() {
        this.setTitle("IHM - Vos Parcours");
        this.setContentPane(fenetreParcours);
        refreshPage();
    }
    
    public void setFenetreTronçonsParcours() {
        this.setTitle("IHM - Tronçons du Parcours");
        this.setContentPane(fenetreTronçonsParcours);
        refreshPage();
    }
    
    public void setFenetreSuiviParcours() {
        this.setTitle("IHM - Suivi Parcours");
        this.setContentPane(fenetreSuiviParcours);
        refreshPage();
    }
    

    public void refreshPage() {
        this.revalidate();
        this.repaint();
    }
    
      

}
