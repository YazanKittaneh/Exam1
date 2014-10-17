package PredicateMaker;
import static org.junit.Assert.*;

import org.junit.Test;

import java.util.function.Predicate;

/**
 * Tests of various predicates.
 *
 * @author Samuel A. Rebelsky
 */
public class PredicateTests
{
  // +---------+-----------------------------------------------------------
  // | Globals |
  // +---------+

  /**
   * A predicate that checks if its parameter is null.
   */
  public static Predicate<Object> isNull = (o) -> (o == null);

  /**
   * A predicate that checks if its parameter is even.
   */
  public static Predicate<Integer> isEven = (i) -> (i % 2) == 0;

  /**
   * A predicate that checks if its parameter is a multiple of 3.
   */
  public static Predicate<Integer> isMultipleOf3 = (i) -> (i % 3) == 0;

  /**
   * A predicate that checks if its parameter has a single digit.
   */
  public static Predicate<Integer> isSingleDigit = (i) -> (i >= 0) && (i <= 9);

  /**
   * A predicate that checks if its parameter is a string of length
   * at least three
   */
  public static Predicate<String> isLengthThreeOrMore =
      (s) -> (s.length() >= 3);

  /**
   * A predicate that checks if character 2 of a string is a vowel
   */
  public static Predicate<String> isVowelChar2 = (s) ->
    {
      switch (s.charAt(2))
        {
          case 'a':
          case 'e':
          case 'i':
          case 'o':
          case 'u':
            return true;
          default:
            return false;
        } // switch
  }; // isCharTwoVowel

  /**
   * A predicate that checks if its parameter contains the letter
   * s (or S).
   */
  public static Predicate<String> containsS = (s) -> (s.indexOf('s') >= 0)
                                                     || (s.indexOf('S') >= 0);

  // +-----------+---------------------------------------------------------
  // | Utilities |
  // +-----------+

  /**
   * Count how many values in an array meet some predicate.
   */
  public static <T> int tally(T[] values, Predicate<T> pred)
  {
    int result = 0;
    for (int i = 0; i < values.length; i++)
      {
        if (pred.test(values[i]))
          result += 1;
      } // for
    return result;
  } // tally(T[], Predicate<T>

  // +---------------+-----------------------------------------------------
  // | Generic Tests |
  // +---------------+

  /**
   * Check if a PredicateMaker successfully combines no tests.
   */
  @SuppressWarnings({ "unchecked" })
  public static void testAndOfNothing(PredicateMaker<Object> pm)
  {
    Object[] vals = { 0, 1, 2, 3 };
    assertEquals(4, tally(vals, pm.and(new Predicate[] {})));
  } // testAndOfNothing(PredicateMaker<?>)

  /**
   * Check if a PredicateMaker successfully combines some integer predicates.
   */
  @SuppressWarnings({ "unchecked" })
  public static void testAndOfIntegerPredicates(PredicateMaker<Integer> pm)
  {
    Integer[] vals = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 }; 
    Integer[] more =
        { 0, null, 2, null, null, 5, 6, null, null, 9, 10, 11, null };

    assertEquals("vals: Evens", 7,
                 tally(vals, pm.and(new Predicate[] { isEven })));
    assertEquals("vals: Multiples of 3", 5,
                 tally(vals, pm.and(new Predicate[] { isMultipleOf3 })));
    assertEquals("vals: Even multiples of 3", 3,
                 tally(vals, pm.and(new Predicate[] { isEven, isMultipleOf3 })));
    assertEquals("vals: Single-digit even multiples of 3",
                 2,
                 tally(vals,
                       pm.and(new Predicate[] { isEven, isMultipleOf3,
                                               isSingleDigit })));

    assertEquals("more: Nulls", 6,
                 tally(more, pm.and(new Predicate[] { isNull })));
    assertEquals("more: Non-nulls", 7,
                 tally(more, pm.and(new Predicate[] { isNull.negate() })));

    assertEquals("more: Even",
                 4,
                 tally(more,
                       pm.and(new Predicate[] { isNull.negate(), isEven })));
    assertEquals("more: Multiples of 3",
                 3,
                 tally(more,
                       pm.and(new Predicate[] { isNull.negate(), isMultipleOf3 })));
    assertEquals("more: Even multiples of 3",
                 2,
                 tally(more,
                       pm.and(new Predicate[] { isNull.negate(), isEven,
                                               isMultipleOf3 })));
    assertEquals("more: Single-digit even multiples of 3",
                 2,
                 tally(more,
                       pm.and(new Predicate[] { isNull.negate(), isEven,
                                               isMultipleOf3, isSingleDigit })));
  } // testAndOfIntegerPredicates(PredicateMaker<Integer>)

  /**
   * Tests of combinations fo string predicates.
   */
  @SuppressWarnings("unchecked")
  public static void testAndOfStringPredicates(PredicateMaker<String> pm)
  {
    String[] strings =
        { "she", "he", null, "tree", "trees", null, "pizza", "zebra", "zebras",
         "stripes", "I", "am", "Sam", "is" };

    assertEquals("Contains s",
                 6,
                 tally(strings,
                       pm.and(new Predicate[] { isNull.negate(), containsS })));
    assertEquals("Length at least 3",
                 8,
                 tally(strings,
                       pm.and(new Predicate[] { isNull.negate(),
                                               isLengthThreeOrMore })));
    assertEquals("Length at least 3, char 2 is vowel",
                 3,
                 tally(strings,
                       pm.and(new Predicate[] { isNull.negate(),
                                               isLengthThreeOrMore,
                                               isVowelChar2 })));
    assertEquals("Length at least 3, char 2 is vowel, contains s",
                 2,
                 tally(strings,
                       pm.and(new Predicate[] { isNull.negate(),
                                               isLengthThreeOrMore,
                                               isVowelChar2, containsS })));
  } // testAndOfStringPredicates

  // +-------+-------------------------------------------------------------
  // | Tests |
  // +-------+

  /**
   * Test PredicateMakers implemented with Anonymous Inner Classes.
   */
  @Test
  public void testPredicateMakerAIC()
  {
    testAndOfNothing(new PredicateMakerAIC<Object>());
    testAndOfIntegerPredicates(new PredicateMakerAIC<Integer>());
    testAndOfStringPredicates(new PredicateMakerAIC<String>());
  } // testPredicateMakerAIC
  
  /**
   * Test PredicateMakers implemented with Lambda Expressions.
   */
  @Test
  public void testPredicateMakerLambda()
  {
    testAndOfNothing(new PredicateMakerLambda<Object>());
    testAndOfIntegerPredicates(new PredicateMakerLambda<Integer>());
    testAndOfStringPredicates(new PredicateMakerLambda<String>());
  } // testPredicateMakerLambda

  /**
   * Test PredicateMakers implements with no anonymity.
   */
  @Test
  public void testPredicateMakerAlt()
  {
    testAndOfNothing(new PredicateMakerAlt<Object>());
    testAndOfIntegerPredicates(new PredicateMakerAlt<Integer>());
    testAndOfStringPredicates(new PredicateMakerAlt<String>());
  } // testPredicateMakerAlt
} // class PredicateTests
