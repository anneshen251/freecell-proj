import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.model.hw04.MultiMoveFreecellModel;
import cs3500.freecell.view.FreecellTextView;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * to test methods of FreeCellTextView.
 */
public class FreeCellTextViewTest {
  FreecellTextView text;
  MultiMoveFreecellModel model3;
  MultiMoveFreecellModel model4;
  FreecellTextView text2;
  SimpleFreecellModel model;
  SimpleFreecellModel model2;
  FreecellTextView view;
  StringBuilder log;

  private void initData() {
    model3 = new MultiMoveFreecellModel();
    model4 = new MultiMoveFreecellModel();
    model3.startGame(model3.getDeck(), 8, 4, false);
    log = new StringBuilder();
    text2 = new FreecellTextView(model3, log);
    model = new SimpleFreecellModel();
    model2 = new SimpleFreecellModel();
    model.startGame(model.getDeck(), 8, 4, false);
    text = new FreecellTextView(model);
    view = new FreecellTextView(model, log);
  }

  @Test
  public void toStringTest() {
    initData();
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
        "C8: 8♦, 3♣, J♣, 6♠, A♥, 9♥", text.toString());
  }

  @Test
  public void toStringGameNotStart() {
    initData();
    assertEquals("", model2.toString());
  }

  @Test
  public void renderGameStartingGame() {
    initData();
    try {
      view.renderBoard();
    } catch (IOException e) {
      e.printStackTrace();
    }
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
        "C8: 8♦, 3♣, J♣, 6♠, A♥, 9♥\n", log.toString());
  }

  @Test
  public void sendOneMessage() {
    initData();
    try {
      view.renderMessage("hello");
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals("hello", log.toString());
  }

  @Test
  public void renderNullModel() {
    initData();
    view = new FreecellTextView(null, log);
    try {
      view.renderBoard();
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals("", log.toString());
  }

  @Test
  public void sendNullMessage() {
    initData();
    try {
      view.renderMessage(null);
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals("", log.toString());
  }

  @Test
  public void renderGameThenSendMessage() {
    initData();
    try {
      view.renderBoard();
      view.renderMessage("hello");
    } catch (IOException e) {
      e.printStackTrace();
    }
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
        "C8: 8♦, 3♣, J♣, 6♠, A♥, 9♥\n" +
        "hello", log.toString());
  }

  @Test
  public void toStringTestMulti() {
    initData();
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
        "C8: 8♦, 3♣, J♣, 6♠, A♥, 9♥", text2.toString());
  }

  @Test
  public void toStringGameNotStartMulti() {
    initData();
    assertEquals("", model4.toString());
  }

  @Test
  public void renderGameStartingGameMulti() {
    initData();
    try {
      text2.renderBoard();
    } catch (IOException e) {
      e.printStackTrace();
    }
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
        "C8: 8♦, 3♣, J♣, 6♠, A♥, 9♥\n", log.toString());
  }

  @Test
  public void sendOneMessageMulti() {
    initData();
    try {
      text2.renderMessage("hello");
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals("hello", log.toString());
  }

  @Test
  public void renderNullModelMulti() {
    initData();
    text2 = new FreecellTextView(null, log);
    try {
      text2.renderBoard();
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals("", log.toString());
  }

  @Test
  public void sendNullMessageMulti() {
    initData();
    try {
      text2.renderMessage(null);
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals("", log.toString());
  }

  @Test
  public void renderGameThenSendMessageMulti() {
    initData();
    try {
      text2.renderBoard();
      text2.renderMessage("hello");
    } catch (IOException e) {
      e.printStackTrace();
    }
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
        "C8: 8♦, 3♣, J♣, 6♠, A♥, 9♥\n" +
        "hello", log.toString());
  }


}
