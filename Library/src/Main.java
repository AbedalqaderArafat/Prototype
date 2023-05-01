import java.awt.EventQueue;

public class Main {
	
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					
					String ID = " ";
					FrontPage2 frame = new FrontPage2(ID);
					frame.setVisible(true);
					
				}
			});
		}
}
