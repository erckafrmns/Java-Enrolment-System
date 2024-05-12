// Dependencies
import java.util.*;

public class StudentDetails { // StudentDetails class
	
	// ACCOUNT DETAILS
	private int studYear;
	private int studSem;
	
	private String studNum; // Holds student number
	private String studPass; // Holds student password
	
	private String scholStatus;

	// PERSONAL DETAILS
	private String fName;
	private String mName;
	private String lName;
	
	private char studSex;
	private String studDOB;
	private String studAddress;
	
	private String studEmail;
	private String studPhoneNum;
	
	private String guardianEmail;
	private String guardianPhoneNum;
	
	// COURSE DETAILS
	private ArrayList<CourseDetails> allCourseList;
	private ArrayList<CourseDetails> currentCourseList;
	
	// Constructor for the 'StudentDetails' class
	public StudentDetails(int studYear, int studSem, 
							String fName, String mName, String lName,
								String studNum, String studPass, String scholStatus,
									char studSex, String studDOB, String studAddress,
										String studEmail, String studPhoneNum, 
											String guardianEmail, String guardianPhoneNum) {
		this.studYear = studYear;
		this.studSem = studSem;
		this.fName = fName;
		this.mName = mName;
		this.lName = lName;
		this.studNum = studNum;
        this.studPass = studPass;
        this.scholStatus = scholStatus;
        this.studSex = studSex;
        this.studDOB = studDOB;
        this.studAddress = studAddress;
        this.studEmail = studEmail;
        this.studPhoneNum = studPhoneNum;
        this.guardianEmail = guardianEmail;
        this.guardianPhoneNum = guardianPhoneNum;
        
        this.allCourseList = new ArrayList<>();
        this.currentCourseList = new ArrayList<>();
	}
	
	// Getter and setter methods for 'studNum'
	public String getStudNum() {
		return studNum;
	}
	public void setStudNum(String studNum) {
		this.studNum = studNum;
	}

	// Getter and setter methods for 'studPass'
	public String getStudPass() {
		return studPass;
	}
	public void setStudPass(String studPass) {
		this.studPass = studPass;
	}
	
	// Getter and setter methods for studYear
	public int getStudYear() {
		return studYear;
	}
	public void setStudYear(int studYear) {
		this.studYear = studYear;
	}
	
	// Getter and setter methods for studSem
	public int getStudSem() {
		return studSem;
	}
	public void setStudSem(int studSem) {
		this.studSem = studSem;
	}
	
	// Getter and setter methods for Student Name
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	
	// Getter and setter methods for 'studNum'
	public String getstudNum() {
        return studNum;
    }
    public void setstudNum(String studNum) {
        this.studNum = studNum;
    }
	
    // Getter and setter methods for 'studPass'
    public String getstudPass() {
        return studPass;
    }
    public void setstudPass(String studPass) {
        this.studPass = studPass;
    }
    
    //Getter and setter method for scholStatus
	public String getScholStatus() {
		return scholStatus;
	}
	public void setScholStatus(String scholStatus) {
		this.scholStatus = scholStatus;
	}
	
	// Getter and setter methods for 'studSex'
	public char getStudSex() {
		return studSex;
	}
	public void setStudSex(char studSex) {
		this.studSex = studSex;
	}

	// Getter and setter methods for 'studDOB'
	public String getStudDOB() {
		return studDOB;
	}
	public void setStudDOB(String studDOB) {
		this.studDOB = studDOB;
	}

	// Getter and setter methods for 'studAddress'
	public String getStudAddress() {
		return studAddress;
	}
	public void setStudAddress(String studAddress) {
		this.studAddress = studAddress;
	}

	// Getter and setter methods for 'studEmail'
	public String getStudEmail() {
		return studEmail;
	}
	public void setStudEmail(String studEmail) {
		this.studEmail = studEmail;
	}

	// Getter and setter methods for 'studPhoneNum'
	public String getStudPhoneNum() {
		return studPhoneNum;
	}
	public void setStudPhoneNum(String studPhoneNum) {
		this.studPhoneNum = studPhoneNum;
	}

	// Getter and setter methods for 'guardianEmail'
	public String getGuardianEmail() {
		return guardianEmail;
	}
	public void setGuardianEmail(String guardianEmail) {
		this.guardianEmail = guardianEmail;
	}
	
	// Getter and setter methods for 'guardianPhoneNum'
	public String getGuardianPhoneNum() {
		return guardianPhoneNum;
	}
	public void setGuardianPhoneNum(String guardianPhoneNum) {
		this.guardianPhoneNum = guardianPhoneNum;
	}
    
    // Getter and setter methods for 'allCourseList'
    public ArrayList<CourseDetails> getAllCourseList() {
        return allCourseList;
    }
    public void setAllCourseList(ArrayList<CourseDetails> allCourseList) {
        this.allCourseList = allCourseList;
    }
    
    // Getter and setter methods for 'currentCourseList'
    public ArrayList<CourseDetails> getCurrentCourseList() {
        return currentCourseList;
    }
    public void setCurrentCourseList(ArrayList<CourseDetails> currentCourseList) {
        this.currentCourseList = currentCourseList;
    }
    
}
