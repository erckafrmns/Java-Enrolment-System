public class MainClass { // Main class
	
	public static void main(String[] args) { // Entry point of the program
       
		FileHandling fileHandling = new FileHandling();		
		
		// RETRIEVE ALL STUDENT ACCOUNT DETAILS
        fileHandling.retrieveAllStudInfo();
       
        Menu menu = new Menu();
		menu.logIn(); // Call logIn() method
		
				
	}

}