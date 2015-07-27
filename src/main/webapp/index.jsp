<!DOCTYPE html>
<%@ page contentType='text/html;charset=UTF-8' language='java' %>
<%@ page import ="crawler.Crawler" %>
<%
Crawler crawler = new Crawler();
long[][] averageCount = crawler.getAverageCount();
String update = crawler.getUpdate();
%>
<html>
	<head>
		<title>Crawler</title>
		<script src="https://www.google.com/jsapi"></script>
		<script>
			google.load("visualization", '1', {packages:["corechart"]});
			google.setOnLoadCallback(drawChart);
			function drawChart() {
				var data = google.visualization.arrayToDataTable([
					['', 'Комментарии', 'Лайки', 'Репосты'],
					['Понедельник',
						<%= averageCount[1][0] %>,
						<%= averageCount[1][1] %>,
						<%= averageCount[1][2] %>],
					['Вторник',
                        <%= averageCount[2][0] %>,
                        <%= averageCount[2][1] %>,
                        <%= averageCount[2][2] %>],
					['Среда',
                        <%= averageCount[3][0] %>,
                        <%= averageCount[3][1] %>,
                        <%= averageCount[3][2] %>],
					['Четверг',
                        <%= averageCount[4][0] %>,
                        <%= averageCount[4][1] %>,
                        <%= averageCount[4][2] %>],
					['Пятница',
                        <%= averageCount[5][0] %>,
                        <%= averageCount[5][1] %>,
                        <%= averageCount[5][2] %>],
					['Суббота',
                        <%= averageCount[6][0] %>,
                        <%= averageCount[6][1] %>,
                        <%= averageCount[6][2] %>],
					['Воскресенье',
                        <%= averageCount[0][0] %>,
                        <%= averageCount[0][1] %>,
                        <%= averageCount[0][2] %>],
				]);
				var options = {
					title: 'Среднее количество комментариев, лайков и репостов в группе "Типичный программист" в ВКонтакте по дням недели',
					hAxis: {title: 'День недели'},
					vAxis: {title: 'Количество постов'}
				};
				var chart = new google.visualization.ColumnChart(document.getElementById('chart'));
				chart.draw(data, options);
			}
		</script>
	</head>
	<body>
		Дата последнего обновления: <%= update %>
		<br>
		<div id='chart' style='width: 1200px; height: 500px;'></div>
	</body>
</html>