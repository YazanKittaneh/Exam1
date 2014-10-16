package Average;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * A quick experiment with MathUtils.average, intended primarily as
 * a mechanism for getting to the debugger.
 */
public class AverageExpt
{
  /**
   * Conduct one experiment, computing the average and printing
   * the value.
   */
  public static void expt(PrintWriter pen, long[] vals)
  {
    try
      {
        pen.println("average(" + Arrays.toString(vals) + ") = " +
                    MathUtils.average(vals));
      } // try
    catch (Exception e)
      {
        pen.println("average(" + Arrays.toString(vals) + ") threw " +
                    e.toString());
      } // catch (Exception)
  } // expt(PrintWriter, long[])

  public static void main(String[] args)
  {
    PrintWriter pen = new PrintWriter(System.out, true);
    expt(pen, new long[] { -5 });
    expt(pen, new long[] { 1,2,3,4,5 });
    pen.close();
  } // main(String[])
} // class AverageExpt
