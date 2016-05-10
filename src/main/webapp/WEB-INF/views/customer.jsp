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
					<h3>New Challenge</h3>
					<span style="padding-top: 5px;">create new challenge!</span>
				</div>
			</div>
			<div class="panel-body">
				<div class="form-group">
					<label>Users</label>
					<kendo:multiSelect name="cust" dataTextField="custName"
						dataValueField="custId" filter="startswith">
						<kendo:dataSource data="${customerList}"></kendo:dataSource>
					</kendo:multiSelect>
				</div>
				<div class="form-group">
					<label>Name</label> <input type="text" name="compName"
						class="form-control" id="name">
				</div>
				<div class="form-group">
					<label>Description</label> <input type="text" name="compDesc"
						class="form-control" id="desc">
				</div>

			</div>
			<div class="panel-footer">
				<p id="notification" class="text-success"></p>
				<div class="pull-right">
					<button class="k-button" id="get">Send Invitation</button>
				</div>
			</div>
		</div>
		<!-- END VISITORS BLOCK -->


		<!-- START SALES BLOCK -->
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="panel-title-box">
					<h3>${challenge1_f1}</h3>
					<span>${challenge1_f2}</span>
				</div>
				<ul class="panel-controls panel-controls-title">
					<li>
						<button class="btn btn-info btn-block btn-facebook" id="get">Share</button>
					</li>
				</ul>

			</div>
			<div class="panel-body">
				<div class="row stacked">

					<div class="progress-list">
						<div class="pull-left">
							<strong>${challenge1.cust1}</strong>
						</div>
						<div class="pull-right">${challenge1.val1}%</div>
						<div class="progress progress-small progress-striped active">
							<div class="progress-bar progress-bar-primary" role="progressbar"
								aria-valuenow="${challenge1.val1}" aria-valuemin="0"
								aria-valuemax="100" style="width: 75%;">${challenge1.val1}%</div>
						</div>
					</div>

					<div class="progress-list">
						<div class="pull-left">
							<strong>${challenge1.cust2}</strong>
						</div>
						<div class="pull-right">${challenge1.val2}%</div>
						<div class="progress progress-small progress-striped active">
							<div class="progress-bar progress-bar-primary" role="progressbar"
								aria-valuenow="${challenge1.val2}" aria-valuemin="0"
								aria-valuemax="100" style="width: 75%;">${challenge1.val2}%</div>
						</div>
					</div>

					<div class="progress-list">
						<div class="pull-left">
							<strong>${challenge1.cust3}</strong>
						</div>
						<div class="pull-right">${challenge1.val3}%</div>
						<div class="progress progress-small progress-striped active">
							<div class="progress-bar progress-bar-primary" role="progressbar"
								aria-valuenow="${challenge1.val3}" aria-valuemin="0"
								aria-valuemax="100" style="width: 75%;">${challenge1.val3}%</div>
						</div>
					</div>

					<div class="progress-list">
						<div class="pull-left">
							<strong>${challenge1.cust4}</strong>
						</div>
						<div class="pull-right">${challenge1.val4}%</div>
						<div class="progress progress-small progress-striped active">
							<div class="progress-bar progress-bar-primary" role="progressbar"
								aria-valuenow="${challenge1.val4}" aria-valuemin="0"
								aria-valuemax="100" style="width: ${challenge1.cust4}%;">${challenge1.val4}%</div>
						</div>
					</div>

					<div class="progress-list">
						<div class="pull-left">
							<strong>${challenge1.cust5}</strong>
						</div>
						<div class="pull-right">${challenge1.cust5}%</div>
						<div class="progress progress-small progress-striped active">
							<div class="progress-bar progress-bar-primary" role="progressbar"
								aria-valuenow="${challenge1.cust5}" aria-valuemin="0"
								aria-valuemax="100" style="width: ${challenge1.cust5}%;">${challenge1.cust5}%</div>
						</div>
					</div>

				</div>
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
	$(document).ready(function() {

		$("#get").click(function() {
			var required = $("#cust").data("kendoMultiSelect");
			var name = $("#name").val();
			var desc = $("#desc").val();
			var ids = required.value();

			if (ids == '') {
				alert("please select users to create a competition");
			} else {
				$.ajax({
					type : 'POST',
					url : 'cust/saveComp',// mapping-string
					data : {
						compUsrs : ids.toString(),
						compName : name,
						compDesc : desc
					},
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
		});
	});
</script>

<%@ include file="footer.jsp"%>