import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.Serializable;;

public class Admin extends User implements AdminInterface, Serializable {
	

	private static final long serialVersionUID = 1L;
	
	Scanner input = new Scanner(System.in);
   
	/**
     * Constructs a Student Object
     * @param username
     * @param password
     * @param firstName
     * @param lastName
     */
	public Admin(String username, String password, String firstName, String lastName) {
		// TODO Auto-generated constructor stub
		super(username, password, firstName, lastName);
	}
	
	//COURSE MANAGEMENT MENU METHODS
    /**
     * Creates a course object and adds it to a given course list.
     * @param allCourses
     */
	public void createCourse(ArrayList<Course> allCourses, ArrayList<Course> fullCourses) {
		System.out.println("--Create a new Course--");
		
		System.out.println("Press ENTER to continue.");
		input.nextLine();

	    System.out.println("Course Name: ");
		String courseName = input.nextLine();
		
		System.out.println("Course ID: ");
		String courseID = input.nextLine();
			
		System.out.println("Maximum number of students allowed in the course: ");
		//ensure input is integer
		while(!input.hasNextInt()) {
			System.out.println("Please enter an integer: ");
		    input.next();
		}
		int maxStudents = input.nextInt();
			
		System.out.println("Number of students that are currently enrolled in the course: ");
		//ensure input is integer
		while(!input.hasNextInt()) {
			System.out.println("Please enter an integer: ");
		    input.next();
		}
		int currentStudents = input.nextInt();
		
		boolean maxExceeded = false;
		if (currentStudents > maxStudents) {
			maxExceeded = true;
		}
		while(maxExceeded) {
			System.out.println("Cannot exceed maximum students allowed: ");
			currentStudents = input.nextInt();
			if (currentStudents <= maxStudents) {
				maxExceeded = false;
			}
		}
		
		//skip to next line after int
		input.nextLine();
	
		System.out.println("Course instructor: ");
		String professor = input.nextLine();
			
		System.out.println("Section Number: ");
		//ensure input is integer
		while(!input.hasNextInt()) {
			System.out.println("Please enter an integer: ");
		    input.next();
		}
		int sectionNum = input.nextInt();
			
		//skip to next line after int
		input.nextLine();
	
		System.out.println("Course Location: ");
		String courseLoc = input.nextLine();
			
		System.out.println("Time: ");
		String time = input.nextLine();
			
		ArrayList<Student> studentList = new ArrayList<Student>();
		
		Course crs = new Course(courseName, courseID, maxStudents, currentStudents, studentList, professor, sectionNum, courseLoc, time);
		
		//add course to ArrayList<Course> allCourses
		allCourses.add(crs);
	
		//add course to full courses list if full
		if (currentStudents == maxStudents) {
			fullCourses.add(crs);
		}
	}

    /**
     * Deletes a given course object and removes it from a given course list.
     * @param allCourses
     */
	public void deleteCourse(ArrayList<Course> allCourses, ArrayList<Course> fullCourses) {
		System.out.println("--Delete a Course--");
		System.out.println("");

		System.out.println("Please enter Course ID:");
		String courseID = input.nextLine();
		
		System.out.println("Please enter Course Section Number:");
		int sectionNum = input.nextInt();
				
		for (int i=0; i < allCourses.size(); i++) {
			//remove the course from all courses list
			if ((courseID.equals(allCourses.get(i).getCourseID())) && (sectionNum == allCourses.get(i).getSectionNumber())) {
				allCourses.remove(i);
				System.out.println("Course deleted from Univerity Course List");
			}
		}
		for (int i=0; i < fullCourses.size(); i++) {
			//remove the course from full courses list if it exists
			if ((courseID.equals(fullCourses.get(i).getCourseID())) && (sectionNum == fullCourses.get(i).getSectionNumber())) {
				fullCourses.remove(i);
			}
		}
	}
	
