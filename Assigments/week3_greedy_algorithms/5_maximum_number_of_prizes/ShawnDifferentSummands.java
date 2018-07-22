import java.util.*;

public class ShawnDifferentSummands {

    private static List<Integer> optimalSummands(int n) {
        // define problem of (k, 1)
        List<Integer> summands = new ArrayList<>();

        for (int k = n, l = 1; k > 0; l++) {
            if (k > 2*l) {
                // add new summand
                summands.add(l);

                // reduce to sub-problem (k-l, l+1);
                k -= l;            
            } else {
                summands.add(k);                
                k -= k;
            }
        }

        return summands;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        
        List<Integer> summands = optimalSummands(n);
        System.out.println(summands.size());

        for (Integer summand : summands) {
            System.out.print(summand + " ");
        }
    }
}

