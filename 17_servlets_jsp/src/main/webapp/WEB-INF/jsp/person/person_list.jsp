<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page import="com.nixsolutions.jdbc.bean.*,java.util.*"%>
<jsp:useBean id="utils" class="com.nixsolutions.jsp.servlet.utils.DAOUtils" scope="session"/>
<t:default title="Persons">
	<jsp:attribute name="head_area">
        <script type="text/javascript">
									function myFunction() {
										var form = document
												.getElementById("form");
									}
								</script>
    </jsp:attribute>
	<jsp:attribute name="content_area">
	  <div id="tableDiv">
	    <table id="person_list">
	      <tr>         
	         <th>Login</th>
				<th>First name</th>
	         <th>Last name</th>
	         <th>Type</th>
	         <th>Status</th>
	         <c:if test="${sessionScope.role eq 'Dean'}">
	           <th>Actions</th>   
	         </c:if>      
	      </tr>
	      <c:forEach var="person" items="${persons}">
	         <tr>
	            <td><c:out value="${person.login}" /></td>
	            <td><c:out value="${person.firstName}" /></td>
	            <td><c:out value="${person.lastName}" /></td>
	            <td><c:out value="${utils.getPersonTypeById(person.personTypeId).value}" /></td>
	            <td><c:out value="${utils.getPersonStatusById(person.personStatusId).value}" /></td>
	            <c:if test="${sessionScope.role eq 'Dean'}">
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
	            </c:if>
	         </tr>
	      </c:forEach>
	    </table>
    </div>
    <c:if test="${sessionScope.role eq 'Dean'}">
	    <div class="actionAdd">
	        <form action="addPerson" method='get'>
	          <input type="submit" value="Add person" />
	        </form>
	     </div>
     </c:if>
    </jsp:attribute>
</t:default>