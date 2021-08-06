import cs3500.freecell.controller.FreecellController;
import cs3500.freecell.controller.SimpleFreecellController;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.model.hw04.MultiMoveFreecellModel;
import org.junit.Test;

import java.io.PipedWriter;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;

/**
 * Tests for FreecellControllers.
 */
public abstract class AbstractFreecellControllerTest {
  StringBuilder log = new StringBuilder();
  MockFreecellModel mockModel = new MockFreecellModel(log);

  /**
   * Constructs an instance of the class under test representing a FreecellController.
   *
   * @param rd the given readable
   */
  protected abstract FreecellController generateFreecellController(Readable rd);

  /**
   * Concrete class for testing FreecellController using the SimpleFreecellModel.
   */
  public static final class SimpleFreecellCellControllerTest
      extends AbstractFreecellControllerTest {
    /**
     * Constructs an instance of the class under test representing a FreecellController.
     *
     * @param rd the given readable
     */
    @Override
    protected FreecellController generateFreecellController(Readable rd) {
      return new SimpleFreecellController(new SimpleFreecellModel(), rd, log);
    }
  }

  /**
   * Concrete class for testing FreecellController using the MultiFreecellModel.
   */
  public static final class MultiFreecellControllerTest extends AbstractFreecellControllerTest {

    /**
     * Constructs an instance of the class under test representing a FreecellController.
     *
     * @param rd the given readable
     */
    @Override
    protected FreecellController generateFreecellController(Readable rd) {
      return new SimpleFreecellController(new MultiMoveFreecellModel(), rd, log);
    }

  }

  @Test(expected = IllegalArgumentException.class)
  public void makeControllerWithNullModel() {
    StringReader in = new StringReader("q");
    SimpleFreecellController controller = new SimpleFreecellController(null, in, log);
  }

  @Test(expected = IllegalArgumentException.class)
  public void makeControllerWithNullReadable() {
    SimpleFreecellController controller = new SimpleFreecellController(mockModel, null, log);
  }

  @Test(expected = IllegalArgumentException.class)
  public void makeControllerWithNullAppendable() {
    StringReader in = new StringReader("q");
    SimpleFreecellController controller = new SimpleFreecellController(mockModel, in, null);
  }


  @Test
  public void testRegularStartGame() {
    StringReader in = new StringReader("q");
    SimpleFreecellController controller = new SimpleFreecellController(mockModel, in, log);
    controller.playGame(mockModel.getDeck(), 7, 3, false);
    assertEquals("deck = [A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦," +
        " 10♦, J♦, Q♦, K♦, A♣, 2♣, 3♣," +
        " 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣, A♠, 2♠, 3♠, 4♠, 5♠, 6♠, " +
        "7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠, A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, " +
        "10♥, J♥, Q♥, K♥]\n" +
        "numCascade = 7\n" +
        "numOpen = 3\n" +
        "shuffle = false\n" +
        "\n" +
        "Game quit prematurely.", log.toString());
  }

  @Test
  public void testStartGameInvalidCascade() {
    StringReader in = new StringReader("q");
    generateFreecellController(in).playGame(mockModel.getDeck(), 3, 3, false);
    assertEquals("Could not start game.", log.toString());
  }

