<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page import="com.nixsolutions.jdbc.bean.*,java.util.*"%>
<t:default title="Person list">
	<jsp:attribute name="head_area">
        <script type="text/javascript">
									function myFunction() {
										var form = document
												.getElementById("form");
									}
								</script>
    </jsp:attribute>
	<jsp:attribute name="content_area">
    <table id="person_list">
      <tr>         
         <th>Login</th>
			<th>First name</th>
			<th>Middle name</th>
         <th>Last name</th>
         <th>Birthday</th>
         <th>Start date</th>
         <th>Actions</th>         
      </tr>
      <c:forEach var="person" items="${persons}">
         <tr>
            <td><c:out value="${person.login}" /></td>
            <td><c:out value="${person.firstName}" /></td>
            <td><c:out value="${person.middleName}" /></td>
            <td><c:out value="${person.lastName}" /></td>
            <td><c:out value="${person.birthday}" /></td>
            <td><c:out value="${person.startDate}" /></td>
            <td class="actions">
              <div>
                <div class="actionEdit">
	                <form action="editPerson" method='post'>
	                  <input type="hidden" name="userId" value="${person.id}" />
	                  <input type="submit" value="Edit" />
	                </form>
                </div>
                <div class="actionDelete">
	                <form action="deletePerson" method='post'
	                  onsubmit="return confirm('Do you want to delete this person?')">
	                  <input type="submit" value="Delete" />
	                  <input type="hidden" name="userId" value="${person.id}" />
	                </form>
                </div>
              </div>
            </td>
         </tr>
      </c:forEach>
    </table>
    <div class="actionAddPerson">
        <form action="admin/addPerson" method='get'>
          <input type="submit" value="Add student" />
          <input type="hidden" name="entity" value="Student" />
        </form>
     </div>
    </jsp:attribute>
</t:default>