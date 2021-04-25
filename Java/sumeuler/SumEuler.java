package sumeuler;

import java.util.LinkedList;

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
    long length = 0, i;
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
    long sum = 0, i;
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

    long lower = Long.parseLong(args[0]);
    long upper = Long.parseLong(args[1]);

    long start = System.currentTimeMillis(); 
    long result = SumEuler.sumTotient(lower, upper);
    long end = System.currentTimeMillis();
    System.out.println("Sum of Totient: " + result);
    System.out.println("Elapsed time: " + (end-start) + "ms");
    long mem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
    System.out.println(mem);
  }

}
/*
module Main where

import Control.Parallel.Strategies

hcf :: Int -> Int -> Int
hcf x 0 = x
hcf x y =  hcf y $ x `rem` y

relprime :: Int -> Int -> Bool
relprime x y = hcf x y == 1

eulerSum :: Int -> Int
eulerSum n = length $ filter (\x -> relprime n x) [1..n]

sumTotient :: (Int, Int) -> Int
sumTotient (lower, upper) = sum $ map eulerSum [lower..upper]

divConq :: (a -> b) ->
            a ->
           (a -> Bool) ->
           (b -> b -> b) ->
           (a -> Maybe (a, a)) ->
            b
divConq f arg threshold conquer divide = go arg
  where
    go arg = case divide arg of
      Nothing      -> f arg
      Just (l0,r0) -> conquer l1 r1 `using` strat where
        l1 = go l0
        r1 = go r0
        strat x = do r l1; r r1; return x
          where r | threshold arg = rseq
                  | otherwise     = rpar

range :: (Int, Int)
range = (0,5000)

chunk :: Int
chunk = 250

divide_range :: (Int, Int) -> Maybe ((Int,Int), (Int,Int))
divide_range (n1, n2)
  | n2 - n1 > chunk  = Just ((n1, mid), (mid+1, n2))    
  | otherwise        = Nothing
  where
      mid = (n1+n2) `div` 2

main = print $ divConq sumTotient range (\(x1,x2) -> (x2-x1) <= chunk) (+) divide_range
*/
