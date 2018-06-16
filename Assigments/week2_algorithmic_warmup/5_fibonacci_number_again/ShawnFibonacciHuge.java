import java.util.*;
import java.lang.*;

public class ShawnFibonacciHuge {
    private static long getFibonacciHugeNaive(long n, long m) {
        if (n <= 1)
            return n;

        long previous = 0;
        long current  = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = (tmp_previous + current) % m;
        }

        return current;
    }


    private static long getFibonacciHugeAdvance(long n, long m) {    
        long remainder = n % getPisanoPeriod(m);

        if (remainder <= 1) {
            return remainder % m;
        }

        long previous = 0;
        long current = 1;

        for (long i = 2; i <= remainder; i++) {
            long temp_previous = previous;
            previous = current;

            current = (previous + temp_previous) % m;
        }

        return current;
    }

    private static long getPisanoPeriod(long m) {
        long previous = 0;
        long current = 1;

        for (long i = 2; i <= m*m; i++) {
            long temp_previous = previous;
            previous = current;
            
            current = (previous + temp_previous) % m;

            // System.out.println(String.format("i(th): %d, previous: %d, current: %d", i, previous, current));

            if (previous == 0 && current == 1) {
                
                // System.out.println("i - 1: " + (i - 1));

                return i - 1;
            }
        }



        throw new IllegalStateException("We couldn't find Pisano period length.");
    }

    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long m = scanner.nextLong();
        // System.out.println(getFibonacciHugeNaive(n, m));

        System.out.println(getFibonacciHugeAdvance(n, m));
    }

    // public static void main(String[] args) {
    //     Random ran = new Random();
        
    //     long rangeN = (long) Math.pow(10, 7); 

    //     while (true) {
    //         long n = (long) (ran.nextDouble() * rangeN);
    //         long m = (long) ran.nextInt(((int) Math.pow(10,3) - 1)) + 2;

    //         System.out.println("%n: " + n + ", %m: " + m);

    //         long remainderNaive = getFibonacciHugeNaive(n, m);
    //         long remainderAdvance = getFibonacciHugeAdvance(n, m);

    //         if (remainderNaive == remainderAdvance) {
    //             System.out.println("Ok\n");
    //         } else {
    //             System.out.println("Wrong! naive: " + remainderNaive + " , advance: " + remainderAdvance);
    //             break;
    //         }
    //     }
    // }
}

