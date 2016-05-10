<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form action="cecb" method="POST">
            <input type="submit" value="Generate Alert" name="gen_alert"/>
    </form>
    
    <p>Total Unit ${curMonth} - ${totUnitCurMonth}</p>
	<p>Total Unit ${prewMonth} - ${totUnitPrewMonth}</p>
	
	<table>
		<thead>
			<tr>
				<th>Cur Mon Day</th>
				<th>Units</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
					<c:when test="${curDate.size()==0}">
						<em>No records.</em>
					</c:when>
					<c:otherwise>
						<c:forEach items="${curDate}" var="curDateDt" varStatus="sts">
						<tr>
						<td>${curDateDt}</td>
						<td>${perDayUnit.get(sts.count - 1)}</td>
						</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
		</tbody>
	</table>
	
	<table>
		<thead>
			<tr>
				<th>Prew Month Day</th>
				<th>Units</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
					<c:when test="${prewDate.size()==0}">
						<em>No records.</em>
					</c:when>
					<c:otherwise>
						<c:forEach items="${prewDate}" var="prewDateDt" varStatus="sts">
						<tr>
						<td>${prewDateDt}</td>
						<td>${perDayUnitPrew.get(sts.count - 1)}</td>
						</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
		</tbody>
	</table>
</body>
</html>