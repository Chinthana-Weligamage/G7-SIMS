package g7sims.classes;

import g7sims.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SummaryDAO {

    public static Summary getSummary() {
        int studentCount = 0;
        int courseCount = 0;
        int instructorCount = 0;
        int enrollmentCount = 0;

        String studentCountSQL = "SELECT COUNT(*) AS count FROM students";
        String courseCountSQL = "SELECT COUNT(*) AS count FROM courses";
        String instructorCountSQL = "SELECT COUNT(*) AS count FROM instructors";
        String enrollmentCountSQL = "SELECT COUNT(*) AS count FROM enrollments";

        try (Connection connection = DBUtil.getConnection()) {

            // Fetch student count
            try (PreparedStatement studentStmt = connection.prepareStatement(studentCountSQL);
                 ResultSet studentRs = studentStmt.executeQuery()) {
                if (studentRs.next()) {
                    studentCount = studentRs.getInt("count");
                }
            }

            // Fetch course count
            try (PreparedStatement courseStmt = connection.prepareStatement(courseCountSQL);
                 ResultSet courseRs = courseStmt.executeQuery()) {
                if (courseRs.next()) {
                    courseCount = courseRs.getInt("count");
                }
            }

            // Fetch instructor count
            try (PreparedStatement instructorStmt = connection.prepareStatement(instructorCountSQL);
                 ResultSet instructorRs = instructorStmt.executeQuery()) {
                if (instructorRs.next()) {
                    instructorCount = instructorRs.getInt("count");
                }
            }

            // Fetch enrollment count
            try (PreparedStatement enrollmentStmt = connection.prepareStatement(enrollmentCountSQL);
                 ResultSet enrollmentRs = enrollmentStmt.executeQuery()) {
                if (enrollmentRs.next()) {
                    enrollmentCount = enrollmentRs.getInt("count");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new Summary(studentCount, courseCount, instructorCount, enrollmentCount);
    }
}

