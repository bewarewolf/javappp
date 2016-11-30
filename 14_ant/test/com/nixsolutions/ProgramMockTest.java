package com.nixsolutions;

import static org.mockito.BDDMockito.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProgramMockTest {

  @Mock private Program.FileUtils utils;
  @Mock private File fOut;  
  @Mock private Robot robot;
  @Mock private BufferedWriter writer;
  @InjectMocks private Program program;

  @Before
  public void setup() throws IOException {   
    when(utils.createWriter(null)).thenReturn(writer);
    willDoNothing().given(robot).stepForward();
  }

  @Test
  public void shouldProcessTurnLeft() throws IOException {
    // when
    program.processRoute("l");

    // then
    verify(robot).turnLeft();
  }
  
  @Test
  public void shouldProcessTurnRight() throws IOException {     
    // when
    program.processRoute("r");

    // then
    verify(robot).turnRight();
  }
  
  @Test
  public void shouldProcessStepForward() throws IOException {
    // when
    program.processRoute("f");

    // then
    verify(robot).stepForward();
  }
}
