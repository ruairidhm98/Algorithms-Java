package sumeuler;

class Worker implements Runnable
{
  int chunks[][];
  long result;
  SumEuler euler;

  public Worker(SumEuler elr, int params[][])
  {
    chunks = params;
    euler = elr;
  }

  @Override
  public void run() {
    for (int i = 0; i < chunks.length; ++i)
    {
      result += euler.sumTotient(chunks[1][0], chunks[1][0]);
    }
  }

  public long getResult()
  {
    return result;
  }
  
}