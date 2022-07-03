package hackerrank.make_candies;

import java.io.IOException;
import java.math.BigInteger;

class Result {

  /*
   * Complete the 'minimumPasses' function below.
   *
   * The function is expected to return a LONG_INTEGER.
   * The function accepts following parameters:
   * 1. LONG_INTEGER m: the starting number of machines
   * 2. LONG_INTEGER w: the starting number of workers
   * 3. LONG_INTEGER p: the cost of a new hire or a new machine
   * 4. LONG_INTEGER n: the number of candies to produce
   */

  public static long minimumPasses(long m, long w, long p, long n) {
    BigInteger machines = BigInteger.valueOf(m);
    BigInteger workers = BigInteger.valueOf(w);
    BigInteger target = BigInteger.valueOf(n);
    BigInteger price = BigInteger.valueOf(p);
    BigInteger candies = BigInteger.ZERO;
    BigInteger pathUntilNow = BigInteger.ZERO;
    BigInteger shortestDirectPath = BigInteger.valueOf(Long.MAX_VALUE);
    BigInteger TWO = BigInteger.valueOf(2);
    if (p>=n) {
      return divideAndCeil(target, machines.multiply(workers)).longValue();
    }
    while (candies.compareTo(target) < 0) {
      BigInteger steps = price.subtract(candies).divide(machines.multiply(workers));
      if (steps.signum() <= 0) {
        BigInteger resources = machines.add(workers).add(candies.divide(price));
        BigInteger half = divideAndCeil(resources, TWO);
        if (machines.compareTo(workers) > 0) {
          machines = machines.max(half);
          workers = resources.subtract(machines);
        } else {
          workers = workers.max(half);
          machines = resources.subtract(workers);
        }
        candies = candies.mod(price);
        steps = BigInteger.ONE;
      }
      candies = candies.add(steps.multiply(machines).multiply(workers));
      pathUntilNow = pathUntilNow.add(steps);
      shortestDirectPath = shortestDirectPath.min(pathUntilNow.add(divideAndCeil(target.subtract(candies), machines.multiply(workers))));
    }
    return shortestDirectPath.min(pathUntilNow).longValue();
  }

  private static BigInteger divideAndCeil(BigInteger a, BigInteger b) {
    BigInteger[] res = a.divideAndRemainder(b);
    BigInteger result = res[0];
    BigInteger remainder = res[1];
    if (remainder.signum() != 0) {
      result = result.add(BigInteger.ONE);
    }
    return result;
  }
}

public class Solution {

  public static void main(String[] args) throws IOException {
    run(1, 3, 1, 2, 12, 3);
    run(2, 1, 1, 6, 45, 16);
    run(3, 5184889632L, 5184889632L, 20, 10000, 1);
    run(4, 100, 1, 10000L, 1000000L, 620);
    run(5, 100, 1, 10000000000L, 1000000000000L, 617737754);
    run(6, 4294967295L, 4294967295L, 100, 1000000000000L, 1);
    run(7, 2, 2, 374, 142, 36);
    run(8, 7, 1, 456, 398, 57);
    run(9,1, 1, 499999999999L, 1000000000000L,999999999999L);
  }

  public static void run(long t, long m, long w, long p, long n, long r) {
    long res = Result.minimumPasses(m, w, p, n);
    System.out.println("Test " + t + ": " + ((res == r) ? " - OK - " : "Expected:" +r+", Actual:"+ res));
  }
}
