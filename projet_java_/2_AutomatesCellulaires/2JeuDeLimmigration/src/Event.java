//Équipe 58 en Teide
import java.util.LinkedList;
import java.awt.Color;
import gui.Oval;
import gui.GUISimulator ;
public abstract class Event {
  private long date ;
  public Event(long date){
    this.date=date;
  }
  public long getDate(){
    return date;
  }
  public abstract void execute();
}
