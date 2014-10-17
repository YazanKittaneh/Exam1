package Average;

import static org.junit.Assert.*;

import java.io.PrintWriter;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * A test of the average method.
 */
public class AverageTest
{

  PrintWriter pen = new PrintWriter(System.out, true);

  // +---------+-----------------------------------------------------------
  // | Helpers |
  // +---------+

  /**
   * Run a test on an array, with an expected value, printing an appropriate
   * error message if it fails.
   */
  void checkAverage(long expected, long values[])
  {
    try
      {
        long result = MathUtils.average(values);
        if (result != expected)
          {
            fail("For average(" + Arrays.toString(values) + ") expected "
                 + expected + " received " + result);
          } // if (result != expected)
      } // try
    catch (Exception e)
      {
        fail("average(" + Arrays.toString(values) + ") threw exception " + e);
      } // catch (Exception)
  } // checkAverage(long, values[])

  // +-------+-------------------------------------------------------------
  // | Tests |
  // +-------+

  /**
   * Tests a null array
   * @throws Exception 
   */
  @Test
  public void test0()
    throws Exception
  {
    boolean failEmpty = false;
    try
      {
        checkAverage(0, new long[] {});
      } // try
    catch (Exception e)
      {
        failEmpty = true;
      }
    if (!failEmpty)
      {
        fail("Average did not fail as expected");
      } //if(!fail);
  } // test0

  /**
   * Tests an empty array of size 1
   * 
   * @throws Exception if checkAverage fails
   */
  @Test
  public void test1Empty()
    throws Exception
  {
    try
      {
        checkAverage(0, new long[] {});
      } //try
    catch (Exception e)
      {
        pen.println("test1Empty Error on run");
      } // catch (Exception e)
  } // test1

  /**
   * Tests an array of size 1 with element i where -10<=i<=10
   * 
   * @throws Exception if checkAverage fails and reports 
   *               what part of the loop failed the test
   */
  @Test
  public void test1Num()
    throws Exception
  {
    long i = 0;
    for (i = -10; i <= 10; i++)
      {
        try
          {
            checkAverage(i, new long[] { i });
          } // try
        catch (Exception e)
          {
            pen.println("test1Num Error on run " + i);
          } // catch (Exception e)

      } // for
  } // test1

  /**
   * Tests an array of size 1 with element 
   * MAX_VALUE of long
   */
  public void test1MAX()
  {
    long i = Long.MAX_VALUE;
    try
      {
        checkAverage(i, new long[] { i });
      } // try
    catch (Exception e)
      {
        pen.println("test1MAX Error on run");
      } // catch (Exception e)
  }

  /**
   * Tests an array of size 1 with element 
   * MIN_VALUE of long
   */
  public void test1MIN()
  {
    long i = Long.MIN_VALUE;
    try
      {
        checkAverage(i, new long[] { i });
      } // try
    catch (Exception e)
      {
        pen.println("test1MAX Error on run");
      } // catch (Exception e)
  }

  /**
   * Tests an array of size 2, with elements i and j
   * are null
   */
  @Test
  public void test2Empty()
    throws Exception
  {
    try
      {
        checkAverage(0, new long[] {,});
      } // try 
    catch (Exception e)
      {
        pen.println("text2Empty Error on array {,}");
      } // catch (Exception e)
  } // test2

  /**
   * Tests an array of size 2, with elements i and j where 
   * -10<=i<=10 and -10<=j<=10
   */
  @Test
  public void test2Num()
    throws Exception
  {
    long i, j;
    for (i = -10; i <= 10; i++)
      {
        for (j = -10; j <= 10; j++)
          {
            try
              {
                
                checkAverage(((i + j) / 2), new long[] { i, j });
              }
            catch (Exception e)
              {
                pen.println("Text2num Error on run " + i + " and " + j);
              }
          }
      }
  } // test2

  /**
   * Tests an array of size 2, with elements MAX_VALUE
   *  and MAX_VALUE of long
   */
  public void test2MAX()

  {
    long i = Long.MAX_VALUE;
    try
      {
        checkAverage(i, new long[] { i, i });
      } // try
    catch (Exception e)
      {
        pen.println("test1MAX Error on run");
      }
  }
  
  /**
   * Tests an array of size 2, with elements MIN_VALUE 
   * and MIN_VALUE of long
   */
  public void test2MIN()

  {
    long i = Long.MIN_VALUE;
    try
      {
        checkAverage(i, new long[] { i, i });
      } // try
    catch (Exception e)
      {
        pen.println("test1MIN Error on run");
      }
  } //test2MIN()

 /**
 * Tests arrays size 3-10000 made of element MAX_VALUE
 */
  public void test3ArrMAX()
  {
    int size=0;
    long max= Long.MAX_VALUE;
    Random generator = new Random();
    
    for (size = 3; size < 10000; size = size + generator.nextInt(100))
      {
        long[] arr = new long[size];
        for (int i = 0; i < size; i++)
          {
            arr[i] = max;
          } // inner for
        checkAverage(max, arr);
      }// outer for
  } //test3ArrMAX
  
  /**
   * Tests arrays size 3-10000 made of element Min_VALUE
   */
    public void test3ArrMIN()
    {
      int size=0;
      long min= Long.MIN_VALUE;
      Random generator = new Random();
      
      for (size = 3; size < 10000; size = size + generator.nextInt(100))
        {
          long[] arr = new long[size];
          for (int i = 0; i < size; i++)
            {
              arr[i] = min;
            } // inner for
          checkAverage(min, arr);
        }// outer for
      
    } //test3ArrMIN
  /**
   * Tests arrays size 3-10000 made of element 1
   */
    public void test3Arr()
    {
      int size=0;
      Random generator = new Random();
      
      for (size = 3; size < 10000; size = size + generator.nextInt(100))
        {
          long[] arr = new long[size];
          for (int i = 0; i < size; i++)
            {
              arr[i] = 1;
            } // inner for
          checkAverage(1, arr);
        }// outer for
    } //test3Arr
  
  /**
   * Main method
   * @param args
   * @throws Exception
   */
  public void main(String[] args)
    throws Exception
  {
    test0();
    test1Empty();
    test1Num();
    test1MAX();
    test1MIN();
    test2Empty();
    //test2Num();
    test2MAX();
    test2MIN();
    test3Arr();
  }

} // class AverageTest
