package g7sims;

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

@WebServlet("/Students")
public class StudentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "insert" ->
                showNewForm(request, response);
            case "list" ->
                listStudents(request, response);
            case "update" ->
                showEditForm(request, response);
            case "delete" ->
                showDeleteForm(request, response);
            default ->
                listStudents(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "insert" ->
                insertStudent(request, response);
            case "update" ->
                updateStudent(request, response);
            case "delete" ->
                deleteStudent(request, response);
            case "search" ->
                searchStudent(request, response);
            default ->
                listStudents(request, response);
        }
    }

    private void listStudents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> students = StudentDAO.getAllStudents();
        request.setAttribute("students", students);
        RequestDispatcher dispatcher = request.getRequestDispatcher("./pages/StudentView.jsp");
        dispatcher.forward(request, response);
    }
    
    private void searchStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        List<Student> students = StudentDAO.getSearchedStudents(keyword);
        request.setAttribute("students", students);
        request.setAttribute("keyword", keyword);
        RequestDispatcher dispatcher = request.getRequestDispatcher("./pages/StudentView.jsp");
        dispatcher.forward(request, response);
    }

    private void insertStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        Student student = new Student(firstName, lastName, email);

        StudentDAO.insertStudent(student);
        response.sendRedirect("Students?action=list");
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int studentId = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        Student student = new Student(studentId, firstName, lastName, email);

        StudentDAO.updateStudent(student);
        response.sendRedirect("Students?action=list");
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        StudentDAO.deleteStudent(id);
        response.sendRedirect("Students?action=list");
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("./pages/StudentCreate.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Student existingStudent = StudentDAO.getStudentById(id);
        System.out.println(existingStudent.toString());
        RequestDispatcher dispatcher = request.getRequestDispatcher("./pages/StudentUpdate.jsp");
        request.setAttribute("student", existingStudent);
        dispatcher.forward(request, response);
    }

    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Student existingStudent = StudentDAO.getStudentById(id);
        System.out.println(existingStudent.toString());
        RequestDispatcher dispatcher = request.getRequestDispatcher("./pages/StudentDelete.jsp");
        request.setAttribute("student", existingStudent);
        dispatcher.forward(request, response);
    }

}
