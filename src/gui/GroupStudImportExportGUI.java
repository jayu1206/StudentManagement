package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.io.FileUtils;

import com.sun.xml.internal.fastinfoset.util.StringArray;

import abstrac.GroupDAO;
import abstrac.StudentDAO;
import bean.GroupBean;
import bean.StudentBean;
import bean.StudentDecoding;
import bean.StudentRate;
import manegement.GroupOpr;
import manegement.StudentOpr;
import process.CSVUtils;

public class GroupStudImportExportGUI extends JFrame implements ActionListener {

	JButton btnSubmit;
	JButton btnDelete;
	JButton btnBack;
	JButton btnExit, btnSample;
	JButton btnMgroup;
	JButton btnMstudents;
	JButton btnMreport;
	JButton btnMImportExport, btnMLogout,btnMmyProfile;
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
	// DefaultTableModel model, model2;
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
	ArrayList<GroupBean> list;
	ArrayList<StudentBean> studList;
	Vector studVector = new Vector();
	Vector v = new Vector();
	StudentDAO studDAO = new StudentOpr();
	ArrayList listId = new ArrayList();
	String osname = System.getProperty("os.name");
	
	GroupStudImportExportGUI(String classId, String className) {

		
		this.classId = classId;
		this.className = className;

		setLayout(new java.awt.BorderLayout());
		setContentPane(new JLabel(new ImageIcon(getClass().getResource("/image/blue.jpg"))));
		setLayout(null);

		JMenuBar mb = new JMenuBar();
		mb.setBackground(new Color(225,39,38));
		mb.add(Box.createRigidArea(new Dimension(10, 40)));

		
		if (osname.contains("Mac")){
			btnMmyProfile = new JButton("My Profile");
			btnMmyProfile.addActionListener(this);
			
			//btnMmyProfile.setBackground(new Color(135,206,250));
			//btnMmyProfile.setBorder(null);
			//btnMmyProfile.setBorderPainted(false);
			//btnMmyProfile.setOpaque(true);
			mb.add(btnMmyProfile); 
			setJMenuBar(mb);
			
			
			btnMgroup = new JButton("Groups");
			btnMgroup.addActionListener(this);
			btnMgroup.setBackground(new Color(225,39,38));
//			btnMgroup.setBorderPainted(false);
//			btnMgroup.setOpaque(true);
			mb.add(btnMgroup); 
			
	        setJMenuBar(mb);
	       
			
			btnMstudents = new JButton("Student");
			btnMstudents.addActionListener(this);
			btnMstudents.setBackground(new Color(225,39,38));
//			btnMstudents.setBorderPainted(false);
//			btnMstudents.setOpaque(true);
			mb.add(btnMstudents);  
	        setJMenuBar(mb);
			
			
			btnMImportExport = new JButton("Import / Export");
			btnMImportExport.addActionListener(this);
			btnMImportExport.setBackground(new Color(225,39,38));
//			btnMImportExport.setBorderPainted(false);
//			btnMImportExport.setOpaque(true);
			mb.add(btnMImportExport);  
	        setJMenuBar(mb);
	        
	        btnMLogout = new JButton("Logout");
	        btnMLogout.addActionListener(this);
	        btnMLogout.setBackground(new Color(225,39,38));
//	        btnMLogout.setForeground(Color.white);
//	        btnMLogout.setOpaque(true);
//	        btnMLogout.setBorderPainted(false);
			mb.add(btnMLogout);  
			
			
	        setJMenuBar(mb);
			
		}else{
			Font f2=new Font("Serif",Font.BOLD,20);
			
			btnMmyProfile = new JButton("My Profile");
			btnMmyProfile.addActionListener(this);
			btnMmyProfile.setBackground(new Color(225,39,38));
			btnMmyProfile.setForeground(Color.white);
			btnMmyProfile.setFont(f2);
			btnMmyProfile.setBorder(null);
			btnMmyProfile.setBorderPainted(false);
			btnMmyProfile.setOpaque(true);
			mb.add(btnMmyProfile); 
			setJMenuBar(mb);
			
			
			btnMgroup = new JButton("Groups");
			btnMgroup.addActionListener(this);
			btnMgroup.setBackground(new Color(225,39,38));
			btnMgroup.setForeground(Color.white);
			btnMgroup.setFont(f2);
			btnMgroup.setBorderPainted(false);
			btnMgroup.setOpaque(true);
			mb.add(btnMgroup); 
			
	        setJMenuBar(mb);
	       
			
			btnMstudents = new JButton("Student");
			btnMstudents.addActionListener(this);
			btnMstudents.setBackground(new Color(225,39,38));
			btnMstudents.setForeground(Color.white);
			btnMstudents.setFont(f2);
			btnMstudents.setBorderPainted(false);
			btnMstudents.setOpaque(true);
			mb.add(btnMstudents);  
	        setJMenuBar(mb);
			

			
			btnMImportExport = new JButton("Import / Export");
			btnMImportExport.addActionListener(this);
			btnMImportExport.setBackground(new Color(225,39,38));
			btnMImportExport.setForeground(Color.white);
			btnMImportExport.setFont(f2);
			btnMImportExport.setBorderPainted(false);
			btnMImportExport.setOpaque(true);
			mb.add(btnMImportExport);  
	        setJMenuBar(mb);
	        
	        btnMLogout = new JButton("Logout");
	        btnMLogout.addActionListener(this);
	        btnMLogout.setBackground(new Color(225,39,38));
	        btnMLogout.setForeground(Color.white);
	        btnMLogout.setFont(f2);
	        btnMLogout.setOpaque(true);
	        btnMLogout.setBorderPainted(false);
			mb.add(btnMLogout);  
			
			
	        setJMenuBar(mb);
		}
		

		setPreferredSize(new java.awt.Dimension(1000, 800));
		setLocationRelativeTo(null);

		Font f = new java.awt.Font("Serif", 3, 25);

		JLabel heading_lbl = new JLabel("Take Flight Decoding and Reading Rate Process Data Manager");
		heading_lbl.setBounds(180, 15, 1000, 50);
		heading_lbl.setForeground(Color.white);
		heading_lbl.setFont(f);
		add(heading_lbl);

		lblImport = new JLabel("Import");
		lblImport.setBounds(200, 120, 110, 40);
		lblImport.setFont(f);
		getForeground();
		lblImport.setForeground(Color.white);
		add(lblImport);

		r1 = new javax.swing.JRadioButton("Group");
		r2 = new javax.swing.JRadioButton("Student");
		r1.setBounds(250, 170, 100, 30);
		r2.setBounds(250, 220, 120, 30);
		r1.addActionListener(this);
		r2.addActionListener(this);
		bg = new javax.swing.ButtonGroup();
		r1.setFont(f);
		r2.setFont(f);
		r1.setForeground(Color.white);
		r2.setForeground(Color.white);
		r1.setBackground(new Color(0,57,166));
		r2.setBackground(new Color(0,57,166));
		bg.add(r1);
		bg.add(r2);
		add(r1);
		add(r2);
		/*
		 * setSize(300, 300); setLayout(null); setVisible(true);
		 */
		/*lblGroup = new JLabel("Group");
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
		*/
		
		
		lblImportGFile = new JLabel("Import File :");
		lblImportGFile.setBounds(400, 150, 150, 60);
		lblImportGFile.setFont(f);
		getForeground();
		lblImportGFile.setForeground(Color.white);
		add(lblImportGFile);

		lblNewStudGroup = new JLabel("New Student Group :");
		lblNewStudGroup.setBounds(400, 240, 250, 60);
		lblNewStudGroup.setFont(f);
		getForeground();
		lblNewStudGroup.setForeground(Color.white);
		add(lblNewStudGroup);

		txtImportFilePath = new JTextField();
		txtImportFilePath.setBounds(550, 170, 130, 30);
		add(txtImportFilePath);

		txtExportFilePath = new JTextField();
		txtExportFilePath.setBounds(700, 420, 130, 30);
		add(txtExportFilePath);

		btn3 = new JButton("File");
		btn3.setBounds(700, 170, 50, 30);
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
		model.addElement(new Item(0, "Select Group"));
		for (bean.GroupBean bean : list) {
			model.addElement(new Item(bean.getGroupID(), bean.getGroupName()));
		}

		// model2.addElement(new Item(0, "All"));
		// for(StudentBean bean : studList ){
		// // model2.addRow(new Object[]{bean.getId(),bean.getStudFirstName()+"
		// "+bean.getStudLastName(),bean.getGrade(),bean.getDob(),bean.getStDate()});
		// model2.addElement(new Item(bean.getId(), bean.getStudFirstName()+" "+
		// bean.getStudLastName()));
		// }

		cbGrpList = new JComboBox(model);
		cbGrpList.setBounds(650, 260, 130, 30);
		cbGrpList.setRenderer(new ItemRenderer());
		cbGrpList.addActionListener(this);
		add(cbGrpList);

		// cbStudentList = new JComboBox();
		// cbStudentList.setBounds(400, 460, 90, 30);
		// // cbStudentList.addActionListener(this);
		// add(cbStudentList);

		v.add("Select Option");
		cbStudentList = new JComboBox();
		cbStudentList.setBounds(400, 460, 130, 30);
		cbStudentList.addItem(v);
		cbStudentList.setRenderer(new Comborenderer());

		// cbStudentList = new CustomComboCheck(v,listId);

		add(cbStudentList);

		cbStudentList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				// TODO Auto-generated method stub
				ourItemSelected();

			}

