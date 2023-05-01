import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;

public class Registration extends JFrame {

	private JTextField usernameField;
	private JTextField Phone;
	private JTextField FirstName;
	private JTextField LastName;
	private JTextField Password;
	private JRadioButton Land;
	private JRadioButton Mobile;
	private JTextField ConfirmPassword;
	private JRadioButton male;
	private JRadioButton female;
	private JTextField email;
	private JTextField emiratesID;
	
	public static boolean isValidEmailAddress(String email) {
	    String regex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
	                   "[a-zA-Z0-9_+&*-]+)*@" +
	                   "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
	                   "A-Z]{2,7}$";
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(email);
	    return matcher.matches();
	}
	
	public static boolean isValidPhoneNumber(String phoneNumber) {
	    String regex = "^\\+(?:[0-9] ?){6,14}[0-9]$";
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(phoneNumber);
	    return matcher.matches();
	}
	
	public static boolean isValidEmiratesID(String emiratesID) {
		// Remove any white spaces from the emiratesID string
	    emiratesID = emiratesID.replaceAll("\\s", "");
	    
	    // Check if the emiratesID is 15 digits
	    if (emiratesID.length() != 15) {
	    	System.out.print("Failed at count");
	        return false;
	    }
	    
	    // Check if the first 3 digits are 784
	    String first3Digits = emiratesID.substring(0, 3);
	    if (!first3Digits.equals("784")) {
	        return false;
	    }
	    
	    int yearOfBirth = Integer.parseInt(emiratesID.substring(3, 7));
	    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+4"));
	    int currentYear = calendar.get(Calendar.YEAR);
	    
	    if (yearOfBirth < currentYear - 100 || yearOfBirth >= currentYear) {
	        return false;
	    }
	    
	    
	    return true;
	}
	
	public class UsernameChecker {
	    public static boolean doesUsernameExist(String username) throws IOException {
	        File file = new File("users.xlsx");
	        FileInputStream inputStream = new FileInputStream(file);
	        Workbook workbook = WorkbookFactory.create(inputStream);
	        Sheet sheet = workbook.getSheetAt(0);
	        for (Row row : sheet) {
	            Cell cell = row.getCell(0);
	            if (cell != null && cell.getStringCellValue().equals(username)) {
	                return true;
	            }
	        }
	        return false;
	    }
	}
	
	public Registration() {
		
		getContentPane().setBackground(new Color(154,198,255));
		Dimension screenDim1 = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(1920, 1080);
        setLocation(screenDim1.width/2 - getWidth()/2, screenDim1.height/2 - getHeight()/2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        
        JLabel SignLab = new JLabel("Create New Account");
        SignLab.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 71));
        SignLab.setHorizontalAlignment(SwingConstants.CENTER);
        SignLab.setBounds(481, 11, 942, 217);
        
        SignLab.setBackground(Color.WHITE);
        
        getContentPane().add(SignLab);
        
        usernameField = new JTextField("Username");
        usernameField.setFont(new Font("Verdana", Font.ITALIC, 20));
        usernameField.setForeground(Color.GRAY);
        usernameField.setBounds(313, 325, 632, 82);
        getContentPane().add(usernameField);
        usernameField.setColumns(10);
        JButton register = new JButton("Register");
        register.setFont(new Font("Verdana", Font.ITALIC, 24));
        register.setBounds(616, 751, 671, 82);
        getContentPane().add(register);
        
