import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.hw02.CardNum;
import cs3500.freecell.model.hw02.ICard;
import cs3500.freecell.model.hw02.Suits;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * represents a mock model for the simplefreecell model
 * to use to check whether the control is sending the right
 * information to the model.
 */
public class MockFreecellModel implements FreecellModel<ICard> {
  final StringBuilder log;

  /**
   * default constructor for SimpleFreecellModel.
   *
   * @param log the given string builder
   */
  public MockFreecellModel(StringBuilder log) {
    this.log = Objects.requireNonNull(log);

  }

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

  @Override
  public void startGame(List<ICard> deck, int numCascadePiles, int numOpenPiles, boolean shuffle) {
    log.append("deck = " + deck.toString() + "\n"
        + "numCascade = " + numCascadePiles + "\n"
        + "numOpen = " + numOpenPiles + "\n"
        + "shuffle = " + shuffle + "\n");
  }

  @Override
  public void move(PileType source, int pileNumber, int cardIndex,
                   PileType destination, int destPileNumber) {
    log.append("source = " + source.toString() + "\n"
        + "pileNumber = " + pileNumber + "\n"
        + "cardIndex = " + cardIndex + "\n"
        + "destination = " + destination.toString() + "\n"
        + "destPileNumber = " + destPileNumber + "\n");
  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public String toString() {
    return "";
  }

  @Override
  public int getNumCardsInFoundationPile(int index) {
    return 0;
  }

  @Override
  public int getNumCascadePiles() {
    return 0;
  }

  @Override
  public int getNumCardsInCascadePile(int index) {
    return 0;
  }

  @Override
  public int getNumCardsInOpenPile(int index) {
    return 0;
  }

  @Override
  public int getNumOpenPiles() {
    return 0;
  }

  @Override
  public ICard getFoundationCardAt(int pileIndex, int cardIndex) {
    return null;
  }

  @Override
  public ICard getCascadeCardAt(int pileIndex, int cardIndex) {
    return null;
  }

  @Override
  public ICard getOpenCardAt(int pileIndex) {
    return null;
  }
}
