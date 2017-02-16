<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<t:default title="Semesters">
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
       <table id="entity_list">
         <tr>         
            <th>Name</th>
            <th>Start date</th>
            <th>End date</th>
            <sec:authorize access="hasRole('Dean')">
              <th>Actions</th>   
            </sec:authorize>
         </tr>
         <c:forEach var="sem" items="${semesters}">
            <tr>
               <td><c:out value="${sem.semesterName}" /></td>
               <td><c:out value="${sem.startDate}" /></td>
               <td><c:out value="${sem.endDate}" /></td>
               <sec:authorize access="hasRole('Dean')">
                  <td class="actions">
                    <div>
                      <div class="actionEdit">
                         <form action="/semesters/edit/${sem.id}" method='post'>
                           <input type="submit" value="Edit" />
                         </form>
                      </div>
                      <div class="actionDelete">
                         <form:form action="/semesters/delete/${sem.id}" method='post'
                           onsubmit="return confirm('Do you want to delete this semester?')">
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
           <form action="/semesters/add" method='post'>
             <input type="submit" value="Add semester" />
           </form>
        </div>
     </sec:authorize>
    </jsp:attribute>
</t:default>