<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<t:default title="Semester details">
   <jsp:attribute name="head_area">
        <script type="text/javascript">
                           function myFunction() {
                              var form = document
                                    .getElementById("form");
                           }
                        </script>
    </jsp:attribute>
   <jsp:attribute name="content_area">
    <form:form action="/semesters/process" commandName="semester" method="post">
      <form:hidden path="id" />
      <table id="add_person_table">
        <tr>
          <td><form:label path="semesterName">Name</form:label></td>
          <td><form:input path="semesterName"/></td>
        </tr>
        <tr>
          <td><form:label path="semesterName">Start date</form:label></td>
          <td><form:input type="date" path="startDate"/></td>
        </tr>
        <tr>
          <td><form:label path="semesterName">End date</form:label></td>
          <td><form:input type="date" path="endDate"/></td>
        </tr>
      </table>
      <input type="submit" value="Process" />
      <a href="<c:url value="/semesters"/>">Back</a>
    </form:form>
   </jsp:attribute>
</t:default>