package cs3500.freecell.model.hw02;

import java.util.List;

/**
 * represents an abstract class for pile.
 */
public abstract class APile implements IPile {
  protected String name;
  protected List<ICard> cards;

  /**
   * constructor for a pile.
   *
   * @param name  the name of the pile
   * @param cards the list of cards in the pile
   */
  public APile(String name, List<ICard> cards) {
    this.name = name;
    this.cards = cards;
  }

  @Override
  public void add(ICard card) {
    if (canAdd(card)) {
      this.cards.add(card);
    }
  }

  @Override
  public ICard remove() {
    if (this.cards.size() > 0) {
      return this.cards.remove(this.cards.size() - 1);
    } else {
      throw new IllegalArgumentException("can't remove from a pile with no cards.");
    }
  }

  @Override
  public int size() {
    return cards.size();
  }

  @Override
  public String toString() {
    String finalString = "";
    if (this.cards.size() == 0) {
      finalString = this.name + ":";
      return finalString;
    } else {
      finalString = this.name + ": ";
      for (ICard c : cards) {
        finalString += c.toString() + ", ";
      }
      finalString = finalString.substring(0, finalString.length() - 2);
      return finalString;
    }
  }

  @Override
  public ICard getCardAt(int index) {
    return this.cards.get(index);
  }

  @Override
  public List<ICard> getListOfCards() {
    return this.cards;
  }

}