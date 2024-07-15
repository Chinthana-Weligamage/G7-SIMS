package g7sims.classes;

public class Enrollment {

    private int enrollmentId;
    private final int courseId;
    private final int studentId;
    private String studentName;
    private String courseName;

    public Enrollment(int enrollmentId, int studentId, int courseId) {
        this.enrollmentId = enrollmentId;
        this.courseId = courseId;
        this.studentId = studentId;
    }

    public Enrollment(int courseId, int studentId) {
        this.courseId = courseId;
        this.studentId = studentId;
    }

    public Enrollment(int enrollmentId, int studentId, String studentName, int courseId, String courseName) {
        this.enrollmentId = enrollmentId;
        this.courseId = courseId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.courseName = courseName;
    }

    public int getEnrollmentId() {
        return enrollmentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getCourseName() {
        return courseName;
    }

}
