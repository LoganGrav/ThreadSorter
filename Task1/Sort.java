import java.util.Arrays;

//implement runnable so we can make threads
public class Sort implements Runnable {
    private final String threadName; //declare threadname for subarray printing
    private final int start;    //declare start and end vars for sorting
    private final int end;

    //initialize variables via constructor
    public Sort(String threadName, int start, int end) {
        this.threadName = threadName;
        this.start = start;
        this.end = end;
    }

    //MultithreadSorter method, I chose insertion sort because its my favorite :)
    @Override
    public void run() {
        for (int i = start + 1; i <= end; i++) { //iterate through subarray
            int key = MultithreadSorter.tempArray[i];    //get key value
            int j = i - 1;  //set j to the index before i

            while (j >= start && MultithreadSorter.tempArray[j] > key) { //check bounds and compare values
                MultithreadSorter.tempArray[j + 1] = MultithreadSorter.tempArray[j];  //shift value to the right
                j--;    //decrement j
            }

            MultithreadSorter.tempArray[j + 1] = key;    //place key in correct position
        }

        //print sorted subarray. I had to use copyOfRange so the size could be variable
        System.out.println(threadName + " subarray: [" + start + "..." + end + "]: " + 
                Arrays.toString(Arrays.copyOfRange(MultithreadSorter.tempArray, start, end + 1)));
    }

    }

