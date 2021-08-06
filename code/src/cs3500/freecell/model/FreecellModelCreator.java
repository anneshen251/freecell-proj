package cs3500.freecell.model;

import cs3500.freecell.model.hw02.ICard;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.model.hw04.MultiMoveFreecellModel;

/**
 * class that creates a free cell game based on it's gametype.
 */
public class FreecellModelCreator {

  /**
   * Creates the given game to support multimove or single move.
   *
   * @param type A given Gametype.
   * @return a FreecellOperations() for the game to implement
   * @throws IllegalArgumentException when type is null
   */
  public static FreecellModel<ICard> create(GameType type) {

    if (type == null) {
      throw new IllegalArgumentException("type cannot be null");
    }

    FreecellModel<ICard> modelType = null;

    if (type.equals(GameType.SINGLEMOVE)) {
      modelType = new SimpleFreecellModel();
    } else if (type.equals(GameType.MULTIMOVE)) {
      modelType = new MultiMoveFreecellModel();
    }
    return modelType;
  }

  /**
   * enum that defines the possible gametypes (single-move and multimove).
   */
  public enum GameType {
    SINGLEMOVE, MULTIMOVE;

    /**
     * default empty constructor for gametype.
     */
    GameType() {
      //empty because theres no fields and only two types.
    }

  }
}
