import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FavBooks extends JFrame {

	private JPanel contentPane;
	private JList<String> favList;
    private JScrollPane bookScroll;
	public FavBooks(String username) {
		
		String ID = username;
		getContentPane().setBackground(new Color(154,198,255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		 Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(1920, 1080);

		
		contentPane.setLayout(null);
		ImageIcon Icon = new ImageIcon("KUSTAR_Logo.jpg");
		Image image = Icon.getImage().getScaledInstance(1904, 315, Image.SCALE_SMOOTH);
	    ImageIcon newIcon = new ImageIcon(image);
		JLabel Logo = new JLabel(newIcon);
		Logo.setBounds(0, 0, 1904, 275);
		contentPane.add(Logo);
		
		DefaultListModel<String> model = new DefaultListModel<String>();
        favList = new JList<String>(model);
        favList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                renderer.setFont(new Font("Courier New", Font.BOLD, 20)); // set the desired font here
                return renderer;
            }
        });
        favList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        bookScroll = new JScrollPane(favList);
        bookScroll.setBounds(29, 329, 1845, 621);
        getContentPane().add(bookScroll);
		
        setLocation(screenDim.width/2 - getWidth()/2, screenDim.height/2 - getHeight()/2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        favList.addMouseListener(new MouseAdapter() {
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
                            if (!nameCell.getStringCellValue().equals(favList.getSelectedValue())) {
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
                        		getContentPane().add(Logo);
                        		
                        		
                            }}} catch (IOException e) {e.printStackTrace();}}}});}
	}


