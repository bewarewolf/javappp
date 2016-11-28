package com.nixsolutions;

import java.io.BufferedWriter;
import java.io.IOException;

public class Robot {

  private int x;
  private int y;
  private Direction direction = Direction.X_POS;
  private BufferedWriter writer;
  
  public void setWriter(BufferedWriter fw) {
    writer = fw;
  }
  
  public void turnLeft() {
    turn(true);
  }
  
  public void turnRight() {
    turn(false);
  }
  
  public void stepForward() throws IOException {
    switch (direction) {
    case X_NEG:
      x--;
      break;
    case X_POS:
      x++;
      break;
    case Y_NEG:
      y--;
      break;
    case Y_POS:
      y++;
      break;
    }
      
    String coord = String.format("[%d,%d]", x, y);
    
    writer.write(coord);
    writer.newLine();
    writer.flush();
  }
  
  /**
   * Changes direction 
   * @param left If true - turns left, else turns right
   */
  private void turn(boolean left) {
    switch (direction) {
    case X_NEG:
      direction = left ? Direction.Y_NEG : Direction.Y_POS;
      break;
    case X_POS:
      direction = left ? Direction.Y_POS : Direction.Y_NEG;
      break;
    case Y_NEG:
      direction = left ? Direction.X_POS : Direction.X_NEG;
      break;
    case Y_POS:
      direction = left ? Direction.X_NEG : Direction.X_POS;
      break;
    }
  }
  
  private enum Direction {
    X_POS,
    X_NEG,
    Y_POS,
    Y_NEG
  }
}
