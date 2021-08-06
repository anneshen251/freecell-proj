import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.CardNum;
import cs3500.freecell.model.hw02.ICard;
import cs3500.freecell.model.hw02.Suits;
import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.model.hw04.MultiMoveFreecellModel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * tests for FreecellModel.
 */
public abstract class AbstractFreecellModelTest {

  /**
   * Constructs an instance of the class under test representing a FreecellModel.
   */
  protected abstract FreecellModel generateModel();

  /**
   * Concrete class for testing FreecellController using the SimpleFreecellModel.
   */
  public static final class SimpleFreecellCellModelTest
      extends AbstractFreecellModelTest {

    /**
     * Constructs an instance of the class under test representing a SimpleFreecellModel.
     */
    @Override
    protected FreecellModel generateModel() {
      return new SimpleFreecellModel();
    }
  }

  /**
   * Concrete class for testing FreecellController using the MultiMoveFreecellModel.
   */
  public static final class MultiMoveFreecellCellModelTest
      extends AbstractFreecellModelTest {

    /**
     * Constructs an instance of the class under test representing a MultiMoveFreecellModel.
     */
    @Override
    protected FreecellModel generateModel() {
      return new MultiMoveFreecellModel();
    }
  }

  @Test
  public void getDeckWorks() {
    FreecellModel model = generateModel();
    assertEquals("[A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, " +
        "K♦, A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣, " +
        "A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠," +
        " A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥]", model.getDeck().toString());
    assertEquals(52, model.getDeck().size());
  }

