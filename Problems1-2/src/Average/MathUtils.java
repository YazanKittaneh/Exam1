package Average;

/**
 * Ava's amazing math utilities.
 */
public class MathUtils
{

  /**
   * Compute the average of all the values in vals.
   *
   * @throws Exception
   *   if the array is empty, because there is no average value
   *   in an empty set.
   */
  public static long average(long[] vals)
    throws Exception
  {
    long temp = 0;
    long avrg = 0;
    int i;
    
    if (vals.length == 0)
      {
        throw new Exception("Array is empty"); // throw exception if array is empty
      }
    else
      {
        for (i = 0; i < vals.length; i++)
          {
            temp = (vals[i] / (long) vals.length); 
              avrg += temp;//otherwise, get average
          } // for
        return avrg;
      }
  } // average(long[])
} // class MathUtils

