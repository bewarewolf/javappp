package com.nixsolutions;

import java.io.BufferedWriter;
import java.io.IOException;

import static org.mockito.BDDMockito.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RobotMockTest {

  @Mock private BufferedWriter buff;
  @InjectMocks private Robot robot;
  
  @Test
  public void shouldStepForward() throws IOException {
    // given
    willDoNothing().given(buff).write(anyString());
    
    //when
    robot.stepForward();
    
    // then
    verify(buff).write("[1,0]");
  }
  
  @Test
  public void shouldTurnLeftAndStepForward() throws IOException {
    // given
    willDoNothing().given(buff).write(anyString());
    
    //when
    robot.turnLeft();
    robot.stepForward();
    
    // then
    verify(buff).write("[0,1]");
  }
  
  @Test
  public void shouldTurnRightAndStepForward() throws IOException {
    // given
    willDoNothing().given(buff).write(anyString());
    
    //when
    robot.turnRight();
    robot.stepForward();
    
    // then
    verify(buff).write("[0,-1]");
  }
}
