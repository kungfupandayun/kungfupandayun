//Équipe 58 en Teide
import java.awt.Color ;
import gui.GUISimulator;
import java.util.LinkedList;

//POUR GERER DIFFERENT COMPORTEMENT DE BOID, VEUILLEZ CHANGER DANS LA FONCTION BEHAVIOR DANS BOIDS.JAVA


public class TestGUI {
  public static void main (String[] args) {

    //INITIALISER LA TAILLE DE FENETRE ET NBRE DE BALL
    int window_sizex=720;
    int window_sizey=725;

    //DECLARATION DU TYPE
    //Boids(int nb_boid,int size)
    Boids lions=new Lions(3,30,Color.RED);
    Boids loups=new Loups(10,20,Color.BLUE);
    Boids zebre=new Zebre(50,10,Color.GREEN);
    GUISimulator gui=new GUISimulator (window_sizex ,window_sizey, Color.WHITE );

    //Creation dun event EventManager
    EventManager manager=new EventManager(gui);
    manager.listeEvent=new LinkedList<Event>();


      //L'ajoute boid dans la liste d'evenement
      //le boid translate à chaque 3 "currentDate"
     manager.addEvent(new BoidsEvent(1,lions,gui));
     manager.addEvent(new BoidsEvent(3,loups,gui));
     manager.addEvent(new BoidsEvent(5,zebre,gui));

     gui.setSimulable(manager) ;
  }
}
