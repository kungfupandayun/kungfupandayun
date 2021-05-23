//Équipe 58 en Teide
import java.awt.Color;
import gui.GUISimulator;

//POUR GERER DIFFERENT COMPORTEMENT DE BOID, VEUILLEZ CHANGER DANS LA FONCTION BEHAVIOR DANS BOIDS.JAVA


public class TestBoidsSimulator {
  public static void main (String[] args) {

    //INITIALISER LA TAILLE DE FENETRE ET NBRE DE BALL
    int nb_ball=30;
    int size=10;
    int window_sizex=720;
    int window_sizey=725;


    //DECLARATION DU TYPE
    Boids boids=new Boids(nb_ball,size,Color.RED);
    GUISimulator gui=new GUISimulator (window_sizex ,window_sizey, Color.WHITE );

    //PREPARER L'AFFICHAGE
    gui.setSimulable(new BoidsSimulator(gui,boids)) ;
  }
}
