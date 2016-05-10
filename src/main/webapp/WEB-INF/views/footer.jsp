<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

</div>
<!-- END PAGE CONTENT WRAPPER -->
</div>
<!-- END PAGE CONTENT -->
</div>
<!-- END PAGE CONTAINER -->

<!-- MESSAGE BOX-->
<div class="message-box animated fadeIn" data-sound="alert"
	id="mb-signout">
	<div class="mb-container">
		<div class="mb-middle">
			<div class="mb-title">
				<span class="fa fa-sign-out"></span> Log <strong>Out</strong> ?
			</div>
			<div class="mb-content">
				<p>Are you sure you want to log out?</p>
				<p>Press No if you want to continue work. Press Yes to logout
					current user.</p>
			</div>
			<div class="mb-footer">
				<div class="pull-right">
					<a href="pages-login.html" class="btn btn-success btn-lg">Yes</a>
					<button class="btn btn-default btn-lg mb-control-close">No</button>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- END MESSAGE BOX-->

<!-- START SCRIPTS -->

<!-- START THIS PAGE PLUGINS-->
<script type="text/javascript" src="<c:url value="/resources/js/plugins/daterangepicker/daterangepicker.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/plugins/moment.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/plugins/bootstrap/bootstrap-datepicker.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/plugins/owl/owl.carousel.min.js"/>"></script>


<script type="text/javascript" src="<c:url value="/resources/js/plugins/rickshaw/rickshaw.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/plugins/rickshaw/d3.v3.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/plugins/morris/morris.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/plugins/scrolltotop/scrolltopcontrol.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/plugins/icheck/icheck.min.js"/>"></script>

<script type="text/javascript" src="<c:url value="/resources/js/plugins.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/actions.js"/>"></script>

<!-- END THIS PAGE PLUGINS-->

<!-- START TEMPLATE -->
<!-- <script type="text/javascript" src="js/settings.js"></script> -->


<!-- END TEMPLATE -->
<!-- END SCRIPTS -->
</body>
</html>