    /**
     * Sets a given course's information
     * @param allCourses
     */
	public void editCourse(ArrayList<Course> allCourses, ArrayList<Course> fullCourses) {
		Course editCourse;
		
	    System.out.println("--Edit a Course--");
		System.out.println("");

		System.out.println("Please enter Course ID:");
		String courseID = input.nextLine();
		
		System.out.println("Please enter Course Section Number:");
		while(!input.hasNextInt()) {
			System.out.println("Please enter an integer: ");
		    input.next();
		}
		int sectionNum = input.nextInt();
		
		for (int i=0; i < allCourses.size(); i++) {
			if ((courseID.equals(allCourses.get(i).getCourseID())) && (sectionNum == allCourses.get(i).getSectionNumber())) {
				editCourse = allCourses.get(i);
				boolean keepgoingEditCourse = true;
				while (keepgoingEditCourse == true) {
					System.out.println("Would you like to change: ");
					System.out.println("1. Maximum number of students");
					System.out.println("2. Enrolled student number");
					System.out.println("3. Course instructor");
					System.out.println("4. Section number");
					System.out.println("5. Course location");
					System.out.println("6. Time");
					System.out.println("7. Exit");
	
					String response = input.nextLine();
					
					switch (response) {
						case "1":
							System.out.println("Enter new maximum number of students: ");
							while(!input.hasNextInt()) {
								System.out.println("Please enter an integer: ");
							    input.next();
							}
							int newMax = input.nextInt();
							editCourse.setMaxNumOfStudents(newMax);
						break;
						case "2":
							System.out.println("Enter new enrolled student number: ");
							while(!input.hasNextInt()) {
								System.out.println("Please enter an integer: ");
							    input.next();
							}
							int newEnrolled = input.nextInt();
							
							boolean maxExceeded = false;
							if (newEnrolled > editCourse.getMaxNumOfStudents()) {
								maxExceeded = true;
							}
							while(maxExceeded) {
								System.out.println("Cannot exceed maximum students allowed: ");
								newEnrolled = input.nextInt();
								if (newEnrolled <= editCourse.getMaxNumOfStudents()) {
									maxExceeded = false;
									if (newEnrolled == editCourse.getMaxNumOfStudents()) {
										fullCourses.add(editCourse);
									}
								}
							}
							editCourse.setNumOfEnrolledStudents(newEnrolled);
						break;
						case "3":
							System.out.println("Enter new course instructor: ");
							String newInstructor = input.nextLine();
							editCourse.setProfessor(newInstructor);
						break;
						case "4":
							System.out.println("Enter new section number: ");
							while(!input.hasNextInt()) {
								System.out.println("Please enter an integer: ");
							    input.next();
							}
							int sectionN = input.nextInt();
							editCourse.setSectionNumber(sectionN);
						break;
						case "5":
							System.out.println("Enter new course location: ");
							String newLoc = input.nextLine();
							editCourse.setLocation(newLoc);
						break;
						case "6":
							System.out.println("Enter new course time: ");
							String newTime = input.nextLine();
							editCourse.setTime(newTime);
						break;
						case "7":
							System.out.println("--Back to Menu--");
							keepgoingEditCourse = false;
						break;
					}
				}
			}
		}
		
		
	}
	
    /**
     * Prints information of a given course.
     * @param allCourses
     */
	public void getCourseInfoByID(ArrayList<Course> allCourses) {
	   
		System.out.println("--Course Information (by ID)--");
		System.out.println("");

		System.out.println("Please enter Course ID:");
		String courseID = input.nextLine();
		
		System.out.println("Please enter Course Section Number:");
		while(!input.hasNextInt()) {
			System.out.println("Please enter an integer: ");
		    input.next();
		}
		int sectionNum = input.nextInt();
		
		input.nextLine();
		
		for (int i=0; i < allCourses.size(); i++) {
			if ((courseID.equals(allCourses.get(i).getCourseID())) && (sectionNum == allCourses.get(i).getSectionNumber())) {
				allCourses.get(i).printCourseInformation();
			}
		}
	}

