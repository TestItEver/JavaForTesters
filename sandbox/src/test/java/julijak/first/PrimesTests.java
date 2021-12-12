package julijak.first;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimesTests {

   @Test
   public void testPrimeTrue() {
      int n = 7; //Integer.MAX_VALUE;
      Assert.assertTrue(Primes.isPrime(n));
   }

   @Test
   public void testPrimeFalse() {
      int n = 188; // Integer.MAX_VALUE - 2;
      Assert.assertFalse(Primes.isPrime(n));
   }

   @Test
   public void testPrimeFalse2() {
      int n = 25;
      Assert.assertFalse(Primes.isPrime(n));
   }
}
