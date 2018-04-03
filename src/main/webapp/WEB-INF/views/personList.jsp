<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Home</title>
</head>
<body>



<c:forEach var="p" items="${persons}">
	
	${p}
	
</c:forEach>
<c:url var="link" value="/sports">
	<c:param name="xyz" value="abc" />
	<c:param name="xyz" value="abc" />
</c:url>
<a href="${link}">link</a>

<c:url value="/register" var="form" />
<a href="${form}">link</a>

</body>
</html>
