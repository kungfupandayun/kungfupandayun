//Équipe 58 en Teide
import java.awt.Color ;
import gui.*;


public class TestGUI {
  public static void main (String[] args) {

    //INITIALISER LA TAILLE DE FENETRE ET NBRE DE BALL
    int nb_ball=50;
    int window_sizex=700;
    int window_sizey=500;
    int delai_temps=5;

    //DECLARATION DU TYPE

    Balls balles=new Balls(nb_ball);
    GUISimulator gui=new GUISimulator (window_sizex ,window_sizey, Color.BLACK );
    EventManager manager=new EventManager();
    manager.addEvent(new BallEvent(delai_temps,balles,gui));
    //PREPARER L'AFFICHAGE
    gui.setSimulable(new BallsSimulator(balles,gui,manager)) ;
  }
}
