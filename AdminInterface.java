import java.util.ArrayList;
import java.io.FileNotFoundException;

public interface AdminInterface {
	
	//COURSE MANAGEMENT MENU METHODS
	void createCourse(ArrayList<Course> allCourses, ArrayList<Course> fullCourses);

	void deleteCourse(ArrayList<Course> allCourses, ArrayList<Course> fullCourses);
	
	void editCourse(ArrayList<Course> allCourses, ArrayList<Course> fullCourses);
	
	void getCourseInfoByID(ArrayList<Course> allCourses);
	
	void registerStudent(ArrayList<Student> allStudents);
		
	//REPORTS MENU METHODS
	void viewAllCourses(ArrayList<Course> allCourses);
	
	void viewAllFullCourses(ArrayList<Course> allCourses);
	
	void downloadFullCourses(ArrayList<Course> allCourse) throws FileNotFoundException;
	
	void viewStudentList(ArrayList<Course> allCourses);

	void viewStudentCourses(ArrayList<Student> allStudents);
	
	void sortCourses(ArrayList<Course> allCourses);
}