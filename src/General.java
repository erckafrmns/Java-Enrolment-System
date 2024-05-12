// Dependencies
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.*;
import java.util.*;

public class General implements ActionListener {
	
	private StudentListContainer container;
	private JFrame frame;
	
	private ArrayList<StudentDetails> studentList;
	
	/**** GENERAL USAGE ****/
	// Colors
	private Color MICSColor1 = new Color(126, 2, 2);
	private Color MICSColor2 = new Color(241, 234, 213);
	// Fonts
	private Font font1 = new Font("Montserrat Medium", Font.BOLD, 23);
	private Font font2 = new Font("Montserrat Medium", Font.PLAIN, 17);
	private Font font3 = new Font("Montserrat Medium", Font.PLAIN, 15);
	private Font font4 = new Font("Bahnschrift", Font.BOLD, 23);
	
	public General() {
		
        container = StudentListContainer.getInstance();
        studentList = container.getStudentList();
        
    }
	
	public void locate(String studNum) {
		
		container.setMarker(-1);
		
		for(int i = 0; i < studentList.size(); i++) {
    		if (studentList.get(i).getstudNum().equals(studNum)) {
	            container.setMarker(i);
	            break;
    	    }
    	}	
		
	}
	
	public void displayRecords(Runnable callback) {
		
		frame = new JFrame();
		JPanel coursesPanel = new JPanel(null);
		
		JLabel titleLabel = new JLabel("STUDENT PORTAL");
		JLabel studNameLabel = new JLabel("Student Name: ");
		JLabel studNumLabel = new JLabel("Student Number: ");
		JLabel studStatLabel = new JLabel("Scholastic Status: ");
		JLabel studYearLabel = new JLabel("Year: ");
		JLabel studSemLabel = new JLabel("Semester: ");
	
		JLabel studNameField;
		JLabel studNumField;
		JLabel studStatField;
		JLabel studYearField;
		JLabel studSemField;
		
		JButton continueButton = new JButton("CONTINUE");
		
		// TABLE HEADERS
		JLabel courseCodeLabel = new JLabel("COURSE CODE");
		JLabel courseNameLabel = new JLabel("DESCRIPTION");
		JLabel facultyLabel = new JLabel("FACULTY NAME");
		JLabel courseUnitsLabel = new JLabel("UNITS");
		JLabel courseGradeLabel = new JLabel("GRADE");
		JLabel statusLabel = new JLabel("GRADE STATUS");
		
		// GENERAL USAGE
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		Color TUPColor1 = new Color(126, 2, 2);
		Font font1 = new Font("Arial", Font.BOLD, 20); 
		
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	    frame.addWindowListener(new WindowAdapter() {
	    	
	        @Override
	        public void windowClosing(WindowEvent e) {
	        	
	            frame.dispose();
	            
	        }
	        
	    });   
        frame.add(coursesPanel);
	    frame.setSize(1350, 700); // Set frame size
        
        //coursesPanel.setLayout(null);
        
        // PORTAL TITLE ENTRY
        titleLabel.setFont(font1);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(120, 0, 1100, 90);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBackground(TUPColor1);
        titleLabel.setOpaque(true);
        coursesPanel.add(titleLabel);
        
        // STUDENT NAME ENTRY
        String fullName = studentList.get(container.getMarker()).getfName() + " " +
        					studentList.get(container.getMarker()).getmName() + " " +
        						studentList.get(container.getMarker()).getlName();
        
        studNameLabel.setBounds(25, 100, 100, 25);
        coursesPanel.add(studNameLabel);       
        studNameField = new JLabel(fullName);
        studNameField.setBounds(130, 100, 300, 25);
        studNameField.setBackground(Color.white);
        studNameField.setOpaque(true);
        coursesPanel.add(studNameField);
        
        // STUDENT NUMBER ENTRY
        studNumLabel.setBounds(25, 130, 100, 25);
        coursesPanel.add(studNumLabel);
        studNumField = new JLabel(studentList.get(container.getMarker()).getstudNum());
        studNumField.setBounds(130, 130, 300, 25);
        studNumField.setBackground(Color.white);
        studNumField.setOpaque(true);
        coursesPanel.add(studNumField);
        
        // STUDENT STATUS ENTRY
        studStatLabel.setBounds(750, 100, 110, 25);
        coursesPanel.add(studStatLabel);
        studStatField = new JLabel(studentList.get(container.getMarker()).getScholStatus());
        studStatField.setBounds(860, 100, 300, 25);
        studStatField.setBackground(Color.white);
        studStatField.setOpaque(true);
        coursesPanel.add(studStatField);
        
        // STUDENT YEAR ENTRY
        studYearLabel.setBounds(750, 130, 100, 25);
        coursesPanel.add(studYearLabel);
        studYearField = new JLabel(Integer.toString(studentList.get(container.getMarker()).getStudYear()));
        studYearField.setBounds(800, 130, 100, 25);
        studYearField.setBackground(Color.white);
        studYearField.setOpaque(true);
        coursesPanel.add(studYearField);
        
        // STUDENT SEMESTER ENTRY
        studSemLabel.setBounds(950, 130, 100, 25);
        coursesPanel.add(studSemLabel);
        studSemField = new JLabel(Integer.toString(studentList.get(container.getMarker()).getStudSem()));
        studSemField.setBounds(1050, 130, 100, 25);
        studSemField.setBackground(Color.white);
        studSemField.setOpaque(true);
        coursesPanel.add(studSemField);
        
        
        /* TABLE HEADER ENTRIES */
        // Course Code
        courseCodeLabel.setBounds(25, 165, 105, 40);
        courseCodeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        courseCodeLabel.setVerticalAlignment(SwingConstants.CENTER);
        courseCodeLabel.setBackground(TUPColor1);
        courseCodeLabel.setForeground(Color.WHITE);
        courseCodeLabel.setOpaque(true);
        courseCodeLabel.setBorder(border);
        coursesPanel.add(courseCodeLabel);
        // Course Description
        courseNameLabel.setBounds(130, 165, 385, 40);
        courseNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        courseNameLabel.setVerticalAlignment(SwingConstants.CENTER);
        courseNameLabel.setBackground(TUPColor1);
        courseNameLabel.setForeground(Color.WHITE);
        courseNameLabel.setOpaque(true);
        courseNameLabel.setBorder(border);
        coursesPanel.add(courseNameLabel);
        // Faculty Name 
        facultyLabel.setBounds(515, 165, 385, 40);
        facultyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        facultyLabel.setVerticalAlignment(SwingConstants.CENTER);
        facultyLabel.setBackground(TUPColor1);
        facultyLabel.setForeground(Color.WHITE);
        facultyLabel.setOpaque(true);
        facultyLabel.setBorder(border);
        coursesPanel.add(facultyLabel);
        // Number of Units 
        courseUnitsLabel.setBounds(900, 165, 100, 40);
        courseUnitsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        courseUnitsLabel.setVerticalAlignment(SwingConstants.CENTER);
        courseUnitsLabel.setBackground(TUPColor1);
        courseUnitsLabel.setForeground(Color.WHITE);
        courseUnitsLabel.setOpaque(true);
        courseUnitsLabel.setBorder(border);
        coursesPanel.add(courseUnitsLabel);
        // Grade 
        courseGradeLabel.setBounds(1000, 165, 100, 40);
        courseGradeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        courseGradeLabel.setVerticalAlignment(SwingConstants.CENTER);
        courseGradeLabel.setBackground(TUPColor1);
        courseGradeLabel.setForeground(Color.WHITE);
        courseGradeLabel.setOpaque(true);
        courseGradeLabel.setBorder(border);
        coursesPanel.add(courseGradeLabel);
        // Grade Status
        statusLabel.setBounds(1100, 165, 205, 40);
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setVerticalAlignment(SwingConstants.CENTER);
        statusLabel.setBackground(TUPColor1);
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setOpaque(true);
        statusLabel.setBorder(border);
        coursesPanel.add(statusLabel);

        StudentDetails student = studentList.get(container.getMarker());
	    ArrayList<CourseDetails> courseList = student.getCurrentCourseList();
	    
	    double finalGWA = 0.00;
	    double up = 0.00;
	    int units = 0;
	    
	    int i = 205;    
	    for (CourseDetails course : courseList) {
	    	
	    	JLabel codeValue = new JLabel(course.getCourseCode());
	    	JLabel descValue = new JLabel(courseDescription(course.getCourseCode()));
	    	JLabel facultyValue = new JLabel(course.getCourseFaculty());
	    	JLabel unitValue = new JLabel(String.valueOf(course.getCourseUnits()));
	    	
	    	up = up + (course.getCourseGrade() * course.getCourseGrade());
	    	units = units + course.getCourseUnits();
	    	
	    	double courseGrade = course.getCourseGrade();    	
	    	JLabel gradeValue = new JLabel(String.format("%.2f", courseGrade));
	    	
	    	JLabel statValue;
	    	
	    	if(course.getCourseGrade() <= 3.00 && course.getCourseGrade() != 0.00)
	    		statValue = new JLabel("PASSED");
	    	else if (course.getCourseGrade() == 0.00)
	    		statValue = new JLabel("-");
	    	else
	    		statValue = new JLabel("FAILED");
	    	
	    	
	    	
	    	// COURSE CODE
	    	codeValue.setBounds(25, i, 105, 40);
	    	codeValue.setHorizontalAlignment(SwingConstants.CENTER);
	    	codeValue.setVerticalAlignment(SwingConstants.CENTER);
	    	codeValue.setBackground(Color.white);
	    	codeValue.setForeground(Color.black);
	    	codeValue.setOpaque(true);
	    	codeValue.setBorder(border);
	        coursesPanel.add(codeValue);
	        // COURSE DESCRIPTION
	        descValue.setBounds(130, i, 385, 40);
	        descValue.setHorizontalAlignment(SwingConstants.CENTER);
	        descValue.setVerticalAlignment(SwingConstants.CENTER);
	    	descValue.setBackground(Color.white);
	    	descValue.setForeground(Color.black);
	    	descValue.setOpaque(true);
	    	descValue.setBorder(border);
	        coursesPanel.add(descValue);
	        // FACULTY NAME
	        facultyValue.setBounds(515, i, 385, 40);
	        facultyValue.setHorizontalAlignment(SwingConstants.CENTER);
	        facultyValue.setVerticalAlignment(SwingConstants.CENTER);
	    	facultyValue.setBackground(Color.white);
	    	facultyValue.setForeground(Color.black);
	    	facultyValue.setOpaque(true);
	    	facultyValue.setBorder(border);
	        coursesPanel.add(facultyValue);
	        // NUMBER OF UNITS
	        unitValue.setBounds(900, i, 100, 40);
	        unitValue.setHorizontalAlignment(SwingConstants.CENTER);
	        unitValue.setVerticalAlignment(SwingConstants.CENTER);
	    	unitValue.setBackground(Color.white);
	    	unitValue.setForeground(Color.black);
	    	unitValue.setOpaque(true);
	    	unitValue.setBorder(border);
	        coursesPanel.add(unitValue);
	        // GRADE
	        gradeValue.setBounds(1000, i, 100, 40);
	        gradeValue.setHorizontalAlignment(SwingConstants.CENTER);
	        gradeValue.setVerticalAlignment(SwingConstants.CENTER);
	    	gradeValue.setBackground(Color.white);
	    	gradeValue.setForeground(Color.black);
	    	gradeValue.setOpaque(true);
	    	gradeValue.setBorder(border);
	        coursesPanel.add(gradeValue);
	        // GRADE STATUS
	        statValue.setBounds(1100, i, 205, 40);
	        statValue.setHorizontalAlignment(SwingConstants.CENTER);
	        statValue.setVerticalAlignment(SwingConstants.CENTER);
	    	statValue.setBackground(Color.white);
	    	statValue.setForeground(Color.black);
	    	statValue.setOpaque(true);
	    	statValue.setBorder(border);
	        coursesPanel.add(statValue);
	    	
	    	i += 40;
	    }
	    
	    if (i >= 500) {
	    	
	    	coursesPanel.setPreferredSize(new Dimension(500, i + 100));
	    	
	        JScrollPane scrollPane = new JScrollPane(coursesPanel);
	        scrollPane.setBounds(0, 0, frame.getWidth(), frame.getHeight() - 100);
	        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	        frame.setContentPane(scrollPane);
	        
	    }
	    
	    finalGWA = up / units;
	    
	    JLabel gwaLabel = new JLabel("General Weighted Average (GWA): ");
	    JLabel gwaValue = new JLabel(String.format("%.3f", finalGWA));
	    // GENERAL WEIGHTED AVERAGE (GWA) LABEL
	    gwaLabel.setBounds(930, i + 20, 225, 40);
	    gwaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gwaLabel.setVerticalAlignment(SwingConstants.CENTER);
	    gwaLabel.setBackground(TUPColor1);
	    gwaLabel.setForeground(Color.WHITE);
	    gwaLabel.setOpaque(true);
        coursesPanel.add(gwaLabel);
	    // GENERAL WEIGHTED AVERAGE (GWA) FIELD
        gwaValue.setBounds(1155, i + 20, 150, 40);
	    gwaValue.setHorizontalAlignment(SwingConstants.CENTER);
        gwaValue.setVerticalAlignment(SwingConstants.CENTER);
	    gwaValue.setBackground(Color.WHITE);
	    gwaValue.setOpaque(true);
        coursesPanel.add(gwaValue);
        
        continueButton.setBounds(25, i + 20, 150, 25);
        continueButton.addActionListener(e -> {
        	frame.dispose();
        	if (callback != null) {
                callback.run();
            }
        });
        coursesPanel.add(continueButton);
        
        /******************FOR ICONS******************/
 		ImageIcon CHEDlogo = new ImageIcon("images/CHED_LOGO.png");
 		ImageIcon MICSlogo = new ImageIcon("images/MICSlogo.png");
 		
 		JLabel CHEDLabel = new JLabel(CHEDlogo);
 		JLabel MICSLabel = new JLabel(MICSlogo);
 		
 		Image image1 = CHEDlogo.getImage();
 		Image image2 = MICSlogo.getImage();
 		
 		Image scaledImage1 = image1.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
 		Image scaledImage2 = image2.getScaledInstance(90, 90, Image.SCALE_SMOOTH);
 		
        CHEDLabel.setIcon(new ImageIcon(scaledImage1));
        CHEDLabel.setBounds(1220, 0, 120, 90);
        CHEDLabel.setBackground(TUPColor1);
        CHEDLabel.setOpaque(true);
        MICSLabel.setIcon(new ImageIcon(scaledImage2));
        MICSLabel.setBounds(0, 0, 120, 90);
        MICSLabel.setBackground(TUPColor1);
        MICSLabel.setOpaque(true);
        
        coursesPanel.add(CHEDLabel);
        coursesPanel.add(MICSLabel);
        /*********************************************/
        /*********************************************/
        
	    // SET FRAME TO MIDDLE OF SCREEN
        int frameWidth = frame.getWidth();
        int frameHeight = frame.getHeight();
        int[] xPos = new int[1];
        int[] yPos = new int[1];     
        setFrametoMiddle(frameWidth, frameHeight, xPos, yPos);        
        frame.setLocation(xPos[0], yPos[0]);
        
        frame.setResizable(false);
        frame.setVisible(true);
        
	}	
	
