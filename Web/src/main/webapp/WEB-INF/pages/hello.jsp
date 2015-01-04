<%@ page import="java.util.Date" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<body>
<h1><%= new Date() %> - ${message}!</h1>

<c:url var="logoutUrl" value="/logout"/>
<form action="${logoutUrl}"
      method="post">
    <input type="submit"
           value="Log out" />
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
</form>

</body>
</html>