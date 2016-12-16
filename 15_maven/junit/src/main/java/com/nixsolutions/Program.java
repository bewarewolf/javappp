package com.nixsolutions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Program {

  private File fOut;
  private Robot robot = new Robot();
  private FileUtils utils = new FileUtils();

  public void setOutput(File fOut) {
    this.fOut = fOut;
  }
  
  public void processRoute(String route) throws IOException {
    try (BufferedWriter bw = utils.createWriter(fOut)) {
      robot.setDirection(Robot.Direction.X_POS);
      robot.setWriter(bw);
      
      for (char goal : route.toCharArray()) {
        switch (goal) {
        case 'l':
          robot.turnLeft();
          break;
        case 'r':
          robot.turnRight();
          break;
        case 'f':
          robot.stepForward();
          break;
        default:
          throw new RuntimeException("Unknown goal");
        }
      }
    }
  }
  
  public class FileUtils {
    public BufferedWriter createWriter(File f) throws IOException {
      return new BufferedWriter(new FileWriter(f));
    }
  }
}
