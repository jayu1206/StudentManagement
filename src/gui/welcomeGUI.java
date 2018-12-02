package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolBar;

public class welcomeGUI extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JMenu menu;
	JMenuItem group,students,report;
	JButton btnMgroup,btnMstudents,btnMreport,btnMImportExport,btnMLogout,btnMmyProfile;
	
	welcomeGUI() {
		// TODO Auto-generated constructor stub
		
		
		JMenuBar mb=new JMenuBar();
		
		mb.setBackground(new Color(225,39,38));
		mb.setOpaque(true);
		mb.add(Box.createRigidArea(new Dimension(10,40)));

		
		setLayout(new BorderLayout());
		setContentPane(new JLabel(new ImageIcon(this.getClass().getResource("/image/background2.png"))));
		setLayout(null);
        
		Font f=FontClass.MuseoSans300(35); 
		Font f2=FontClass.MuseoSans700(35);
		
		
		JLabel label= new JLabel("W E L C O M E");
		label.setBounds(370,100,900,230);
		label.setFont(f);
		add(label);
		
		label= new JLabel("PROGRESS MONITOR DATA MANAGER SYSTEM");
		label.setBounds(100,150,900,230);
		label.setFont(f2);
		add(label);
		
		
		btnMmyProfile = new JButton(new ImageIcon(this.getClass().getResource("/image/my profile OFF.png")));
		btnMmyProfile.setRolloverIcon(new ImageIcon(this.getClass().getResource("/image/my profile ON.png")));
		btnMmyProfile.setBounds(190, 300, 100, 100);
		btnMmyProfile.addActionListener(this);
		btnMmyProfile.setBorder(null);
		btnMmyProfile.setBorderPainted(false);
		btnMmyProfile.setOpaque(true);
		add(btnMmyProfile);
		
		
		btnMgroup = new JButton(new ImageIcon(this.getClass().getResource("/image/groups OFF.png")));
		btnMgroup.setRolloverIcon(new ImageIcon(this.getClass().getResource("/image/groups ON.png")));
		btnMgroup.setBounds(310, 300, 100, 100);
		btnMgroup.addActionListener(this);
		btnMgroup.setBorder(null);
		btnMgroup.setBorderPainted(false);
		btnMgroup.setOpaque(true);
		add(btnMgroup);
		
		btnMstudents = new JButton(new ImageIcon(this.getClass().getResource("/image/student OFF.png")));
		btnMstudents.setRolloverIcon(new ImageIcon(this.getClass().getResource("/image/student ON.png")));
		btnMstudents.setBounds(430, 300, 100, 100);
		btnMstudents.addActionListener(this);
		btnMstudents.setBorder(null);
		btnMstudents.setBorderPainted(false);
		btnMstudents.setOpaque(true);
		add(btnMstudents);
		
		
		btnMImportExport = new JButton(new ImageIcon(this.getClass().getResource("/image/import export OFF.png")));
		btnMImportExport.setRolloverIcon(new ImageIcon(this.getClass().getResource("/image/import export ON.png")));
		btnMImportExport.setBounds(550, 300, 100, 100);
		btnMImportExport.addActionListener(this);
		btnMImportExport.setBorder(null);
		btnMImportExport.setBorderPainted(false);
		btnMImportExport.setOpaque(true);
		add(btnMImportExport);
		
		
		btnMLogout = new JButton(new ImageIcon(this.getClass().getResource("/image/logout OFF.png")));
		btnMLogout.setRolloverIcon(new ImageIcon(this.getClass().getResource("/image/logout ON.png")));
		btnMLogout.setBounds(670, 300, 100, 100);
		btnMLogout.addActionListener(this);
		btnMLogout.setBorder(null);
		btnMLogout.setBorderPainted(false);
		btnMLogout.setOpaque(true);
		add(btnMLogout);
		
        
    	setSize(1000,800);  
		centerFrame();
	    setTitle("Progress Monitor Data Manager");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setResizable(false);
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
		
		if(e.getSource()==btnMgroup){
			synchronized (this) {
				new GroupGUI();
				this.setVisible(false);
			}
		}
		
		if(e.getSource()==btnMstudents){
			synchronized (this) {
				new StudentGUI("", "");
				this.setVisible(false);
			}
		}
		
		if(e.getSource()==btnMLogout){
			System.exit(0);
		}
		if(e.getSource()==btnMImportExport){
			synchronized (this) {
				new GroupStudImportExportGUI("", "");
				setVisible(false);
			}
		}
		if(e.getSource() == btnMmyProfile){
			synchronized (this) {
				new MyProfileGUI();
				this.setVisible(false);
			}
		}
		
	}
	
	
	public static void main(String args[]){
		
		new welcomeGUI();
	}
	
	
}


