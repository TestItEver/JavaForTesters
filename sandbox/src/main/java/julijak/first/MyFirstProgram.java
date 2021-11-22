package julijak.first;

import org.w3c.dom.css.Rect;

public class MyFirstProgram {

   public static void main(String[] args) {
      hello("world");
      hello("Alex");

      Square s = new Square(5);
      //s.l = 5;
      System.out.println("Area of the square with side length " + s.l + " = " + s.area());

      Rectangle r = new Rectangle(3,7);
      //r.a = 3;
      //r.b = 7;
      System.out.println("Area of the rectangle with side lengths " + r.a + " and " + r.b + " = " + r.area());
   }

   public static void hello(String w){
      System.out.println("Hello, " + w + "!");
   }

   /*
   public static double area(Square s){
      return s.l * s.l;
   }

   public static double area(Rectangle r){
      return r.a * r.b;
   }

    */

}