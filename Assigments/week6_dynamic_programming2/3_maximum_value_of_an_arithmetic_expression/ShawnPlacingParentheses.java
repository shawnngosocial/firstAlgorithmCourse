import java.util.*;

public class ShawnPlacingParentheses {
    
    private static long getMaximValue(String exp) {
        int noOfDigits = (exp.length() - 1)/2 + 1;

        long[][] maximum = new long[noOfDigits][noOfDigits];
        long[][] minimum = new long[noOfDigits][noOfDigits];

        // initialize for in case exp contains only one 1 digit
        for (int i = 0; i < noOfDigits; i++) {
            maximum[i][i] = (long) exp.charAt(2*i) - '0';
            minimum[i][i] = (long) exp.charAt(2*i) - '0';
        }

        for (int s = 1; s < noOfDigits; s++) {
            for (int i = 0; i < noOfDigits - s; i++) {
                int j = i + s;

                Optimal optimal = getOptimalOfExp(i, j, maximum, minimum, exp);

                maximum[i][j] = optimal.getMax();
                minimum[i][j] = optimal.getMin();
            }
        }


        // DEBUG
        // System.out.println("\n\nFinal maximum matrix: ");
        // print2DMatrix(maximum);
        // System.out.println("Final minimum matrix: ");
        // print2DMatrix(minimum);
        // System.out.println("\n\n");


        return maximum[0][noOfDigits - 1];
    }


    private static Optimal getOptimalOfExp(int i, int j, long[][] maximum, long[][] minimum, String exp) {
        Optimal optimal = new Optimal(Long.MIN_VALUE, Long.MAX_VALUE);


        for (int k = i; k < j; k++) {
            char operator = exp.charAt(2*k+1);

            updateOptimal(maximum[i][k], maximum[k+1][j], operator, optimal);
            updateOptimal(maximum[i][k], minimum[k+1][j], operator, optimal);
            updateOptimal(minimum[i][k], maximum[k+1][j], operator, optimal);
            updateOptimal(minimum[i][k], minimum[k+1][j], operator, optimal);
        }

        return optimal;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String exp = scanner.next();
        System.out.println(getMaximValue(exp));
    }



    // ==============================  UTILITY  ================================
    

    private static class Optimal {
        private long max;
        private long min;

        public Optimal(long max, long min) {
            this.max = max;
            this.min = min;
        }

        public long getMax() {
            return this.max;
        }

        public long getMin() {
            return this.min;
        }

        public void setMax(long max) {
            this.max = max;
        }

        public void setMin(long min) {
            this.min = min;
        }

        @Override
        public String toString() {
            return String.format("[max: %d, min: %d]", max, min);
        }
    }

    private static void updateOptimal(long a, long b, char operator, Optimal optimal) {
        long result = eval(a, b, operator);
        if (result > optimal.getMax()) {
            optimal.setMax(result);
        }
        if (result < optimal.getMin()) {
            optimal.setMin(result);
        }
    }

    private static long eval(long a, long b, char op) {
        if (op == '+') {
            return a + b;
        } else if (op == '-') {
            return a - b;
        } else if (op == '*') {
            return a * b;
        } else {
            assert false;
            return 0;
        }
    }

    private static void print2DMatrix(long[][] matrix) {
        for (long[] row : matrix){
            System.out.println(Arrays.toString(row));
        }
    }
}

