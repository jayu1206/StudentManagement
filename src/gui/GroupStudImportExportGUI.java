package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import abstrac.GroupDAO;
import abstrac.StudentDAO;
import bean.GroupBean;
import bean.StudentBean;
import manegement.GroupOpr;
import manegement.StudentOpr;
import process.CSVUtils;

public class GroupStudImportExportGUI extends javax.swing.JFrame implements java.awt.event.ActionListener {
	JButton btnSubmit;
	JButton btnDelete;
	JButton btnBack;
	JButton btnExit;
	JButton btnMgroup;
	JButton btnMstudents;
	JButton btnMreport;
	JButton btnMImportExport,btnMLogout;
	JButton btnImport;
	JButton btnExport;
	JMenuItem group;
	JMenuItem students;
	JMenuItem report;
	JMenu menu;
	String classId;
	String className;
	JComboBox cbGrpExportList;
	JComboBox cbStudentList;
	JComboBox cbGrpList;
	JLabel lblImport;
	JLabel lblExport;
	JLabel lblImportGFile;
	JLabel lblNewStudGroup;
	JLabel lblStudent;
	JLabel lblGroup;
	JLabel lblExportGFile;
	JLabel lblExportSFile;
	JTextField txtNewStudGroup;
	JTextField txtImportFilePath, txtExportFilePath;
//	DefaultTableModel model, model2;
	JTable jt;
	JButton btnAddDecoding;
	JButton btnSaveDecoding;
	JButton btnPloatDecoding;
	DefaultTableModel modelRate;
	JTable jtRate;
	JButton btnAddRate;
	JButton btnSaveRate;
	JButton btnPloatRate;
	JButton btn1;
	JButton btn2;
	JButton btn3;
	JButton btn4;
	JMenuBar mb;
	JMenu file;
	JMenuItem open;
	JTextArea ta;
	JFileChooser fileChooser;
	JRadioButton r1;
	JRadioButton r2;
	ButtonGroup bg;
	//Vector model, model2;
	ArrayList<GroupBean> list;
	ArrayList<StudentBean> studList ;
	StudentDAO studDAO = new StudentOpr();

