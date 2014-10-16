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
    int count =0;
    
    if (vals.length == 0)
      {
        throw new Exception("Array is empty"); // throw exception if array is empty
      }
    else
      {
        for (i = 0; i < vals.length; i++)
          {
            System.out.println(vals[i]);
            if((vals[i] % vals.length) >= .5 && count%2 == 0)
              {
                System.out.println("true " + i);
                avrg++;
                count++;
              }
            System.out.println("increment " + i);
            temp = (vals[i] / (long) vals.length); 
              avrg += temp;//otherwise, get average
          } // for
        return avrg;
      }
  } // average(long[])
} // class MathUtils

