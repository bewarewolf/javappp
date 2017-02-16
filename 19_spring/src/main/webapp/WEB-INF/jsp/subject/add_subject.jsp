<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<t:default title="Subject details">
   <jsp:attribute name="head_area">
        <script type="text/javascript">
                           function myFunction() {
                              var form = document
                                    .getElementById("form");
                           }
                        </script>
    </jsp:attribute>
   <jsp:attribute name="content_area">
    <form:form action="/subjects/process" commandName="subject" method="post">
      <form:hidden path="id" />
      <table id="add_person_table">
        <tr>
          <td><form:label path="name">Name</form:label></td>
          <td><form:input path="name"/></td>
        </tr>
        <tr>
          <td><form:label path="teacher">Teacher</form:label></td>
          <td>
            <form:select path="teacher">
              <form:options items="${teacherList}" />
            </form:select>
          </td>
        </tr>
        <tr>
          <td><form:label path="semester">Semester</form:label></td>
          <td>
            <form:select path="semester">
              <form:options items="${semesterList}" />
            </form:select>
          </td>
        </tr>
      </table>
      <input type="submit" value="Process" />
      <a href="<c:url value="/subjects"/>">Cancel</a>
    </form:form>
   </jsp:attribute>
</t:default>