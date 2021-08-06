import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw04.MultiMoveFreecellModel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class for MultiMoveFreecellModel.
 */
public class MultiMoveFreecellModelTest {
  MultiMoveFreecellModel model;

  /**
   * initializes the data.
   */
  private void initData() {
    model = new MultiMoveFreecellModel();
  }

  @Test
  public void move3CardsWithTwoOpenAndOneEmptyPiles() {
    initData();
    model.startGame(model.getDeck(), 52, 1, false);
    model.move(PileType.CASCADE, 27, 0, PileType.CASCADE, 2);
    model.move(PileType.CASCADE, 0, 0, PileType.CASCADE, 2);
    //multimove
    model.move(PileType.CASCADE, 2, 0, PileType.CASCADE, 0);
    assertEquals("F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "O1:\n" +
        "C1: 3♦, 2♠, A♦\n" +
        "C2: 2♦\n" +
        "C3:\n" +
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
        "C28:\n" +
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

  @Test(expected = IllegalArgumentException.class)
  public void multiMoveFoundationToCascade() {
    initData();
    model.startGame(model.getDeck(), 52, 3, false);
    for (int i = 0; i < 5; i++) {
      model.move(PileType.CASCADE, i, 0, PileType.FOUNDATION, 0);
    }
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
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
    //move F1 to C1 should cause illegalArguementException
    model.move(PileType.FOUNDATION, 0, 0, PileType.CASCADE, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void multiMoveFoundationToFoundation() {
    initData();
    model.startGame(model.getDeck(), 52, 3, false);
    for (int i = 0; i < 5; i++) {
      model.move(PileType.CASCADE, i, 0, PileType.FOUNDATION, 0);
    }
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
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
    //move F1->F2 should cause illegalarguementexception
    model.move(PileType.FOUNDATION, 0, 0, PileType.FOUNDATION, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void multiMoveCascadeToFoundation() {
    initData();
    model.startGame(model.getDeck(), 20, 5, false);
    model.move(PileType.CASCADE, 19, 1, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 14, 1, PileType.OPEN, 2);
    model.move(PileType.CASCADE, 14, 0, PileType.OPEN, 3);
    model.move(PileType.CASCADE, 1, 2, PileType.OPEN, 4);
    model.move(PileType.OPEN, 4, 0, PileType.CASCADE, 14);
    model.move(PileType.OPEN, 3, 0, PileType.CASCADE, 14);
    model.move(PileType.OPEN, 0, 0, PileType.CASCADE, 14);
    model.move(PileType.CASCADE, 0, 2, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 0, 1, PileType.OPEN, 1);
    model.move(PileType.CASCADE, 19, 0, PileType.OPEN, 4);
    model.move(PileType.CASCADE, 7, 2, PileType.CASCADE, 19);
    model.move(PileType.CASCADE, 9, 2, PileType.OPEN, 3);
    assertEquals("F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "O1: 2♥\n" +
        "O2: 8♣\n" +
        "O3: 9♠\n" +
        "O4: J♥\n" +
        "O5: 7♣\n" +
        "C1: A♦\n" +
        "C2: 2♦, 9♣\n" +
        "C3: 3♦, 10♣, 4♥\n" +
        "C4: 4♦, J♣, 5♥\n" +
        "C5: 5♦, Q♣, 6♥\n" +
        "C6: 6♦, K♣, 7♥\n" +
        "C7: 7♦, A♠, 8♥\n" +
        "C8: 8♦, 2♠\n" +
        "C9: 9♦, 3♠, 10♥\n" +
        "C10: 10♦, 4♠\n" +
        "C11: J♦, 5♠, Q♥\n" +
        "C12: Q♦, 6♠, K♥\n" +
        "C13: K♦, 7♠\n" +
        "C14: A♣, 8♠\n" +
        "C15: 3♥, 2♣, A♥\n" +
        "C16: 3♣, 10♠\n" +
        "C17: 4♣, J♠\n" +
        "C18: 5♣, Q♠\n" +
        "C19: 6♣, K♠\n" +
        "C20: 9♥", model.toString());
    //moving C15 -> F1 should throw illegal argument exception
    model.move(PileType.CASCADE, 14, 0, PileType.FOUNDATION, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void multiMoveAllFullNoEmptyPiles() {
    initData();
    model.startGame(model.getDeck(), 20, 5, false);
    model.move(PileType.CASCADE, 19, 1, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 14, 1, PileType.OPEN, 2);
    model.move(PileType.CASCADE, 14, 0, PileType.OPEN, 3);
    model.move(PileType.CASCADE, 1, 2, PileType.OPEN, 4);
    model.move(PileType.OPEN, 4, 0, PileType.CASCADE, 14);
    model.move(PileType.OPEN, 3, 0, PileType.CASCADE, 14);
    model.move(PileType.OPEN, 0, 0, PileType.CASCADE, 14);
    model.move(PileType.CASCADE, 0, 2, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 0, 1, PileType.OPEN, 1);
    model.move(PileType.CASCADE, 19, 0, PileType.OPEN, 4);
    model.move(PileType.CASCADE, 7, 2, PileType.CASCADE, 19);
    model.move(PileType.CASCADE, 9, 2, PileType.OPEN, 3);
    assertEquals("F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "O1: 2♥\n" +
        "O2: 8♣\n" +
        "O3: 9♠\n" +
        "O4: J♥\n" +
        "O5: 7♣\n" +
        "C1: A♦\n" +
        "C2: 2♦, 9♣\n" +
        "C3: 3♦, 10♣, 4♥\n" +
        "C4: 4♦, J♣, 5♥\n" +
        "C5: 5♦, Q♣, 6♥\n" +
        "C6: 6♦, K♣, 7♥\n" +
        "C7: 7♦, A♠, 8♥\n" +
        "C8: 8♦, 2♠\n" +
        "C9: 9♦, 3♠, 10♥\n" +
        "C10: 10♦, 4♠\n" +
        "C11: J♦, 5♠, Q♥\n" +
        "C12: Q♦, 6♠, K♥\n" +
        "C13: K♦, 7♠\n" +
        "C14: A♣, 8♠\n" +
        "C15: 3♥, 2♣, A♥\n" +
        "C16: 3♣, 10♠\n" +
        "C17: 4♣, J♠\n" +
        "C18: 5♣, Q♠\n" +
        "C19: 6♣, K♠\n" +
        "C20: 9♥", model.toString());
    //should throw error when C15 stack -> C10, tested assertequals before writing
    //this move, assertequals used for visualization
    model.move(PileType.CASCADE, 14, 0, PileType.CASCADE, 9);
  }

  @Test
  public void multiMoveStackNotStartingFromZeroIndex() {
    initData();
    model.startGame(model.getDeck(), 20, 5, false);
    model.move(PileType.CASCADE, 19, 1, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 14, 1, PileType.OPEN, 2);
    model.move(PileType.CASCADE, 14, 0, PileType.OPEN, 3);
    model.move(PileType.CASCADE, 1, 2, PileType.OPEN, 4);
    model.move(PileType.OPEN, 4, 0, PileType.CASCADE, 14);
    model.move(PileType.OPEN, 3, 0, PileType.CASCADE, 14);
    model.move(PileType.OPEN, 0, 0, PileType.CASCADE, 14);
    model.move(PileType.CASCADE, 19, 0, PileType.OPEN, 4);
    model.move(PileType.CASCADE, 9, 2, PileType.OPEN, 3);
    model.move(PileType.CASCADE, 14, 0, PileType.CASCADE, 9);
    model.move(PileType.CASCADE, 0, 2, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 2, 2, PileType.CASCADE, 19);
    assertEquals("F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "O1: 2♥\n" +
        "O2:\n" +
        "O3: 9♠\n" +
        "O4: J♥\n" +
        "O5: 7♣\n" +
        "C1: A♦, 8♣\n" +
        "C2: 2♦, 9♣\n" +
        "C3: 3♦, 10♣\n" +
        "C4: 4♦, J♣, 5♥\n" +
        "C5: 5♦, Q♣, 6♥\n" +
        "C6: 6♦, K♣, 7♥\n" +
        "C7: 7♦, A♠, 8♥\n" +
        "C8: 8♦, 2♠, 9♥\n" +
        "C9: 9♦, 3♠, 10♥\n" +
        "C10: 10♦, 4♠, 3♥, 2♣, A♥\n" +
        "C11: J♦, 5♠, Q♥\n" +
        "C12: Q♦, 6♠, K♥\n" +
        "C13: K♦, 7♠\n" +
        "C14: A♣, 8♠\n" +
        "C15:\n" +
        "C16: 3♣, 10♠\n" +
        "C17: 4♣, J♠\n" +
        "C18: 5♣, Q♠\n" +
        "C19: 6♣, K♠\n" +
        "C20: 4♥", model.toString());
    //multimove from C10 index 1 to C4
    model.move(PileType.CASCADE, 9, 1, PileType.CASCADE, 3);
    assertEquals("F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "O1: 2♥\n" +
        "O2:\n" +
        "O3: 9♠\n" +
        "O4: J♥\n" +
        "O5: 7♣\n" +
        "C1: A♦, 8♣\n" +
        "C2: 2♦, 9♣\n" +
        "C3: 3♦, 10♣\n" +
        "C4: 4♦, J♣, 5♥, 4♠, 3♥, 2♣, A♥\n" +
        "C5: 5♦, Q♣, 6♥\n" +
        "C6: 6♦, K♣, 7♥\n" +
        "C7: 7♦, A♠, 8♥\n" +
        "C8: 8♦, 2♠, 9♥\n" +
        "C9: 9♦, 3♠, 10♥\n" +
        "C10: 10♦\n" +
        "C11: J♦, 5♠, Q♥\n" +
        "C12: Q♦, 6♠, K♥\n" +
        "C13: K♦, 7♠\n" +
        "C14: A♣, 8♠\n" +
        "C15:\n" +
        "C16: 3♣, 10♠\n" +
        "C17: 4♣, J♠\n" +
        "C18: 5♣, Q♠\n" +
        "C19: 6♣, K♠\n" +
        "C20: 4♥", model.toString());

  }

  @Test
  public void multiMoveStackStartingFromZeroIndex() {
    initData();
    model.startGame(model.getDeck(), 20, 5, false);
    model.move(PileType.CASCADE, 19, 1, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 14, 1, PileType.OPEN, 2);
    model.move(PileType.CASCADE, 14, 0, PileType.OPEN, 3);
    model.move(PileType.CASCADE, 1, 2, PileType.OPEN, 4);
    model.move(PileType.OPEN, 4, 0, PileType.CASCADE, 14);
    model.move(PileType.OPEN, 3, 0, PileType.CASCADE, 14);
    model.move(PileType.OPEN, 0, 0, PileType.CASCADE, 14);
    model.move(PileType.CASCADE, 0, 2, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 0, 1, PileType.OPEN, 1);
    model.move(PileType.CASCADE, 19, 0, PileType.OPEN, 4);
    assertEquals("F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "O1: 2♥\n" +
        "O2: 8♣\n" +
        "O3: 9♠\n" +
        "O4:\n" +
        "O5: 7♣\n" +
        "C1: A♦\n" +
        "C2: 2♦, 9♣\n" +
        "C3: 3♦, 10♣, 4♥\n" +
        "C4: 4♦, J♣, 5♥\n" +
        "C5: 5♦, Q♣, 6♥\n" +
        "C6: 6♦, K♣, 7♥\n" +
        "C7: 7♦, A♠, 8♥\n" +
        "C8: 8♦, 2♠, 9♥\n" +
        "C9: 9♦, 3♠, 10♥\n" +
        "C10: 10♦, 4♠, J♥\n" +
        "C11: J♦, 5♠, Q♥\n" +
        "C12: Q♦, 6♠, K♥\n" +
        "C13: K♦, 7♠\n" +
        "C14: A♣, 8♠\n" +
        "C15: 3♥, 2♣, A♥\n" +
        "C16: 3♣, 10♠\n" +
        "C17: 4♣, J♠\n" +
        "C18: 5♣, Q♠\n" +
        "C19: 6♣, K♠\n" +
        "C20:", model.toString());
    //multimove, C15 -> C20 should have stack
    model.move(PileType.CASCADE, 14, 0, PileType.CASCADE, 19);
    assertEquals("F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "O1: 2♥\n" +
        "O2: 8♣\n" +
        "O3: 9♠\n" +
        "O4:\n" +
        "O5: 7♣\n" +
        "C1: A♦\n" +
        "C2: 2♦, 9♣\n" +
        "C3: 3♦, 10♣, 4♥\n" +
        "C4: 4♦, J♣, 5♥\n" +
        "C5: 5♦, Q♣, 6♥\n" +
        "C6: 6♦, K♣, 7♥\n" +
        "C7: 7♦, A♠, 8♥\n" +
        "C8: 8♦, 2♠, 9♥\n" +
        "C9: 9♦, 3♠, 10♥\n" +
        "C10: 10♦, 4♠, J♥\n" +
        "C11: J♦, 5♠, Q♥\n" +
        "C12: Q♦, 6♠, K♥\n" +
        "C13: K♦, 7♠\n" +
        "C14: A♣, 8♠\n" +
        "C15:\n" +
        "C16: 3♣, 10♠\n" +
        "C17: 4♣, J♠\n" +
        "C18: 5♣, Q♠\n" +
        "C19: 6♣, K♠\n" +
        "C20: 3♥, 2♣, A♥", model.toString());
  }

  @Test
  public void multiMoveWhenOnlyEmptyOpenPiles() {
    initData();
    model.startGame(model.getDeck(), 10, 10, false);
    for (int i = 4; i >= 0; i--) {
      model.move(PileType.CASCADE, 9, i, PileType.OPEN, i);
    }
    model.move(PileType.OPEN, 2, 0, PileType.CASCADE, 9);
    model.move(PileType.CASCADE, 1, 5, PileType.OPEN, 2);
    model.move(PileType.CASCADE, 1, 4, PileType.CASCADE, 9);
    assertEquals("F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "O1: 10♦\n" +
        "O2: 7♣\n" +
        "O3: K♥\n" +
        "O4: A♥\n" +
        "O5: J♥\n" +
        "O6:\n" +
        "O7:\n" +
        "O8:\n" +
        "O9:\n" +
        "O10:\n" +
        "C1: A♦, J♦, 8♣, 5♠, 2♥, Q♥\n" +
        "C2: 2♦, Q♦, 9♣, 6♠\n" +
        "C3: 3♦, K♦, 10♣, 7♠, 4♥\n" +
        "C4: 4♦, A♣, J♣, 8♠, 5♥\n" +
        "C5: 5♦, 2♣, Q♣, 9♠, 6♥\n" +
        "C6: 6♦, 3♣, K♣, 10♠, 7♥\n" +
        "C7: 7♦, 4♣, A♠, J♠, 8♥\n" +
        "C8: 8♦, 5♣, 2♠, Q♠, 9♥\n" +
        "C9: 9♦, 6♣, 3♠, K♠, 10♥\n" +
        "C10: 4♠, 3♥", model.toString());
    //multi-move from C10 -> C4
    model.move(PileType.CASCADE, 9, 0, PileType.CASCADE, 3);
    assertEquals("F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "O1: 10♦\n" +
        "O2: 7♣\n" +
        "O3: K♥\n" +
        "O4: A♥\n" +
        "O5: J♥\n" +
        "O6:\n" +
        "O7:\n" +
        "O8:\n" +
        "O9:\n" +
        "O10:\n" +
        "C1: A♦, J♦, 8♣, 5♠, 2♥, Q♥\n" +
        "C2: 2♦, Q♦, 9♣, 6♠\n" +
        "C3: 3♦, K♦, 10♣, 7♠, 4♥\n" +
        "C4: 4♦, A♣, J♣, 8♠, 5♥, 4♠, 3♥\n" +
        "C5: 5♦, 2♣, Q♣, 9♠, 6♥\n" +
        "C6: 6♦, 3♣, K♣, 10♠, 7♥\n" +
        "C7: 7♦, 4♣, A♠, J♠, 8♥\n" +
        "C8: 8♦, 5♣, 2♠, Q♠, 9♥\n" +
        "C9: 9♦, 6♣, 3♠, K♠, 10♥\n" +
        "C10:", model.toString());
  }

  @Test
  public void multiMoveWhenOnlyEmptyCascadePiles() {
    initData();
    model.startGame(model.getDeck(), 52, 1, false);
    model.move(PileType.CASCADE, 1, 0, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 14, 0, PileType.CASCADE, 2);
    model.move(PileType.CASCADE, 0, 0, PileType.CASCADE, 2);
    assertEquals("F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "O1: 2♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3: 3♦, 2♣, A♦\n" +
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
        "C15:\n" +
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
    //multimove C3 -> C17
    model.move(PileType.CASCADE, 2, 0, PileType.CASCADE, 16);
    assertEquals("F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "O1: 2♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
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
        "C15:\n" +
        "C16: 3♣\n" +
        "C17: 4♣, 3♦, 2♣, A♦\n" +
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

  //moved three cards when there was only one empty cascade pile
  @Test(expected = IllegalArgumentException.class)
  public void movedTooManyCardsOneEmptyCascade() {
    initData();
    model.startGame(model.getDeck(), 20, 5, false);
    model.move(PileType.CASCADE, 19, 1, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 14, 1, PileType.OPEN, 2);
    model.move(PileType.CASCADE, 14, 0, PileType.OPEN, 3);
    model.move(PileType.CASCADE, 1, 2, PileType.OPEN, 4);
    model.move(PileType.OPEN, 4, 0, PileType.CASCADE, 14);
    model.move(PileType.OPEN, 3, 0, PileType.CASCADE, 14);
    model.move(PileType.OPEN, 0, 0, PileType.CASCADE, 14);
    model.move(PileType.CASCADE, 0, 2, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 0, 1, PileType.OPEN, 1);
    model.move(PileType.CASCADE, 5, 2, PileType.OPEN, 3);
    model.move(PileType.CASCADE, 19, 0, PileType.OPEN, 4);
    //multimove
    model.move(PileType.CASCADE, 14, 0, PileType.CASCADE, 19);
  }

  @Test
  public void multiMoveIntoEmptyCascadePile() {
    initData();
    model.startGame(model.getDeck(), 20, 5, false);
    model.move(PileType.CASCADE, 19, 1, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 14, 1, PileType.OPEN, 2);
    model.move(PileType.CASCADE, 14, 0, PileType.OPEN, 3);
    model.move(PileType.CASCADE, 1, 2, PileType.OPEN, 4);
    model.move(PileType.OPEN, 4, 0, PileType.CASCADE, 14);
    model.move(PileType.OPEN, 3, 0, PileType.CASCADE, 14);
    model.move(PileType.OPEN, 0, 0, PileType.CASCADE, 14);
    model.move(PileType.CASCADE, 0, 2, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 0, 1, PileType.OPEN, 1);
    model.move(PileType.CASCADE, 19, 0, PileType.OPEN, 4);
    assertEquals("F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "O1: 2♥\n" +
        "O2: 8♣\n" +
        "O3: 9♠\n" +
        "O4:\n" +
        "O5: 7♣\n" +
        "C1: A♦\n" +
        "C2: 2♦, 9♣\n" +
        "C3: 3♦, 10♣, 4♥\n" +
        "C4: 4♦, J♣, 5♥\n" +
        "C5: 5♦, Q♣, 6♥\n" +
        "C6: 6♦, K♣, 7♥\n" +
        "C7: 7♦, A♠, 8♥\n" +
        "C8: 8♦, 2♠, 9♥\n" +
        "C9: 9♦, 3♠, 10♥\n" +
        "C10: 10♦, 4♠, J♥\n" +
        "C11: J♦, 5♠, Q♥\n" +
        "C12: Q♦, 6♠, K♥\n" +
        "C13: K♦, 7♠\n" +
        "C14: A♣, 8♠\n" +
        "C15: 3♥, 2♣, A♥\n" +
        "C16: 3♣, 10♠\n" +
        "C17: 4♣, J♠\n" +
        "C18: 5♣, Q♠\n" +
        "C19: 6♣, K♠\n" +
        "C20:", model.toString());
    //multimove, C15 -> C20 should have stack
    model.move(PileType.CASCADE, 14, 0, PileType.CASCADE, 19);
    assertEquals("F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "O1: 2♥\n" +
        "O2: 8♣\n" +
        "O3: 9♠\n" +
        "O4:\n" +
        "O5: 7♣\n" +
        "C1: A♦\n" +
        "C2: 2♦, 9♣\n" +
        "C3: 3♦, 10♣, 4♥\n" +
        "C4: 4♦, J♣, 5♥\n" +
        "C5: 5♦, Q♣, 6♥\n" +
        "C6: 6♦, K♣, 7♥\n" +
        "C7: 7♦, A♠, 8♥\n" +
        "C8: 8♦, 2♠, 9♥\n" +
        "C9: 9♦, 3♠, 10♥\n" +
        "C10: 10♦, 4♠, J♥\n" +
        "C11: J♦, 5♠, Q♥\n" +
        "C12: Q♦, 6♠, K♥\n" +
        "C13: K♦, 7♠\n" +
        "C14: A♣, 8♠\n" +
        "C15:\n" +
        "C16: 3♣, 10♠\n" +
        "C17: 4♣, J♠\n" +
        "C18: 5♣, Q♠\n" +
        "C19: 6♣, K♠\n" +
        "C20: 3♥, 2♣, A♥", model.toString());
  }

  @Test
  public void multiMoveOntoNonEmptyCascadePile() {
    initData();
    model.startGame(model.getDeck(), 20, 5, false);
    model.move(PileType.CASCADE, 19, 1, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 14, 1, PileType.OPEN, 2);
    model.move(PileType.CASCADE, 14, 0, PileType.OPEN, 3);
    model.move(PileType.CASCADE, 1, 2, PileType.OPEN, 4);
    model.move(PileType.OPEN, 4, 0, PileType.CASCADE, 14);
    model.move(PileType.OPEN, 3, 0, PileType.CASCADE, 14);
    model.move(PileType.OPEN, 0, 0, PileType.CASCADE, 14);
    model.move(PileType.CASCADE, 19, 0, PileType.OPEN, 4);
    model.move(PileType.CASCADE, 9, 2, PileType.OPEN, 3);
    assertEquals("F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "O1:\n" +
        "O2:\n" +
        "O3: 9♠\n" +
        "O4: J♥\n" +
        "O5: 7♣\n" +
        "C1: A♦, 8♣, 2♥\n" +
        "C2: 2♦, 9♣\n" +
        "C3: 3♦, 10♣, 4♥\n" +
        "C4: 4♦, J♣, 5♥\n" +
        "C5: 5♦, Q♣, 6♥\n" +
        "C6: 6♦, K♣, 7♥\n" +
        "C7: 7♦, A♠, 8♥\n" +
        "C8: 8♦, 2♠, 9♥\n" +
        "C9: 9♦, 3♠, 10♥\n" +
        "C10: 10♦, 4♠\n" +
        "C11: J♦, 5♠, Q♥\n" +
        "C12: Q♦, 6♠, K♥\n" +
        "C13: K♦, 7♠\n" +
        "C14: A♣, 8♠\n" +
        "C15: 3♥, 2♣, A♥\n" +
        "C16: 3♣, 10♠\n" +
        "C17: 4♣, J♠\n" +
        "C18: 5♣, Q♠\n" +
        "C19: 6♣, K♠\n" +
        "C20:", model.toString());
    //multi-move from C15 -> C10
    model.move(PileType.CASCADE, 14, 0, PileType.CASCADE, 9);
    assertEquals("F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "O1:\n" +
        "O2:\n" +
        "O3: 9♠\n" +
        "O4: J♥\n" +
        "O5: 7♣\n" +
        "C1: A♦, 8♣, 2♥\n" +
        "C2: 2♦, 9♣\n" +
        "C3: 3♦, 10♣, 4♥\n" +
        "C4: 4♦, J♣, 5♥\n" +
        "C5: 5♦, Q♣, 6♥\n" +
        "C6: 6♦, K♣, 7♥\n" +
        "C7: 7♦, A♠, 8♥\n" +
        "C8: 8♦, 2♠, 9♥\n" +
        "C9: 9♦, 3♠, 10♥\n" +
        "C10: 10♦, 4♠, 3♥, 2♣, A♥\n" +
        "C11: J♦, 5♠, Q♥\n" +
        "C12: Q♦, 6♠, K♥\n" +
        "C13: K♦, 7♠\n" +
        "C14: A♣, 8♠\n" +
        "C15:\n" +
        "C16: 3♣, 10♠\n" +
        "C17: 4♣, J♠\n" +
        "C18: 5♣, Q♠\n" +
        "C19: 6♣, K♠\n" +
        "C20:", model.toString());

  }

  //moving four cards when there is one empty cascade and one empty open
  @Test
  public void multiMoveWhenCanBeMovedIsEqualToNumberMoved() {
    initData();
    model.startGame(model.getDeck(), 20, 5, false);
    model.move(PileType.CASCADE, 19, 1, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 14, 1, PileType.OPEN, 2);
    model.move(PileType.CASCADE, 14, 0, PileType.OPEN, 3);
    model.move(PileType.CASCADE, 1, 2, PileType.OPEN, 4);
    model.move(PileType.OPEN, 4, 0, PileType.CASCADE, 14);
    model.move(PileType.OPEN, 3, 0, PileType.CASCADE, 14);
    model.move(PileType.OPEN, 0, 0, PileType.CASCADE, 14);
    model.move(PileType.CASCADE, 19, 0, PileType.OPEN, 4);
    model.move(PileType.CASCADE, 9, 2, PileType.OPEN, 3);
    model.move(PileType.CASCADE, 14, 0, PileType.CASCADE, 9);
    model.move(PileType.CASCADE, 0, 2, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 2, 2, PileType.CASCADE, 19);
    assertEquals("F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "O1: 2♥\n" +
        "O2:\n" +
        "O3: 9♠\n" +
        "O4: J♥\n" +
        "O5: 7♣\n" +
        "C1: A♦, 8♣\n" +
        "C2: 2♦, 9♣\n" +
        "C3: 3♦, 10♣\n" +
        "C4: 4♦, J♣, 5♥\n" +
        "C5: 5♦, Q♣, 6♥\n" +
        "C6: 6♦, K♣, 7♥\n" +
        "C7: 7♦, A♠, 8♥\n" +
        "C8: 8♦, 2♠, 9♥\n" +
        "C9: 9♦, 3♠, 10♥\n" +
        "C10: 10♦, 4♠, 3♥, 2♣, A♥\n" +
        "C11: J♦, 5♠, Q♥\n" +
        "C12: Q♦, 6♠, K♥\n" +
        "C13: K♦, 7♠\n" +
        "C14: A♣, 8♠\n" +
        "C15:\n" +
        "C16: 3♣, 10♠\n" +
        "C17: 4♣, J♠\n" +
        "C18: 5♣, Q♠\n" +
        "C19: 6♣, K♠\n" +
        "C20: 4♥", model.toString());
    //multi-move C10 starting from 4 of spades to C15
    model.move(PileType.CASCADE, 9, 1, PileType.CASCADE, 14);
    assertEquals("F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "O1: 2♥\n" +
        "O2:\n" +
        "O3: 9♠\n" +
        "O4: J♥\n" +
        "O5: 7♣\n" +
        "C1: A♦, 8♣\n" +
        "C2: 2♦, 9♣\n" +
        "C3: 3♦, 10♣\n" +
        "C4: 4♦, J♣, 5♥\n" +
        "C5: 5♦, Q♣, 6♥\n" +
        "C6: 6♦, K♣, 7♥\n" +
        "C7: 7♦, A♠, 8♥\n" +
        "C8: 8♦, 2♠, 9♥\n" +
        "C9: 9♦, 3♠, 10♥\n" +
        "C10: 10♦\n" +
        "C11: J♦, 5♠, Q♥\n" +
        "C12: Q♦, 6♠, K♥\n" +
        "C13: K♦, 7♠\n" +
        "C14: A♣, 8♠\n" +
        "C15: 4♠, 3♥, 2♣, A♥\n" +
        "C16: 3♣, 10♠\n" +
        "C17: 4♣, J♠\n" +
        "C18: 5♣, Q♠\n" +
        "C19: 6♣, K♠\n" +
        "C20: 4♥", model.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidMoveCorrectBuild() {
    initData();
    model.startGame(model.getDeck(), 20, 5, false);
    model.move(PileType.CASCADE, 19, 1, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 14, 1, PileType.OPEN, 2);
    model.move(PileType.CASCADE, 14, 0, PileType.OPEN, 3);
    model.move(PileType.CASCADE, 1, 2, PileType.OPEN, 4);
    model.move(PileType.OPEN, 4, 0, PileType.CASCADE, 14);
    model.move(PileType.OPEN, 3, 0, PileType.CASCADE, 14);
    model.move(PileType.OPEN, 0, 0, PileType.CASCADE, 14);
    model.move(PileType.CASCADE, 19, 0, PileType.OPEN, 4);
    model.move(PileType.CASCADE, 9, 2, PileType.OPEN, 3);
    assertEquals("F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "O1:\n" +
        "O2:\n" +
        "O3: 9♠\n" +
        "O4: J♥\n" +
        "O5: 7♣\n" +
        "C1: A♦, 8♣, 2♥\n" +
        "C2: 2♦, 9♣\n" +
        "C3: 3♦, 10♣, 4♥\n" +
        "C4: 4♦, J♣, 5♥\n" +
        "C5: 5♦, Q♣, 6♥\n" +
        "C6: 6♦, K♣, 7♥\n" +
        "C7: 7♦, A♠, 8♥\n" +
        "C8: 8♦, 2♠, 9♥\n" +
        "C9: 9♦, 3♠, 10♥\n" +
        "C10: 10♦, 4♠\n" +
        "C11: J♦, 5♠, Q♥\n" +
        "C12: Q♦, 6♠, K♥\n" +
        "C13: K♦, 7♠\n" +
        "C14: A♣, 8♠\n" +
        "C15: 3♥, 2♣, A♥\n" +
        "C16: 3♣, 10♠\n" +
        "C17: 4♣, J♠\n" +
        "C18: 5♣, Q♠\n" +
        "C19: 6♣, K♠\n" +
        "C20:", model.toString());
    //invalid move from C15 to C4, I know assertequals works since I used it in a previous test
    //put assertequals here just for visualization
    model.move(PileType.CASCADE, 14, 0, PileType.CASCADE, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidBuildButCorrectMove() {
    initData();
    model.startGame(model.getDeck(), 20, 5, false);
    model.move(PileType.CASCADE, 19, 1, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 14, 1, PileType.OPEN, 2);
    model.move(PileType.CASCADE, 14, 0, PileType.OPEN, 3);
    model.move(PileType.CASCADE, 1, 2, PileType.OPEN, 4);
    model.move(PileType.OPEN, 4, 0, PileType.CASCADE, 14);
    model.move(PileType.OPEN, 3, 0, PileType.CASCADE, 14);
    model.move(PileType.OPEN, 0, 0, PileType.CASCADE, 14);
    model.move(PileType.CASCADE, 19, 0, PileType.OPEN, 4);
    model.move(PileType.CASCADE, 9, 2, PileType.OPEN, 3);
    assertEquals("F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "O1:\n" +
        "O2:\n" +
        "O3: 9♠\n" +
        "O4: J♥\n" +
        "O5: 7♣\n" +
        "C1: A♦, 8♣, 2♥\n" +
        "C2: 2♦, 9♣\n" +
        "C3: 3♦, 10♣, 4♥\n" +
        "C4: 4♦, J♣, 5♥\n" +
        "C5: 5♦, Q♣, 6♥\n" +
        "C6: 6♦, K♣, 7♥\n" +
        "C7: 7♦, A♠, 8♥\n" +
        "C8: 8♦, 2♠, 9♥\n" +
        "C9: 9♦, 3♠, 10♥\n" +
        "C10: 10♦, 4♠\n" +
        "C11: J♦, 5♠, Q♥\n" +
        "C12: Q♦, 6♠, K♥\n" +
        "C13: K♦, 7♠\n" +
        "C14: A♣, 8♠\n" +
        "C15: 3♥, 2♣, A♥\n" +
        "C16: 3♣, 10♠\n" +
        "C17: 4♣, J♠\n" +
        "C18: 5♣, Q♠\n" +
        "C19: 6♣, K♠\n" +
        "C20:", model.toString());
    //invalid build from C17 to C4, I know assertequals works since I used it in a previous test
    //put assertequals here just for visualization
    model.move(PileType.CASCADE, 16, 0, PileType.CASCADE, 3);
  }
}