	GroupStudImportExportGUI(String classId, String className) {
		
		this.classId=classId;
		this.className=className;
		
		setLayout(new java.awt.BorderLayout());
		setContentPane(new JLabel(new ImageIcon(getClass().getResource("/image/black-back-ground.jpg"))));
		setLayout(null);

		JMenuBar mb=new JMenuBar();
		mb.setBackground(new Color(135,206,250));
		mb.add(Box.createRigidArea(new Dimension(10,40)));

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
		
		btnMreport = new JButton("Reports");
		btnMreport.addActionListener(this);
		btnMreport.setBackground(new Color(135,206,250));
		btnMreport.setBorderPainted(false);
		mb.add(btnMreport);  
        setJMenuBar(mb);
		
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

		setPreferredSize(new java.awt.Dimension(1000, 800));
		setLocationRelativeTo(null);

		Font f = new java.awt.Font("Serif", 3, 25);

		JLabel heading_lbl = new JLabel("Take Flight Decoding and Reading Rate Process Data Manager");
		heading_lbl.setBounds(180, 15, 1000, 50);
		heading_lbl.setForeground(Color.white);
		heading_lbl.setFont(f);
		add(heading_lbl);

		lblImport = new JLabel("Import");
		lblImport.setBounds(200, 150, 110, 40);
		lblImport.setFont(f);
		getForeground();
		lblImport.setForeground(Color.white);
		add(lblImport);

		r1 = new javax.swing.JRadioButton("Group");
		r2 = new javax.swing.JRadioButton("Student");
		r1.setBounds(250, 200, 100, 30);
		r2.setBounds(250, 250, 100, 30);
		r1.addActionListener(this);
		r2.addActionListener(this);
		bg = new javax.swing.ButtonGroup();
		bg.add(r1);
		bg.add(r2);
		add(r1);
		add(r2);
		/*setSize(300, 300);
		setLayout(null);
		setVisible(true);*/

		lblImportGFile = new JLabel("Import File :");
		lblImportGFile.setBounds(400, 180, 150, 60);
		lblImportGFile.setFont(f);
		getForeground();
		lblImportGFile.setForeground(Color.white);
		add(lblImportGFile);

		lblNewStudGroup = new JLabel("New Student Group :");
		lblNewStudGroup.setBounds(400, 270, 250, 60);
		lblNewStudGroup.setFont(f);
		getForeground();
		lblNewStudGroup.setForeground(Color.white);
		add(lblNewStudGroup);

		txtImportFilePath = new JTextField();
		txtImportFilePath.setBounds(550, 200, 130, 30);
		add(txtImportFilePath);
		
		txtExportFilePath = new JTextField();
		txtExportFilePath.setBounds(700, 420, 130, 30);
		add(txtExportFilePath);

		btn3 = new JButton("File");
		btn3.setBounds(700, 200, 50, 30);
		btn3.setBackground(Color.WHITE);
		btn3.setOpaque(true);
		btn3.setBorderPainted(false);
		btn3.setFont(new java.awt.Font("Britannic Bold", 0, 15));
		add(btn3);
		getContentPane().add(btn3);
		btn3.addActionListener(this);

		Vector model = new Vector();
		
		GroupDAO dao = new GroupOpr();		
		list = dao.getAllGroups();		
		
		for (bean.GroupBean bean : list) {
			model.addElement(new Item(bean.getGroupID(), bean.getGroupName()));
		}		
		
//		model2.addElement(new Item(0, "All"));
//		for(StudentBean bean : studList ){
//        	// model2.addRow(new Object[]{bean.getId(),bean.getStudFirstName()+" "+bean.getStudLastName(),bean.getGrade(),bean.getDob(),bean.getStDate()});
//        	 model2.addElement(new Item(bean.getId(), bean.getStudFirstName()+" "+ bean.getStudLastName()));
//        }

		cbGrpList = new JComboBox(model);
		cbGrpList.setBounds(650, 290, 130, 30);
		cbGrpList.setRenderer(new ItemRenderer());
		cbGrpList.addActionListener(this);
		add(cbGrpList);
		
		
		
		cbStudentList = new JComboBox();
		cbStudentList.setBounds(400, 460, 90, 30);
		//cbStudentList.addActionListener(this);
		add(cbStudentList);
		
		cbGrpExportList = new JComboBox(model);
		cbGrpExportList.setBounds(400, 420, 90, 30);
		cbGrpExportList.setRenderer(new ItemRenderer());
		cbGrpExportList.addActionListener(this);
		add(cbGrpExportList);
		

		btnImport = new JButton("Import");
		btnImport.setBounds(400, 350, 100, 30);
		btnImport.setBackground(Color.WHITE);
		btnImport.setOpaque(true);
		btnImport.setBorderPainted(false);
		btnImport.setFont(new Font("Britannic Bold", 0, 15));
		add(btnImport);
		getContentPane().add(btnImport);
		btnImport.addActionListener(this);
		
		btnExport = new JButton("Export");
		btnExport.setBounds(400, 520, 100, 30);
		btnExport.setBackground(Color.WHITE);
		btnExport.setOpaque(true);
		btnExport.setBorderPainted(false);
		btnExport.setFont(new Font("Britannic Bold", 0, 15));
		add(btnExport);
		getContentPane().add(btnExport);
		btnExport.addActionListener(this);
		
		lblExport = new JLabel("Export");
		lblExport.setBounds(200, 370, 110, 40);
		lblExport.setFont(f);
		getForeground();
		lblExport.setForeground(Color.white);
		add(lblExport);
		

		lblGroup = new JLabel("Group");
		lblGroup.setBounds(250, 410, 110, 40);
		lblGroup.setFont(f);
		getForeground();
		lblGroup.setForeground(Color.white);
		add(lblGroup);

		lblStudent = new JLabel("Student");
		lblStudent.setBounds(250, 450, 110, 40);
		lblStudent.setFont(f);
		getForeground();
		lblStudent.setForeground(Color.white);
		add(lblStudent);

		lblExportGFile = new JLabel("Export File :");
		lblExportGFile.setBounds(550, 400, 150, 60);
		lblExportGFile.setFont(f);
		getForeground();
		lblExportGFile.setForeground(Color.white);
		add(lblExportGFile);

		btn1 = new JButton("File");
		btn1.setBounds(850, 420, 50, 30);
		btn1.setBackground(Color.WHITE);
		btn1.setOpaque(true);
		btn1.setBorderPainted(false);
		btn1.setFont(new Font("Britannic Bold", 0, 15));
		add(btn1);
		getContentPane().add(btn1);
		btn1.addActionListener(this);

		btnBack = new JButton("Back");
		btnBack.setBounds(0, 600, 150, 40);
		btnBack.setBackground(Color.WHITE);
		btnBack.setOpaque(true);
		btnBack.setBorderPainted(false);
		btnBack.setFont(new Font("Britannic Bold", 0, 15));
		add(btnBack);
		getContentPane().add(btnBack);
		btnBack.addActionListener(this);

		btnExit = new JButton("Exit");
		btnExit.setBounds(840, 600, 150, 40);
		btnExit.setBackground(Color.WHITE);
		btnExit.setOpaque(true);
		btnExit.setBorderPainted(false);
		btnExit.setFont(new java.awt.Font("Britannic Bold", 0, 15));
		add(btnExit);
		getContentPane().add(btnExit);
		btnExit.addActionListener(this);

		setSize(1000, 800);
		centerFrame();
		setTitle("Progress Monitor Data Manager");
		//setDefaultCloseOperation(3);

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

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn1) {
			JFileChooser chooser;
			String choosertitle = "Test";
			int result;
	        
		    chooser = new JFileChooser(); 
		    chooser.setCurrentDirectory(new java.io.File("."));
		    chooser.setDialogTitle(choosertitle);
		    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);		    
		    chooser.setAcceptAllFileFilterUsed(false);
   
