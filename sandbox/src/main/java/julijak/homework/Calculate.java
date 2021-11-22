package julijak.homework;

public class Calculate {
   public static void main(String[] args) {
      Point p1 = new Point(-1,-1);
      Point p2 = new Point(2, 2);

      System.out.println("Distance calculated with function is: " + distance(p1,p2));
      System.out.println("Distance calculated with class method is: " + p1.distance(p2));

   }

   //Calculation of the distance between two points
   public static double distance(Point p1, Point p2){
      double x;
      double y;
      x = Math.abs(p1.x - p2.x);
      y = Math.abs(p1.y - p2.y);
      return Math.sqrt(x * x + y * y);
   }
}
