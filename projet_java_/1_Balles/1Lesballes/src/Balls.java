//Équipe 58 en Teide
import java.util.LinkedList;

public class Balls{

  public LinkedList<Point> balls;

  public Balls() {
      balls=new LinkedList<Point>();
  }

  public void ajoutePoint(Point x){
    balls.add(x);
  }


  public void translate(int translatex, int translatey) {
      for (Point b : balls){
      b.translate(translatex,translatey);
    }
  }

  public void reInit(){
    for (Point b : balls)
    b.setLocation(b.original_position(b));
  }


  @Override
  public String toString(){
    String allballs="voici les points:\n";
    for (Point b : balls)
    {
      allballs+="(";
      allballs+=b.toString();
      allballs+=")";
      allballs+="\n";
    }
    return allballs;
  }
}
