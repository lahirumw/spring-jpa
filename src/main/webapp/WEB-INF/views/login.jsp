<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="java.util.List"%>
<%@ page import=" java.util.Random"%>

<!DOCTYPE html>
<html lang="en" class="body-full-height">
<head>
<!-- META SECTION -->
<title>Login</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />

<link rel="icon" href="<c:url value="/resources/img/favicon.ico"/>"
	type="image/x-icon" />
<!-- END META SECTION -->

<!-- CSS INCLUDE -->
<link rel="stylesheet" type="text/css" id="theme"
	href="<c:url value="/resources/css/theme-default.css"/>" />
<!-- EOF CSS INCLUDE -->

</head>
<body>

	<div class="login-container">

		<div class="login-box animated fadeInDown">
			<div class="login-logo"></div>
			<div class="login-body">
				<div class="login-title">
					<strong>Welcome</strong>, Please login<button name="lov" onclick='lovFunction()'>LOV</button>
				</div>
				<form action="login" class="form-horizontal" method="POST">
					<div class="form-group">
						<div class="col-md-12">
							<input type="text" name="username" class="form-control"
								placeholder="E-mail" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-12">
							<input type="password" name="password" class="form-control"
								placeholder="Password" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-6">
							<a href="#" class="btn btn-link btn-block">Forgot your
								password?</a>
						</div>
						<div class="col-md-6">
							<button type="submit" class="btn btn-info btn-block btn_login">Log
								In</button>
						</div>
					</div>

					<div class="panel-body" id="accOneColThree">
						<div class="login-subtitle">
							Don't have an account yet? <a href="#">Create an account</a>
						</div>
					</div>

					<c:if test="${error_msg!=null}">
						<div class="alert alert-danger" role="alert">
							<button type="button" class="close" data-dismiss="alert">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>
							<strong>Oh snap!</strong>${error-msg}
						</div>
					</c:if>
				</form>
			</div>
			<div class="login-footer">
				<div class="pull-left">&copy; 2014 Synergistic Solutions</div>
				<div class="pull-right">
					<a href="#">About</a> | <a href="#">Privacy</a> | <a href="#">Contact
						Us</a>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">

		function lovFunction(){
			alert('ok');
			var name = 'lahiru';
			$.ajax({
				type:'POST',
				url:'',
				data:{name:name},
				success : function(data) {
					$("#notification").text("Successfully saved");
					console.log("ajax call successful");
				},
				error : function(jqXHR, textStatus, errorThrown) {
					$("#notification").text("");
					alert("error loading the data" + jqXHR + " error" + errorThrown);
				}
			});
		}
	</script>
</body>
</html>
