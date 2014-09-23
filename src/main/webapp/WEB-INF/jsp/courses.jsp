<%@ include file="include.jsp"%>
<c:url value = "/new/course" var = "newCourse"></c:url>
<form:form method = "POST" commandName = "courseForm" action="${newCourse }">
	<form:input path="name"/>
	<form:hidden path="version"/>
	<form:hidden path="id" />
	<input type="submit" value="create course" />	
</form:form>
<c:forEach items = "${courses }" var = "course">
	<c:set var = "course" value = "${course }" scope = "request"></c:set>
	<jsp:include page="course.jsp"></jsp:include>
	<br />
</c:forEach>