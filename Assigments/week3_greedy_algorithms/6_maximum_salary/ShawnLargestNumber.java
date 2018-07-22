import java.util.*;
import java.lang.*;

public class ShawnLargestNumber {
    
    // ===================== MAIN ALGORITH =============

    private static String largestNumber(List<String> digits) {
        StringBuilder largestNumber = new StringBuilder();

        while(digits.size() > 0) {
            String max = Integer.toString(0);
            
            // store index to remove faster
            int index = -1;

            for (int i = 0; i < digits.size(); i++) {
                if (isGreater(digits.get(i), max)) {
                    max = digits.get(i);    
                    index = i;
                }
            }

            // append max to result
            largestNumber.append(max);

            // remove inserted element
            digits.remove(index);
        }

        return largestNumber.toString();
    }

    private static boolean isGreater(String first, String second) {                
        return Integer.parseInt(first + second) > Integer.parseInt(second + first);
    }

    // ================ END ALGORITHM ==================



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int n = scanner.nextInt();
        List<String> a = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            a.add(scanner.next());
        }
        System.out.println(largestNumber(a));
    }
}



