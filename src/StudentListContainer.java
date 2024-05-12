// Dependencies
import java.util.*;

public class StudentListContainer { // 'StudentListContainer' class
	
	// The 'instance' variable ensures that only one (1) instance
	// of the 'StudentListContainer' is present
	private static StudentListContainer instance1;
	
	// The 'studentList' is an 'ArrayList' that will store the list of students
    private ArrayList<StudentDetails> studentList;
    
    private int marker;
    
    // Private constructor that initializes the 'studentList' by creating
    // a new 'ArrayList' object
    private StudentListContainer() {
        studentList = new ArrayList<>();
        marker = -1;
    }
    
    // The getInstance() method ensures that only one instance of the
    // 'StudentListContainer' class is created
    public static synchronized StudentListContainer getInstance() {
        if (instance1 == null) { // If 'instance' is 'null...
        	// new instance of 'StudentListContainer' is created
        	// and assigned to 'instance'
            instance1 = new StudentListContainer();
        }
        // Else, an instance already exists
        
        // Return 'instance'
        return instance1;
    }
    
    // Sort studentList according to 'studNum'
    public void sortStudList() {
    	studentList.sort((s1, s2) -> s1.getstudNum().compareTo(s2.getstudNum()));
    }
    
    // A getter for the 'studentList'
    public ArrayList<StudentDetails> getStudentList() {
        return studentList;
    }
    
    // Getter and setter methods for 'marker'
    public int getMarker() {
    	return marker;
    }   
    public void setMarker(int marker) {
    	this.marker = marker;
    }
    
    // Remove duplicates
    public void removeDupCourses() {
    	
        ArrayList<CourseDetails> allCourses = instance1.getStudentList().get(marker).getAllCourseList();
        ArrayList<CourseDetails> uniqueCourses = new ArrayList<>();

        for (CourseDetails course : allCourses) {
            boolean isDuplicate = false;
            for (CourseDetails duplicate : uniqueCourses) {
                if (course.getCourseCode().equals(duplicate.getCourseCode())) {
                    isDuplicate = true;
                    if (course.getCourseGrade() < duplicate.getCourseGrade()) {
                        // Update the duplicate course with lower grade
                        duplicate.setCourseGrade(course.getCourseGrade());
                    }
                    break;
                }
            }
            if (!isDuplicate) {
                // Add the unique course to the new ArrayList
                uniqueCourses.add(course);
            }
        }

        // Update the original ArrayList with the unique courses
        allCourses.clear();
        allCourses.addAll(uniqueCourses);
    }
    
}
