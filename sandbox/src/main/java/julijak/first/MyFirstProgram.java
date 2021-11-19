package julijak.first;

public class MyFirstProgram {

   public static void main(String[] args) {
      hello("world");
      hello("Alex");

      double l = 5;
      System.out.println("Area of the square with side length " + l + " = " + area(l));

      double a = 3;
      double b = 7;
      System.out.println("Area of the rectangle with side lengths " + a + " and " + b + " = " + area(a,b));
   }

   public static void hello(String w){
      System.out.println("Hello, " + w + "!");
   }

   public static double area(double len){
      return len * len;
   }

   public static double area(double a, double b){
      return a * b;
   }

}