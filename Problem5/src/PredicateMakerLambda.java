import java.util.function.Predicate;

/**
 * Things that make new predicates using lambda expressions.
 *
 * @author Ann
 * @author Nina
 * @author Mus
 */
public class PredicateMakerLambda<T>
  implements PredicateMaker<T>
{
  /**
   * Create a new predicate that holds only when all of the values
   * in preds hold.
   */
  public Predicate<T> and(Predicate<T>[] preds)
  {
    // STUB!
    return (val) -> { return true; };
  } // and(Predicate<T>[])
} // class PredicateMakerLambda<T>
