<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
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
          <th>Name</th>
          <th>Type</th>
          <th>Status</th>
          <sec:authorize access="hasRole('Dean')">
            <th>Actions</th>   
          </sec:authorize>
        </tr>
        <c:forEach var="person" items="${persons}">
          <tr>
            <td><c:out value="${person.login}" /></td>
            <td><c:out value="${person.name}" /></td>
            <td><c:out value="${person.personType}" /></td>
            <td><c:out value="${person.personStatus}" /></td>
            <sec:authorize access="hasRole('Dean')">
              <td class="actions">
                <div>
                  <div class="actionEdit">
                    <form action="/persons/edit/${person.id}" method='post'>
                      <input type="submit" value="Edit" />
                    </form>
                  </div>
                  <div class="actionDelete">
                    <form:form action="/persons/delete/${person.id}" method='post'
                        onsubmit="return confirm('Do you want to delete this person?')">
                      <input type="submit" value="Delete" />
                    </form:form>
                  </div>
                </div>
              </td>
            </sec:authorize>
          </tr>
        </c:forEach>
      </table>
    </div>
    <sec:authorize access="hasRole('Dean')">
      <div class="actionAdd">
          <form action="/persons/add" method='post'>
            <input type="submit" value="Add person" />
          </form>
      </div>
    </sec:authorize>
  </jsp:attribute>
</t:default>