import java.awt.*; 
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;  

public class NewStudent {
	
	private General general;
	private StudentListContainer container;
	private Menu menu;
	
    private JFrame frame;
    private JPanel menuPanel;
    private JPanel resultPanel;
    private JPanel nextPagePanel;  
    private JTextField inputExamNumField;
    private JPanel studentDetailsPanel;
    
    private String studNum;
    private String studPass;
    
    private JTextField nameTextField; // Last Name
    private JTextField givenNameTextField; // First Name
    private JTextField middleNameTextField; // Middle Name
    private JRadioButton maleRadioButton; // For male
    private JRadioButton femaleRadioButton; // For female
    private JTextField birthdateTextField; // Birthday
    private JTextField phoneNumTextField; // Student's Phone Number
    private JTextField emailAddTextField; // Student's Email Address
    private JTextField addrssTextField; // Student's Address
    private JTextField gemailAddTextField; // Guardian's Email Address
    private JTextField gPhoneNumTextField; // Guardian's Phone Number
    
    private Runnable callback;
    
    private boolean showResultPageCalled = false; // Results page initially not visited
    private boolean showNextPageCalled = false; // Message page initially not visited
    private boolean studentDetPageCalled = false; // Student details page initially not visited

    public static class examineeDet {
    	
    	String examineeNo;
    	double gr1;
    	double gr2;
    	double gr3;
    	
    }
    
    private ArrayList<examineeDet> examineeList;
    private int position; 
    
    // General Usage
    private Font header = new Font("Arial", Font.BOLD, 28);
    private Font btn = new Font("Arial", Font.BOLD, 14);
    private Font newf = new Font("Arial", Font.BOLD + Font.ITALIC, 14);
    private Color MICSColor1 = new Color(126, 2, 2); // medyo maroon
    private Color MICSColor2 = new Color(241, 234, 213); // beige
    private Color MICSColor3 = Color.decode("#FFFFFF");
    private Color buttoncolor = Color.decode("#472213");
    
    public NewStudent() {
    	
    	general = new General();
    	container = StudentListContainer.getInstance();
    	examineeList = new ArrayList<>();
    	menu = new Menu();
    	
    }
    
