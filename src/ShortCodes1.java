import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This Class contains fib, factorial and String analyzing methods using recursion with memoization and direct approach.
 * @author Vladimir Krekic
 */

public class ShortCodes1 {

	private static Map<Integer, BigInteger> CACHE = new HashMap<>();

	/**
	 * Recursion with memoization
	 * Efficient as for loop. 
	 * If it is called several times, better than for loop because it stores data.
	 * Requires a lot of MEMORY for big numbers.
	 * @param key : int that represents position of resulting number in Fibonacci sequence
	 * @return : int - number on key position in Fibonacci sequence
	 */
	public static BigInteger fibonacci(int key) {
		if(!CACHE.containsKey(key))
			CACHE.put(key, key <= 2 ? BigInteger.ONE : fibonacci(key - 1).add(fibonacci(key - 2))); // key <= 2 is exit condition
		return CACHE.get(key);
	}
	
	/**
	 * Recursion with memoization
	 * Efficient as for loop. 
	 * If it is called several times, better than for loop because it stores data.
	 * Requires a lot of MEMORY for big numbers.
	 * @param key : int
	 * @return : int - factorial of key
	 */
	public static BigInteger factorial(int key) {
		if(!CACHE.containsKey(key))
			CACHE.put(key, (key <= 1) ? BigInteger.ONE : factorial(key-1).multiply(BigInteger.valueOf(key))); // key <= 1 is exit condition
		return CACHE.get(key);
	}
	
	/**
     * This method is checking if two strings are twins.
     * Two Strings are twins if they contain the same characters, not necessarily in the same order.
     * @param string1 : String for comparison
	 * @param string2 : String for comparison
	 * @return : boolean
     */
    public static boolean isTwin(String string1, String string2) {

        if(string1.length() != string2.length())
            return false;

        if(string1.length() == 0)		//exit condition
            return true;

        StringBuilder stringBuilder1 = new StringBuilder(string1);
        StringBuilder stringBuilder2 = new StringBuilder(string2);

        for(int i=0; i<stringBuilder1.length(); i++) {
            for(int j=0; j<stringBuilder2.length(); j++) {
                if(stringBuilder1.charAt(i) == stringBuilder2.charAt(j)) {
                    stringBuilder2.deleteCharAt(j);
                    break;
                }
            }
        }
        return stringBuilder2.length() == 0;
    }
	
    /**
     * Wrapper method for checking if two strings are twins.
     * Two Strings are twins if they contain the same characters, not necessarily in the same order.
     * Recursive approach
     * Makes StringBuilder objects and passes them to other method.
     * In recursive approach, wrapper method is the best solution because we do not want to create a lot
     * of Strings, due to theirs immutability and we do not want to create new StringBuilders again and
     * again for each step of recursion.
     * @param string1 : String for comparison
	 * @param string2 : String for comparison
	 * @return : boolean
     */
    public static boolean isTwinRecursive(String string1, String string2) {

        if(string1.length() != string2.length())
            return false;

        StringBuilder stringBuilder1 = new StringBuilder(string1);
        StringBuilder stringBuilder2 = new StringBuilder(string2);

        return isTwinRecursive(stringBuilder1, stringBuilder2);
    }
    
    /**
     * This method is checking if two strings are twins.
     * Two Strings are twins if they contain the same characters, not necessarily in the same order.
     * Recursive approach
     * @param stringBuilder1 : StringBuilder for comparison passed from wrapper method
     * @param stringBuilder2 : StringBuilder for comparison passed from wrapper method
     * @return : boolean returned to wrapper method
     */    
    private static boolean isTwinRecursive(StringBuilder stringBuilder1, StringBuilder stringBuilder2) {

        if(stringBuilder1.length() == 0) //exit condition
            return true;

        for(int i=0; i<stringBuilder2.length(); i++) {
            if(stringBuilder1.charAt(0) == stringBuilder2.charAt(i)) {
                stringBuilder1.deleteCharAt(0);
                stringBuilder2.deleteCharAt(i);
                return isTwinRecursive(stringBuilder1, stringBuilder2);
            }
        }
        return false;
    }
    
    /**
     * Wrapper method for checking if number and orientation of parentheses
     * in passed math expression is correct.
     * Wrapper method strips everything except parentheses and forwards them as a char array.
     * @param mathExpression : String - math expression passed as String
     * @return boolean
     */
    public static boolean parentheses(String mathExpression){
        return parentheses(Stream.of(mathExpression.split(""))
								.filter(e-> e.equals("(") || e.equals(")"))
								.collect(Collectors.joining())
								.toCharArray());
    }

    /**
     * Method for checking if number and orientation of parentheses is correct.
     * @param array : char[] passed from wrapper method
     * @return : boolean returned to wrapper method
     */
    private static boolean parentheses(char[] array){
        if (array.length % 2 != 0)
            return false;
        int counter = 0;
        for (char c : array) {
            counter += c == '(' ? 1 : -1;
            if(counter < 0)
                return false;
        }
        return (counter == 0);
    }
}
