<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Remove Enrollment</title>
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
                                    <h1 class="h4 text-gray-900 mb-4">Remove Enrollment</h1>
                                </div>
                                <form action="Enrollments" method="post" class="user">
                                    <input type="hidden" name="action" value="delete" readonly>
                                    <input type="hidden" name="id" value="${enrollment.enrollmentId}">
                                    <div class="form-group">
                                        <input type="text" class="form-control form-control-user" name="studentId"
                                               placeholder="Student ID" value="${enrollment.studentId} - ${enrollment.studentName}" readonly>
                                    </div>
                                        
                                    <div class="form-group">
                                        <input type="text" class="form-control form-control-user" name="courseId"
                                               placeholder="course ID" value="${enrollment.courseId} - ${enrollment.courseName}" readonly>
                                    </div>

                                    <input type="submit" value="Remove" class="btn btn-primary btn-user btn-block">
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