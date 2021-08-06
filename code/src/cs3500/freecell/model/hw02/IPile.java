package cs3500.freecell.model.hw02;

import java.util.List;

/**
 * represents an interface for Pile.
 */
public interface IPile {

  /**
   * adds a card to end of the pile, does nothing if does not follow
   * specified rules.
   *
   * @param card the given card to be added
   */
  void add(ICard card);


  /**
   * removes a card from the end of the pile.
   *
   * @return the card that was removed
   * @throws IllegalArgumentException if pile is empty.
   */
  ICard remove();

  /**
   * returns the number of cards in the pile.
   */
  int size();

  /**
   * determines whether the given card can be added to the pile.
   *
   * @param card the given card to be assessed.
   * @return true if it can be added and false otherwise.
   */
  boolean canAdd(ICard card);

  /**
   * gets the specified card from the list.
   *
   * @return the card at the index
   */
  ICard getCardAt(int index);

  /**
   * gets the list of cards.
   *
   * @return the list of cards
   */
  List<ICard> getListOfCards();

}
