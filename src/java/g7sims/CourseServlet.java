import g7sims.classes.Course;
import g7sims.classes.CourseDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/Courses")
public class CourseServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "insert" ->
                showNewForm(request, response);
            case "list" ->
                listCourses(request, response);
            case "update" ->
                showEditForm(request, response);
            case "delete" ->
                showDeleteForm(request, response);
            default ->
                listCourses(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "insert" ->
                insertCourse(request, response);
            case "update" ->
                updateCourse(request, response);
            case "delete" ->
                deleteCourse(request, response);
            case "search" ->
                searchCourse(request, response);
            default ->
                listCourses(request, response);
        }
    }

    private void listCourses(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Course> courses = CourseDAO.getAllCourses();
        request.setAttribute("courses", courses);
        RequestDispatcher dispatcher = request.getRequestDispatcher("./pages/CourseView.jsp");
        dispatcher.forward(request, response);
    }

    private void searchCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        List<Course> courses = CourseDAO.getSearchedCourses(keyword);
        request.setAttribute("courses", courses);
        request.setAttribute("keyword", keyword);
        RequestDispatcher dispatcher = request.getRequestDispatcher("./pages/CourseView.jsp");
        dispatcher.forward(request, response);
    }

    private void insertCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseName = request.getParameter("courseName");
        String courseDescription = request.getParameter("courseDescription");

        Course course = new Course(courseName, courseDescription);

        CourseDAO.insertCourse(course);
        response.sendRedirect("Courses?action=list");
    }

    private void updateCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int courseId = Integer.parseInt(request.getParameter("id"));
        String courseName = request.getParameter("courseName");
        String courseDescription = request.getParameter("courseDescription");
        Course course = new Course(courseId, courseName, courseDescription);

        CourseDAO.updateCourse(course);
        response.sendRedirect("Courses?action=list");
    }

    private void deleteCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        CourseDAO.deleteCourse(id);
        response.sendRedirect("Courses?action=list");
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("./pages/CourseCreate.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Course existingCourse = CourseDAO.getCourseById(id);
        System.out.println(existingCourse.toString());
        RequestDispatcher dispatcher = request.getRequestDispatcher("./pages/CourseUpdate.jsp");
        request.setAttribute("course", existingCourse);
        dispatcher.forward(request, response);
    }

    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Course existingCourse = CourseDAO.getCourseById(id);
        System.out.println(existingCourse.toString());
        RequestDispatcher dispatcher = request.getRequestDispatcher("./pages/CourseDelete.jsp");
        request.setAttribute("course", existingCourse);
        dispatcher.forward(request, response);
    }

}
