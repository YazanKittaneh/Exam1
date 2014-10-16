package PartialSort;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * Some simple experiments with pseudo-sort.
 *
 * @author Samuel A. Rebelsky
 */
public class Experiments
{
  // +---------+-----------------------------------------------------
  // | Globals |
  // +---------+

  /**
   * Sam's easy comparator.  It's type unsafe and uses raw types, but I 
   * don't care.
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public static Comparator<Comparable> ezcompare = 
    (left,right) -> left.compareTo(right);

  /**
   * A random number generator.
   */
  public static Random random = new Random();

  // +-----------+---------------------------------------------------
  // | Functions |
  // +-----------+

  /**
   * Run a single experiment: Psueodsort using a particular element.
   */
  public static void experiment(PrintWriter pen, Comparable<?> val, 
      Comparable<?>[] values)
  {
    // Print original info
    pen.println("--------------------------------------------------");
    pen.println("old values: " + Arrays.toString(values));
    pen.println("val:        " + val.toString());

    // Sort! (more or less)
    int[] boundaries = Utils.pseudoSort(val, values, ezcompare);

    // Print results
    pen.println("boundaries: " + Arrays.toString(boundaries));
    pen.println("new values: " + Arrays.toString(values));

    // Check results
    for (int i = 0; i < boundaries[0]; i++)
      {
        if (ezcompare.compare(values[i], val) >= 0)
          {
            pen.println("  ERROR: values[" + i + "] (" + values[i] + ") "
                + "is not less than " + val);
          } // if
      } // for
    for (int i = boundaries[0]; i < boundaries[1]; i++)
      {
        if (ezcompare.compare(values[i], val) != 0)
          {
            pen.println("  ERROR: values[" + i + "] (" + values[i] + ") "
                + "is not equal to " + val);
          } // if
      } // for
    for (int i = boundaries[1]; i < values.length; i++)
      {
        if (ezcompare.compare(values[i], val) <= 0)
          {
            pen.println("  ERROR: values[" + i + "] (" + values[i] + ") "
                + "is not greater than " + val);
          } // if
      } // for

    // Print a helpful separator
    pen.println();
  } // experiment(PrintWriter, Comparable, Comparable[])

  /**
   * Run n randomized experiments: For each experiment, shuffle the vector,
   * pick a random element of the vector and pseudosort with that element.
   */
  public static void randomizedExperiments(int n, PrintWriter pen, 
      Comparable<?>[] values)
  {
    for (int i = 0; i < n; i++)
      {
        shuffle(values);
        experiment(pen, values[Math.abs(random.nextInt()) % values.length], 
            values);
      } // for
  } // randomizedExperiment

  /**
   * Shuffle the elements of an array.
   */
  public static void shuffle(Comparable<?>[] values)
  {
    for (int i = 0; i < values.length; i++)
      {
        swap(values, i, Math.abs(random.nextInt()) % values.length);
      } // for
  } // shuffle(Coparable[])

  /**
   * Swap two elements of an array.
   */
  public static void swap(Comparable<?>[] values, int i, int j)
  {
    Comparable<?> tmp = values[i];
    values[i] = values[j];
    values[j] = tmp;
  } // swap(Comparable[], int, int)

  // +------+--------------------------------------------------------
  // | Main |
  // +------+
  /**
   * Run all of the experiments.
   */
  public static void main(String[] args)
  {
    PrintWriter pen = new PrintWriter(System.out, true);

    randomizedExperiments(5, pen, new Integer[] { 1, 2, 3, 4, 5, 4, 3, 2, 1 });
    randomizedExperiments(5, pen, new String[] { "twas", "brillig", "and", 
        "the", "slithy", "toves", "did", "gyre", "and", "gimble", "in", 
        "the", "wabe", "all", "mimsy", "were", "the", "borogoves" });

    pen.close();
  } // main(String[])
} // Experiments
