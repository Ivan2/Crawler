<!DOCTYPE html>
<%@ page contentType='text/html;charset=UTF-8' language='java' %>
<%@ page import ="crawler.Crawler" %>
<%@ page import ="data.*" %>
<%
Crawler crawler = new Crawler();
AverageCount[] averageCount = crawler.getAverageCountArray();
Info info = crawler.getInfo();
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
						<%= averageCount[1].getCommentsCount() %>,
						<%= averageCount[1].getLikesCount() %>,
						<%= averageCount[1].getRepostsCount() %>],
					['Вторник',
						<%= averageCount[2].getCommentsCount() %>,
						<%= averageCount[2].getLikesCount() %>,
						<%= averageCount[2].getRepostsCount() %>],
					['Среда',
						<%= averageCount[3].getCommentsCount() %>,
						<%= averageCount[3].getLikesCount() %>,
						<%= averageCount[3].getRepostsCount() %>],
					['Четверг',
						<%= averageCount[4].getCommentsCount() %>,
						<%= averageCount[4].getLikesCount() %>,
						<%= averageCount[4].getRepostsCount() %>],
					['Пятница',
						<%= averageCount[5].getCommentsCount() %>,
						<%= averageCount[5].getLikesCount() %>,
						<%= averageCount[5].getRepostsCount() %>],
					['Суббота',
						<%= averageCount[6].getCommentsCount() %>,
						<%= averageCount[6].getLikesCount() %>,
						<%= averageCount[6].getRepostsCount() %>],
					['Воскресенье',
						<%= averageCount[0].getCommentsCount() %>,
						<%= averageCount[0].getLikesCount() %>,
						<%= averageCount[0].getRepostsCount() %>],
				]);
				var options = {
					title: 'Среднее количество комментариев, лайков и репостов в группе "Типичный программист" в ВКонтакте по дням недели',
					hAxis: {title: 'День недели'},
					vAxis: {title: 'Количество комментариев, лайков и репостов'}
				};
				var chart = new google.visualization.ColumnChart(document.getElementById('chart'));
				chart.draw(data, options);
			}
		</script>
	</head>
	<body>
		Дата последнего обновления: <%= info.getUpdate() %>
		<br>
		Количество обработанных постов: <%= info.getPostCount() %>
		<br>
		<br>
		<div id='chart' style='width: 1200px; height: 500px;'></div>
	</body>
</html>