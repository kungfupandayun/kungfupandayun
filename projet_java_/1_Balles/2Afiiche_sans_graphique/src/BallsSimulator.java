//Équipe 58 en Teide
import java.awt.Color;
import gui.Oval;
import gui.GUISimulator ;

public class BallsSimulator implements gui.Simulable{

    private Balls balles;
    private GUISimulator gui;

    public BallsSimulator(Balls balles,GUISimulator gui){
      this.balles=balles;
      this.gui=gui;
    }

    @Override
    public void next(){
      gui.reset();
      balles.translate();
      System.out.println(balles.toString());
    }

    @Override
    public void restart(){
      gui.reset();
      balles.reInit();
      System.out.println(balles.toString());
    }


}
