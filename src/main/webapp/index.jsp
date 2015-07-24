<!DOCTYPE html>
<%@ page contentType='text/html;charset=UTF-8' language='java' %>
<jsp:useBean id="bean" class="crawler.Crawler" />
<html>
	<head>
		<title>Crawler</title>
		<script src="https://www.google.com/jsapi"></script>
		<script>
			google.load("visualization", '1', {packages:["corechart"]});
			google.setOnLoadCallback(drawChart);
			function drawChart() {
				var data = google.visualization.arrayToDataTable([
					['', '', {role: 'style'}],
					['Понедельник', <jsp:getProperty name="bean" property="mondayPostCount"/>, 'blue'],
					['Вторник', <jsp:getProperty name="bean" property="tuesdayPostCount"/>, 'red'],
					['Среда', <jsp:getProperty name="bean" property="wednesdayPostCount"/>, 'green'],
					['Четверг', <jsp:getProperty name="bean" property="thursdayPostCount"/>, 'yellow'],
					['Пятница', <jsp:getProperty name="bean" property="fridayPostCount"/>, 'blue'],
					['Суббота', <jsp:getProperty name="bean" property="saturdayPostCount"/>, 'red'],
					['Воскресенье', <jsp:getProperty name="bean" property="sundayPostCount"/>, 'purple'],
				]);
				var options = {
					title: 'Количество постов в группе "Типичный программист" в ВКонтакте по дням недели',
					hAxis: {title: 'День недели'},
					vAxis: {title: 'Количество постов'},
					legend: {position: 'none'}
				};
				var chart = new google.visualization.ColumnChart(document.getElementById('chart'));
				chart.draw(data, options);
			}
		</script>
	</head>
	<body>
		<div id='chart' style='width: 1200px; height: 500px;'></div>
		<br>
		test
	</body>
</html>