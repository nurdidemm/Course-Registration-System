import java.util.ArrayList;

interface StudentInterface {
	
	//GETTERS
	public String getFirstName();
	
	public String getLastName();
	
	public String getUsername();

	public String getPassword();

	public ArrayList<Course> getCourseList();
	
	//STUDENT MENU METHODS
	public void viewAllCourses(ArrayList<Course> allCourses);
	
	public void viewAllOpenCourses(ArrayList<Course> allCourses);
	
	public void registerToCourse(ArrayList<Course> allCourses, ArrayList<Student> allStudents);
	
	public void withdrawFromCourse(ArrayList<Course> allCourses);
	
	public void viewMyCourses();
	
}