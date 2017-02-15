<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page import="com.nixsolutions.jdbc.bean.*,java.util.*"%>
<jsp:useBean id="utils" class="com.nixsolutions.jsp.servlet.utils.DAOUtils" scope="session"/>
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
    <form action="processSemester" method="post">
      <input type="hidden" name="semesterId" value="${semester.id}">
      <table id="add_person_table">
        <tr>
          <td><label>Name</label></td>
          <td><input type="text" name="semesterName" value="${semester.semesterName}"/></td>
        </tr>
        <tr>
          <td><label>Start date</label></td>
          <td><input type="date" name="startDate" value="${semester.startDate}"/></td>
        </tr>
        <tr>
          <td><label>End date</label></td>
          <td><input type="date" name="endDate" value="${semester.endDate}"/></td>
        </tr>
      </table>
      <input type="submit" value="Process" />
      <a href="<c:url value="/semesters"/>">Cancel</a>
    </form>
   </jsp:attribute>
</t:default>