package cs3500.freecell.model.hw04;

import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.CascadePile;
import cs3500.freecell.model.hw02.ICard;
import cs3500.freecell.model.hw02.IPile;
import cs3500.freecell.model.hw02.SimpleFreecellModel;

import java.util.ArrayList;
import java.util.List;

/**
 * represents a free cell game where moving multiple cards within cascade piles
 * is allowed so long as they follow specific constraints.
 * This specific model uses the {@code Card} representation.
 *
 * <p>decided to extend SimpleFreecellModel instead of creating an AbstractFreecellModel
 * since all methods are the same except move which has some additional modifications,
 * but still has shared code with SimpleFreecellModel.</p>
 */
public class MultiMoveFreecellModel extends SimpleFreecellModel {

  /**
   * the constructor for MultiMoveFreecellModel.
   */
  public MultiMoveFreecellModel() {
    super();
  }

  @Override
  public void move(PileType source, int pileNumber, int cardIndex,
                   PileType destination, int destPileNumber)
      throws IllegalArgumentException, IllegalStateException {
    if (!super.start) {
      throw new IllegalStateException("game hasn't started");
    }
    IPile from = getPile(source, pileNumber);
    IPile dest = getPile(destination, destPileNumber);

    //multi-move code
    if (source == PileType.CASCADE && destination == PileType.CASCADE
        && cardIndex != from.size() - 1) {
      int canBeMoved = (getNumFreeOpenPiles() + 1) * (1 << getNumFreeCascade());
      if (canBeMoved < from.size() - cardIndex) {
        throw new IllegalArgumentException("tried to move too many cards");
      } else if (!dest.canAdd(from.getCardAt(cardIndex))) {
        throw new IllegalArgumentException("invalid move");
      } else if (!validBuild(cardIndex, from)) {
        throw new IllegalArgumentException("build is invalid");
      }
      //move all
      addThenRemoveAll(from, dest, cardIndex);
    } else { //single-move
      super.move(source, pileNumber, cardIndex, destination, destPileNumber);
    }
  }

  /**
   * Adds all the neccessary cards to the destination pile then removes
   * them from the starting pile.
   *
   * @param from      the from pile
   * @param dest      the destination pile
   * @param cardIndex the index to start removing cards
   */
  private void addThenRemoveAll(IPile from, IPile dest, int cardIndex) {
    List<ICard> temp = new ArrayList<>(from.getListOfCards().subList(cardIndex, from.size()));
    for (ICard card : temp) {
      dest.add(card);
    }
    for (int i = 0; i < temp.size(); i++) {
      from.remove();
    }
  }

  /**
   * determines whether the stack of cards in cascade is valid to move.
   *
   * @return true if valid, false otherwise.
   */
  private boolean validBuild(int cardIndex, IPile pile) {
    CascadePile tempCascade = new CascadePile("", new ArrayList<>());
    for (int i = cardIndex; i < pile.size(); i++) {
      if (!tempCascade.canAdd(pile.getCardAt(i))) {
        return false;
      }
      tempCascade.add(pile.getCardAt(i));
    }
    return true;
  }

  /**
   * calculates the number of free open piles available.
   *
   * @return the number of free open piles
   */
  private int getNumFreeOpenPiles() {
    int count = 0;
    for (IPile pile : super.listOfOpenPiles) {
      if (pile.size() == 0) {
        count += 1;
      }
    }
    return count;
  }

  /**
   * calculates the number of free cascade piles available.
   *
   * @return the number of free cascade piles
   */
  private int getNumFreeCascade() {
    int count = 0;
    for (IPile pile : super.listOfCascadePiles) {
      if (pile.size() == 0) {
        count += 1;
      }
    }
    return count;
  }
}
