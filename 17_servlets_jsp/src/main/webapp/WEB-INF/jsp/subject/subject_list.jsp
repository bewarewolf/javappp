<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page import="com.nixsolutions.jdbc.bean.*,java.util.*"%>
<jsp:useBean id="utils" class="com.nixsolutions.jsp.servlet.utils.DAOUtils" scope="session"/>
<t:default title="Subjects">
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
            <th>Teacher</th>
            <th>Semester</th>
            <c:if test="${sessionScope.role eq 'Dean'}">
              <th>Actions</th>   
            </c:if>      
         </tr>
         <c:forEach var="subj" items="${subjects}">
            <tr>
               <td><c:out value="${subj.name}" /></td>
               <td><c:out value="${subj.teacher}" /></td>
               <td><c:out value="${subj.semester}" /></td>
               <c:if test="${sessionScope.role eq 'Dean'}">
                  <td class="actions">
                    <div>
                      <div class="actionEdit">
                         <form action="admin/addSubject" method='post'>
                           <input type="hidden" name="subjectId" value="${subj.id}" />
                           <input type="submit" value="Edit" />
                         </form>
                      </div>
                      <div class="actionDelete">
                         <form action="admin/deleteSubject" method='post'
                           onsubmit="return confirm('Do you want to delete this subject?')">
                           <input type="submit" value="Delete" />
                           <input type="hidden" name="subjectId" value="${subj.id}" />
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
           <form action="admin/addSubject" method='post'>
             <input type="submit" value="Add subject" />
           </form>
        </div>
     </c:if>
    </jsp:attribute>
</t:default>