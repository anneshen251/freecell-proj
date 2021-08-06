package cs3500.freecell.model.hw02;

import java.util.List;

/**
 * represents a foundation pile.
 */
public class FoundationPile extends APile {

  /**
   * constructor for foundation pile.
   *
   * @param name  the name of the pile
   * @param cards the list of cards in the pile
   */
  public FoundationPile(String name, List<ICard> cards) {
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
    if (this.cards.size() == 0) {
      return (card.getVal() == 1);
    }
    ICard lastCard = this.cards.get(this.cards.size() - 1);
    return (lastCard.getColor().equals(card.getColor())
        && lastCard.getVal() + 1 == card.getVal());
  }

  /**
   * returns the number of cards in the pile.
   */
  @Override
  public int size() {
    return this.cards.size();
  }

}