    /**
     * Creates a new student object and adds it to the system's student list.
     * @param allStudents
     */
	public void registerStudent(ArrayList<Student> allStudents) {
	    System.out.println("--Register a Student--");
		System.out.println("");
		boolean usernameUnique = true;
		
		System.out.println("Enter student username: ");
		String uName = input.nextLine();

		for(int i=0; i < allStudents.size(); i++) {
			if(uName.equals(allStudents.get(i).getUsername())) {
				usernameUnique = false;
			}
		}
		while(!usernameUnique) {
			System.out.println("This username is taken, try another username:  ");
			uName = input.nextLine();
		}
		
		System.out.println("Enter student password: ");
		String pWord = input.nextLine();
		
		System.out.println("Enter student first name: ");
		String fName = input.nextLine();
		
		System.out.println("Enter student last name: ");
		String lName = input.nextLine();
		
		//create new student object
		Student stu = new Student(uName, pWord, fName, lName);
		
		int stuID = 0;
		//iterate through all students array list
		for(int j=0; j < allStudents.size(); j++) {
			//if student id is same, increment stuID by 1 until unique
			if (allStudents.get(j).getStudentID() == stuID) {
				stuID += 1;
			}
		}
		//assign a unique StudentID to the new student object
		stu.setStudentID(stuID);

		//add student object variable to ArrayList<Student> allStudents 
		allStudents.add(stu);
	}
	
	//REPORTS MENU METHODS
    /**
     * Prints all courses in the system.
     * @param allCourses
     */
	public void viewAllCourses(ArrayList<Course> allCourses) {
		System.out.println("--View All Courses--");  
		System.out.println("");

		//iterate through array of all courses
		for(int i=0; i < allCourses.size(); i++) {
			//print info for each course
			System.out.print(allCourses.get(i).getCourseName());
			System.out.println("  (section # " + allCourses.get(i).getSectionNumber() + " )");
			System.out.println("	Course ID : " + allCourses.get(i).getCourseID());
			System.out.println("        Maximum # of Students: " + allCourses.get(i).getMaxNumOfStudents());
			System.out.println("        # of Enrolled Students: " + allCourses.get(i).getNumOfEnrolledStudents());
			System.out.println("                   ----Enrolled Students----");
			
			//iterate through array of all students in a course if there are any students
			if (allCourses.get(i).getListOfStudents().size() >= 1) {
				for (int j = 0; j < allCourses.get(i).getListOfStudents().size(); j++) {
					//for each student object print last name, first name (user name)
					Student stu = allCourses.get(i).getListOfStudents().get(j);
					System.out.println("                       " + stu.getLastName() + "  ,  " + stu.getFirstName() + "   --- #" + stu.getStudentID() + " ---" );
					System.out.println("");
				}
			}
			else {
				System.out.println("				NO STUDENTS");
			}
			System.out.println("");
		}
	}
	
    /**
     * Prints all full courses in the system.
     * @param allCourses
     */
	public void viewAllFullCourses(ArrayList<Course> allCourses) {
		System.out.println("--View All Full Courses--");
		System.out.println("");

		//iterate through array of all courses
		for(int i=0; i < allCourses.size(); i++) {
			//print course name if course is full
			if (allCourses.get(i).getMaxNumOfStudents() == allCourses.get(i).getNumOfEnrolledStudents()) {
				System.out.println(allCourses.get(i).getCourseName());
			}
		}
	}

    /**
     * Writes all full courses into a CSV file.
     * @param allCourses
     * @throws FileNotFoundException
     */
	public void downloadFullCourses(ArrayList<Course> allCourses) throws FileNotFoundException {
		System.out.println("--Write Full Course List to a File--");
		System.out.println("");
		
		//create Print writer for CSV file
		PrintWriter writer = new PrintWriter("University_Full_Courses.csv");
		//iterate through array of all courses
		for(int i=0; i < allCourses.size(); i++) {
			//print to CSV file if course is full
			if (allCourses.get(i).getMaxNumOfStudents() == allCourses.get(i).getNumOfEnrolledStudents()) {
				writer.println(allCourses.get(i).getCourseName());
			}
		}
		System.out.println("Full Courses List downloaded under file name: 'University_Full_Courses.csv'");
		writer.close();	
	}

