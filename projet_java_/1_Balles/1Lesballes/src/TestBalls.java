//Équipe 58 en Teide
import java.util.LinkedList;

public class TestBalls{

  public static void main(String[] args){
      Balls balles=new Balls();
      balles.ajoutePoint(new Point(2,3));
      balles.ajoutePoint(new Point(3,4));
      balles.ajoutePoint(new Point(4,5));
      System.out.println(balles.toString());


      balles.translate(1,1);
      System.out.println(balles.toString());

      balles.reInit();
      System.out.println(balles.toString());
  }

}
