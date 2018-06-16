import java.util.*;
import java.lang.*;

public class ShawnGCD {
  private static int gcd_naive(int a, int b) {
    int current_gcd = 1;
    for(int d = 2; d <= a && d <= b; ++d) {
      if (a % d == 0 && b % d == 0) {
        if (d > current_gcd) {
          current_gcd = d;
        }
      }
    }

    return current_gcd;
  }

  private static int gcd_advance_recursive(int a, int b) {
    int remainder = a % b;
    return remainder == 0 ? b : gcd_advance_recursive(b, remainder);
  }

  private static int gcd_advance_iterative(int a, int b) {
    int remainder = a % b;
    
    int dividend = a;
    int divisor = b;

    while (remainder != 0) {
      dividend = divisor;
      divisor = remainder;

      remainder = dividend % divisor;
    }

    return divisor;
  }

  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    int a = scanner.nextInt();
    int b = scanner.nextInt();

    // System.out.println(gcd_naive(a, b));
    System.out.println(gcd_advance_recursive(a,b));
    // System.out.println(gcd_advance_iterative(a,b));
  }


  // public static void main(String args[]) {
  //   Random ran = new Random();

  //   while (true) {
  //     int a = ran.nextInt(2 * ((int) Math.pow(10, 9))) + 1;
  //     int b = ran.nextInt(2 * ((int) Math.pow(10, 9))) + 1;

  //     System.out.println(String.format("a: %d, b: %d. ", a, b));

  //     int remainder_naive = gcd_naive(a, b);
  //     int remainder_advance = gcd_advance_recursive(a, b);

  //     if (remainder_naive == remainder_advance) {
  //       System.out.println("Ok.\n");
  //     } else {
  //       System.out.println(String.format("naive: %d, advance: %d \n", remainder_naive, remainder_advance));
  //       break;
  //     }
  //   }
  // } 

    // public static void main(String args[]) {
    //   Random ran = new Random();

    //   while (true) {
    //     int a = ran.nextInt(2 * ((int) Math.pow(10, 9))) + 1;
    //     int b = ran.nextInt(2 * ((int) Math.pow(10, 9))) + 1;

    //     System.out.println(String.format("a: %d, b: %d. ", a, b));

    //     int remainder_recursive = gcd_advance_recursive(a, b);
    //     int remainder_iterative = gcd_advance_iterative(a, b);

    //     if (remainder_recursive == remainder_iterative) {
    //       System.out.println("Ok.\n");
    //     } else {
    //       System.out.println(String.format("naive: %d, advance: %d \n", remainder_recursive, remainder_iterative));
    //       break;
    //     }
    //   }
    // }  
}

