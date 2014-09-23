<%@ include file="include.jsp"%>
<c:url value = "/new/studentcourse" var = "newStudentCourse"></c:url>
<form:form method = "POST" commandName = "studentCourseForm" action="${newStudentCourse }">
	
	Status: <form:input path="status"/>
	Grade: <form:input path="grade"/>
	Weight: <form:input path="weight"/>
	<c:if test="${dataType == 'course' }">
		Student: <form:input path="student.id"/>
		<form:hidden path="course.id" value = "${course.id }"/>
	</c:if>
	<c:if test="${dataType == 'student' }">
		Course: <form:input path="course.id" />
		<form:hidden path="student.id" value = "${student.id }"/>
	</c:if>
	<input type="submit" value="create studentCourse" />
</form:form>
<c:forEach items = "${studentCourses }" var = "studentCourse">
	<c:set var="studentCourse" value="${studentCourse }" scope ="request" ></c:set>
	<jsp:include page = "studentCourse.jsp"></jsp:include>
	<br />
</c:forEach>