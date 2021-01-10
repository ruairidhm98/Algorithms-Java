package sumeuler;

class SumEuler
{
  private static long hcf(long x, long y)
  {
    if (y == 0)
    {
      return x;
    }
    return hcf(y, x % y);
  }

  private static boolean relprime(long x, long y)
  {
    return hcf(x, y) == 1;
  }

  private static long euler(long n)
  {
    long length = 0;

    for(long i = 1; i < n; ++i)
    {
      if (relprime(n, i))
      {
        ++length;
      }
    }
    return length;
  }

  public static long sumTotient(long lower, long upper)
  {
    long sum = 0;
    
    for (long i = lower; i <= upper; ++i)
    {
      sum += euler(i);
    }
    return sum;
  }

  public static void main(String args[])
  {
    if (args.length != 2)
    {
      System.err.println("Error: not 2 arguments. Please provide lower, upper");
      return;
    }

    long lower = Long.parseLong(args[0]), upper = Long.parseLong(args[1]);

    long start = System.currentTimeMillis();
    long result = SumEuler.sumTotient(lower, upper);
    long end = System.currentTimeMillis();
    System.out.println("Sum of Totient: " + result);
    System.out.println("Elapsed time: " + (end-start) + "ms");
  }

}