
public class CourseDetails {
	
	private String courseCode;
	private String courseSched;
	private String courseFaculty;
	private int courseUnits;
	private double courseGrade;
	
	public CourseDetails(String courseCode, String courseSched, String courseFaculty, 
							int courseUnits, double courseGrade) {
		this.courseCode = courseCode;
        this.courseSched = courseSched;
        this.courseFaculty = courseFaculty;
        this.courseUnits = courseUnits;
        this.courseGrade = courseGrade;
	}
	
	// Getter and setter methods for 'courseCode'
	public String getCourseCode() {
        return courseCode;
    }
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
   
	// Getter and setter methods for 'courseSched'
	public String getCourseSched() {
		return courseSched;
	}
	public void setCourseSched(String courseSched) {
		this.courseSched = courseSched;
	}

	// Getter and setter methods for 'courseFaculty'
	public String getCourseFaculty() {
		return courseFaculty;
	}
	public void setCourseFaculty(String courseFaculty) {
		this.courseFaculty = courseFaculty;
	}
    
	// Getter and setter methods for 'courseUnits'
    public int getCourseUnits() {
        return courseUnits;
    }
    public void setCourseUnits(int courseUnits) {
        this.courseUnits = courseUnits;
    }
    
    // Getter and setter methods for 'courseGrade'
    public double getCourseGrade() {
        return courseGrade;
    }
    public void setCourseGrade(double courseGrade) {
        this.courseGrade = courseGrade;
    }
}
