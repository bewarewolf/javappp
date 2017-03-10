package com.nixsolutions.university.ws.soap;

import javax.xml.transform.Source;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.ResourceSource;
import static org.springframework.ws.test.server.RequestCreators.*;
import static org.springframework.ws.test.server.ResponseMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:service-ws-servlet.xml")
public class SemesterEndpointTest {

  @Autowired
  private ApplicationContext applicationContext;

  private MockWebServiceClient mockClient;

  @Before
  public void createClient() throws Exception {
    mockClient = MockWebServiceClient.createClient(applicationContext);
  }

  @Test 
  public void shouldGetSemesterById() throws Exception {
    // given
    Source requestPayload = new ResourceSource(new ClassPathResource("soap/semesterByIdReq.xml"));
    Source expectedResponsePayload = new ResourceSource(new ClassPathResource("soap/semesterById.xml"));

    // then
    mockClient.sendRequest(withPayload(requestPayload)).andExpect(payload(expectedResponsePayload));
  }
  
  @Test 
  public void shouldCreateSemester() throws Exception {
    // given
    Source requestPayload = new ResourceSource(new ClassPathResource("soap/createSemesterReq.xml"));
    Source expectedResponsePayload = new ResourceSource(new ClassPathResource("soap/createSemester.xml"));

    // then
    mockClient.sendRequest(withPayload(requestPayload)).andExpect(payload(expectedResponsePayload));
  }
  
  @Test 
  public void shouldUpdateSemester() throws Exception {
    // given
    Source requestPayload = new ResourceSource(new ClassPathResource("soap/updateSemesterReq.xml"));
    Source expectedResponsePayload = new ResourceSource(new ClassPathResource("soap/updateSemester.xml"));

    // then
    mockClient.sendRequest(withPayload(requestPayload)).andExpect(payload(expectedResponsePayload));
  }
  
  @Test 
  public void shouldDeleteSemester() throws Exception {
    // given
    Source requestPayload = new ResourceSource(new ClassPathResource("soap/deleteSemesterReq.xml"));
    Source expectedResponsePayload = new ResourceSource(new ClassPathResource("soap/deleteSemester.xml"));

    // then
    mockClient.sendRequest(withPayload(requestPayload)).andExpect(payload(expectedResponsePayload));
  }
}
