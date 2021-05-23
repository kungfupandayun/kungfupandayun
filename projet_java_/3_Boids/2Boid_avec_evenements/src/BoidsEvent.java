//Équipe 58 en Teide
import java.awt.Color;
import gui.Oval;
import gui.* ;

public class BoidsEvent extends Event {
  public Boids boids;
  public GUISimulator gui;

  public BoidsEvent(long date, Boids boids,GUISimulator gui){
    super(date);
    this.boids=boids;
    this.gui = gui;
  }
  public void execute(){
    gui.reset();
    boids.translate();
    System.out.println(boids.toString());
    for(Vecteur p: boids.boids){
      gui.addGraphicalElement(new Oval(p.x, p.y, Color.BLUE, Color.BLUE, 10));
    }
  }

  public void reInit(){
    gui.reset();
    boids.reInit();
    System.out.println(boids.toString());
    for(Vecteur p: boids.boids)
      gui.addGraphicalElement(new Oval(p.x, p.y, Color.decode("#1f77b4"), Color.decode("#1f77b4"), 10));
  }
}
