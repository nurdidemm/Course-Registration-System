import java.util.Scanner;
import java.io.*;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class CourseRegistrationSystem {
	
	private static ArrayList<Course> allCourses = new ArrayList<Course>();
	private static ArrayList<Course> fullCourses = new ArrayList<Course>();
	private static ArrayList<Student> allStudents = new ArrayList<Student>();
	private static Student loggedStudent;

	public static void main(String[] args) throws FileNotFoundException {
				
		//DE-SERIALIZATION
		try{
			FileInputStream fis1 = new FileInputStream("All_Courses.ser");
		    ObjectInputStream ois1 = new ObjectInputStream(fis1); 
		    allCourses = (ArrayList<Course>) ois1.readObject();
		   	
		    FileInputStream fis2 = new FileInputStream("All_Students.ser");
	     	ObjectInputStream ois2 = new ObjectInputStream(fis2);
		    allStudents = (ArrayList<Student>) ois2.readObject();
		    ois1.close();
		    fis1.close();
		    ois2.close();
		    fis2.close();
		    }
		catch(Exception e){
			//read CSV file
			addCoursesToSystem();
		}
		
		Admin admin = new Admin("Admin", "Admin001", "Admin", "Adminson");
								
		Scanner input = new Scanner(System.in);

		System.out.println("Admin or Student?");
		String response = input.nextLine();
		
		if (response.toLowerCase().equals("admin")) {
			boolean keepgoing = true;

			while (keepgoing == true) {
				System.out.println("Please enter your login information.");
				System.out.println("Username: ");
				String username = input.nextLine();
				System.out.println("Password: ");
				String password = input.nextLine();
				//check if Admin login info is correct
				if (username.equals("Admin") && password.equals("Admin001")) {
					keepgoing = false;		//exit loop
				}
				else {
					System.out.println("");
					System.out.println("Wrong username or password");
					keepgoing = true;
				}
			}
			
			System.out.println("");
			System.out.println("");
			
			//loop to keep Admin Menu running
			boolean keepgoingAdminMenu = true;
			while (keepgoingAdminMenu == true) {
				//Admin Menu
				System.out.println("1.Course Management");
				System.out.println("2.Reports");
				System.out.println("3.Exit");

				String adminResponse = input.nextLine();
					
				switch (adminResponse) {
					//COURSE MANAGEMENT
					case "1":
						//loop to keep Course Management Menu running
						boolean keepgoingManagement = true;
							while (keepgoingManagement == true) {
							//COURSE MANAGEMENT MENU
							System.out.println("");
							System.out.println("COURSE MANAGEMENT");
							System.out.println("");
							System.out.println("1.Create a new course");
							System.out.println("2.Delete a course");
							System.out.println("3.Edit a course");
							System.out.println("4.Display information for a given course (by course ID)");
							System.out.println("5.Register a student");
							System.out.println("6.Back to Main Menu");
									
							adminResponse = input.nextLine();
								
							switch (adminResponse) {
								//Create a Course
								case "1": 
									admin.createCourse(allCourses, fullCourses);
								break;
								//Delete a Course
								case "2":
									//remove course from all university course list
									admin.deleteCourse(allCourses, fullCourses);
								break;
								//Edit a Course
								case "3":
								    admin.editCourse(allCourses, fullCourses);
								break;
								//Course Information by ID
								case "4":
									admin.getCourseInfoByID(allCourses);
								break;
								//Register a Student
								case "5":
									admin.registerStudent(allStudents);
								break;
								//EXIT
								case "6":
								    System.out.println("--Back to Main Menu--");
								    //break while loop to return to Admin Menu
								    keepgoingManagement = false;
								break;
								}
						}
					break;
					//REPORTS
					case "2":
						//loop to keep Reports Menu running
						boolean keepgoingReportsMenu = true;
						while (keepgoingReportsMenu == true) {
							//REPORTS MENU
							System.out.println("");
							System.out.println("REPORTS");
							System.out.println("");
							System.out.println("1.View all courses");
							System.out.println("2.View all courses that are FULL");
							System.out.println("3.Write to a file the list of course that are full");
							System.out.println("4.View the names of the students that are registered in a specific course");
							System.out.println("5.View the list of courses that a given student is registered in");
							System.out.println("6. Sort the courses based on the current number of students registered");
							System.out.println("7. Back to Main Menu");
					
							adminResponse = input.nextLine();
							
							switch (adminResponse) {
								//View All Courses
								case "1":
									admin.viewAllCourses(allCourses);
								break;
								//View All Full Courses
								case "2":
									admin.viewAllFullCourses(allCourses);
								break;
								//Write Full Course List to a File
								case "3":
								    admin.downloadFullCourses(allCourses);
								break;
								//View Student List in a Course
								case "4":
								    admin.viewStudentList(allCourses);
								break;
								//View Course List of a Student
								case "5":
									admin.viewStudentCourses(allStudents);
								break;
								//Sort Courses (based on registered student number)
								case "6":
									admin.sortCourses(allCourses);
								break;
								//EXIT
								case "7":
								    System.out.println("--Back to Main Menu--");
								    keepgoingReportsMenu = false;
								break;
							}
						}
					break;
					case "3": 
						System.out.println("--Exit--");
						keepgoingAdminMenu = false;
					break;
				}
			}
	}
		else if (response.toLowerCase().equals("student")) {
			boolean keepgoing = true;
			while (keepgoing == true) {
				System.out.println("Please enter your login information:");
				System.out.println("Username: ");
				String username = input.nextLine();
				System.out.println("Password: ");
				String password = input.nextLine();
				
				//for loop to check all student objects to see if user name and password matches
				for (int i = 0; i < allStudents.size(); i++) {
					Student stu = allStudents.get(i);
					if (stu.getUsername().equals(username)) {
						if (stu.getPassword().equals(password)) {
							//assign loggedStudent to the student object if a match is found
							loggedStudent = stu;
							keepgoing = false;		//exit loop
							System.out.print("...Login Successful...");
							break;
						}
						else {
							System.out.println("Wrong username or password.");
							keepgoing = true;
						}
					}	
				}
			}
			
			System.out.println("");
			System.out.println("");

			boolean keepgoingStudentMenu = true;
			while (keepgoingStudentMenu == true) {
				//Student Menu
				System.out.println("Course Management");
				System.out.println("");
	
				System.out.println("1.View all courses");			
				System.out.println("2.View all open courses");
				System.out.println("3.Register in a course");
				System.out.println("4.Withdraw from a course");
				System.out.println("5.View my courses");
				System.out.println("6.Exit");
				
				String studentResponse = input.nextLine();
				
				switch (studentResponse) {
					//View All Courses
					case "1":
						loggedStudent.viewAllCourses(allCourses);
					break;
					//View All Open Courses
					case "2":
						loggedStudent.viewAllOpenCourses(allCourses);
					break;
					//Register to a Course
					case "3":
						loggedStudent.registerToCourse(allCourses, allStudents);
					break;
					//Withdraw from a Course
					case "4":
						loggedStudent.withdrawFromCourse(allCourses);
					break;
					//View My Courses
					case "5":
						loggedStudent.viewMyCourses();
					break;
					//EXIT
					case "6":
						System.out.println("--Exit--");
						keepgoingStudentMenu = false;		//exit loop
					break;
				}
			}
		}
		else {
			System.out.println("You have to be an admin or a student to use this system.");
		}
		
		//SERIALIZATION
	    try{
	    	FileOutputStream fos1= new FileOutputStream("All_Courses.ser");
	    	ObjectOutputStream oos1= new ObjectOutputStream(fos1);
	    	oos1.writeObject(allCourses);
	        oos1.close();
	        fos1.close();
	    }
		catch(IOException ioe){
	        ioe.printStackTrace();
	    }

		try {
			FileOutputStream fos2= new FileOutputStream("All_Students.ser");
	    	ObjectOutputStream oos2= new ObjectOutputStream(fos2);
	    	oos2.writeObject(allStudents);
	        oos2.close();
	        fos2.close();
		}
		catch(IOException ioe){
	        ioe.printStackTrace();
	    }
	input.close();
	}
	
    /**
     * Reads and adds courses from CSV file to the Course Registration System
     */
	public static void addCoursesToSystem() throws FileNotFoundException {
		Scanner inputFile = new Scanner(new File("MyUniversityCourses.csv"));
		inputFile.nextLine();
		
		while(inputFile.hasNextLine()) {
			String line = inputFile.nextLine();
			String[] course = line.split(",");
			ArrayList<Student> studentList = new ArrayList<Student>();
		
			//assign each element of course array to appropriate data types
			String courseName = course[0];
			String courseId = course[1];
			int maxStudents = Integer.parseInt(course[2]);
			int currentStudents = Integer.parseInt(course[3]);
			String courseInstructor = course[5];
			int sectionNumber = Integer.parseInt(course[6]);
			String courseLocation = course[7];
			
			//create Course objects and add them to the allCourses array
			allCourses.add(new Course(courseName, courseId, maxStudents, currentStudents, studentList, courseInstructor, sectionNumber, courseLocation, ""));
		}
		inputFile.close();
	}
	
}