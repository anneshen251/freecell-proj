package cs3500.freecell.model.hw02;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represents a model for the game of Freecell.
 * This specific model uses the {@code Card} representation and allows only one for
 * one card to move.
 *
 * <p>changed the private fields to protected since the subclass MultiMoveFreecellModel
 * needs to mutate the piles. Also changed the getPile method to protected since it is
 * accessed in MultiMoveFreecellModel.
 */
public class SimpleFreecellModel implements FreecellModel<ICard> {
  protected List<IPile> listOfCascadePiles;
  protected List<IPile> listOfOpenPiles;
  protected List<IPile> listOfFoundationPiles;
  protected boolean start;

  /**
   * default constructor for AFreecellModel.
   */
  public SimpleFreecellModel() {
    this.listOfOpenPiles = new ArrayList<>();
    this.listOfFoundationPiles = new ArrayList<>();
    this.listOfCascadePiles = new ArrayList<>();
    start = false;
  }

  /**
   * Return a valid and complete deck of cards for a game of Freecell. There is
   * no restriction imposed on the ordering of these cards in the deck. An
   * invalid deck is defined as a deck that has one or more of these flaws: <ul>
   * <li>It does not have 52 cards</li> <li>It has duplicate cards</li> <li>It
   * has at least one invalid card (invalid suit or invalid number) </li> </ul>
   *
   * @return the deck of cards as a list
   */
  @Override
  public List<ICard> getDeck() {
    List<ICard> deck = new ArrayList<ICard>();
    for (Suits suit : Suits.values()) {
      for (CardNum num : CardNum.values()) {
        deck.add(new Card(num, suit));
      }
    }
    return deck;
  }

  /**
   * Deal a new game of freecell with the given deck, with or without shuffling
   * it first. This method first verifies that the deck is valid. It deals the
   * deck among the cascade piles in roundrobin fashion. Thus if there are 4
   * cascade piles, the 1st pile will get cards 0, 4, 8, ..., the 2nd pile will
   * get cards 1, 5, 9, ..., the 3rd pile will get cards 2, 6, 10, ... and the
   * 4th pile will get cards 3, 7, 11, .... Depending on the number of cascade
   * piles, they may have a different number of cards
   *
   * @param deck            the deck to be dealt
   * @param numCascadePiles number of cascade piles
   * @param numOpenPiles    number of open piles
   * @param shuffle         if true, shuffle the deck else deal the deck as-is
   * @throws IllegalArgumentException if the deck is invalid
   */
  @Override
  public void startGame(List<ICard> deck, int numCascadePiles, int numOpenPiles, boolean shuffle)
      throws IllegalArgumentException {
    if (!isValid(deck)) {
      throw new IllegalArgumentException("given deck is invalid");
    }
    if (numCascadePiles < 4) {
      throw new IllegalArgumentException("must be at least 4 cascade piles");
    }
    if (numOpenPiles < 1) {
      throw new IllegalArgumentException("must be at least one open pile");
    }
    if (shuffle) {
      Collections.shuffle(deck);
    }
    this.listOfFoundationPiles = buildPile(PileType.FOUNDATION, 4);
    this.listOfCascadePiles = buildPile(PileType.CASCADE, numCascadePiles);
    this.listOfOpenPiles = buildPile(PileType.OPEN, numOpenPiles);
    this.start = true;
    distribute(deck);
  }

  /**
   * Move a card from the given source pile to the given destination pile, if
   * the move is valid.
   *
   * @param source         the type of the source pile see @link{PileType}
   * @param pileNumber     the pile number of the given type, starting at 0
   * @param cardIndex      the index of the card to be moved from the source
   *                       pile, starting at 0
   * @param destination    the type of the destination pile (see
   * @param destPileNumber the pile number of the given type, starting at 0
   * @throws IllegalArgumentException if the move is not possible {@link
   *                                  PileType})
   * @throws IllegalStateException    if a move is attempted before the game has
   *                                  starts
   */
  @Override
  public void move(PileType source, int pileNumber, int cardIndex,
                   PileType destination, int destPileNumber)
      throws IllegalArgumentException, IllegalStateException {
    if (!this.start) {
      throw new IllegalStateException("game hasn't started");
    }
    IPile from = getPile(source, pileNumber);

    if (source == PileType.FOUNDATION) {
      throw new IllegalArgumentException("source cannot be a foundation pile");
    }

    if (cardIndex != from.size() - 1) {
      throw new IllegalArgumentException("invalid card index");
    }

    IPile dest = getPile(destination, destPileNumber);
    ICard removed = from.remove();

    if (!dest.canAdd(removed)) {
      from.add(removed);
      throw new IllegalArgumentException("invalid move");
    }
    dest.add(removed);
  }

