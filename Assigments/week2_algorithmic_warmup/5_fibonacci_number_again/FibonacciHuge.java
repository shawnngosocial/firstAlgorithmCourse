import java.util.*;

public class FibonacciHuge {
    private static long getFibonacciHugeNaive(long n, long m) {
        if (n <= 1)
            return n;

        long previous = 0;
        long current  = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
        }

        return current % m;
    }


    private static long getFibonacciHugeAdvance(long n, long m) {
        int remainder = n % getPisanoPeriod(m);
        return remainder % m;
    }

    private static int getPisanoPeriod(long m) {
        long previous = 0;
        long current = 1;

        for (int i = 2; i <= 6*m; i++) {
            long temp_previous = previous;
            previous = current;

            current = (previous + temp_previous) % m;

            if (previous == 0 && current == 1) {
                return i + 1;
            }
        }

        throw new IllegalStateArgumentException("We couldn't find Pisano period length.");
    }

    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long m = scanner.nextLong();
        // System.out.println(getFibonacciHugeNaive(n, m));

        System.out.println(getFibonacciHugeAdvance(n, m));
    }
}

