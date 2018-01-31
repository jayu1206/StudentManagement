import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class TabbedPane extends JFrame{
	
	 public TabbedPane() {
         
	       /* setTitle("Tabbed Pane");
	        setSize(500, 500);
	        JTabbedPane jtp = new JTabbedPane();
	        getContentPane().add(jtp);
	        
	        JPanel jp1 = new JPanel();
	        JPanel jp2 = new JPanel();
	        
	        JLabel label1 = new JLabel();
	        label1.setBounds(0,0,100,50);
	        label1.setText("You are in area of Tab1");
	        
	        
	        JLabel label2 = new JLabel();
	        label2.setText("You are in area of Tab2");
	        
	        
	        jp1.add(label1);
	        jp2.add(label2);
	        
	        
	        jtp.addTab("Tab1", label1);
	        jtp.addTab("Tab2", label2);*/
		 
		 
		 
		 /* example 2  */
		 
		 JPanel panel = new JPanel();
		    Box box = Box.createVerticalBox();
		    for (int i = 0; i < 100; i++) {
		      box.add(new JLabel("Hello!"));
		    }
		    panel.add(box);

		    JTabbedPane tab = new JTabbedPane();
		    JScrollPane scroll = new JScrollPane(panel);
		    scroll.setPreferredSize(new Dimension(200, 20));
		    tab.add(scroll, "Panel 1");
		    
		    
		    
		    JButton btn=new JButton("Hiii");
		    tab.add(btn);
		    
		    
		    
		    
		    setTitle("Tabbed Pane");
	        setSize(500, 500);
	        getContentPane().add(tab);
		 
	         
	    }
	 /*public static void main(String[] args) {
     
     TabbedPane tp = new TabbedPane();
     tp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     tp.setVisible(true);
      
 }*/

public static void main(String[] args) {
	    

	    /*JOptionPane.showMessageDialog(null, tab, "Test Tabbed",
	        JOptionPane.PLAIN_MESSAGE);*/
	
	TabbedPane tp = new TabbedPane();
    tp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    tp.setVisible(true);
	
	
	  }

}
