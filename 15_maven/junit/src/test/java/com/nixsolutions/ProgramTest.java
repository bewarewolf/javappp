package com.nixsolutions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class ProgramTest {

  private Program prog = new Program();
  private File outFile;
  
  private String route = "lffrflfrrfff";
  private String[] coord = {
      "[0,1]",
      "[0,2]",
      "[1,2]",
      "[1,3]",
      "[1,2]",
      "[1,1]",
      "[1,0]"
  };
  
  @Rule
  public TemporaryFolder temp = new TemporaryFolder();
  
  @Before
  public void setup() throws IOException {
    outFile = temp.newFile();
    
    prog = new Program();    
    prog.setOutput(outFile);
  }
  
  @Test
  public void shouldWriteRouteToFile() throws IOException {
    // when
    prog.processRoute(route);
    
    // then
    String[] result = Files.readAllLines(outFile.toPath()).toArray(new String[0]);
    
    Assert.assertArrayEquals(coord, result);
  }
}
