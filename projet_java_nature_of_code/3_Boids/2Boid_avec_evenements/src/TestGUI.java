//Équipe 58 en Teide
import java.awt.Color ;
import gui.GUISimulator;
import java.util.LinkedList;

//POUR GERER DIFFERENT COMPORTEMENT DE BOID, VEUILLEZ CHANGER DANS LA FONCTION BEHAVIOR DANS BOIDS.JAVA

public class TestGUI {
  public static void main (String[] args) {

    //INITIALISER LA TAILLE DE FENETRE ET NBRE DE BALL
    int nb_boid=20;
    int size=10;
    int window_sizex=720;
    int window_sizey=725;
    int decalage_tmp=3;

    //DECLARATION DU TYPE
    Boids boids=new Boids(nb_boid,size,Color.RED);
    GUISimulator gui=new GUISimulator (window_sizex ,window_sizey, Color.WHITE );

    //Creation dun event EventManager
    EventManager manager =  new EventManager();
    manager.listeEvent=new LinkedList<Event>();
      //L'ajoute boid dans la liste d'evenement
      //le boid translate à chaque 3 "currentDate"
      manager.addEvent(new BoidsEvent(decalage_tmp,boids,gui));

     gui.setSimulable(new BoidsSimulator(boids,gui,manager)) ;
  }
}
