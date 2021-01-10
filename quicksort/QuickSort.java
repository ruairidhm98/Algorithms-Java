package quicksort;

import java.util.Arrays;
import java.util.Random;

class QuickSort
{
  private static Random rand = new Random();

  private static void swap(int array[], int i, int j)
  {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

  private static int median(int array[], int i, int j, int k)
  {
    // Need to do this fast as possible, so avoid the overhead of putting these in an array and sorting
    int max = Math.max(array[i], Math.max(array[j], array[k]));
    int median;
    // Compute max of all 3 and then maximum of the other two to obtain the median
    if (max == array[i])
    {
      median = Math.max(array[j], array[k]);
      return median == array[j] ? j : k;
    }
    else if (max == j)
    {
      median = Math.max(array[i], array[k]);
      return median == array[i] ? i : k;
    }
    median = Math.max(array[i], array[j]);
    return median == array[i] ? i : j;
  }

  private static int partition(int array[], int start, int end)
  {
    int nextIdx = start-1;
    int mid = (start+end)/2;

    // Choose pivot to be median of array[start], array[end] and array[mid]
    int pivotIdx = median(array, start, end, mid);
    int pivot = array[pivotIdx];
    // Move pivot to end of array
    swap(array, pivotIdx, end);
    
    for (int i = start; i <= end; ++i)
    {
      // Move all < pivot to start of array, exact position determined by nextIdx
      if (array[i] < pivot)
      {
        swap(array, ++nextIdx, i);
      }
    }
    // Move pivot to correct place
    swap(array, ++nextIdx, end);
    return nextIdx;
  }
  
  private static void quicksort(int array[], int start, int end)
  {
    if (start < end)
    {
      int partitionIndex = partition(array, start, end);
      quicksort(array, start, partitionIndex-1);
      quicksort(array, partitionIndex+1, end);
    }
  }

  private static void fillArray(int array[])
  {
    for (int i = 0; i < array.length; ++i)
    {
      array[i] = rand.nextInt();
    }
  }

  public static void main(String args[])
  {
    if (args.length != 1)
    {
      System.err.println("Error: please provide size of input array");
      return;
    }
  
    int nums[] = new int[Integer.parseInt(args[0])];
    fillArray(nums);

    long start = System.currentTimeMillis();
    quicksort(nums, 0, nums.length-1);
    long end = System.currentTimeMillis();
    
    System.out.println("Elapsed time: " + (end-start) + "ms");
  }

}