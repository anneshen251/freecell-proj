package cs3500.freecell.model.hw02;

/**
 * represents an interface for Card.
 */
public interface ICard {

  /**
   * determines whether the card is valid or not according to
   * CardNum and Suits specifications.
   *
   * @return true if is valid and false otherwise.
   */
  boolean isValid();

  /**
   * converts the card to a string representation.
   *
   * @return the string format of a card.
   */
  String toString();

  /**
   * gets the color of the suit.
   *
   * @return the color as a string
   */
  String getColor();

  /**
   * gets the value of the card.
   *
   * @return an integer specifying the value
   */
  int getVal();

}