	public void updateStudInfo() {
		
		frame = new JFrame("MANILA INSTITUTE OF COMPUTER STUDIES");
		
		ArrayList<StudentDetails> studentList = container.getStudentList();

	    JPanel nextPagePanel = new JPanel(); 
	    JPanel studentDetailsPanel = new JPanel();
	    
	    JTextField nameTextField; // Last Name
	    JTextField givenNameTextField; // First Name
	    JTextField middleNameTextField; // Middle Name
	    JRadioButton maleRadioButton; // For male
	    JRadioButton femaleRadioButton; // For female
	    JTextField birthdateTextField; // Birthday
	    JTextField phoneNumTextField; // Student's Phone Number
	    JTextField emailAddTextField; // Student's Email Address
	    JTextField addrssTextField; // Student's Address
	    JTextField gemailAddTextField; // Guardian's Email Address
	    JTextField gPhoneNumTextField; // Guardian's Phone Number
		
		nextPagePanel.setVisible(false);
    	studentDetailsPanel.setLayout(null);  
    	
    	frame.setSize(1350, 700);
    	
    	JButton submitButton= new JButton(); 
    	
    	Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        Color TUPColor1 = new Color(126, 2, 2); 
        Font font1 = new Font("Arial", Font.BOLD, 30); 
        Font font2 = new Font("Arial", Font.BOLD, 15); 
        Font font3 = new Font("Arial", Font.PLAIN, 10);

        JLabel titleLabel = new JLabel("Enrollment Form");
        titleLabel.setBounds(0, 0, 1350, 90);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setVerticalAlignment(SwingConstants.CENTER); 
        titleLabel.setFont(font1);
        titleLabel.setBackground(TUPColor1);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setOpaque(true);
        titleLabel.setBorder(border);
        studentDetailsPanel.add(titleLabel);
        
        JLabel studDetLabel = new JLabel("Student's Details");
        studDetLabel.setBounds(0, 90, 1350, 40);
        studDetLabel.setHorizontalAlignment(SwingConstants.CENTER);
        studDetLabel.setVerticalAlignment(SwingConstants.CENTER); 
        studDetLabel.setFont(font2);
        studDetLabel.setBackground(Color.WHITE);
        studDetLabel.setForeground(Color.BLACK);
        studDetLabel.setOpaque(true);
        studDetLabel.setBorder(border);
        studentDetailsPanel.add(studDetLabel); 
        
        /******************************************************/
        /******************* DISABLED DETAILS *****************/   
        
        JLabel studsNameTitle = new JLabel("Student's Name"); 
        studsNameTitle.setBounds(390, 130, 150, 25);
        studentDetailsPanel.add(studsNameTitle);

        // LAST NAME LABEL AND INPUT FIELD
        JLabel nameLabel = new JLabel("Last Name");
        nameLabel.setBounds(390, 175, 150, 25); 
        nameLabel.setFont(font3);
        studentDetailsPanel.add(nameLabel);
        //
        nameTextField = new JTextField(studentList.get(container.getMarker()).getlName());
        nameTextField.setBounds(390, 155, 150, 25);
        studentDetailsPanel.add(nameTextField);
        nameTextField.setEnabled(false);

        // GIVEN NAME LABEL AND INPUT FIELD
        JLabel givenNameLabel = new JLabel("Given Name");
        givenNameLabel.setBounds(580, 175, 150, 25); 
        givenNameLabel.setFont(font3);
        studentDetailsPanel.add(givenNameLabel);
        //
        givenNameTextField = new JTextField(studentList.get(container.getMarker()).getfName());
        givenNameTextField.setBounds(580, 155, 150, 25);
        studentDetailsPanel.add(givenNameTextField); 
        givenNameTextField.setEnabled(false);
        
        // MIDDLE NAME LABEL AND INPUT FIELD
        JLabel middleNameLabel = new JLabel("Middle Name");
        middleNameLabel.setBounds(770, 175, 150, 25); 
        middleNameLabel.setFont(font3);
        studentDetailsPanel.add(middleNameLabel);
        //
        middleNameTextField = new JTextField(studentList.get(container.getMarker()).getmName());
        middleNameTextField.setBounds(770, 155, 150, 25);
        studentDetailsPanel.add(middleNameTextField); 
        middleNameTextField.setEnabled(false);
        
        // SEX LABEL
        JLabel sexLabel = new JLabel("Sex");
        sexLabel.setBounds(390, 220, 150, 25);
        studentDetailsPanel.add(sexLabel);
        // SEX OPTIONS
        maleRadioButton = new JRadioButton("Male");
        femaleRadioButton = new JRadioButton("Female");

        maleRadioButton.setBounds(390, 245, 70, 25);
        femaleRadioButton.setBounds(460, 245, 80, 25);  
        
        if(studentList.get(container.getMarker()).getStudSex() == 'M')
        	maleRadioButton.setSelected(true);
        else
        	femaleRadioButton.setSelected(true);
      
        maleRadioButton.setEnabled(false);
        femaleRadioButton.setEnabled(false);
        
        ButtonGroup sexButtonGroup = new ButtonGroup();
        sexButtonGroup.add(maleRadioButton);
        sexButtonGroup.add(femaleRadioButton);
        
        studentDetailsPanel.add(maleRadioButton);
        studentDetailsPanel.add(femaleRadioButton); 
        
        /******************* DISABLED DETAILS *****************/
        /******************************************************/
        
        JLabel birthdateLabel = new JLabel("Date of Birth");
        birthdateLabel.setBounds(580, 220, 150, 25);
        studentDetailsPanel.add(birthdateLabel); 
        
        JLabel bdayLabel = new JLabel("[MM-DD-YYYY]");
        bdayLabel.setBounds(580, 265, 150, 25); 
        bdayLabel.setFont(font3);
        studentDetailsPanel.add(bdayLabel);

        birthdateTextField = new JTextField(studentList.get(container.getMarker()).getStudDOB());
        birthdateTextField.setBounds(580, 245, 150, 25);
        studentDetailsPanel.add(birthdateTextField); 
        
        JLabel phoneNumLabel = new JLabel("Phone No.");
        phoneNumLabel.setBounds(770, 300, 150, 25);
        studentDetailsPanel.add(phoneNumLabel);

        phoneNumTextField = new JTextField(studentList.get(container.getMarker()).getStudPhoneNum());
        phoneNumTextField.setBounds(770, 325, 150, 25);
        studentDetailsPanel.add(phoneNumTextField); 
        
        JLabel emailAddLabel = new JLabel("Email");
        emailAddLabel.setBounds(390, 300, 150, 25);
        studentDetailsPanel.add(emailAddLabel); 
        
        JLabel emailFormatLabel = new JLabel("example@example.com");
        emailFormatLabel.setBounds(390, 345, 150, 25); 
        emailFormatLabel.setFont(font3);
        studentDetailsPanel.add(emailFormatLabel);

        emailAddTextField = new JTextField(studentList.get(container.getMarker()).getStudEmail());
        emailAddTextField.setBounds(390, 325, 345, 25);
        studentDetailsPanel.add(emailAddTextField); 
        
        JLabel addrssLabel = new JLabel("Student's Address");
        addrssLabel.setBounds(390, 390, 150, 25);
        studentDetailsPanel.add(addrssLabel); 
        
        JLabel addrssFormatLabel = new JLabel("Street Address");
        addrssFormatLabel.setBounds(390, 435, 150, 25); 
        addrssFormatLabel.setFont(font3);
        studentDetailsPanel.add(addrssFormatLabel);

        addrssTextField = new JTextField(studentList.get(container.getMarker()).getStudAddress());
        addrssTextField.setBounds(390, 415, 535, 25);
        studentDetailsPanel.add(addrssTextField);   
        
        JLabel gemailAddLabel = new JLabel("Guardian's Email");
        gemailAddLabel.setBounds(390, 480, 150, 25);
        studentDetailsPanel.add(gemailAddLabel); 
        
        JLabel gemailFormatLabel = new JLabel("example@example.com");
        gemailFormatLabel.setBounds(390, 525, 150, 25); 
        gemailFormatLabel.setFont(font3);
        studentDetailsPanel.add(gemailFormatLabel);

        gemailAddTextField = new JTextField(studentList.get(container.getMarker()).getGuardianEmail());
        gemailAddTextField.setBounds(390, 505, 345, 25);
        studentDetailsPanel.add(gemailAddTextField); 
        
        JLabel gPhoneNumLabel = new JLabel("Guardian's Phone No.");
        gPhoneNumLabel.setBounds(770, 480, 150, 25);
        studentDetailsPanel.add(gPhoneNumLabel); 
        
        gPhoneNumTextField = new JTextField(studentList.get(container.getMarker()).getGuardianPhoneNum());
        gPhoneNumTextField.setBounds(770, 505, 150, 25);
        studentDetailsPanel.add(gPhoneNumTextField);    
        
        submitButton = new JButton("Submit");
        submitButton.setBounds(610, 590, 80, 25); 
        submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				// STUDENT BIRTHDATE
		        if (!birthdateTextField.getText().isEmpty()) 
		            studentList.get(container.getMarker()).setStudDOB(birthdateTextField.getText());
		        
		        // STUDENT PHONE NUMBER
		        if (!phoneNumTextField.getText().isEmpty()) 
		            studentList.get(container.getMarker()).setStudPhoneNum(phoneNumTextField.getText());
		        

		        // STUDENT EMAIL ADDRESS
		        if (!emailAddTextField.getText().isEmpty()) 
		            studentList.get(container.getMarker()).setStudEmail(emailAddTextField.getText());
		        
		        // STUDENT ADDRESS
		        if (!addrssTextField.getText().isEmpty()) 
		            studentList.get(container.getMarker()).setStudAddress(addrssTextField.getText());
		        
		        // STUDENT'S GUARDIAN EMAIL ADDRESS
		        if (!gemailAddTextField.getText().isEmpty()) 
		            studentList.get(container.getMarker()).setGuardianEmail(gemailAddTextField.getText());
		        
		        // STUDENT'S GUARDIAN PHONE NUMBER
		        if (!gPhoneNumTextField.getText().isEmpty()) 
		            studentList.get(container.getMarker()).setGuardianPhoneNum(gPhoneNumTextField.getText());
		        
				loadingAnimation("SAVING...", () -> {
	        		
					frame.dispose();
	                
			    });
				
			}
                
        });  
        
        studentDetailsPanel.add(submitButton); 
        studentDetailsPanel.setVisible(true);
        frame.getContentPane().add(studentDetailsPanel); 
		frame.setVisible(true);
		frame.setResizable(false);
		
		// SET FRAME TO MIDDLE OF SCREEN
        int frameWidth = frame.getWidth();
        int frameHeight = frame.getHeight();
        int[] xPos = new int[1];
        int[] yPos = new int[1];     
        setFrametoMiddle(frameWidth, frameHeight, xPos, yPos);        
        frame.setLocation(xPos[0], yPos[0]);
		
	}
	
	public void successEnroll(Runnable callback) {
		
		frame = new JFrame("MANILA INSTITUTE OF COMPUTER STUDIES");
		
		//ArrayList<StudentDetails> studentList = container.getStudentList();
		
		JPanel panel = new JPanel();
		
		JLabel header = new JLabel();
		JLabel schoolTitle = new JLabel("MANILA INSTITUTE OF COMPUTER STUDIES");
		JLabel statusTag = new JLabel("STATUS UPDATED");
		
		JButton proceedButton = new JButton("PROCEED");
		
		JLabel messageLabel = new JLabel("YOUR ARE NOW ENROLLED FOR");
		JLabel message2Label;
		
		panel.setBackground(MICSColor2);
		panel.setLayout(null);
		
		/*** IMAGES ***/
		/**************/
		ImageIcon schoolPic = new ImageIcon("images/MICSlogo.png");     
    	JLabel icon = new JLabel(schoolPic);    	
    	Image image = schoolPic.getImage();
    	Image scaledImage = image.getScaledInstance(60, 60, Image.SCALE_SMOOTH);

        icon.setIcon(new ImageIcon(scaledImage));
        icon.setBounds(5, 5, 60, 60);

        panel.add(icon);
		/**************/
		/*** IMAGES ***/
        
        schoolTitle.setFont(font4);
        schoolTitle.setForeground(MICSColor2);
        schoolTitle.setBounds(80, 25, 500, 25);
        panel.add(schoolTitle);
        
        header.setBackground(MICSColor1);
        header.setBounds(0, 0, 600, 70);
		header.setOpaque(true);
		panel.add(header);
		
		statusTag.setFont(font1);
		statusTag.setForeground(MICSColor1);
		statusTag.setHorizontalAlignment(SwingConstants.CENTER);
		statusTag.setVerticalAlignment(SwingConstants.CENTER);
		statusTag.setBounds(185, 100, 230, 25);
        panel.add(statusTag);
        
        messageLabel.setFont(font2);
        messageLabel.setBackground(MICSColor1);
        messageLabel.setForeground(MICSColor2);
        messageLabel.setOpaque(true);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setVerticalAlignment(SwingConstants.CENTER);
        messageLabel.setBounds(150, 160, 300, 25);
        panel.add(messageLabel);
        
        // FOR NEW STUDENT YEAR AND SEMESTER (after enrollment)
        int x = container.getStudentList().get(container.getMarker()).getStudYear(); 
        int y = container.getStudentList().get(container.getMarker()).getStudSem();
        
        String yearNSem = "";
        
        if(x == 1)
        	yearNSem += "1ST ";
        else if (x == 2)
        	yearNSem += "2ND ";
        else if (x == 3)
        	yearNSem += "3RD ";
        else if (x == 4)
        	yearNSem += "4TH ";        
        
        yearNSem += "YEAR - ";
        
        if(y == 1)
        	yearNSem += "1ST ";
        else if (y == 2)
        	yearNSem += "2ND ";
        
        yearNSem += "SEMESTER";
        // FOR NEW STUDENT YEAR AND SEMESTER (after enrollment)
        
        message2Label = new JLabel(yearNSem);
        message2Label.setFont(font1);
        message2Label.setForeground(MICSColor1);
        message2Label.setOpaque(true);
        message2Label.setHorizontalAlignment(SwingConstants.CENTER);
        message2Label.setVerticalAlignment(SwingConstants.CENTER);
        message2Label.setBounds(100, 200, 400, 25);
        panel.add(message2Label);      
        
        proceedButton.setForeground(MICSColor2);
        proceedButton.setBackground(MICSColor1);
        proceedButton.setOpaque(true);
        proceedButton.setFocusPainted(false);
        proceedButton.setBounds(250, 250, 100, 25);
        proceedButton.addActionListener(e -> {
        	frame.dispose();
        	if (callback != null) {
                callback.run();
            }
        });
        panel.add(proceedButton);
		
		frame.setSize(600, 350);
		frame.add(panel);
		frame.setResizable(false);
        frame.setVisible(true);
		
		// SET FRAME TO MIDDLE OF SCREEN
        int frameWidth = frame.getWidth();
        int frameHeight = frame.getHeight();
        int[] xPos = new int[1];
        int[] yPos = new int[1];     
        setFrametoMiddle(frameWidth, frameHeight, xPos, yPos);        
        frame.setLocation(xPos[0], yPos[0]);
		
	}
	
	public void showAccount() {
		
		frame = new JFrame("MANILA INSTITUTE OF COMPUTER STUDIES");
		
		JPanel panel = new JPanel();
		
		JLabel header = new JLabel();
		JLabel schoolTitle = new JLabel("MANILA INSTITUTE OF COMPUTER STUDIES");
		JLabel accountTitle = new JLabel("ACCOUNT");
		
		JLabel studNameLabel = new JLabel("STUDENT NAME");
		
		String name = studentList.get(container.getMarker()).getlName() + ", " +
						studentList.get(container.getMarker()).getfName() + " " +
							studentList.get(container.getMarker()).getmName();
		JLabel studNameField = new JLabel(name);	
		
		JLabel userLabel = new JLabel("USERNAME");
		JLabel userField = new JLabel(studentList.get(container.getMarker()).getstudNum());
		
		JButton changePassButton = new JButton("CHANGE PASSWORD");
		JButton backButton = new JButton("BACK");
		
		panel.setBackground(MICSColor2);
		panel.setLayout(null);
		
		/*** IMAGES ***/
		/**************/
		ImageIcon schoolPic = new ImageIcon("images/MICSlogo.png");     
    	JLabel icon = new JLabel(schoolPic);    	
    	Image image = schoolPic.getImage();
    	Image scaledImage = image.getScaledInstance(60, 60, Image.SCALE_SMOOTH);

        icon.setIcon(new ImageIcon(scaledImage));
        icon.setBounds(5, 5, 60, 60);

        panel.add(icon);
		/**************/
		/*** IMAGES ***/
       
        schoolTitle.setFont(font4);
        schoolTitle.setForeground(MICSColor2);
        schoolTitle.setBounds(80, 25, 500, 25);
        panel.add(schoolTitle);
		
		header.setBackground(MICSColor1);
        header.setBounds(0, 0, 600, 70);
		header.setOpaque(true);
		panel.add(header);
		
        accountTitle.setFont(font1);
        accountTitle.setForeground(MICSColor1);
        accountTitle.setHorizontalAlignment(SwingConstants.CENTER);
        accountTitle.setVerticalAlignment(SwingConstants.CENTER);
        accountTitle.setBounds(225, 100, 150, 25);
        panel.add(accountTitle);
        
        studNameLabel.setFont(font2);
        studNameLabel.setForeground(MICSColor1);
        studNameLabel.setBounds(30, 170, 150, 25);
        panel.add(studNameLabel);
        
        studNameField.setFont(font3);
        studNameField.setForeground(Color.BLACK);
        studNameField.setBackground(Color.white);
        studNameField.setOpaque(true);
        studNameField.setBounds(200, 170, 350, 25);
        panel.add(studNameField);
        
        userLabel.setFont(font2);
        userLabel.setForeground(MICSColor1);
        userLabel.setBounds(30, 220, 150, 25);
        panel.add(userLabel);
        
        userField.setFont(font3);
        userField.setForeground(Color.BLACK);
        userField.setBackground(Color.white);
        userField.setOpaque(true);
        userField.setBounds(200, 220, 350, 25);
        panel.add(userField);
        
        changePassButton.setForeground(MICSColor2);
        changePassButton.setBackground(MICSColor1);
        changePassButton.setOpaque(true);
        changePassButton.setFocusPainted(false);
        changePassButton.setBounds(200, 300, 200, 25);
        changePassButton.addActionListener(new ActionListener() {
        	
            public void actionPerformed(ActionEvent e) {
            	
            	frame.dispose();
            	changePass();
            	
            }
            
        });
        panel.add(changePassButton);
        
        backButton.setForeground(MICSColor1);
        backButton.setBackground(MICSColor2);
        backButton.setOpaque(true);
        backButton.setFocusPainted(false);
        backButton.setBounds(265, 350, 70, 25);
        backButton.addActionListener(new ActionListener() {
        	
            public void actionPerformed(ActionEvent e) {
            	
            	frame.dispose();
            	
            }
            
        });
        panel.add(backButton);
        
		frame.setSize(600, 450);
		frame.add(panel);
		frame.setResizable(false);
        frame.setVisible(true);
		
		// SET FRAME TO MIDDLE OF SCREEN
        int frameWidth = frame.getWidth();
        int frameHeight = frame.getHeight();
        int[] xPos = new int[1];
        int[] yPos = new int[1];     
        setFrametoMiddle(frameWidth, frameHeight, xPos, yPos);        
        frame.setLocation(xPos[0], yPos[0]);
        	
	}
	
	public void changePass()
	{
		frame = new JFrame("MANILA INSTITUTE OF COMPUTER STUDIES");
		
		JPanel panel = new JPanel();
		
		JLabel header = new JLabel();
		JLabel schoolTitle = new JLabel("MANILA INSTITUTE OF COMPUTER STUDIES");
		JLabel passTitle = new JLabel("CHANGE PASSWORD");
		
		JLabel passLabel1 = new JLabel("ENTER PASSWORD");
		JPasswordField passField1 = new JPasswordField();
		JLabel passLabel2 = new JLabel("CONFIRM PASSWORD");
		JPasswordField passField2 = new JPasswordField();
		JLabel passLabel3 = new JLabel("ENTER NEW PASSWORD");
		JPasswordField passField3 = new JPasswordField();
		
		JButton confirmButton = new JButton("CHANGE PASSWORD");
		JButton backButton = new JButton("BACK");
		
		panel.setBackground(MICSColor2);
		panel.setLayout(null);
		
		/*** IMAGES ***/
		/**************/
		ImageIcon schoolPic = new ImageIcon("images/MICSlogo.png");     
    	JLabel icon = new JLabel(schoolPic);    	
    	Image image = schoolPic.getImage();
    	Image scaledImage = image.getScaledInstance(60, 60, Image.SCALE_SMOOTH);

        icon.setIcon(new ImageIcon(scaledImage));
        icon.setBounds(5, 5, 60, 60);

        panel.add(icon);
		/**************/
		/*** IMAGES ***/
       
        schoolTitle.setFont(font4);
        schoolTitle.setForeground(MICSColor2);
        schoolTitle.setBounds(80, 25, 500, 25);
        panel.add(schoolTitle);
		
		header.setBackground(MICSColor1);
        header.setBounds(0, 0, 600, 70);
		header.setOpaque(true);
		panel.add(header);
		
        passTitle.setFont(font1);
        passTitle.setForeground(MICSColor1);
        passTitle.setHorizontalAlignment(SwingConstants.CENTER);
        passTitle.setVerticalAlignment(SwingConstants.CENTER);
        passTitle.setBounds(150, 100, 300, 25);
        panel.add(passTitle);
        	
    	passLabel1.setFont(font2);
        passLabel1.setForeground(MICSColor1);
        passLabel1.setBounds(30, 170, 250, 25);
        panel.add(passLabel1);
        
        passField1.setFont(font3);
        passField1.setForeground(Color.BLACK);
        passField1.setBackground(Color.white);
        passField1.setOpaque(true);
        passField1.setBounds(300, 170, 250, 25);
        panel.add(passField1);
        
        passLabel2.setFont(font2);
        passLabel2.setForeground(MICSColor1);
        passLabel2.setBounds(30, 220, 250, 25);
        panel.add(passLabel2);
        
        passField2.setFont(font3);
        passField2.setForeground(Color.BLACK);
        passField2.setBackground(Color.white);
        passField2.setOpaque(true);
        passField2.setBounds(300, 220, 250, 25);
        panel.add(passField2);
        
        passLabel3.setFont(font2);
        passLabel3.setForeground(MICSColor1);
        passLabel3.setBounds(30, 270, 250, 25);
        panel.add(passLabel3);
        
        passField3.setFont(font3);
        passField3.setForeground(Color.BLACK);
        passField3.setBackground(Color.white);
        passField3.setOpaque(true);
        passField3.setBounds(300, 270, 250, 25);
        panel.add(passField3);
        
        confirmButton.setForeground(MICSColor2);
        confirmButton.setBackground(MICSColor1);
        confirmButton.setOpaque(true);
        confirmButton.setFocusPainted(false);
        confirmButton.setBounds(200, 320, 200, 25);
        confirmButton.addActionListener(new ActionListener() {
        	
            public void actionPerformed(ActionEvent e) {
            	
            	 if(studentList.get(container.getMarker()).getstudPass().equals(passField1.getText())) {
            		
	            	if(passField1.getText().equals(passField2.getText())) {
	            		
	            		studentList.get(container.getMarker()).setstudPass(passField3.getText());
	            		
	            		loadingAnimation("SAVING NEW PASSWORD...", () -> {
	            			
	            			frame.dispose();
	            			showAccount();
	            			
	            		});
	            		
	            	} else {
	            		
	            		JOptionPane.showMessageDialog(frame, "Passwords do not match. Please try again.", "PASSWORD MISMATCH", JOptionPane.ERROR_MESSAGE);
	            		
	            	}
	            	
            	} else {
            		
            		JOptionPane.showMessageDialog(frame, "Password is incorrect. Please try again.", "PASSWORD ERROR", JOptionPane.ERROR_MESSAGE);
            		
            	}
            	
            }
            
        });
        panel.add(confirmButton);
        
        backButton.setForeground(MICSColor1);
        backButton.setBackground(MICSColor2);
        backButton.setOpaque(true);
        backButton.setFocusPainted(false);
        backButton.setBounds(265, 360, 70, 25);
        backButton.addActionListener(new ActionListener() {
        	
            public void actionPerformed(ActionEvent e) {
            	
            	frame.dispose();
            	showAccount();
            	
            }
            
        });
        panel.add(backButton);
        
        frame.setSize(600, 450);
		frame.add(panel);
		frame.setResizable(false);
        frame.setVisible(true);
		
		// SET FRAME TO MIDDLE OF SCREEN
        int frameWidth = frame.getWidth();
        int frameHeight = frame.getHeight();
        int[] xPos = new int[1];
        int[] yPos = new int[1];     
        setFrametoMiddle(frameWidth, frameHeight, xPos, yPos);        
        frame.setLocation(xPos[0], yPos[0]);
        
	}
	
	public void setFrametoMiddle(int frameWidth, int frameHeight,
									int[] xPos, int[] yPos) {
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();  // Get the screen size
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        xPos[0] = (screenWidth - frameWidth) / 2;  // Calculate the x position for the frame
        yPos[0] = (screenHeight - frameHeight) / 2;  // Calculate the y position for the frame
	
	}
	
	public String courseDescription(String courseCode) {
		
		String courseDesc = null;
		
		if(courseCode.equals("CC113"))
			courseDesc = "INTRODUCTION TO COMPUTING";
		else if(courseCode.equals("CC131L"))
			courseDesc = "COMPUTING PROGRAMMING 1, Lab";
		else if(courseCode.equals("CC132"))
			courseDesc = "COMPUTING PROGRAMMING 1, Lec";
		else if(courseCode.equals("MATHA05S"))
			courseDesc = "FUNDAMENTALS OF MATH ANALYSIS";
		else if(courseCode.equals("CC141L"))
			courseDesc = "COMPUTING PROGRAMMING 2, Lab";
		else if(courseCode.equals("CC142"))
			courseDesc = "COMPUTING PROGRAMMING 2, Lec";
		else if(courseCode.equals("CC103"))
			courseDesc = "DISCRETE STRUCTURES";
		else if(courseCode.equals("CS123"))
			courseDesc = "LINEAR ALGEBRA";
		else if(courseCode.equals("MATHA35"))
			courseDesc = "DIFFERENTIAL AND INTEGRAL CALCULUS";
		else if(courseCode.equals("CC211L"))
			courseDesc = "DATA STRUCTURES AND ALGORITHM, Lab";
		else if(courseCode.equals("CC212"))
			courseDesc = "DATA STRUCTURES AND ALGORITHM, Lec";
		else if(courseCode.equals("CS251L"))
			courseDesc = "OBJECT ORIENTED PROGRAMMING, Lab";
		else if(courseCode.equals("CS252"))
			courseDesc = "OBJECT ORIENTED PROGRAMMING, Lec";
		else if(courseCode.equals("CS271L"))
			courseDesc = "COMPUTER ARCHITECTURE AND ORGANIZATION, Lab";
		else if(courseCode.equals("CS272"))
			courseDesc = "COMPUTER ARCHITECTURE AND ORGANIZATION, Lec";
		else if(courseCode.equals("CS213"))
			courseDesc = "HUMAN COMPUTER INTERACTION";
		else if(courseCode.equals("CS233"))
			courseDesc = "COMBINATORICS AND GRAPH THEORY";
		else if(courseCode.equals("CC201L"))
			courseDesc = "INFORMATION MANAGEMENT, Lab";
		else if(courseCode.equals("CC202"))
			courseDesc = "INFORMATION MANAGEMENT, Lec";
		else if(courseCode.equals("CS201L"))
			courseDesc = "OPERATING SYSTEMS, Lab";
		else if(courseCode.equals("CS202"))
			courseDesc = "OPERATING SYSTEMS, Lec";
		else if(courseCode.equals("CS221L"))
			courseDesc = "PROGRAMMING LANGUAGE (DESIGN AND IMPLEMENTATION), Lab";
		else if(courseCode.equals("CS222"))
			courseDesc = "PROGRAMMING LANGUAGE (DESIGN AND IMPLEMENTATION), Lec";
		else if(courseCode.equals("CS261L"))
			courseDesc = "NETWORK AND COMMUNICATIONS, Lab";
		else if(courseCode.equals("CS262"))
			courseDesc = "NETWORK AND COMMUNICATIONS, Lec";
		else if(courseCode.equals("CS243"))
			courseDesc = "DESIGN AND ANALYSIS OF ALGORITHMS";
		else if(courseCode.equals("CC223"))
			courseDesc = "APPLICATIONS DEVELOPMENT AND EMERGING TECHNOLOGIES";
		else if(courseCode.equals("MATHSTAT03"))
			courseDesc = "PROBABILITY AND STATISTICS";
		else if(courseCode.equals("CSE1"))
			courseDesc = "CS PROFESSIONAL ELECTIVE 1";
		else if(courseCode.equals("CSE2"))
			courseDesc = "CS PROFESSIONAL ELECTIVE 2";
		else if(courseCode.equals("CS373"))
			courseDesc = "PARALLEL AND DISTRIBUTED COMPUTING";
		else if(courseCode.equals("CS351L"))
			courseDesc = "SOFTWARE ENGINEERING 1, Lab";
		else if(courseCode.equals("CS352"))
			courseDesc = "SOFTWARE ENGINEERING 1, Lec";
		else if(courseCode.equals("CS333"))
			courseDesc = "DATA ANALYTICS";
		else if(courseCode.equals("CS313"))
			courseDesc = "INFORMATION ASSURANCE AND SECURITY";
		else if(courseCode.equals("CC311L"))
			courseDesc = "WEB DEVELOPMENT, Lab";
		else if(courseCode.equals("CC312"))
			courseDesc = "WEB DEVELOPMENT, Lec";
		else if(courseCode.equals("CC303"))
			courseDesc = "METHODS OF RESEARCH IN COMPUTING";
		else if(courseCode.equals("CS303"))
			courseDesc = "AUTOMATA THEORY AND FORMAL LANGUAGE";
		else if(courseCode.equals("CS321L"))
			courseDesc = "ARTIFICIAL INTELLIGENCE, Lab";
		else if(courseCode.equals("CS322"))
			courseDesc = "ARTIFICIAL INTELLIGENCE, Lec";
		else if(courseCode.equals("CS343"))
			courseDesc = "MODELING AND SIMULATION";
		else if(courseCode.equals("CS361L"))
			courseDesc = "SOFTWARE ENGINEERING 2, Lab";
		else if(courseCode.equals("CS362"))
			courseDesc = "SOFTWARE ENGINEERING 2, Lec";
		else if(courseCode.equals("CSE3"))
			courseDesc = "CS PROFESSIONAL ELECTIVE 3";
		else if(courseCode.equals("CSE4"))
			courseDesc = "CS PROFESSIONAL ELECTIVE 4";
		else if(courseCode.equals("CS413"))
			courseDesc = "THESIS WRITING 1";
		else if(courseCode.equals("CS433"))
			courseDesc = "SOCIAL AND PROFESSIONAL ISSUES";
		else if(courseCode.equals("CS403"))
			courseDesc = "SUPERVISED INDUSTRIAL TRAINING";
		else
			courseDesc = "THESIS WRITING 2";
		
		return courseDesc;
	}	

    public void loadingAnimation(String announcement, Runnable callback) {
    	 
    	Color MICSColor1 = new Color(126, 2, 2); // medyo maroon
    	Color MICSColor2 = new Color(241, 234, 213); // beige

        JFrame frame = new JFrame();
        frame.getContentPane().setBackground(MICSColor2);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Loading Animation Example");

        // Create a JLabel to display the announcement
        JLabel announcementLabel = new JLabel(announcement);
        announcementLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        announcementLabel.setForeground(Color.black);
        announcementLabel.setHorizontalAlignment(SwingConstants.CENTER);
        announcementLabel.setVerticalAlignment(SwingConstants.CENTER);
        frame.add(announcementLabel, BorderLayout.NORTH);

        // Create a JProgressBar
        JProgressBar progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setPreferredSize(new Dimension(300, 30));
        progressBar.setForeground(MICSColor1); // Set the progress bar color
        frame.add(progressBar, BorderLayout.CENTER);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Start the loading animation on a separate thread
        Thread animationThread = new Thread(() -> {
            Timer timer = new Timer(100, new ActionListener() {
                int progress = 0;

                @Override
                public void actionPerformed(ActionEvent e) {
                    progress += 5;
                    progressBar.setValue(progress);

                    if (progress >= 100) {
                        // Stop the timer and perform any necessary actions
                        ((Timer) e.getSource()).stop();
                        frame.dispose();

                        if (callback != null) { // Invoke callback
                            callback.run();
                        }
                    }
                }
            });

            // Start the timer
            timer.start();
        });

        // Start the animation thread
        animationThread.start();
        
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
