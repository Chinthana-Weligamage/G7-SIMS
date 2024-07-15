package g7sims.classes;

import g7sims.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoursePopularity {

    public static List<Map.Entry<String, Integer>> getCoursePopularity() {
        List<Map.Entry<String, Integer>> coursePopularityList = new ArrayList<>();

        String sql = "SELECT courses.course_name, COUNT(enrollments.course_id) AS enrollment_count "
                + "FROM courses LEFT JOIN enrollments ON courses.course_id = enrollments.course_id "
                + "GROUP BY courses.course_id";

        try (Connection connection = DBUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String courseName = resultSet.getString("course_name");
                int enrollmentCount = resultSet.getInt("enrollment_count");
                coursePopularityList.add(new HashMap.SimpleEntry<>(courseName, enrollmentCount));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return coursePopularityList;
    }
}
