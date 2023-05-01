import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SignIn extends JFrame {

	private JTextField usernameField;
	private JPasswordField passwordField;

	public SignIn() {
		getContentPane().setBackground(new Color(154,198,255));
		Dimension screenDim1 = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(1920, 1080);
        setLocation(screenDim1.width/2 - getWidth()/2, screenDim1.height/2 - getHeight()/2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        
        JLabel SignLab = new JLabel("Sign In");
        SignLab.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 71));
        SignLab.setHorizontalAlignment(SwingConstants.CENTER);
        SignLab.setBounds(481, 131, 942, 217);
        
        SignLab.setBackground(Color.WHITE);
        
        getContentPane().add(SignLab);
        
        usernameField = new JTextField("Username");
        usernameField.setFont(new Font("Verdana", Font.ITALIC, 20));
        usernameField.setForeground(Color.GRAY);
        usernameField.setBounds(616, 386, 671, 82);
        getContentPane().add(usernameField);
        usernameField.setColumns(10);
        
        usernameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Clear the "Username" text when the field gains focus
                if (usernameField.getText().equals("Username")) {
                    usernameField.setText("");
                    usernameField.setFont(new Font("Verdana", Font.PLAIN, 20));
                    usernameField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Restore the "Username" text if the field is empty
                if (usernameField.getText().isEmpty()) {
                    usernameField.setText("Username");
                    usernameField.setFont(new Font("Verdana", Font.PLAIN, 20));
                    usernameField.setForeground(Color.GRAY);
                }
            }
        });
        
        passwordField = new JPasswordField("Password");
        passwordField.setFont(new Font("Verdana", Font.ITALIC, 20));
        passwordField.setForeground(Color.GRAY);
        passwordField.setBounds(616, 479, 671, 82);
        getContentPane().add(passwordField);
        
        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Clear the "Password" text when the field gains focus
                if (String.valueOf(passwordField.getPassword()).equals("Password")) {
                    passwordField.setText("");
                    passwordField.setFont(new Font("Verdana", Font.PLAIN, 20));
                    passwordField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Restore the "Password" text if the field is empty
                if (passwordField.getPassword().length == 0) {
                    passwordField.setText("Password");
                    passwordField.setFont(new Font("Verdana", Font.PLAIN, 20));
                    passwordField.setForeground(Color.GRAY);
                }
            }
        });
        
        
        
        JButton signIn = new JButton("Sign In");
        signIn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		 try {
                     File file = new File("users.xlsx");
                     XSSFWorkbook workbook = new XSSFWorkbook(file);
                     Sheet sheet = workbook.getSheetAt(0);
                     Iterator<Row> rowIterator = sheet.iterator();
                     rowIterator.next();
                     while (rowIterator.hasNext()) {
                         Row row = rowIterator.next();
                         Cell usernameCell = row.getCell(0);
                         Cell passwordCell = row.getCell(1);
                         String cellUsername = usernameCell.getStringCellValue();
                         String cellPassword = passwordCell.getStringCellValue();
                         if (cellUsername.equals(usernameField.getText()) && cellPassword.equals(passwordField.getText())) {
                             dispose();
                        	 FrontPage2 F = new FrontPage2(cellUsername);
                             F.setVisible(true);
                             
                             break;
                         }
                     }
                     workbook.close();
                 } catch (IOException e1) {
                     e1.printStackTrace();
                 } catch (InvalidFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		
        	}
        });
        signIn.setFont(new Font("Verdana", Font.ITALIC, 24));
        signIn.setBounds(616, 595, 671, 82);
        getContentPane().add(signIn);
        
        JButton register = new JButton("Register");
        register.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		dispose();
        		Registration R = new Registration();
        		R.setVisible(true);
        		
        	}
        });
        register.setFont(new Font("Verdana", Font.ITALIC, 24));
        register.setBounds(958, 683, 329, 82);
        getContentPane().add(register);
        
        JCheckBox passwordCheckBox = new JCheckBox("Show Password");
        passwordCheckBox.setBounds(616, 565, 671, 23);
        getContentPane().add(passwordCheckBox);
        
        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        		FrontPage2 S = new FrontPage2(" ");
        		S.setVisible(true);
        		
        	}
        });
        back.setFont(new Font("Verdana", Font.ITALIC, 24));
        back.setBounds(616, 683, 329, 82);
        getContentPane().add(back);
   
	}
}
