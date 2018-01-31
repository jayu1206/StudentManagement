package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
	JButton btnMgroup,btnMstudents,btnMreport,btnMImportExport,btnMLogout;
	
	welcomeGUI() {
		// TODO Auto-generated constructor stub
		
		/*JMenuBar mb=new JMenuBar();  
        menu=new JMenu("     Menu     ");  
		
        group=new JMenuItem("     Groups     ");  
        group.addActionListener(this);
        menu.add(group);
        mb.add(menu);  
        setJMenuBar(mb);
        
        students=new JMenuItem("     Student     ");  
        students.addActionListener(this);
        menu.add(students);
        mb.add(menu);  
        setJMenuBar(mb);
        
        report=new JMenuItem("     Reports     ");  
        report.addActionListener(this);
        menu.add(report);
        mb.add(menu);  
        setJMenuBar(mb);*/
		
		final JToolBar toolBar = new JToolBar();
		toolBar.setBackground(new Color(135,206,250));
		
		btnMgroup = new JButton("Groups");
		btnMgroup.setBackground(new Color(135,206,250));
		//btnMgroup.setBorderPainted(false);
		btnMgroup.addActionListener(this);
		toolBar.add(btnMgroup);
		
		btnMstudents = new JButton("Student");
		btnMstudents.setBackground(new Color(135,206,250));
		//btnMstudents.setBorderPainted(false);
		btnMstudents.addActionListener(this);
		toolBar.add(btnMstudents);
		
		btnMreport = new JButton("Reports");
		btnMreport.setBackground(new Color(135,206,250));
		//btnMreport.setBorderPainted(false);
		btnMreport.addActionListener(this);
		toolBar.add(btnMreport);
		
		btnMImportExport = new JButton("Import / Export");
		btnMImportExport.setBackground(new Color(135,206,250));
		//btnMImportExport.setBorderPainted(false);
		btnMImportExport.addActionListener(this);
		toolBar.add(btnMImportExport);
		
		btnMLogout = new JButton("Logout");
		btnMLogout.setBackground(new Color(107,5,37));
	    btnMLogout.setForeground(Color.white);
		//btnMImportExport.setBorderPainted(false);
		btnMLogout.addActionListener(this);
		toolBar.add(btnMLogout);
		
		
		getContentPane().add(toolBar, BorderLayout.NORTH);
		
		
		
		JPanel jsp1 = new JPanel(new BorderLayout());
	 	jsp1.setBackground(new Color(141, 31, 6));
	 	
	    ImageIcon image = new ImageIcon(this.getClass().getResource("/image/Image12_1.jpg"));
	    JLabel label = new JLabel("", image, JLabel.CENTER);
	    jsp1.add( label, BorderLayout.CENTER );
	    
	    
        JPanel jsp2 = new JPanel();	   
        image = new ImageIcon(this.getClass().getResource("/image/welcomBCK.jpg"));
	    label = new JLabel("", image, JLabel.CENTER);
	    label.setOpaque(true);
	    jsp2.add( label, BorderLayout.CENTER );
	    
	    label= new JLabel("Welcome to Progress Monitor Data Manager System");
	    jsp2.add(label);
       
        
		
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, 
                false, jsp1, jsp2);
        splitPane.setDividerLocation(200 + splitPane.getInsets().top);
        splitPane.setDividerSize(0);
        splitPane.setOneTouchExpandable(false);	        
        getContentPane().add(splitPane);
        
        
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
		
		if(e.getSource()==btnMgroup){
			this.setVisible(false);
			new GroupGUI();
		}
		
		if(e.getSource()==btnMstudents){
			this.setVisible(false);
			new StudentGUI("", "");
		}
		
		if(e.getSource()==btnMLogout){
			System.exit(0);
		}
		if(e.getSource()==btnMImportExport){
			setVisible(false);
			new GroupStudImportExportGUI();
		}
		
	}
	
	
	public static void main(String args[]){
		
		new welcomeGUI();
	}
	
	
}


