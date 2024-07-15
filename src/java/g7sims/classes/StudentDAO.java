package g7sims.classes;

import g7sims.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public static List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        
        try (Connection connection = DBUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM students")) {
             
            while (resultSet.next()) {
                int studentId = resultSet.getInt("student_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                
                Student student = new Student(studentId, firstName, lastName, email);
                
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return students;
    }
    
    public static Student getStudentById(int studentId) {
        Student student = null;
        String sql = "SELECT * FROM students WHERE student_id = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, studentId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");

                student = new Student(studentId, firstName, lastName, email);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }
    
    public static void insertStudent(Student student) {
        String sql = "INSERT INTO students (first_name, last_name, email) VALUES (?, ?, ?)";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setString(3, student.getEmail());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void updateStudent(Student student) {
        String sql = "UPDATE students SET first_name = ?, last_name = ?, email = ? WHERE student_id = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setString(3, student.getEmail());
            statement.setInt(4, student.getStudentId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE student_id = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static List<Student> getSearchedStudents(String keyword) {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE first_name LIKE ? OR last_name LIKE ? OR email LIKE ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            String searchKeyword = "%" + keyword + "%";
            statement.setString(1, searchKeyword);
            statement.setString(2, searchKeyword);
            statement.setString(3, searchKeyword);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int studentId = resultSet.getInt("student_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                
                Student student = new Student(studentId, firstName, lastName, email);
                
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
}
