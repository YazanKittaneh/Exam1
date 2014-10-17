package PredicateMaker;
import java.util.function.Predicate;

/**
 * Things that make new predicates.
 *
 * @author Con
 * @author Joy N.
 */
public interface PredicateMaker<T>
{
  /**
   * Create a new predicate that holds only when all of the values
   * in preds hold.
   */
  public Predicate<T> and(Predicate<T>[] preds);
} // interface PredicateMaker<T>
