package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import bean.adminBean;
import gui.CheckUsernamePassGUI;

public class CheckOSGUI extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JButton btnSubmit;
	JComboBox cb;
	
	public CheckOSGUI() {
		// TODO Auto-generated constructor stub
		setLayout(new BorderLayout());
		setContentPane(new JLabel(new ImageIcon(this.getClass().getResource("/image/black-back-ground.jpg"))));
		setLayout(null);
		
		setPreferredSize(new Dimension(1000, 800));
		setLocationRelativeTo(null);
		
		
		Font f=new Font("Serif", Font.ITALIC | Font.BOLD, 35);   // Creating font style and size for heading

		// step 3 : creating JLabel for Heading
				JLabel heading_lbl=new JLabel("Select Your Opertation System");
				heading_lbl.setBounds(300,150,1000,50);
				heading_lbl.setForeground(Color.white);
				heading_lbl.setFont(f);
				add(heading_lbl);
				
				
				Font f1=new Font("Serif",Font.BOLD,30);
				Font f2=new Font("Serif",Font.BOLD,20);
				
        
				String country[]={"Windows","MAC","Linux"};        
			    cb=new JComboBox(country);    
			    cb.setBounds(350, 250,300,50); 
			    cb.setFont(f1);
			    add(cb);        
			   
			    
			    btnSubmit =  new JButton("Submit");
				btnSubmit.setBounds(400,350,150,50);
				btnSubmit.setBackground(Color.WHITE);
				btnSubmit.setOpaque(true);
				btnSubmit.setBorderPainted(false);
				btnSubmit.setFont(new Font("Britannic Bold", Font.PLAIN, 30));
				add(btnSubmit);
				btnSubmit.addActionListener(this);
			    
			    
        
    	setSize(1000,800);  
		centerFrame();
	    setTitle("Progress Monitor Data Manager");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true); 
	}
	private void centerFrame() {

        Dimension windowSize = getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();

        int dx = centerPoint.x - windowSize.width / 2;
        int dy = centerPoint.y - windowSize.height / 2;    
        setLocation(dx, dy);
        
        
}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==btnSubmit){
			//this.setVisible(false);
			//new GroupGUI();
			/*String uname = (String) cb.getItemAt(cb.getSelectedIndex());  
			System.out.println("You selected  :"+ uname);*/
			
			String uname = (String) cb.getItemAt(cb.getSelectedIndex());  
			if (uname.equalsIgnoreCase("Windows")){
				
				//For Windows
				//JOptionPane.showMessageDialog(this, "You Select "+uname);
				
				Runtime r = Runtime.getRuntime();
				Process p = null;  
				File file = new File(this.getClass().getResource("/mysql/mysql-5.5.15-winx64.msi").getFile());
				String mysqlpath = file.getAbsolutePath();
				System.out.println(mysqlpath);
				try {
					p = r.exec("cmd /c "+mysqlpath);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}else if (uname.equalsIgnoreCase("Linux")){
				
				//For Linux
				JOptionPane.showMessageDialog(this, "In Progrss for  "+uname);
				
			}else {
				//For MAC
				JOptionPane.showMessageDialog(this, "In Progrss for "+uname);
				
			}
			
			System.exit(0);
			
			
		}
		
	}
	
	
	/*public static void main(String args[]){
		
		new CheckOSGUI();
	}*/
	
}
