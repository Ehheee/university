<%@ include file="include.jsp"%>
<c:out value="${course.name }"></c:out> -- <a href="<c:url value='/course/${course.id }' />">Students </a>
<c:if test="${empty courses }">
	<c:set var = "studentCourses" value = "${course.studentCourses }" scope = "request"></c:set>
	<jsp:include page="studentCourses.jsp"></jsp:include>
</c:if>