package g7sims.classes;

import g7sims.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAO {

    public static List<Enrollment> getAllEnrollments() {
        List<Enrollment> enrollments = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery("SELECT * FROM enrollments")) {

            while (resultSet.next()) {
                int enrollmentId = resultSet.getInt("enrollment_id");
                int studentId = resultSet.getInt("student_id");
                int courseId = resultSet.getInt("course_id");

                Enrollment enrollment = new Enrollment(enrollmentId, studentId, courseId);

                enrollments.add(enrollment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return enrollments;
    }

    public static List<Enrollment> getAllEnrollmentsWithDetails() {
        List<Enrollment> enrollments = new ArrayList<>();
        String sql = "SELECT e.enrollment_id, s.student_id, CONCAT(s.first_name, ' ', s.last_name) AS student_name, c.course_id, c.course_name "
                + "FROM enrollments e "
                + "JOIN students s ON e.student_id = s.student_id "
                + "JOIN courses c ON e.course_id = c.course_id";

        try (Connection connection = DBUtil.getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int enrollmentId = resultSet.getInt("enrollment_id");
                int studentId = resultSet.getInt("student_id");
                String studentName = resultSet.getString("student_name");
                int courseId = resultSet.getInt("course_id");
                String courseName = resultSet.getString("course_name");
                Enrollment enrollment = new Enrollment(enrollmentId, studentId, studentName, courseId, courseName);

                enrollments.add(enrollment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrollments;
    }

    public static Enrollment getEnrollmentById(int enrollmentId) {
        Enrollment enrollment = null;
        String sql = "SELECT * FROM enrollments WHERE enrollment_id = ?";

        try (Connection connection = DBUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, enrollmentId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int studentId = resultSet.getInt("student_id");
                int courseId = resultSet.getInt("course_id");

                enrollment = new Enrollment(enrollmentId, studentId, courseId);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return enrollment;
    }

    public static Enrollment getEnrollmentByIdWithDetails(int enrollmentId) {
        Enrollment enrollment = null;
        String sql = "SELECT e.enrollment_id, s.student_id, CONCAT(s.first_name, ' ', s.last_name) AS student_name, "
                + "c.course_id, c.course_name "
                + "FROM enrollments e "
                + "JOIN students s ON e.student_id = s.student_id "
                + "JOIN courses c ON e.course_id = c.course_id "
                + "WHERE e.enrollment_id = ?";

        try (Connection connection = DBUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, enrollmentId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int studentId = resultSet.getInt("student_id");
                String studentName = resultSet.getString("student_name");
                int courseId = resultSet.getInt("course_id");
                String courseName = resultSet.getString("course_name");

                enrollment = new Enrollment(enrollmentId, studentId, studentName, courseId, courseName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return enrollment;
    }

// Check if the student exists
    private static boolean studentExists(int studentId) {
        String sql = "SELECT 1 FROM students WHERE student_id = ?";
        try (Connection connection = DBUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentId);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // Returns true if the student exists
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Check if the course exists
    private static boolean courseExists(int courseId) {
        String sql = "SELECT 1 FROM courses WHERE course_id = ?";
        try (Connection connection = DBUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, courseId);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // Returns true if the course exists
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Insert enrollment with validation
    public static void insertEnrollment(Enrollment enrollment) {
        if (!studentExists(enrollment.getStudentId())) {
            System.out.println("Student ID " + enrollment.getStudentId() + " does not exist.");
            return;
        }

        if (!courseExists(enrollment.getCourseId())) {
            System.out.println("Course ID " + enrollment.getCourseId() + " does not exist.");
            return;
        }

        String sql = "INSERT INTO enrollments (student_id, course_id) VALUES (?, ?)";

        try (Connection connection = DBUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, enrollment.getStudentId());
            statement.setInt(2, enrollment.getCourseId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateEnrollment(Enrollment enrollment) {
        String sql = "UPDATE enrollments SET student_id = ?, course_id = ? WHERE enrollment_id = ?";

        try (Connection connection = DBUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, enrollment.getStudentId());
            statement.setInt(2, enrollment.getCourseId());
            statement.setInt(4, enrollment.getEnrollmentId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteEnrollment(int id) {
        String sql = "DELETE FROM enrollments WHERE enrollment_id = ?";

        try (Connection connection = DBUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Enrollment> getSearchedEnrollments(String keyword) {
        List<Enrollment> enrollments = new ArrayList<>();

        String sql = "SELECT * FROM enrollments WHERE student_id LIKE ? OR course_id LIKE ?";

        try (Connection connection = DBUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            String searchKeyword = "%" + keyword + "%";
            statement.setString(1, searchKeyword);
            statement.setString(2, searchKeyword);
            statement.setString(3, searchKeyword);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int enrollmentId = resultSet.getInt("enrollment_id");
                int studentId = resultSet.getInt("student_id");
                int courseId = resultSet.getInt("course_id");

                Enrollment enrollment = new Enrollment(enrollmentId, studentId, courseId);

                enrollments.add(enrollment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrollments;
    }
}
