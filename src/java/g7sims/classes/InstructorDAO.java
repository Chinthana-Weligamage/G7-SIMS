package g7sims.classes;

import g7sims.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InstructorDAO {
    public static List<Instructor> getAllInstructors() {
        List<Instructor> instructors = new ArrayList<>();
        
        try (Connection connection = DBUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM instructors")) {
             
            while (resultSet.next()) {
                int instructorId = resultSet.getInt("instructor_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                
                Instructor instructor = new Instructor(instructorId, firstName, lastName, email);
                
                instructors.add(instructor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return instructors;
    }
    
    public static Instructor getInstructorById(int instructorId) {
        Instructor instructor = null;
        String sql = "SELECT * FROM instructors WHERE instructor_id = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, instructorId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");

                instructor = new Instructor(instructorId, firstName, lastName, email);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return instructor;
    }
    
    public static void insertInstructor(Instructor instructor) {
        String sql = "INSERT INTO instructors (first_name, last_name, email) VALUES (?, ?, ?)";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, instructor.getFirstName());
            statement.setString(2, instructor.getLastName());
            statement.setString(3, instructor.getEmail());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void updateInstructor(Instructor instructor) {
        String sql = "UPDATE instructors SET first_name = ?, last_name = ?, email = ? WHERE instructor_id = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, instructor.getFirstName());
            statement.setString(2, instructor.getLastName());
            statement.setString(3, instructor.getEmail());
            statement.setInt(4, instructor.getInstructorId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void deleteInstructor(int id) {
        String sql = "DELETE FROM instructors WHERE instructor_id = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static List<Instructor> getSearchedInstructors(String keyword) {
        List<Instructor> instructors = new ArrayList<>();
        String sql = "SELECT * FROM instructors WHERE first_name LIKE ? OR last_name LIKE ? OR email LIKE ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            String searchKeyword = "%" + keyword + "%";
            statement.setString(1, searchKeyword);
            statement.setString(2, searchKeyword);
            statement.setString(3, searchKeyword);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int instructorId = resultSet.getInt("instructor_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                
                Instructor instructor = new Instructor(instructorId, firstName, lastName, email);
                
                instructors.add(instructor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return instructors;
    }
}
