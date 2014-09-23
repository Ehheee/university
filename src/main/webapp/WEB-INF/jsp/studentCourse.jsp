<%@ include file="include.jsp"%>
<!-- 
Tests under which dataType are studentCourses currently shown.
If shown under course then there is no need to put course link or info again.
 -->
<c:choose>

	<c:when test="${dataType != 'course' }">
	
		<a href="<c:url value='/course/${studentCourse.course.id } ' />" >${studentCourse.course.name } </a>
	</c:when>
	<c:when test="${dataType != 'student' }">
	
		<a href="<c:url value='/student/${studentCourse.student.id } ' />" >${studentCourse.student.name } </a>
	</c:when>
	
</c:choose>
<c:out value = "${studentCourse.status }"></c:out> -- Grade: <c:out value = "${studentCourse.grade }"></c:out> -- Weight: <c:out value="${studentCourse.weight }"></c:out>