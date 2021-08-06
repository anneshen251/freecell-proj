package cs3500.freecell.model.hw02;

/**
 * enum to represent card values from 1-10, ace, king, queen, and jack.
 */
public enum CardNum {
  //acceptable representations of a card
  ACE(1, "A"), TWO(2, "2"), THREE(3, "3"), FOUR(4, "4"),
  FIVE(5, "5"), SIX(6, "6"), SEVEN(7, "7"),
  EIGHT(8, "8"), NINE(9, "9"), TEN(10, "10"),
  JACK(11, "J"), QUEEN(12, "Q"), KING(13, "K");
  private final int num;
  private final String symbol;

  /**
   * the constructor for CardNum.
   *
   * @param num    the numerical value of a card
   * @param symbol the representation of the value of a card
   */
  CardNum(int num, String symbol) {
    this.num = num;
    this.symbol = symbol;
  }

  /**
   * The string representation of the card symbol.
   *
   * @return the card symbol as a string.
   */
  public String getSymbol() {
    return this.symbol;
  }

  /**
   * the int representation of the card num.
   *
   * @return an integer representing card value.
   */
  public int getVal() {
    return this.num;
  }

}
