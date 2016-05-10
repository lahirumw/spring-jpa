c<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="java.util.List"%>
<%@ page import=" java.util.Random"%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags"%>

<%@ include file="header.jsp"%>


<!-- 	KENDO FILES -->
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/styles/kendo.common.min.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/styles/kendo.default.min.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/styles/kendo.dataviz.default.min.css"/>" />

<script type="text/javascript"
	src="<c:url value="/resources/js/jquery.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/plugins/jquery/jquery-ui.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/plugins/bootstrap/bootstrap.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/kendo.all.min.js"/>"></script>
<!-- END KENDO FILES -->
<c:url value="/multiselect/remote-data/read" var="readUrl" />

<div class="row">
	<div class="col-md-8">
		<div class="panel panel-info">
			<div class="panel-heading">Your power Consumption</div>
			<div class="panel-body">
				<div class="chart-wrapper">
					<%
						String[] categories = {"1", "2", "3", "4", "5", "6", "7", "8", "9",
								"10", "11", "12", "12", "14", "15", "16", "17", "18", "19",
								"20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
								"30", "31"};
					%>
					<kendo:chart name="chart" style="height:400px!important;">
						<kendo:chart-legend position="bottom" />
						<kendo:chart-chartArea background="transparent" />
						<kendo:chart-series>
							<kendo:chart-seriesItem type="line" style="smooth"
								data="${intArr1 }" name="Current Month">
							</kendo:chart-seriesItem>
							<kendo:chart-seriesItem type="line" style="smooth"
								data="${intArr2 }" name="Last Month">
							</kendo:chart-seriesItem>
						</kendo:chart-series>
						<kendo:chart-categoryAxis>
							<kendo:chart-categoryAxisItem categories="<%=categories%>">
								<kendo:chart-categoryAxisItem-majorGridLines visible="false" />
							</kendo:chart-categoryAxisItem>
						</kendo:chart-categoryAxis>
						<kendo:chart-valueAxis>
							<kendo:chart-valueAxisItem axisCrossingValue="-10">
								<kendo:chart-valueAxisItem-labels format="{0} Units" />
								<kendo:chart-valueAxisItem-line visible="false" />
							</kendo:chart-valueAxisItem>
						</kendo:chart-valueAxis>
						<kendo:chart-tooltip visible="true"
							template="#= series.name #: #= value #" />
					</kendo:chart>
				</div>
			</div>
		</div>
	</div>
	<div class="col-md-4">
		<!-- START VISITORS BLOCK -->
		<div class="panel panel-default">

			<div class="panel-heading">
				<div class="panel-title-box">
					
				</div>
			</div>
			<div class="panel-body list-group">
				<a href="#" class="list-group-item"><span class="fa fa-star"></span> ${curMonth} <span class="badge badge-danger">${totUnitCurMonth} units</span></a>
                <a href="#" class="list-group-item"><span class="fa fa-star"></span> ${prevMonth} <span class="badge badge-danger">${totUnitPrewMonth} units</span></a> 
			</div>
			<div class="panel-footer">
				
			</div>
		</div>
		<!-- END VISITORS BLOCK -->


		<!-- START SALES BLOCK -->
		<div class="panel panel-default">
			<div class="panel-heading">
				

			</div>
			<div class="panel-body">
				<button class="btn btn-info" type="button" onclick="searchClick()">
						<i class="glyphicon glyphicon-search"></i>Bill via SMS</button>
						<p id="notification" class="text-success"></p>
				<button class="btn btn-info" type="button" onclick="sendAlert()">
						<i class="glyphicon glyphicon-search"></i>Alert via SMS</button>
						<p id="notification" class="text-success"></p>
			</div>
		</div>
		<!-- END SALES BLOCK -->

	</div>
</div>




<style>
.demo-section h2 {
	text-transform: uppercase;
	font-size: 1.2em;
	margin-bottom: 10px;
}

.climate,.temperature,.conditioner {
	margin: 0 30px;
	padding: 30px 0 0 0;
}

h1 {
	margin-bottom: 20px;
	font-size: 1.2em;
}
</style>

<script>

	function searchClick(){
		$.ajax({
			type : 'POST',
			url : 'client/sendBill',
			success : function(data) {
				$("#notification").text("Successfully send...");
			},
			error : function(jqXHR, textStatus, errorThrown) {
			}
		});
	}

	function sendAlert(){
		$.ajax({
		type : 'POST',
		url : 'client/sendAlert',
		success : function(data) {
			$("#notification").text("Successfully send...");
		},
		error : function(jqXHR, textStatus, errorThrown) {
		}
	});
	}
</script>

<%@ include file="footer.jsp"%>