  @Test
  public void testToString() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 5, 1, false);
    assertEquals("F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "O1:\n" +
        "C1: A♦, 6♦, J♦, 3♣, 8♣, K♣, 5♠, 10♠, 2♥, 7♥, Q♥\n" +
        "C2: 2♦, 7♦, Q♦, 4♣, 9♣, A♠, 6♠, J♠, 3♥, 8♥, K♥\n" +
        "C3: 3♦, 8♦, K♦, 5♣, 10♣, 2♠, 7♠, Q♠, 4♥, 9♥\n" +
        "C4: 4♦, 9♦, A♣, 6♣, J♣, 3♠, 8♠, K♠, 5♥, 10♥\n" +
        "C5: 5♦, 10♦, 2♣, 7♣, Q♣, 4♠, 9♠, A♥, 6♥, J♥", model.toString());
  }

  @Test
  public void startGameNoShuffleDeck() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 8, 4, false);
    assertEquals("F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "C1: A♦, 9♦, 4♣, Q♣, 7♠, 2♥, 10♥\n" +
        "C2: 2♦, 10♦, 5♣, K♣, 8♠, 3♥, J♥\n" +
        "C3: 3♦, J♦, 6♣, A♠, 9♠, 4♥, Q♥\n" +
        "C4: 4♦, Q♦, 7♣, 2♠, 10♠, 5♥, K♥\n" +
        "C5: 5♦, K♦, 8♣, 3♠, J♠, 6♥\n" +
        "C6: 6♦, A♣, 9♣, 4♠, Q♠, 7♥\n" +
        "C7: 7♦, 2♣, 10♣, 5♠, K♠, 8♥\n" +
        "C8: 8♦, 3♣, J♣, 6♠, A♥, 9♥", model.toString());
  }

  @Test
  public void startGameWithShuffleDeck() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 8, 4, true);
    String temp1 = model.toString();
    model.startGame(model.getDeck(), 8, 4, true);
    String temp2 = model.toString();
    assertNotEquals(temp1, temp2);
  }

  @Test
  public void moveCascadeToOpenPile() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 8, 2, false);
    model.move(PileType.CASCADE, 1, 6, PileType.OPEN, 1);
    assertEquals("F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "O1:\n" +
        "O2: J♥\n" +
        "C1: A♦, 9♦, 4♣, Q♣, 7♠, 2♥, 10♥\n" +
        "C2: 2♦, 10♦, 5♣, K♣, 8♠, 3♥\n" +
        "C3: 3♦, J♦, 6♣, A♠, 9♠, 4♥, Q♥\n" +
        "C4: 4♦, Q♦, 7♣, 2♠, 10♠, 5♥, K♥\n" +
        "C5: 5♦, K♦, 8♣, 3♠, J♠, 6♥\n" +
        "C6: 6♦, A♣, 9♣, 4♠, Q♠, 7♥\n" +
        "C7: 7♦, 2♣, 10♣, 5♠, K♠, 8♥\n" +
        "C8: 8♦, 3♣, J♣, 6♠, A♥, 9♥", model.toString());
  }

  @Test
  public void moveOpenToOpenPile() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 8, 2, false);
    model.move(PileType.CASCADE, 1, 6, PileType.OPEN, 1);
    model.move(PileType.OPEN, 1, 0, PileType.OPEN, 0);
    assertEquals("F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "O1: J♥\n" +
        "O2:\n" +
        "C1: A♦, 9♦, 4♣, Q♣, 7♠, 2♥, 10♥\n" +
        "C2: 2♦, 10♦, 5♣, K♣, 8♠, 3♥\n" +
        "C3: 3♦, J♦, 6♣, A♠, 9♠, 4♥, Q♥\n" +
        "C4: 4♦, Q♦, 7♣, 2♠, 10♠, 5♥, K♥\n" +
        "C5: 5♦, K♦, 8♣, 3♠, J♠, 6♥\n" +
        "C6: 6♦, A♣, 9♣, 4♠, Q♠, 7♥\n" +
        "C7: 7♦, 2♣, 10♣, 5♠, K♠, 8♥\n" +
        "C8: 8♦, 3♣, J♣, 6♠, A♥, 9♥", model.toString());

  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidMoveOpenToCascadeWrongValRightSuit() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 8, 2, false);
    model.move(PileType.CASCADE, 6, 5, PileType.OPEN, 1);
    model.move(PileType.OPEN, 1, 0, PileType.CASCADE, 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidMoveOpenToCascadeWrongVal() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 8, 2, false);
    model.move(PileType.CASCADE, 1, 6, PileType.OPEN, 1);
    model.move(PileType.OPEN, 1, 0, PileType.CASCADE, 1);
  }

  @Test(expected = IllegalStateException.class)
  public void moveWhenGameHasntStarted() {
    FreecellModel model = generateModel();
    model.move(PileType.CASCADE, 7, 2, PileType.OPEN, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidMoveCascadeToFoundation() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 8, 4, false);
    model.move(PileType.CASCADE, 5, 5, PileType.FOUNDATION, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveToFullOpenPile() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 8, 4, false);
    model.move(PileType.CASCADE, 1, 11, PileType.OPEN, 3);
    model.move(PileType.CASCADE, 0, 10, PileType.OPEN, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveWithIndexTooBig() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 8, 4, false);
    model.move(PileType.CASCADE, 0, 11, PileType.OPEN, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveWithIndexTooSmall() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 8, 4, false);
    model.move(PileType.CASCADE, 0, -1, PileType.OPEN, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void tooLittleOpenPiles() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 8, 0, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void tooLittleCascadePiles() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 3, 1, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void tooLittleCascadePileAndOpenPiles() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 3, 0, false);
  }

  @Test
  public void isGameOverTestTrue() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 52, 1, false);
    for (int i = 0; i < 13; i++) {
      model.move(PileType.CASCADE, i, 0, PileType.FOUNDATION, 0);
    }
    for (int i = 13; i < 26; i++) {
      model.move(PileType.CASCADE, i, 0, PileType.FOUNDATION, 1);
    }
    for (int i = 26; i < 39; i++) {
      model.move(PileType.CASCADE, i, 0, PileType.FOUNDATION, 2);
    }
    for (int i = 39; i < 52; i++) {
      model.move(PileType.CASCADE, i, 0, PileType.FOUNDATION, 3);
    }
    assertEquals(true, model.isGameOver());
  }

  @Test
  public void isGameOverTestFalse() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 52, 1, false);
    for (int i = 0; i < 13; i++) {
      model.move(PileType.CASCADE, i, 0, PileType.FOUNDATION, 0);
    }
    for (int i = 13; i < 26; i++) {
      model.move(PileType.CASCADE, i, 0, PileType.FOUNDATION, 1);
    }
    for (int i = 26; i < 39; i++) {
      model.move(PileType.CASCADE, i, 0, PileType.FOUNDATION, 2);
    }
    assertEquals(false, model.isGameOver());
  }

  @Test
  public void testGetNumCardsInFoundationPileEmpty() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 8, 3, false);
    assertEquals(0, model.getNumCardsInFoundationPile(0));
  }

  @Test(expected = IllegalStateException.class)
  public void testGetNumCardsInFoundationPileGameNotStart() {
    FreecellModel model = generateModel();
    assertEquals(0, model.getNumCardsInFoundationPile(0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetNumCardsInFoundationPileIndexTooBig() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 8, 3, false);
    model.getNumCardsInFoundationPile(8);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetNumCardsInFoundationPileIndexTooSmall() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 8, 3, false);
    model.getNumCardsInFoundationPile(-1);
  }

  @Test
  public void testGetNumCardsInFoundationPile() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 52, 3, false);
    for (int i = 0; i < 13; i++) {
      model.move(PileType.CASCADE, i, 0, PileType.FOUNDATION, 0);
    }
    assertEquals(13, model.getNumCardsInFoundationPile(0));
  }

  @Test
  public void testGetNumCascadePilesGameNotStart() {
    FreecellModel model = generateModel();
    assertEquals(-1, model.getNumCascadePiles());
  }

  @Test
  public void testGetNumCascadePiles() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 8, 3, false);
    assertEquals(8, model.getNumCascadePiles());
  }

  @Test(expected = IllegalStateException.class)
  public void testGetNumCardsInOpenPileGameNotStart() {
    FreecellModel model = generateModel();
    model.getNumCardsInOpenPile(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetNumCardsInOpenPileIndexTooBig() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 8, 3, false);
    model.getNumCardsInOpenPile(8);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetNumCardsInOpenPileIndexTooSmall() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 8, 3, false);
    model.getNumCardsInOpenPile(-1);
  }

  @Test
  public void testGetNumCardsInOpenPile() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 8, 3, false);
    assertEquals(0, model.getNumCardsInOpenPile(1));
  }

  @Test
  public void testGetNumOpenPilesGameNotStart() {
    FreecellModel model = generateModel();
    assertEquals(-1, model.getNumOpenPiles());
  }

  @Test
  public void testGetNumOpenPiles() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 8, 3, false);
    assertEquals(3, model.getNumOpenPiles());
  }

  @Test(expected = IllegalStateException.class)
  public void testGetFoundationCardAtGameNotStart() {
    FreecellModel model = generateModel();
    model.getFoundationCardAt(0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetFoundationCardPileIndexOutOfBound() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 8, 3, false);
    model.getFoundationCardAt(9, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetFoundationCardCardIndexOutOfBound() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 52, 3, false);
    for (int i = 0; i < 13; i++) {
      model.move(PileType.CASCADE, i, 0, PileType.FOUNDATION, 0);
    }
    model.getFoundationCardAt(0, 14);
  }

  @Test
  public void testGetFoundationCard() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 52, 3, false);
    for (int i = 0; i < 13; i++) {
      model.move(PileType.CASCADE, i, 0, PileType.FOUNDATION, 0);
    }
    assertEquals(new Card(CardNum.KING, Suits.DIAMOND), model.getFoundationCardAt(0, 12));
  }

  @Test(expected = IllegalStateException.class)
  public void testGetCascadeCardAtGameNotStart() {
    FreecellModel model = generateModel();
    model.getCascadeCardAt(0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetCascadeCardPileIndexOutOfBound() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 8, 3, false);
    model.getCascadeCardAt(9, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetCascadeCardCardIndexOutOfBound() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 52, 3, false);
    for (int i = 0; i < 13; i++) {
      model.move(PileType.CASCADE, i, 0, PileType.FOUNDATION, 0);
    }
    model.getCascadeCardAt(0, 2);
  }

  @Test
  public void testGetCascadeCard() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 8, 3, false);
    assertEquals(new Card(CardNum.TWO, Suits.HEART), model.getCascadeCardAt(0, 5));
  }

  @Test
  public void moveCascadeToFoundationPile() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 52, 3, false);
    model.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    assertEquals(1, model.getNumCardsInFoundationPile(0));
    assertEquals(new Card(CardNum.ACE, Suits.DIAMOND), model.getFoundationCardAt(0, 0));
    assertEquals(0, model.getNumCardsInCascadePile(0));
  }

  @Test
  public void moveOpenToCascadePile() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 52, 3, false);
    model.move(PileType.CASCADE, 0, 0, PileType.OPEN, 0);
    model.move(PileType.OPEN, 0, 0, PileType.CASCADE, 0);
    assertEquals(0, model.getNumCardsInOpenPile(0));
    assertEquals(1, model.getNumCardsInCascadePile(0));
  }

  @Test
  public void moveCascadeToCascadePile() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 52, 3, false);
    model.move(PileType.CASCADE, 13, 0, PileType.CASCADE, 1);
    assertEquals(0, model.getNumCardsInCascadePile(13));
    assertEquals(new Card(CardNum.ACE, Suits.CLUB), model.getCascadeCardAt(1, 1));
  }

  @Test
  public void moveOpenToFoundationPile() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 52, 3, false);
    model.move(PileType.CASCADE, 0, 0, PileType.OPEN, 1);
    model.move(PileType.OPEN, 1, 0, PileType.FOUNDATION, 0);
    assertEquals(new Card(CardNum.ACE, Suits.DIAMOND), model.getFoundationCardAt(0, 0));
    assertEquals(0, model.getNumCardsInOpenPile(1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveFoundationToFoundationPile() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 52, 3, false);
    model.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 1);
    model.move(PileType.FOUNDATION, 1, 0, PileType.FOUNDATION, 2);

  }

  @Test(expected = IllegalArgumentException.class)
  public void moveFoundationToCascadePile() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 52, 3, false);
    model.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 1);
    model.move(PileType.FOUNDATION, 1, 0, PileType.CASCADE, 14);
  }

  @Test
  public void moveFoundationToOpenPile() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 52, 3, false);
    model.move(PileType.CASCADE, 0, 0, PileType.OPEN, 1);
    model.move(PileType.OPEN, 1, 0, PileType.FOUNDATION, 0);
    assertEquals(0, model.getNumCardsInOpenPile(1));
    assertEquals(1, model.getNumCardsInFoundationPile(0));
    assertEquals(new Card(CardNum.ACE, Suits.DIAMOND), model.getFoundationCardAt(0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidMoveCascadeToFoundationPileWrongVal() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 52, 3, false);
    model.move(PileType.CASCADE, 1, 0, PileType.FOUNDATION, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidMoveCascadeToFoundationPileWrongSuit() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 52, 3, false);
    model.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 14, 0, PileType.FOUNDATION, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startGameGivenInvalidDeck() {
    FreecellModel model = generateModel();
    List<ICard> deck = new ArrayList<>();
    deck.add(new Card(CardNum.ACE, Suits.DIAMOND));
    deck.add(new Card(CardNum.NINE, Suits.DIAMOND));
    model.startGame(deck, 8, 4, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startGameGivenInvalidDeckDuplicates() {
    FreecellModel model = generateModel();
    List<ICard> deck = new ArrayList<>();
    for (int i = 0; i < 52; i++) {
      deck.add(new Card(CardNum.ACE, Suits.DIAMOND));
    }
    model.startGame(deck, 8, 4, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startGameTooLittleCascadePiles() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 3, 4, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startGameTooLittleOpenPiles() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 4, 0, false);
  }

  @Test
  public void getCardAtOpenPileWithNoCards() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 4, 1, false);
    assertEquals(null, model.getOpenCardAt(0));
  }

  @Test
  public void getCardAtOpenPile() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 4, 1, false);
    model.move(PileType.CASCADE, 0, 12, PileType.OPEN, 0);
    assertEquals(new Card(CardNum.TEN, Suits.HEART), model.getOpenCardAt(0));
  }


  @Test(expected = IllegalArgumentException.class)
  public void invalidMoveCascadeToFoundationPileAfterAceWrongSuit() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 52, 1, false);
    model.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 14, 0, PileType.FOUNDATION, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidMoveCascadeToFoundationPileAfterAceWrongVal() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 52, 1, false);
    model.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 2, 0, PileType.FOUNDATION, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidMoveOpenToCascadePileWrongSuit() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 52, 1, false);
    model.move(PileType.CASCADE, 0, 0, PileType.OPEN, 0);
    model.move(PileType.OPEN, 0, 0, PileType.CASCADE, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidMoveOpenToCascadePileWrongVal() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 52, 1, false);
    model.move(PileType.CASCADE, 0, 0, PileType.OPEN, 0);
    model.move(PileType.OPEN, 0, 0, PileType.CASCADE, 2);
  }

  @Test
  public void moveFoundationTwice() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 52, 1, false);
    model.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 1, 0, PileType.FOUNDATION, 0);
    assertEquals("F1: A♦, 2♦\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "O1:\n" +
        "C1:\n" +
        "C2:\n" +
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
        "C52: K♥", model.toString());
  }

  @Test
  public void callingStartGameTwice() {
    FreecellModel model = generateModel();
    model.startGame(model.getDeck(), 52, 1, false);
    model.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    model.startGame(model.getDeck(), 4, 3, false);
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
        "C4: 4♦, 8♦, Q♦, 3♣, 7♣, J♣, 2♠, 6♠, 10♠, A♥, 5♥, 9♥, K♥", model.toString());
  }
}


