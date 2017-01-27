<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page import="com.nixsolutions.jdbc.bean.*,java.util.*"%>
<jsp:useBean id="utils"
	class="com.nixsolutions.jsp.servlet.utils.DAOUtils" scope="session" />
<t:default title="Journal">
	<jsp:attribute name="head_area">
        <script type="text/javascript">
									function myFunction() {
										var form = document
												.getElementById("form");
									}
								</script>
    </jsp:attribute>
	<jsp:attribute name="content_area">
     <div id="dropdowns">   
       <form action="journal">    
        <select name="subject" onchange="this.form.submit()">
          <c:forEach var="subj" items="${subjects}">
            <option value="${subj}" ${subj == selectedSubj ? 'selected="selected"' : ''}>${subj}</option>
          </c:forEach>
        </select> 
        <select name="student" onchange="this.form.submit()">
          <c:forEach var="stud" items="${students}">
            <option value="${stud}" ${stud == selectedStud ? 'selected="selected"' : ''}>${stud}</option>
          </c:forEach>
        </select>
        </form>   
		</div>
     <div id="tableDiv">
       <table id="entity_list">
         <tr>         
            <th>Student</th>
            <th>Subject</th>
            <th>Grade</th>
         </tr>
         <c:forEach var="rec" items="${journal}">
            <tr>
               <td><c:out value="${rec.student}" /></td>
               <td><c:out value="${rec.subject}" /></td>
               <td><c:out value="${rec.grade}" /></td>
            </tr>
         </c:forEach>
       </table>
      </div>
    </jsp:attribute>
</t:default>