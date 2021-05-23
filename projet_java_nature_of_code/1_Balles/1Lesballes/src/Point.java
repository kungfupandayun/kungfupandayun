//Équipe 58 en Teide
class Point extends java.awt.Point{
  private int ori_x;
  private int ori_y;

  public Point(int x,int y){
    super(x,y);
    ori_x=x;
    ori_y=y;
  }

  Point original_position(Point p)
  {
    p.x=ori_x;
    p.y=ori_y;
    return p;
  }
  @Override
  public String toString() {
    return "(" + x + "," + y + ")";
  }
}
