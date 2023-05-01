import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Objects;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Component;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrontPage2 extends JFrame {

	protected static final String String = null;
	private JTextArea description;
	private JLabel LabelImage;
	public FrontPage2(String username) {
		
		String ID = username;
		
		getContentPane().setBackground(new Color(154,198,255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//			
			Dimension screenDim1 = Toolkit.getDefaultToolkit().getScreenSize();
	        setSize(1920, 1080);
	        setLocation(screenDim1.width/2 - getWidth()/2, screenDim1.height/2 - getHeight()/2);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        getContentPane().setLayout(null);
	        
	        ImageIcon Icon = new ImageIcon("KUSTAR_Logo.jpg");
			Image image1 = Icon.getImage().getScaledInstance(1904, 315, Image.SCALE_SMOOTH);
		    ImageIcon newIcon = new ImageIcon(image1);
			JLabel Logo = new JLabel(newIcon);
			Logo.setBounds(0, 0, 1904, 275);
			getContentPane().add(Logo);
			
			//Button that takes you to about page
	        JButton About = new JButton("About");
	        About.setFont(new Font("Verdana", Font.PLAIN, 23));
	        About.setFocusPainted(false);
	        About.setBackground(new Color(233, 232, 255));
	        About.setBounds(21, 286, 582, 62);
	        getContentPane().add(About);
	        
	        //Button for the favorites List
	        JButton Favourites = new JButton("Favourites");
	        Favourites.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		
	        		if(ID.equals(" ")) {
	        			dispose();
	        			SignIn S = new SignIn();
	        			S.setVisible(true);
	        		}
	        		else {
	        			
	        			dispose();
	        			FavBooks F = new FavBooks(ID);
	        			F.setVisible(true);
	        		}
	        	}
	        });
	        Favourites.setFont(new Font("Verdana", Font.PLAIN, 23));
	        Favourites.setFocusPainted(false);
	        Favourites.setBackground(new Color(233, 232, 255));
	        Favourites.setBounds(661, 286, 582, 62);
	        getContentPane().add(Favourites);
	        
	        //Button for Search Screen
	        JButton Search = new JButton("Search");
	        Search.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		dispose();
					Search3 S;
					try {
						S = new Search3(username);
						S.setVisible(true);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
	        	}
	        });
	        Search.setFont(new Font("Verdana", Font.PLAIN, 23));
	        Search.setFocusPainted(false);
	        Search.setBackground(new Color(233, 232, 255));
	        Search.setBounds(1293, 286, 582, 62);
	        getContentPane().add(Search);
	        
	        //Events pane
	        JScrollPane eventScroll = new JScrollPane((Component) null);
	        eventScroll.setBounds(21, 359, 1854, 307);
	        getContentPane().add(eventScroll); 
	        JLabel EventsTab = new JLabel(" Upcoming Events:");
	        EventsTab.setFont(new Font("Courier New", Font.BOLD, 18));
	        eventScroll.setColumnHeaderView(EventsTab);  
	        DefaultListModel<String> model = new DefaultListModel<String>();
	        JList<String> eventList = new JList<String>(model);
	        eventList.setCellRenderer(new DefaultListCellRenderer() {
	            @Override
	            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
	                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	                renderer.setFont(new Font("Courier New", Font.BOLD, 20)); // set the desired font here
	                return renderer;
	            }
	        });
	        eventScroll.setViewportView(eventList);
	        



	        
	        
	        
	        //Weekly best pane
	        JScrollPane weeklyScroll = new JScrollPane((Component) null);
	        weeklyScroll.setBounds(21, 723, 1854, 307);
	        getContentPane().add(weeklyScroll);
	        JLabel lblWeeklyMostRented = new JLabel(" Weekly Most Rented:");
	        lblWeeklyMostRented.setFont(new Font("Courier New", Font.BOLD, 18));
	        weeklyScroll.setColumnHeaderView(lblWeeklyMostRented);
	        DefaultListModel<String> model1 = new DefaultListModel<String>();
	        JList<String> weeklyList = new JList<String>(model1);
	        weeklyList.setCellRenderer(new DefaultListCellRenderer() {
	            @Override
	            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
	                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	                renderer.setFont(new Font("Courier New", Font.BOLD, 20)); // set the desired font here
	                return renderer;
	            }
	        });
	        weeklyScroll.setViewportView(weeklyList);
	        
	        JButton Login = new JButton("Sign In");
	        Login.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		
	        		dispose();
	        		SignIn S = new SignIn();
	        		S.setVisible(true);
	        		
	        	}
	        });
	        
	        JButton profile = new JButton("Profile");
	        profile.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		
	        		dispose();
	        		SignIn S = new SignIn();
	        		S.setVisible(true);
	        		
	        	}
	        });
	        //"😎"
	        Login.setFont(new Font("Verdana", Font.PLAIN, 23));
	        Login.setBounds(1694, 0, 210, 62);
	        
	        profile.setFont(new Font("Verdana", Font.PLAIN, 23));
	        profile.setBounds(1694, 0, 210, 62);
	        if(ID.equals(" ")) {
	        	getContentPane().add(Login);
	        }
	        else {
	        	getContentPane().add(profile);
	        }
	        
	        //******************************************************************************************************************
	        
	        try {
	            FileInputStream excelFile = new FileInputStream(new File("Events.xlsx"));
	            XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
	            Sheet sheet = workbook.getSheetAt(0);
	            Iterator<Row> rowIterator = sheet.iterator();
	            // Skip first row
	            rowIterator.next();
	            while (rowIterator.hasNext()) {
	                Row row = rowIterator.next();
	                
	                Cell nameCell = row.getCell(0);
	                Cell imageCell = row.getCell(1);
	                Cell dateCell = row.getCell(2);
	                Cell descriptionCell = row.getCell(3);
	                
	                String name = nameCell.getStringCellValue();
	                
	                String date = "";
	                if (dateCell.getCellType() == CellType.NUMERIC) {
	                    java.util.Date date2 = dateCell.getDateCellValue();
	                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	                    date = dateFormat.format(date2);
	                } else {
	                    date = dateCell.getStringCellValue();
	                }
	               
	                
	                String imageName = imageCell.getStringCellValue();
	                String description = descriptionCell.getStringCellValue();
	                if(!name.equals("") && !date.equals("") && !imageName.equals("") && !description.equals("")) {
	                	String S = name + " - " + date;
	                	model.addElement(S);
	                }
	                
	            }
	            
	            eventList.addMouseListener(new MouseAdapter() {
	                public void mouseClicked(MouseEvent event) {
	                    if (event.getClickCount() == 1) {
	                        try {
	                        	
	                            LabelImage = new JLabel();
	                            description = new JTextArea();
	                            LabelImage.setIcon(null);
	                            description.setText("");
	                            getContentPane().removeAll();
                                
	                            FileInputStream excelFile = new FileInputStream(new File("Events.xlsx"));
	                            XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
	                            Sheet sheet = workbook.getSheetAt(0);

	                            Iterator<Row> rowIterator = sheet.iterator();
	                            rowIterator.next();
	                            while (rowIterator.hasNext()) {
	                                Row row = rowIterator.next();

	                                // Get the cell values for this row
	                                Cell nameCell = row.getCell(0);
	                                Cell imageCell = row.getCell(1);
	                                Cell descriptionCell = row.getCell(3);
	                                Cell dateCell = row.getCell(2);

	                                // Extract the string values for each cell
	                                String name = nameCell.getStringCellValue();
	                                String imageName = imageCell.getStringCellValue();
	                                String descriptionText = descriptionCell.getStringCellValue();
	                                String date = "";
	                                
	                                if (dateCell.getCellType() == CellType.NUMERIC) {
	            	                    java.util.Date date2 = dateCell.getDateCellValue();
	            	                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	            	                    date = dateFormat.format(date2);
	            	                } else {
	            	                    date = dateCell.getStringCellValue();
	            	                }
	                                

	                                String[] myArray = eventList.getSelectedValue().split(" - ");
	                                String Eq = myArray[0];
	                                if (!name.equals(Eq)) {
	                                    continue;
	                                } else {
	                                	
	                                	description = new JTextArea(name + " - " + date + "\n\n" + descriptionText);
	                        	        description.setEditable(false);
	                        	        description.setLineWrap(true);
	                                    description.setWrapStyleWord(true);
	                        	        description.setFont(new Font("Courier New", Font.BOLD, 18));
	                        	        JScrollPane scrollPane = new JScrollPane(description);
	                                    scrollPane.setBounds(569, 359, 551, 671);
	                                    getContentPane().add(scrollPane);
	                                	
	                                    ImageIcon icon = new ImageIcon("Event Images/" + imageName);
	                                    Image image = icon.getImage().getScaledInstance(538, 671, Image.SCALE_SMOOTH);
	                                    LabelImage = new JLabel(new ImageIcon(image));
	                                    LabelImage.setBounds(21, 359, 538, 671);
	                                    getContentPane().repaint();
	                                    getContentPane().revalidate();
	                                    
	                                    eventScroll.setBounds(1130, 359, 745, 307);
	                                    weeklyScroll.setBounds(1130, 723, 745, 307);
	                                    getContentPane().add(eventScroll);
	                                    getContentPane().add(weeklyScroll);
	                                    getContentPane().add(Login);
	                                    getContentPane().add(Logo);
	                                    getContentPane().add(About);
	                                    getContentPane().add(Favourites);
	                                    getContentPane().add(Search);
	                                    getContentPane().add(LabelImage);
	                                    getContentPane().add(scrollPane);
	                                    
	                                    
	                                }
	                                
	                            }
	                        } catch (IOException e) {
	                            e.printStackTrace();
	                        }

	                    }
	                }
	            });
	                
	            //******************************************************************************************************
	            
	            try {
		            FileInputStream excelFile1 = new FileInputStream(new File("Books.xlsx"));
		            XSSFWorkbook workbook1 = new XSSFWorkbook(excelFile1);
		            Sheet sheet1 = workbook1.getSheetAt(0);
		            Iterator<Row> rowIterator1 = sheet1.iterator();
		            // Skip first row
		            rowIterator1.next();
		            for (int i = 1; i <= sheet1.getLastRowNum(); i++) {
		                Row row = rowIterator1.next();
		                
		                Cell nameCell = row.getCell(0);
		                Cell imageCell = row.getCell(1);

		                DataFormatter formatter = new DataFormatter();
		                Cell statusCell = row.getCell(3);
		                Cell descriptionCell = row.getCell(2);
		                
		                String name = nameCell.getStringCellValue();
		                String imageName = imageCell.getStringCellValue();
		                String description = formatter.formatCellValue(descriptionCell);
		                String status = formatter.formatCellValue(statusCell);
		                
		                if(!name.equals("") && !status.equals("") && !imageName.equals("") && !description.equals("")) {
		                	
		                	model1.addElement(i + ". " + name);
		                }
		                
		            	}}catch (IOException e) {e.printStackTrace();}
	                        
		            
	            weeklyList.addMouseListener(new MouseAdapter() {
	                public void mouseClicked(MouseEvent event) {
	                    if (event.getClickCount() == 1) {
	                        try {
	                        	
	                            LabelImage = new JLabel();
	                            description = new JTextArea();
	                            LabelImage.setIcon(null);
	                            description.setText("");
	                            getContentPane().removeAll();
                                
	                            FileInputStream excelFile = new FileInputStream(new File("Books.xlsx"));
	                            XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
	                            Sheet sheet = workbook.getSheetAt(0);

	                            Iterator<Row> rowIterator = sheet.iterator();
	                            rowIterator.next();
	                            for (int i = 0; i < 10 && rowIterator.hasNext(); i++) {
	                                Row row = rowIterator.next();

	                                // Get the cell values for this row
	                                Cell nameCell = row.getCell(0);
	                                Cell imageCell = row.getCell(1);
	                                Cell descriptionCell = row.getCell(2);
	                                Cell statusCell = row.getCell(3);

	                                // Extract the string values for each cell
	                                String name = nameCell.getStringCellValue();
	                                String imageName = imageCell.getStringCellValue();
	                                String descriptionText = descriptionCell.getStringCellValue();
	                                String status = statusCell.getStringCellValue();
	                                
	                                String[] myArray = weeklyList.getSelectedValue().split("\\. ");
	                                String Eq = myArray[1];
	                                if (!name.equals(Eq)) {
	                                    continue;
	                                } else {
	                                	
	                                	description = new JTextArea(name + " - " + status + "\n\n" + descriptionText);
	                        	        description.setEditable(false);
	                        	        description.setLineWrap(true);
	                                    description.setWrapStyleWord(true);
	                        	        description.setFont(new Font("Courier New", Font.BOLD, 18));
	                        	        JScrollPane scrollPane = new JScrollPane(description);
	                                    scrollPane.setBounds(569, 359, 551, 671);
	                                    getContentPane().add(scrollPane);
	                                	
	                                    ImageIcon icon = new ImageIcon("BookImages/" + imageName);
	                                    Image image = icon.getImage().getScaledInstance(538, 671, Image.SCALE_SMOOTH);
	                                    LabelImage = new JLabel(new ImageIcon(image));
	                                    LabelImage.setBounds(21, 359, 538, 671);
	                                    getContentPane().repaint();
	                                    getContentPane().revalidate();
	                                    
	                                    eventScroll.setBounds(1130, 359, 745, 307);
	                                    weeklyScroll.setBounds(1130, 723, 745, 307);
	                                    getContentPane().add(eventScroll);
	                                    getContentPane().add(weeklyScroll);
	                                    getContentPane().add(Login);
	                                    getContentPane().add(Logo);
	                                    getContentPane().add(About);
	                                    getContentPane().add(Favourites);
	                                    getContentPane().add(Search);
	                                    getContentPane().add(LabelImage);
	                                    getContentPane().add(scrollPane);
	                                    getContentPane().add(Login);
	                                    
	                                }
	                                
	                            }
	                        } catch (IOException e) {
	                            e.printStackTrace();
	                        }

	                    }
	                }
	            });
	                
	            
	            workbook.close();
	            excelFile.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	        //******************************************************************************************************************
	       
	        
	}
}