		    if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
		    	
		    	File path = chooser.getSelectedFile();
		    	txtExportFilePath.setText(path.toString());
		    
		     }else {
		    	 System.out.println("No Selection ");
		     }	
			
		}

		if(e.getSource()==btnMLogout){
			System.exit(0);
		}
		
		if (e.getSource() == btn3) {
			this.fileChooser = new JFileChooser();
			this.fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			int result = this.fileChooser.showOpenDialog(this);
			if (result == 0) {
				File selectedFile = this.fileChooser.getSelectedFile();
				txtImportFilePath.setText(selectedFile.getAbsolutePath());
				System.out.println("Selected file: " + selectedFile.getAbsolutePath());
			}
		}


		if (r1.isSelected()) {
			cbGrpList.setEnabled(false);
			
			if (e.getSource() == btnImport) {
				if (txtImportFilePath.getText().length() <= 0) {
					JOptionPane.showMessageDialog(this, "Please Select File");
				} else {
					//group import file csv
					
					String fileName = txtImportFilePath.getText();
					Path pathToFile = Paths.get(fileName);
					boolean flag = false;
					try{
						
						BufferedReader br = Files.newBufferedReader(pathToFile,
				                StandardCharsets.US_ASCII);
						String line = br.readLine();
					
						while ((line = br.readLine()) != null) {
				            if (!line.isEmpty()) {
				                // use comma as separator
				                 String[] data = line.split(",");
				                 if (data.length != 0){
				                	 
				                	 GroupBean group = new GroupBean();
									 GroupOpr dao=new GroupOpr();
									 group.setGroupName(data[0]);
									 group.setStartDate(data[1]);
									 flag = dao.insertGroups(group);
				                 }else{
						        		JOptionPane.showMessageDialog(this,"File is Empty");
						        		break;
						         }                 
								 
				            }
				        }
						if(flag){
							JOptionPane.showMessageDialog(this,"Groups Created");
							this.setVisible(false);
							new GroupGUI();
						}else{
							JOptionPane.showMessageDialog(this,"Groups not created Please try again");
						}
						
					}catch(Exception ioe){
						ioe.printStackTrace();
					}
				}
			}
		}

		if (r2.isSelected()) {
			cbGrpList.setEnabled(true);
			if (e.getSource() == btnImport) {
				
				if (txtImportFilePath.getText().length() <= 0) {
					JOptionPane.showMessageDialog(this, "Please Select File.");
				}else{
					
					//System.out.println("Checked and pressed");
					String fileName = txtImportFilePath.getText();
					Path pathToFile = Paths.get(fileName);
					StudentBean bean=new StudentBean();
					StudentDAO dao=new StudentOpr();
					boolean done = false;
					try{
						
						BufferedReader br = Files.newBufferedReader(pathToFile,
				                StandardCharsets.US_ASCII);
						String line = br.readLine();
						Item item = (Item)cbGrpList.getSelectedItem();
						while ((line = br.readLine()) != null) {
				            if (!line.isEmpty()) {
							
							 String[] data = line.split(",");
							 if (data.length != 0){
								 
								 bean.setGroupId(item.getId());
								 bean.setStudFirstName(data[2]);
								 bean.setStudLastName(data[3]);
								 bean.setGrade(Integer.parseInt(data[4]));
								 bean.setDob(data[5]);
								 bean.setStDate(data[6]);
								 bean.setTeacher(data[7]);	
								 bean.setAge(data[8]);	
								 done = dao.insertStudent(bean);
							 }else{
								 JOptionPane.showMessageDialog(this,"File is Empty");
					        	 break;
							}									        		 
						}
					}
					if(done){
			        		JOptionPane.showMessageDialog(this,"Students Added Successfully");
			        		setVisible(false);
			        		new StudentGUI(classId, className);
			        }
				}catch(Exception ioe){
					ioe.printStackTrace();
				}	
			}			
							
		}
	}
	if (e.getSource() == cbGrpExportList) {
		
		Item item = (Item)cbGrpExportList.getSelectedItem();
		int grpId = item.getId();
		studList = studDAO.getAllStudents(grpId+"");
		cbStudentList.removeAllItems();
		for(StudentBean bean : studList ){
			cbStudentList.addItem(new Item(bean.getId(), bean.getStudFirstName()+" "+ bean.getStudLastName()));
        }
		cbStudentList.setRenderer(new ItemRenderer());
		add(cbStudentList);
			
	}
	
	if(e.getSource()==btnExit){
		System.exit(0);
		
	}
	
	if(e.getSource()==btnMgroup){
		this.setVisible(false);
		new GroupGUI();
	}
	
	if(e.getSource()==btnMstudents){
		this.setVisible(false);
		new StudentGUI("", "");
	}
	
	//Export button	
	if (e.getSource() == btnExport) {

		if (txtExportFilePath.getText().length() <= 0) {
			JOptionPane.showMessageDialog(this, "Please Select Folder for File Save.");
		}else{
			
			Item studItem = (Item)cbStudentList.getSelectedItem();

			if (studItem != null && studItem.getId() != 0){
				//By id of student All
				
				try{
					Item item = (Item)cbGrpExportList.getSelectedItem();
					int grpId = item.getId();
					studList = studDAO.getAllStudents(grpId+"");
					String csvFile = txtExportFilePath.getText()+"/ExportFile.csv";
			        FileWriter writer = new FileWriter(csvFile);
			        List<String> stud = new ArrayList<String>(); 
			        stud.add(" ");
			        stud.add("GROUP ID");
			        stud.add("FIRST NAME");
			        stud.add("LAST NAME");
			        stud.add("GRADE");
			        stud.add("DATE OF BIRTH");
			        stud.add("START DATE");
			        stud.add("TEACHER");
			        stud.add("AGE");
			        stud.add(",");
			        stud.add("\n");
			        for (StudentBean bean: studList) {
			        	
			        	stud.add(bean.getGroupId()+"");
			        	stud.add(bean.getStudFirstName());
			        	stud.add(bean.getStudLastName());  
			        	stud.add(bean.getGrade()+"");
			        	stud.add(bean.getDob());
			        	stud.add(bean.getStDate());	
			        	stud.add(bean.getTeacher());	
			        	stud.add(bean.getAge()+"");
			        	stud.add("\n"); 
			        	
			        }
			        CSVUtils.writeLine(writer, stud);        
			        writer.flush();
			        writer.close();
			        JOptionPane.showMessageDialog(this,"File Created Successfully");
					
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}else {
				//All student by group id
				try{
					
					Vector model2 = new Vector();
					studList = studDAO.getAllStudents("");
					model2.addElement(new Item(0, "All"));
					String csvFile = txtExportFilePath.getText()+"/ExportFile.csv";
			        FileWriter writer = new FileWriter(csvFile);
			        List<String> stud = new ArrayList<String>(); 
			        
			        stud.add(" ");
			        stud.add("GROUP ID");
			        stud.add("FIRST NAME");
			        stud.add("LAST NAME");
			        stud.add("GRADE");
			        stud.add("DATE OF BIRTH");
			        stud.add("START DATE");
			        stud.add("TEACHER");
			        stud.add("AGE");
			        stud.add(",");
			        stud.add("\n");
			        for (StudentBean bean: studList) {
			        	
			        	stud.add(bean.getGroupId()+"");
			        	stud.add(bean.getStudFirstName());
			        	stud.add(bean.getStudLastName());  
			        	stud.add(bean.getGrade()+"");
			        	stud.add(bean.getDob());
			        	stud.add(bean.getStDate());	
			        	stud.add(bean.getTeacher());
			        	stud.add(bean.getAge());
			        	stud.add("\n"); 
			        	
			        }
			        CSVUtils.writeLine(writer, stud);        
			        writer.flush();
			        writer.close();
			        JOptionPane.showMessageDialog(this,"File Created Successfully");
					
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}		
	
	}
		
}

	

	public static void main(String[] args) {
		//new GroupStudImportExportGUI();
	}
}
