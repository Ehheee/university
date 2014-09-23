<%@ include file="include.jsp"%>
<c:out value="${student.name }">   </c:out>  --  <c:out value="${student.weightedAverage }"></c:out>  --  <a href="<c:url value='/student/${student.id }' />">Students Courses:</a>
<c:if test="${empty students }">
	<c:set var = "studentCourses" value = "${student.studentCourses }" scope = "request"></c:set>
	<jsp:include page="studentCourses.jsp"></jsp:include>
</c:if>