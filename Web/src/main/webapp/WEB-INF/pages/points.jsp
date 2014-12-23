<%@ page session="false" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
</head>
<body>
${points.size()}
<c:choose>
    <c:when test="${not empty points}">
        <dl class="listings">
            <c:forEach items="${points}" var="point">
                <dt>
                    <a href="/NeoNavigator/points/detail//${point.id}">
                        <c:out value="${point.name}"/> -
                        <c:out value="${point.latitude}"/> x
                        <c:out value="${point.longitude}"/></a><br/>
                </dt>
                <dd>
                    ...
                </dd>
            </c:forEach>
        </dl>
    </c:when>
</c:choose>
</body>
</html>
