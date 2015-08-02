<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page import="java.util.*" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world! I am SPRING.
</h1>

<P>  The time on the server is ${serverTime}. </P>
<P>  The time from JSP is <%= new Date() %>. </P>
</body>
</html>
