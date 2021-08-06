package cs3500.freecell.model.hw02;

/**
 * represents a card form a deck of playing cards, must have a valid number and suit
 * associated with it.
 */
public class Card implements ICard {
  private final CardNum num;
  private final Suits suit;

  /**
   * constructor for card.
   *
   * @param num  the numerical value
   * @param suit the suit the card belongs to
   */
  public Card(CardNum num, Suits suit) {
    this.num = num;
    this.suit = suit;
  }

  @Override
  public boolean isValid() {
    for (Suits suit : Suits.values()) {
      for (CardNum cn : CardNum.values()) {
        if (cn.equals(this.num) && suit.equals(this.suit)) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public String toString() {
    return this.num.getSymbol() + this.suit.getSymbol();
  }

  @Override
  public boolean equals(Object obj) {

    if (obj == this) {
      return true;
    }
    if (obj instanceof Card) {
      Card temp = (Card) obj;
      return temp.num == this.num
          && temp.suit == this.suit;
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return this.num.hashCode() * 10000 + this.suit.hashCode();
  }

  @Override
  public String getColor() {
    return this.suit.getColor();
  }

  @Override
  public int getVal() {
    return this.num.getVal();
  }
}
