import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class About extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public About() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(230, 241, 255));
		setContentPane(contentPane);
		Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(1920, 1080);

		ImageIcon Icon = new ImageIcon("KUSTAR_Logo.jpg");
		Image image = Icon.getImage().getScaledInstance(1904, 315, Image.SCALE_SMOOTH);
	    ImageIcon newIcon = new ImageIcon(image);

		JLabel Logo = new JLabel(newIcon);
		Logo.setBounds(0, 0, 1904, 275);
		contentPane.add(Logo);
		
        setLocation(screenDim.width/2 - getWidth()/2, screenDim.height/2 - getHeight()/2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane.setLayout(null);
	
	}

}
