//Équipe 58 en Teide
import gui.GUISimulator;
import java.awt.Color ;

public class TestBallsSimulator {
 public static void main ( String [] args ) {
   int window_sizex=720;
   int window_sizey=725;
   int nb_ball=3;
   Balls balles=new Balls(nb_ball);
   GUISimulator gui=new GUISimulator (window_sizex ,window_sizey, Color . WHITE );
   gui.setSimulable(new BallsSimulator(balles,gui)) ;
 }
}
