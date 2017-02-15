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
        <h2>You don't have permissions to visit this page</h2>
    </jsp:attribute>
</t:default>