<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Update Instructor</title>
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
                                    <h1 class="h4 text-gray-900 mb-4">Update Instructor</h1>
                                </div>
                                <form action="Instructors" method="post" class="user">
                                    <input type="hidden" name="action" value="update" readonly>
                                    <input type="hidden" name="id" value="${instructor.instructorId}" readonly>
                                    <div class="form-group">
                                        <input type="text" class="form-control form-control-user" name="firstName"
                                               placeholder="First Name"  value="${instructor.firstName}" required>
                                    </div>
                                    <div class="form-group">
                                        <input type="text" class="form-control form-control-user" name="lastName"
                                               placeholder="Last Name" value="${instructor.lastName}" required>
                                    </div>
                                    <div class="form-group">
                                        <input type="email" class="form-control form-control-user" name="email"
                                               placeholder="Email Address" value="${instructor.email}" required>
                                    </div>

                                    <input type="submit" value="Save" class="btn btn-primary btn-user btn-block">
                                    <hr>
                                    <div class="text-center">
                                        <a class="small" href="./Instructors?action=list">Cancel</a>
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