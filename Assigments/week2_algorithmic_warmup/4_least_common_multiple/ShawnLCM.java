import java.util.*;

public class ShawnLCM {
  private static long lcm_naive(int a, int b) {
    for (long l = 1; l <= (long) a * b; ++l)
      if (l % a == 0 && l % b == 0)
        return l;

    return (long) a * b;
  }

  private static long lcm_advance(int a, int b) {    
    int gcd = gcd(a,b);
    return (long) (a/gcd)*b;
  }

  private static int gcd(int a, int b) {
    int remainder = a % b;    
    return remainder == 0 ? b : gcd(b, remainder);
  }

  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    int a = scanner.nextInt();
    int b = scanner.nextInt();

    // System.out.println(lcm_naive(a, b));
    System.out.println(lcm_advance(a, b));
  }

  // public static void main(String args[]) {
  //   Random ran = new Random();

  //   while (true) {
  //     int a = ran.nextInt(2 * ((int) Math.pow(10, 4))) + 1;
  //     int b = ran.nextInt(2 * ((int) Math.pow(10, 4))) + 1;

  //     System.out.println(String.format("a: %d, b: %d", a, b));

  //     long lcm_naive = lcm_naive(a, b);
  //     long lcm_advance = lcm_advance(a, b);

  //     if (lcm_advance == lcm_naive) {
  //       System.out.println("Ok\n");
  //     } else {
  //       System.out.println(String.format("Wrong! advance: %d, naive: %d", lcm_advance, lcm_naive));
  //       break;
  //     }
  //   }
  // }
}