    public void entry(Runnable callback) {
    	
        this.callback = callback;
        	
        retrieveExamInfo();
        	
        JLabel examNumLabel = new JLabel();
        JButton searchButton = new JButton();
        	
        frame = new JFrame("MANILA INSTITUTE OF COMPUTER STUDIES");
        menuPanel = new JPanel();
        resultPanel = new JPanel();
        nextPagePanel = new JPanel(); 
        studentDetailsPanel = new JPanel(); 

        frame.setSize(750, 450);  
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        sizeFrame();
            
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (!showResultPageCalled && !showNextPageCalled && !studentDetPageCalled) {
                    general.loadingAnimation("LOADING...", () -> {
                        showResultPageCalled = showNextPageCalled = studentDetPageCalled = false;
                        frame.dispose();
                        menu.logIn();
                    });
                }
            }	
        });
            
        frame.add(menuPanel); 
            
        menuPanel.setSize(375, 225);
        menuPanel.setLayout(null);
        menuPanel.setBackground(MICSColor1);  
            
        /* STUDENT PORTAL LABEL */
        JLabel exLabel = new JLabel("EXAMINATION RESULT");
        exLabel.setFont(header);
        exLabel.setForeground(MICSColor2);
        exLabel.setBounds(400, 50, 400, 40);
        menuPanel.add(exLabel);

        // EXAMINEE LABEL
        examNumLabel = new JLabel("Examinee Number: ");
        examNumLabel.setBounds(512, 155, 160, 36); 
        examNumLabel.setForeground(MICSColor2);
        menuPanel.add(examNumLabel);

        // SEARCH BUTTON
        searchButton = new RoundedButton("Search"); 
        searchButton.setFont(btn);
        searchButton.setForeground(MICSColor3);
        searchButton.setBackground(buttoncolor);
        searchButton.setBounds(460, 200, 200, 36);
        menuPanel.add(searchButton); 
        
        final JButton finalSearchButton = searchButton;

        MouseListener animationListener = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                finalSearchButton.setForeground(Color.ORANGE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                finalSearchButton.setForeground(MICSColor3);
            }
        };

        searchButton.addMouseListener(animationListener);
      
        // EXAMINEE TEXTBOX
        inputExamNumField = new RoundedJTextField(10);
        inputExamNumField.setBounds(460, 128, 200, 36); 
        inputExamNumField.setBackground(MICSColor3);
        menuPanel.add(inputExamNumField); 
         
        JLabel quote = new JLabel("\"Dare to achieve\" -unknown");
        quote.setFont(newf);
        quote.setForeground(MICSColor2);
        quote.setBounds(460, 280, 200, 20);
        quote.setHorizontalAlignment(SwingConstants.CENTER);
        menuPanel.add(quote);

        // ACTIONLISTENER FOR SEARCH BUTTON
        searchButton.addActionListener(new ActionListener() {
            	
            public void actionPerformed(ActionEvent e) {
                position = locateExamineeNo(inputExamNumField.getText());
                general.loadingAnimation("VERIFYING...", () -> {
                    if (position > -1) {
                        showResultPage();
                    } else {
                        JOptionPane.showMessageDialog(null, "EXAMINEE NUMBER INVALID", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                });
            }
        });
            
        /***********************************/
        /************FOR ICONS**************/
        ImageIcon MICSlogo = new ImageIcon("images/MICSlogo.png");
        JLabel icon = new JLabel(MICSlogo);
        JLabel school = new JLabel("<html>MANILA INSTITUTE OF<br>COMPUTER STUDIES</html>");
        JLabel blank = new JLabel();
        Image image = MICSlogo.getImage();
        Image scaledImage = image.getScaledInstance(250, 250, Image.SCALE_SMOOTH);

        school.setText("<html><div style='text-align: center;'>MANILA INSTITUTE OF<br>COMPUTER STUDIES</div></html>");
        school.setFont(header);
        school.setForeground(MICSColor1);
        school.setBounds(10, 45, 360, 60);
        school.setOpaque(false);
        school.setHorizontalAlignment(SwingConstants.CENTER);
        school.setVerticalAlignment(SwingConstants.CENTER);

        icon.setIcon(new ImageIcon(scaledImage));
        icon.setBounds(0, 50, 370, 400);
        icon.setBackground(MICSColor2);
        icon.setOpaque(false);

        blank.setBounds(0, 0, 375, 415);
        blank.setBackground(MICSColor2);
        blank.setOpaque(true);
        menuPanel.add(school);
        menuPanel.add(icon);
        menuPanel.add(blank);

        menuPanel.setComponentZOrder(school, 0);
        menuPanel.setComponentZOrder(icon, 1);

        /************FOR ICONS**************/
        /***********************************/ 
        frame.setBackground(MICSColor1); 
            
    }

    private void showResultPage() {
        menuPanel.setVisible(false);
        resultPanel.setLayout(null);

        showResultPageCalled = true; // Result page already called  
        

        JLabel outputExamNumLabel = new JLabel();
        JButton continueButton = new JButton();

        // GENERAL USAGE
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        Color TUPColor1 = new Color(126, 2, 2);
        Font font1 = new Font("Arial", Font.BOLD, 20); 
       
 
        ImageIcon MICSlogo = new ImageIcon("images/MICSlogo.png");
        Image scaledImage = MICSlogo.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
        logoLabel.setBounds(100, 15, 70, 60);
       
        
        frame.setSize(1350, 700);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.getContentPane().add(resultPanel);
        sizeFrame(); // Set frame at center of screen

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                if (!showNextPageCalled && !studentDetPageCalled) {

                    general.loadingAnimation("LOADING...", () -> {

                        showResultPageCalled = showNextPageCalled = studentDetPageCalled = false;
                        frame.dispose();
                        showNextPage();

                    });

                }

            }
        });

        JLabel desLabel = new JLabel("MANILA INSTITUTE OF COMPUTER STUDIES");
        desLabel.setBounds(0, 0, 1350, 90);
        desLabel.setHorizontalAlignment(SwingConstants.CENTER);
        desLabel.setVerticalAlignment(SwingConstants.CENTER);
        desLabel.setFont(font1);
        desLabel.setBackground(TUPColor1);
        desLabel.setForeground(Color.WHITE);
        desLabel.setOpaque(true);
        desLabel.setBorder(border);
        resultPanel.add(desLabel);

        JLabel tLabel = new JLabel("NOTICE OF RATING");
        tLabel.setBounds(0, 70, 1350, 90);
        tLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tLabel.setVerticalAlignment(SwingConstants.CENTER);
        tLabel.setFont(font1);
        tLabel.setForeground(TUPColor1);
        resultPanel.add(tLabel);

        JLabel headLabel = new JLabel("Rating Per Competency Areas");
        headLabel.setBounds(0, 230, 1350, 90);
        headLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headLabel.setVerticalAlignment(SwingConstants.CENTER);
        headLabel.setFont(font1);
        headLabel.setForeground(TUPColor1);
        resultPanel.add(headLabel);

        // Congratulations message
        String congratulationsMessage = "<br><br><br>Congratulations on passing the MICS examination! Your competency rating displayed exceptional performance and highlights your dedication and hard work. We believe this achievement is just the beginning of a successful journey for you. Keep up the excellent work!<br><br><br><br><br><br><br><br><br><br><br><br><br><br>Best regards,<br><br>Andrew Bazzi<br>CS Department Head<br>Manila Institute of Computer Studies";

        JLabel congratulationsLabel = new JLabel("<html><body style='width: 1000px; text-align: justified;'>" + congratulationsMessage + "</body></html>");
        congratulationsLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        Dimension resultPanelSize = resultPanel.getSize();

        int x = (resultPanelSize.width - congratulationsLabel.getPreferredSize().width) / 1;
        int y = (resultPanelSize.height - congratulationsLabel.getPreferredSize().height) / 2;

        congratulationsLabel.setBounds(x, y, congratulationsLabel.getPreferredSize().width, congratulationsLabel.getPreferredSize().height);

        resultPanel.add(congratulationsLabel);

        // TABLE HEADERS
        JLabel examineeNumLabel = new JLabel("EXAMINEE NO.");
        JLabel verbalLabel = new JLabel("Verbal");
        JLabel analyticalLabel = new JLabel("Analytical");
        JLabel numericalLabel = new JLabel("Numerical");
        JLabel generalInfoLabel = new JLabel("General Info.");

        /* TABLE HEADER ENTRIES */
        // EXAMINEE NO.
        examineeNumLabel.setBounds(380, 300, 130, 40);
        examineeNumLabel.setHorizontalAlignment(SwingConstants.CENTER);
        examineeNumLabel.setVerticalAlignment(SwingConstants.CENTER);
        examineeNumLabel.setBackground(TUPColor1);
        examineeNumLabel.setForeground(Color.WHITE);
        examineeNumLabel.setOpaque(true);
        examineeNumLabel.setBorder(border);
        resultPanel.add(examineeNumLabel);
        // VERBAL SCORE
        verbalLabel.setBounds(510, 300, 110, 40);
        verbalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        verbalLabel.setVerticalAlignment(SwingConstants.CENTER);
        verbalLabel.setBackground(TUPColor1);
        verbalLabel.setForeground(Color.WHITE);
        verbalLabel.setOpaque(true);
        verbalLabel.setBorder(border);
        resultPanel.add(verbalLabel);
        // ANALYTICAL SCORE
        analyticalLabel.setBounds(620, 300, 110, 40);
        analyticalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        analyticalLabel.setVerticalAlignment(SwingConstants.CENTER);
        analyticalLabel.setBackground(TUPColor1);
        analyticalLabel.setForeground(Color.WHITE);
        analyticalLabel.setOpaque(true);
        analyticalLabel.setBorder(border);
        resultPanel.add(analyticalLabel);
        // NUMERICAL SCORE
        numericalLabel.setBounds(730, 300, 110, 40);
        numericalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        numericalLabel.setVerticalAlignment(SwingConstants.CENTER);
        numericalLabel.setBackground(TUPColor1);
        numericalLabel.setForeground(Color.WHITE);
        numericalLabel.setOpaque(true);
        numericalLabel.setBorder(border);
        resultPanel.add(numericalLabel);
        // GENERAL INFO. SCORE
        generalInfoLabel.setBounds(840, 300, 110, 40);
        generalInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        generalInfoLabel.setVerticalAlignment(SwingConstants.CENTER);
        generalInfoLabel.setBackground(TUPColor1);
        generalInfoLabel.setForeground(Color.WHITE);
        generalInfoLabel.setOpaque(true);
        generalInfoLabel.setBorder(border);
        resultPanel.add(generalInfoLabel);

        String resultText;
        examineeDet examinee = examineeList.get(position);
        resultText = examinee.examineeNo + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                + examinee.gr1 + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                + examinee.gr2 + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                + examinee.gr3;
        resultText = "<html><pre style='border: 1px solid black;'>" + resultText.replace("\n", "<br>") + "</pre></html>";

        outputExamNumLabel = new JLabel(resultText);
        outputExamNumLabel.setFont(new Font("Arial", Font.BOLD, 16));
        outputExamNumLabel.setBounds(380, 335, 570, 80);
        outputExamNumLabel.setHorizontalAlignment(SwingConstants.CENTER);
        outputExamNumLabel.setVerticalAlignment(SwingConstants.CENTER);
        outputExamNumLabel.setBackground(Color.WHITE);
        outputExamNumLabel.setForeground(Color.BLACK);
        outputExamNumLabel.setOpaque(true);
        outputExamNumLabel.setBorder(border);
        resultPanel.add(outputExamNumLabel);

        continueButton = new RoundedButton("Continue");  
        continueButton.setFont(btn);
        continueButton.setForeground(MICSColor3);
        continueButton.setBackground(buttoncolor);
        continueButton.setBounds(610, 600, 200, 36); 
        	
        final JButton finalSearchButton = continueButton;

        MouseListener animationListener = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                finalSearchButton.setForeground(Color.ORANGE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                finalSearchButton.setForeground(MICSColor3);
            }
        }; 
        
        continueButton.addMouseListener(animationListener);
        continueButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                general.loadingAnimation("LOADING...", () -> {

                    frame.dispose();
                    showNextPage();

                });

            }
        });

        resultPanel.add(continueButton); 
        
        resultPanel.add(logoLabel);
        resultPanel.add(desLabel);

        resultPanel.setVisible(true);
        resultPanel.setBackground(MICSColor2);
    }

    private void showNextPage() {

        resultPanel.setVisible(false);
        nextPagePanel.setLayout(null);

        JButton yesButton = new JButton();
        JButton noButton = new JButton();

        // GENERAL USAGE
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        Color TUPColor1 = new Color(126, 2, 2);
        Font font1 = new Font("Arial", Font.BOLD, 15);    

        JLabel noteLabel = new JLabel("MANILA INSTITUTE OF COMPUTER STUDIES");
        nextPagePanel.add(noteLabel);
 
        showNextPageCalled = true;
        
        // Set frame size
        frame.setSize(800, 500); 
        frame.setResizable(false);
        frame.setVisible(true);
        frame.getContentPane().add(nextPagePanel);
        sizeFrame();

        frame.addWindowListener(new WindowAdapter() {
        	@Override
            public void windowClosing(WindowEvent e) {
        		if (!studentDetPageCalled) {
        			
        			general.loadingAnimation("LOADING...", () -> {
                		
                		showResultPageCalled = showNextPageCalled = studentDetPageCalled = false;
                        frame.dispose();
                        entry(null);
                    
                	});
                    
                }
            }	
        });
        
        // NOTE
        noteLabel.setBounds(140, 80, 535, 40);
        noteLabel.setFont(font1);
        noteLabel.setHorizontalAlignment(SwingConstants.CENTER);
        noteLabel.setVerticalAlignment(SwingConstants.CENTER);
        noteLabel.setBackground(TUPColor1);
        noteLabel.setForeground(Color.WHITE);
        noteLabel.setOpaque(true);
        noteLabel.setBorder(border);
        nextPagePanel.add(noteLabel);

        String message = "<html><pre>&nbsp;&nbsp;Greetings, Students!<br><br>"
                + "&nbsp;&nbsp;&nbsp;&nbsp;The Manila Institute of Computer Studies does not recognize credit <br>"
                + "&nbsp;&nbsp;units obtained from other educational institutions. <br>"
                + "&nbsp;&nbsp;Consequently, you are required to enroll at the first-year level of the program.<br><br>"
                + "&nbsp;&nbsp;Would you like to proceed with the admission process under these terms?</pre></html>";

        JLabel messageLabel = new JLabel(message);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 12));
        messageLabel.setBounds(140, 120, 535, 155);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setVerticalAlignment(SwingConstants.CENTER);
        messageLabel.setBackground(Color.WHITE);
        messageLabel.setForeground(Color.BLACK);
        messageLabel.setOpaque(true);
        messageLabel.setBorder(border);

        nextPagePanel.add(messageLabel);

        yesButton = new RoundedButton("Yes"); 
        yesButton.setFont(btn);
        yesButton.setForeground(MICSColor3); 
        yesButton.setBounds(290, 330, 100, 36);
        yesButton.setBackground(buttoncolor);
        noButton = new RoundedButton("No"); 
        noButton.setFont(btn);
        noButton.setForeground(MICSColor3);
        noButton.setBackground(buttoncolor);
        noButton.setBounds(420, 330, 100, 36); 
        
        final JButton finalYesButton = yesButton;
        final JButton finalNoButton = noButton;

        MouseListener yesAnimationListener = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	finalYesButton.setForeground(Color.ORANGE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	finalYesButton.setForeground(MICSColor3);
            }
        };

        MouseListener noAnimationListener = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	finalNoButton.setForeground(Color.ORANGE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	finalNoButton.setForeground(MICSColor3);
            }
        };

        yesButton.addMouseListener(yesAnimationListener);
        noButton.addMouseListener(noAnimationListener);

        yesButton.addActionListener(new ActionListener() {
        	
            public void actionPerformed(ActionEvent e) {
            	
            	general.loadingAnimation("LOADING...", () -> {
            		
            		frame.dispose();
                    studentDetPage();
                    
                });
                
            }
            
        });

        noButton.addActionListener(new ActionListener() {
        	
            public void actionPerformed(ActionEvent e) {
            	
            	general.loadingAnimation("LOADING...", () -> {
            		
            		showResultPageCalled = showNextPageCalled = studentDetPageCalled = false;
                    frame.dispose();
                    entry(null);
                
            	});
                
            }
            
        });

        nextPagePanel.add(yesButton);
        nextPagePanel.add(noButton);
        nextPagePanel.setVisible(true); 
        nextPagePanel.setBackground(MICSColor2);

    }
    
    private void studentDetPage() {

        studentDetPageCalled = true; // Student details portal called

        nextPagePanel.setVisible(false);
        studentDetailsPanel.setLayout(null);

        JButton submitButton = new JButton();

        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        Color TUPColor1 = new Color(126, 2, 2);
        Font font1 = new Font("Arial", Font.BOLD, 30);
        Font font2 = new Font("Arial", Font.BOLD, 15);
        Font font3 = new Font("Arial", Font.PLAIN, 10);

        frame.setSize(840, 700);
        frame.getContentPane().add(studentDetailsPanel);
        sizeFrame();
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                general.loadingAnimation("LOADING...", () -> {

                    showResultPageCalled = showNextPageCalled = studentDetPageCalled = false;
                    studentDetPageCalled = false;
                    frame.dispose();
                    menu.logIn();

                });

            }
        });

        JLabel titleLabel = new JLabel("ENROLLMENT FORM");
        titleLabel.setBounds(0, 0, 840, 90);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(font1);
        titleLabel.setBackground(TUPColor1);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setOpaque(true);
        titleLabel.setBorder(border);
        studentDetailsPanel.add(titleLabel);

        JLabel studDetLabel = new JLabel("Student's Details");
        studDetLabel.setBounds(0, 90, 840, 40);
        studDetLabel.setHorizontalAlignment(SwingConstants.CENTER);
        studDetLabel.setVerticalAlignment(SwingConstants.CENTER);
        studDetLabel.setFont(font2);
        studDetLabel.setBackground(Color.WHITE);
        studDetLabel.setForeground(Color.BLACK);
        studDetLabel.setOpaque(true);
        studDetLabel.setBorder(border);
        studentDetailsPanel.add(studDetLabel);

        JLabel studsNameTitle = new JLabel("Student's Name");
        studsNameTitle.setBounds(150, 140, 150, 35);
        studentDetailsPanel.add(studsNameTitle);

        JLabel nameLabel = new JLabel("Last Name");
        nameLabel.setBounds(150, 187, 150, 35);
        nameLabel.setFont(font3);
        studentDetailsPanel.add(nameLabel);

        nameTextField = new RoundedJTextField(32);
        nameTextField.setBackground(MICSColor3);
        nameTextField.setBounds(150, 165, 150, 35);
        studentDetailsPanel.add(nameTextField);

        // Given name label and input field
        JLabel givenNameLabel = new JLabel("Given Name");
        givenNameLabel.setBounds(345, 187, 150, 35);
        givenNameLabel.setFont(font3);
        studentDetailsPanel.add(givenNameLabel);

        givenNameTextField = new RoundedJTextField(32);
        givenNameTextField.setBackground(MICSColor3);
        givenNameTextField.setBounds(345, 165, 150, 35);
        studentDetailsPanel.add(givenNameTextField);

        JLabel middleNameLabel = new JLabel("Middle Name");
        middleNameLabel.setBounds(535, 187, 150, 35);
        middleNameLabel.setFont(font3);
        studentDetailsPanel.add(middleNameLabel);

        middleNameTextField = new RoundedJTextField(32);
        middleNameTextField.setBackground(MICSColor3);
        middleNameTextField.setBounds(535, 165, 150, 35);
        studentDetailsPanel.add(middleNameTextField);

        JLabel sexLabel = new JLabel("Sex");
        sexLabel.setBounds(150, 220, 150, 35);
        studentDetailsPanel.add(sexLabel);

        // Sex Label
        maleRadioButton = new JRadioButton("Male");
        femaleRadioButton = new JRadioButton("Female");

        // Set the bounds for the radio buttons
        maleRadioButton.setBounds(150, 245, 70, 35); 
        maleRadioButton.setBackground(MICSColor2);
        femaleRadioButton.setBounds(230, 245, 80, 35); 
        femaleRadioButton.setBackground(MICSColor2);

        // Create a ButtonGroup and add the radio buttons
        ButtonGroup sexButtonGroup = new ButtonGroup();
        sexButtonGroup.add(maleRadioButton);
        sexButtonGroup.add(femaleRadioButton);

        // Add the radio buttons to the panel
        studentDetailsPanel.add(maleRadioButton);
        studentDetailsPanel.add(femaleRadioButton);

        JLabel birthdateLabel = new JLabel("Date of Birth");
        birthdateLabel.setBounds(345, 220, 150, 35);
        studentDetailsPanel.add(birthdateLabel);

        JLabel bdayLabel = new JLabel("[MM-DD-YYYY]");
        bdayLabel.setBounds(345, 267, 150, 35);
        bdayLabel.setFont(font3);
        studentDetailsPanel.add(bdayLabel);

        birthdateTextField = new RoundedJTextField(32);
        birthdateTextField.setBackground(MICSColor3);
        birthdateTextField.setBounds(345, 245, 150, 35);
        studentDetailsPanel.add(birthdateTextField);

        JLabel phoneNumLabel = new JLabel("Phone No.");
        phoneNumLabel.setBounds(535, 300, 150, 35);
        studentDetailsPanel.add(phoneNumLabel);

        phoneNumTextField = new RoundedJTextField(12);
        phoneNumTextField.setBackground(MICSColor3);
        phoneNumTextField.setBounds(535, 325, 150, 35);
        studentDetailsPanel.add(phoneNumTextField);

        JLabel emailAddLabel = new JLabel("Email");
        emailAddLabel.setBounds(150, 300, 150, 35);
        studentDetailsPanel.add(emailAddLabel);

        JLabel emailFormatLabel = new JLabel("example@example.com");
        emailFormatLabel.setBounds(150, 347, 150, 35);
        emailFormatLabel.setFont(font3);
        studentDetailsPanel.add(emailFormatLabel);

        emailAddTextField = new RoundedJTextField(32);
        emailAddTextField.setBackground(MICSColor3);
        emailAddTextField.setBounds(150, 325, 345, 35);
        studentDetailsPanel.add(emailAddTextField);

        JLabel addrssLabel = new JLabel("Student's Address");
        addrssLabel.setBounds(150, 390, 150, 35);
        studentDetailsPanel.add(addrssLabel);

        JLabel addrssFormatLabel = new JLabel("Street Address");
        addrssFormatLabel.setBounds(150, 437, 150, 35);
        addrssFormatLabel.setFont(font3);
        studentDetailsPanel.add(addrssFormatLabel);

        addrssTextField = new RoundedJTextField(50);
        addrssTextField.setBackground(MICSColor3);
        addrssTextField.setBounds(150, 415, 535, 35);
        studentDetailsPanel.add(addrssTextField);

        JLabel gemailAddLabel = new JLabel("Guardian's Email");
        gemailAddLabel.setBounds(150, 480, 150, 35);
        studentDetailsPanel.add(gemailAddLabel);

        JLabel gemailFormatLabel = new JLabel("example@example.com");
        gemailFormatLabel.setBounds(150, 527, 150, 35);
        gemailFormatLabel.setFont(font3);
        studentDetailsPanel.add(gemailFormatLabel);

        gemailAddTextField = new RoundedJTextField(50);
        gemailAddTextField.setBackground(MICSColor3);
        gemailAddTextField.setBounds(150, 505, 345, 35);
        studentDetailsPanel.add(gemailAddTextField);

        JLabel gPhoneNumLabel = new JLabel("Guardian's Phone No.");
        gPhoneNumLabel.setBounds(535, 480, 150, 35);
        studentDetailsPanel.add(gPhoneNumLabel);

        gPhoneNumTextField = new RoundedJTextField(12);
        gPhoneNumTextField.setBackground(MICSColor3);
        gPhoneNumTextField.setBounds(535, 505, 150, 35);
        studentDetailsPanel.add(gPhoneNumTextField);

        submitButton = new RoundedButton("Submit");
        submitButton.setFont(btn);
        submitButton.setForeground(MICSColor3);
        submitButton.setBackground(buttoncolor);
        submitButton.setBounds(300, 590, 200, 36); 
        
        final JButton finalSearchButton = submitButton; 

        MouseListener animationListener = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                finalSearchButton.setForeground(Color.ORANGE); 
            }

            @Override
            public void mouseExited(MouseEvent e) {
                finalSearchButton.setForeground(MICSColor3); 
            }
        }; 
         
        submitButton.addMouseListener(animationListener);
        
        // ONCE USER SUBMITS HIS/HER PERSONAL DETAILS, PROCEED
        // TO STUDENT ACCOUNT NUMBER AND PASSWORD GENERATOR
        submitButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                // NEXT PAGE

                accNumGen();
                passGen();

                general.loadingAnimation("GENERATING STUDENT ACCOUNT AND PASSWORD...", () -> {

                    displayAccandPass(() -> {

                        saveNewStudent();

                        general.loadingAnimation("SAVING...", () -> {

                            frame.dispose();
                            examineeList.remove(position);
                            saveExamInfo();

                            if (callback != null) {
                                callback.run();
                            }

                        });

                    });

                });
                
                setDefaultPic();

            }

        });

        studentDetailsPanel.add(submitButton);
        studentDetailsPanel.setVisible(true);
        studentDetailsPanel.setBackground(MICSColor2);

    }
   
    // STUDENT ACCOUNT NUMBER AND PASSWORD GENERATOR DISPLAY
    private void displayAccandPass (Runnable call) {
    	
    	int result = JOptionPane.showConfirmDialog(null, "STUDENT NUMBER: " + studNum + "\n"
                + "PASSWORD: " + studPass, "Account and Password", JOptionPane.OK_CANCEL_OPTION);
    	
    	if (result == JOptionPane.OK_OPTION) {
            // "OK" button clicked
            if (call != null) {
                call.run();
            }
        }
    	
    }
    
    // GENERATE STUDENT NUMBER
    private void accNumGen() {
    	
    	ArrayList<StudentDetails> studentList = container.getStudentList();
    	int check = -1;
    	
    	do {
    		
    		Random random = new Random();
        	Calendar calendar = Calendar.getInstance();
        	String str = new String("MICS");
        	int[] lastFour = new int[4];
        	
        	int currentY = calendar.get(Calendar.YEAR); // Get the current year
        	int sy = currentY % 100; // Get the last two digits of the current year
        	
        	// Generate four random numbers
        	for (int i = 0; i < 4; i++) {
        		int randomNumber = random.nextInt(9);
        		lastFour[i] = randomNumber;
        	}
        	
        	// Convert and store the lastFour to a string variable  "lastStr"
        	StringBuilder sb = new StringBuilder();
        	
        	for(int i = 0; i < 4; i++) {
        		sb.append(lastFour[i]);
        	}
        	
        	String lastStr = sb.toString();
        	
        	studNum = str + sy + lastStr;
        	
        	for(int i = 0; i < studentList.size(); i++) {
        		if(studentList.get(i).getstudNum().equals(studNum)) {
        			check = -1;
        			break;
        		}
        		check = 1;
        	}
        	
    	} while (check == -1);	
    	
    }
    
    // GENERATE STUDENT PASSWORD
    private void passGen() {
    	
    	String fName = givenNameTextField.getText();
    	String lName = nameTextField.getText().replace(" ", "");
    	
    	// GET THE INITIALS OF FIRST NAME
    	String[] fParts = fName.split(" ");
    	StringBuilder fInitials = new StringBuilder();
    	
    	for(String part : fParts) {
    		
    		if(!part.isEmpty()) {
    			
    			fInitials.append(part.charAt(0));
    			
    		}
    		
    	}   	
    	fName = fInitials.toString();
    	
    	fName = fName.toLowerCase();
    	lName = lName.toLowerCase();
    	
    	studPass = fName + lName;
    	
    }
    
    private void saveNewStudent() {
    	
    	ArrayList<StudentDetails> studentList = container.getStudentList();
    	
    	String[] courses = {"CC131L", "CC132", "CC113", "MATHA05S"};
    	int[] units = {1, 2, 3, 5};
    	
    	int studYear = 1;
    	int studSem = 1;
    	String lName = nameTextField.getText().toUpperCase();
        String fName = givenNameTextField.getText().toUpperCase();
        String mName = middleNameTextField.getText().toUpperCase();
        char sex = maleRadioButton.isSelected() ? 'M' : 'F';
        String birthdate = birthdateTextField.getText();
        String phoneNumber = phoneNumTextField.getText();
        String email = emailAddTextField.getText();
        String address = addrssTextField.getText();
        String guardianEmail = gemailAddTextField.getText();
        String guardianPhoneNumber = gPhoneNumTextField.getText();
    	
        StudentDetails student = new StudentDetails (studYear, studSem, fName, mName, lName, studNum,
                studPass, "REGULAR" , sex, birthdate, address, email, phoneNumber, guardianEmail, guardianPhoneNumber);
        studentList.add(student);
        
        general.locate(studNum);
        
        for(int i = 0; i < courses.length; i++) {
        	
        	CourseDetails newallCourse = new CourseDetails(courses[i], "<DAY> <TIME SLOT>", "FACULTY NAME",
        													units[i], 0.00);
        	CourseDetails newcurrCourse = new CourseDetails(courses[i], "<DAY> <TIME SLOT>", "FACULTY NAME",
															units[i], 0.00);
        	
        	studentList.get(container.getMarker()).getAllCourseList().add(newallCourse);
        	studentList.get(container.getMarker()).getCurrentCourseList().add(newcurrCourse);
        	
        }
        
    }  
    
    // FILE HANDLING
    private void retrieveExamInfo() {
    	
    	try {
    		
    		BufferedReader reader = new BufferedReader(new FileReader("DATABASE/1/DB1.txt"));
			String line;
			
			while((line = reader.readLine()) != null) {
				
				// Decoder
				char[] decoder = line.toCharArray();
				
				for(int i = 0; i < decoder.length; i++) {
					char c = decoder[i];

	        	    if (c != '\t') {
	        	        c -= 3; // Increment the ASCII value by 3
	        	    }
					
	        	    decoder[i] = c;
				}
				
				line = new String(decoder);
				
				// Delimiter
				String[] components = line.split("\t");
				
				// Parse the line and create examineeDet objects
				NewStudent.examineeDet examinee = new NewStudent.examineeDet();
				
				// Check if the line has enough components
	            if (components.length >= 4) {
	                // Initialize the examinee object
	            	
	                examinee.examineeNo = components[0];
	                examinee.gr1 = Double.parseDouble(components[1]);
	                examinee.gr2 = Double.parseDouble(components[2]);
	                examinee.gr3 = Double.parseDouble(components[3]);

	                examineeList.add(examinee);
	            } else {
	                // Handle the case where the line does not have enough components
	                System.err.println("Invalid line: " + line);
	            }
				
			}
			reader.close();
    		
    	} catch (IOException e) {
			
			e.printStackTrace();
			
		}
    	
    }    
    
    // EXAMINEE POPULATION AND LOCATION FUNCTIONS
    private void saveExamInfo() {
    	
    	try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter("DATABASE/1/DB1.txt", false));
	        for (examineeDet examinee : examineeList) {
	        	String x = examinee.examineeNo + "\t" + examinee.gr1 + "\t" +
	            					examinee.gr2 + "\t" + examinee.gr3;
	        	
	        	char[] examDetChars = x.toCharArray();
	            
	        	for (int i = 0; i < examDetChars.length; i++) {
	        	    char c = examDetChars[i];

	        	    if (c != '\t') {
	        	        c += 3; // Increment the ASCII value by 3
	        	    }

	        	    examDetChars[i] = c; // Update the character in the char array
	        	}

	        	String modifiedExamDet = new String(examDetChars);
	        	
	            writer.write(modifiedExamDet);
	            writer.newLine();
	        }
	        
	        writer.close();
    		
    	} catch (IOException e) {
			
			e.printStackTrace();
			
		}
    	
    }    
 
    private int locateExamineeNo (String examineeNo) {
    	
    	for(int i = 0; i < examineeList.size(); i++) {
    		
    		examineeDet examinee = examineeList.get(i);
    		
    		if(examinee.examineeNo.equals(examineeNo)) 
    			return i;
    	}
    	
    	return -1;
    }
    
    // MISCELLANEOUS
    private void sizeFrame() {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // Get the screen size
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int frameWidth = frame.getWidth();
        int frameHeight = frame.getHeight();
        int xPos = (screenWidth - frameWidth) / 2; // Calculate the x position for the frame
        int yPos = (screenHeight - frameHeight) / 2; // Calculate the y position for the frame
        frame.setLocation(xPos, yPos); // Set the position of the frame

        frame.setResizable(false);
        frame.setVisible(true);
        
    }
    
    private void setDefaultPic() {
    	
    	String stuNum = studNum;
    
    	char[] decoder = stuNum.toCharArray();
    	for(int i = 0; i < decoder.length; i++) {
    		
    		if(i < 4)
    			decoder[i] += 5;
    		else if(i >= 4 && i < 10)
    			decoder[i] += 17;
    		
    	}	
    	stuNum = new String(decoder);
    	
    	String defaultPic = "images/defaultProfile.jpg";
    	String currStudPic = "DATABASE/2/" + stuNum;
    	
    	File defaultFile = new File(defaultPic);    	
    	File currStudPic_File = new File(currStudPic + ".jpg");   	 	
    	
    	 try {
             // Copy the default file to the destination directory with the new encrypted filename
             Files.copy(defaultFile.toPath(), currStudPic_File.toPath(), StandardCopyOption.REPLACE_EXISTING);

    	 } catch (IOException e) {
             e.printStackTrace();
         }
    	
    }
    
    
    // make the Text field rounder
    class RoundedJTextField extends JTextField {
        /**
         *
         */
        private static final long serialVersionUID = 1L;
        private Shape shape;

        public RoundedJTextField(int size) {
            super(size);
            setOpaque(false);
        }

        protected void paintComponent(Graphics g) {
            g.setColor(getBackground());
            g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
            super.paintComponent(g);
        }

        protected void paintBorder(Graphics g) {
            // no color
        }

        public boolean contains(int x, int y) {
            if (shape == null || !shape.getBounds().equals(getBounds())) {
                shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
            }
            return shape.contains(x, y);
        }

        public Shape getShape() {
            return shape;
        }

        public void setShape(Shape shape) {
            this.shape = shape;
        }
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
