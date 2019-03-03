package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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

public class GroupStudImportExportGUI extends JFrame implements ActionListener,ItemListener {

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
	JLabel lblExportGFile,lblExportGFileName;
	JLabel lblExportSFile;
	JTextField txtNewStudGroup;
	JTextField txtImportFilePath, txtExportFilePath,txtExportGFileName;
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
	JRadioButton r1,r1Export;
	JRadioButton r2,r2Export;
	ButtonGroup bg,btExport;
	ArrayList<GroupBean> list;
	ArrayList<StudentBean> studList;
	Vector studVector = new Vector();
	Vector v = new Vector();
	Vector vgroup = new Vector();
	StudentDAO studDAO = new StudentOpr();
	ArrayList listId = new ArrayList();
	String osname = System.getProperty("os.name");
	
	GroupStudImportExportGUI(String classId, String className) {

		System.out.println("GroupStudImportExportGUI");
		this.classId = classId;
		this.className = className;

		setLayout(new java.awt.BorderLayout());
		setContentPane(new JLabel(new ImageIcon(getClass().getResource("/image/sky.png"))));
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
		

		setPreferredSize(new java.awt.Dimension(1000, 800));
		setLocationRelativeTo(null);

		Font f=FontClass.MuseoSans700Italic(25);
		
		Font f1=FontClass.MuseoSans700(18); 
		Font f2=FontClass.MuseoSans500(20);

		JLabel heading_lbl=new JLabel("Take Flight Decoding and Reading Rate ");
		heading_lbl.setBounds(290,15,600,30);
		heading_lbl.setFont(f);
		add(heading_lbl);
		
		heading_lbl=new JLabel("Process Data Manager");
		heading_lbl.setBounds(350,40,600,30);
		heading_lbl.setFont(f);
		add(heading_lbl);

		lblImport = new JLabel("Import");
		lblImport.setBounds(200, 100, 110, 40);
		lblImport.setFont(f1);
		getForeground();
		add(lblImport);

		r1 = new javax.swing.JRadioButton("Group");
		r2 = new javax.swing.JRadioButton("Student");
		r1.setBounds(250, 150, 100, 30);
		r2.setBounds(250, 200, 120, 30);
		r1.addActionListener(this);
		r2.addActionListener(this);
		bg = new javax.swing.ButtonGroup();
		r1.setFont(f2);
		r2.setFont(f2);
		
		r1.setOpaque(false);
		r1.setContentAreaFilled(false);
		r1.setBorderPainted(false);
		r2.setOpaque(false);
		r2.setContentAreaFilled(false);
		r2.setBorderPainted(false);
		
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
		lblImportGFile.setBounds(400, 130, 150, 60);
		lblImportGFile.setFont(f1);
		getForeground();
		add(lblImportGFile);

		lblNewStudGroup = new JLabel("New Student Group :");
		lblNewStudGroup.setBounds(400, 185, 250, 60);
		lblNewStudGroup.setFont(f1);
		getForeground();
		add(lblNewStudGroup);

		txtImportFilePath = new JTextField();
		txtImportFilePath.setBounds(530, 150, 130, 30);
		add(txtImportFilePath);


		btn3 = new JButton("File");
		btn3.setBounds(680, 150, 50, 30);
		btn3.setOpaque(true);
		btn3.setBorderPainted(false);
		btn3.setBackground(Color.WHITE);
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


		cbGrpList = new JComboBox(model);
		cbGrpList.setBounds(600, 200, 130, 30);
		cbGrpList.setRenderer(new ItemRenderer());
		cbGrpList.addActionListener(this);
		add(cbGrpList);

		// cbStudentList = new JComboBox();
		// cbStudentList.setBounds(400, 460, 90, 30);
		// // cbStudentList.addActionListener(this);
		// add(cbStudentList);


		btnImport = new JButton("Import");
		btnImport.setBounds(400, 270, 100, 30);
		btnImport.setBackground(Color.WHITE);
		btnImport.setOpaque(true);
		btnImport.setBorderPainted(false);
		btnImport.setFont(FontClass.MuseoSans900(15));
		btnImport.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(btnImport);
		getContentPane().add(btnImport);
		btnImport.addActionListener(this);
		
		btnSample = new JButton("Import Format");
		btnSample.setBounds(530, 270, 200, 30);
		btnSample.setBackground(Color.WHITE);
		btnSample.setOpaque(true);
		btnSample.setBorderPainted(false);
		btnSample.setFont(FontClass.MuseoSans900(15));
		btnSample.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(btnSample);
		getContentPane().add(btnSample);
		btnSample.addActionListener(this);

		/* Export UI  */

		lblExport = new JLabel("Export");
		lblExport.setBounds(200, 350, 110, 40);
		lblExport.setFont(f1);
		getForeground();
		add(lblExport);

		
		
		r1Export = new javax.swing.JRadioButton("Group");
		r1Export.setBounds(400, 390, 100, 30);
		//r1Export.addActionListener(this);
		r1Export.setFont(f2);
		r1Export.setOpaque(false);
		r1Export.setContentAreaFilled(false);
		r1Export.setBorderPainted(false);
		r1Export.addItemListener(this);
		
		r2Export = new javax.swing.JRadioButton("Student");
		r2Export.setBounds(520, 390, 120, 30);
		//r2Export.addActionListener(this);
		r2Export.setFont(f2);
		r2Export.setOpaque(false);
		r2Export.setContentAreaFilled(false);
		r2Export.setBorderPainted(false);
		r2Export.addItemListener(this);
		
		bg = new javax.swing.ButtonGroup();
		bg.add(r1Export);
		bg.add(r2Export);
		
		
		add(r1Export);
		add(r2Export);
		
		
		lblGroup = new JLabel("Group");
		lblGroup.setBounds(250, 430, 110, 40);
		lblGroup.setFont(f2);
		getForeground();
		add(lblGroup);

		vgroup.addElement(new Item(0, "Select Group"));
		cbGrpExportList = new JComboBox();
		cbGrpExportList.setBounds(400, 440, 90, 30);
		cbGrpExportList.setModel(new DefaultComboBoxModel(vgroup));
		cbGrpExportList.setRenderer(new ItemRenderer());
		cbGrpExportList.setEnabled(false);
		add(cbGrpExportList);
		
		cbGrpExportList.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	if(r2Export.isSelected()){
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
				}
		    }
		});
		
		
		
		/*v.add("Select Option");
		cbStudentList = new JComboBox();
		cbStudentList.setBounds(400, 480, 130, 30);
		cbStudentList.addItem(v);
		cbStudentList.setRenderer(new Comborenderer());
		*/
		
		lblExportGFile = new JLabel("Export File :");
		lblExportGFile.setBounds(520, 420, 150, 60);
		lblExportGFile.setFont(f2);
		getForeground();
		add(lblExportGFile);
		
		txtExportFilePath = new JTextField();
		txtExportFilePath.setBounds(650, 440, 130, 30);
		add(txtExportFilePath);
		
		btn1 = new JButton("File");
		btn1.setBounds(800, 440, 50, 30);
		btn1.setBackground(Color.WHITE);
		btn1.setOpaque(true);
		btn1.setBorderPainted(false);
		btn1.setFont(FontClass.MuseoSans900(15));
		add(btn1);
		getContentPane().add(btn1);
		btn1.addActionListener(this);
		
		
		lblStudent = new JLabel("Student");
		lblStudent.setBounds(250, 470, 110, 40);
		lblStudent.setFont(f2);
		getForeground();
		add(lblStudent);
		
		v.add("Select Option");
		cbStudentList = new JComboBox();
		cbStudentList.setBounds(400, 480, 130, 30);
		cbStudentList.addItem(v);
		cbStudentList.setRenderer(new Comborenderer());
		cbStudentList.setEnabled(false);

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

		
		
		lblExportGFileName = new JLabel("File Name :");
		lblExportGFileName.setBounds(550, 470, 150, 60);
		lblExportGFileName.setFont(f2);
		getForeground();
		add(lblExportGFileName);
		
		txtExportGFileName = new JTextField();
		txtExportGFileName.setBounds(660, 485, 190, 30);
		txtExportGFileName.setFont(f2);
		add(txtExportGFileName);
		
				
		btnExport = new JButton("Export");
		btnExport.setBounds(400, 540, 100, 30);
		btnExport.setBackground(Color.WHITE);
		btnExport.setOpaque(true);
		btnExport.setBorderPainted(false);
		btnExport.setFont(FontClass.MuseoSans900(15));
		btnExport.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(btnExport);
		getContentPane().add(btnExport);
		btnExport.addActionListener(this);

      /*   btnExit = new JButton(new ImageIcon(this.getClass().getResource("/image/Exit2.png")));
         btnExit.setBounds(880,600,120,40);
         btnExit.setBackground(Color.WHITE);
         btnExit.setOpaque(true);
         btnExit.setBorderPainted(false);
         add(btnExit);
         getContentPane().add(btnExit);
         btnExit.addActionListener(this);*/
         
         
		btnBack = new JButton(new ImageIcon(this.getClass().getResource("/image/back.png")));
        btnBack.setBounds(100,600,120,40);
        btnBack.setOpaque(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setBorderPainted(false);
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(btnBack);
        getContentPane().add(btnBack);
        btnBack.addActionListener(this);
        
        
        btnExit = new JButton(new ImageIcon(this.getClass().getResource("/image/Exit2.png")));
        btnExit.setBounds(800,600,120,40);
        btnExit.setBackground(Color.WHITE);
        btnExit.setOpaque(true);
        btnExit.setBorderPainted(false);
        btnExit.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
		
		
		/*GroupDAO dao = new GroupOpr();
		list = dao.getAllGroups();
		model.addElement(new Item(0, "Select Group"));
		for (bean.GroupBean bean : list) {
			model.addElement(new Item(bean.getGroupID(), bean.getGroupName()));
		}*/
		
		/*vgroup.addElement(new Item(-1, "Select Group"));
		cbGrpExportList = new JComboBox();
		cbGrpExportList.setBounds(400, 440, 90, 30);
		cbGrpExportList.setModel(new DefaultComboBoxModel(vgroup));
		//cbGrpExportList.addItem(vgroup);
		cbGrpExportList.setRenderer(new ItemRenderer());
		cbGrpExportList.setEnabled(false);
		add(cbGrpExportList);*/
		
		
		
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
									bean.setGrade(Integer.parseInt(data[4].trim()));
									
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
										studDecoBean.setWeek(Integer.parseInt(studDeco[1].trim()));
										Pattern dateFrmtPtrn = Pattern
												.compile("(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/((19|20)\\d\\d)");
										Matcher mtch = dateFrmtPtrn.matcher(studDeco[2].trim());
										if (!mtch.matches()) {
											JOptionPane.showMessageDialog(this, "Date format should be MM/DD/YYYY");
											break;	
										}
										
										studDecoBean.setDate(studDeco[2].trim());
										studDecoBean.setBook(Integer.parseInt(studDeco[3].trim()));
										studDecoBean.setLesson(Integer.parseInt(studDeco[4].trim()));
										studDecoBean.setForm(studDeco[5]);
										studDecoBean.setScore(Integer.parseInt(studDeco[6].trim()));	
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
										studRateBean.setText(Integer.parseInt(studBean[2].trim()));
										studRateBean.setTime(Integer.parseInt(studBean[3].trim()));
										studRateBean.setCwpm(Integer.parseInt(studBean[4].trim()));
										studRateBean.setErrors(Integer.parseInt(studBean[5].trim()));
										studRateBean.setWeek(Integer.parseInt(studBean[6].trim()));
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
		/*if (e.getSource() == cbGrpExportList) {
			if(r2Export.isSelected()){
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
			}else{
				return;
			}
			

		}*/

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
			}else if(txtExportGFileName.getText().trim().length()==0){
				JOptionPane.showMessageDialog(this, "Please Provie Export File Name.");
			}else if(!cbGrpExportList.isEnabled()){
				JOptionPane.showMessageDialog(this, "Please Select Group");
			}else {

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

					
					
					String csvFile = txtExportFilePath.getText() +"/"+txtExportGFileName.getText() + ".csv";
					FileWriter writer = null;

					writer = new FileWriter(csvFile);
					List<String> stud = new ArrayList<String>();
					
					if(listId.size()==0 && !r2Export.isSelected()){
						stud.add(" ");
						stud.add("Group Name");
						stud.add("Start Date");
						stud.add(",");
						stud.add("\n");
						
						Item item = (Item) cbGrpExportList.getSelectedItem();
						int grpId = item.getId();
						GroupDAO dao=new GroupOpr();
						
						if(grpId==0 && r2Export.isSelected()){
							JOptionPane.showMessageDialog(this, "Please Select Group");
							return;
						}
						
						if(grpId==0){
							ArrayList<GroupBean> groupList = dao.getAllGroups();
							for(GroupBean bean : groupList){
								 stud.add(bean.getGroupName());
								 stud.add(" "+bean.getStartDate());
								 stud.add("\n");
							}
							 
							
						}else{
							 GroupBean bean =  dao.getGroup(grpId);
							 stud.add(bean.getGroupName());
							 stud.add(" "+bean.getStartDate());
							 stud.add("\n");
						}
						
						
						 
						 
						
					}else{					
						
						if(id.size()==0){
							JOptionPane.showMessageDialog(this, "Please Select Student");
							return;
						}
			
								boolean flag = false;
								for (int i = 0; i < id.size(); i++) {
									if (Integer.parseInt(id.get(i).toString().trim()) == 0) {
										flag = true;
										break;
									}
								}
			
								if (!flag) {
									
									stud.add(" ");
									for (int i = 0; i < id.size(); i++) {
										
										// JOptionPane.showMessageDialog(this, id.get(i));
										int studentID = Integer.parseInt(id.get(i).toString().trim());
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

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
	
		if (e.getStateChange() == ItemEvent.SELECTED) {
			if(r1Export.isSelected()){
				cbGrpExportList.setEnabled(true);
				cbStudentList.setEnabled(false);
				vgroup.removeAllElements();
				
				v.removeAllElements();
				v.add("Select Option");
				cbStudentList.setModel(new DefaultComboBoxModel(v));
				
				vgroup.addElement(new Item(0, "All"));
				for (bean.GroupBean bean : list) {
					vgroup.addElement(new Item(bean.getGroupID(), bean.getGroupName()));
	    		}
				cbGrpExportList.setModel(new DefaultComboBoxModel(vgroup));
				add(cbGrpExportList);
				
				
			}
			if(r2Export.isSelected()){
				cbGrpExportList.setEnabled(true);
				cbStudentList.setEnabled(true);
				
				vgroup.removeAllElements();
				vgroup.addElement(new Item(0,"Select Group"));
				for (bean.GroupBean bean : list) {
					vgroup.addElement(new Item(bean.getGroupID(), bean.getGroupName()));
	    		}
				cbGrpExportList.setModel(new DefaultComboBoxModel(vgroup));
				add(cbGrpExportList);
			}
	    } 
	}



	/*
	 * public static void main(String[] args) { //new
	 * GroupStudImportExportGUI(); }
	 */

}
