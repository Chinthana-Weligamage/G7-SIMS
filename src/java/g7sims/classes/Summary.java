package g7sims.classes;

public class Summary {

    private final int studentCount;
    private final int courseCount;
    private final int instructorCount;
    private final int enrollmentCount;

    public Summary(int studentCount, int courseCount, int instructorCount, int enrollmentCount) {
        this.studentCount = studentCount;
        this.courseCount = courseCount;
        this.instructorCount = instructorCount;
        this.enrollmentCount = enrollmentCount;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public int getCourseCount() {
        return courseCount;
    }

    public int getInstructorCount() {
        return instructorCount;
    }

    public int getEnrollmentCount() {
        return enrollmentCount;
    }

}
