<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="title"%>
<%@ attribute name="head_area" fragment="true"%>
<%@ attribute name="content_area" fragment="true"%>
<%@ attribute name="sidebar_area" fragment="true"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<c:url value="/resources/css/style.css" var="cssUrl"/>
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
          <h1><a href="<c:url value="/home"/>">University</a></h1>
          
        </div>
        <sec:authorize access="isAuthenticated()">
          <div id="user">          
            <p><sec:authentication property="principal.username" />
            <a href="<c:url value="/logout"/>">Logout</a></p>
          </div>
        </sec:authorize>
      </div>
      
    </div>
    <div id="bodyDiv">
	    <div id="menubar">
	        <ul id="menu">
	          <sec:authorize access="isAuthenticated()">
	            <li class="selected"><a href="<c:url value="/home"/>">Home</a></li>	          
		         <sec:authorize access="hasAnyRole('Dean','Teacher')">
	              <li><a href="<c:url value="/persons"/>">Persons</a></li>
		         </sec:authorize>
		         <li><a href="<c:url value="/subjects"/>">Subjects</a></li>
		         <li><a href="<c:url value="/semesters"/>">Semesters</a></li>
		         <li><a href="<c:url value="/journal"/>">Journal</a></li>
	          </sec:authorize>
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