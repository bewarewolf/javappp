package com.nixsolutions.university.ws.soap;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.nixsolutions.university.model.Semester;
import com.nixsolutions.university.ws.soap.semester.GetSemesterRequest;
import com.nixsolutions.university.ws.soap.semester.GetSemesterResponse;
import com.nixsolutions.university.ws.soap.semester.ListSemesterRequest;
import com.nixsolutions.university.ws.soap.semester.ListSemesterResponse;

public class SemesterConsumer extends WebServiceGatewaySupport {

  private final String URL = "http://localhost:8090/soap/semester";
  
  public Semester getSemester(Integer id) {

    GetSemesterRequest request = SemesterUtil.getObjectFactory().createGetSemesterRequest();
    request.setId(id);

    GetSemesterResponse response = (GetSemesterResponse) getWebServiceTemplate()
	.marshalSendAndReceive(URL, request);

    return SemesterUtil.convertToModel(response.getSemester());
  }

  public List<Semester> listSemesters(Integer id) {

    ListSemesterRequest request = SemesterUtil.getObjectFactory().createListSemesterRequest();

    ListSemesterResponse response = (ListSemesterResponse) getWebServiceTemplate()
	.marshalSendAndReceive(URL, request);

    return response.getSemester().stream().map(s -> SemesterUtil.convertToModel(s)).collect(Collectors.toList());
  }
}
