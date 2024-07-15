<%@ page import="java.util.List" %>
<%@ page import="g7sims.classes.Course" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

    <head>
        <title>Course List</title>
        <%@ include file="/components/head.jsp" %>
    </head>

    <body id="page-top">

        <!-- Page Wrapper -->
        <div id="wrapper">

            <!-- Sidebar -->
            <%@ include file="/components/sidebar.jsp" %>

            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">

                <!-- Main Content -->
                <div id="content">

                    <!-- Topbar -->
                    <%@ include file="/components/topbar.jsp" %>

                    <!-- Begin Page Content -->
                    <div class="container-fluid">

                        <!-- Page Heading -->
                        <h1 class="h3 mb-2 text-gray-800">Course List</h1>
                        <p class="mb-4">Course details of G7-SIMS School Information Management System.</p>

                        <!-- Course Table -->
                        <div class="card shadow mb-4">
                            <div class="card-header py-3 d-md-flex justify-content-md-end">
                                <!-- Course Search -->
                                <%
                                String keyword = "";
                                if (request.getAttribute("keyword") != null){
                                    keyword = (String) request.getAttribute("keyword");
                                }
                                %>
                                <form action="Courses" method="post"
                                      class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                                    <input type="hidden" name="action" value="search">

                                    <div class="input-group">
                                        <input type="search" name="keyword" value="<%= keyword %>" class="form-control bg-white border-0 small" placeholder="Search for...">
                                        <div class="input-group-append">
                                            <button class="btn btn-primary" type="submit">
                                                <i class="fas fa-search fa-sm"></i>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                                <a class="btn btn-success px-4" href="Courses?action=insert">
                                    <i class="fas fa-plus-circle fa-sm p-2"></i>Add New Course</a>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <thead class="text-center">
                                            <tr>
                                                <th>Course ID</th>
                                                <th>Course Name</th>
                                                <th>Description</th>
                                                <th>Actions</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                List<Course> courses = (List<Course>) request.getAttribute("courses");
                                                for (Course course : courses) {
                                            %>
                                            <tr>
                                                <td><%= course.getCourseId() %></td>
                                                <td><%= course.getCourseName() %></td>
                                                <td><%= course.getCourseDescription() %></td>
                                                <td>
                                                    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                                                        <a class="btn btn-warning mx-1 btn-sm" href="Courses?action=update&id=<%= course.getCourseId() %>">Edit</a>
                                                        <a class="btn btn-danger mx-1 btn-sm" href="Courses?action=delete&id=<%= course.getCourseId() %>">Delete</a>
                                                    </div>
                                                </td>
                                            </tr>
                                            <% } %>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- /.container-fluid -->
                </div>
                <!-- End of Main Content -->

                <!-- Footer -->
                <%@ include file="/components/footer.jsp" %>
            </div>
            <!-- End of Content Wrapper -->
        </div>

    </body>

</html>
