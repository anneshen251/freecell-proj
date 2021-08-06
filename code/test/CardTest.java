import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.hw02.CardNum;
import cs3500.freecell.model.hw02.Suits;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * to test methods in the Card class.
 */
public class CardTest {
  Card valid1;
  Card valid2;
  Card valid2Dup;
  Card invalid1;
  Card invalid2;
  Card invalid3;

  /**
   * initializes the data.
   */
  private void initData() {
    valid1 = new Card(CardNum.ACE, Suits.CLUB);
    invalid1 = new Card(null, Suits.HEART);
    invalid2 = new Card(CardNum.TWO, null);
    invalid3 = new Card(null, null);
    valid2 = new Card(CardNum.TWO, Suits.HEART);
    valid2Dup = new Card(CardNum.TWO, Suits.HEART);
  }

  @Test
  public void isValidTest() {
    initData();
    assertEquals(true, valid1.isValid());
  }

  @Test
  public void isValidWithNullArgNum() {
    initData();
    assertEquals(false, invalid1.isValid());
  }

  @Test
  public void isValidWithNullArgSuit() {
    initData();
    assertEquals(false, invalid2.isValid());
  }

  @Test
  public void isValidBothNullArg() {
    initData();
    assertEquals(false, invalid3.isValid());
  }

  @Test
  public void testToString() {
    initData();
    assertEquals("A♣", valid1.toString());
  }

  @Test
  public void testEqualToItself() {
    initData();
    assertEquals(true, valid1.equals(valid1));
  }

  @Test
  public void testEqualFail() {
    initData();
    assertEquals(false, valid1.equals(valid2));
  }

  @Test
  public void testEqualDifferentObjRef() {
    initData();
    assertEquals(true, valid2.equals(valid2Dup));
  }

  @Test
  public void testHashCode() {
    initData();
    assertEquals(-289277877, valid1.hashCode());
  }

  @Test
  public void testGetColor() {
    initData();
    assertEquals("black", valid1.getColor());
  }

  @Test
  public void testGetVal() {
    initData();
    assertEquals(1, valid1.getVal());
  }
}


