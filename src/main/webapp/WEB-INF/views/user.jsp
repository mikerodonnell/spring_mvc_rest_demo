<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<% System.out.println("~~~~~~~~~ system out rendering user.jsp"); %>
<%= "I am user JSP" %>
<%= "... details for user: " %>
<c:out value="${userName}" />