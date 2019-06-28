import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * This Class contains methods for List, 1D, 2D, and 3D arrays manipulation using J7 and J8 methods.
 * @author Vladimir Krekic
 */

public class ShortCodes2 {
	
	private static Random random = new Random();
	private static int index;

    /**
     * Prints the list in reverse order
     * @param list - List<Object>
     */
    public static void printReversedList(List<Object> list){
        list.stream()
                .map(e -> e.toString())
                .reduce((a, b) -> b + ", " + a)
                .ifPresent(System.out::println);
    }

    /**
     * Prints reversed String if present (Optional.ifPresent())
     * @param string - String for reversing
     */
    public static void printReversedString(String string){
        Stream.of(string.split(""))
                .reduce((a, b) -> b + a)
                .ifPresent(System.out::println);
    }

    /**
     * Returns reversed String
     * @param string - String for reversing
     * @return - reversed String
     */
    public static String returnReversedString(String string){
        return Stream.of(string.split(""))
                    .reduce((a, b) -> b + a)
                    .get();
    }

    /**
     * Flattens matrix so you can find min, max, count
     * @param matrix - 2D array
     * @return - max element in matrix
     */
	public static int matrixMax(int[][] matrix) {
		return Stream.of(matrix)
					.flatMapToInt(IntStream::of)
					.max()  //min(), count()
					.getAsInt();
	}

     /**
     * Flattens cube so you can find min, max, count
     * @param cube - 3D array
     * @return - min element in cube
     */
	public static int cubeMin(int[][][] cube) {
		return Stream.of(cube)
					.flatMap(Stream::of)
					.flatMapToInt(IntStream::of)
					.min() //max(), count()
					.getAsInt();
	}

    /**
     * Prints all the elements of 2D array
     * @param array - 2D array
     */
    public static void print2D(int[][] array){
        Stream.of(array)
                .flatMapToInt(IntStream::of)
                .forEach(value -> System.out.print(value + ", "));
    }

    /**
     * Prints all the elements of 3D array
     * @param array - 3D array
     */
    public static void print3D(int[][][] array){
        Stream.of(array)
                .flatMap(Stream::of)
                .flatMapToInt(IntStream::of)
                .forEach(value -> System.out.print(value + ", "));
    }

    /**
     * Returns String representing all the elements of 3D array separated with ",".
     * No "," after last element.
     * @param array - 3D array
     * @return String representing all the elements of 3D array separated with ",".
     */
	public static String cubeAsString(int[][][] array) {
		return Stream.of(array)
					.flatMap(Stream::of)
					.flatMapToInt(IntStream::of)
					.boxed()
					.map(e -> e.toString())
					.collect(Collectors.joining(", "));
	}

    /**
     * Generates random List<Integer>
     * @param numberOfElements - number of elements in the list
     * @param lowerBound - lower bound of random generated numbers
     * @param upperBound - upper bound of random generated numbers
     * @return - constrained list of random generated Integers
     */
	public static List<Integer> generateRandomList2(int numberOfElements, int lowerBound, int upperBound) {
		return random.ints(lowerBound, upperBound)
					.limit(numberOfElements)
					.boxed()
					.collect(Collectors.toList());							
	}

    /**
     * Returns number of repetitions of the searched element of the list
     * @param list - List<Integer>
     * @param numberToFind - int to find in list
     * @return - number of repetitions of the searched element of the list
     */
    public static int numberOfRepetitionsListJ7(List<Integer> list, int numberToFind){
        int result = 0;
        for(int i = 0; i < list.size(); i++){
            result += (list.get(i) == numberToFind)? 1 : 0;
        }
        return result;
    }

    /**
     * Returns number of repetitions of the searched element of the list using ShortCodes2
     * @param list - List<Integer>
     * @param numberToFind - int to find in list
     * @return - number of repetitions of the searched element of the list
     */
    public static int numberOfRepetitionsListJ8(List<Integer> list, int numberToFind){
        return (int)list.stream()
                .filter(e-> e == numberToFind)
                .count();
    }

