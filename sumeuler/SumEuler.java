package sumeuler;

import java.util.ArrayList;

class SumEuler
{
  private ArrayList<Worker> workers;
  private int nThreads;

  SumEuler(int n)
  {
    workers = new ArrayList<>();
    nThreads = n;
  }

  private long hcf(long x, long y)
  {
    if (y == 0)
    {
      return x;
    }
    return hcf(y, x % y);
  }

  private boolean relprime(long x, long y)
  {
    return hcf(x, y) == 1;
  }

  private long euler(long n)
  {
    long length = 0;

    for(long i = 0; i < n; ++i)
    {
      if (relprime(n, i))
      {
        ++length;
      }
    }
    return length;
  }

  public long sumTotient(long lower, long upper)
  {
    long sum = 0;
    
    for (long i = lower; i <= upper; ++i)
    {
      sum += euler(i);
    }
    return sum;
  }

  public long parallelise(long lower, long upper)
  {
    
    for (int i = 0; i < nThreads; ++i)
    {
      //workers.add(new Worker(this, i));
    }

    return 0L;
  }

  public static void main(String args[])
  {
    if (args.length < 4)
    {
      System.err.println("Error: not 3 arguments. Please provide lower, upper and number of threads");
      return;
    }

    long lower = Long.parseLong(args[0]), upper = Long.parseLong(args[1]);
    int nThreads = Integer.parseInt(args[2]);

    SumEuler task = new SumEuler(nThreads);
    task.sumTotient(lower, upper);
    


  }

}