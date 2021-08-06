import cs3500.freecell.model.hw02.CascadePile;
import cs3500.freecell.model.hw02.FoundationPile;
import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.hw02.OpenPile;
import cs3500.freecell.model.hw02.CardNum;
import cs3500.freecell.model.hw02.Suits;
import cs3500.freecell.model.hw02.ICard;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * to test the abstract pile class.
 */
public class AbstractPileTest {
  CascadePile cp;
  OpenPile open;
  FoundationPile fp;
  FoundationPile fp2;

  /**
   * initializes the data.
   */
  private void initData() {
    cp = new CascadePile("C1", new ArrayList<ICard>());
    open = new OpenPile("O2", new ArrayList<ICard>());
    fp = new FoundationPile("F4", new ArrayList<ICard>());
    fp2 = new FoundationPile("F3", new ArrayList<ICard>());
    fp.add(new Card(CardNum.ACE, Suits.CLUB));
    fp.add(new Card(CardNum.TWO, Suits.CLUB));
    fp.add(new Card(CardNum.THREE, Suits.CLUB));
  }

  @Test
  public void testToString() {
    initData();
    assertEquals("F4: A♣, 2♣, 3♣", fp.toString());
  }

  @Test
  public void basicAddCascadePile() {
    initData();
    cp.add(new Card(CardNum.ACE, Suits.CLUB));
    assertEquals("C1: A♣", cp.toString());
  }

  @Test
  public void basicAddOpenPile() {
    initData();
    open.add(new Card(CardNum.FIVE, Suits.HEART));
    assertEquals("O2: 5♥", open.toString());
  }

  @Test
  public void basicAddFoundationPile() {
    initData();
    fp.add(new Card(CardNum.FOUR, Suits.CLUB));
    assertEquals("F4: A♣, 2♣, 3♣, 4♣", fp.toString());
  }

  @Test
  public void addMultipleToPile() {
    initData();
    assertEquals("F4: A♣, 2♣, 3♣", fp.toString());
  }

  @Test
  public void testRemoveMethod() {
    initData();
    assertEquals("3♣", fp.remove().toString());
    assertEquals("F4: A♣, 2♣", fp.toString());
  }

  @Test
  public void testCanAddOnEmptyOpen() {
    initData();
    assertEquals(true, open.canAdd(new Card(CardNum.TWO, Suits.CLUB)));
  }

  @Test
  public void testCanAddFoundationWrongVal() {
    initData();
    assertEquals(false, fp.canAdd(new Card(CardNum.FIVE, Suits.CLUB)));
  }

  @Test
  public void testCanAddFoundationWrongSuit() {
    initData();
    assertEquals(false, fp.canAdd(new Card(CardNum.FOUR, Suits.HEART)));
  }

  @Test
  public void testCanAddOnFullOpenPile() {
    initData();
    open.add(new Card(CardNum.ACE, Suits.HEART));
    assertEquals(false, open.canAdd(new Card(CardNum.FIVE, Suits.HEART)));
  }

  @Test
  public void testCanAddCascadePileEmpty() {
    initData();
    assertEquals(true, cp.canAdd(new Card(CardNum.FIVE, Suits.HEART)));
  }

  @Test
  public void testCanAddCascadePileWrongVal() {
    initData();
    cp.add(new Card(CardNum.EIGHT, Suits.HEART));
    assertEquals(false, cp.canAdd(new Card(CardNum.NINE, Suits.SPADE)));
  }

  @Test
  public void testCanAddCascadePileWrongSuit() {
    initData();
    cp.add(new Card(CardNum.EIGHT, Suits.HEART));
    assertEquals(false, cp.canAdd(new Card(CardNum.SEVEN, Suits.DIAMOND)));
  }

  @Test
  public void testCanAddCascadePileRightValAndSuit() {
    initData();
    cp.add(new Card(CardNum.EIGHT, Suits.HEART));
    assertEquals(true, cp.canAdd(new Card(CardNum.SEVEN, Suits.SPADE)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveOnEmptyOpen() {
    initData();
    ICard temp = open.remove();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveOnEmptyCascade() {
    initData();
    ICard temp = cp.remove();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveOnEmptyFoundation() {
    initData();
    ICard temp = fp2.remove();
  }

  @Test
  public void testSizeMethod() {
    initData();
    assertEquals(3, fp.size());
  }

  @Test
  public void testGetCardAt() {
    initData();
    assertEquals(new Card(CardNum.THREE, Suits.CLUB), fp.getCardAt(2));
  }

  @Test
  public void testGetListOfCards() {
    initData();
    assertEquals("[A♣, 2♣, 3♣]", fp.getListOfCards().toString());
  }


}
