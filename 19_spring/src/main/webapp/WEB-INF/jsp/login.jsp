<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:default title="Home">
    <jsp:attribute name="head_area">
        <script type="text/javascript">
            function myFunction () {
                var form = document.getElementById("form");
            }
        </script>
    </jsp:attribute>
    <jsp:attribute name="content_area">
        <div style="margin: 15% auto; width: 300px;">
         <form action="j_spring_security_check" method="post">
            <table>
               <tr>
                  <td>Login:</td>
                  <td><input type="text" name="j_username" /></td>
               </tr>
               <tr>
                  <td>Password:</td>
                  <td><input type="password" name="j_password" /></td>
               </tr>
            </table>
            <input type="submit" value="Login" />
         </form>
         </div>
         <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
           <font color="red">
              Your login attempt was not successful due to <br/><br/>
           <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.         
           </font>
         </c:if>
    </jsp:attribute>
</t:default>