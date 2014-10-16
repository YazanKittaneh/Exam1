package MutableStrings;
/**
 * Mutable strings.  
 *
 * @author Cher
 * @author Ray
 * @author Your Name Here
 */
public interface MutableString
  extends CharSequence
{
  /**
   * Append str to the end of the string.
   */
  public void append(String str);

  /**
   * Insert str immediately before the ith character of
   * the current string.
   *
   * @pre
   *   0 <= i < this.length()
   */
  public void prepend(int i, String str);

  /**
   * Remove the characters starting at start and finishing 
   * immediately before end.
   */
  public void remove(int start, int end);

  /**
   * Replace all copies of pattern with replacement.
   */
  public void replace(String pattern, String replacement);
} // class MutableString
