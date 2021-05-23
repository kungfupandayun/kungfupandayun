//Équipe 58 en Teide
import java.util.LinkedList;
import java.awt.Color;
import gui.Oval;
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


  public void next(Balls balles,GUISimulator gui){
    System.out.println("Next...Current date : "+ currentDate);
    if((currentDate%listeEvent.get(0).getDate())==0)listeEvent.get(0).execute();
    currentDate++;
  }
  public boolean isFinished(){
    if(listeEvent.size()==0) return true ;
    else return false ;
  }

  public void restart(Balls balles,GUISimulator gui){
    currentDate=0;
    System.out.println("Next...Current date : "+ currentDate);
    listeEvent.get(0).reInit();
    currentDate++;
  }
}
