<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Course Popularity</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
    <h1>Course Popularity</h1>
    <p>${courseNames}</p>
    <canvas id="coursePopularityChart"></canvas>
    <script>
        var ctx = document.getElementById('coursePopularityChart').getContext('2d');

        // Retrieve the data passed from the servlet
        var courseNames = ${courseNames};
        var enrollmentsPerCourse = ${enrollmentsPerCourse};

        var coursePopularityChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: courseNames,
                datasets: [{
                    label: 'Number of Enrollments',
                    data: enrollmentsPerCourse,
                    backgroundColor: 'rgba(75, 192, 192, 0.2)',
                    borderColor: 'rgba(75, 192, 192, 1)',
                    borderWidth: 1
                }]
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
</body>
</html>
