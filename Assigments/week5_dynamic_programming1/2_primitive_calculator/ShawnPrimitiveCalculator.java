import java.util.*;

public class ShawnPrimitiveCalculator {
    private static List<Integer> optimal_sequence(int n) {
        List<Integer> sequence = new ArrayList<Integer>();
        while (n >= 1) {
            sequence.add(n);
            if (n % 3 == 0) {
                n /= 3;
            } else if (n % 2 == 0) {
                n /= 2;
            } else {
                n -= 1;
            }
        }
        Collections.reverse(sequence);
        return sequence;
    }


    private static class MinCalcNumber {
        private int minCalc;
        private int prevNum;

        public MinCalcNumber(int minCalc, int prevNum) {
            this.minCalc = minCalc;
            this.prevNum = prevNum;
        }
    }


    private static List<Integer> getOptimalSequenceDP(int n) {
        // intialize array
        MinCalcNumber[] optimalNumbers = new MinCalcNumber[n+1];
        optimalNumbers[0] = new MinCalcNumber(0, 0);
        optimalNumbers[1] = new MinCalcNumber(0, 0);

        // compute array
        for (int i = 2; i <= n; i++) {
            int minOperation = Integer.MAX_VALUE;
            int intermediateNumber = Integer.MAX_VALUE;

            if (i % 3 == 0) {
                if (optimalNumbers[i/3].minCalc + 1 < minOperation) {
                    minOperation = optimalNumbers[i/3].minCalc + 1;
                    intermediateNumber = i/3;
                }
            }

            if (i % 2 == 0) {
                if (optimalNumbers[i/2].minCalc + 1 < minOperation) {
                    minOperation = optimalNumbers[i/2].minCalc + 1;
                    intermediateNumber = i/2;
                }
            }

            if (optimalNumbers[i-1].minCalc + 1 < minOperation) {
                minOperation = optimalNumbers[i-1].minCalc + 1;
                intermediateNumber = i - 1;
            }
            
            optimalNumbers[i] = new MinCalcNumber(minOperation, intermediateNumber);
        }

        // backtract to get sequence
        List<Integer> sequence = new ArrayList<Integer>();
        sequence.add(n);

        int value = n;
        while (value != 1) {
            value = optimalNumbers[value].prevNum;
            sequence.add(value);
        }
        
        Collections.reverse(sequence);

        return sequence;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        List<Integer> sequence = getOptimalSequenceDP(n);
        System.out.println(sequence.size() - 1);

        for (Integer x : sequence) {
            System.out.print(x + " ");
        }
    }
}

