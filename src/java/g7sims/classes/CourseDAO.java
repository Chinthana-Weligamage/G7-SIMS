package g7sims.classes;

import g7sims.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    public static List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery("SELECT * FROM courses")) {

            while (resultSet.next()) {
                int courseId = resultSet.getInt("course_id");
                String courseName = resultSet.getString("course_name");
                String courseDescription = resultSet.getString("course_description");

                Course course = new Course(courseId, courseName, courseDescription);

                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;
    }

    public static Course getCourseById(int courseId) {
        Course course = null;
        String sql = "SELECT * FROM courses WHERE course_id = ?";

        try (Connection connection = DBUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, courseId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String courseName = resultSet.getString("course_name");
                String courseDescription = resultSet.getString("course_description");

                course = new Course(courseId, courseName, courseDescription);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return course;
    }

    public static void insertCourse(Course course) {
        String sql = "INSERT INTO courses (course_name, course_description) VALUES (?, ?)";

        try (Connection connection = DBUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, course.getCourseName());
            statement.setString(2, course.getCourseDescription());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateCourse(Course course) {
        String sql = "UPDATE courses SET course_name = ?, course_description = ? WHERE course_id = ?";

        try (Connection connection = DBUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, course.getCourseName());
            statement.setString(2, course.getCourseDescription());
            statement.setInt(3, course.getCourseId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteCourse(int id) {
        String sql = "DELETE FROM courses WHERE course_id = ?";

        try (Connection connection = DBUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Course> getSearchedCourses(String keyword) {
        List<Course> courses = new ArrayList<>();

        String sql = "SELECT * FROM courses WHERE course_name LIKE ? OR course_description LIKE ?";

        try (Connection connection = DBUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            String searchKeyword = "%" + keyword + "%";
            statement.setString(1, searchKeyword);
            statement.setString(2, searchKeyword);
            statement.setString(3, searchKeyword);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int courseId = resultSet.getInt("course_id");
                String courseName = resultSet.getString("course_name");
                String courseDescription = resultSet.getString("course_description");

                Course course = new Course(courseId, courseName, courseDescription);

                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
}
