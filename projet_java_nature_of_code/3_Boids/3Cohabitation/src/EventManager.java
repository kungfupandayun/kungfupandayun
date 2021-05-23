//Équipe 58 en Teide
import java.util.LinkedList;
import java.awt.Color;
import gui.Oval;
import gui.GUISimulator ;

public class EventManager implements gui.Simulable{

  public long currentDate;
  public LinkedList<Event> listeEvent = new LinkedList<Event>();
  private GUISimulator gui;


  public EventManager(GUISimulator gui){
    currentDate=0;
    this.gui=gui;
  }


  public void addEvent(Event e){//Evenement extends Event
    listeEvent.add(e);
  }


  public void next(){
    System.out.println("Next...Current date : "+ currentDate);
    gui.reset();
    for(Event x:listeEvent){
      if((currentDate%x.getDate())==0) x.execute();
      /*pour ne pas gui.reset qui efface l'écran*/
      else x.constant();
    }
    currentDate++;
  }


  public boolean isFinished(){
    if(listeEvent.size()==0) return true ;
    else return false ;
  }

  public void restart(){
    gui.reset();
    currentDate=0;
    System.out.println("Next...Current date : "+ currentDate);
    for(Event x:listeEvent)
       x.reInit();
    currentDate++;
  }
}
