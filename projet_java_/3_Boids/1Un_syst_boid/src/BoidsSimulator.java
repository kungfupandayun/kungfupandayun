//Équipe 58 en Teide
import java.awt.Color;
import java.util.LinkedList;
import gui.*;
import gui.Oval;
import gui.GUISimulator ;

public class BoidsSimulator implements gui.Simulable{
    public GUISimulator gui;
    public Boids boides;

    public BoidsSimulator(GUISimulator gui,Boids boids){
      this.gui=gui;
      this.boides=boids;
    }

    @Override
    public void next(){
      gui.reset();
      boides.translate();
      System.out.println(boides.toString());
      for(Vecteur p: boides.boids){
        gui.addGraphicalElement(new Oval(p.x, p.y,p.couleur,p.couleur, p.size));
      }
    }

    @Override
    public void restart(){
      gui.reset();
      boides.reInit();
      System.out.println(boides.toString());
      for(Vecteur p: boides.boids){
        gui.addGraphicalElement(new Oval(p.x, p.y, Color.decode("#1f77b4"), Color.decode("#1f77b4"),p.size));
      }
    }
}