			private void ourItemSelected() {
				// TODO Auto-generated method stub

				Object selected = cbStudentList.getSelectedItem();
				if (selected instanceof JCheckBox) {

					JCheckBox chk = (JCheckBox) selected;
					chk.setSelected(!chk.isSelected());
					repaint();
					
					// Get and Dispaly Last selcted items
					Object[] selection = chk.getSelectedObjects();
					if (selection != null) {

						for (Object lastItem : selection) {
							//JOptionPane.showMessageDialog(null, lastItem.toString());
							listId.add(lastItem.toString());
							System.out.println(lastItem.toString());
						}
					}else{
						//String str[]= chk.getText().split("-");
						//int temp
						listId.remove(chk.getText());
						System.out.println(chk.getText());
						
					}
				}

			}

		});

		cbGrpExportList = new JComboBox(model);
		cbGrpExportList.setBounds(400, 420, 90, 30);
		cbGrpExportList.setRenderer(new ItemRenderer());
		cbGrpExportList.addActionListener(this);
		add(cbGrpExportList);

		btnImport = new JButton("Import");
		btnImport.setBounds(400, 310, 100, 30);
		btnImport.setBackground(Color.WHITE);
		btnImport.setOpaque(true);
		btnImport.setBorderPainted(false);
		btnImport.setFont(new Font("Britannic Bold", 0, 15));
		add(btnImport);
		getContentPane().add(btnImport);
		btnImport.addActionListener(this);
		
		btnSample = new JButton("Import Format");
		btnSample.setBounds(530, 310, 200, 30);
		btnSample.setBackground(Color.WHITE);
		btnSample.setOpaque(true);
		btnSample.setBorderPainted(false);
		btnSample.setFont(new Font("Britannic Bold", 0, 15));
		add(btnSample);
		getContentPane().add(btnSample);
		btnSample.addActionListener(this);

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
		// setDefaultCloseOperation(3);
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

			} else {
				System.out.println("No Selection ");
			}

		}

		if (e.getSource() == btnMLogout) {
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
				}
				else {
					// group import file csv

					String fileName = txtImportFilePath.getText();
					Path pathToFile = Paths.get(fileName);
					boolean flag = false;
					try {

						BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII);
						String line = br.readLine();

						while ((line = br.readLine()) != null) {
							if (!line.isEmpty()) {
								// use comma as separator
								String[] data = line.split(",");
								if (data.length != 0) {

									GroupBean group = new GroupBean();
									GroupOpr dao = new GroupOpr();
									group.setGroupName(data[1]);
									Pattern dateFrmtPtrn = Pattern
											.compile("(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/((19|20)\\d\\d)");
									Matcher mtch = dateFrmtPtrn.matcher(data[2].trim());
									if (!mtch.matches()) {
										JOptionPane.showMessageDialog(this, "Date format should be MM/DD/YYYY");
										break;	
									}
									
									group.setStartDate(data[2]);
									flag = dao.insertGroups(group);
								} else {
									JOptionPane.showMessageDialog(this, "File is Empty");
									break;
								}

							}
						}
						if (flag) {
							JOptionPane.showMessageDialog(this, "Groups Created");
							this.setVisible(false);
							new GroupGUI();
						} else {
							JOptionPane.showMessageDialog(this, "Groups not created Please try again");
						}

					} catch (Exception ioe) {
						ioe.printStackTrace();
					}
				}
			}
		}

		if (r2.isSelected()) {
			cbGrpList.setEnabled(true);
			Item item = (Item) cbGrpList.getSelectedItem();
			if (e.getSource() == btnImport) {

				if (txtImportFilePath.getText().length() <= 0) {
					JOptionPane.showMessageDialog(this, "Please Select File.");
				}else if(item.getId()==0){
					JOptionPane.showMessageDialog(this, "Please Select Group.");
				} else {

					// System.out.println("Checked and pressed");
					String fileName = txtImportFilePath.getText();
					Path pathToFile = Paths.get(fileName);
					StudentBean bean = new StudentBean();
					//StudentDecoding StudDecoBean = new StudentDecoding();
					StudentDAO dao = new StudentOpr();
					boolean done = false;
					int studID = 0;

					try {

						BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII);
						String line = br.readLine();
						
						boolean dateFlag=checkDateFormatFile(br,line);
						
					if(dateFlag){
							
						br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII);
						line = br.readLine();
						int iteration = 0;
						while ((line = br.readLine()) != null) {
							String[] data = line.split(",");
							if (data.length != 0) {

								if (iteration == 0){
									bean.setGroupId(item.getId());
									bean.setStudFirstName(data[2]);
									bean.setStudLastName(data[3]);
									bean.setGrade(Integer.parseInt(data[4]));
									
									Pattern dateFrmtPtrn = Pattern
											.compile("(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/((19|20)\\d\\d)");
									Matcher mtch = dateFrmtPtrn.matcher(data[5].trim());
									if (!mtch.matches()) {
										JOptionPane.showMessageDialog(this, "Date format should be MM/DD/YYYY");
										break;	
									}
									
									bean.setDob(data[5].trim());
									
									mtch = dateFrmtPtrn.matcher(data[6].trim());
									if (!mtch.matches()) {
										JOptionPane.showMessageDialog(this, "Date format should be MM/DD/YYYY");
										break;	
									}
									
									bean.setStDate(data[6].trim());
									bean.setTeacher(data[7]);
									bean.setAge(data[8]);
									System.out.println("Student Data : " + bean);
									studID = dao.insertStudentByCSV(bean);
									iteration++;
								}
							
								
							} else {
								JOptionPane.showMessageDialog(this, "File is Empty");
								break;
							}

							
							String header1 = data[1];
							//String header2 = null;
							
							if (header1.contains("Decoding Data")){
								int iteration1 = 0;
								
								StudentDecoding studDecoBean = new StudentDecoding();
								StudentRate studRateBean = new StudentRate();
								while ((line = br.readLine()) != null) {
									String[] studDeco = line.split(",");
									String header2 = studDeco[1];
									if(iteration1 == 0) {
								        iteration1++;  
								        continue;
								    }									
									
									if (studDeco.length != 0 && iteration1 == 1) {
									
										
										if (header2.contains("Rate Data")){
											
												iteration++;
												break;											
										}
										studDecoBean.setStudId(studID);
										studDecoBean.setWeek(Integer.parseInt(studDeco[1]));
										Pattern dateFrmtPtrn = Pattern
												.compile("(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/((19|20)\\d\\d)");
										Matcher mtch = dateFrmtPtrn.matcher(studDeco[2].trim());
										if (!mtch.matches()) {
											JOptionPane.showMessageDialog(this, "Date format should be MM/DD/YYYY");
											break;	
										}
										
										studDecoBean.setDate(studDeco[2].trim());
										studDecoBean.setBook(Integer.parseInt(studDeco[3]));
										studDecoBean.setLesson(Integer.parseInt(studDeco[4]));
										studDecoBean.setForm(studDeco[5]);
										studDecoBean.setScore(Integer.parseInt(studDeco[6]));	
										System.out.println("Decoding Data : " + studDecoBean);
										done = dao.insertDecoderData(studDecoBean);
									}	
									
								}								
							}
							
							if(iteration == 2) {
								iteration++;  
						        continue;
						    }	
							if (iteration == 3){
								
								StudentRate studRateBean = new StudentRate();
								while ((line = br.readLine()) != null) {
									String[] studBean = line.split(",");
									
									if (studBean.length > 1){
									
										String header3 = studBean[1];
										if (header3.contains("GROUP ID")){
											iteration = 0;
											break;
										}
										studRateBean.setStudId(studID);
										Pattern dateFrmtPtrn = Pattern
												.compile("(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/((19|20)\\d\\d)");
										Matcher mtch = dateFrmtPtrn.matcher(studBean[1].trim());
										if (!mtch.matches()) {
											JOptionPane.showMessageDialog(this, "Date format should be MM/DD/YYYY");
											break;	
										}
										
										studRateBean.setDate(studBean[1].trim());
										studRateBean.setText(Integer.parseInt(studBean[2]));
										studRateBean.setTime(Integer.parseInt(studBean[3]));
										studRateBean.setCwpm(Integer.parseInt(studBean[4]));
										studRateBean.setErrors(Integer.parseInt(studBean[5]));
										studRateBean.setWeek(Integer.parseInt(studBean[6]));
										System.out.println("Rating Data : "+studRateBean);
										done = dao.insertRateData(studRateBean);
									}
									
								}							
								
							}
							
													
							
						}
					}

						if (done) {
							JOptionPane.showMessageDialog(this, "Students Added Successfully");
							setVisible(false);
							new StudentGUI(classId, className);
						}
					} catch (Exception ioe) {
						ioe.printStackTrace();
					}
				}

			}
		}
		if (e.getSource() == cbGrpExportList) {

			Item item = (Item) cbGrpExportList.getSelectedItem();
			int grpId = item.getId();
			studList = studDAO.getAllStudents(grpId + "");

			v.removeAllElements();
			v.add("Select Option");
			if (!studList.isEmpty()) {

				v.add(new JCheckBox("ALL", false));
			}
			for (StudentBean bean : studList) {
				v.add(new JCheckBox(bean.getStudFirstName() + " " + bean.getStudLastName() + " - " + bean.getId(),
						false));
			}
			cbStudentList.setModel(new DefaultComboBoxModel(v));
			add(cbStudentList);

			// cbStudentList.removeAllItems();
			//
			// if (!studList.isEmpty()){
			// cbStudentList.addItem(new Item(0, "All"));
			// }
			// for (StudentBean bean : studList) {
			// cbStudentList.addItem(new Item(bean.getId(),
			// bean.getStudFirstName() + " " + bean.getStudLastName()));
			// }
			// cbStudentList.setRenderer(new ItemRenderer());
			// add(cbStudentList);

		}

		if (e.getSource() == btnExit) {
			System.exit(0);

		}

		if (e.getSource() == btnMgroup) {
			synchronized (this) {
				new GroupGUI();
				this.setVisible(false);
			}
			
		}

		if (e.getSource() == btnMstudents) {
			synchronized (this) {
				new StudentGUI("", "");
				this.setVisible(false);
			}
		}

		if(e.getSource() == btnMmyProfile){
			synchronized (this) {
				new MyProfileGUI();
				this.setVisible(false);
			}
		}
		
		if(e.getSource() == btnBack){
			synchronized (this) {
				new welcomeGUI();
				this.setVisible(false);
			}
		}
		
		if(e.getSource() == btnSample){
			//this.setVisible(false);
			//new welcomeGUI();
			//Desktop
			String path1="";
			String path2="";
			String path11 ="";
			String path22 = "";
			if (osname.contains("Mac")){
				path1 = "./data/group.csv";
				path2  = "./data/Student.csv";
				path11 = "./group.csv";	    		    
				 path22  = "./Student.csv";
				
			}else{
				/*path1 = "./temp/group.csv";
			    path2  = "./temp/Student.csv";
			    path11 = "./data/group.csv";	    		    
				 path22  = "./data/Student.csv";*/
				path1 = "./data/group.csv";
				path2  = "./data/Student.csv";
				path11 = "./group.csv";	    		    
				path22  = "./Student.csv";
			    
			}
					
			
			
			 
		   
			try {
					
				File source = new File(path1);
				File dest = new File(path11);
				FileUtils.copyFile(source, dest);
				
				File source1 = new File(path2);
				File dest1 = new File(path22);
				FileUtils.copyFile(source1, dest1);
			
			    System.out.println("File downloaded");
			    JOptionPane.showMessageDialog(this, "File Download at "+dest.getAbsolutePath());
			} catch (IOException ee) {
			    ee.printStackTrace();
			}

			
			
		}
		
		
		// Export button
		if (e.getSource() == btnExport) {

			if (txtExportFilePath.getText().length() <= 0) {
				JOptionPane.showMessageDialog(this, "Please Select Folder for File Save.");
			} else {

				try {

					// System.out.println("Only perticular user");
					ArrayList id = new ArrayList();
					for (Object str : listId) {
						System.out.println(str.toString());
						if (!str.toString().equalsIgnoreCase("ALL")) {
							String temp[] = str.toString().split("-");
							id.add(temp[1].trim());

						} else {
							id.add("0");
							break;
						}

					}

					
					
					String csvFile = txtExportFilePath.getText() + "/ExportFile.csv";
					FileWriter writer = null;

					writer = new FileWriter(csvFile);
					List<String> stud = new ArrayList<String>();
					
					if(listId.size()==0){
						stud.add(" ");
						stud.add("Group Name");
						stud.add("Start Date");
						stud.add(",");
						stud.add("\n");
						
						Item item = (Item) cbGrpExportList.getSelectedItem();
						int grpId = item.getId();
						GroupDAO dao=new GroupOpr();
						
						 GroupBean bean =  dao.getGroup(grpId);
							stud.add(bean.getGroupName());
							stud.add(" "+bean.getStartDate());
							stud.add("\n");
						
					}else{					
								
			
								boolean flag = false;
								for (int i = 0; i < id.size(); i++) {
									if (Integer.parseInt(id.get(i).toString()) == 0) {
										flag = true;
										break;
									}
								}
			
								if (!flag) {
									
									stud.add(" ");
									for (int i = 0; i < id.size(); i++) {
										
										// JOptionPane.showMessageDialog(this, id.get(i));
										int studentID = Integer.parseInt(id.get(i).toString());
										if (studentID != 0) {
			
											//stud.add(" ");
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
											
											StudentBean studBean = studDAO.getStudentById(studentID);
											stud.add(studBean.getGroupId() + "");
											stud.add(studBean.getStudFirstName());
											stud.add(studBean.getStudLastName());
											stud.add(studBean.getGrade() + "");
											stud.add(" "+studBean.getDob()+"");
											stud.add(" "+studBean.getStDate()+" ");
											stud.add(studBean.getTeacher());
											stud.add(studBean.getAge() + "");
											stud.add("\n");
//											stud.add(" ");stud.add(" ");stud.add(" ");
											stud.add(" Decoding Data");
											stud.add("\n");
											stud.add("Week");
											stud.add("Date");
											stud.add("Book");
											stud.add("Lesson");
											stud.add("Form");
											stud.add("Score");
											stud.add("\n");
											for(StudentDecoding decode :  studBean.getListDecoding()){
												stud.add(decode.getWeek()+" ");
												stud.add(" "+decode.getDate());
												stud.add(decode.getBook()+"");
												stud.add(decode.getLesson()+"");
												stud.add(decode.getForm()+"");
												stud.add(decode.getScore()+"");
												stud.add("\n");
											}
//											stud.add("\n");
//											stud.add(" ");stud.add(" ");stud.add(" ");
											stud.add(" Rate Data");
											stud.add("\n");
											stud.add("Date");
											stud.add("Text");
											stud.add("Time");
											stud.add("CWPM");
											stud.add("Errors");
											stud.add("Week");
											stud.add("\n");
											
											for(StudentRate rate :  studBean.getListRate()){
												stud.add(" "+rate.getDate()+"");
												stud.add(rate.getText()+"");
												stud.add(rate.getTime()+"");
												stud.add(rate.getCwpm()+"");
												stud.add(rate.getErrors()+"");
												stud.add(rate.getWeek()+"");
												stud.add("\n");
											}
											
											
			
										}
									}
								} else {
									
									
									stud.add(" ");
									Item item = (Item) cbGrpExportList.getSelectedItem();
									int grpId = item.getId();
									studList = studDAO.getAllStudents(grpId + "");
									for (StudentBean bean : studList) {
			
										//stud.add(" ");
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
										
										
										stud.add(bean.getGroupId() + "");
										stud.add(bean.getStudFirstName());
										stud.add(bean.getStudLastName());
										stud.add(bean.getGrade() + "");
										stud.add(" "+bean.getDob()+"");
										stud.add(" "+bean.getStDate()+"");
										stud.add(bean.getTeacher());
										stud.add(bean.getAge());
										stud.add("\n");
//										stud.add(" ");stud.add(" ");stud.add(" ");
										stud.add(" Decoding Data");
										stud.add("\n");
										stud.add("Week");
										stud.add("Date");
										stud.add("Book");
										stud.add("Lesson");
										stud.add("Form");
										stud.add("Score");
										stud.add("\n");
										for(StudentDecoding decode :  bean.getListDecoding()){
											stud.add(decode.getWeek()+"");
											stud.add(" "+decode.getDate()+"");
											stud.add(decode.getBook()+"");
											stud.add(decode.getLesson()+"");
											stud.add(decode.getForm()+"");
											stud.add(decode.getScore()+"");
											stud.add("\n");
										}
										
//										stud.add("\n");
//										stud.add(" ");stud.add(" ");stud.add(" ");
										stud.add(" Rate Data");
										stud.add("\n");
										stud.add("Date");
										stud.add("Text");
										stud.add("Time");
										stud.add("CWPM");
										stud.add("Errors");
										stud.add("Week");
										stud.add("\n");
										for(StudentRate rate :  bean.getListRate()){
											stud.add(" "+rate.getDate()+"");
											stud.add(rate.getText()+"");
											stud.add(rate.getTime()+"");
											stud.add(rate.getCwpm()+"");
											stud.add(rate.getErrors()+"");
											stud.add(rate.getWeek()+"");
											stud.add("\n");
										}
										
										
										//stud.add("\n");
									}
								}
					}
					CSVUtils.writeLine(writer, stud);
					writer.flush();
					writer.close();
					JOptionPane.showMessageDialog(this, "File Created Successfully");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		}

	}

	private boolean checkDateFormatFile(BufferedReader br, String line) {
		// TODO Auto-generated method stub
		
		
		boolean flag=true;
		int iteration = 0;
		try{
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				if (data.length != 0) {
	
					if (iteration == 0){
						
						Pattern dateFrmtPtrn = Pattern
								.compile("(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/((19|20)\\d\\d)");
						Matcher mtch = dateFrmtPtrn.matcher(data[5].trim());
						if (!mtch.matches()) {
							JOptionPane.showMessageDialog(this, "Date format should be MM/DD/YYYY");
							flag=false;
							break;	
						}
						
						
						mtch = dateFrmtPtrn.matcher(data[6].trim());
						if (!mtch.matches()) {
							JOptionPane.showMessageDialog(this, "Date format should be MM/DD/YYYY");
							flag=false;
							break;	
						}
					
						iteration++;
					}
				
					
				} else {
					JOptionPane.showMessageDialog(this, "File is Empty");
					flag=false;
					break;
				}
	
				
				String header1 = data[1];
				//String header2 = null;
				
				if (header1.contains("Decoding Data")){
					int iteration1 = 0;
					
					
					while ((line = br.readLine()) != null) {
						String[] studDeco = line.split(",");
						String header2 = studDeco[1];
						if(iteration1 == 0) {
					        iteration1++;  
					        continue;
					    }									
						
						if (studDeco.length != 0 && iteration1 == 1) {
						
							
							if (header2.contains("Rate Data")){
								
									iteration++;
									break;											
							}
							
							Pattern dateFrmtPtrn = Pattern
									.compile("(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/((19|20)\\d\\d)");
							Matcher mtch = dateFrmtPtrn.matcher(studDeco[2].trim());
							if (!mtch.matches()) {
								JOptionPane.showMessageDialog(this, "Date format should be MM/DD/YYYY");
								flag=false;
								break;	
							}
							
							
						}	
						
					}								
				}
				
				if(iteration == 2) {
					iteration++;  
			        continue;
			    }	
				if (iteration == 3){
					
					while ((line = br.readLine()) != null) {
						String[] studBean = line.split(",");
						
						if (studBean.length > 1){
						
							String header3 = studBean[1];
							if (header3.contains("GROUP ID")){
								iteration = 0;
								break;
							}
							
							Pattern dateFrmtPtrn = Pattern
									.compile("(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/((19|20)\\d\\d)");
							Matcher mtch = dateFrmtPtrn.matcher(studBean[1].trim());
							if (!mtch.matches()) {
								JOptionPane.showMessageDialog(this, "Date format should be MM/DD/YYYY");
								flag=false;
								break;	
							}
							
						}
						
					}							
					
				}
			}
		}catch(Exception e){
			System.out.println("Import validation error : "+e.getMessage());
			
		}
		
		return flag;
	}

	private boolean isEmptyStringArray(String[] studDeco) {
		 for(int i=0; i<studDeco.length; i++){ 
			  if(studDeco[i] != null){
			    return true;
			  }
		 }
		return false;
	}



	/*
	 * public static void main(String[] args) { //new
	 * GroupStudImportExportGUI(); }
	 */

}
