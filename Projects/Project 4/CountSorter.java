/******************************************************************************************************************************
 * Shashank Sinha
 * Project 4
 * 10/25/2022
 * Course Section 901
 * This project counts the occurrence of numbers in an array and converts it to a sorted array listing out each number in order
 ******************************************************************************************************************************/


package cmsc256;
import java.util.Arrays;
import java.util.Random;


public class CountSorter {


    public static void main(String[] args) {
        Random rand = new Random();

        int[] testArray = new int[rand.nextInt(100)];

        for (int i = 0; i < testArray.length; i++)

            testArray[i] = rand.nextInt(testArray.length);

        System.out.println("Before sort:\n" + Arrays.toString(testArray));

        int[] counters = getCounters(testArray);

        System.out.println("Counters:\n" + Arrays.toString(counters));

        int[] sortedArray = countingSort(testArray);

        System.out.println("After sort:\n" + Arrays.toString(sortedArray));

    }

    public static boolean hasAllPositiveElements(final int[] anArray) {
        if (anArray == null) {
            throw new IllegalArgumentException("Array is empty");
        }
        for (int i = 0; i < anArray.length; i++) {
            if (anArray[i] < 0) {
                return false;
            }
            // returns true if all elements in the parameter array are positive

        }
        return true;
    }

    public static int findMax(final int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Array is empty");
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 0) {
                System.out.println("Array contains a negative number");
            }
        }

        int max = array[0];
        for (int num : array) {
            if (num > max) {
                max = num;
            }
        }
        return max;
        // returns the maximum value in the parameter array
    }

    public static int[] getCounters(final int[] anArray) {
        if (anArray == null) {
            throw new IllegalArgumentException("Array is empty");
        }
        for (int i = 0; i < anArray.length; i++) {
            if (anArray[i] < 0) {
                throw new IllegalArgumentException("Array contains a negative number");
            }
        }

        int max = findMax(anArray);
        int[] countArray = new int[max + 1];
        for (int count : anArray) {
            countArray[count]++;
        }
        return countArray; // returns an array containing the count for each value in the parameter array
    }

    public static int[] convertCountersToSortedArray(final int[] countArray) {
        int j = 0;
        int value;

        int[] sortedArray = new int[countArray.length];

        for (int i = 0; i < countArray.length; i++){
            value = countArray[i];
            while (value != 0){
                sortedArray[j] = i;
                j++;
                value--;
            }
        }
        return sortedArray;
    }

    public static int[] countingSort(final int[] anArray) {
        int length = 0;
        for (int j : anArray) {
            length += j;
        }

        int[] countingSortArray = new int[length];
        boolean positiveElements = hasAllPositiveElements(anArray);

        if (positiveElements){
            int max = findMax(anArray);
            int[] array;
            array = getCounters(anArray);
            countingSortArray = convertCountersToSortedArray(array);
        }
        else {
            throw new IllegalArgumentException("Array has negative number");
        }

        return countingSortArray;
    }
}


