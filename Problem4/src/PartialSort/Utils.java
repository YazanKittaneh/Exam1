package PartialSort;
import java.util.Comparator;

/**
 * Assorted utilities.
 */
public class Utils
{
  /**
   * Rearrange values into three parts: less than val, equal to val, and
   * greater than val.
   *
   * @return boundaries
   *   an array of two integers. boundaries[0] contains the index of the
   *   first value greater than or equal to val, if such a value exists,
   *   or values.length, if there is no such value.  boundaries[1] contains
   *   the index of the first value strictly greater than val, if such a
   *   value exists, or values.length, if there is no such value.
   * @post
   *   No values have been added to values or removed from values.
   * @post
   *   All elements with index less than boundaries[0] are smaller thna
   *   val.  All elements with index greater than or equal to boundaries[0] 
   *   and less than boundaries[1] are equal to val.  All elements with
   *   index greater than or equal to boundaries[1] are larger than val.
   */
  public static <T> int[] pseudoSort(T val, T[] values,
       Comparator<? super T> order)
  {
    int lessthanBound;
    int greaterthanBound;
    int equalsBound;
    int lowerBound;
    int upperBound;
    T temp; 
    int i;
    
    for (i=0; i<=values.length; i++)
      {
        if(//val is greater than)
            {
              temp = values[lowerBound];
              lowerBound++
            }
            else if (val is less than)
              {
                
              }
              
            
      }
    
    return new int[] { values.length, values.length };
  } // pseudoSort
} // class Utils
