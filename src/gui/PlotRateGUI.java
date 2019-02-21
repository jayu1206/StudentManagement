package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import abstrac.StudentDAO;
import bean.StudentBean;
import manegement.StudentOpr;

public class PlotRateGUI extends JFrame implements ActionListener{
	
	JButton btnBack,btnContinue;
	JButton  btnMgroup,btnMstudents,btnMreport,btnMImportExport,btnMLogout,btnMmyProfile;
	JMenuItem group,students,report;
	JMenu menu;
	String classId,className;
	
	JLabel lblstudent,lblDataRange,lblAll,lblPlot,lblthrough;
	JTextField txtStudent,txtbegin,txtend;
	JRadioButton allRadio = new JRadioButton(" All");
    JRadioButton weekRadio = new JRadioButton(" Text");
    JRadioButton indiStudDataRadio = new JRadioButton("Individual student data");
    JRadioButton studDataClsAvgRadio = new JRadioButton("Student data with class average");
    ButtonGroup bG = new ButtonGroup();
    ButtonGroup bG2 = new ButtonGroup();
    StudentBean bean=new StudentBean();
	
	PlotRateGUI(StudentBean bean, String classId, String className){
		System.out.println("PlotRateGUI");
		this.classId = classId;
		this.className = className;
		StudentDAO studDao = new StudentOpr();
		StudentBean studdata = studDao.getAllStudentsWithDecod_Rate_Data(bean.getId());
		this.bean = studdata;
		
		setLayout(new BorderLayout());
		setContentPane(new JLabel(new ImageIcon(this.getClass().getResource("/image/sky.png"))));
		setLayout(null);
		
		JMenuBar mb = null;
		String osname = System.getProperty("os.name");
		
		if (osname.contains("Mac")){
			mb=new JMenuBar(){
				 
	            @Override
	            public void paintComponent(Graphics g) {
	                Dimension size = this.getSize();
	                g.drawImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/image/menu_background.png")), 0, 0, size.width, size.height, this);
	            }
	        };
		}else{
			mb=new JMenuBar();
			mb.setBackground(new Color(193,39,35));
		}
		
		
		//mb.setBackground(new Color(193,39,35));
		mb.add(Box.createRigidArea(new Dimension(10,40)));

		
		JLabel lb=new JLabel("                                ");
		mb.add(lb);
		
		btnMmyProfile = new JButton(new ImageIcon(this.getClass().getResource("/image/my profile.png")));
		btnMmyProfile.addActionListener(this);
		btnMmyProfile.setBackground(new Color(193,39,35));
		btnMmyProfile.setBorder(null);
		btnMmyProfile.setBorderPainted(false);
		btnMmyProfile.setOpaque(false);
		btnMmyProfile.setContentAreaFilled(false);
		btnMmyProfile.setCursor(new Cursor(Cursor.HAND_CURSOR));
		mb.add(btnMmyProfile);
		setJMenuBar(mb);
		
		
		lb=new JLabel("     ");
		mb.add(lb);
		
		btnMgroup = new JButton(new ImageIcon(this.getClass().getResource("/image/groups.png")));
		btnMgroup.addActionListener(this);
		btnMgroup.setBackground(new Color(193,39,35));
		btnMgroup.setBorderPainted(false);
		btnMgroup.setOpaque(false);
		btnMgroup.setContentAreaFilled(false); 
		btnMgroup.setFocusPainted(false); 
		btnMgroup.setCursor(new Cursor(Cursor.HAND_CURSOR));
		mb.add(btnMgroup); 
        setJMenuBar(mb);
       
        
		btnMstudents = new JButton(new ImageIcon(this.getClass().getResource("/image/student.png")));
		btnMstudents.addActionListener(this);
		btnMstudents.setBackground(new Color(193,39,35));
		btnMstudents.setBorderPainted(false);
		btnMstudents.setOpaque(false);
		btnMstudents.setContentAreaFilled(false); 
		btnMstudents.setFocusPainted(false); 
		btnMstudents.setCursor(new Cursor(Cursor.HAND_CURSOR));
		mb.add(btnMstudents);  
        setJMenuBar(mb);
		

        
		btnMImportExport = new JButton(new ImageIcon(this.getClass().getResource("/image/import export.png")));
		btnMImportExport.addActionListener(this);
		btnMImportExport.setBackground(new Color(193,39,35));
		btnMImportExport.setBorderPainted(false);
		btnMImportExport.setOpaque(false);
		btnMImportExport.setContentAreaFilled(false); 
		btnMImportExport.setFocusPainted(false);
		btnMImportExport.setCursor(new Cursor(Cursor.HAND_CURSOR));
		mb.add(btnMImportExport);  
        setJMenuBar(mb);
        
        
        mb.add(Box.createHorizontalGlue());
        btnMLogout = new JButton(new ImageIcon(this.getClass().getResource("/image/logout.png")));
       // btnMLogout.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        btnMLogout.addActionListener(this);
        btnMLogout.setBackground(new Color(193,39,35));
        btnMLogout.setOpaque(false);
        btnMLogout.setBorderPainted(false);
        btnMLogout.setContentAreaFilled(false); 
        btnMLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnMLogout.setFocusPainted(false);
        
		mb.add(btnMLogout);  
        setJMenuBar(mb);
        
        lb=new JLabel("             ");
		mb.add(lb);
        
		setPreferredSize(new Dimension(1000, 800));
		setLocationRelativeTo(null);
		
		
		 /*---------------------------------- Creating JLabel for Heading Text ------------------------------------------- */
		 
		Font f=FontClass.MuseoSans700Italic(25);   // Creating font style and size for heading

		// step 3 : creating JLabel for Heading
				JLabel heading_lbl=new JLabel("Reading Rate Plot Option");
				heading_lbl.setBounds(350,55,1000,50);
				heading_lbl.setFont(f);
				add(heading_lbl);
				
				
				Font f1=FontClass.MuseoSans700(18); 
				Font f2=FontClass.MuseoSans500(20);
				
					lblstudent = new JLabel("Student");
					if (osname.contains("Mac")){
						lblstudent.setBounds(200,170,120,30);  
						}else{
							lblstudent.setBounds(200,170,100,30);  
						}
					
					
					lblstudent.setFont(f1);
					add(lblstudent);
					
					txtStudent = new JTextField(bean.getStudFirstName() + " "+bean.getStudLastName());
					txtStudent.setBounds(330,170,200,30); 
					txtStudent.setEditable(false);
					txtStudent.setFont(f2);
					add(txtStudent);
					
					
					lblDataRange = new JLabel("Data Range");
					lblDataRange.setBounds(200,230,300,40); 
					lblDataRange.setFont(f1);
					add(lblDataRange);
					
				
					bG.add(allRadio);
			        bG.add(weekRadio);
			        allRadio.setSelected(true);
			       // weekRadio.setSelected(true);
			        
			        allRadio.setBounds(250,300,150,40); 
			        allRadio.setOpaque(false);
			        allRadio.setContentAreaFilled(false);
			        allRadio.setBorderPainted(false);
			        allRadio.setFont(f2);
					add(allRadio);
					allRadio.addActionListener(this);
					
					weekRadio.setBounds(250,360,150,40); 
					weekRadio.setOpaque(false);
					weekRadio.setContentAreaFilled(false);
					weekRadio.setBorderPainted(false);
					weekRadio.setFont(f2);
					add(weekRadio);
					weekRadio.addActionListener(this);
					
					txtbegin = new JTextField("Begin");
					txtbegin.setBounds(420,360,100,30); 
					txtbegin.setEditable(false);
					txtbegin.setFont(f2);
					add(txtbegin);
					
					
					lblthrough = new JLabel("through");
					if (osname.contains("Mac")){
						lblthrough.setBounds(550,355,120,40); 
						}else{
							lblthrough.setBounds(550,355,100,40); 
						}
					
					lblthrough.setFont(f1);
					add(lblthrough);
					
					txtend = new JTextField("End");
					txtend.setBounds(650,360,100,30); 
					txtend.setEditable(false);
					txtend.setFont(f2);
					add(txtend);
					
					
					
					
					 btnBack = new JButton(new ImageIcon(this.getClass().getResource("/image/back.png")));
					 btnBack.setBounds(100,600,120,40);
			         btnBack.setOpaque(false);
			         btnBack.setContentAreaFilled(false);
			         btnBack.setBorderPainted(false);
			         btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
			         add(btnBack);
			         btnBack.addActionListener(this);
			         
			         
			         btnContinue = new JButton(new ImageIcon(this.getClass().getResource("/image/arrow right.png")));
			         btnContinue.setBounds(800,600,120,40);
			         btnContinue.setOpaque(false);
			         btnContinue.setContentAreaFilled(false);
			         btnContinue.setBorderPainted(false);
			         btnContinue.setCursor(new Cursor(Cursor.HAND_CURSOR));
			         add(btnContinue);
			         btnContinue.addActionListener(this);
					
				
				setSize(1000,800);
				centerFrame();
				setTitle("Progress Monitor Data Manager");
				/* setDefaultCloseOperation(javax.swing.
				       WindowConstants.DISPOSE_ON_CLOSE);*/
				WindowListener exitListener = new WindowAdapter() {
				
				   public void windowClosing(WindowEvent e) {
					   setVisible(false);
						new StudentDetailsInfoGUI(bean, classId, className);
				   }
				};
				addWindowListener(exitListener);
				// pack();
				setLayout(null); 
				setResizable(false);
				setVisible(true); 
				
		
	}
	
	
	private void centerFrame() {
		// TODO Auto-generated method stub
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
		
		if(e.getSource()==btnBack){
			synchronized (this) {
				new StudentDetailsInfoGUI(bean, classId, className);
				this.setVisible(false);
			}
			
		}		
		
		if(e.getSource()==  btnMstudents){
			synchronized (this) {
	    		new StudentGUI(classId, className);
	    		this.setVisible(false);
			}
		}
		
		if(e.getSource()==btnMgroup){
			synchronized (this) {
				new GroupGUI();
				this.dispose();
			}
			
		}
		
		if(e.getSource()==btnMImportExport){
			synchronized (this) {
				new GroupStudImportExportGUI("", "");
				this.setVisible(false);
			}
		}
		
		if(e.getSource() == btnContinue){
			if(allRadio.isSelected()){
				synchronized (this) {
					StudentDAO studDao= new StudentOpr();
					bean=studDao.getAllStudentsWithDecod_Rate_Data(bean.getId());
					//new DecodePlotGraphGUI(bean,classId,className);
					new PlotRateGraphGUI(bean,classId,className,"","");
					setVisible(false);
				}
				
			}
			if (weekRadio.isSelected()){
				
				synchronized (this) {
					String txtBegin = txtbegin.getText();
					String txtEnd = txtend.getText();
					new PlotRateGraphGUI(bean,classId,className, txtBegin, txtEnd);
					this.setVisible(false);
				}
			}
			
			
		}
		
		if(weekRadio.isSelected()){
			txtbegin.setText("Begin");
			txtend.setText("End");			
			txtbegin.setEditable(true);
			txtend.setEditable(true);
		}else{
			txtbegin.setText("");
			txtend.setText("");
			txtbegin.setEditable(false);
			txtend.setEditable(false);
		}
		
		 if(e.getSource()==btnMLogout){
	            System.exit(0);
		 }
		 
		 if(e.getSource() == btnMmyProfile){
			 synchronized (this) {
				new MyProfileGUI();
				this.setVisible(false);
			 }
			}
		
	}
	

	public static void main(String args[]){
		//new PlotRateGUI();
		
	}


}
