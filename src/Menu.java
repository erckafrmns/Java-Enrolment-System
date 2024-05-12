import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.geom.RoundRectangle2D;

public class Menu implements ActionListener {

    private StudentListContainer container;
    private FileHandling fileHandling;
    private EncryptDecrypt ectdct;
    private General general;

    private JFrame frame;
    private JPanel menuPanel;
    private JLabel portLabel;
    private JLabel numLabel;
    private JLabel passLabel;
    private JLabel toNewEnroll;
    private JTextField numField;
    private JPasswordField passField;
    private JButton button;

    public Menu() {

        container = StudentListContainer.getInstance();
        ectdct = new EncryptDecrypt();
        fileHandling = new FileHandling();
        general = new General();

    }

    public void logIn() {

        // General Usage
        Font header = new Font("Arial", Font.BOLD, 28);
        Font btn = new Font("Arial", Font.BOLD, 14);
        Font newf = new Font("Arial", Font.BOLD + Font.ITALIC, 14);
        Color MICSColor1 = new Color(126, 2, 2); // medyo maroon
        Color MICSColor2 = new Color(241, 234, 213); // beige
        Color MICSColor3 = Color.decode("#FFFFFF");
        Color buttoncolor = Color.decode("#472213");

        frame = new JFrame();
        menuPanel = new JPanel();
        menuPanel.setOpaque(false);

        frame.setSize(750, 450);                
        frame.getContentPane().setBackground(MICSColor1);

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                general.loadingAnimation("EXITING...", () -> {

                    ectdct.encryptAllStud();
                    fileHandling.saveAllStudInfo();
                    frame.dispose();

                });

            }
        });
        frame.add(menuPanel);

        menuPanel.setSize(375, 225);
        menuPanel.setLayout(null);

        /* STUDENT PORTAL LABEL */
        portLabel = new JLabel("STUDENT PORTAL");
        portLabel.setFont(header);
        portLabel.setForeground(MICSColor2);
        portLabel.setBounds(435, 50, 400, 40);
        menuPanel.add(portLabel);

        /* STUDENT NUMBER BOXES */
        numLabel = new JLabel("Student Number");
        numLabel.setBounds(512, 155, 160, 36);
        numLabel.setForeground(MICSColor2);
        menuPanel.add(numLabel);

        numField = new RoundedJTextField(10);
        numField.setBounds(460, 128, 200, 36);
        numField.setBackground(MICSColor3);
        menuPanel.add(numField);

        /* PASSWORD BOXES */
        passLabel = new JLabel("Password");
        passLabel.setBounds(530, 217, 160, 36);
        passLabel.setForeground(MICSColor2);
        menuPanel.add(passLabel);

        passField = new RoundedPasswordField(20);
        passField.setBounds(460, 190, 200, 36);
        passField.setBackground(MICSColor3);
        menuPanel.add(passField);

        /* BUTTONS */
        button = new RoundedButton("Log-in");
        button.setFont(btn);
        button.setForeground(MICSColor3);
        button.setBounds(460, 260, 200, 36);
        button.setBackground(buttoncolor);
        button.addActionListener(this);
        menuPanel.add(button); 
        
        final JButton finalSearchButton = button;

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

        button.addMouseListener(animationListener);

        toNewEnroll = new JLabel("I AM A NEW STUDENT");
        toNewEnroll.setForeground(MICSColor2);
        toNewEnroll.setFont(newf);
        toNewEnroll.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        toNewEnroll.setBounds(482, 280, 200, 60);
        toNewEnroll.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                MainMenu mainMenu = new MainMenu();

                general.loadingAnimation("LOADING...", () -> {

                    try {

                        frame.dispose(); // Close frame

                        NewStudent newStudent = new NewStudent();

                        newStudent.entry(() -> {
                        		
                        	general.loadingAnimation("LOADING...", () -> {
                        		
	                            general.successEnroll(() -> {
	                            	mainMenu.mainMenu();
		                    	});
	                            
                        	});

                        }); // Open new frame

                    } catch (Exception ex) {

                        ex.printStackTrace();

                    }

                });

            }

            // FOR AESTHETICS ONLY
            @Override
            public void mouseEntered(MouseEvent e) {
                setLabelStyle(Color.decode("#E19C9D"), Font.BOLD);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setLabelStyle(MICSColor2, Font.BOLD + Font.ITALIC);
            }
        });

        menuPanel.add(toNewEnroll);

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

        // SET FRAME TO MIDDLE OF SCREEN
        int frameWidth = frame.getWidth();
        int frameHeight = frame.getHeight();
        int[] xPos = new int[1];
        int[] yPos = new int[1];
        general.setFrametoMiddle(frameWidth, frameHeight, xPos, yPos);
        frame.setLocation(xPos[0], yPos[0]);

        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void setLabelStyle(Color color, int fontStyle) {

        Font font = toNewEnroll.getFont();
        Font newFont = new Font(font.getName(), fontStyle, font.getSize());
        toNewEnroll.setForeground(color);
        toNewEnroll.setFont(newFont);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ArrayList<StudentDetails> studentList = container.getStudentList();
        MainMenu mainMenu = new MainMenu();

        general.locate(numField.getText());

        if (container.getMarker() == -1) {
            JOptionPane.showMessageDialog(null, "INVALID STUDENT NUMBER",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } else {

            if (studentList.get(container.getMarker()).getstudPass().equals(new String(passField.getPassword()))) {

                general.loadingAnimation("LOGGING IN...", () -> {

                    frame.dispose();
                    fileHandling.retrieveIndivStudInfo();
                    mainMenu.mainMenu();

                });

            } else {

                JOptionPane.showMessageDialog(null, "PASSWORD INCORRECT",
                        "Error", JOptionPane.ERROR_MESSAGE);

            }
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

    public class RoundedPasswordField extends JPasswordField {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Shape shape;

        public RoundedPasswordField(int size) {
            super(size);
            setOpaque(false);
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
