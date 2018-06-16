import java.util.Scanner;
import java.util.*;

public class Fibonacci {
  private static long calc_fib(int n) {
    if (n <= 1)
      return n;

    return calc_fib(n - 1) + calc_fib(n - 2);
  }

  private static long calcFibEfficient(int n) {    
    // retun when n small
    if (n <= 1) {
      return n;
    }

    long previous = 0;
    long current = 1;

    for (int i = 2; i <= n; i++) {
      long temp = current;
      current = current + previous;

      // assign previous to last value of current before updating
      previous = temp;
    }

    return current;
  }

  public static void main(String args[]) {
    Scanner in = new Scanner(System.in);    
    System.out.println(calcFibEfficient(in.nextInt()));

    // Random ran = new Random();

    // while (true) {
    //   int n = ran.nextInt(46) - 1;
    //   // int n = 47;

    //   System.out.println(n);
    //   long naive = calc_fib(n);
    //   long efficient = calcFibEfficient(n);

    //   if (efficient == naive) {
    //     System.out.println("OK");
    //   } else {
    //     System.out.println(String.format("Wrong answer! efficient: %d, naive: %d", efficient, naive));
    //     break;
    //   }
    // }
  }
}
