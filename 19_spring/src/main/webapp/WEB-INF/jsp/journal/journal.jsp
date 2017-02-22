<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<t:default title="Journal">
  <jsp:attribute name="head_area">
    <script type="text/javascript">
      function myFunction() {
        var form = document
          .getElementById("form");
      }
    </script>
  </jsp:attribute>
  <jsp:attribute name="content_area">
    <div id="dropdowns">   
      <form:form commandName="map" action="/journal" method="get">
        <form:label path="subject">Subject: </form:label>
        <form:select path="subject" onchange="this.form.submit()">
          <form:option value="ALL" label="--- Select ---"/>
          <form:options items="${subjectList}" />
        </form:select>
        <sec:authorize access="hasAnyRole('Dean','Teacher')">
          <form:label path="student">Student: </form:label>
          <form:select path="student" onchange="this.form.submit()">
            <form:option value="ALL" label="--- Select ---"/>
            <form:options items="${studentList}" />
          </form:select> 
        </sec:authorize>         
      </form:form>   
    </div>
    <div id="tableDiv">
      <table id="entity_list">
        <tr>
          <th>Student</th>
          <th>Subject</th>
          <th>Grade</th>
        </tr>
        <c:forEach var="rec" items="${journal}">
          <tr>
            <td><c:out value="${rec.student}" /></td>
            <td><c:out value="${rec.subject}" /></td>
            <td><c:out value="${rec.grade}" /></td>
          </tr>
        </c:forEach>
      </table>
    </div>
  </jsp:attribute>
</t:default>