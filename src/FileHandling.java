import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class FileHandling {
	
	private StudentListContainer container;
	private EncryptDecrypt ectdct;
	
	public FileHandling() {
		
		container = StudentListContainer.getInstance();
		ectdct = new EncryptDecrypt();
		
	}
	
	public void retrieveAllStudInfo() { // RETRIEVE FROM DB2.txt
		
		ArrayList<StudentDetails> studentList = container.getStudentList();
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader("DATABASE/1/DB2.txt"));
			String line;
			while((line = reader.readLine()) != null) {
				
				String[] components = line.split("<--->");
				
				if (components.length >= 15) {
	                int studYear = Integer.parseInt(components[0]);
	                int studSem = Integer.parseInt(components[1]);
	                String lName = components[2];
	                String fName = components[3];
	                String mName = components[4];
	                String studNum = components[5];
	                String studPass = components[6];
	                String scholStatus = components[7];
	                char studSex = components[8].charAt(0);
	                String studDOB = components[9];
	                String studEmail = components[10];
	                String studPhoneNum = components[11];
	                String studAddress = components[12];
	                String guardianEmail = components[13];
	                String guardianPhoneNum = components[14];

	                StudentDetails student = new StudentDetails(studYear, studSem, fName, mName, lName, studNum,
	                        studPass, scholStatus, studSex, studDOB, studAddress, studEmail, studPhoneNum,
	                        guardianEmail, guardianPhoneNum);
	                studentList.add(student);
	            } else {
	                System.out.println("Invalid data format in line: " + line);
	            }	
				
			}
			reader.close();
			
			ectdct.decryptAllStud(); // DECRYPT FIRST
			container.sortStudList(); // BEFORE SORTING
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public void retrieveIndivStudInfo() { // RETRIEVE FROM STUDENT INDIVIDUAL DATABASE
		
		ArrayList <StudentDetails> studentList = container.getStudentList();	
    	CourseDetails currCourse, allCourse;
    	  	
    	String hold1, hold2;
    	hold1 = hold2 = studentList.get(container.getMarker()).getStudNum();
    	
    	char[] decoder1, decoder2;
    	decoder1 = hold1.toCharArray();
    	decoder2 = hold2.toCharArray();
    	
    	
    	for(int i = 0; i < decoder1.length; i++) {
    		
    		if(i < 4)
    			decoder1[i] += 5;
    		else if(i >= 4 && i < 10)
    			decoder1[i] += 17;
    		
    	}	
    	for(int i = 0; i < decoder2.length; i++) {
    		
    		if(i < 4)
    			decoder2[i] += 5;
    		else if(i >= 4 && i < 10)
    			decoder2[i] += 17;
    		
    	}
    	
    	hold1 = new String(decoder1);
    	hold2 = new String(decoder2);
    	
    	hold1 = "DATABASE/2/" + hold1 + ".txt";
    	hold2 = "DATABASE/2/" + hold2 + "1" +".txt";
    	
    	try {
    		
			BufferedReader reader = new BufferedReader(new FileReader(hold1));
			String line;
			while((line = reader.readLine()) != null) {
				
				String[] components = line.split("<--->");
				
				String courseCode = components[0];
				int courseUnits = Integer.parseInt(components[1]);
				double courseGrd = Double.parseDouble(components[2]);
				String courseFaculty = components[3];
				String courseSched = components[4];
				
				allCourse = new CourseDetails(courseCode, courseSched, courseFaculty, 
												courseUnits, courseGrd);
				studentList.get(container.getMarker()).getAllCourseList().add(allCourse);			
			}
			reader.close();
			
    	} catch (IOException e) {
    		
			e.printStackTrace();
			
		}
    	
    	try {
    		
			BufferedReader reader = new BufferedReader(new FileReader(hold2));
			String line;
			while((line = reader.readLine()) != null) {
				
				String[] components = line.split("<--->");
				
				String courseCode = components[0];
				int courseUnits = Integer.parseInt(components[1]);
				double courseGrd = Double.parseDouble(components[2]);
				String courseFaculty = components[3];
				String courseSched = components[4];
				
				currCourse = new CourseDetails(courseCode, courseSched, courseFaculty, 
												courseUnits, courseGrd);
				studentList.get(container.getMarker()).getCurrentCourseList().add(currCourse);			
			}
			reader.close();
			
    	} catch (IOException e) {
    		
			e.printStackTrace();
			
		}
    	
    	ectdct.decryptIndivStud(); // DECRYPT
    	
	}
	
	public void saveAllStudInfo() { // SAVE TO DB2.txt

	    ArrayList<StudentDetails> studentList = container.getStudentList();   

	    try {
	    	
	        BufferedWriter writer = new BufferedWriter(new FileWriter("DATABASE/1/DB2.txt", false));
	        for (StudentDetails student : studentList) {
	            String studDet = student.getStudYear() + "<--->" + student.getStudSem() + "<--->" +
	                    student.getlName() + "<--->" + student.getfName() + "<--->" +
	                    student.getmName() + "<--->" + student.getStudNum() + "<--->" +
	                    student.getStudPass() + "<--->" + student.getScholStatus() + "<--->" +
	                    student.getStudSex() + "<--->" + student.getStudDOB() + "<--->" +
	                    student.getStudEmail() + "<--->" + student.getStudPhoneNum() + "<--->" +
	                    student.getStudAddress() + "<--->" + student.getGuardianEmail() + "<--->" +
	                    student.getGuardianPhoneNum();

	            writer.write(studDet);
	            writer.newLine();
	        }
	        
	        writer.close();
	        
	    } catch (IOException e) {
	    	
	        e.printStackTrace();
	        
	    }
		
	}
	
	public void saveIndivStudInfo() { // SAVE TO STUDENT INDIVIDUAL DATABASE (at DATABASE/2/...)
		
	    ArrayList<StudentDetails> studentList = container.getStudentList();
	    
	    String hold1, hold2;
	    hold1 = hold2 = studentList.get(container.getMarker()).getStudNum();

	    char[] decoder1, decoder2;
    	decoder1 = hold1.toCharArray();
    	decoder2 = hold2.toCharArray();
    	
    	
    	for(int i = 0; i < decoder1.length; i++) {
    		
    		if(i < 4)
    			decoder1[i] += 5;
    		else if(i >= 4 && i < 10)
    			decoder1[i] += 17;
    		
    	}	
    	for(int i = 0; i < decoder2.length; i++) {
    		
    		if(i < 4)
    			decoder2[i] += 5;
    		else if(i >= 4 && i < 10)
    			decoder2[i] += 17;
    		
    	}
    	
    	hold1 = new String(decoder1);
    	hold2 = new String(decoder2);
	    
	    hold1 = "DATABASE/2/" + hold1 + ".txt";
	    hold2 = "DATABASE/2/" + hold2 + "1" + ".txt";
	    
	    ectdct.encryptIndivStud();
	    
	    //SAVE ALL THE COURSES THE STUDENT HAS TAKEN
	    try {
	        File directory = new File("DATABASE/2/");
	        directory.mkdirs(); // Create the directories if they don't exist

	        BufferedWriter writer = new BufferedWriter(new FileWriter(hold1, false));
	        for (CourseDetails course : studentList.get(container.getMarker()).getAllCourseList()) 
	        {
	            String crs = course.getCourseCode() + "<--->" + course.getCourseUnits() + "<--->" +
	                    course.getCourseGrade() + "<--->" + course.getCourseFaculty() + "<--->" + course.getCourseSched();

	            writer.write(crs);
	            writer.newLine();
	        }
	        writer.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	    //SAVE THE CURRENT COURSES THE STUDENT IS TAKING 
	    try {
	        BufferedWriter writer = new BufferedWriter(new FileWriter(hold2, false));
	        for (CourseDetails course : studentList.get(container.getMarker()).getCurrentCourseList()) {
	            String crs = course.getCourseCode() + "<--->" + course.getCourseUnits() + "<--->" +
	                    course.getCourseGrade() + "<--->" + course.getCourseFaculty() + "<--->" + course.getCourseSched();
	            writer.write(crs);
	            writer.newLine();
	        }
	        writer.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
	}
	
	public void saveStudSched () { // SAVE STUDENT'S CURRENT SCHEDULE AT COMPUTER'S DESKTOP
		
		ArrayList<StudentDetails> studentList = container.getStudentList();
		
		String desktopPath = System.getProperty("user.home") + "/Desktop";
		String fileName = studentList.get(container.getMarker()).getlName() + "_" + 
							studentList.get(container.getMarker()).getStudYear() + "-" +
								studentList.get(container.getMarker()).getStudSem() + ".txt";
		
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(desktopPath + "/" + fileName));
			
			String name = studentList.get(container.getMarker()).getlName() + ", " +
							studentList.get(container.getMarker()).getfName() + " " +
								studentList.get(container.getMarker()).getmName();
			String yearNSem = "YEAR: " + studentList.get(container.getMarker()).getStudYear() + " | " +
								"SEMESTER: " + studentList.get(container.getMarker()).getStudSem();
			
			writer.write(name); writer.newLine();
			writer.write(studentList.get(container.getMarker()).getStudNum()); writer.newLine(); writer.newLine();
			writer.write(yearNSem); writer.newLine();
			
			for (CourseDetails course : studentList.get(container.getMarker()).getCurrentCourseList()) {
	            String crs = course.getCourseCode() + " | " + course.getCourseUnits() + " | " + course.getCourseSched();
	            writer.write(crs);
	            writer.newLine();
	        }
			writer.close();
			
			JOptionPane.showMessageDialog(null, "Schedule saved at: " + desktopPath , 
											"SCHEDULE SAVED", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
}
