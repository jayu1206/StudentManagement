package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
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

public class DecodePlotGUI extends JFrame implements ActionListener{
	
	JButton btnBack,btnContinue;
	JButton  btnMgroup,btnMstudents,btnMreport,btnMImportExport,btnMLogout,btnMmyProfile;
	JMenuItem group,students,report;
	JMenu menu;
	String classId,className;
	
	JLabel lblstudent,lblDataRange,lblAll,lblPlot,lblthrough;
	JTextField txtStudent,txtbegin,txtend;
	JRadioButton allRadio = new JRadioButton("   All");
    JRadioButton weekRadio = new JRadioButton("Weeks");
    JRadioButton indiStudDataRadio = new JRadioButton("Individual student data");
    JRadioButton studDataClsAvgRadio = new JRadioButton("Student data with class average");
    ButtonGroup bG = new ButtonGroup();
    ButtonGroup bG2 = new ButtonGroup();
    StudentBean bean=new StudentBean();
	
	DecodePlotGUI(StudentBean bean, String classId, String className){
		
		this.classId=classId;
		this.className=className;
		
		StudentDAO studDao= new StudentOpr();
        StudentBean studdata=studDao.getAllStudentsWithDecod_Rate_Data(bean.getId());
        this.bean = studdata;
		
		setLayout(new BorderLayout());
		setContentPane(new JLabel(new ImageIcon(this.getClass().getResource("/image/black-back-ground.jpg"))));
		setLayout(null);
		
		JMenuBar mb=new JMenuBar();
		mb.setBackground(new Color(135,206,250));
		mb.add(Box.createRigidArea(new Dimension(10,40)));

		btnMmyProfile = new JButton("My Profile");
		btnMmyProfile.addActionListener(this);
		btnMmyProfile.setBackground(new Color(135,206,250));
		btnMmyProfile.setBorder(null);
		btnMmyProfile.setBorderPainted(false);
		mb.add(btnMmyProfile);
		
        btnMgroup = new JButton("Groups");
		btnMgroup.addActionListener(this);
		btnMgroup.setBackground(new Color(135,206,250));
		btnMgroup.setBorderPainted(false);
		mb.add(btnMgroup); 
		
        setJMenuBar(mb);
       
       
		
		btnMstudents = new JButton("Student");
		btnMstudents.addActionListener(this);
		btnMstudents.setBackground(new Color(135,206,250));
		btnMstudents.setBorderPainted(false);
		mb.add(btnMstudents);  
        setJMenuBar(mb);
		
//		btnMreport = new JButton("Reports");
//		btnMreport.addActionListener(this);
//		btnMreport.setBackground(new Color(135,206,250));
//		btnMreport.setBorderPainted(false);
//		mb.add(btnMreport);  
//        setJMenuBar(mb);
		
		btnMImportExport = new JButton("Import / Export");
		btnMImportExport.addActionListener(this);
		btnMImportExport.setBackground(new Color(135,206,250));
		btnMImportExport.setBorderPainted(false);
		mb.add(btnMImportExport);  
        setJMenuBar(mb);
        
        btnMLogout = new JButton("Logout");
        btnMLogout.addActionListener(this);
        btnMLogout.setBackground(new Color(107,5,37));
        btnMLogout.setForeground(Color.white);
        btnMLogout.setBorderPainted(false);
		mb.add(btnMLogout);  
        setJMenuBar(mb);
        
		setPreferredSize(new Dimension(1000, 800));
		setLocationRelativeTo(null);
		
		
		 /*---------------------------------- Creating JLabel for Heading Text ------------------------------------------- */
		 
		Font f=new Font("Serif", Font.ITALIC | Font.BOLD, 25);   // Creating font style and size for heading

		// step 3 : creating JLabel for Heading
				JLabel heading_lbl=new JLabel("Decoding Data Plot Option");
				heading_lbl.setBounds(350,15,1000,50);
				heading_lbl.setForeground(Color.white);
				heading_lbl.setFont(f);
				add(heading_lbl);
				
				
				Font f1=new Font("Serif",Font.BOLD,30);
				Font f2=new Font("Serif",Font.BOLD,20);
				
				
			
				
					lblstudent = new JLabel("Class");
					lblstudent.setBounds(200,120,80,30); 
					lblstudent.setForeground(Color.white);
					lblstudent.setFont(f1);
					add(lblstudent);
					
					txtStudent = new JTextField(bean.getStudFirstName() + " "+bean.getStudLastName());
					txtStudent.setBounds(330,120,200,30); 
					txtStudent.setEditable(false);
					txtStudent.setFont(f2);
					add(txtStudent);
					
					
					lblDataRange = new JLabel("Data Range");
					lblDataRange.setBounds(200,180,300,40); 
					lblDataRange.setForeground(Color.white);
					lblDataRange.setFont(f1);
					add(lblDataRange);
					
				
					bG.add(allRadio);
			        bG.add(weekRadio);
			        allRadio.setSelected(true);
			      //  weekRadio.setSelected(true);
			        
			        allRadio.setBounds(250,250,150,40); 
			        allRadio.setBackground(Color.black);
			        allRadio.setForeground(Color.white);
			        allRadio.setFont(f2);
					add(allRadio);
					allRadio.addActionListener(this);
					
					weekRadio.setBounds(250,310,150,40); 
					weekRadio.setBackground(Color.black);
					weekRadio.setForeground(Color.white);
					weekRadio.setFont(f2);
					add(weekRadio);
					weekRadio.addActionListener(this);
					
					txtbegin = new JTextField("Begin");
					txtbegin.setBounds(450,310,100,30); 
					txtbegin.setEditable(false);
					txtbegin.setFont(f2);
					add(txtbegin);
					
					
					lblthrough = new JLabel("through");
					lblthrough.setBounds(600,305,100,40); 
					lblthrough.setForeground(Color.white);
					lblthrough.setFont(f1);
					add(lblthrough);
					
					txtend = new JTextField("End");
					txtend.setBounds(750,310,100,30); 
					txtend.setEditable(false);
					txtend.setFont(f2);
					add(txtend);
					
					
					lblPlot = new JLabel("Plot");
					lblPlot.setBounds(200,390,300,40); 
					lblPlot.setForeground(Color.white);
					lblPlot.setFont(f1);
					add(lblPlot);
					
					
					bG2.add(indiStudDataRadio);
					bG2.add(studDataClsAvgRadio);
					
					indiStudDataRadio.setBounds(250,450,250,40); 
					indiStudDataRadio.setBackground(Color.black);
					indiStudDataRadio.setForeground(Color.white);
					indiStudDataRadio.setFont(f2);
					indiStudDataRadio.setSelected(true);
					add(indiStudDataRadio);
					
//					studDataClsAvgRadio.setBounds(250,510,300,40); 
//					studDataClsAvgRadio.setBackground(Color.black);
//					studDataClsAvgRadio.setForeground(Color.white);
//					studDataClsAvgRadio.setFont(f2);
//					add(studDataClsAvgRadio);
					
					
					
					btnBack = new JButton("Back");
			         btnBack.setBounds(0,600,150,40);
			         btnBack.setBackground(Color.WHITE);
			         btnBack.setOpaque(true);
			         btnBack.setBorderPainted(false);
			         btnBack.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
			         add(btnBack);
			         btnBack.addActionListener(this);
			         
			         
			         btnContinue = new JButton("Continue");
			         btnContinue.setBounds(840,600,150,40);
			         btnContinue.setBackground(Color.WHITE);
			         btnContinue.setOpaque(true);
			         btnContinue.setBorderPainted(false);
			         btnContinue.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
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
		
		if(e.getSource() == btnBack){
			
			
			setVisible(false);
			new StudentDetailsInfoGUI(bean, classId, className);
			
		}
		
		
		if(e.getSource()==  btnMstudents){
			setVisible(false);
    		new StudentGUI(classId, className);
		}
		
		if(e.getSource()==btnMgroup){
			dispose();
			new GroupGUI();
			
		}
		
		if(e.getSource()==btnMLogout){
			System.exit(0);
		}
		
		if(e.getSource() == btnMmyProfile){
			this.setVisible(false);
			new MyProfileGUI();
		}
		
		if(e.getSource()==btnMImportExport){
			setVisible(false);
			new GroupStudImportExportGUI(classId, className);
		}
		
		if(e.getSource() == btnContinue){
			if(allRadio.isSelected() && indiStudDataRadio.isSelected()){
				setVisible(false);
				new DecodePlotGraphGUI(bean,classId,className,"All", "","");
			}
			
			if(allRadio.isSelected() && studDataClsAvgRadio.isSelected()){
				setVisible(false);
				new DecodePlotGraphGUI(bean,classId,className,"Avg","","");
			}
			
			if (weekRadio.isSelected() && indiStudDataRadio.isSelected()){
				
				setVisible(false);
				String txtBegin = txtbegin.getText();
				String txtEnd = txtend.getText();
				new DecodePlotGraphGUI(bean,classId,className,"All", txtBegin, txtEnd);
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
		
		
	}
	
	/*public static void main(String args[]){
		new DecodePlotGUI(null,"", "");
		
	}*/

}
