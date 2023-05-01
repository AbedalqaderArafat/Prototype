import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import javax.imageio.ImageIO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Search3 extends JFrame implements ListSelectionListener, ActionListener {
    private JList<String> bookList;
    private JScrollPane bookScroll;
    private JButton searchButton;
    private JTextField searchField;
    public Search3(String username) throws IOException {
        super("Book List");
        getContentPane().setBackground(new Color(154,198,255));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        setSize(800, 600);



        // Create book list and add to scroll pane
        DefaultListModel<String> model = new DefaultListModel<String>();
        bookList = new JList<String>(model);
        bookList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                renderer.setFont(new Font("Courier New", Font.BOLD, 20)); // set the desired font here
                return renderer;
            }
        });
        bookList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        bookList.addListSelectionListener(this);
        bookScroll = new JScrollPane(bookList);
        bookScroll.setBounds(26, 394, 1845, 621);
        getContentPane().add(bookScroll);

        // Create search field and button
        searchField = new JTextField();
        searchField.setBounds(26, 331, 1322, 52);
        getContentPane().add(searchField);
        searchButton = new JButton("Search");
        searchButton.setBounds(1358, 298, 513, 86);
        searchButton.addActionListener(this);
        getContentPane().add(searchButton);
        getContentPane().setLayout(null);
		ImageIcon Icon = new ImageIcon("KUSTAR_Logo.jpg");
		Image image = Icon.getImage().getScaledInstance(1904, 315, Image.SCALE_SMOOTH);
	    ImageIcon newIcon = new ImageIcon(image);
		JLabel Logo = new JLabel(newIcon);
		Logo.setBounds(0, 0, 1904, 275);
		getContentPane().add(Logo);
		
		String[] genres = {"Select Genre","Action", "Adventure", "Comedy", "Drama", "Horror", "Mystery", "Romance", "Sci-Fi", "Thriller", "Self-help"};
		 JComboBox genreComboBox = new JComboBox(genres);
	        genreComboBox.setBounds(26, 298, 1322, 22);
	        getContentPane().add(genreComboBox);
		//*************************************************************************************************************=
		 try {
	            FileInputStream excelFile = new FileInputStream(new File("Books.xlsx"));
	            XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
	            Sheet sheet = workbook.getSheetAt(0);
	            Iterator<Row> rowIterator = sheet.iterator();
	            rowIterator.next();
	            while (rowIterator.hasNext()) {
	                Row row = rowIterator.next();
	                Cell nameCell = row.getCell(0);
	                Cell imageCell = row.getCell(1);
	                Cell descriptionCell = row.getCell(2);
	                Cell rentalCountCell = row.getCell(5);
	                Cell maxRentalCountCell = row.getCell(4);
	                Cell statusCell = row.getCell(3);
	                String name = nameCell.getStringCellValue();
	                String imageName = imageCell.getStringCellValue();
	                String imagePath = "BookImages/" + imageName;
	                File imageFile = new File(imagePath);
	                ImageIO.read(imageFile);
	                descriptionCell.getStringCellValue();
	                int rentalCount = (int) rentalCountCell.getNumericCellValue();
	                maxRentalCountCell.getNumericCellValue();
	                int counterValue = rentalCount;
	                String status = statusCell.getStringCellValue();
	                // Only change status if counter value exceeds x
	                if (counterValue > 10) {
	                    if (status.equals("available")) {
	                        statusCell.setCellValue("Rented Out");
	                    } else if (status.equals("Rented Out")) {
	                        statusCell.setCellValue("available");
	                    }
	                }
	                
	                String S = name;
	                
	                model.addElement(S);
	                
	            }
	            workbook.close();
	            excelFile.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		 //********************************************************************************************************************
		
        setVisible(true);
        Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();

        setSize(1920, 1080);

        setLocation(screenDim.width/2 - getWidth()/2, screenDim.height/2 - getHeight()/2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        JButton Back = new JButton("←");
        Back.setFocusPainted(false);
        Back.setBackground(new Color(255,247, 247));
    
    
        bookList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    String name = (String) bookList.getSelectedValue();

                    int result = JOptionPane.showConfirmDialog(Search3.this, "Do you want to rent " + name + "?",
                            "Confirmation", JOptionPane.YES_NO_OPTION);

                    if (result == JOptionPane.YES_OPTION) { 
                        // Check if the user is signed in
                        
                    }
                }
            }
        });
        
        JTextField searchField = new JTextField();
        searchField.setBounds(26, 331, 1322, 52);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(1358, 331, 513, 52);
        searchButton.setFocusPainted(false);
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	getContentPane().removeAll();
            	Logo.setBounds(0, 0, 1904, 275);
        		getContentPane().add(Logo);
        		bookScroll.setBounds(25, 390, 1850, 630);
        	    getContentPane().add(bookScroll);
        	    getContentPane().add(searchField);
                getContentPane().add(searchButton);
                getContentPane().add(Back);
                getContentPane().add(genreComboBox);
                
                String searchText = searchField.getText().toLowerCase();
                DefaultListModel<String> model = new DefaultListModel<>();
                try {
                    FileInputStream excelFile = new FileInputStream(new File("Books.xlsx"));
                    XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
                    Sheet sheet = workbook.getSheetAt(0);
                    Iterator<Row> rowIterator = sheet.iterator();
                    rowIterator.next();
                    while (rowIterator.hasNext()) {
                        Row row = rowIterator.next();
                        Cell nameCell = row.getCell(0);
                        Cell genreCell = row.getCell(6);
                        String bookName = nameCell.getStringCellValue();
                        String genre = genreCell.getStringCellValue();
                        String selectedGenre = (String) genreComboBox.getSelectedItem(); 

                        if (selectedGenre.equals("Select Genre") || selectedGenre.equals(genre)) {
                            if (searchText.isEmpty() || bookName.toLowerCase().contains(searchText)) {
                                model.addElement(bookName);
                            }
                        } else {
                            if (selectedGenre.equals(genre) && (searchText.isEmpty() || bookName.contains(searchText))) {
                                model.addElement(bookName);
                            }
                        }
                    }
                    bookList.setModel(model);
                    excelFile.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        
        getContentPane().add(searchField);
        getContentPane().add(searchButton);
       

        

        Back.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        		FrontPage2 S = new FrontPage2(username);
        		S.setVisible(true);
        		
        	}
        });
        Back.setFont(new Font("Arial", Font.PLAIN, 20));
        Back.setBounds(0, 0, 82, 30);
        getContentPane().add(Back);
       

        	
        
        bookList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                if (event.getClickCount() == 1) {
                    try {
                    	
                        JLabel label = new JLabel();
                        JTextArea description = new JTextArea();
                        label.setIcon(null);
                        description.setText("");

                        getContentPane().removeAll();

                        FileInputStream excelFile = new FileInputStream(new File("Books.xlsx"));
                        XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
                        Sheet sheet = workbook.getSheetAt(0);
                        Iterator<Row> rowIterator = sheet.iterator();
                        rowIterator.next();
                        while (rowIterator.hasNext()) {

                            Row row = rowIterator.next();
                            Cell nameCell = row.getCell(0);
                            Cell imageCell = row.getCell(1);
                            Cell descriptionCell = row.getCell(2);
                            String imageName = imageCell.getStringCellValue();
                            String descriptionText = descriptionCell.getStringCellValue();
                            if (!nameCell.getStringCellValue().equals(bookList.getSelectedValue())) {
                                continue;
                            } else {

                                ImageIcon icon = new ImageIcon("BookImages/" + imageName);
                                Image image = icon.getImage().getScaledInstance(441, 619, Image.SCALE_SMOOTH);
                                label = new JLabel(new ImageIcon(image));
                                label.setBounds(26, 396, 441, 619);
                                getContentPane().add(label);

                                description = new JTextArea(descriptionText);
                                description.setLineWrap(true);
                                description.setWrapStyleWord(true);
                                description.setFont(new Font("Courier New", Font.BOLD, 18));
                                description.setEditable(false);
                                JScrollPane scrollPane = new JScrollPane(description);
                                scrollPane.setBounds(487, 394, 406, 619);
                                getContentPane().add(scrollPane);

                                bookScroll.setBounds(903, 394, 968, 621);
                                getContentPane().add(bookScroll);
                                getContentPane().validate();
                                getContentPane().repaint();
                                
                                getContentPane().add(searchField);
                                searchButton.setBounds(1358, 298, 513, 86);
                                getContentPane().add(searchButton);
                                getContentPane().add(Back);
                                Logo.setBounds(0, 0, 1904, 275);
                        		getContentPane().add(Logo);
                        		getContentPane().add(genreComboBox);
                        		
                            }}} catch (IOException e) {e.printStackTrace();}}}});}
    
    

   
    

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