  /**
   * builds the pile list given the type and how many.
   *
   * @param type the type of pile to be added
   * @param num  the number or piles to be added
   * @return a list of piles with a specific type and amount
   */
  private List<IPile> buildPile(PileType type, int num) {
    List<IPile> pile = new ArrayList<IPile>();
    for (int i = 1; i <= num; i++) {
      if (type == PileType.FOUNDATION) {
        pile.add(new FoundationPile("F" + i, new ArrayList<ICard>()));
      } else if (type == PileType.CASCADE) {
        pile.add(new CascadePile("C" + i, new ArrayList<ICard>()));
      } else {
        pile.add(new OpenPile("O" + i, new ArrayList<ICard>()));
      }
    }
    return pile;
  }

  /**
   * distributes the cards round robin style in the cascade piles.
   */
  private void distribute(List<ICard> deck) {
    List<ICard> tempDeck = new ArrayList<ICard>(deck.subList(0, deck.size()));
    int count = 0;
    for (ICard c : tempDeck) {
      this.listOfCascadePiles.get(count % getNumCascadePiles()).getListOfCards().add(c);
      count += 1;
    }
  }

  /**
   * determines whether a card pile is valid.
   *
   * @param deck the given card deck
   * @return true is card pile is valid and false if not.
   */
  private boolean isValid(List<ICard> deck) {
    List<ICard> prev = new ArrayList<ICard>();
    if (deck.size() != 52) {
      return false;
    }
    for (ICard c : deck) {
      if (!c.isValid() || prev.contains(c)) {
        return false;
      }
      prev.add(c);
    }
    return true;
  }

  /**
   * retrieves the pile specified by type and index.
   *
   * @param type  the type of the pile
   * @param index the index of the pile
   * @return the pile specified.
   * @throws IllegalArgumentException if index is out of bounds.
   */
  protected IPile getPile(PileType type, int index) {
    if (type == PileType.CASCADE) {
      if (index < 0 || this.listOfCascadePiles.size() <= index) {
        throw new IllegalArgumentException("cascade index out of bounds");
      }
      return this.listOfCascadePiles.get(index);
    } else if (type == PileType.FOUNDATION) {
      if (index < 0 || this.listOfFoundationPiles.size() <= index) {
        throw new IllegalArgumentException("open index out of bounds");
      }
      return this.listOfFoundationPiles.get(index);
    } else if (type == PileType.OPEN) {
      if (index < 0 || this.listOfOpenPiles.size() <= index) {
        throw new IllegalArgumentException("foundation index out of bounds");
      }
      return this.listOfOpenPiles.get(index);
    } else {
      throw new IllegalArgumentException("pile does not exist");
    }
  }

  /**
   * Signal if the game is over or not.
   *
   * @return true if game is over, false otherwise
   */
  @Override
  public boolean isGameOver() {
    int count = 0;
    for (IPile p : this.listOfFoundationPiles) {
      count += p.size();
    }
    return count == 52;
  }

  @Override
  public String toString() {
    String finalString = "";
    for (IPile p : this.listOfFoundationPiles) {
      finalString += p.toString() + "\n";
    }
    for (IPile p : this.listOfOpenPiles) {
      finalString += p.toString() + "\n";
    }
    for (IPile p : this.listOfCascadePiles) {
      finalString += p.toString() + "\n";
    }
    finalString = finalString.stripTrailing();
    return finalString;
  }

  /**
   * Get the number of cards in a given foundation pile.
   *
   * @param index the index of the foundation pile, starting at 0
   * @return the number of cards in the given foundation pile
   * @throws IllegalArgumentException if the provided index is invalid
   * @throws IllegalStateException    if the game has not started
   */
  @Override
  public int getNumCardsInFoundationPile(int index)
      throws IllegalArgumentException, IllegalStateException {
    if (!start) {
      throw new IllegalStateException("game has not begun");
    }
    if (index > this.listOfFoundationPiles.size() - 1 || index < 0) {
      throw new IllegalArgumentException("provided index is invalid");
    }
    return this.listOfFoundationPiles.get(index).size();
  }

  /**
   * Get the number of cascade piles in this game of freecell.
   *
   * @return the number of cascade piles, as an integer, or -1 if the game has not started yet
   */
  @Override
  public int getNumCascadePiles() {
    if (!start) {
      return -1;
    } else {
      return this.listOfCascadePiles.size();
    }
  }

