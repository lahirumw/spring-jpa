<!-- PAGE IMPORTS -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html lang="en">
<head>
<!-- META SECTION -->
<title>Synergistic Solutions</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />

<link rel="icon" href="logo.ico" type="image/x-icon" />
<!-- END META SECTION -->

<!-- CSS INCLUDE -->
<link rel="stylesheet" type="text/css" id="theme"
	href="<c:url value="/resources/css/theme-default.css"/>" />
<!-- EOF CSS INCLUDE -->

</head>
<body>
	<!-- START PAGE CONTAINER -->
	<div class="page-container">

		<!-- START PAGE SIDEBAR -->
		<div class="page-sidebar">
			<!-- START X-NAVIGATION -->
			<ul class="x-navigation">
				<li class="xn-logo"><a href="#">Go Green</a> <a href="#"
					class="x-navigation-control"></a></li>
				<li class="xn-profile"><a href="#" class="profile-mini"> </a>
					<div class="profile">
						<div class="profile-image">
							<c:set var="imageName"
								value="<%=session.getAttribute(\"user_image\")%>"></c:set>
							<c:set var="url" value="/resources/img/user/${imageName }.jpg"></c:set>
							<img src="<c:url value="${url}"/>"
								alt="<%=session.getAttribute("user_name")%>" />
						</div>
						<div class="profile-data">
							<div class="profile-data-name"><%=session.getAttribute("user_name")%></div>
							<div class="profile-data-title"><%=session.getAttribute("user_title")%></div>
						</div>
					</div></li>
				<li class="xn-title">Statistics</li>
				<li class="active"></li>


			</ul>
			<!-- END X-NAVIGATION -->
		</div>
		<!-- END PAGE SIDEBAR -->

		<!-- PAGE CONTENT -->
		<div class="page-content">

			<!-- START X-NAVIGATION VERTICAL -->
			<ul class="x-navigation x-navigation-horizontal x-navigation-panel">

				<!-- SEARCH -->
				<li class="xn-search">
					<form role="form">
						<input type="text" name="search" placeholder="Search..." />
					</form>
				</li>
				<!-- END SEARCH -->
				<!-- SIGN OUT -->
				<li class="xn-icon-button pull-right"><a href="#"
					class="mb-control" data-box="#mb-signout"><span
						class="fa fa-sign-out"></span></a></li>
				<!-- END SIGN OUT -->
				<!-- MESSAGES -->
				<li class="xn-icon-button pull-right"><a href="#"><span
						class="fa fa-comments"></span></a>
					<div class="informer informer-danger">${msgList.size}</div>
					<div class="panel panel-primary animated zoomIn xn-drop-left">
						<div class="panel-heading">
							<h3 class="panel-title">
								<span class="fa fa-comments"></span> Messages
							</h3>
							<div class="pull-right">
								<span class="label label-danger">${msgList.size} new</span>
							</div>
						</div>
						<div class="panel-body list-group list-group-contacts scroll"
							style="height: 200px;">

							<c:forEach items="${msgList}" var="element">

								<a href="#" class="list-group-item">
									<div class="list-group-status status-online"></div> <img
									src="assets/images/users/user2.jpg" class="pull-left"
									alt="John Doe" /> <span class="contacts-title">${element.title}</span>
									<p>${element.desc}</p>
								</a>

							</c:forEach>

						</div>
						<div class="panel-footer text-center">
							<a href="pages-messages.html">Show all messages</a>
						</div>
					</div></li>
				<!-- END MESSAGES -->
				<!-- TASKS -->
				<li class="xn-icon-button pull-right"><a href="#"><span
						class="fa fa-tasks"></span></a>
					<div class="informer informer-warning">${progressList.size}</div>
					<div class="panel panel-primary animated zoomIn xn-drop-left">
						<div class="panel-heading">
							<h3 class="panel-title">
								<span class="fa fa-tasks"></span> Tasks
							</h3>
							<div class="pull-right">
								<span class="label label-warning">${progressList.size}
									active</span>
							</div>
						</div>


						<div class="panel-body list-group scroll" style="height: 200px;">

							<c:forEach items="${progressList}" var="element">

								<a class="list-group-item" href="#"> <strong>${element.title}</strong>
									<div class="progress progress-small progress-striped active">
										<div class="progress-bar progress-bar-danger"
											role="progressbar" aria-valuenow="${element.value}"
											aria-valuemin="0" aria-valuemax="100" style="width: 90%;">${element.percent}</div>
									</div> <small class="text-muted">${element.desc}</small>
								</a>

							</c:forEach>

						</div>
						<div class="panel-footer text-center">
							<a href="pages-tasks.html">Show all tasks</a>
						</div>
					</div></li>
				<!-- END TASKS -->
			</ul>
			<!-- END X-NAVIGATION VERTICAL -->

			<!-- START BREADCRUMB -->
			<ul class="breadcrumb">
				<li><a href="#">Home</a></li>
				<li class="active">Dashboard</li>
			</ul>
			<!-- END BREADCRUMB -->

			<!-- PAGE CONTENT WRAPPER -->
			<div class="page-content-wrap">