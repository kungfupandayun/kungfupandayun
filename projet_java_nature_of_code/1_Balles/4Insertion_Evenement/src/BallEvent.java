//Équipe 58 en Teide
import java.awt.Color;
import gui.Oval;
import gui.GUISimulator ;

public class BallEvent extends Event {
  public Balls balles;
  public GUISimulator gui;

  public BallEvent(long date, Balls balles,GUISimulator gui){
    super(date);
    this.balles=balles;
    this.gui = gui;
  }
  public void execute(){
    gui.reset();
    balles.translate();
    System.out.println(balles.toString());
    for(Point p: balles.balls){
      gui.addGraphicalElement(new Oval(p.x, p.y, Color.BLUE, Color.BLUE, 10));
    }
  }

  public void reInit(){
    gui.reset();
    balles.reInit();
    System.out.println(balles.toString());
    for(Point p: balles.balls)
      gui.addGraphicalElement(new Oval(p.x, p.y, Color.decode("#1f77b4"), Color.decode("#1f77b4"), 10));
  }
}
