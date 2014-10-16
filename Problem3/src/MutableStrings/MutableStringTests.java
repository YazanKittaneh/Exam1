package MutableStrings;
import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Random;

/**
 * A variety of tests of the MutableString class.  These tests are
 * flawed in that each method is tested separately, but the methods
 * are not tested together.
 * 
 * @author Samuel A. Rebelsky
 */
public class MutableStringTests
{
  // +-----------+---------------------------------------------------------
  // | Utilities |
  // +-----------+

  /**
   * Check that each of the characters in a mutable string is the
   * same as each of the characters in a non-mutable string.
   */
  public void checkChars(String msg, String str, MutableString mut)
  {
    if (str.length() != mut.length())
      {
        fail(msg + " (strings have different lengths): " + 
             str.length() + " vs. " + mut.length());
      } // if the lengths differ
    for (int i = 0; i < str.length(); i++)
      {
        if (str.charAt(i) != mut.charAt(i))
          {
            fail(msg + " (mismatch at char " + i + "): " +
                 "'" + str.charAt(i) + "' vs '" + mut.charAt(i) + "'");
          } // if the characters don't match
      } // for i
  } // checkChars(String, String, MutableString)

  /**
   * Put quotation marks around a string.
   */
  public String quote(String str)
  {
    return "\"" + str + "\"";
  } // quote(String)

  /**
   * Test the remove method by repeatedly deleting sections of str..
   */
  public void testRemove(String str)
  {
    Random gen = new Random();
    MutableString mut = new BasicMutableString(str);
    StringBuffer buf = new StringBuffer(str);
    int len;
    while ((len = buf.length()) != 0)
      {
        // Compute a random number in the range [0 .. len)
        int start = gen.nextInt(len); 
        // Compute a random number in the range [start .. len]
        int end = start + gen.nextInt(len + 1 - start);  
        // Remember the old versions for error messages
        String save = buf.toString();
        // Remove from both mutable and buffer
        mut.remove(start, end);
        buf.delete(start, end);
        if (! mut.toString().equals(buf.toString())) 
          {
            fail("Removing characters " + start + " to " + end + " from " +
                 quote(save) + ", buf=" + quote(buf.toString()) + ", mut = " +
                 quote(mut.toString()));
          } // fail
      } // while
  } // testRemove(String)

  /**
   * Test the replace method by repeatedly replacing assorted substrings.
   */
  public void testReplace(String str)
  {
    Random gen = new Random();
    String tmp = str;
    MutableString mut = new BasicMutableString(str);

    for (int i = 0; i < 10; i++)
      {
        int len = tmp.length();
        // Compute a random number in the range [0 .. len)
        int start = gen.nextInt(len); 
        // Compute a random number in the range [start+1 .. len]
        int end = start + 1 + gen.nextInt(len - start);  
        // Grab the substring from that portion.  That's our pattern.
        String pattern = tmp.substring(start, end);
        // Our replacement is somewhat boring b/c I'm lazy
        String replacement = "abbabbba";
        // Save the old version of the string for error messages
        String save = tmp;

        // Update both versions
        tmp = tmp.replace(pattern, replacement);
        mut.replace(pattern, replacement);

        if (! tmp.equals(mut.toString())) 
          {
            fail(save + ".replace(" + quote(pattern) + ", " + 
                 quote(replacement) + ")" + " should yield " + 
                 quote(tmp) + " but gave " + quote(mut.toString()));
          } // if they don't match
      } // for
  } // testReplace

  // +-------+-------------------------------------------------------------
  // | Tests |
  // +-------+

  /**
   * A few simple tests, mostly of charAt and length.
   */
  @Test
  public void test()
  {
    checkChars("string a", "a", new BasicMutableString("a"));
    checkChars("string ab", "ab", new BasicMutableString("ab"));
    checkChars("empty/1", "", new BasicMutableString(""));
    checkChars("empty/2", "", new BasicMutableString());
  } // test()

  /**
   * Tests of the append method.  (Also checks charAt and length.)
   */
  @Test
  public void testAppend()
  {
    MutableString mut = new BasicMutableString();
    assertEquals("Empty string", "", mut.toString());
    mut.append("alpha");
    assertEquals("append(alpha)", "alpha", mut.toString());
    mut.append("beta");
    assertEquals("append(beta)", "alphabeta", mut.toString());
    mut.append("");
    assertEquals("append(<empty>)", "alphabeta", mut.toString());
    mut.append("gamma");
    assertEquals("append(gamma)", "alphabetagamma", mut.toString());

    checkChars("testAppend", "alphabetagamma", mut);
  } // testAppend

  /**
   * Tests of the prepend method, only prepending at char 0.
   */
  @Test
  public void testPrepend0()
  {
    MutableString mut = new BasicMutableString();
    assertEquals("Empty string", "", mut.toString());
    mut.prepend(0,"alpha");
    assertEquals("prepend(0,alpha)", "alpha", mut.toString());
    mut.prepend(0,"beta");
    assertEquals("prepend(0,beta)", "betaalpha", mut.toString());
    mut.prepend(0,"");
    assertEquals("prepend(0,<empty>)", "betaalpha", mut.toString());
    mut.prepend(0,"gamma");
    assertEquals("prepend(0,gamma)", "gammabetaalpha", mut.toString());

    checkChars("testPrepend", "gammabetaalpha", mut);
  } // testPrepend0

  /**
   * More tests of the prepend method.
   */
  @Test
  public void testPrepend()
  {
    MutableString mut = new BasicMutableString("alphabet");
    mut.prepend(8, "X");
    assertEquals("prepend(8,X)", "alphabetX", mut.toString());
    mut.prepend(7, "X");
    assertEquals("prepend(7,X)", "alphabeXtX", mut.toString());
    mut.prepend(6, "X");
    assertEquals("prepend(6,X)", "alphabXeXtX", mut.toString());
    mut.prepend(5, "X");
    assertEquals("prepend(5,X)", "alphaXbXeXtX", mut.toString());
    mut.prepend(4, "X");
    assertEquals("prepend(4,X)", "alphXaXbXeXtX", mut.toString());
    mut.prepend(3, "X");
    assertEquals("prepend(3,X)", "alpXhXaXbXeXtX", mut.toString());
    mut.prepend(2, "X");
    assertEquals("prepend(2,X)", "alXpXhXaXbXeXtX", mut.toString());
    mut.prepend(1, "X");
    assertEquals("prepend(1,X)", "aXlXpXhXaXbXeXtX", mut.toString());
    mut.prepend(0, "X");
    assertEquals("prepend(0,X)", "XaXlXpXhXaXbXeXtX", mut.toString());

    mut = new BasicMutableString("alphabet");
    assertEquals("alphabet", "alphabet", mut.toString());
    mut.prepend(5, "--");
    assertEquals("mut.prepend(5,--)", "alpha--bet", mut.toString());
    mut.prepend(6, "gamma");
    assertEquals("mut.prepend(6,gamma)", "alpha-gamma-bet", mut.toString());
  } // testPrepend

  /**
   * Test the remove method.
   */
  @Test
  public void testRemove()
  {
    for (int i = 0; i < 10; i++)
      {
        testRemove("a");
        testRemove("abcde");
        testRemove("alphabetical");
      } // for
  } // testRemove()

  /**
   * Test the replace method.
   */
  @Test
  public void testReplace()
  {
    for (int i = 0; i < 10; i++)
      {
        testReplace("aaaaaabbbbbbb");
        testReplace("alphabetalphabetalphabet");
      } // for
  } // testReplace

} // class MutableStringTests
