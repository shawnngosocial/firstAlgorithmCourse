import java.util.*;

public class ShawnFibonacciSumSquares {
    private static long getFibonacciSumSquaresNaive(long n) {
        if (n <= 1)
            return n;

        long previous = 0;
        long current  = 1;
        long sum      = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;

            current = (tmp_previous + current) % 10;
            sum += (current * current) % 10;

            sum %= 10;
        }

        return sum;
    }


    private static int getFibonacciSumSquaresAdvance(long n) {
        return (lastDigitFibonaci(n) * lastDigitFibonaci(n+1)) % 10;
    }

    private static int lastDigitFibonaci(long n) {
        if (n <= 1) {
            return (int) n;
        }

        // pisano period of 10 is 60
        int period = 60;

        // Since F(n) % 10 == F(n % period) % 10
        int fibonacciIndex = (int) (n % 60);

        if (fibonacciIndex <= 1) {
            return fibonacciIndex;
        }

        int previous = 0;
        int current = 1;
        int temp_previous;

        for (int i = 2; i <= fibonacciIndex; i++) {
            temp_previous = previous;
            previous = current;

            current = (previous + temp_previous) % 10;            
        }

        return current;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        // long s = getFibonacciSumSquaresNaive(n);
        long s = getFibonacciSumSquaresAdvance(n);
        System.out.println(s);
    }


    // public static void main(String[] args) {
    //     Random ran = new Random();

    //     while(true) {
    //         long n = (long) (ran.nextDouble() * Math.pow(10,7));

    //         System.out.println(String.format("n: %d", n));

    //         int lastDigitFibonaciSquareNaive = (int) getFibonacciSumSquaresNaive(n);
    //         int lastDigitFibonaciSquareAdvance = getFibonacciSumSquaresAdvance(n);

    //         if (lastDigitFibonaciSquareNaive == lastDigitFibonaciSquareAdvance) {
    //             System.out.println("Ok\n");
    //         } else {
    //             System.out.println(String.format("Wrong! naive: %d, advance: %d", lastDigitFibonaciSquareNaive, lastDigitFibonaciSquareAdvance));
    //             break;
    //         }
    //     }
    // }
}

