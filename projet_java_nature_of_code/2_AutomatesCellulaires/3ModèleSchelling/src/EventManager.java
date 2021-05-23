//Équipe 58 en Teide
import java.util.LinkedList;
import java.awt.Color;
import gui.Rectangle;
import gui.GUISimulator ;

public class EventManager {
  public long currentDate;
  public LinkedList<Event> listeEvent = new LinkedList<Event>();
  public EventManager(){
    currentDate=0;
  }
  public void addEvent(Event e){//Evenement extends Event
    listeEvent.add(e);
  }
  public void next(Grille grille,GUISimulator gui){
    System.out.println("Next...Current date : "+ currentDate);
    listeEvent.get(0).execute();
    listeEvent.remove(0);
    currentDate++;
    this.addEvent(new GrilleEvent(currentDate,grille,gui));
  }
  public boolean isFinished(){
    if(listeEvent.size()==0) return true ;
    else return false ;
  }
  public void restart(){};
}
