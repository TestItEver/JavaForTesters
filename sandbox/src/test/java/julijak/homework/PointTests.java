package julijak.homework;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

   @Test
   public void testDistanceEqualsNull() {
      Point p1 = new Point(10, 10);
      Point p2 = new Point(10, 10);
      Assert.assertEquals(p1.distance(p2), 0.0);
   }

   @Test
   public void testFirstQuadrant() {
      Point p1 = new Point(0, 0);
      Point p2 = new Point(3, 4);
      Assert.assertEquals(p1.distance(p2), 5.0);
   }

   @Test
   public void testSecondQuadrant() {
      Point p1 = new Point(-1, 0);
      Point p2 = new Point(-1, 1);
      Assert.assertEquals(p1.distance(p2), Math.sqrt(1));
   }

   @Test
   public void testThirdQuadrant() {
      Point p1 = new Point(-1, -1);
      Point p2 = new Point(-2, -2);
      Assert.assertEquals(p1.distance(p2), Math.sqrt(2));
   }

   @Test
   public void testFourthQuadrant() {
      Point p1 = new Point(2, -1);
      Point p2 = new Point(1, -2);
      Assert.assertEquals(p1.distance(p2), Math.sqrt(2));
   }

   @Test
   public void testOppositeQuadrants() {
      Point p1 = new Point(1,3);
      Point p2 = new Point(-2,-2);
      Assert.assertEquals(p1.distance(p2),Math.sqrt(9+25));
   }
}
