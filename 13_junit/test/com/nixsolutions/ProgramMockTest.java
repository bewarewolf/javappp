package com.nixsolutions;

import static org.mockito.BDDMockito.*;

import java.io.BufferedWriter;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProgramMockTest {

  @Mock private Program.FileUtils utils;
  @Mock private Robot robot;
  @Mock private BufferedWriter writer;
  @InjectMocks private Program program;

  @Before
  public void setup() throws IOException {   
    when(utils.createWriter(null)).thenReturn(writer);
    //willDoNothing().given(robot).stepForward();
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
  
  @Test
  public void shouldProcessRoute() throws IOException {    
    // when
    program.processRoute("lffrflfrrfff");

    // then
    verify(robot, times(7)).stepForward();
    verify(robot, times(2)).turnLeft();
    verify(robot, times(3)).turnRight();
  }
  
  @Test
  public void shouldWriteRoute() throws IOException {
    // given
    willCallRealMethod().given(robot).stepForward();
    willCallRealMethod().given(robot).turnLeft();
    willCallRealMethod().given(robot).turnRight();
    willCallRealMethod().given(robot).setWriter(any(BufferedWriter.class));
    willCallRealMethod().given(robot).setDirection(any(Robot.Direction.class));
    
    robot.setWriter(writer);
    robot.setDirection(Robot.Direction.X_POS);
    
    String[] coord = {
	      "[0,1]",
	      "[0,2]",
	      "[1,2]",
	      "[1,3]",
	      "[1,2]",
	      "[1,1]",
	      "[1,0]"
	  };
    
    // when
    program.processRoute("lffrflfrrfff");

    // then
        
    InOrder order = inOrder(writer);
    
    for (String strCoord : coord) {
      order.verify(writer).write(strCoord);
    }
  }
}
