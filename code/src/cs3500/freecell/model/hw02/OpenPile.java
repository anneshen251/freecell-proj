package cs3500.freecell.model.hw02;

import java.util.List;

/**
 * represents an open pile.
 */
public class OpenPile extends APile {

  /**
   * constructor for open pile.
   *
   * @param name  the name of the pile
   * @param cards the list of cards in the pile
   */
  public OpenPile(String name, List<ICard> cards) {
    super(name, cards);
  }

  /**
   * determines whether the given card can be added to the pile.
   *
   * @param card the given card to be assessed.
   * @return true if it can be added and false otherwise.
   */
  @Override
  public boolean canAdd(ICard card) {
    return (this.cards.size() == 0);
  }
}
