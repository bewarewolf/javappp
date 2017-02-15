<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
    <form:form action="/persons/process" method="post"
					commandName="person">  
	      <table>	      
	        <tr>
             <td><form:label path="login">Login</form:label></td>
             <td><form:input path="login" /></td>
           </tr>
           <tr>
             <td><form:label path="password">Password</form:label></td>
             <td><form:input path="password" /></td>
           </tr>
	        <tr>
	          <td><form:label path="firstName">First name</form:label></td>
	          <td><form:input path="firstName" /></td>
	        </tr>
	        <tr>
	          <td><form:label path="middleName">Middle name</form:label></td>
	          <td><form:input path="middleName" /></td>
	        </tr>
	        <tr>
	          <td><form:label path="lastName">Last name</form:label></td>
	          <td><form:input path="lastName" /></td>
	        </tr>
	        <tr>
	          <td><form:label path="birthday">Birthday</form:label></td>
	          <td>
	            <form:input type="date" path="birthday" />
				 </td>
	        </tr>
	        <tr>	        
	          <td><form:label path="startDate">Start date</form:label></td>
	          <td>
	            <form:input type="date" path="startDate" />
				 </td>
	        </tr>
	        <tr>
	          <td><form:label path="personStatus">Person Status</form:label></td>
	          <td>
	            <form:select path="personStatus">
	              <form:options items="${statusList}" />
	            </form:select>
	          </td>
	        </tr>
	        <tr>
	          <td><form:label path="personType">Person Type</form:label></td>
	          <td>
	            <form:select path="personType">
	              <form:options items="${typeList}" />
	            </form:select>
	          </td>
	        </tr>
	        
	        <tr>
	          <td colspan="2">
	            <input type="submit" value="Process" />
	            <a href="<c:url value="/persons"/>" class="buttonCancel">Back</a>
	          </td>
	        </tr>
	        <tr>
           <td colspan = 2>
             <form:hidden path="id" />
           </td>
         </tr>
	      </table>
    </form:form>
    </div>
    <div id="phone_table">
      <h3>Phone numbers</h3>
      <c:if test="${fn:length(person.phoneNumbers) gt 0}">
	      <table>	        
	          <c:forEach var="phone" items="${person.phoneNumbers}">
	            <tr>
	              <td><c:out value="${phone.phoneNumber}" /></td>
  	              <td>
                  <form:form
										action="/persons/${person.id}/deletePhone/${phone.id}"
										method='post'
										onsubmit="return confirm('Do you want to delete this phone number?')">
                    <input type="submit" value="Delete" />
                  </form:form>
                 </td>
	            </tr>
	          </c:forEach>
	      </table>	      
        </c:if>
        <c:if test="${person != null}">
          <form:form method="post" commandName="phoneNumber"
						action="/persons/${person.id}/addPhone">
            <form:input path="phoneNumber" />
            <input type="submit" value="Add" />
          </form:form>
        </c:if>
      </div>
    </div>
    
	</jsp:attribute>
</t:default>