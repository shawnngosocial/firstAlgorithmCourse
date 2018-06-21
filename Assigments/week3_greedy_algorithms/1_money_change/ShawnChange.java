import java.util.Scanner;
import java.util.*;
import java.lang.*;

public class ShawnChange {
	private static final int[] coins = {10, 5, 1};

    private static int getChange(int m) {        
        int numberOfCoins = 0;        

        // sort coins denominations
        sortDesc();
		int index = 0;

		while (m > 0) {
			int newLeft = m - coins[index];			

			if (newLeft < 0) {
				index++;
				continue;
			}

			m = newLeft;
			numberOfCoins++;
		}

        return numberOfCoins;
    }

    private static void sortDesc() {  
    	// assume that we are performing sort desc here  	
    }


    private static int getChangeDifferent(int m) {
    	int numberOfCoins = 0;
    	int index = 0;

    	// sort coins denominations
    	sortDesc();
    	    
    	while (m > 0) {	
    		int quotient = m/coins[index];

    		if (quotient > 0) {
    			numberOfCoins += quotient;
    			m -= quotient * coins[index];
    		} else {
    			quotient = m/coins[++index];
    		}
    	}

    	return numberOfCoins;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));
        // System.out.println(getChangeDifferent(m));
    }

	// public static void main(String[] args) {
 //        Random ran = new Random();

 //        while(true) {
 //        	int change = ran.nextInt(1000);

 //        	System.out.println(change);

 //        	int numberOfCoins = getChange(change);
 //        	int numberOfCoinsDifferent = getChangeDifferent(change);

 //        	if (numberOfCoins == numberOfCoinsDifferent) {
 //        		System.out.println("Ok.");
 //        	} else {
 //        		System.out.println(String.format("change: %d, different: %d", numberOfCoins, numberOfCoinsDifferent));
 //        		break;
 //        	}
 //        }
 //    }    
}

