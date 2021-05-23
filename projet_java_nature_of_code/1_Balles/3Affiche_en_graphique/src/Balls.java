//Équipe 58 en Teide
import java.util.LinkedList;
import java.util.Random;

class Balls{

  public LinkedList<Point> balls;
  int window_sizex=720;
  int window_sizey=725;
  static private int nb;
  static private int translate_pas=50;


  Random random=new Random();

  public Balls(int nb_ball) {
      balls = new LinkedList<Point>();
      this.nb=nb_ball;
      while(nb_ball>0){
        ajoutePoint();
        nb_ball--;
      }

  }

  private void ajoutePoint() {
      Point p=new Point(random.	nextInt(window_sizex),random.	nextInt(window_sizey));
      balls.add(p);
      p.translatex=random.nextInt(translate_pas);
      p.translatey=random.nextInt(translate_pas);
  }

  private void bounce(Point b){
    if(b.x>window_sizex||b.x<0) b.translatex=b.translatex*-1;
    if(b.y>window_sizey||b.y<0) b.translatey=b.translatey*-1;
  }

  public void translate() {
    for (Point b : balls){
      b.translate(b.translatex,b.translatey);
      bounce(b);
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
