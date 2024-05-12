import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

public class MainMenu extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private StudentListContainer container;
	private General general;
	private FileHandling fileHandling;
	private Menu menu;
	
	private JFrame frame;
	private JPanel mMenuPanel;
	
	/**** GENERAL USAGE ****/
	// Colors
	private Color MICSColor1 = new Color(126, 2, 2); // maroon
	private Color MICSColor2 = new Color(241, 234, 213); // beige
	private Color color3 = Color.decode("#472213");
	// Fonts
	private Font font1 = new Font("Montserrat Medium", Font.BOLD, 14);
	private Font font2 = new Font("Montserrat Medium", Font.BOLD, 16);
	private Font font3 = new Font("Montserrat Medium", Font.PLAIN, 12);
	private Font font4 = new Font("Bahnschrift", Font.BOLD, 30);
	private Font font5 = new Font("Montserrat", Font.BOLD, 10);
	// Border
	Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
	//Variable
	private String CurrImage;
	//////////
	
	public MainMenu() {
		
        container = StudentListContainer.getInstance();
        general = new General();
        fileHandling = new FileHandling();
        menu = new Menu();
        
    }
	
	public void mainMenu() {
		
		frame = new JFrame("MANILA INSTITUTE OF COMPUTER STUDIES");
		mMenuPanel = new JPanel();
		
		JLabel separator1 = new JLabel();
		
		// BUTTONS
		JButton displayButton = new RoundedButton("View Grades");
		JButton updateButton = new RoundedButton("Student Information");
		JButton enrollButton = new RoundedButton("Enrollment");
		JButton accountButton = new RoundedButton("Account Settings");
		JButton logOutButton = new RoundedButton("LOG-OUT");
		JButton saveButton = new RoundedButton("SAVE SCHEDULE");
		JButton uploadButton = new JButton("Upload Image");
        
        mMenuPanel.setBackground(MICSColor2);
        mMenuPanel.setLayout(null);
        
        /******* IMAGES ********/
        /***********************/
        
        // STUDENT PICTURE         
        retrieveStudentImage();
        displayImage();
        
        ImageIcon schoolPic = new ImageIcon("images/MICSlogo.png");     
    	JLabel icon2 = new JLabel(schoolPic);    	
    	Image image2 = schoolPic.getImage();
    	Image scaledImage2 = image2.getScaledInstance(120, 120, Image.SCALE_SMOOTH);

        icon2.setIcon(new ImageIcon(scaledImage2));
        icon2.setBounds(280, 15, 120, 120);
        icon2.setBackground(MICSColor2);
        icon2.setOpaque(false);

        mMenuPanel.add(icon2);        
        /******* IMAGES ********/
        /***********************/
        
        // MICS Title Card
        JLabel school1 = new JLabel("MANILA INSTITUTE OF");
        JLabel school2 = new JLabel("COMPUTER STUDIES");
        
        school1.setFont(font4);
        school2.setFont(font4);
        
        school1.setForeground(MICSColor1);
        school2.setForeground(MICSColor1);
        
        school1.setBounds(400, 45, 370, 30);
        school2.setBounds(400, 85, 370, 30);
        
        mMenuPanel.add(school1);
        mMenuPanel.add(school2);
        
        displaySchedule();      
        
        
        // STUDENT NAME
        String name = container.getStudentList().get(container.getMarker()).getlName() + ", " +
        				container.getStudentList().get(container.getMarker()).getfName() + " " +
        				 container.getStudentList().get(container.getMarker()).getmName().charAt(0) + ".";
        JLabel studentName = new JLabel(name);
        studentName.setFont(font2);
        studentName.setForeground(MICSColor2);
        studentName.setHorizontalAlignment(SwingConstants.CENTER);
        studentName.setVerticalAlignment(SwingConstants.CENTER);
        studentName.setBounds(10, 180, 230, 30);
        mMenuPanel.add(studentName);
        
        // STUDENT NUMBER
        JLabel studentNumber = new JLabel(container.getStudentList().get(container.getMarker()).getstudNum());
        studentNumber.setFont(font3);
        studentNumber.setForeground(MICSColor2);
        studentNumber.setHorizontalAlignment(SwingConstants.CENTER);
        studentNumber.setVerticalAlignment(SwingConstants.CENTER);
        studentNumber.setBounds(25, 200, 200, 30);
        mMenuPanel.add(studentNumber);
        
        /******* BUTTONS FOR NAVIGATION ********/
        
        uploadButton.setBounds(50, 148, 150, 25);
        uploadButton.setBackground(MICSColor2);
        uploadButton.setForeground(MICSColor1);
        uploadButton.setFocusPainted(false);
        uploadButton.setFont(font1);
        uploadButton.addActionListener(this);
        mMenuPanel.add(uploadButton);
        
        displayButton.setBounds(25, 260, 200, 25);
        displayButton.setBackground(MICSColor2);
        displayButton.setForeground(MICSColor1);
        displayButton.setFocusPainted(false);
        displayButton.setFont(font1);
        displayButton.addActionListener(this);
        mMenuPanel.add(displayButton);
        
        updateButton.setBounds(25, 290, 200, 25);
        updateButton.setBackground(MICSColor2);
        updateButton.setForeground(MICSColor1);
        updateButton.setFocusPainted(false);
        updateButton.setFont(font1);
        updateButton.addActionListener(this);
        mMenuPanel.add(updateButton);
        
        enrollButton.setBounds(25, 320, 200, 25);
        enrollButton.setBackground(MICSColor2);
        enrollButton.setForeground(MICSColor1);
        enrollButton.setFocusPainted(false);
        enrollButton.setFont(font1);
        enrollButton.addActionListener(this);
        mMenuPanel.add(enrollButton);
        
        accountButton.setBounds(25, 350, 200, 25);
        accountButton.setBackground(MICSColor2);
        accountButton.setForeground(MICSColor1);
        accountButton.setFocusPainted(false);
        accountButton.setFont(font1);
        accountButton.addActionListener(this);
        mMenuPanel.add(accountButton);
        
        logOutButton.setBounds(65, 400, 120, 25);
        logOutButton.setForeground(Color.white);
        logOutButton.setBackground(color3);
        logOutButton.setFocusPainted(false);
        logOutButton.setFont(font1);
        logOutButton.addActionListener(this);
        mMenuPanel.add(logOutButton);
        
        saveButton.setBounds(550, 435, 200, 20);
        saveButton.setBackground(MICSColor1);
        saveButton.setForeground(MICSColor2);
        saveButton.setFocusPainted(false);
        saveButton.setFont(font1);
        saveButton.addActionListener(this);
        mMenuPanel.add(saveButton);
        
        // BACKGROUND COLOR
        separator1.setBounds(0, 0, 250, 500);
        separator1.setBackground(MICSColor1);
        separator1.setOpaque(true);
        mMenuPanel.add(separator1); 
        
        final JButton udButton = uploadButton; 
        final JButton disButton = displayButton; 
        final JButton udtButton = updateButton; 
        final JButton enButton = enrollButton;  
        final JButton accButton = accountButton;  
        final JButton logButton = logOutButton; 
        final JButton svButton = saveButton;
        

        MouseListener animationListener = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	udButton.setForeground(Color.ORANGE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	udButton.setForeground(MICSColor1);
            }
        }; 
        MouseListener disanimationListener = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	disButton.setForeground(Color.ORANGE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	disButton.setForeground(MICSColor1);
            }
        };   
        MouseListener udtanimationListener = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	udtButton.setForeground(Color.ORANGE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	udtButton.setForeground(MICSColor1);
            }
        }; 
        MouseListener enanimationListener = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	enButton.setForeground(Color.ORANGE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	enButton.setForeground(MICSColor1);
            }
        };  
        MouseListener accanimationListener = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	accButton.setForeground(Color.ORANGE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	accButton.setForeground(MICSColor1);
            }
        };  
        MouseListener loganimationListener = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	logButton.setForeground(Color.ORANGE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	logButton.setForeground(MICSColor2);
            }
        }; 
        MouseListener svanimationListener = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	svButton.setForeground(Color.ORANGE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	svButton.setForeground(MICSColor2);
            }
        };


        uploadButton.addMouseListener(animationListener); 
        displayButton.addMouseListener(disanimationListener); 
        updateButton.addMouseListener(udtanimationListener); 
        enrollButton.addMouseListener(enanimationListener); 
        accountButton.addMouseListener(accanimationListener); 
        logOutButton.addMouseListener(loganimationListener); 
        saveButton.addMouseListener(svanimationListener);
        
        // FRAME ATTRIBUTES
        frame.setSize(800, 500); // Set frame size
		frame.setResizable(false);
    	frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    	frame.addWindowListener(new WindowAdapter() {
        	@Override
            public void windowClosing(WindowEvent e) {
        		
        		general.loadingAnimation("SAVING YOUR DATA...", () -> {

            		fileHandling.saveIndivStudInfo();
            		
            		frame.dispose();
                    
            		// EMPTY THE COURSES LIST OF THE STUDENT
            		container.getStudentList().get(container.getMarker()).getAllCourseList().clear();
            		container.getStudentList().get(container.getMarker()).getCurrentCourseList().clear();		
            		
            		menu.logIn();
                    
                    
    		    });
	                
            }	
        });
    	frame.add(mMenuPanel);
        frame.setVisible(true);  
        
		// SET FRAME TO MIDDLE OF SCREEN
		int frameWidth = frame.getWidth();
        int frameHeight = frame.getHeight();
        int[] xPos = new int[1];
        int[] yPos = new int[1];     
        general.setFrametoMiddle(frameWidth, frameHeight, xPos, yPos);        
        frame.setLocation(xPos[0], yPos[0]);

	}
	
	private void displaySchedule() {
		
		// YEAR AND SEMESTER TAGS DECLARATION
		JLabel yrTag = new JLabel("YEAR: ");
		JLabel semTag = new JLabel("SEMESTER: ");	
		JLabel studStatTag = new JLabel("STATUS: ");
		JLabel yrLab = new JLabel(Integer.toString(container.getStudentList().get(container.getMarker()).getStudYear()));
		JLabel semLab = new JLabel(Integer.toString(container.getStudentList().get(container.getMarker()).getStudSem()));
		JLabel studStatLab = new JLabel(container.getStudentList().get(container.getMarker()).getScholStatus());
		
		// TABLE HEADERS DECLARATION
		JLabel courseCode = new JLabel("SUBJECT CODE");
		JLabel courseUnits = new JLabel("UNITS");
		JLabel courseFaculty = new JLabel("FACULTY NAME");
		JLabel courseSched = new JLabel("SCHEDULE");
		
		// SCHOLASTIC STATUS TAG
		studStatTag.setFont(font1);
		studStatLab.setFont(font1);

		studStatTag.setForeground(MICSColor1);
		studStatTag.setHorizontalAlignment(SwingConstants.CENTER);
		studStatTag.setVerticalAlignment(SwingConstants.CENTER);
		studStatTag.setBounds(280, 140, 100, 25);
		
		studStatLab.setForeground(Color.BLACK);
		studStatLab.setBackground(Color.WHITE);
		studStatLab.setOpaque(true);
		studStatLab.setBounds(365, 140, 130, 25);
		
		// YEAR AND SEMESTER TAGS
		yrTag.setFont(font1);
		yrLab.setFont(font1);
		semTag.setFont(font1);
		semLab.setFont(font1);
		
		yrTag.setForeground(MICSColor1);
		yrTag.setHorizontalAlignment(SwingConstants.CENTER);
		yrTag.setVerticalAlignment(SwingConstants.CENTER);
		yrTag.setBounds(520, 140, 100, 25);
		
		yrLab.setForeground(Color.BLACK);
		yrLab.setBackground(Color.WHITE);
		yrLab.setOpaque(true);
		yrLab.setHorizontalAlignment(SwingConstants.CENTER);
		yrLab.setVerticalAlignment(SwingConstants.CENTER);
		yrLab.setBounds(600, 140, 20, 25);
		
		semTag.setForeground(MICSColor1);
		semTag.setHorizontalAlignment(SwingConstants.CENTER);
		semTag.setVerticalAlignment(SwingConstants.CENTER);
		semTag.setBounds(630, 140, 100, 25);
		
		semLab.setForeground(Color.BLACK);
		semLab.setBackground(Color.WHITE);
		semLab.setOpaque(true);
		semLab.setHorizontalAlignment(SwingConstants.CENTER);
		semLab.setVerticalAlignment(SwingConstants.CENTER);
		semLab.setBounds(730, 140, 20, 25);	
		
		// TABLE HEADERS
		courseCode.setFont(font5);
		courseUnits.setFont(font5);
		courseFaculty.setFont(font5);
		courseSched.setFont(font5);
		
		courseCode.setForeground(Color.WHITE);
		courseCode.setBackground(MICSColor1);
		courseCode.setOpaque(true);
		courseCode.setHorizontalAlignment(SwingConstants.CENTER);
		courseCode.setVerticalAlignment(SwingConstants.CENTER);
		courseCode.setBounds(300, 180, 100, 25);
		
		courseUnits.setForeground(Color.WHITE);
		courseUnits.setBackground(MICSColor1);
		courseUnits.setOpaque(true);
		courseUnits.setHorizontalAlignment(SwingConstants.CENTER);
		courseUnits.setVerticalAlignment(SwingConstants.CENTER);
		courseUnits.setBounds(400, 180, 50, 25);
		
		courseFaculty.setForeground(Color.WHITE);
		courseFaculty.setBackground(MICSColor1);
		courseFaculty.setOpaque(true);
		courseFaculty.setHorizontalAlignment(SwingConstants.CENTER);
		courseFaculty.setVerticalAlignment(SwingConstants.CENTER);
		courseFaculty.setBounds(450, 180, 150, 25);
		
		courseSched.setForeground(Color.WHITE);
		courseSched.setBackground(MICSColor1);
		courseSched.setOpaque(true);
		courseSched.setHorizontalAlignment(SwingConstants.CENTER);
		courseSched.setVerticalAlignment(SwingConstants.CENTER);
		courseSched.setBounds(600, 180, 150, 25);
		
		ArrayList<StudentDetails> studentList = container.getStudentList();
		StudentDetails student = studentList.get(container.getMarker());
		ArrayList<CourseDetails> courseList = student.getCurrentCourseList();
		
		int i = 205;
		for(CourseDetails course : courseList) {
			
			JLabel code = new JLabel(course.getCourseCode());
			JLabel unit = new JLabel(String.valueOf(course.getCourseUnits()));
			JLabel faculty = new JLabel(course.getCourseFaculty());
			JLabel sched = new JLabel(course.getCourseSched());	
			
			code.setFont(font5);
			unit.setFont(font5);
			faculty.setFont(font5);
			sched.setFont(font5);
			
			code.setBackground(Color.white);
			code.setBorder(border);
			code.setOpaque(true);
			code.setHorizontalAlignment(SwingConstants.CENTER);
			code.setVerticalAlignment(SwingConstants.CENTER);
			code.setBounds(300, i, 100, 15);
			
			unit.setBackground(Color.white);
			unit.setBorder(border);
			unit.setOpaque(true);
			unit.setHorizontalAlignment(SwingConstants.CENTER);
			unit.setVerticalAlignment(SwingConstants.CENTER);
			unit.setBounds(400, i, 50, 15);
			
			faculty.setBackground(Color.white);
			faculty.setBorder(border);
			faculty.setOpaque(true);
			faculty.setHorizontalAlignment(SwingConstants.CENTER);
			faculty.setVerticalAlignment(SwingConstants.CENTER);
			faculty.setBounds(450, i, 150, 15);
			
			sched.setBackground(Color.white);
			sched.setBorder(border);
			sched.setOpaque(true);
			sched.setHorizontalAlignment(SwingConstants.CENTER);
			sched.setVerticalAlignment(SwingConstants.CENTER);
			sched.setBounds(600, i, 150, 15);
			
			mMenuPanel.add(code);
			mMenuPanel.add(unit);
			mMenuPanel.add(faculty);
			mMenuPanel.add(sched);
			
			i += 15;
			
		}
		
		mMenuPanel.add(studStatTag);
		mMenuPanel.add(studStatLab);
		
		mMenuPanel.add(yrTag);
		mMenuPanel.add(yrLab);
		mMenuPanel.add(semTag);
		mMenuPanel.add(semLab);
		
		mMenuPanel.add(courseCode);
		mMenuPanel.add(courseUnits);
		mMenuPanel.add(courseFaculty);
		mMenuPanel.add(courseSched);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		// ArrayList<StudentDetails> studentList = container.getStudentList();
		
        ReturningStudent returningStudent = new ReturningStudent();
		
        String command = e.getActionCommand();

        if (command.equals("View Grades")) {
        	
        	general.loadingAnimation("LOADING...", () -> {
        		
	            general.displayRecords(null);
	            
		    });
        
        } else if (command.equals("Student Information")) {
        	
        	general.loadingAnimation("LOADING...", () -> {
                
        		general.updateStudInfo();
                
		    });
            
        } else if (command.equals("Enrollment")) {
        	
        	if(checkEligibility() == 1) {
        		
	            general.displayRecords(() -> {
	                general.loadingAnimation("PROCESSING NEW SET OF SUBJECTS...", () -> {
	                	
	                	frame.dispose();
	                    returningStudent.selectNewSubs();
	                    
	                    general.displayRecords(() -> {	                    	
	                    	
	                    	general.loadingAnimation("LOADING...", () -> {
	                    		
		                    	general.successEnroll(() -> {
		                    		mainMenu();
		                    	});
		                    	
	                    	});
	                    	
	                    });
	                    
	                });
			    });
	            
        	} else {
        		
        		JOptionPane.showMessageDialog(
        	            null,
        	            "ERROR: Ineligible for enrollment"
        	            + "\nNOTE: Please finish the current semester first",
        	            "Error",
        	            JOptionPane.ERROR_MESSAGE);
        		
        	}
        	
        } else if(command.equals("Account Settings")) {
        	
        	general.loadingAnimation("LOADING...", () -> {
        		
        		general.showAccount();
                
		    });
        	
        } else if(command.equals("SAVE SCHEDULE")) {
        	
        	general.loadingAnimation("LOADING...", () -> {
        		
        		fileHandling.saveStudSched();
                
		    });
        	
        }else if (command.equals("LOG-OUT")) {
        	
        	general.loadingAnimation("SAVING YOUR DATA...", () -> {

        		fileHandling.saveIndivStudInfo();
        		
        		frame.dispose();
                
        		// EMPTY THE COURSES LIST OF THE STUDENT
        		container.getStudentList().get(container.getMarker()).getAllCourseList().clear();
        		container.getStudentList().get(container.getMarker()).getCurrentCourseList().clear();		
        		
        		menu.logIn();
                
                
		    });
        	
        } else if(command.equals("Upload Image")) {
        	
        	JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select an image to upload");

            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
            	frame.dispose(); 
            	File selectedFile = fileChooser.getSelectedFile();
                
                try {
                	// Copy the selected file to the Directory Path
                	String DirPath = "DATABASE/2/";
                	String newFileDir;
                	String studID = container.getStudentList().get(container.getMarker()).getStudNum();
                 	
                	char[] decoder = studID.toCharArray();
                 	   	
                	for(int i = 0; i < decoder.length; i++) {
                 		
                 		if(i < 4)
                 			decoder[i] += 5;
                 		else if(i >= 4 && i < 10)
                 			decoder[i] += 17;
                 		
                 	}	
                	studID = new String(decoder);
                    String fileExtension = getFileExtension(selectedFile.getName());
                    newFileDir = DirPath + studID + fileExtension;                   
                    File fp = new File(newFileDir);
                    Files.copy(selectedFile.toPath(), fp.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    
                     CurrImage = newFileDir;
                     mainMenu();
                     
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error uploading image!");
                }
            }

        }
    }
	
	private String getFileExtension(String fileName) {
		
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex != -1) {
            return fileName.substring(dotIndex);
        }
        return "";
    }
	
	private void retrieveStudentImage() {
		
		String directoryPath = "DATABASE/2/";
		String[] extensions = {".jpg", ".jpeg", ".png"};
		String fullFile = null;
		
		String studID = container.getStudentList().get(container.getMarker()).getStudNum();
    	
    	char[] decoder = studID.toCharArray();
    	   	
    	for(int i = 0; i < decoder.length; i++) {
    		
    		if(i < 4)
    			decoder[i] += 5;
    		else if(i >= 4 && i < 10)
    			decoder[i] += 17;
    		
    	}	
    	
    	studID = new String(decoder);

		
		for(int i = 0; i < 3; i++) {
			
			fullFile = directoryPath + studID + extensions[i];

	        File file = new File(fullFile);
			
			if(file.exists()) {
				CurrImage = fullFile;
				break;
		        
			}
			
		}

	}
	
	private void displayImage() {
	   
	        ImageIcon studPic = new ImageIcon(CurrImage);
	        JLabel icon1 = new JLabel(studPic);
	        Image image1 = studPic.getImage();
	        Image scaledImage1 = image1.getScaledInstance(150, 150, Image.SCALE_SMOOTH);

	        icon1.setIcon(new ImageIcon(scaledImage1));
	        icon1.setBounds(50, 20, 150, 150);
	        icon1.setBackground(MICSColor2);
	        icon1.setOpaque(false);

	        mMenuPanel.add(icon1);
        
	        mMenuPanel.revalidate();
	        mMenuPanel.repaint();
	}
	
	private int checkEligibility() {
		
		ArrayList<StudentDetails> studentList = container.getStudentList();
		ArrayList<CourseDetails> courses = studentList.get(container.getMarker()).getCurrentCourseList();
		
		for(int i = 0; i < courses.size(); i++)			
			if(courses.get(i).getCourseGrade() == 0.00)
				return -1;
		
		return 1;
		
	} 
	
	public class RoundedButton extends JButton {
        /**
         *
         */
        private static final long serialVersionUID = 1L;
        private Shape shape;

        public RoundedButton(String text) {
            super(text);
            setOpaque(false);
            setBorderPainted(false);
        }

        protected void paintComponent(Graphics g) {
            g.setColor(getBackground());
            g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
            super.paintComponent(g);
        }

        protected void paintBorder(Graphics g) {
            // No color
        }

        public boolean contains(int x, int y) {
            if (shape == null || !shape.getBounds().equals(getBounds())) {
                shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
            }
            return shape.contains(x, y);
        }
    }

}
