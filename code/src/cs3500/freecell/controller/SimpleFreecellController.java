package cs3500.freecell.controller;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.ICard;
import cs3500.freecell.view.FreecellTextView;
import cs3500.freecell.view.FreecellView;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a Controller for a SimpleFreeCell game.
 * controls the input given by the user and output that is then sent back to user
 * and catches any invalid commands inputted by user.
 */
public class SimpleFreecellController implements FreecellController {
  private final Readable rd;
  private final FreecellModel model;
  private final FreecellView view;

  /**
   * constructor for simple freecell controller.
   *
   * @param model represents the given model
   * @param rd    represents readable for user input
   * @param ap    represents appendable to send output
   */
  public SimpleFreecellController(FreecellModel<ICard> model, Readable rd, Appendable ap) {

    if (model == null) {
      throw new IllegalArgumentException("model cannot be null");
    }

    if (rd == null || ap == null) {
      throw new IllegalArgumentException("Readable and/or Appendable can't be null");
    }

    this.rd = rd;
    this.model = model;
    this.view = new FreecellTextView(model, ap);

  }

  /**
   * Start and play a new game of freecell with the provided deck. This deck
   * should be used as-is. This method returns only when the game is over
   * (either by winning or by quitting)
   *
   * @param deck        the deck to be used to play this game
   * @param numCascades the number of cascade piles
   * @param numOpens    the number of open piles
   * @param shuffle     shuffle the deck if true, false otherwise
   * @throws IllegalStateException    if writing to the Appendable object used
   *                                  by it fails or reading from the provided Readable fails
   * @throws IllegalArgumentException if the model or deck provided to it are null
   */
  @Override
  public void playGame(List deck, int numCascades, int numOpens, boolean shuffle)
      throws IllegalStateException, IllegalArgumentException {

    if (deck == null) {
      throw new IllegalArgumentException("deck cannot be null");
    }

    if (numCascades < 4 || numOpens < 1) {
      appendMsg("Could not start game.");
      return;
    }

    try {
      model.startGame(deck, numCascades, numOpens, shuffle);
      view.renderBoard();
      makeMove();
      if (model.isGameOver()) {
        gameOver();
      }
    } catch (IOException e) {
      appendMsg(e.getMessage());
    }
  }

  /**
   * if game is over displays final game state and the game over message.
   *
   * @throws IOException if transmission of game state to destination fails
   */
  private void gameOver() throws IOException {
    view.renderBoard();
    appendMsg("Game over.");
  }

  /**
   * reads the user input and makes the given move if valid.
   *
   * @throws IOException if readable/scanner fails
   */
  private void makeMove() throws IOException {


    Scanner input = new Scanner(this.rd);

    int pileNum = -1;
    int destNum = -1;
    int cardIndex = -1;
    PileType type1 = null;
    PileType type2 = null;

    while (input.hasNext()) {
      String temp = input.next();

      if (this.model.isGameOver()) {
        gameOver();
        return;
      }

      if (temp.equalsIgnoreCase("q")) {
        appendMsg("Game quit prematurely.");
        return;
      }

      if (pileNum == -1 && type1 == null) {
        if (validPile(temp)) {
          type1 = returnType(temp.substring(0, 1));
          pileNum += Integer.parseInt(temp.substring(1));
        } else {
          appendMsg("source pile invalid, please try again." + "\n");
        }
      } else if (cardIndex == -1) {
        if (validCardIndex(temp)) {
          cardIndex += Integer.parseInt(temp);
        } else {
          appendMsg("card index invalid, please try again." + "\n");
        }
      } else if (type2 == null && destNum == -1) {
        if (validPile(temp)) {
          type2 = returnType(temp.substring(0, 1));
          destNum += Integer.parseInt(temp.substring(1));
        } else {
          appendMsg("invalid destination pile, please try again." + "\n");
        }
      }
      if (pileNum != -1 && type1 != null && type2 != null && cardIndex != -1 && destNum != -1) {
        try {
          this.model.move(type1, pileNum, cardIndex, type2, destNum);
          this.view.renderBoard();
          pileNum = -1;
          destNum = -1;
          cardIndex = -1;
          type1 = null;
          type2 = null;
        } catch (IllegalArgumentException e) {
          appendMsg("invalid move, try again: " + e.getMessage() + "\n");
          pileNum = -1;
          destNum = -1;
          cardIndex = -1;
          type1 = null;
          type2 = null;
        }
      }
    }

    if (!this.model.isGameOver()) {
      throw new IllegalStateException("ran out of input\n" + model.toString());
    } else {
      gameOver();
    }
  }

  /**
   * determines whether the card index command is valid or not.
   * An index is valid if it is an integer greater than one.
   *
   * @param command the command given as a String
   * @return true if the card index is valid and false otherwise.
   */
  private boolean validCardIndex(String command) {
    int value = -1;
    try {
      value = Integer.parseInt(command);
    } catch (NumberFormatException e) {
      return false;
    }
    return value > 0;
  }

  /**
   * determines whether the pile command is valid or not.
   * A pile is valid if the first letter starts with C, F, O
   * and has a number following it.
   *
   * @param command the command given as a String
   * @return true if the pile is valid and false otherwise.
   */
  private boolean validPile(String command) {
    int value;
    if (command.length() > 1) {
      if ("CFO".contains(command.substring(0, 1))) {
        try {
          value = Integer.parseInt(command.substring(1));
          return value > 0;
        } catch (NumberFormatException e) {
          return false;
        }
      }
    }
    return false;
  }

  /**
   * Takes in a single character string and returns the associated Piletype.
   *
   * @param symbol the single letter string that symbolizes the pile
   * @return the correct Piletype according to the symbol given
   */
  private PileType returnType(String symbol) {
    PileType finalPile;
    switch (symbol) {
      case "C":
        finalPile = PileType.CASCADE;
        break;
      case "O":
        finalPile = PileType.OPEN;
        break;
      case "F":
        finalPile = PileType.FOUNDATION;
        break;
      default:
        throw new IllegalArgumentException("Invalid symbol given.");
    }
    return finalPile;
  }

  /**
   * appends the given message to appendable.
   *
   * @param message the message to be appended
   * @throws IllegalStateException if can not append to appendable
   */
  private void appendMsg(String message) {
    try {
      this.view.renderMessage(message);
    } catch (IOException e) {
      throw new IllegalStateException("could not append message");
    }
  }
}

