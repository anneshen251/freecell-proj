package cs3500.freecell.view;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.FreecellModelState;

import java.io.IOException;

/**
 * represents a free cell model as a text view, helps to display the state of the free cell game.
 */
public class FreecellTextView implements FreecellView {
  private final FreecellModelState<?> model;
  private final Appendable output;

  /**
   * the constructor for FreecellTextView.
   *
   * @param model the given FreecellModel
   */
  public FreecellTextView(FreecellModelState<?> model) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.model = model;
    this.output = null;
  }

  /**
   * the constructor for FreecellTextView with appendable.
   *
   * @param model  the given FreecellModel
   * @param output the given Appendable to output
   */
  public FreecellTextView(FreecellModel<?> model, Appendable output) {
    this.model = model;
    this.output = output;
  }

  @Override
  public String toString() {
    return model.toString();
  }

  /**
   * Render the board to the provided data destination. The board should be rendered exactly
   * in the format produced by the toString method above
   *
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  @Override
  public void renderBoard() throws IOException {
    try {
      if (this.output == null) {
        System.out.println(model.toString());
      } else if (this.model == null) {
        output.append("");
      } else {
        output.append(model.toString() + "\n");
      }
    } catch (IOException e) {
      throw new IOException("cannot transmit to provided data destination.");
    }
  }

  /**
   * Render a specific message to the provided data destination.
   *
   * @param message the message to be transmitted
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  @Override
  public void renderMessage(String message) throws IOException {
    try {
      if (message == null) {
        output.append("");
      } else {
        output.append(message);
      }
    } catch (IOException e) {
      throw new IOException("cannot transmit to provided data destination.");
    }
  }


}
