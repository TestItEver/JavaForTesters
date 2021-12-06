package julijak.homework;

import jdk.nashorn.internal.objects.NativeString;

import java.util.HashMap;
import java.util.HashSet;

public class Calculate {
   public static void main(String[] args) {
      HashSet<Character> s = new HashSet<>();
      s.add('a');
      s.add('d');
      s.add('f');
      System.out.println(s);

      s.add('a');
      s.add('e');
      s.add('e');
      s.remove('a');
      s.remove('c');

      System.out.println(s);

      HashMap<String, String> myMap = new HashMap<>();
      myMap.put("Detektive", "Duke");
      System.out.println(myMap);
      myMap.put("Detektive", "Duke");
      System.out.println(myMap);
      System.out.println(myMap.size());

      System.out.println(rekursion("ALLA"));

      Point p1 = new Point(-1,-1);
      Point p2 = new Point(2, 2);

      System.out.println("Distance calculated with function is: " + distance(p1,p2));
      System.out.println("Distance calculated with class method is: " + p1.distance(p2));

      int[] arr = new int[5];
      arr[0] = 1;
      arr[1] = 2;
      arr[2] = 2;
      arr[3] = 33;
      arr[4] = 1;

      for (int i : arr){
         int anz = 0;
         for (int j : arr){
            if (i == j) {
               anz++;
            }
         }
         if (anz == 1) {
            System.out.println(i);
         }
      }

   }

   //Calculation of the distance between two points
   public static double distance(Point p1, Point p2){
      double x;
      double y;
      x = Math.abs(p1.x - p2.x);
      y = Math.abs(p1.y - p2.y);
      return Math.sqrt(x * x + y * y);
   }

   private static boolean rekursion(String wort){
      if (wort.length()<=1) {
         return true;
      }
      if (wort.charAt(0) == wort.charAt(wort.length()-1)) {
         return rekursion(wort.substring(1, wort.length()-1));
      }
      return false;
   }
}

