
import java.util.ArrayList;
import java.io.Serializable;;

public class Course implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String title;
	private String courseID;
	private int maxNumberOfStudents;
	private int enrolledStudentNum;
	private ArrayList<Student> studentsInCourse;
	private String professor;
	private int sectionNumber;
	private String location;
	private String time;
	
	private ArrayList<String> courseInformation = new ArrayList<String>();
	
	public Course(String title, String courseID, int maxNumberOfStudents, int enrolledStudentNum, ArrayList<Student> studentsInCourse, String professor, int sectionNumber, String location, String time) {
		//set up all the properties for the object
		this.title = title;
		this.courseID = courseID;
		this.maxNumberOfStudents = maxNumberOfStudents;
		this.enrolledStudentNum = enrolledStudentNum;
		this.studentsInCourse = studentsInCourse;
		this.professor = professor;
		this.sectionNumber = sectionNumber;
		this.location = location;
		this.time = time;
	}
	
	//GETTERS
    /**
     * @return course ID
     */
	protected String getCourseID() {
		String courseID = this.courseID;
		return courseID;
	}
	
    /**
     * @return course name
     */
	protected String getCourseName() {
		String courseName = this.title;
		return courseName;
	}
	
    /**
     * @return course section number
     */
	protected int getSectionNumber() {
		int sectionNum = this.sectionNumber;
		return sectionNum;
	}
	
    /**
     * @return maximum number of students
     */
	protected int getMaxNumOfStudents() {
		int maxStu = this.maxNumberOfStudents;
		return maxStu;
	}
	
    /**
     * @return number of students enrolled.
     */
	protected int getNumOfEnrolledStudents() {
		int enrolledStu = this.enrolledStudentNum;
		return enrolledStu;
	}
	
    /**
     * @return ArrayList<Student> of students in course.
     */
	protected ArrayList<Student> getListOfStudents() {
		ArrayList<Student> listOfStu = this.studentsInCourse;
		return listOfStu;
	}	
	
    /**
     * @return an array of course information.
     */
	protected ArrayList<String> getCourseInformation() {
		this.courseInformation.add(this.title);
		this.courseInformation.add(this.courseID);
		this.courseInformation.add(this.professor);
		this.courseInformation.add(String.valueOf(this.maxNumberOfStudents));
		this.courseInformation.add(String.valueOf(this.enrolledStudentNum));
		this.courseInformation.add(this.location);
		
		return this.courseInformation;
	}

	//SETTERS
    /**
     * Sets maximum number of students.
     */
	protected void setMaxNumOfStudents(int maxNumStudents) {
		this.maxNumberOfStudents = maxNumStudents;
	}
	
    /**
     * Sets number of enrolled students.
     */
	protected void setNumOfEnrolledStudents(int numEnrolledStudents) {
		this.enrolledStudentNum = numEnrolledStudents;
	}
	
    /**
     * Sets instructor name.
     */
	protected void setProfessor(String prof) {
		this.professor = prof;
	}
	
    /**
     * Sets section number.
     */
	protected void setSectionNumber(int sectNum) {
		this.sectionNumber = sectNum;
	}
	
    /**
     * Sets course location.
     */
	protected void setLocation(String loc) {
		this.location = loc;
	}
	
    /**
     * Sets course time.
     */
	protected void setTime(String timeT) {
		this.time = timeT;
	}
	
	
    /**
     * Prints course information.
     */
	protected void printCourseInformation() {
		System.out.println("Course Name: " + this.title);
		System.out.println("Course ID: " + this.courseID);
		System.out.println("Course Instructor: " + this.professor);
		System.out.println("Maximum number of students allowed: " + this.maxNumberOfStudents);
		System.out.println("Number of students currently enrolled: " + this.enrolledStudentNum);
		System.out.println("Course Location: " + this.location);
	}


	
	
}
