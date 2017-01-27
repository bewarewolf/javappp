<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page import="com.nixsolutions.jdbc.bean.*,java.util.*"%>
<jsp:useBean id="utils" class="com.nixsolutions.jsp.servlet.utils.DAOUtils" scope="session"/>
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
            <c:if test="${sessionScope.role eq 'Dean'}">
              <th>Actions</th>   
            </c:if>      
         </tr>
         <c:forEach var="sem" items="${semesters}">
            <tr>
               <td><c:out value="${sem.semesterName}" /></td>
               <td><c:out value="${sem.startDate}" /></td>
               <td><c:out value="${sem.endDate}" /></td>
               <c:if test="${sessionScope.role eq 'Dean'}">
                  <td class="actions">
                    <div>
                      <div class="actionEdit">
                         <form action="admin/addSemester" method='post'>
                           <input type="hidden" name="semesterId" value="${sem.id}" />
                           <input type="submit" value="Edit" />
                         </form>
                      </div>
                      <div class="actionDelete">
                         <form action="admin/deleteSemester" method='post'
                           onsubmit="return confirm('Do you want to delete this semester?')">
                           <input type="submit" value="Delete" />
                           <input type="hidden" name="semesterId" value="${sem.id}" />
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
           <form action="admin/addSemester" method='post'>
             <input type="submit" value="Add semester" />
           </form>
        </div>
     </c:if>
    </jsp:attribute>
</t:default>