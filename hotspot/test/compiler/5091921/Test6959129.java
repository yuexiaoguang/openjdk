/**
 * @test
 * @bug 6959129
 * @summary COMPARISON WITH INTEGER.MAX_INT DOES NOT WORK CORRECTLY IN THE CLIENT VM.
 *
 * @run main/othervm -ea Test6959129
 */

public class Test6959129 {

  public static void main(String[] args) {
    long start  = System.currentTimeMillis();
    int min = Integer.MAX_VALUE-30000;
    int max = Integer.MAX_VALUE;
    long maxmoves = 0;
    try {
      maxmoves = maxMoves(min, max);
    } catch (AssertionError e) {
      System.out.println("Passed");
      System.exit(95);
    }
    System.out.println("maxMove:" + maxmoves);
    System.out.println("FAILED");
    System.exit(97);
  }
  /**
   * Imperative implementation that returns the length hailstone moves
   * for a given number.
   */
  public static long hailstoneLengthImp(long n) {
    long moves = 0;
    while (n != 1) {
      assert n > 1;
      if (isEven(n)) {
        n = n / 2;
      } else {
        n = 3 * n + 1;
      }
      ++moves;
    }
    return moves;
  }

  private static boolean isEven(long n) {
    return n % 2 == 0;
  }

  /**
   * Returns the maximum length of the hailstone sequence for numbers
   * between min to max.
   *
   * For rec1 - Assume that min is bigger than max.
   */
  public static long maxMoves(int min, int max) {
    long maxmoves = 0;
    for (int n = min; n <= max; n++) {
      if ((n & 1023) == 0) System.out.println(n);
      long moves = hailstoneLengthImp(n);
      if (moves > maxmoves) {
        maxmoves = moves;
      }
    }
    return maxmoves;
  }
}

