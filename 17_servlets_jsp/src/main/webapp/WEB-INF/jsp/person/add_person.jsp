<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page import="com.nixsolutions.jdbc.bean.*,java.util.*"%>
<jsp:useBean id="utils"
	class="com.nixsolutions.jsp.servlet.utils.DAOUtils" scope="session" />
<t:default title="Person list">
	<jsp:attribute name="head_area">
        <script type="text/javascript">
									function myFunction() {
										var form = document
												.getElementById("form");
									}
								</script>
		<meta name="viewport" content="width=device-width, initial-scale=1">
    </jsp:attribute>
	<jsp:attribute name="content_area">
	  <div id="user_info">
	  <div id="add_person_table">
    <form action="processPerson" method="post">
      <input type="hidden" name="personId" value="${person.id}">
      <input type="hidden" name="entity" value="${entity}">
        
	      <table>
	        <tr>
	          <td><label>First name</label></td>
	          <td><input type="text" name="fName"
								value="${person.firstName}" /></td>
	        </tr>
	        <tr>
	          <td><label>Middle name</label></td>
	          <td><input type="text" name="mName"
								value="${person.middleName}" /></td>
	        </tr>
	        <tr>
	          <td><label>Last name</label></td>
	          <td><input type="text" name="lName"
								value="${person.lastName}" /></td>
	        </tr>
	        <tr>
	          <td><label>Birthday</label></td>
	          <td><input type="date" name="birthday"
								value="${person.birthday}" /></td>
	        </tr>
	        <tr>
	          <td><label>Start date</label></td>
	          <td><input type="date" name="startDate"
								value="${person.startDate}" /></td>
	        </tr>
	        <tr>
	          <td><label>Person Status</label></td>
	          <td>
	            <select name="ps">
	              <c:forEach var="status" items="${utils.psList}">
	                <option value="${status.id}"
											${status.id == person.personStatusId ? 'selected="selected"' : ''}>${status.value}</option>
	              </c:forEach>
	            </select>
	          </td>
	        </tr>
	        <tr>
	          <td><label>Person Type</label></td>
	          <td>
	            <select name="pt">
	              <c:forEach var="type" items="${utils.ptList}">
	                <option value="${type.id}"
											${type.id == person.personTypeId ? 'selected="selected"' : ''}>${type.value}</option>
	              </c:forEach>
	            </select>
	          </td>
	        </tr>
	        <tr>
	          <td><label>Login</label></td>
	          <td><input type="text" name="login"
								value="${person.login}" /></td>
	        </tr>
	        <tr>
	          <td><label>Password</label></td>
	          <td><input type="password" name="password"
								value="${person.password}" /></td>
	        </tr>
	        <tr>
	          <td><label>Confirm password</label></td>
	          <td><input type="password" name="passwordConf"
								value="${person.password}" /></td>
	        </tr>
	      </table>
      <input type="submit" value="Process" />
      <a href="<c:url value="/admin/persons"/>" class="buttonCancel">Cancel</a>
    </form>
    </div>
    <div id="phone_table">
      <h3>Phone numbers</h3>
      <c:if test="${fn:length(phones) gt 0}">
	      <table>	        
	          <c:forEach var="phone" items="${phones}">
	            <tr>
	              <td><c:out value="${phone.phoneNumber}" /></td>
  	              <td>
                  <form action="deletePhone" method='post'
                      onsubmit="return confirm('Do you want to delete this phone number?')">
                    <input type="submit" value="Delete" />
                    <input type="hidden" name="phoneId" value="${phone.id}" />
                    <input type="hidden" name="userId" value="${person.id}" />
                  </form>
                 </td>
	            </tr>
	          </c:forEach>
	      </table>	      
        </c:if>
        <c:if test="${person != null}">
          <form method="post" action="insertPhone">
            <input type="hidden" name="userId" value="${person.id}" />
            <input type="text" name="number" />
            <input type="submit" value="Add" />
          </form>
        </c:if>
      </div>
    </div>
    
	</jsp:attribute>
</t:default>