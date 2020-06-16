import java.util.ArrayList;
import java.util.Scanner;
import java.io.Serializable;

public class Student extends User implements StudentInterface, Serializable {
	
	private static final long serialVersionUID = 1L;

	private ArrayList<Course> myCourses = new ArrayList<Course>();
	
	static Scanner input = new Scanner(System.in);
	int studentID;
	
    /**
     * Constructs a Student Object
     * @param username
     * @param password
     * @param firstName
     * @param lastName
     */
	public Student(String username, String password, String firstName, String lastName) {
		// TODO Auto-generated constructor stub
		super(username, password, firstName, lastName);
	}
	
	//GETTERS
    /**
     * @return First name of the student.
     */
	public String getFirstName() {
		String fName = this.firstName;
		return fName;
	}
	
    /**
     * @return Last name of the Student.
     */
	public String getLastName() {
		String lName = this.lastName;
		return lName;
	}
	
    /**
     * @return User name of the student.
     */
	public String getUsername() {
		String userName = this.username;
		return userName;
	}
	
    /**
     * @return Password of the student.
     */
	public String getPassword() {
		String passWord = this.password;
		return passWord;
	}
	
    /**
     * @return Course list of the student.
     */
	public ArrayList<Course> getCourseList() {
		ArrayList<Course> courseList = this.myCourses;
		return courseList;
	}
	
    /**
     * @return Unique student ID.
     */
	public int getStudentID() {
		int studentID = this.studentID;
		return studentID;
	}
	
	//SETTERS
    /**
     * Sets studentID.
     */
	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}
	
	//STUDENT MENU METHODS
    /**
     * Prints all courses in the system.
     * @param allCourses
     */
	public void viewAllCourses(ArrayList<Course> allCourses) {
		System.out.println("--View All Courses--");
		System.out.println("");
		System.out.println("");

		System.out.println("Course Name ----- Section Number");
		System.out.println("");
		
		//iterate through the list of all course objects
		for(int i=0; i < allCourses.size(); i++) {
			//print Course Name & Section Number
			System.out.println(allCourses.get(i).getCourseName() + " ------ " + allCourses.get(i).getSectionNumber());
		}
	}

	/**
	 * Prints all 'open' courses in the system
	 * @param allCourses
	 */
	public void viewAllOpenCourses(ArrayList<Course> allCourses) {
		System.out.println("--View All Open Courses--");
		System.out.println("");
		
		for(int i=0; i < allCourses.size(); i++) {
			if (allCourses.get(i).getMaxNumOfStudents() > allCourses.get(i).getNumOfEnrolledStudents()) {
				System.out.println(allCourses.get(i).getCourseName());
			}
		}
	}
	
    /**
     * Register a student to a given course.
     * Adds student's first and last name to appropriate course's student list.
     * Adds the course to student's course list.
     * @param allCourses
     * @param allStudents
     */
	public void registerToCourse(ArrayList<Course> allCourses, ArrayList<Student> allStudents) {
		System.out.println("--Register to a Course--");
		System.out.println("");
		
		System.out.println("Enter Course Name: ");
		String courseName = input.nextLine();

		System.out.println("Enter Section Number #: ");
		while(!input.hasNextInt()) {
			System.out.println("Please enter an integer: ");
		    input.next();
		}
		int courseSection = input.nextInt();

		//skip to next line
		input.nextLine();
		
		System.out.println("Enter first name: ");
		String firstName = input.nextLine();

		System.out.println("Enter last name: ");
		String lastName = input.nextLine();
		
		//get student object from student first and last name
		for (int i=0; i < allStudents.size(); i++) {
			Student stu = allStudents.get(i);
			if((firstName.equals(stu.getFirstName())) && (lastName.equals(stu.getLastName()))) {					
				
				//get course object from course name and section number
				for (int j=0; j < allCourses.size(); j++) {
					Course crs = allCourses.get(j);
					if ((courseName.equals(crs.getCourseName()) && (courseSection == crs.getSectionNumber()))) {
						
						//add student first and last name to course's student object list
						if (crs.getMaxNumOfStudents() > crs.getNumOfEnrolledStudents()) {
							crs.getListOfStudents().add(stu);
							crs.setNumOfEnrolledStudents(crs.getNumOfEnrolledStudents()+1);
							this.myCourses.add(crs);
						}
						else {
							System.out.println("Sorry, this course is full :(");
						}
					}
				}
			}
		}
	}
	
    /**
     * Withdraw a student from a given course.
     * Removes Student object from course's student list.
     * Removes Course from student's course list.
     * @param allCourses
     */
	public void withdrawFromCourse(ArrayList<Course> allCourses) {
		System.out.println("--Withdraw from a Course--");
		System.out.println("");

		System.out.println("Enter course name: ");
		String courseName = input.nextLine();

		System.out.println("Enter first name: ");
		String firstName = input.nextLine();

		System.out.println("Enter last name: ");
		String lastName = input.nextLine();		
		
		//get course object from section number
		for (int i=0; i < allCourses.size(); i++) {
			if (courseName.equals(allCourses.get(i).getCourseName())) {
				Course course = allCourses.get(i);
				//iterate through course's list of students
				for (int j=0; j < course.getListOfStudents().size(); j++) {
					
					//if found remove first and last name from list of student objects
					if ((course.getListOfStudents().get(j).getFirstName().equals(firstName)) && (course.getListOfStudents().get(j).getLastName().equals(lastName))) {
						Student stu = course.getListOfStudents().get(j);
						course.getListOfStudents().remove(j);
						
						//decrease enrolled student number by 1
						course.setNumOfEnrolledStudents(course.getNumOfEnrolledStudents() - 1);
						
						//remove course object from student's list of course objects
						for (int k =0; k < stu.getCourseList().size(); k++) {
							if(stu.getCourseList().get(k).getCourseName().equals(courseName)) {
								stu.getCourseList().remove(k);
							}
						}
					}
				}
			}
		}
		input.close();
	}
	
    /**
     * Prints all courses a student is registered in.
     */
	public void viewMyCourses() {
		System.out.println("--View My Courses--");
		System.out.println("");
		
		//get student's list of course objects
		for (int i=0; i < this.getCourseList().size(); i++) {
			System.out.println(this.getCourseList().get(i).getCourseName());
		}
		System.out.println("");
	}
	

}