        JButton back = new JButton("Home Page");
        back.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        		FrontPage2 S = new FrontPage2(" ");
        		S.setVisible(true);
        		
        	}
        });
        back.setFont(new Font("Verdana", Font.ITALIC, 24));
        back.setBounds(616, 844, 329, 82);
        getContentPane().add(back);
        
        Phone = new JTextField("Phone Number (Start with +971)");
        Phone.setForeground(Color.GRAY);
        Phone.setFont(new Font("Verdana", Font.ITALIC, 20));
        Phone.setColumns(10);
        Phone.setBounds(313, 418, 464, 82);
        getContentPane().add(Phone);
        
        FirstName = new JTextField("First Name");
        FirstName.setForeground(Color.GRAY);
        FirstName.setFont(new Font("Verdana", Font.ITALIC, 20));
        FirstName.setColumns(10);
        FirstName.setBounds(313, 232, 632, 82);
        getContentPane().add(FirstName);
        
        LastName = new JTextField("Last Name");
        LastName.setForeground(Color.GRAY);
        LastName.setFont(new Font("Verdana", Font.ITALIC, 20));
        LastName.setColumns(10);
        LastName.setBounds(958, 232, 632, 82);
        getContentPane().add(LastName);
        
        Password = new JTextField("Password");
        Password.setForeground(Color.GRAY);
        Password.setFont(new Font("Verdana", Font.ITALIC, 20));
        Password.setColumns(10);
        Password.setBounds(958, 325, 632, 82);
        getContentPane().add(Password);
        
        Land = new JRadioButton("Landline");
        Land.setBounds(783, 460, 162, 40);
        getContentPane().add(Land);
        
        Mobile = new JRadioButton("Mobile");
        Mobile.setBounds(783, 419, 162, 35);
        getContentPane().add(Mobile);
        
        ButtonGroup phoneType = new ButtonGroup();
        phoneType.add(Land);
        phoneType.add(Mobile);
        
        ConfirmPassword = new JTextField("Confirm Password");
        ConfirmPassword.setForeground(Color.GRAY);
        ConfirmPassword.setFont(new Font("Verdana", Font.ITALIC, 20));
        ConfirmPassword.setColumns(10);
        ConfirmPassword.setBounds(958, 418, 632, 82);
        getContentPane().add(ConfirmPassword);
        
        
        JButton signIn = new JButton("Sign In");
        signIn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		dispose();
        		SignIn S = new SignIn();
        		S.setVisible(true);
        	}
        });
        signIn.setFont(new Font("Verdana", Font.ITALIC, 24));
        signIn.setBounds(958, 844, 329, 82);
        getContentPane().add(signIn);
        
        List<String> items = Arrays.asList("Nationality:", "Afghanistan", "Albania", "Algeria", "Angola", "Argentina",
        		"Australia", "Austria", "Bangladesh", "Belgium", "Bolivia", "Brazil", "Canada", "Chile",
        		"China", "Colombia", "Congo, Democratic Republic of the", "Costa Rica", "Croatia", "Cuba",
        		"Czech Republic", "Denmark", "Dominican Republic", "Ecuador", "Egypt", "El Salvador",
        		"Equatorial Guinea", "Ethiopia", "Finland", "France", "Gabon", "Germany", "Ghana", "Greece",
        		"Guatemala", "Haiti", "Honduras", "Hungary", "India", "Indonesia", "Iran", "Iraq", "Ireland",
        		"Italy", "Jamaica", "Japan", "Jordan", "Kenya", "Kuwait", "Lebanon", "Libya", "Madagascar",
        		"Malaysia", "Mali", "Mexico", "Mongolia", "Morocco", "Mozambique", "Myanmar", "Namibia",
        		"Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria", "North Korea", "Norway",
        		"Pakistan", "Palestine","Panama", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Qatar", "Romania",
        		"Russia", "Rwanda", "Saudi Arabia", "Senegal", "Serbia", "Singapore", "Slovakia", "Slovenia",
        		"Somalia", "South Africa", "South Korea", "Spain", "Sri Lanka", "Sudan", "Sweden", "Switzerland",
        		"Syria", "Taiwan", "Tanzania", "Thailand", "Tunisia", "Turkey", "Uganda", "Ukraine", 
        		"United Arab Emirates", "United Kingdom", "United States", "Uruguay", "Uzbekistan", 
        		"Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe");
        
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(items.toArray(new String[0]));
        JComboBox<String> Nationality = new JComboBox<>(model);
        
        Nationality.setBounds(311, 647, 312, 35);
        getContentPane().add(Nationality);
        
        
        
        //
        
        List<String> UAE = Arrays.asList("Emirate:", "Abu Dhabi", "Ajman", "Dubai", "Fujairah", "Ras Al Khaimah", "Sharjah", "Umm Al Quwain");
        
        DefaultComboBoxModel<String> model1 = new DefaultComboBoxModel<>(UAE.toArray(new String[0]));
        JComboBox<String> Emirates = new JComboBox<>(model1);
        Emirates.setBounds(633, 647, 312, 35);
        getContentPane().add(Emirates);
        
        male = new JRadioButton("Male");
        male.setBounds(313, 600, 312, 40);
        getContentPane().add(male);
        
        female = new JRadioButton("Female");
        female.setBounds(633, 600, 312, 40);
        getContentPane().add(female);
        
        ButtonGroup gender = new ButtonGroup();
        gender.add(male);
        gender.add(female);
        
        email = new JTextField("Email Address");
        email.setForeground(Color.GRAY);
        email.setFont(new Font("Verdana", Font.ITALIC, 20));
        email.setColumns(10);
        email.setBounds(313, 511, 1277, 82);
        getContentPane().add(email);
        
        emiratesID = new JTextField("Emirates ID");
        emiratesID.setForeground(Color.GRAY);
        emiratesID.setFont(new Font("Verdana", Font.ITALIC, 20));
        emiratesID.setColumns(10);
        emiratesID.setBounds(958, 600, 632, 82);
        getContentPane().add(emiratesID);
        
        
        register.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		if(FirstName.equals("First Name")|| LastName.equals("Last Name") || usernameField.equals("Username")|| Password.equals("Password") 
        				|| ConfirmPassword.equals("Confirm Password")|| Phone.equals("Phone Number") || emiratesID.equals("Emirate ID") || email.equals("Email Address") ||gender.getSelection() == null 
        				|| phoneType.getSelection() == null || Nationality.getSelectedItem().equals("Nationality:") || Emirates.getSelectedItem().equals("Emirate:")) {
        			
        			JOptionPane.showMessageDialog(null, "One or more of the fields are empty OR you have not selected on of the options correctly ");
        			
        		}
        		else {
        			if(!isValidEmailAddress(email.getText())){
        				JOptionPane.showMessageDialog(null, "Please enter a valid email address.");
        				
        			}
        			else {
        				if(!isValidPhoneNumber(Phone.getText())){
            				JOptionPane.showMessageDialog(null, "Please enter a valid phone number.");
            			}
            			else {
            				if(!isValidEmiratesID(emiratesID.getText())){
            					JOptionPane.showMessageDialog(null, "Please enter a valid Emirates ID.");
                			}
                			else {
                				if(!Password.getText().equals(ConfirmPassword.getText())){
                					JOptionPane.showMessageDialog(null, "The passwords are not the same.");
                    			}
                    			else {
                    				
                        				try {
											if (UsernameChecker.doesUsernameExist(usernameField.getText())) {
											    JOptionPane.showMessageDialog(null, "Username already exists.");
											} else {
											    
											      FileInputStream inputStream = new FileInputStream(new File("users.xlsx"));
											      XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
											      XSSFSheet sheet = workbook.getSheetAt(0);
											      
											      int rowIndex = 0;
											      Iterator<Row> rowIterator = sheet.iterator();
											      while (rowIterator.hasNext()) {
											         Row row = rowIterator.next();
											         if (row.getCell(0) == null) {
											            break;
											         }
											         rowIndex++;
											      }

											      ButtonModel selectedButton = gender.getSelection();
											      ButtonModel selectedButton1 = phoneType.getSelection();

											      // Create a new row and write the registration information
											      Row row = sheet.createRow(rowIndex);
											      Cell cell = row.createCell(0);
											      cell.setCellValue(usernameField.getText());
											      cell = row.createCell(1);
											      cell.setCellValue(Password.getText());
											      cell = row.createCell(2);
											      cell.setCellValue(email.getText());
											      cell = row.createCell(3);
											      cell.setCellValue(FirstName.getText());
											      cell = row.createCell(4);
											      cell.setCellValue(LastName.getText());
											      cell = row.createCell(5);
											      cell.setCellValue(selectedButton.getActionCommand());
											      cell = row.createCell(6);
											      cell.setCellValue(Phone.getText());
											      cell = row.createCell(7);
											      cell.setCellValue(selectedButton1.getActionCommand());
											      cell = row.createCell(8);
											      cell.setCellValue(emiratesID.getText());
											      cell = row.createCell(9);
											      cell.setCellValue((String)Nationality.getSelectedItem());
											      cell = row.createCell(10);
											      cell.setCellValue((String)Emirates.getSelectedItem());
											      
											      // Write the updated workbook back to the file
											      FileOutputStream outputStream = new FileOutputStream("users.xlsx");
											      workbook.write(outputStream);
											      outputStream.close();
											      workbook.close();
											   
											}
											
											
										} catch (HeadlessException | IOException e1) {
											
											e1.printStackTrace();
										}
                        				
                        				dispose();
                        				SignIn S = new SignIn();
                        				S.setVisible(true);
                        			}
                			}
            			}
        			}
        		}
        		
        	}
        });
      //---------------------------------------------------------------------------------------------------------------------------------
      //---------------------------------------------------------------------------------------------------------------------------------
      //---------------------------------------------------------------------------------------------------------------------------------
        
        usernameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Clear the "Username" text when the field gains focus
                if (usernameField.getText().equals("Username")) {
                    usernameField.setText("");
                    usernameField.setFont(new Font("Verdana", Font.ITALIC, 20));
                    usernameField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Restore the "Username" text if the field is empty
                if (usernameField.getText().isEmpty()) {
                    usernameField.setText("Username");
                    usernameField.setFont(new Font("Verdana", Font.ITALIC, 20));
                    usernameField.setForeground(Color.GRAY);
                }
            }
        });
        
        email.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Clear the "Username" text when the field gains focus
                if (email.getText().equals("Email Address")) {
                    email.setText("");
                    email.setFont(new Font("Verdana", Font.ITALIC, 20));
                    email.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Restore the "Username" text if the field is empty
                if (email.getText().isEmpty()) {
                    email.setText("Email Address");
                    email.setFont(new Font("Verdana", Font.ITALIC, 20));
                    email.setForeground(Color.GRAY);
                }
            }
        });
        
        Phone.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Clear the "Username" text when the field gains focus
                if (Phone.getText().equals("Phone Number (Start with +971)")) {
                    Phone.setText("");
                    Phone.setFont(new Font("Verdana", Font.ITALIC, 20));
                    Phone.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Restore the "Username" text if the field is empty
                if (Phone.getText().isEmpty()) {
                    Phone.setText("Phone Number (Start with +971)");
                    Phone.setFont(new Font("Verdana", Font.ITALIC, 20));
                    Phone.setForeground(Color.GRAY);
                }
            }
        });
        
        emiratesID.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Clear the "Username" text when the field gains focus
                if (emiratesID.getText().equals("Emirates ID")) {
                    emiratesID.setText("");
                    emiratesID.setFont(new Font("Verdana", Font.ITALIC, 20));
                    emiratesID.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Restore the "Username" text if the field is empty
                if (emiratesID.getText().isEmpty()) {
                    emiratesID.setText("Emirates ID");
                    emiratesID.setFont(new Font("Verdana", Font.ITALIC, 20));
                    emiratesID.setForeground(Color.GRAY);
                }
            }
        });
        
        FirstName.addFocusListener(new FocusListener() {
        	@Override
        	public void focusGained(FocusEvent e) {
        	// Clear the "First Name" text when the field gains focus
        	if (FirstName.getText().equals("First Name")) {
        	FirstName.setText("");
        	FirstName.setFont(new Font("Verdana", Font.ITALIC, 20));
        	FirstName.setForeground(Color.BLACK);
        	}
        	}

        	        @Override
        	        public void focusLost(FocusEvent e) {
        	            // Restore the "First Name" text if the field is empty
        	            if (FirstName.getText().isEmpty()) {
        	                FirstName.setText("First Name");
        	                FirstName.setFont(new Font("Verdana", Font.ITALIC, 20));
        	                FirstName.setForeground(Color.GRAY);
        	            }
        	        }
        	    });
        	LastName.addFocusListener(new FocusListener() {
        	@Override
        	public void focusGained(FocusEvent e) {
        	// Clear the "Last Name" text when the field gains focus
        	if (LastName.getText().equals("Last Name")) {
        	LastName.setText("");
        	LastName.setFont(new Font("Verdana", Font.ITALIC, 20));
        	LastName.setForeground(Color.BLACK);
        	}
        	}

        	        @Override
        	        public void focusLost(FocusEvent e) {
        	            // Restore the "Last Name" text if the field is empty
        	            if (LastName.getText().isEmpty()) {
        	                LastName.setText("Last Name");
        	                LastName.setFont(new Font("Verdana", Font.ITALIC, 20));
        	                LastName.setForeground(Color.GRAY);
        	            }
        	        }
        	    });
        	
        	Password.addFocusListener(new FocusListener() {
        	@Override
        	public void focusGained(FocusEvent e) {
        	// Clear the "Password" text when the field gains focus
        	if (Password.getText().equals("Password")) {
        	Password.setText("");
        	Password.setFont(new Font("Verdana", Font.ITALIC, 20));
        	Password.setForeground(Color.BLACK);
        	}
        	}

        	
        	        @Override
        	        public void focusLost(FocusEvent e) {
        	            // Restore the "Password" text if the field is empty
        	            if (Password.getText().isEmpty()) {
        	                Password.setText("Password");
        	                Password.setFont(new Font("Verdana", Font.ITALIC, 20));
        	                Password.setForeground(Color.GRAY);
        	            }
        	        }
        	    });
        	
        	ConfirmPassword.addFocusListener(new FocusListener() {
        	@Override
        	public void focusGained(FocusEvent e) {
        	// Clear the "Confirm Password" text when the field gains focus
        	if (ConfirmPassword.getText().equals("Confirm Password")) {
        	ConfirmPassword.setText("");
        	ConfirmPassword.setFont(new Font("Verdana", Font.ITALIC, 20));
        	ConfirmPassword.setForeground(Color.BLACK); // to hide the text characters
        	}
        	}   
        	 @Override
             public void focusLost(FocusEvent e) {
                
			     if (ConfirmPassword.getText().isEmpty() || ConfirmPassword.getText().equals("Confirm Password")) {
				     ConfirmPassword.setText("Confirm Password");
				     ConfirmPassword.setFont(new Font("Verdana", Font.ITALIC, 20));
				     ConfirmPassword.setForeground(Color.GRAY);
			     }
			   }
			});

        	
        
        
        
        
        
        
        
        
        
        
        
        
        
   
	}
}