    /**
     * Returns number of repetitions of the searched element of the 2D array
     * @param matrix - int[][]
     * @param numberToFind - int to find in 2D array
     * @return - number of repetitions of the searched element of the matrix
     */
    public static int numberOfRepetitionsMatrixJ7(int[][] matrix, int numberToFind){
        int count = 0;
        for(int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[i].length; j++){
                count += (matrix[i][j] == numberToFind)? 1 : 0;
            }
        }
        return count;
    }

    /**
     * Returns number of repetitions of the searched element of the 2D array using ShortCodes2
     * @param matrix - int[][]
     * @param numberToFind - int to find in 2D array
     * @return - number of repetitions of the searched element of the matrix
     */
    public static int numberOfRepetitionsMatrixJ8(int[][] matrix, int numberToFind){
        return (int)Stream.of(matrix)
                .flatMapToInt(IntStream::of)
                .filter(e -> e == numberToFind)
                .count();
    }

    /**
     * Returns number of repetitions of the searched element of the 3D array
     * @param cube - int[][][]
     * @param numberToFind - int to find in 3D array
     * @return - number of repetitions of the searched element of the cube
     */
    public static int numberOfElementsCubeJ7(int[][][] cube, int numberToFind){
        int count = 0;
        for(int i = 0; i < cube.length; i++){
            for (int j = 0; j < cube[i].length; j++){
                for(int k = 0; k < cube[i][j].length; k++){
                    count += (cube[i][j][k] == numberToFind)? 1 : 0;
                }
            }
        }
        return count;
    }

    /**
     * Returns number of repetitions of the searched element of the 3D array using ShortCodes2
     * @param cube - int[][]
     * @param numberToFind - int to find in 3D array
     * @return - number of repetitions of the searched element of the cube
     */
    public static int numberOfRepetitionsCubeJ8(int[][][] cube, int numberToFind){
        return (int)Stream.of(cube)
                .flatMap(Stream::of)
                .flatMapToInt(IntStream::of)
                .filter(e -> e == numberToFind)
                .count();
    }

    /**
     * Returns the most common element in the List and number of its repetitions
     * @param list - List<Integer>
     * @return - int[] containing most common element in the List and number of its repetitions
     */
    public static int[] mostCommonElementJ7(List<Integer> list){
        //The first part of the method generates Map containing
        //all the elements with a number of theirs repetitions
        Map<Integer, Integer> CASH = new HashMap<>();
        for(int element : list){
            if(CASH.containsKey(element))
                CASH.replace(element, CASH.get(element)+1);
            else
                CASH.put(element, 1);
        }
        //The second part of the method finds and returns
        //the most common element with a number of its repetitions
        int[] result = {-1, 0};
        for (Map.Entry<Integer, Integer> element : CASH.entrySet()){
            if(element.getValue() > result[1]){
                result[0] = element.getKey();
                result[1] = element.getValue();
            }
        }
        return result;
    }

    /**
     * Returns the most common element in the List and number of its repetitions
     * using some J8 methods and lambdas
     * @param list - List<Integer>
     * @return - int[] containing most common element in the List and number of its repetitions
     */
    public static int[] mostCommonElementJ8(List<Integer> list){
        //The first part of the method generates Map containing
        //all the elements with a number of theirs repetitions
        Map<Integer, Integer> CASH = new HashMap<>();
        for(int element : list){
            CASH.computeIfPresent(element, (k,v) -> v + 1);
            CASH.computeIfAbsent(element, v -> v = 1);
        }
        //The second part of the method finds and returns
        //the most common element with a number of its repetitions
        int[] result = {-1, 0};
        CASH.forEach((k,v)-> {
            if(v > result[1]){
                result[0] = k;
                result[1] = v;
            }
        });
        return result;
    }

    /**
     * Finds an index of the searched element of the List
     * @param list - List<Integer>
     * @param numberToFind - number to find
     * @return - first index of the searched number
     */
    public static int findElementIndex(List<Integer> list, int numberToFind){
        for(index = 0; index < list.size(); index++){
            if(numberToFind == list.get(index))
                return index;
        }
        return -1; //If not found
    }

    /**
     * Finds an index of the searched element of the List
     * @param list - List<Integer>
     * @param numberToFind - number to find
     * @return - first index of the searched number
     */
    public static int findElementBinarySearch(List<Integer> list, int numberToFind){
        list.sort(Comparator.naturalOrder());   //.binarySearch works only for sorted lists
                                                //Be aware that sort is very expensive operation
        return Collections.binarySearch(list, numberToFind);
    }
}
