<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<t:default title="Home">
  <jsp:attribute name="head_area">
    <script type="text/javascript">
      function myFunction() {
        var form = document
            .getElementById("form");
      }
    </script>
  </jsp:attribute>
  <jsp:attribute name="content_area">
    <h1>Welcome, 
      <sec:authorize access="isAuthenticated()">
        <p><sec:authentication property="principal.username" /></p>
      </sec:authorize>
    </h1>
  </jsp:attribute>
</t:default>