  @Test
  public void testStartGameInvalidOpen() {
    StringReader in = new StringReader("q");
    generateFreecellController(in).playGame(mockModel.getDeck(), 4, 0, false);
    assertEquals("Could not start game.", log.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullDeckStartGame() {
    StringReader in = new StringReader("q");
    generateFreecellController(in).playGame(null, 4, 3, false);
  }

  @Test
  public void testQuitLowerCase() {
    StringReader in = new StringReader("q");
    SimpleFreecellController controller = new SimpleFreecellController(mockModel, in, log);
    controller.playGame(mockModel.getDeck(), 4, 3, false);
    assertEquals("deck = [A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦" +
        ", 10♦, J♦, Q♦, K♦, A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣" +
        ", Q♣, K♣, A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠, A♥, 2♥," +
        " 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥]\n" +
        "numCascade = 4\n" +
        "numOpen = 3\n" +
        "shuffle = false\n" +
        "\n" +
        "Game quit prematurely.", log.toString());
  }

  @Test
  public void testQuitUpperCase() {
    StringReader in = new StringReader("Q");
    SimpleFreecellController controller = new SimpleFreecellController(mockModel, in, log);
    controller.playGame(mockModel.getDeck(), 4, 3, false);
    assertEquals("deck = [A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦" +
        ", 10♦, J♦, Q♦, K♦, A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣" +
        ", Q♣, K♣, A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠, A♥, 2♥," +
        " 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥]\n" +
        "numCascade = 4\n" +
        "numOpen = 3\n" +
        "shuffle = false\n" +
        "\n" +
        "Game quit prematurely.", log.toString());
  }

  @Test
  public void testQuitSeparatedBySpace() {
    StringReader in = new StringReader("C1 4 Q F2");
    SimpleFreecellController controller = new SimpleFreecellController(mockModel, in, log);
    controller.playGame(mockModel.getDeck(), 4, 3, false);
    assertEquals("deck = [A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦" +
        ", 10♦, J♦, Q♦, K♦, A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣" +
        ", Q♣, K♣, A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠, A♥, 2♥," +
        " 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥]\n" +
        "numCascade = 4\n" +
        "numOpen = 3\n" +
        "shuffle = false\n" +
        "\n" +
        "Game quit prematurely.", log.toString());
  }

  @Test
  public void testQuitSeperatedByLine() {
    StringReader in = new StringReader("C2 5 \n q 4");
    SimpleFreecellController controller = new SimpleFreecellController(mockModel, in, log);
    controller.playGame(mockModel.getDeck(), 4, 3, false);
    assertEquals("deck = [A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦" +
        ", 10♦, J♦, Q♦, K♦, A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣" +
        ", Q♣, K♣, A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠, A♥, 2♥," +
        " 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥]\n" +
        "numCascade = 4\n" +
        "numOpen = 3\n" +
        "shuffle = false\n" +
        "\n" +
        "Game quit prematurely.", log.toString());
  }

  @Test
  public void multipleQuits() {
    StringReader in = new StringReader("Q q Q q");
    SimpleFreecellController controller = new SimpleFreecellController(mockModel, in, log);
    controller.playGame(mockModel.getDeck(), 4, 3, false);
    assertEquals("deck = [A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦" +
        ", 10♦, J♦, Q♦, K♦, A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣" +
        ", Q♣, K♣, A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠, A♥, 2♥," +
        " 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥]\n" +
        "numCascade = 4\n" +
        "numOpen = 3\n" +
        "shuffle = false\n" +
        "\n" +
        "Game quit prematurely.", log.toString());
  }

  @Test
  public void QNotSeparatedBySpace() {
    StringReader in = new StringReader("C1Q q");
    SimpleFreecellController controller = new SimpleFreecellController(mockModel, in, log);
    controller.playGame(mockModel.getDeck(), 4, 3, false);
    assertEquals("deck = [A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10" +
        "♦, J♦, Q♦, K♦, A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣," +
        " A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠, A♥, 2♥, 3♥, 4♥, 5" +
        "♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥]\n" +
        "numCascade = 4\n" +
        "numOpen = 3\n" +
        "shuffle = false\n" +
        "\n" +
        "source pile invalid, please try again.\n" +
        "Game quit prematurely.", log.toString());
  }

  @Test
  public void QasSourcePile() {
    StringReader in = new StringReader("q 4 C1");
    SimpleFreecellController controller = new SimpleFreecellController(mockModel, in, log);
    controller.playGame(mockModel.getDeck(), 4, 3, false);
    assertEquals("deck = [A♦, 2♦, 3♦, 4♦, 5♦, 6♦" +
        ", 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦, A♣, 2♣, 3♣, 4♣, " +
        "5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣, A♠, 2♠, " +
        "3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠, " +
        "A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥," +
        " Q♥, K♥]\n" +
        "numCascade = 4\n" +
        "numOpen = 3\n" +
        "shuffle = false\n" +
        "\n" +
        "Game quit prematurely.", log.toString());
  }

  @Test
  public void QasCardIndex() {
    StringReader in = new StringReader("C1 q C2");
    SimpleFreecellController controller = new SimpleFreecellController(mockModel, in, log);
    controller.playGame(mockModel.getDeck(), 4, 3, false);
    assertEquals("deck = [A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦," +
        " 8♦, 9♦, 10♦, J♦, Q♦, K♦, A♣, 2♣, 3♣, 4♣, 5♣, 6♣, " +
        "7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣, A♠, 2♠, 3♠, 4♠, 5♠, " +
        "6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠, A♥, 2♥, 3♥, 4♥, " +
        "5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥]\n" +
        "numCascade = 4\n" +
        "numOpen = 3\n" +
        "shuffle = false\n" +
        "\n" +
        "Game quit prematurely.", log.toString());
  }

  @Test
  public void QasDestPile() {
    StringReader in = new StringReader("C1 11 q");
    SimpleFreecellController controller = new SimpleFreecellController(mockModel, in, log);
    controller.playGame(mockModel.getDeck(), 4, 3, false);
    assertEquals("deck = [A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, " +
        "8♦, 9♦, 10♦, J♦, Q♦, K♦, A♣, 2♣, 3♣, 4♣, 5♣, 6♣," +
        " 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣, A♠, 2♠, 3♠, 4♠, 5♠," +
        " 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠, A♥, 2♥, 3♥, 4♥," +
        " 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥]\n" +
        "numCascade = 4\n" +
        "numOpen = 3\n" +
        "shuffle = false\n" +
        "\n" +
        "Game quit prematurely.", log.toString());
  }

  @Test
  public void testMoveC1toF4withMock() {
    StringReader in = new StringReader("C1 5 F4 q");
    SimpleFreecellController controller = new SimpleFreecellController(mockModel, in, log);
    controller.playGame(mockModel.getDeck(), 6, 3, false);
    assertEquals("deck = [A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦" +
        ", K♦, A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣, A♠, 2♠, 3♠, 4♠" +
        ", 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠, A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, " +
        "10♥, J♥, Q♥, K♥]\n" +
        "numCascade = 6\n" +
        "numOpen = 3\n" +
        "shuffle = false\n" +
        "\n" +
        "source = CASCADE\n" +
        "pileNumber = 0\n" +
        "cardIndex = 4\n" +
        "destination = FOUNDATION\n" +
        "destPileNumber = 3\n" +
        "\n" +
        "Game quit prematurely.", log.toString());
  }

  @Test
  public void testGameOverTrue() {
    String temp = "";
    for (int i = 1; i <= 13; i++) {
      temp += "C" + i + " 1" + " F1\n";
    }
    for (int i = 14; i <= 26; i++) {
      temp += "C" + i + " 1" + " F2\n";
    }
    for (int i = 27; i <= 39; i++) {
      temp += "C" + i + " 1" + " F3\n";
    }
    for (int i = 40; i <= 52; i++) {
      temp += "C" + i + " 1" + " F4\n";
    }
    StringReader in = new StringReader(temp);
    generateFreecellController(in).playGame(mockModel.getDeck(), 52, 4, false);
    assertEquals("Game over.",
        log.substring(log.toString().lastIndexOf("\n")).stripLeading());
  }

  @Test
  public void testGameOverTrueWithSomeBadSourceInputs() {
    String temp = "";
    for (int i = 1; i <= 13; i++) {
      temp += "C" + i + " 1" + " F1\n";
    }
    for (int i = 14; i <= 26; i++) {
      temp += "C" + i + " 1" + " F2\n";
    }
    temp += " 33 ";
    temp += " -22 ";
    temp += " zz ";
    for (int i = 27; i <= 39; i++) {
      temp += "C" + i + " 1" + " F3\n";
    }
    temp += " r4 ";
    for (int i = 40; i <= 52; i++) {
      temp += "C" + i + " 1" + " F4\n";
    }
    StringReader in = new StringReader(temp);
    generateFreecellController(in).playGame(mockModel.getDeck(), 52, 4, false);
    assertEquals("Game over.",
        log.substring(log.toString().lastIndexOf("\n")).stripLeading());
  }

  @Test
  public void invalidMoveWrongCardIndex() {
    StringReader in = new StringReader("C1 2 F4 q");
    generateFreecellController(in).playGame(mockModel.getDeck(), 52, 3, false);
    assertEquals("F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "C1: A♦\n" +
        "C2: 2♦\n" +
        "C3: 3♦\n" +
        "C4: 4♦\n" +
        "C5: 5♦\n" +
        "C6: 6♦\n" +
        "C7: 7♦\n" +
        "C8: 8♦\n" +
        "C9: 9♦\n" +
        "C10: 10♦\n" +
        "C11: J♦\n" +
        "C12: Q♦\n" +
        "C13: K♦\n" +
        "C14: A♣\n" +
        "C15: 2♣\n" +
        "C16: 3♣\n" +
        "C17: 4♣\n" +
        "C18: 5♣\n" +
        "C19: 6♣\n" +
        "C20: 7♣\n" +
        "C21: 8♣\n" +
        "C22: 9♣\n" +
        "C23: 10♣\n" +
        "C24: J♣\n" +
        "C25: Q♣\n" +
        "C26: K♣\n" +
        "C27: A♠\n" +
        "C28: 2♠\n" +
        "C29: 3♠\n" +
        "C30: 4♠\n" +
        "C31: 5♠\n" +
        "C32: 6♠\n" +
        "C33: 7♠\n" +
        "C34: 8♠\n" +
        "C35: 9♠\n" +
        "C36: 10♠\n" +
        "C37: J♠\n" +
        "C38: Q♠\n" +
        "C39: K♠\n" +
        "C40: A♥\n" +
        "C41: 2♥\n" +
        "C42: 3♥\n" +
        "C43: 4♥\n" +
        "C44: 5♥\n" +
        "C45: 6♥\n" +
        "C46: 7♥\n" +
        "C47: 8♥\n" +
        "C48: 9♥\n" +
        "C49: 10♥\n" +
        "C50: J♥\n" +
        "C51: Q♥\n" +
        "C52: K♥\n" +
        "invalid move, try again: invalid card index\n" +
        "Game quit prematurely.", log.toString());
  }

  @Test
  public void invalidMoveThenRightMove() {
    StringReader in = new StringReader("C1 2 F4 C14 1 F4 q");
    generateFreecellController(in).playGame(mockModel.getDeck(), 52, 3, false);
    assertEquals("F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "C1: A♦\n" +
        "C2: 2♦\n" +
        "C3: 3♦\n" +
        "C4: 4♦\n" +
        "C5: 5♦\n" +
        "C6: 6♦\n" +
        "C7: 7♦\n" +
        "C8: 8♦\n" +
        "C9: 9♦\n" +
        "C10: 10♦\n" +
        "C11: J♦\n" +
        "C12: Q♦\n" +
        "C13: K♦\n" +
        "C14: A♣\n" +
        "C15: 2♣\n" +
        "C16: 3♣\n" +
        "C17: 4♣\n" +
        "C18: 5♣\n" +
        "C19: 6♣\n" +
        "C20: 7♣\n" +
        "C21: 8♣\n" +
        "C22: 9♣\n" +
        "C23: 10♣\n" +
        "C24: J♣\n" +
        "C25: Q♣\n" +
        "C26: K♣\n" +
        "C27: A♠\n" +
        "C28: 2♠\n" +
        "C29: 3♠\n" +
        "C30: 4♠\n" +
        "C31: 5♠\n" +
        "C32: 6♠\n" +
        "C33: 7♠\n" +
        "C34: 8♠\n" +
        "C35: 9♠\n" +
        "C36: 10♠\n" +
        "C37: J♠\n" +
        "C38: Q♠\n" +
        "C39: K♠\n" +
        "C40: A♥\n" +
        "C41: 2♥\n" +
        "C42: 3♥\n" +
        "C43: 4♥\n" +
        "C44: 5♥\n" +
        "C45: 6♥\n" +
        "C46: 7♥\n" +
        "C47: 8♥\n" +
        "C48: 9♥\n" +
        "C49: 10♥\n" +
        "C50: J♥\n" +
        "C51: Q♥\n" +
        "C52: K♥\n" +
        "invalid move, try again: invalid card index\n" +
        "F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4: A♣\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "C1: A♦\n" +
        "C2: 2♦\n" +
        "C3: 3♦\n" +
        "C4: 4♦\n" +
        "C5: 5♦\n" +
        "C6: 6♦\n" +
        "C7: 7♦\n" +
        "C8: 8♦\n" +
        "C9: 9♦\n" +
        "C10: 10♦\n" +
        "C11: J♦\n" +
        "C12: Q♦\n" +
        "C13: K♦\n" +
        "C14:\n" +
        "C15: 2♣\n" +
        "C16: 3♣\n" +
        "C17: 4♣\n" +
        "C18: 5♣\n" +
        "C19: 6♣\n" +
        "C20: 7♣\n" +
        "C21: 8♣\n" +
        "C22: 9♣\n" +
        "C23: 10♣\n" +
        "C24: J♣\n" +
        "C25: Q♣\n" +
        "C26: K♣\n" +
        "C27: A♠\n" +
        "C28: 2♠\n" +
        "C29: 3♠\n" +
        "C30: 4♠\n" +
        "C31: 5♠\n" +
        "C32: 6♠\n" +
        "C33: 7♠\n" +
        "C34: 8♠\n" +
        "C35: 9♠\n" +
        "C36: 10♠\n" +
        "C37: J♠\n" +
        "C38: Q♠\n" +
        "C39: K♠\n" +
        "C40: A♥\n" +
        "C41: 2♥\n" +
        "C42: 3♥\n" +
        "C43: 4♥\n" +
        "C44: 5♥\n" +
        "C45: 6♥\n" +
        "C46: 7♥\n" +
        "C47: 8♥\n" +
        "C48: 9♥\n" +
        "C49: 10♥\n" +
        "C50: J♥\n" +
        "C51: Q♥\n" +
        "C52: K♥\n" +
        "Game quit prematurely.", log.toString());
  }

  @Test
  public void testInvalidInputSourcePile() {
    StringReader in = new StringReader("Cd q");
    generateFreecellController(in).playGame(mockModel.getDeck(), 6, 3, false);
    assertEquals("F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "C1: A♦, 7♦, K♦, 6♣, Q♣, 5♠, J♠, 4♥, 10♥\n" +
        "C2: 2♦, 8♦, A♣, 7♣, K♣, 6♠, Q♠, 5♥, J♥\n" +
        "C3: 3♦, 9♦, 2♣, 8♣, A♠, 7♠, K♠, 6♥, Q♥\n" +
        "C4: 4♦, 10♦, 3♣, 9♣, 2♠, 8♠, A♥, 7♥, K♥\n" +
        "C5: 5♦, J♦, 4♣, 10♣, 3♠, 9♠, 2♥, 8♥\n" +
        "C6: 6♦, Q♦, 5♣, J♣, 4♠, 10♠, 3♥, 9♥\n" +
        "source pile invalid, please try again.\n" +
        "Game quit prematurely.", log.toString());
  }

  @Test
  public void testInvalidInputCardIndex() {
    StringReader in = new StringReader("C1 f q");
    generateFreecellController(in).playGame(mockModel.getDeck(), 6, 3, false);
    assertEquals("F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "C1: A♦, 7♦, K♦, 6♣, Q♣, 5♠, J♠, 4♥, 10♥\n" +
        "C2: 2♦, 8♦, A♣, 7♣, K♣, 6♠, Q♠, 5♥, J♥\n" +
        "C3: 3♦, 9♦, 2♣, 8♣, A♠, 7♠, K♠, 6♥, Q♥\n" +
        "C4: 4♦, 10♦, 3♣, 9♣, 2♠, 8♠, A♥, 7♥, K♥\n" +
        "C5: 5♦, J♦, 4♣, 10♣, 3♠, 9♠, 2♥, 8♥\n" +
        "C6: 6♦, Q♦, 5♣, J♣, 4♠, 10♠, 3♥, 9♥\n" +
        "card index invalid, please try again.\n" +
        "Game quit prematurely.", log.toString());
  }

  @Test
  public void testInvalidInputDestPile() {
    StringReader in = new StringReader("C1 4 V3 q");
    generateFreecellController(in).playGame(mockModel.getDeck(), 6, 3, false);
    assertEquals("F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "C1: A♦, 7♦, K♦, 6♣, Q♣, 5♠, J♠, 4♥, 10♥\n" +
        "C2: 2♦, 8♦, A♣, 7♣, K♣, 6♠, Q♠, 5♥, J♥\n" +
        "C3: 3♦, 9♦, 2♣, 8♣, A♠, 7♠, K♠, 6♥, Q♥\n" +
        "C4: 4♦, 10♦, 3♣, 9♣, 2♠, 8♠, A♥, 7♥, K♥\n" +
        "C5: 5♦, J♦, 4♣, 10♣, 3♠, 9♠, 2♥, 8♥\n" +
        "C6: 6♦, Q♦, 5♣, J♣, 4♠, 10♠, 3♥, 9♥\n" +
        "invalid destination pile, please try again.\n" +
        "Game quit prematurely.", log.toString());
  }

  @Test
  public void testMoveWithMultipleInvalidsInBetween() {
    StringReader in = new StringReader("ew C1 e e 4 ee e e w F3 e q");
    FreecellController controller = new SimpleFreecellController(mockModel, in, log);
    controller.playGame(mockModel.getDeck(), 6, 3, false);
    assertEquals("deck = [A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, " +
        "8♦, 9♦, 10♦, J♦, Q♦, K♦, A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣," +
        " 9♣, 10♣, J♣, Q♣, K♣, A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, " +
        "10♠, J♠, Q♠, K♠, A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥" +
        ", Q♥, K♥]\n" +
        "numCascade = 6\n" +
        "numOpen = 3\n" +
        "shuffle = false\n" +
        "\n" +
        "source pile invalid, please try again.\n" +
        "card index invalid, please try again.\n" +
        "card index invalid, please try again.\n" +
        "invalid destination pile, please try again.\n" +
        "invalid destination pile, please try again.\n" +
        "invalid destination pile, please try again.\n" +
        "invalid destination pile, please try again.\n" +
        "source = CASCADE\n" +
        "pileNumber = 0\n" +
        "cardIndex = 3\n" +
        "destination = FOUNDATION\n" +
        "destPileNumber = 2\n" +
        "\n" +
        "source pile invalid, please try again.\n" +
        "Game quit prematurely.", log.toString());
  }

  @Test(expected = IllegalStateException.class)
  public void runningOutOfInput() {
    StringReader in = new StringReader("C1 4");
    generateFreecellController(in).playGame(mockModel.getDeck(), 6, 3, false);
  }

  @Test
  public void multipleInvalidInputs() {
    StringReader in = new StringReader("C C1 -12 3 F3 F2 q");
    FreecellController controller = new SimpleFreecellController(mockModel, in, log);
    controller.playGame(mockModel.getDeck(), 6, 3, false);
    assertEquals("deck = [A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, " +
        "8♦, 9♦, 10♦, J♦, Q♦, K♦, A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣" +
        ", 8♣, 9♣, 10♣, J♣, Q♣, K♣, A♠, 2♠, 3♠, 4♠, 5♠, 6♠," +
        " 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠, A♥, 2♥, 3♥, 4♥, 5♥" +
        ", 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥]\n" +
        "numCascade = 6\n" +
        "numOpen = 3\n" +
        "shuffle = false\n" +
        "\n" +
        "source pile invalid, please try again.\n" +
        "card index invalid, please try again.\n" +
        "source = CASCADE\n" +
        "pileNumber = 0\n" +
        "cardIndex = 2\n" +
        "destination = FOUNDATION\n" +
        "destPileNumber = 2\n" +
        "\n" +
        "Game quit prematurely.", log.toString());
  }

  @Test
  public void invalidMoveMadeIndexOutOfBounds() {
    StringReader in = new StringReader("C8 5 F1 q");
    generateFreecellController(in).playGame(mockModel.getDeck(), 4, 3, false);
    assertEquals("F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "C1: A♦, 5♦, 9♦, K♦, 4♣, 8♣, Q♣, 3♠, 7♠, J♠, 2♥, 6♥, 10♥\n" +
        "C2: 2♦, 6♦, 10♦, A♣, 5♣, 9♣, K♣, 4♠, 8♠, Q♠, 3♥, 7♥, J♥\n" +
        "C3: 3♦, 7♦, J♦, 2♣, 6♣, 10♣, A♠, 5♠, 9♠, K♠, 4♥, 8♥, Q♥\n" +
        "C4: 4♦, 8♦, Q♦, 3♣, 7♣, J♣, 2♠, 6♠, 10♠, A♥, 5♥, 9♥, K♥\n" +
        "invalid move, try again: cascade index out of bounds\n" +
        "Game quit prematurely.", log.toString());
  }

  @Test(expected = IllegalStateException.class)
  public void cannotAppendToAppendable() {
    StringReader in = new StringReader("C8 5 F1 q");
    Appendable badAppendable = new PipedWriter();
    FreecellController controller = new SimpleFreecellController(mockModel, in, badAppendable);
    controller.playGame(mockModel.getDeck(), 4, 3, false);
  }

  @Test
  public void invalidMoveMadeIndexOutOfBoundsThenCorrectMove() {
    StringReader in = new StringReader("C8 5 F1 C1 13 O3 q");
    generateFreecellController(in).playGame(mockModel.getDeck(), 4, 3, false);
    assertEquals("F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "C1: A♦, 5♦, 9♦, K♦, 4♣, 8♣, Q♣, 3♠, 7♠, J♠, 2♥, 6♥, 10♥\n" +
        "C2: 2♦, 6♦, 10♦, A♣, 5♣, 9♣, K♣, 4♠, 8♠, Q♠, 3♥, 7♥, J♥\n" +
        "C3: 3♦, 7♦, J♦, 2♣, 6♣, 10♣, A♠, 5♠, 9♠, K♠, 4♥, 8♥, Q♥\n" +
        "C4: 4♦, 8♦, Q♦, 3♣, 7♣, J♣, 2♠, 6♠, 10♠, A♥, 5♥, 9♥, K♥\n" +
        "invalid move, try again: cascade index out of bounds\n" +
        "F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "O1:\n" +
        "O2:\n" +
        "O3: 10♥\n" +
        "C1: A♦, 5♦, 9♦, K♦, 4♣, 8♣, Q♣, 3♠, 7♠, J♠, 2♥, 6♥\n" +
        "C2: 2♦, 6♦, 10♦, A♣, 5♣, 9♣, K♣, 4♠, 8♠, Q♠, 3♥, 7♥, J♥\n" +
        "C3: 3♦, 7♦, J♦, 2♣, 6♣, 10♣, A♠, 5♠, 9♠, K♠, 4♥, 8♥, Q♥\n" +
        "C4: 4♦, 8♦, Q♦, 3♣, 7♣, J♣, 2♠, 6♠, 10♠, A♥, 5♥, 9♥, K♥\n" +
        "Game quit prematurely.", log.toString());
  }

  @Test
  public void ThreeCorrectValidInputsInARow() {
    StringReader in = new StringReader("C8 5 F1 C1 13 O3 F4 34 O2 q");
    FreecellController controller = new SimpleFreecellController(mockModel, in, log);
    controller.playGame(mockModel.getDeck(), 4, 3, false);
    assertEquals("deck = [A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, " +
        "10♦, J♦, Q♦, K♦, A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣," +
        " Q♣, K♣, A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠," +
        " A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥]\n" +
        "numCascade = 4\n" +
        "numOpen = 3\n" +
        "shuffle = false\n" +
        "\n" +
        "source = CASCADE\n" +
        "pileNumber = 7\n" +
        "cardIndex = 4\n" +
        "destination = FOUNDATION\n" +
        "destPileNumber = 0\n" +
        "\n" +
        "source = CASCADE\n" +
        "pileNumber = 0\n" +
        "cardIndex = 12\n" +
        "destination = OPEN\n" +
        "destPileNumber = 2\n" +
        "\n" +
        "source = FOUNDATION\n" +
        "pileNumber = 3\n" +
        "cardIndex = 33\n" +
        "destination = OPEN\n" +
        "destPileNumber = 1\n" +
        "\n" +
        "Game quit prematurely.", log.toString());
  }

}
