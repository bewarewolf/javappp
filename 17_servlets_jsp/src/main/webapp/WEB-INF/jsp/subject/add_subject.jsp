<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page import="com.nixsolutions.jdbc.bean.*,java.util.*"%>
<jsp:useBean id="utils" class="com.nixsolutions.jsp.servlet.utils.DAOUtils" scope="session"/>
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
    <form action="processSubject" method="post">
      <input type="hidden" name="subjectId" value="${subject.id}">
      <table id="add_person_table">
        <tr>
          <td><label>Name</label></td>
          <td><input type="text" name="subjName" value="${subject.name}"/></td>
        </tr>
        <tr>
          <td><label>Teacher</label></td>
          <td>
            <select name="teacher">
              <c:forEach var="teach" items="${teachers}">
                <option value="${teach.id}">${teach.name}</option>
              </c:forEach>
            </select>
          </td>
        </tr>
        <tr>
          <td><label>Semester</label></td>
          <td>
            <select name="semester">
              <c:forEach var="sem" items="${semesters}">
                <option value="${sem.id}">${sem.semesterName}</option>
              </c:forEach>
            </select>
          </td>
        </tr>
      </table>
      <input type="submit" value="Process" />
      <a href="<c:url value="/subjects"/>">Cancel</a>
    </form>
   </jsp:attribute>
</t:default>