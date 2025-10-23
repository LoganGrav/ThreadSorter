public class Merge implements Runnable {
    private final int start;
    private final int end;
    private final int midpoint;

    //interface constructor
    public Merge(int start, int end, int midpoint) {
        this.start = start;
        this.end = end;
        this.midpoint = midpoint;
    }

    //MultithreadSorter merge method
    @Override
    public void run() { 
        int startPointer = start; //pointers for each half
        int midPointer = midpoint;
        for (int i = start; i <= end; i++) { //iterate through the full array
            if (startPointer != midpoint && midPointer != end + 1) { //check that neither pointer is out of bounds
                if (MultithreadSorter.tempArray[startPointer] < MultithreadSorter.tempArray[midPointer]) { //if left pointer value is smaller
                    MultithreadSorter.sortedArray[i] = MultithreadSorter.tempArray[startPointer]; //add left value to sorted array
                    startPointer++;
                } else {
                    MultithreadSorter.sortedArray[i] = MultithreadSorter.tempArray[midPointer]; //else add right value to sorted array
                    midPointer++;
                }
            } else {
                if (startPointer == midpoint) { //if left pointer is out of bounds
                    MultithreadSorter.sortedArray[i] = MultithreadSorter.tempArray[midPointer]; //append right subarray values
                    midPointer++;
                } else { //if right pointer is out of bounds
                    MultithreadSorter.sortedArray[i] = MultithreadSorter.tempArray[startPointer]; //append left subarray values
                    startPointer++;
                }
            }
        }
    }
    
}
