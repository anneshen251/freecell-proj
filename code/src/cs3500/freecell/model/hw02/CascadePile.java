package cs3500.freecell.model.hw02;

import java.util.List;

/**
 * represents a cascade pile.
 */
public class CascadePile extends APile {
  /**
   * constructor for a cascade pile.
   *
   * @param name  the name of the pile
   * @param cards the list of cards in the pile
   */
  public CascadePile(String name, List<ICard> cards) {
    super(name, cards);
  }

  @Override
  public boolean canAdd(ICard card) {
    if (this.cards.size() == 0) {
      return true;
    }
    ICard lastCard = this.cards.get(this.cards.size() - 1);
    return (!lastCard.getColor().equals(card.getColor())
        && lastCard.getVal() - 1 == card.getVal());
  }


  /**
   * returns the number of cards in the pile.
   */
  @Override
  public int size() {
    return this.cards.size();
  }
}
