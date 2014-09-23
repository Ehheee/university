<%@ include file="include.jsp"%>
<jsp:include page="header.jsp"></jsp:include>
<c:choose>
	
	<c:when test = "${not empty courses || not empty courseForm}" >
		<jsp:include page="courses.jsp"></jsp:include>
	</c:when>
	
	<c:when test = "${not empty students || not empty studentForm}" >
		<jsp:include page="students.jsp"></jsp:include>
	</c:when>
	
</c:choose>
<c:if test="${not empty studentCourseForm }">
	<jsp:include page="${dataType }.jsp"></jsp:include>
</c:if>
<c:if test="${not empty jspContent }">
	<jsp:include page="${jspContent }"></jsp:include>
</c:if>
<jsp:include page="footer.jsp"></jsp:include>