  /**
   * Get the number of cards in a given cascade pile.
   *
   * @param index the index of the cascade pile, starting at 0
   * @return the number of cards in the given cascade pile
   * @throws IllegalArgumentException if the provided index is invalid
   * @throws IllegalStateException    if the game has not started
   */
  @Override
  public int getNumCardsInCascadePile(int index)
      throws IllegalArgumentException, IllegalStateException {
    if (!start) {
      throw new IllegalStateException("game has not begun");
    }
    if (index > this.listOfCascadePiles.size() - 1 || index < 0) {
      throw new IllegalArgumentException("provided index is invalid");
    }
    return this.listOfCascadePiles.get(index).size();
  }

  /**
   * Get the number of cards in a given open pile.
   *
   * @param index the index of the open pile, starting at 0
   * @return the number of cards in the given open pile
   * @throws IllegalArgumentException if the provided index is invalid
   * @throws IllegalStateException    if the game has not started
   */
  @Override
  public int getNumCardsInOpenPile(int index)
      throws IllegalArgumentException, IllegalStateException {
    if (!start) {
      throw new IllegalStateException("game has not begun");
    }
    if (index > this.listOfOpenPiles.size() - 1 || index < 0) {
      throw new IllegalArgumentException("provided index is invalid");
    }
    return this.listOfOpenPiles.get(index).size();
  }

  /**
   * Get the number of open piles in this game of freecell.
   *
   * @return the number of open piles, as an integer, or -1 if the game has not started yet
   */
  @Override
  public int getNumOpenPiles() {
    if (!start) {
      return -1;
    } else {
      return this.listOfOpenPiles.size();
    }
  }

  /**
   * Get the card at the provided index in the provided foundation pile.
   *
   * @param pileIndex the index of the foundation pile, starting at 0
   * @param cardIndex the index of the card in the above foundation pile, starting at 0
   * @return the card at the provided indices
   * @throws IllegalArgumentException if the pileIndex or cardIndex is invalid
   * @throws IllegalStateException    if the game has not started
   */
  @Override
  public ICard getFoundationCardAt(int pileIndex, int cardIndex)
      throws IllegalArgumentException, IllegalStateException {
    if (!start) {
      throw new IllegalStateException("game has not begun");
    }
    if (pileIndex > this.listOfFoundationPiles.size() - 1 || pileIndex < 0 || cardIndex < 0 ||
        cardIndex > this.listOfFoundationPiles.get(pileIndex).size() - 1) {
      throw new IllegalArgumentException("provided index is invalid");
    }
    return this.listOfFoundationPiles.get(pileIndex).getCardAt(cardIndex);
  }

  /**
   * Get the card at the provided index in the provided cascade pile.
   *
   * @param pileIndex the index of the cascade pile, starting at 0
   * @param cardIndex the index of the card in the above cascade pile, starting at 0
   * @return the card at the provided indices
   * @throws IllegalArgumentException if the pileIndex or cardIndex is invalid
   * @throws IllegalStateException    if the game has not started
   */
  @Override
  public ICard getCascadeCardAt(int pileIndex, int cardIndex)
      throws IllegalArgumentException, IllegalStateException {
    if (!start) {
      throw new IllegalStateException("game has not begun");
    }
    if (pileIndex > this.listOfCascadePiles.size() - 1 || pileIndex < 0 || cardIndex < 0 ||
        cardIndex > this.listOfCascadePiles.get(pileIndex).size() - 1) {
      throw new IllegalArgumentException("provided index is invalid");
    }
    return this.listOfCascadePiles.get(pileIndex).getCardAt(cardIndex);
  }

  /**
   * Get the card in the given open pile.
   *
   * @param pileIndex the index of the open pile, starting at 0
   * @return the card at the provided index, or null if there is no card there
   * @throws IllegalArgumentException if the pileIndex is invalid
   * @throws IllegalStateException    if the game has not started
   */
  @Override
  public ICard getOpenCardAt(int pileIndex)
      throws IllegalArgumentException, IllegalStateException {
    if (!start) {
      throw new IllegalStateException("game has not begun");
    }
    if (pileIndex > this.listOfOpenPiles.size() - 1 || pileIndex < 0) {
      throw new IllegalArgumentException("provided index is invalid");
    }
    if (this.listOfOpenPiles.get(pileIndex).size() != 0) {
      return this.listOfOpenPiles.get(pileIndex).getCardAt(0);
    } else {
      return null;
    }
  }
}
