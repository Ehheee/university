<%@ include file="include.jsp"%>
<c:url value = "/new/student" var = "newStudent"></c:url>
<form:form method = "POST" commandName = "studentForm" action="${newStudent }">
	<form:input path="name"></form:input>
	<form:hidden path="version"/>
	<form:hidden path="id" />
	<input type="submit" value="create student" />
</form:form>
<c:forEach items = "${students }" var = "student">


	<c:set var = "student" value = "${student }" scope = "request"></c:set>
	<jsp:include page="student.jsp"></jsp:include>
	<br />
</c:forEach>
 