import java.io.BufferedReader; //import necessary libraries
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultithreadSorter {
    static int[] unsortedArray; //initialize global arrays
    static int[] sortedArray;
    @SuppressWarnings("unused")
    static int[] tempArray; //I kept getting unused warnings becasue it's only used in other classes
    static int n; //size of the arrays
    static int midpoint; //midpoint for splitting the array

    public static void main(String[]args) {
        List<Integer> inputList = readInput(); //call method to read input file and put it into inputList
        createArrays(inputList); //call method to fill arrays with input data
        sortAndMerge(); //call method to sort and merge the arrays
    }


//Method for making the integer list from the input file
    public static List<Integer>  readInput() {
        List<Integer> inputList = new ArrayList<>(); //initialize input list
        try (BufferedReader br = new BufferedReader(new FileReader("Task1/integer_list.txt"))) { //try to read the input file
            String data;
            data = br.readLine();
            String[] numbers = data.split(" "); //split the line into individual numbers
            for (String number: numbers) {
                inputList.add(Integer.valueOf(number)); //convert each number to integer and add to list
            }
            return(inputList);
        }
        catch (Exception e) { //error handling
            System.out.println(e);
            return(inputList);
        }
    }



//Method for initializing the global arrays and filling them with the input data
    public static void createArrays(List<Integer> inputList) {
        n = inputList.size(); //get size of input list
        unsortedArray = new int[n]; //initialize global arrays with proper size
        sortedArray = new int[n];


        for (int i = 0; i < n; i++) {   //iterate through input list and fill
            unsortedArray[i] = inputList.get(i);
        }

        midpoint = n / 2; //calculate midpoint for splitting the array
    }

    public static void sortAndMerge() {
        tempArray = Arrays.copyOf(unsortedArray, unsortedArray.length); //copy unsorted array so that we can print the original later

        //I used zero indexing for the thread names becuase the example image showed thread 0 and thread 1
        Thread sortThread0 = new Thread(new Sort("Sorting Thread 0", 0, midpoint - 1)); //create first sorting thread for first half
        Thread sortThread1 = new Thread(new Sort("Sorting Thread 1", midpoint, n - 1)); //create second sorting thread for second half
        sortThread0.start(); //start first sorting thread
        sortThread1.start(); //start second sorting thread

        try {
            sortThread0.join(); //wait for first sorting thread to finish
            sortThread1.join(); //wait for second sorting thread to finish
        } catch (InterruptedException e) {
            System.out.println(e);
            return;
        }



        Thread mergeThread = new Thread(new Merge(0, n - 1, midpoint)); //create merging thread
        mergeThread.start(); //start merging thread

        try {
            mergeThread.join(); //wait for merging thread to finish
        } catch (InterruptedException e) {
            System.out.println(e);
            return;
        }


        System.out.println("Original List: " + Arrays.toString(unsortedArray)); //print unsorted array
        System.out.println("Sorted List: " + Arrays.toString(sortedArray)); //print sorted array
    }

}