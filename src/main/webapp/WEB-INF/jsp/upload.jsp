<%@ include file="include.jsp"%>
<c:url value = "/upload" var = "uploadUrl"/>
<form action = "${uploadUrl}" method = "POST" enctype="multipart/form-data">
	<input type="file" name="xml"></input>
	<select name = "dataType">
		<option value = "student">Students</option>
		<option value = "studentCourse">Student Courses</option>
		<option value = "course">Courses</option>
	</select>
	<input type="submit" value="send" />
</form>