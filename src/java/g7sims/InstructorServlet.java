package g7sims;

import g7sims.classes.Instructor;
import g7sims.classes.InstructorDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/Instructors")
public class InstructorServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "insert" ->
                showNewForm(request, response);
            case "list" ->
                listInstructors(request, response);
            case "update" ->
                showEditForm(request, response);
            case "delete" ->
                showDeleteForm(request, response);
            default ->
                listInstructors(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "insert" ->
                insertInstructor(request, response);
            case "update" ->
                updateInstructor(request, response);
            case "delete" ->
                deleteInstructor(request, response);
            case "search" ->
                searchInstructor(request, response);
            default ->
                listInstructors(request, response);
        }
    }

    private void listInstructors(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Instructor> instructors = InstructorDAO.getAllInstructors();
        request.setAttribute("instructors", instructors);
        RequestDispatcher dispatcher = request.getRequestDispatcher("./pages/InstructorView.jsp");
        dispatcher.forward(request, response);
    }

    private void searchInstructor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        List<Instructor> instructors = InstructorDAO.getSearchedInstructors(keyword);
        request.setAttribute("instructors", instructors);
        request.setAttribute("keyword", keyword);
        RequestDispatcher dispatcher = request.getRequestDispatcher("./pages/InstructorView.jsp");
        dispatcher.forward(request, response);
    }

    private void insertInstructor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        Instructor instructor = new Instructor(firstName, lastName, email);

        InstructorDAO.insertInstructor(instructor);
        response.sendRedirect("Instructors?action=list");
    }

    private void updateInstructor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int instructorId = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        Instructor instructor = new Instructor(instructorId, firstName, lastName, email);

        InstructorDAO.updateInstructor(instructor);
        response.sendRedirect("Instructors?action=list");
    }

    private void deleteInstructor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        InstructorDAO.deleteInstructor(id);
        response.sendRedirect("Instructors?action=list");
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("./pages/InstructorCreate.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Instructor existingInstructor = InstructorDAO.getInstructorById(id);
        System.out.println(existingInstructor.toString());
        RequestDispatcher dispatcher = request.getRequestDispatcher("./pages/InstructorUpdate.jsp");
        request.setAttribute("instructor", existingInstructor);
        dispatcher.forward(request, response);
    }

    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Instructor existingInstructor = InstructorDAO.getInstructorById(id);
        System.out.println(existingInstructor.toString());
        RequestDispatcher dispatcher = request.getRequestDispatcher("./pages/InstructorDelete.jsp");
        request.setAttribute("instructor", existingInstructor);
        dispatcher.forward(request, response);
    }
}
