import java.util.*;

public class EncryptDecrypt {

	private static final int KEY = 3;
	private static StudentListContainer container;
	
	public EncryptDecrypt()
	{
		
		container = StudentListContainer.getInstance();
		
	}
	
//-----------------------------ENCRYPTION-----------------------------
	
	public void encryptAllStud() {

        ArrayList<StudentDetails> studentList = container.getStudentList();

        for (StudentDetails student : studentList) {
        	
        	// Encrypt student details
        	String studNum = encryptStr(student.getStudNum());
        	String studPass = encryptStr(student.getStudPass());
        	String status = encryptStr(student.getScholStatus());
        	int studYear = encryptInt1(student.getStudYear());
        	int studSem = encryptInt1(student.getStudSem());
        	String lName = encryptStr(student.getlName());
            String fName = encryptStr(student.getfName());
            String mName = encryptStr(student.getmName());
            char sex = encryptChar(student.getStudSex());
            String birthdate = encryptStr(student.getStudDOB());
            String phoneNumber = encryptStr(student.getStudPhoneNum());
            String email = encryptStr(student.getStudEmail());
            String address = encryptStr(student.getStudAddress());
            String guardianEmail = encryptStr(student.getGuardianEmail());
            String guardianPhoneNumber = encryptStr(student.getGuardianPhoneNum());
         
            // Set encrypted values back to student object
            student.setStudNum(studNum);
            student.setStudPass(studPass);
            student.setScholStatus(status);
            student.setStudYear(studYear);
            student.setStudSem(studSem);
            student.setlName(lName);
            student.setfName(fName);
            student.setmName(mName);
            student.setStudSex(sex);
            student.setStudDOB(birthdate);
            student.setStudPhoneNum(phoneNumber);
            student.setStudEmail(email);
            student.setStudAddress(address);
            student.setGuardianEmail(guardianEmail);
            student.setGuardianPhoneNum(guardianPhoneNumber);

        }
        
    }

    public void encryptIndivStud() {
    	
    	ArrayList<StudentDetails> studentList = container.getStudentList();
        ArrayList<CourseDetails> allCourseList = studentList.get(container.getMarker()).getAllCourseList();
        ArrayList<CourseDetails> currentCourseList = new ArrayList<>(studentList.get(container.getMarker()).getCurrentCourseList());
        
        // Encrypt allCourseList
        for (CourseDetails allCourse : allCourseList) {
        	
            String courseCode = encryptStr(allCourse.getCourseCode());
            String courseSched = encryptStr(allCourse.getCourseSched());
            String courseFac = encryptStr(allCourse.getCourseFaculty());
            int courseUnit = encryptInt1(allCourse.getCourseUnits());
            double courseGrd = encryptDouble1(allCourse.getCourseGrade());

            // Set encrypted values back to course object
            allCourse.setCourseCode(courseCode);
            allCourse.setCourseFaculty(courseFac);
            allCourse.setCourseSched(courseSched);
            allCourse.setCourseUnits(courseUnit);
            allCourse.setCourseGrade(courseGrd);
        }
        
        for (CourseDetails currCourse : currentCourseList) {
    		
    		String courseCode = encryptStr(currCourse.getCourseCode());
    		String courseSched = encryptStr(currCourse.getCourseSched());
    		String courseFac = encryptStr(currCourse.getCourseFaculty());
    		int courseUnit = encryptInt1(currCourse.getCourseUnits());
    		double courseGrd = encryptDouble1(currCourse.getCourseGrade());
    		
    		// Set encrypted values back to student object
    		currCourse.setCourseCode(courseCode);
    		currCourse.setCourseFaculty(courseFac);
    		currCourse.setCourseSched(courseSched);
    		currCourse.setCourseUnits(courseUnit);
    		currCourse.setCourseGrade(courseGrd);
        	
    		
    	}

 	   
     }     
     
//-----------------------------DECRYPTION-----------------------------

