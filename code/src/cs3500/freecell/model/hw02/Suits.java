package cs3500.freecell.model.hw02;

/**
 * enum for the suit of a card.
 */
public enum Suits {
  DIAMOND("red", "♦"), CLUB("black", "♣"), SPADE("black", "♠"),
  HEART("red", "♥");
  private final String symbol;
  private final String color;

  /**
   * constructor for Suits.
   *
   * @param color the color of the card
   * @param symbol the suit the card belongs to
   */
  Suits(String color, String symbol) {
    this.symbol = symbol;
    this.color = color;
  }

  /**
   * The string representation of the symbol.
   *
   * @return the symbol as a string.
   */
  public String getSymbol() {
    return this.symbol;
  }

  /**
   * The string representation of color.
   *
   * @return the color as a string.
   */
  public String getColor() {
    return this.color;
  }
}
