<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
            <!-- check if dean -->
              <th>Actions</th>   
            
         </tr>
         <c:forEach var="sem" items="${semesters}">
            <tr>
               <td><c:out value="${sem.semesterName}" /></td>
               <td><c:out value="${sem.startDate}" /></td>
               <td><c:out value="${sem.endDate}" /></td>
               <!-- check if dean -->
                  <td class="actions">
                    <div>
                      <div class="actionEdit">
                         <form action="/semesters/edit/${sem.id}" method='get'>
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
               
            </tr>
         </c:forEach>
       </table>
    </div>
    <!-- check if dean -->
       <div class="actionAdd">
           <form action="admin/addSemester" method='post'>
             <input type="submit" value="Add semester" />
           </form>
        </div>
     
    </jsp:attribute>
</t:default>