    public void decryptAllStud() {
		
        ArrayList<StudentDetails> studentList = container.getStudentList();
        
        for (StudentDetails student : studentList) {
            
        	// DECRYPT STUDENT DETAILS
        	String studNum = decryptStr(student.getStudNum());
        	String studPass = decryptStr(student.getStudPass());
        	String status = decryptStr(student.getScholStatus());
        	int studYear = decryptInt1(student.getStudYear());
        	int studSem = decryptInt1(student.getStudSem());
        	String lName = decryptStr(student.getlName());
            String fName = decryptStr(student.getfName());
            String mName = decryptStr(student.getmName());
            char sex = decryptChar(student.getStudSex());
            String birthdate = decryptStr(student.getStudDOB());
            String phoneNumber = decryptStr(student.getStudPhoneNum());
            String email = decryptStr(student.getStudEmail());
            String address = decryptStr(student.getStudAddress());
            String guardianEmail = decryptStr(student.getGuardianEmail());
            String guardianPhoneNumber = decryptStr(student.getGuardianPhoneNum());
                        
            // Set decrypted values back to student object
            student.setStudNum(studNum);
            student.setStudPass(studPass);
            student.setScholStatus(status);
            student.setStudYear(studYear);
            student.setStudSem(studSem);
            student.setlName(lName);
            student.setfName(fName);
            student.setmName(mName);
            student.setStudSex(sex);
            student.setStudDOB(birthdate);
            student.setStudPhoneNum(phoneNumber);
            student.setStudEmail(email);
            student.setStudAddress(address);
            student.setGuardianEmail(guardianEmail);
            student.setGuardianPhoneNum(guardianPhoneNumber);
           
        }
   
    }
	
	public void decryptIndivStud() {
    	
		ArrayList<StudentDetails> studentList = container.getStudentList();
    	ArrayList<CourseDetails> allCourseList = studentList.get(container.getMarker()).getAllCourseList();
    	ArrayList<CourseDetails> currentCourseList = studentList.get(container.getMarker()).getCurrentCourseList();
    	
    	for (CourseDetails allCourse : allCourseList) {
    		
    		String courseCode = decryptStr(allCourse.getCourseCode());
    		String courseSched = decryptStr(allCourse.getCourseSched());
    		String courseFac = decryptStr(allCourse.getCourseFaculty());
    		int courseUnit = decryptInt1(allCourse.getCourseUnits());
    		double courseGrd = decryptDouble1(allCourse.getCourseGrade());
    		
    		// Set encrypted values back to student object
    		allCourse.setCourseCode(courseCode);
    		allCourse.setCourseFaculty(courseFac);
    		allCourse.setCourseSched(courseSched);
    		allCourse.setCourseUnits(courseUnit);
    		allCourse.setCourseGrade(courseGrd);
    		
    	}
    	
    	for (CourseDetails currCourse : currentCourseList) {
    		
    		String courseCode = decryptStr(currCourse.getCourseCode());
    		String courseSched = decryptStr(currCourse.getCourseSched());
    		String courseFac = decryptStr(currCourse.getCourseFaculty());
    		int courseUnit = decryptInt1(currCourse.getCourseUnits());
    		double courseGrd = decryptDouble1(currCourse.getCourseGrade());
    		
    		// Set encrypted values back to student object
    		currCourse.setCourseCode(courseCode);
    		currCourse.setCourseFaculty(courseFac);
    		currCourse.setCourseSched(courseSched);
    		currCourse.setCourseUnits(courseUnit);
    		currCourse.setCourseGrade(courseGrd);
    		
    	}
	   
    }	
	
//-----------------------ENCRYPT----------------------
	
	private static int encryptInt1(int data) {
		
		return data + KEY;
	 }
	
	private static double encryptDouble1(double data) {
		
		return data + KEY;
	 }
	 
	private static String encryptStr(String data) {

		
		 StringBuilder encryptedData = new StringBuilder();
		 for (char c : data.toCharArray()) {
			 char base = Character.isLowerCase(c) ? 'a' : 'A';
			 encryptedData.append((char) (((c - base + KEY) % 256) + base));
		 }
		 
	     
		 return encryptedData.toString();
		
	 }
	 
	private static char encryptChar(char data) {
		 
		 char base = Character.isLowerCase(data) ? 'a' : 'A';
		 return (char) (((data - base + KEY) % 26) + base);
	 }
	
//------------------------DECRYPT------------------------

	private static int decryptInt1(int data) {
		 
			return data - KEY;
	}
	
	private static double decryptDouble1(double data) {
		
			return data - KEY;
	}

	private static String decryptStr(String data) {
		 
		 StringBuilder decryptedData = new StringBuilder();
		 for (char c : data.toCharArray()) {
			 char base = Character.isLowerCase(c) ? 'a' : 'A';
			 decryptedData.append((char) (((c - base - KEY) % 256) + base));
		 }
		 
		 return decryptedData.toString();
	 }
	 
	private static char decryptChar(char data) {
		 
		 char base = Character.isLowerCase(data) ? 'a' : 'A';
		 return (char) (((data - base - KEY + 26) % 26) + base);
	 }

}
	
