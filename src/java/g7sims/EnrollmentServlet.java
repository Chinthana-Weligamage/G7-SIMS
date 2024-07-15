package g7sims;

import g7sims.classes.Course;
import g7sims.classes.CourseDAO;
import g7sims.classes.Enrollment;
import g7sims.classes.EnrollmentDAO;
import g7sims.classes.Student;
import g7sims.classes.StudentDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/Enrollments")
public class EnrollmentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "insert" ->
                showNewForm(request, response);
            case "list" ->
                listEnrollments(request, response);
            case "update" ->
                showEditForm(request, response);
            case "delete" ->
                showDeleteForm(request, response);
            default ->
                listEnrollments(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "insert" ->
                insertEnrollment(request, response);
            case "update" ->
                updateEnrollment(request, response);
            case "delete" ->
                deleteEnrollment(request, response);
            case "search" ->
                searchEnrollment(request, response);
            default ->
                listEnrollments(request, response);
        }
    }

    private void listEnrollments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Enrollment> enrollments = EnrollmentDAO.getAllEnrollmentsWithDetails();
        request.setAttribute("enrollments", enrollments);
        RequestDispatcher dispatcher = request.getRequestDispatcher("./pages/EnrollmentView.jsp");
        dispatcher.forward(request, response);
    }
    
    private void searchEnrollment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        List<Enrollment> enrollments = EnrollmentDAO.getSearchedEnrollments(keyword);
        request.setAttribute("enrollments", enrollments);
        request.setAttribute("keyword", keyword);
        RequestDispatcher dispatcher = request.getRequestDispatcher("./pages/EnrollmentView.jsp");
        dispatcher.forward(request, response);
    }

    private void insertEnrollment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        
        Enrollment enrollment = new Enrollment(courseId, studentId);
        
        EnrollmentDAO.insertEnrollment(enrollment);
        response.sendRedirect("Enrollments?action=list");
    }

    private void updateEnrollment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int enrollmentId = Integer.parseInt(request.getParameter("id"));
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        int courseId = Integer.parseInt(request.getParameter("courseId"));

        Enrollment enrollment = new Enrollment(enrollmentId, studentId, courseId);

        EnrollmentDAO.updateEnrollment(enrollment);
        response.sendRedirect("Enrollments?action=list");
    }

    private void deleteEnrollment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int enrollmentid = Integer.parseInt(request.getParameter("id"));
        EnrollmentDAO.deleteEnrollment(enrollmentid);
        response.sendRedirect("Enrollments?action=list");
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> students = StudentDAO.getAllStudents();
        List<Course> courses = CourseDAO.getAllCourses();
        
        request.setAttribute("students", students);
        request.setAttribute("courses", courses);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("./pages/EnrollmentCreate.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int enrollmentid = Integer.parseInt(request.getParameter("id"));
        Enrollment existingEnrollment = EnrollmentDAO.getEnrollmentById(enrollmentid);
        
        List<Student> students = StudentDAO.getAllStudents();
        List<Course> courses = CourseDAO.getAllCourses();
        
        request.setAttribute("students", students);
        request.setAttribute("courses", courses);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("./pages/EnrollmentUpdate.jsp");
        request.setAttribute("enrollment", existingEnrollment);
        dispatcher.forward(request, response);
    }

    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int enrollmentid = Integer.parseInt(request.getParameter("id"));
        Enrollment existingEnrollment = EnrollmentDAO.getEnrollmentByIdWithDetails(enrollmentid);
        System.out.println(existingEnrollment.toString());
        RequestDispatcher dispatcher = request.getRequestDispatcher("./pages/EnrollmentDelete.jsp");
        request.setAttribute("enrollment", existingEnrollment);
        dispatcher.forward(request, response);
    }

}
