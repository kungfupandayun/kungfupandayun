//Équipe 58 en Teide
import java.awt.Color;
import gui.Oval;
import gui.GUISimulator ;

public class BallsSimulator implements gui.Simulable{

    private Balls balles;
    private GUISimulator gui;
    private EventManager manager;

    public BallsSimulator(Balls balles,GUISimulator gui,EventManager manager){
      this.balles=balles;
      this.gui=gui;
      this.manager=manager;
    }

    @Override
    public void next(){
      manager.next(balles,gui);
    }

    @Override
    public void restart(){
      manager.restart(balles,gui);
    }


}
