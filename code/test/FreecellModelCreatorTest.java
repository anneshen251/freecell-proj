import cs3500.freecell.model.FreecellModelCreator;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.model.hw04.MultiMoveFreecellModel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class for FreecellModelCreator.
 */
public class FreecellModelCreatorTest {
  FreecellModelCreator creator = new FreecellModelCreator();
  SimpleFreecellModel simple = new SimpleFreecellModel();
  MultiMoveFreecellModel multi = new MultiMoveFreecellModel();

  @Test
  public void produceSimpleModel() {
    assertEquals(simple.getClass(),
        creator.create(FreecellModelCreator.GameType.SINGLEMOVE).getClass());
  }

  @Test
  public void produceMultiMoveModel() {
    assertEquals(multi.getClass(),
        creator.create(FreecellModelCreator.GameType.MULTIMOVE).getClass());
  }

  @Test(expected = IllegalArgumentException.class)
  public void typeGivenIsNull() {
    creator.create(null);
  }

}
