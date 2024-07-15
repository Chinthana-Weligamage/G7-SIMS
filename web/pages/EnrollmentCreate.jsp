<%@ page import="java.util.List" %>
<%@ page import="g7sims.classes.Student" %>
<%@ page import="g7sims.classes.Course" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>New Enrollment</title>
        <%@ include file="/components/head.jsp" %>
    </head>

    <body class="bg-gradient-primary" style="
          background-image: url('./img/HomeBackground.jpg');
          height: 100vh;
          background-position: center;
          background-repeat: no-repeat;
          background-size: cover;
          ">

        <div class="container py-5">
            <div class="card o-hidden border-0 shadow-lg my-5">
                <div class="card-body p-0">
                    <!-- Nested Row within Card Body -->
                    <div class="row">
                        <!-- Logo -->
                        <%@ include file="/components/formpage-logo.jsp" %>
                        <div class="col-lg-7">
                            <div class="p-5">
                                <div class="text-center">
                                    <h1 class="h4 text-gray-900 mb-4">New Enrollment</h1>
                                </div>
                                <form action="Enrollments" method="post" class="user">
                                    <input type="hidden" name="action" value="insert">

                                    <div class="form-group">
                                        <input class="form-control form-control-user" list="students" name="studentId" id="studentId" placeholder="Student ID" required>
                                        <datalist id="students">
                                            <%
                                                List<Student> students = (List<Student>) request.getAttribute("students");
                                                if (students != null) {
                                                    for (Student student : students) {
                                            %>
                                            <option value="<%= student.getStudentId() %>"><%= student.getFirstName() %> <%= student.getLastName() %></option>
                                            <%
                                                    }
                                                } else {
                                            %>
                                            <option value="" disabled>No students available</option>
                                            <%
                                                }
                                            %>
                                        </datalist>
                                    </div>

                                    <div class="form-group">
                                        <input class="form-control form-control-user" list="courses" name="courseId" id="courseId" placeholder="Course ID" required>
                                        <datalist id="courses">
                                            <%
                                                List<Course> courses = (List<Course>) request.getAttribute("courses");
                                                if (courses != null) {
                                                    for (Course course : courses) {
                                            %>
                                            <option value="<%= course.getCourseId() %>"><%= course.getCourseName() %></option>
                                            <%
                                                    }
                                                } else {
                                            %>
                                            <option value="" disabled>No courses available</option>
                                            <%
                                                }
                                            %>
                                        </datalist>
                                    </div>

                                    <input type="submit" value="Enroll" class="btn btn-primary btn-user btn-block">
                                    <hr>
                                    <div class="text-center">
                                        <a class="small" href="./Enrollments?action=list">Cancel</a>
                                    </div>
                                </form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <!-- Bootstrap core JavaScript-->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="js/sb-admin-2.min.js"></script>

    </body>

</html>