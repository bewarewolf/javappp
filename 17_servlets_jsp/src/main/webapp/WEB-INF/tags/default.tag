<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="title"%>
<%@ attribute name="head_area" fragment="true"%>
<%@ attribute name="content_area" fragment="true"%>
<%@ attribute name="sidebar_area" fragment="true"%>

<c:url value="/css/style.css" var="cssUrl"/>
<html>

<head>
  <title>${title}</title>  
  <meta http-equiv="content-type" content="text/html; charset=windows-1252" />
  <link href="${cssUrl}" type="text/css" rel="stylesheet"/>
  <jsp:invoke fragment="head_area" />  
</head>

<body>
  <div id="main">
    <div id="header">
      <div id="logo">
        <div id="logo_text">
          <!-- class="logo_colour", allows you to change the colour of the text -->
          <h1><a href="index.html">University</a></h1>
          
        </div>
        <div id="user">
            <p>${sessionScope.user.firstName} ${sessionScope.user.lastName}
            <a href="<c:url value="/logout"/>">Logout</a></p>
          </div>
      </div>
      
    </div>
    <div id="bodyDiv">
	    <div id="menubar">
	        <ul id="menu">
	          <!-- put class="selected" in the li tag for the selected page - to highlight which page you're on -->
	          <li class="selected"><a href="<c:url value="/admin"/>">Home</a></li>
	          <c:if test="${sessionScope.role ne 'Student'}">
               <li><a href="<c:url value="/admin/persons"/>">Persons</a></li>
	          </c:if>
	          <li><a href="<c:url value="/subjects"/>">Subjects</a></li>
	          <li><a href="<c:url value="/semesters"/>">Semesters</a></li>
	          <li><a href="<c:url value="/journal"/>">Journal</a></li>
	        </ul>
	      </div>
	    <div id="site_content">
	      <!-- div class="sidebar">
	        <jsp:invoke fragment="sidebar_area" />
	      </div-->
	      
	      <div id="content">
	        <jsp:invoke fragment="content_area" />
	      </div>
	    </div>
    </div>
    <div id="footer">
        Copyright &copy; 2017 | All Rights Reserved
    </div>
  </div>
</body>
</html>