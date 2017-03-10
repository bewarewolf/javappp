package com.nixsolutions.university.ws.soap;

import java.util.List;
import java.util.stream.Collectors;

import javax.xml.datatype.DatatypeConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.nixsolutions.university.service.SemesterService;
import com.nixsolutions.university.ws.soap.semester.CreateSemesterRequest;
import com.nixsolutions.university.ws.soap.semester.CreateSemesterResponse;
import com.nixsolutions.university.ws.soap.semester.DeleteSemesterRequest;
import com.nixsolutions.university.ws.soap.semester.DeleteSemesterResponse;
import com.nixsolutions.university.ws.soap.semester.GetSemesterRequest;
import com.nixsolutions.university.ws.soap.semester.GetSemesterResponse;
import com.nixsolutions.university.ws.soap.semester.ListSemesterRequest;
import com.nixsolutions.university.ws.soap.semester.ListSemesterResponse;
import com.nixsolutions.university.ws.soap.semester.ObjectFactory;
import com.nixsolutions.university.ws.soap.semester.UpdateSemesterRequest;
import com.nixsolutions.university.ws.soap.semester.UpdateSemesterResponse;

@Endpoint
public class SemesterEndpoint {
  
  private final SemesterService semesterService;
  
  private final ObjectFactory objectFactory = new ObjectFactory();
  
  private final String NAMESPACE = "http://nixsolutions.com/university/soap/semester";
  
  @Autowired
  public SemesterEndpoint(SemesterService service) {
    this.semesterService = service;
  }
  
  @PayloadRoot(localPart = "getSemesterRequest", namespace = NAMESPACE)
  @ResponsePayload
  public GetSemesterResponse getSemester(@RequestPayload GetSemesterRequest semesterRequest) 
      throws DatatypeConfigurationException {
    GetSemesterResponse resp = objectFactory.createGetSemesterResponse();        
    resp.setSemester(SemesterUtil.convertFromModel(semesterService.getById(semesterRequest.getId())));
    return resp;
  }
  
  @PayloadRoot(localPart = "createSemesterRequest", namespace = NAMESPACE)
  @ResponsePayload
  public CreateSemesterResponse createSemester(@RequestPayload CreateSemesterRequest semesterRequest) {
    CreateSemesterResponse resp = objectFactory.createCreateSemesterResponse();
    Integer id = semesterService.saveOrUpdate(semesterRequest.getSemester());
    resp.setId(id);
    return resp;
  }
  
  @PayloadRoot(localPart = "updateSemesterRequest", namespace = NAMESPACE)
  @ResponsePayload
  public UpdateSemesterResponse updateSemester(@RequestPayload UpdateSemesterRequest semesterRequest) {
    UpdateSemesterResponse resp = objectFactory.createUpdateSemesterResponse();
    semesterService.saveOrUpdate(semesterRequest.getSemester());
    resp.setStatus("success");
    return resp;
  }
  
  @PayloadRoot(localPart = "listSemesterRequest", namespace = NAMESPACE)
  @ResponsePayload
  public ListSemesterResponse listSemester(@RequestPayload ListSemesterRequest semesterRequest) {
    ListSemesterResponse resp = objectFactory.createListSemesterResponse();
    List<com.nixsolutions.university.model.Semester> out = semesterService.getAll();
    resp.getSemester().addAll(out.stream().map(s -> SemesterUtil.convertFromModel(s)).collect(Collectors.toList()));
    return resp;
  }
  
  @PayloadRoot(localPart = "deleteSemesterRequest", namespace = NAMESPACE)
  @ResponsePayload
  public DeleteSemesterResponse deleteSemester(@RequestPayload DeleteSemesterRequest semesterRequest) {
    DeleteSemesterResponse resp = objectFactory.createDeleteSemesterResponse();
    semesterService.delete(semesterRequest.getId());
    resp.setStatus("success");
    return resp;
  }
  
  
}
