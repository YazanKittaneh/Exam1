package MutableStrings;

/**
 * A basic implementation of mutable strings.  An alternative to string 
 * buffers when you want something like a string that you can change.
 *
 * @author Cher
 * @author Ray
 * @author Your Name Here
 */
public class BasicMutableString
    implements MutableString
{
  // +-------+-------------------------------------------------------------
  // | Notes |
  // +-------+

  /*
    We store strings in arrays of characters.  When we need to expand
    the array, we double the size of the array.  That suggests we never
    use more than twice as much is necessary (well, except when we then
    delete characters), but keeps running time relatively good.
   */

  // +-----------+---------------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * The default capacity of a mutable string.
   */
  static final int DEFAULT_CAPACITY = 16;

  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The contents of the string.  May include extra capacity to make
   * it simpler to expand the string.
   */
  char contents[];

  /**
   * The actual size of the string.
   */
  int size;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create an empty mutable string.
   */
  public BasicMutableString()
  {
    this.contents = new char[DEFAULT_CAPACITY];
    this.size = 0;
  } // BasicMutableString()

  /**
   * Create a mutable string, initialized to str.
   */
  public BasicMutableString(String str)
  {
    this.size = str.length();
    this.contents = new char[computeNeededCapacity(this.size)];
    for (int i = 0; i < size; i++)
      this.contents[i] = str.charAt(i);
  } // BasicMutableString(String)

  // +---------+-----------------------------------------------------------
  // | Helpers |
  // +---------+

  /**
   * Compute the first reasonable capacity larger than mincap.
   */
  int computeNeededCapacity(int mincap)
  {
    // Start at either the default or current capacity
    int capacity = DEFAULT_CAPACITY;
    if (this.contents != null)
      capacity = this.contents.length;

    // Keep increasing until we are large enoug
    while (capacity < mincap)
      {
        capacity *= 2;
      } // while

    // And we're done
    return capacity;
  } // computeNeededCapacity(int)

  // +-------------------------+-------------------------------------------
  // | Standard Object Methods |
  // +-------------------------+

  /**
   * Determine if we are the same as another object.
   */
  public boolean equals(Object other)
  {
    return (other instanceof BasicMutableString)
           && equals((BasicMutableString) other);
  } // equals(Object)

  /**
   * Convert to a string.
   */
  public String toString()
  {
    return new String(this.contents, 0, this.size);
  } // toString()

  // +-----------+---------------------------------------------------------
  // | Observers |
  // +-----------+

  /**
   * Get the ith character of this string.
   *
   * @pre 0 <= i < this.length()
   */
  public char charAt(int i)
  {
    return this.contents[i];
  } // charAt(int)

  /**
   * Determine if we are the same as another mutable string.
   */
  public boolean equals(BasicMutableString other)
  {
    return this.toString().equals(other.toString());
  } // equals(MutableString)

  /**
   * Get the length of this mutable string.
   */
  public int length()
  {
    return this.size;
  } // length()

  /**
   * Get a new <code>CharSequence</code> that is a subsequence
   * of this sequence.
   */
  public CharSequence subSequence(int start, int end)
  {
    // Inefficient, but usable
    return new BasicMutableString(new String(this.contents, start, end));
  } // toString()

  // +----------+----------------------------------------------------------
  // | Mutators |
  // +----------+

  /**
   * Append str to the end of the string.
   */
  public void append(String str)
  {
    int newsize = this.size + str.length();

    // If there's insufficient capacity, make a new array
    if (newsize >= this.contents.length)
      {
        char[] oldcontents = this.contents;
        this.contents = new char[computeNeededCapacity(newsize)];
        for (int i = 0; i < this.size; i++)
          this.contents[i] = oldcontents[i];
      } // if

    // Copy the characters.
    for (int i = this.size; i < newsize; i++)
      this.contents[i] = str.charAt(i - this.size);

    // Update the size
    this.size = newsize;
  } // append(String)

  /**
   * Insert str immediately before the ith character of
   * the current string.
   *
   * @pre
   *   0 <= i < this.length()
   */
  public void prepend(int i, String str)
  {
    int len = str.length();
    int newsize = this.size + len;

    // If there's insufficient capacity, make a new array,
    // leaving space for the string to be prepended.
    if (newsize >= this.contents.length)
      {
        char[] oldcontents = this.contents;
        this.contents = new char[computeNeededCapacity(newsize)];
        for (int j = 0; j < i; j++)
          this.contents[j] = oldcontents[j];
        for (int j = i; j < this.size; j++)
          this.contents[j + len] = oldcontents[j];
      } // if there's insufficient space

    // Otherwise, there's sufficient capacity, but we need to
    // make a space in the array.
    else
      {
        for (int j = this.size - 1; j >= i; j--)
          this.contents[j + len] = this.contents[j];
      } // else

    // There's space.  Put things in
    for (int j = 0; j < len; j++)
      this.contents[i + j] = str.charAt(j);

    // And that's it
    this.size = newsize;
  } // prepend(String)

  /**
   * Remove the characters starting at start and finishing 
   * immediately before end.
   */
  public void remove(int start, int end)
  {
    int offset = end - start;
    for (int i = end; i < this.size; i++)
      this.contents[i - offset] = this.contents[i];
    this.size -= offset;
  } // remove(int, int)

  /**
   * Replace all copies of pattern with replacement.
   */
  @SuppressWarnings("unused")
  public void replace(String pattern, String replacement)
  {
    int lowerBound = 0;
    int upperBound = this.length();
    int current = 0;
    int diff = Math.abs(pattern.length() - replacement.length());
    int i = 0, j = 0;
    int limit = 16;
    int strLength = this.length();
    boolean fails = false;
    /*
     * I want this code to go through the string, 
     * once it finds the matching first character,
     * its going to keep going until pattern.length.
     * Then it sets the current to that position
     * and the replaces whatever is there. 
     * 
     * 
     * thing is, i need it to reset afterwards current will now be at a new place 
     */
    iterator: while (current < upperBound)
      {
        //System.out.println("within while1");
        checker: while (fails == false)
          {
            if (i == pattern.length())
              {
                //System.out.println("within if");
                if (pattern.length() < replacement.length()) //the replacement is smaller than original
                  {
                    if ((this.length() + diff) > limit)
                      {
                        this.size =
                            computeNeededCapacity(pattern.length()
                                                  + replacement.length());
                        for (i = current; i <= upperBound; i++) //move everything down by diff
                          {
                            this.contents[i + diff] = this.contents[i];
                          }
                        for (i = current - diff; i < current; i++) // put the replacement in
                          {
                            this.contents[i] = replacement.charAt(j);
                            j++;
                          }
                      }
                    //set variables
                    j = 0;
                    current = current + diff;
                    upperBound = upperBound + diff;
                  }//if
                else
                  {
                    if (pattern.length() == replacement.length()) // replacement equal to pattern
                      {
                        //System.out.println("replacement equal to pattern");
                        for (i = current - diff; i < current; i++)
                          {
                            this.contents[i] = replacement.charAt(j);
                            j++;
                          }
                        j = 0;
                      }//else if
                    else
                      {
                        if (pattern.length() > replacement.length()) //replacement greater than pattern
                          {
                            for (i = limit; i >= current; i--)
                              {
                                this.contents[i - diff] = this.contents[i];
                              }
                            for (i = lowerBound; i < current; i++)
                              {
                                this.contents[i] = replacement.charAt(i);
                              } //else if
                          } // else if
                      }
                  }
              }
            else
              {
                System.out.println("current " + current + " its on: "
                                   + this.contents[current]);
                System.out.println("full string: " + this.toString());
                if (this.charAt(current) == pattern.charAt(i))
                  {
                    fails = false;
                    i++;
                  }
                else
                  fails = true;
                current++;
              }
          }
        //reset variables
        fails = false;
        i = 0;

      } // replace(String, String)
  } // class BasicMutableString
}
