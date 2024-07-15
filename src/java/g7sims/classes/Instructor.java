package g7sims.classes;

public class Instructor {

    private int instructorId;
    private final String firstName;
    private final String lastName;
    private final String email;

    public Instructor(int instructorId, String firstName, String lastName, String email) {
        this.instructorId = instructorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Instructor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
