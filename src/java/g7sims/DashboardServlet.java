package g7sims;

import g7sims.classes.CoursePopularity;
import g7sims.classes.Summary;
import g7sims.classes.SummaryDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(name = "DashboardServlet", urlPatterns = {"/Dashboard"})
public class DashboardServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve the summary data
        Summary summary = SummaryDAO.getSummary();
        request.setAttribute("summary", summary);
        
        
        // Retrieve the Course Popularity data
        List<Map.Entry<String, Integer>> coursePopularityList = CoursePopularity.getCoursePopularity();

        List<String> courseNames = new ArrayList<>();
        List<Integer> enrollmentsPerCourse = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : coursePopularityList) {
            courseNames.add("'" + entry.getKey() + "'");
            enrollmentsPerCourse.add(entry.getValue());
        }

        request.setAttribute("courseNames", courseNames);
        request.setAttribute("enrollmentsPerCourse", enrollmentsPerCourse);

        // Forward the request to the Dashboard.jsp        
        RequestDispatcher dispatcher = request.getRequestDispatcher("./pages/Dashboard.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
