<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Admin - Quản Lý Server AI Cờ Vua</title>
<!-- Thêm liên kết CSS tại đây -->
<link rel="stylesheet"
	href="<c:url value='/template/admin/CSS/style.css'/>">
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>



</head>
<body>


	<div class="sidebar">

		<nav>
			<header>
				<h1>Quản Lý Server AI Cờ Vua</h1>
			</header>
			<!-- Menu Navigation -->
			<ul>
				<li><a href="#dashboard">Dashboard</a></li>
				<li><a href="#manage-servers">Quản Lý Server AI</a></li>
				<li><a href="#settings">Cài Đặt</a></li>
				<li><a href="#support">Hỗ Trợ</a></li>
			</ul>
		</nav>
		<footer>
			<p>&copy; 2023 Quản Lý Server AI Cờ Vua</p>
		</footer>
	</div>



	<div class="content">
		<section id="dashboard">
			<h2>Dashboard</h2>
			<div class="chart-container">
				<canvas id="myChart"></canvas>
			</div>
		</section>


		<section id="manage-servers">
			<h2>Quản Lý Server AI</h2>
			<table class="manage-servers-table">
				<thead>
					<tr>
						<th>Tên Server</th>
						<th>Số Ván Cờ PVP</th>
						<th>Số Ván Cờ PVE</th>
						<th>Tình Trạng Kết Nối</th>
						<th>Hành Động</th>
					</tr>
				</thead>
				<tbody>
					<!-- Sử dụng JSTL để lặp qua từng server status -->
					<c:forEach items="${listStatus}" var="status">
						<tr>
							<td>${status.serverName}</td>
							<td>${status.gamePVP}</td>
							<td>${status.gamePVE}</td>
							<td>${status.status}</td>
							<td>
								<!-- Thực hiện các hành động phù hợp -->
								<button class="action-button disconnect-button"
									onclick="disconnectServer('${status.serverName}')">Ngắt
									Kết Nối</button>
								<button class="action-button"
									onclick="redistributeGames('${status.serverName}')">Đẩy
									Ván Cờ</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</section>



		<section id="settings">
			<h2>Cài Đặt</h2>
			<!-- Nội dung cài đặt -->
		</section>

		<section id="support">
			<h2>Hỗ Trợ</h2>
			<!-- Thông tin hỗ trợ -->
		</section>
	</div>

	<!-- Thêm các script JavaScript tại đây -->
	<script>
    const pvpData = [];
    const pveData = [];
    const labels = [];

    <c:forEach items="${pvpCounts}" var="entry">
        labels.push("${entry.key}");
        pvpData.push(${entry.value});
    </c:forEach>

    <c:forEach items="${pveCounts}" var="entry">
        pveData.push(${entry.value});
    </c:forEach>

    const ctx = document.getElementById('myChart').getContext('2d');
    const myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: labels, // Tên của các server
            datasets: [
                {
                    label: 'Số lượng ván cờ PVE đang xử lý',
                    data: pveData, // Dữ liệu PVE
                    backgroundColor: 'rgba(54, 162, 235, 0.6)',
                    borderColor: 'rgba(54, 162, 235, 1)',
                    borderWidth: 1
                },
                {
                    label: 'Số lượng ván cờ PVP đang xử lý',
                    data: pvpData, // Dữ liệu PVP
                    backgroundColor: 'rgba(255, 99, 132, 0.6)',
                    borderColor: 'rgba(255, 99, 132, 1)',
                    borderWidth: 1
                }
            ]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
</script>

<script>
    function fetchDataAndUpdatePage() {
        $.ajax({
            url: '/spring-mvc/getServerData',
            type: 'GET',
            dataType: 'json',
            cache: false,
            success: function(data) {
                console.log("Data received:", data);
                updateServerTable(data.listStatus);
                updateChart(data.pvpCounts, data.pveCounts);
            },
            error: function(error) {
                console.log('Error:', error);
            }
        });
    }

    function updateServerTable(listStatus) {
        var tableHTML = "";
        $.each(listStatus, function(i, status) {
            tableHTML += "<tr>" +
                "<td>" + status.serverName + "</td>" +
                "<td>" + status.gamePVP + "</td>" +
                "<td>" + status.gamePVE + "</td>" +
                "<td>" + status.status + "</td>" +
                "<td>" +
                    "<button class='action-button disconnect-button' onclick='disconnectServer(\"" + status.serverName + "\")'>Ngắt Kết Nối</button>" +
                    "<button class='action-button' onclick='redistributeGames(\"" + status.serverName + "\")'>Đẩy Ván Cờ</button>" +
                "</td>" +
            "</tr>";
        });
        $("#manage-servers tbody").html(tableHTML);
    }

    function updateChart(pvpCounts, pveCounts) {
        var pvpData = [];
        var pveData = [];
        var labels = [];

        for (var server in pvpCounts) {
            labels.push(server);
            pvpData.push(pvpCounts[server]);
            pveData.push(pveCounts[server]);
        }

        myChart.data.labels = labels;
        myChart.data.datasets[0].data = pveData;
        myChart.data.datasets[1].data = pvpData;
        myChart.update();
    }


    $(document).ready(function() {
        setInterval(fetchDataAndUpdatePage, 2000); // Refresh every 2 seconds
    });
</script>



</body>
</html>
