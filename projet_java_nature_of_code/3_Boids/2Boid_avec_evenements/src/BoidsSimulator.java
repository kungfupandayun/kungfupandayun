//Équipe 58 en Teide
import java.awt.Color;
import java.util.LinkedList;
import gui.*;
import gui.Oval;
import gui.GUISimulator ;

public class BoidsSimulator implements gui.Simulable{

      private Boids boids;
      private GUISimulator gui;
      private EventManager manager;

      public BoidsSimulator(Boids boids,GUISimulator gui,EventManager manager){
        this.boids=boids;
        this.gui=gui;
        this.manager=manager;
      }

      @Override
      public void next(){
        manager.next(boids,gui);
      }

      @Override
      public void restart(){
        manager.restart(boids,gui);
      }

}