    /**
     * Prints all students in a given course.
     * @param allCourses
     */
	public void viewStudentList(ArrayList<Course> allCourses) {
		System.out.println("--View Student List in a Course--");
		System.out.println("");

		System.out.println("Enter Course ID: ");
	    String courseID = input.nextLine();
	    
		System.out.println("Enter Course Section Number: ");
	    int sectionNum = input.nextInt();
	    
	    for (int i = 0; i < allCourses.size(); i ++) {
	    	//find course object from course id
	    	if ((courseID.equals(allCourses.get(i).getCourseID())) && (sectionNum == allCourses.get(i).getSectionNumber())) {
	    		//get list of students array list from course object
	    		ArrayList<Student> listOfStu = allCourses.get(i).getListOfStudents();
	    		
			    //iterate through student array list of course object
	    		for (int j = 0; j < listOfStu.size(); j ++) {
			    	//print last name, first name of each student object
	    			System.out.println(listOfStu.get(j).getLastName() + "   ,   " + listOfStu.get(j).getFirstName());
			    }
	    	}
	    }
	}

    /**
     * Prints all courses a student is registered in.
     * @param allStudents
     */
	public void viewStudentCourses(ArrayList<Student> allStudents) {
	    System.out.println("--View Course List of a Student--");
		System.out.println("");

		System.out.println("Please press ENTER to continue.");
		input.nextLine();
		
		System.out.println("Enter student's first name: ");
	    String fName = input.nextLine();

		System.out.println("Enter student's last name: ");
	    String lName = input.nextLine();

	    for(int i=0; i < allStudents.size(); i++) {
	    	if ((fName.equals(allStudents.get(i).getFirstName()) && (lName.equals(allStudents.get(i).getLastName())))) {
	    		ArrayList<Course> stuCourseList = allStudents.get(i).getCourseList();
	    		for (int j=0; j < stuCourseList.size(); j++) {
	    			System.out.println(stuCourseList.get(j).getCourseName());
	    		}
	    	}
	    }
	}

    /**
     * Prints all courses sorted by number of enrolled students.
     * @param allCourses
     */
	public void sortCourses(ArrayList<Course> allCourses) {
	    System.out.println("--Sort Courses (based on registered student number)--");
		System.out.println("");
		//store a copy of all courses
		ArrayList<Course> allCoursesCopy = allCourses;
		//store sorted Courses to a new array list
		ArrayList<Course> sortedCourseList = new ArrayList<Course>();
		
		//iterate through each course on duplicated list of all courses
		for(int i = 0; i < allCoursesCopy.size(); i++) {
			//assign temporary course object
			Course tempCourse = allCoursesCopy.get(i);
			//iterate through all courses second time
			for(int j=0; j < allCoursesCopy.size(); j++) {
				//if tempCourse student num is smaller than that of any other courses, assign tempCourse to be the larger
				if (tempCourse.getNumOfEnrolledStudents() < allCoursesCopy.get(j).getNumOfEnrolledStudents()) {
					tempCourse = allCoursesCopy.get(j);
				}
			}
			//add tempCourse to new sorted array list
			sortedCourseList.add(tempCourse);
			//delete the added course from duplicate array list to avoid repetition
			allCoursesCopy.remove(tempCourse);
		}
		
		//print sorted course list with course name and number of enrolled students
		for (int i=0; i < sortedCourseList.size(); i++) {
			System.out.println(sortedCourseList.get(i).getCourseName());
			System.out.println(sortedCourseList.get(i).getNumOfEnrolledStudents());
		}
	}
}