package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.basic.BasicTableUI;
import javax.swing.plaf.basic.BasicTableUI.MouseInputHandler;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import manegement.GroupOpr;
import manegement.StudentOpr;
import abstrac.StudentDAO;
import bean.StudentBean;
import bean.StudentDecoding;
import bean.StudentRate;

public class StudentDetailsInfoGUI extends JFrame implements ActionListener {

	StudentBean bean = new StudentBean();

	JButton btnSubmit, btnDelete, btnBack, btnExit;
	JButton btnMgroup, btnMstudents, btnMreport, btnMImportExport, btnMLogout,btnMmyProfile;
	JMenuItem group, students, report;
	JMenu menu;
	String classId, className;
	JMenuItem delete, deleteRate;

	/* For 1st tab data */
	JLabel lblstudNo, lblFirstName, lblLastName, lblGrade, lblAge, lblteacher;
	JTextField txtstudNo, txtFirstName, txtLastName, txtGrade, txtAge, txtTeacher;
	JButton btnUpdateStudInfo;

	/* For 2nd tab data */
	DefaultTableModel model;
	JTable jt;
	JButton btnAddDecoding, btnSaveDecoding, btnPloatDecoding;

	/* For 3rd tab data */
	DefaultTableModel modelRate;
	JTable jtRate;
	JButton btnAddRate, btnSaveRate, btnPloatRate;
	String osname = System.getProperty("os.name");


	StudentDetailsInfoGUI(StudentBean bean, String classId, String className) {

		this.classId = classId;
		this.className = className;
		this.bean = bean;

		setLayout(new BorderLayout());
		setContentPane(new JLabel(new ImageIcon(this.getClass().getResource("/image/black-back-ground.jpg"))));
		setLayout(null);

		JMenuBar mb = new JMenuBar();
		mb.setBackground(new Color(135, 206, 250));
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
			btnMgroup.setBackground(new Color(135,206,250));
//			btnMgroup.setBorderPainted(false);
//			btnMgroup.setOpaque(true);
			mb.add(btnMgroup); 
			
	        setJMenuBar(mb);
	       
			
			btnMstudents = new JButton("Student");
			btnMstudents.addActionListener(this);
			btnMstudents.setBackground(new Color(135,206,250));
//			btnMstudents.setBorderPainted(false);
//			btnMstudents.setOpaque(true);
			mb.add(btnMstudents);  
	        setJMenuBar(mb);
			
			
			btnMImportExport = new JButton("Import / Export");
			btnMImportExport.addActionListener(this);
			btnMImportExport.setBackground(new Color(135,206,250));
//			btnMImportExport.setBorderPainted(false);
//			btnMImportExport.setOpaque(true);
			mb.add(btnMImportExport);  
	        setJMenuBar(mb);
	        
	        btnMLogout = new JButton("Logout");
	        btnMLogout.addActionListener(this);
	        btnMLogout.setBackground(new Color(135,206,250));
//	        btnMLogout.setForeground(Color.white);
//	        btnMLogout.setOpaque(true);
//	        btnMLogout.setBorderPainted(false);
			mb.add(btnMLogout);  
			
			
	        setJMenuBar(mb);
			
		}else{
			btnMmyProfile = new JButton("My Profile");
			btnMmyProfile.addActionListener(this);
			btnMmyProfile.setBackground(new Color(135,206,250));
			btnMmyProfile.setBorder(null);
			btnMmyProfile.setBorderPainted(false);
			btnMmyProfile.setOpaque(true);
			mb.add(btnMmyProfile); 
			setJMenuBar(mb);
			
			
			btnMgroup = new JButton("Groups");
			btnMgroup.addActionListener(this);
			btnMgroup.setBackground(new Color(135,206,250));
			btnMgroup.setBorderPainted(false);
			btnMgroup.setOpaque(true);
			mb.add(btnMgroup); 
			
	        setJMenuBar(mb);
	       
			
			btnMstudents = new JButton("Student");
			btnMstudents.addActionListener(this);
			btnMstudents.setBackground(new Color(135,206,250));
			btnMstudents.setBorderPainted(false);
			btnMstudents.setOpaque(true);
			mb.add(btnMstudents);  
	        setJMenuBar(mb);
			

			
			btnMImportExport = new JButton("Import / Export");
			btnMImportExport.addActionListener(this);
			btnMImportExport.setBackground(new Color(135,206,250));
			btnMImportExport.setBorderPainted(false);
			btnMImportExport.setOpaque(true);
			mb.add(btnMImportExport);  
	        setJMenuBar(mb);
	        
	        btnMLogout = new JButton("Logout");
	        btnMLogout.addActionListener(this);
	        btnMLogout.setBackground(new Color(107,5,37));
	        btnMLogout.setForeground(Color.white);
	        btnMLogout.setOpaque(true);
	        btnMLogout.setBorderPainted(false);
			mb.add(btnMLogout);  
			
			
	        setJMenuBar(mb);
		}
		

		setPreferredSize(new Dimension(1000, 800));
		setLocationRelativeTo(null);

		/*---------------------------------- Creating JLabel for Heading Text ------------------------------------------- */

		Font f = new Font("Serif", Font.ITALIC | Font.BOLD, 25); // Creating
																	// font
																	// style and
																	// size for
																	// heading

		// step 3 : creating JLabel for Heading
		JLabel heading_lbl = new JLabel("Take Flight Decoding and Reading Rate Process Data Manager");
		heading_lbl.setBounds(180, 15, 1000, 50);
		heading_lbl.setForeground(Color.white);
		heading_lbl.setFont(f);
		add(heading_lbl);

		/* P1 for first tab data */
		JPanel p1 = createContactPanel1(this.bean); // Call method for set the
													// 1st tab frame contenct
		p1.setBounds(100, 100, 800, 400);
		p1.setBackground(Color.gray);

		/* P2 for first tab data */
		JPanel p2 = createContactPanel2(this.bean);
		p2.setBounds(100, 100, 800, 400);
		p2.setBackground(Color.gray);

		/* P3 for first tab data */
		JPanel p3 = createContactPanel3(this.bean);
		p3.setBounds(100, 100, 800, 400);
		p3.setBackground(Color.gray);

		JTabbedPane tp = new JTabbedPane();
		tp.setBounds(100, 100, 800, 400);
		tp.add("Student Info", p1);
		tp.add("Decoding", p2);
		tp.add("Reading Rate", p3);
		add(tp);

		/*
		 * heading_lbl=new JLabel("Select record to edit");
		 * heading_lbl.setBounds(350,480,1000,50);
		 * heading_lbl.setForeground(Color.red); heading_lbl.setFont(f);
		 * heading_lbl.setFont(f); add(heading_lbl);
		 */
		btnBack = new JButton("Back");
		btnBack.setBounds(0, 600, 150, 40);
		btnBack.setBackground(Color.WHITE);
		btnBack.setOpaque(true);
		btnBack.setBorderPainted(false);
		btnBack.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
		add(btnBack);
		getContentPane().add(btnBack);
		btnBack.addActionListener(this);

		btnExit = new JButton("Exit");
		btnExit.setBounds(840, 600, 150, 40);
		btnExit.setBackground(Color.WHITE);
		btnExit.setOpaque(true);
		btnExit.setBorderPainted(false);
		btnExit.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
		add(btnExit);
		getContentPane().add(btnExit);
		btnExit.addActionListener(this);

		setSize(1000, 800);
		centerFrame();
		setTitle("Progress Monitor Data Manager");
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		/*
		 * WindowListener exitListener = new WindowAdapter() {
		 * 
		 * public void windowClosing(WindowEvent e) { setVisible(false); new
		 * welcomeGUI(); } }; addWindowListener(exitListener);
		 */
		// pack();
		// setLayout(new FlowLayout());
		setVisible(true);

	}

	public JPanel createContactPanel1(StudentBean studBean) {

		Font f1 = new Font("Serif", Font.BOLD, 20);
		Font f3 = new Font("Serif", Font.BOLD, 15);

		JPanel panelGeneral = new JPanel();
		panelGeneral.setLayout(null); // new Color(107,5,37)
		panelGeneral.setBackground(new Color(107, 5, 37));

		lblstudNo = new JLabel("Student Record Number");
		lblstudNo.setBounds(20, 20, 250, 25);
		lblstudNo.setForeground(Color.white);
		lblstudNo.setFont(f1);
		panelGeneral.add(lblstudNo);

		txtstudNo = new JTextField();
		txtstudNo.setBounds(280, 20, 60, 25);
		txtstudNo.setFont(f3);
		txtstudNo.setEditable(false);
		txtstudNo.setText(studBean.getId() + "");
		panelGeneral.add(txtstudNo);

		lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(20, 60, 250, 25);
		lblFirstName.setForeground(Color.white);
		lblFirstName.setFont(f1);
		panelGeneral.add(lblFirstName);

		txtFirstName = new JTextField();
		txtFirstName.setBounds(150, 60, 200, 25);
		txtFirstName.setText(studBean.getStudFirstName());
		txtFirstName.setFont(f3);
		panelGeneral.add(txtFirstName);

		lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(20, 90, 250, 25);
		lblLastName.setForeground(Color.white);
		lblLastName.setFont(f1);
		panelGeneral.add(lblLastName);

		txtLastName = new JTextField();
		txtLastName.setBounds(150, 90, 200, 25);
		txtLastName.setText(studBean.getStudLastName());
		txtLastName.setFont(f3);
		panelGeneral.add(txtLastName);

		lblGrade = new JLabel("Grade");
		lblGrade.setBounds(20, 120, 250, 25);
		lblGrade.setForeground(Color.white);
		lblGrade.setFont(f1);
		panelGeneral.add(lblGrade);

		txtGrade = new JTextField();
		txtGrade.setBounds(100, 120, 60, 25);
		txtGrade.setText(studBean.getGrade() + "");
		txtGrade.setFont(f3);
		panelGeneral.add(txtGrade);

		lblAge = new JLabel("Age");
		lblAge.setBounds(20, 150, 250, 25);
		lblAge.setForeground(Color.white);
		lblAge.setFont(f1);
		panelGeneral.add(lblAge);

		txtAge = new JTextField();
		txtAge.setBounds(100, 150, 60, 25);
		txtAge.setText(studBean.getAge());
		txtAge.setFont(f3);
		panelGeneral.add(txtAge);

		lblteacher = new JLabel("Classroom Teacher");
		lblteacher.setBounds(20, 180, 250, 25);
		lblteacher.setForeground(Color.white);
		lblteacher.setFont(f1);
		panelGeneral.add(lblteacher);

		txtTeacher = new JTextField();
		txtTeacher.setBounds(200, 180, 200, 25);
		txtTeacher.setText(studBean.getTeacher());
		txtTeacher.setFont(f3);
		panelGeneral.add(txtTeacher);

		btnUpdateStudInfo = new JButton("Update");
		btnUpdateStudInfo.setBounds(200, 230, 200, 25);
		btnUpdateStudInfo.setBackground(Color.white);
		btnUpdateStudInfo.setOpaque(true);
		btnUpdateStudInfo.setBorderPainted(false);
		btnUpdateStudInfo.setFont(new Font("Britannic Bold", Font.PLAIN, 20));
		btnUpdateStudInfo.addActionListener(this);
		panelGeneral.add(btnUpdateStudInfo);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.add(panelGeneral);
		panelGeneral.setBounds(15, 20, 760, 330);

		panel.setPreferredSize(new Dimension(380, 620));

		return panel;
	}

	public JPanel createContactPanel2(StudentBean studBean) {

		Font f1 = new Font("Serif", Font.BOLD, 20);
		Font f3 = new Font("Serif", Font.BOLD, 15);

		JPanel panelGeneral = new JPanel();
		panelGeneral.setLayout(new GridLayout(3, 1)); // new Color(107,5,37)
		panelGeneral.setBackground(new Color(107, 5, 37));

		lblstudNo = new JLabel(studBean.getStudFirstName() + " " + studBean.getStudLastName());
		lblstudNo.setBounds(20, 20, 250, 25);
		lblstudNo.setForeground(Color.white);
		lblstudNo.setFont(f1);

		panelGeneral.add(lblstudNo);

		model = new DefaultTableModel();

		jt = new JTable();
		jt.setRowHeight(30);

		jt.setFont(new Font("Times New Roman", Font.PLAIN, 13));

		// jt.setBounds(500,250,500,100);
		jt.setModel(model);
		model.addColumn("Record");
		model.addColumn("Week");
		model.addColumn("Date");
		model.addColumn("Book");
		model.addColumn("Lesson");
		model.addColumn("Form");
		model.addColumn("Score");
		// model.isCellEditable(row, column)
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		for (int i = 0; i <= 6; i++) {
			jt.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);

		}

		jt.setPreferredScrollableViewportSize(new Dimension(800, 300));

		// double click on table row and open other window code
		// jt.setDefaultEditor(Object.class, null);
		/*
		 * jt.setUI(new BasicTableUI() { // Create the mouse listener for the
		 * JTable. protected MouseInputListener createMouseInputListener() {
		 * return new MouseInputHandler() { // Display frame on double-click
		 * public void mouseClicked(MouseEvent e) { if (e.getClickCount()==2) {
		 * //new AddGroupGUI(); } }
		 * 
		 * public void mouseReleased(MouseEvent e) { int r =
		 * jt.rowAtPoint(e.getPoint()); if (r >= 0 && r < jt.getRowCount()) {
		 * jt.setRowSelectionInterval(r, r); } else { jt.clearSelection(); }
		 * 
		 * int rowindex = jt.getSelectedRow(); if (rowindex < 0) return; if
		 * (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
		 * JPopupMenu popup = new JPopupMenu("Delete");
		 * popup.show(e.getComponent(), e.getX(), e.getY()); } } }; } });
		 */

		delete = new JMenuItem("Delete");
		JPopupMenu popup = new JPopupMenu("Delete");
		popup.add(delete);
		

		if (osname.contains("Mac")){
			jt.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					if (e.getButton() == java.awt.event.MouseEvent.BUTTON3) {
						//System.out.println("In side after ckucj");
						int r = jt.rowAtPoint(e.getPoint());
						if (r >= 0 && r < jt.getRowCount()) {
							jt.setRowSelectionInterval(r, r);
						} else {
							jt.clearSelection();
						}
	
						int rowindex = jt.getSelectedRow();
						//System.out.println("rowindex" +rowindex);
						if (rowindex < 0){
							return;
						}
						popup.show(e.getComponent(), e.getX(), e.getY());
						if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
							//System.out.println("In popup show");
							popup.show(e.getComponent(), e.getX(), e.getY());
						}
					}
				}
			});
		}else{
			
			jt.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					int r = jt.rowAtPoint(e.getPoint());
					if (r >= 0 && r < jt.getRowCount()) {
						jt.setRowSelectionInterval(r, r);
					} else {
						jt.clearSelection();
					}

					int rowindex = jt.getSelectedRow();
					if (rowindex < 0)
						return;
					if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {

						popup.show(e.getComponent(), e.getX(), e.getY());
					}
				}
			});
		}
		
		

		delete.addActionListener(this);

		for (StudentDecoding decoBean : studBean.getListDecoding()) {

			model.addRow(new Object[] { decoBean.getDecoId(), decoBean.getWeek(), decoBean.getDate(),
					decoBean.getBook(), decoBean.getLesson(), decoBean.getForm(), decoBean.getScore() });

		}

		JTableHeader header = jt.getTableHeader();
		header.setBackground(Color.yellow);

		JScrollPane scroller = new JScrollPane(jt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		panelGeneral.add(scroller);

		JPanel panelCreditCard = new JPanel(new FlowLayout());
		// panelCreditCard.setLayout(null);
		panelCreditCard.setBackground(new Color(107, 5, 37));
		// panelCreditCard.setBorder(new TitledBorder("Payment method"));

		/*
		 * JLabel heading_lbl=new JLabel("Select record to edit");
		 * heading_lbl.setBounds(350,480,1000,50);
		 * heading_lbl.setForeground(Color.red); heading_lbl.setFont(f1);
		 * panelCreditCard.add(heading_lbl);
		 */

		btnAddDecoding = new JButton("Add Record");
		btnAddDecoding.setBounds(10, 10, 200, 25);
		btnAddDecoding.setBackground(Color.white);
		btnAddDecoding.setOpaque(true);
		btnAddDecoding.setBorderPainted(false);
		btnAddDecoding.setFont(new Font("Britannic Bold", Font.PLAIN, 20));
		btnAddDecoding.addActionListener(this);
		panelCreditCard.add(btnAddDecoding);

		btnSaveDecoding = new JButton("Save Record");
		btnSaveDecoding.setBounds(250, 10, 200, 25);
		btnSaveDecoding.setBackground(Color.white);
		btnSaveDecoding.setOpaque(true);
		btnSaveDecoding.setBorderPainted(false);
		btnSaveDecoding.setFont(new Font("Britannic Bold", Font.PLAIN, 20));
		btnSaveDecoding.addActionListener(this);
		panelCreditCard.add(btnSaveDecoding);

		btnPloatDecoding = new JButton("Plot Data");
		btnPloatDecoding.setBounds(250, 10, 200, 25);
		btnPloatDecoding.setBackground(Color.white);
		btnPloatDecoding.setOpaque(true);
		btnPloatDecoding.setBorderPainted(false);
		btnPloatDecoding.setFont(new Font("Britannic Bold", Font.PLAIN, 20));
		btnPloatDecoding.addActionListener(this);
		panelCreditCard.add(btnPloatDecoding);

		panelGeneral.add(panelCreditCard);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.add(panelGeneral);
		panelGeneral.setBounds(15, 20, 760, 330);

		/*
		 * panel.add(panelCreditCard); panelCreditCard.setBounds(10, 490, 370,
		 * 120);
		 */

		panel.setPreferredSize(new Dimension(380, 620));

		return panel;
	}

	public JPanel createContactPanel3(StudentBean studBean) {

		Font f1 = new Font("Serif", Font.BOLD, 20);
		Font f3 = new Font("Serif", Font.BOLD, 15);

		JPanel panelGeneral = new JPanel();
		panelGeneral.setLayout(new GridLayout(3, 1)); // new Color(107,5,37)
		panelGeneral.setBackground(new Color(107, 5, 37));

		lblstudNo = new JLabel(studBean.getStudFirstName() + " " + studBean.getStudLastName());
		lblstudNo.setBounds(20, 20, 250, 25);
		lblstudNo.setForeground(Color.white);
		lblstudNo.setFont(f1);

		panelGeneral.add(lblstudNo);

		modelRate = new DefaultTableModel();

		jtRate = new JTable();
		jtRate.setRowHeight(30);

		jtRate.setFont(new Font("Times New Roman", Font.PLAIN, 13));

		jtRate.setModel(modelRate);
		modelRate.addColumn("Record");
		modelRate.addColumn("Week");
		modelRate.addColumn("Date");
		modelRate.addColumn("Text");
		modelRate.addColumn("Time");
		modelRate.addColumn("CWPM");
		modelRate.addColumn("Errors");

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		for (int i = 0; i <= 6; i++) {
			jtRate.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		jtRate.setPreferredScrollableViewportSize(new Dimension(800, 300));

		deleteRate = new JMenuItem("Delete");
		JPopupMenu popup = new JPopupMenu("Delete");
		popup.add(deleteRate);

		
		if (osname.contains("Mac")){
		
			
			jtRate.addMouseListener(new MouseAdapter() {
				@Override
				
				public void mouseReleased(MouseEvent e) {
					if (e.getButton() == java.awt.event.MouseEvent.BUTTON3) {
						int r = jtRate.rowAtPoint(e.getPoint());
						if (r >= 0 && r < jtRate.getRowCount()) {
							jtRate.setRowSelectionInterval(r, r);
						} else {
							jtRate.clearSelection();
						}
		
						int rowindex = jtRate.getSelectedRow();
						if (rowindex < 0)
							return;
						
						popup.show(e.getComponent(), e.getX(), e.getY());
						if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
		
							popup.show(e.getComponent(), e.getX(), e.getY());
						}
					}
				}
			});
		}else{
		
		
		
		
			jtRate.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					int r = jtRate.rowAtPoint(e.getPoint());
					if (r >= 0 && r < jtRate.getRowCount()) {
						jtRate.setRowSelectionInterval(r, r);
					} else {
						jtRate.clearSelection();
					}
	
					int rowindex = jtRate.getSelectedRow();
					if (rowindex < 0)
						return;
					if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
	
						popup.show(e.getComponent(), e.getX(), e.getY());
					}
				}
			});
		}
		deleteRate.addActionListener(this);

		for (StudentRate rateBean : studBean.getListRate()) {
			modelRate.addRow(new Object[] { rateBean.getRateId(), rateBean.getWeek(), rateBean.getDate(),
					rateBean.getText(), rateBean.getTime(), rateBean.getCwpm(), rateBean.getErrors() });

		}

		JTableHeader header = jtRate.getTableHeader();
		header.setBackground(Color.yellow);

		JScrollPane scroller = new JScrollPane(jtRate, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		panelGeneral.add(scroller);

		JPanel panelCreditCard = new JPanel(new FlowLayout());
		// panelCreditCard.setLayout(null);
		panelCreditCard.setBackground(new Color(107, 5, 37));
		// panelCreditCard.setBorder(new TitledBorder("Payment method"));

		btnAddRate = new JButton("Add Record");
		btnAddRate.setBounds(10, 10, 200, 25);
		btnAddRate.setBackground(Color.white);
		btnAddRate.setOpaque(true);
		btnAddRate.setBorderPainted(false);
		btnAddRate.setFont(new Font("Britannic Bold", Font.PLAIN, 20));
		btnAddRate.addActionListener(this);
		panelCreditCard.add(btnAddRate);

		btnSaveRate = new JButton("Save Record");
		btnSaveRate.setBounds(250, 10, 200, 25);
		btnSaveRate.setBackground(Color.white);
		btnSaveRate.setOpaque(true);
		btnSaveRate.setBorderPainted(false);
		btnSaveRate.setFont(new Font("Britannic Bold", Font.PLAIN, 20));
		btnSaveRate.addActionListener(this);
		panelCreditCard.add(btnSaveRate);

		btnPloatRate = new JButton("Plot Data");
		btnPloatRate.setBounds(250, 10, 200, 25);
		btnPloatRate.setBackground(Color.white);
		btnPloatRate.setOpaque(true);
		btnPloatRate.setBorderPainted(false);
		btnPloatRate.setFont(new Font("Britannic Bold", Font.PLAIN, 20));
		btnPloatRate.addActionListener(this);
		panelCreditCard.add(btnPloatRate);

		panelGeneral.add(panelCreditCard);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.add(panelGeneral);
		panelGeneral.setBounds(15, 20, 760, 330);

		/*
		 * panel.add(panelCreditCard); panelCreditCard.setBounds(10, 490, 370,
		 * 120);
		 */

		panel.setPreferredSize(new Dimension(380, 620));

		return panel;
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
		if (e.getSource() == btnMLogout) {
			System.exit(0);
		}

		StudentDAO dao = new StudentOpr();
		if (e.getSource() == btnAddDecoding) {

			int nexVal = dao.getNextValue();
			int row = jt.getRowCount();

			if (row > 0) {
				if (Integer.parseInt(jt.getModel().getValueAt(row - 1, 0).toString()) >= nexVal) {
					nexVal = Integer.parseInt(jt.getModel().getValueAt(row - 1, 0).toString()) + 1;
				}
			}

			model.addRow(new Object[] { nexVal, "", "", "", "", "", "" });
			
			jt.changeSelection(row,0,true,false);

		}

		if (e.getSource() == btnAddRate) {

			int nexVal = dao.getNextValueRate();
			int row = jtRate.getRowCount();

			if (row > 0) {
				if (Integer.parseInt(jtRate.getModel().getValueAt(row - 1, 0).toString()) >= nexVal) {
					nexVal = Integer.parseInt(jtRate.getModel().getValueAt(row - 1, 0).toString()) + 1;
				}
			}

			modelRate.addRow(new Object[] { nexVal, "", "", "", "", "", "" });
			jtRate.changeSelection(row,0,true,false);
		}

		if (e.getSource() == btnSaveDecoding) {

			int row = jt.getRowCount();

			if (jt.isEditing())
				jt.getCellEditor().stopCellEditing();

			boolean flag = true;
			for (int i = 0; i < row; i++) {

				if (jt.getModel().getValueAt(i, 1).toString().length() == 0
						&& jt.getModel().getValueAt(i, 2).toString().length() == 0
						&& jt.getModel().getValueAt(i, 3).toString().length() == 0
						&& jt.getModel().getValueAt(i, 4).toString().length() == 0
						&& jt.getModel().getValueAt(i, 5).toString().length() == 0
						&& jt.getModel().getValueAt(i, 6).toString().length() == 0) {
					continue;

				}

				if (jt.getModel().getValueAt(i, 1).toString().length() == 0) {
					JOptionPane.showMessageDialog(this, "Please provide Week ");
					flag = false;
				} else if (jt.getModel().getValueAt(i, 2).toString().length() == 0) {
					JOptionPane.showMessageDialog(this, "Please provide Date ");
					flag = false;
				} else if (jt.getModel().getValueAt(i, 3).toString().length() == 0) {
					JOptionPane.showMessageDialog(this, "Please provide Book ");
					flag = false;
				} else if (jt.getModel().getValueAt(i, 4).toString().length() == 0) {
					JOptionPane.showMessageDialog(this, "Please provide Lesson ");
					flag = false;
				} else if (jt.getModel().getValueAt(i, 5).toString().length() == 0) {
					JOptionPane.showMessageDialog(this, "Please provide Form ");
					flag = false;
				} else if (jt.getModel().getValueAt(i, 6).toString().length() == 0) {
					JOptionPane.showMessageDialog(this, "Please provide Score ");
					flag = false;
				} else {

					try {
						Integer.parseInt(jt.getModel().getValueAt(i, 1).toString());
					} catch (NumberFormatException ee) {
						JOptionPane.showMessageDialog(this, "Please provide Week in number format ");
						flag = false;
					}

					try {
						Integer.parseInt(jt.getModel().getValueAt(i, 3).toString());
					} catch (NumberFormatException ee) {
						JOptionPane.showMessageDialog(this, "Please provide Book in number format ");
						flag = false;
					}

					try {
						Integer.parseInt(jt.getModel().getValueAt(i, 4).toString());
					} catch (NumberFormatException ee) {
						JOptionPane.showMessageDialog(this, "Please provide Lesson in number format ");
						flag = false;
					}

					try {
						Integer.parseInt(jt.getModel().getValueAt(i, 6).toString());
					} catch (NumberFormatException ee) {
						JOptionPane.showMessageDialog(this, "Please provide Score in number format ");
						flag = false;
					}

					Pattern dateFrmtPtrn = Pattern
							.compile("(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/((19|20)\\d\\d)");
					Matcher mtch = dateFrmtPtrn.matcher(jt.getModel().getValueAt(i, 2).toString());
					if (!mtch.matches()) {
						JOptionPane.showMessageDialog(this, "Date format should be MM/DD/YYYY");
						flag = false;
					}

					if (flag) {

						StudentDecoding bean = new StudentDecoding();
						bean.setDecoId(Integer.parseInt(jt.getModel().getValueAt(i, 0).toString()));
						bean.setWeek(Integer.parseInt(jt.getModel().getValueAt(i, 1).toString()));
						bean.setDate(jt.getModel().getValueAt(i, 2).toString());
						bean.setBook(Integer.parseInt(jt.getModel().getValueAt(i, 3).toString()));
						bean.setLesson(Integer.parseInt(jt.getModel().getValueAt(i, 4).toString()));
						bean.setForm(jt.getModel().getValueAt(i, 5).toString());
						bean.setScore(Integer.parseInt(jt.getModel().getValueAt(i, 6).toString()));
						bean.setStudId(this.bean.getId());
						flag = dao.insertDecoderData(bean);

					}

				}

			}
			if (flag) {
				JOptionPane.showMessageDialog(this, "Save Successfully");
			}

		}

		if (e.getSource() == btnSaveRate) {

			int row = jtRate.getRowCount();

			if (jtRate.isEditing())
				jtRate.getCellEditor().stopCellEditing();

			boolean flag = true;
			int count= 0 ;
			int firstText;
			List list = new ArrayList();
			for (int i = 0; i < row; i++) {

				if (jtRate.getModel().getValueAt(i, 1).toString().length() == 0
						&& jtRate.getModel().getValueAt(i, 2).toString().length() == 0
						&& jtRate.getModel().getValueAt(i, 3).toString().length() == 0
						&& jtRate.getModel().getValueAt(i, 4).toString().length() == 0
						&& jtRate.getModel().getValueAt(i, 5).toString().length() == 0
						&& jtRate.getModel().getValueAt(i, 6).toString().length() == 0) {

					continue;

				}

				if (jtRate.getModel().getValueAt(i, 1).toString().length() == 0) {
					JOptionPane.showMessageDialog(this, "Please provide Week "); // Integer
					flag = false;
				} else if (jtRate.getModel().getValueAt(i, 2).toString().length() == 0) {
					JOptionPane.showMessageDialog(this, "Please provide Date ");
					flag = false;
				} else if (jtRate.getModel().getValueAt(i, 3).toString().length() == 0) {
					JOptionPane.showMessageDialog(this, "Please provide Text "); // Integer
					flag = false;
				} else if (jtRate.getModel().getValueAt(i, 4).toString().length() == 0) {
					JOptionPane.showMessageDialog(this, "Please provide Time "); // Integer
					flag = false;
				} else if (jtRate.getModel().getValueAt(i, 5).toString().length() == 0) {
					JOptionPane.showMessageDialog(this, "Please provide CWPM "); // Integer
					flag = false;
				} else if (jtRate.getModel().getValueAt(i, 6).toString().length() == 0) {
					JOptionPane.showMessageDialog(this, "Please provide Errors "); // Integer
					flag = false;
				} else if(!jtRate.getModel().getValueAt(i, 4).toString().equals("1") && 
						!jtRate.getModel().getValueAt(i, 4).toString().equals("2") ){
					
					JOptionPane.showMessageDialog(this, "Times values accepted only 1 & 2 "); // Integer
					flag = false;
					
				}else {

					try {
						Integer.parseInt(jtRate.getModel().getValueAt(i, 1).toString());
					} catch (NumberFormatException ee) {
						JOptionPane.showMessageDialog(this, "Please provide Week in number format ");
						flag = false;
					}

					try {
						Integer.parseInt(jtRate.getModel().getValueAt(i, 3).toString());
					} catch (NumberFormatException ee) {
						JOptionPane.showMessageDialog(this, "Please provide Text in number format ");
						flag = false;
					}

					try {
						Integer.parseInt(jtRate.getModel().getValueAt(i, 4).toString());
					} catch (NumberFormatException ee) {
						JOptionPane.showMessageDialog(this, "Please provide Time in number format ");
						flag = false;
					}

					try {
						Integer.parseInt(jtRate.getModel().getValueAt(i, 5).toString());
					} catch (NumberFormatException ee) {
						JOptionPane.showMessageDialog(this, "Please provide CWPM in number format ");
						flag = false;
					}

					try {
						Integer.parseInt(jtRate.getModel().getValueAt(i, 6).toString());
					} catch (NumberFormatException ee) {
						JOptionPane.showMessageDialog(this, "Please provide Errors in number format ");
						flag = false;
					}

					Pattern dateFrmtPtrn = Pattern
							.compile("(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/((19|20)\\d\\d)");
					Matcher mtch = dateFrmtPtrn.matcher(jtRate.getModel().getValueAt(i, 2).toString());
					if (!mtch.matches()) {
						JOptionPane.showMessageDialog(this, "Date format should be MM/DD/YYYY");
						flag = false;		

					}
					
					// Check Text Only 2 Times
					firstText = Integer.parseInt(jtRate.getModel().getValueAt(i, 3).toString());					
					list.add(firstText);				
					count=Collections.frequency(list, firstText);
					
					if (count >= 3){			    		
						flag = false;	
			    	}	
					
					
					if (flag) {
						
						StudentRate bean = new StudentRate();
						bean.setRateId(Integer.parseInt(jtRate.getModel().getValueAt(i, 0).toString()));
						bean.setStudId(this.bean.getId());
						bean.setWeek(Integer.parseInt(jtRate.getModel().getValueAt(i, 1).toString()));
						bean.setDate(jtRate.getModel().getValueAt(i, 2).toString());
						bean.setText(Integer.parseInt(jtRate.getModel().getValueAt(i, 3).toString()));
						bean.setTime(Integer.parseInt(jtRate.getModel().getValueAt(i, 4).toString()));
						bean.setCwpm(Integer.parseInt(jtRate.getModel().getValueAt(i, 5).toString()));
						bean.setErrors(Integer.parseInt(jtRate.getModel().getValueAt(i, 6).toString()));

						flag = dao.insertRateData(bean);

					}else{
						JOptionPane.showMessageDialog(this, "Maximum attempt only 2 times");
					}

				}

			}
			if (flag) {
				JOptionPane.showMessageDialog(this, "Save Successfully");
			}

		}

		if (e.getSource() == btnBack) {
			synchronized (this) {
				new StudentGUI(classId, className);
				this.setVisible(false);
			}

		}
		if (e.getSource() == btnMImportExport) {
			synchronized (this) {
				new GroupStudImportExportGUI(classId, className);
				this.setVisible(false);
			}
		}
		if (e.getSource() == btnExit) {
			System.exit(0);

		}
		if (e.getSource() == btnUpdateStudInfo) {

			if (txtstudNo.getText().length() == 0) {
				JOptionPane.showMessageDialog(this, "Student Number count not be blank");
			} else if (txtFirstName.getText().length() == 0) {
				JOptionPane.showMessageDialog(this, "Please provide Student First Name");
			} else if (txtLastName.getText().length() == 0) {
				JOptionPane.showMessageDialog(this, "Please provide Student Last Name");
			} else if (txtGrade.getText().length() == 0) {
				JOptionPane.showMessageDialog(this, "Please provide Student Grade");
			} else if (txtAge.getText().length() == 0) {
				JOptionPane.showMessageDialog(this, "Please provide Student Age");
			} else if (txtTeacher.getText().length() == 0) {
				JOptionPane.showMessageDialog(this, "Please provide Class Teacher");
			} else {
				boolean flag = true;
				try {
					Integer.parseInt(txtGrade.getText());

					StudentBean bean = new StudentBean();
					bean.setId(Integer.parseInt(txtstudNo.getText()));
					bean.setStudFirstName(txtFirstName.getText());
					bean.setStudLastName(txtLastName.getText());
					bean.setGrade(Integer.parseInt(txtGrade.getText()));
					bean.setTeacher(txtTeacher.getText());

					flag = dao.updateStudentInfo(bean);

					if (flag) {
						JOptionPane.showMessageDialog(this, "Updated Successfully");
					}

				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(this, "Provide Grade in Number format");
					flag = false;
				}

			}

		}

		if (e.getSource() == btnPloatDecoding) {
			StudentDAO studDao= new StudentOpr();
			 this.bean=studDao.getAllStudentsWithDecod_Rate_Data(bean.getId());
			int size = bean.getListDecoding().size();
			
			if (size >= 3){
				synchronized (this) {
					new DecodePlotGUI(bean, classId, className);
					this.setVisible(false);
				}
				
			}else{
				JOptionPane.showMessageDialog(this,"To Plot Graph We need at least 3 records.");
			}
			

		}

		if (e.getSource() == btnMstudents) {
			synchronized (this) {
				new StudentGUI(classId, className);
				this.setVisible(false);
			}
		}

		if (e.getSource() == btnMgroup) {
			synchronized (this) {
				new GroupGUI();
				this.dispose();
			}

		}

		if(e.getSource() == btnMmyProfile){
			synchronized (this) {
				new MyProfileGUI();
				this.setVisible(false);
			}
		}
		
		if (e.getSource() == btnPloatRate) {
			synchronized (this) {
				new PlotRateGUI(bean, classId, className);
				this.dispose();
			}

		}

		if (e.getSource() == delete) {
			DefaultTableModel dtm = (DefaultTableModel) jt.getModel();
			int selRow = jt.getSelectedRow();
			// int selCol = jt.getSelectedColumn();
			if (selRow < 0) {
				JOptionPane.showMessageDialog(this, "Please select Group");

			} else {

				int n = JOptionPane.showConfirmDialog(this, "Delete Seleced Decoding?", "Confirm Delete",
						JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					String value = jt.getModel().getValueAt(selRow, 0).toString();

					boolean flag = dao.deleteDecoding(value);
					if (flag) {
						dtm.removeRow(selRow);
					} else {
						JOptionPane.showMessageDialog(this, "Failed! Please try again..");
					}

				}

			}

		}

		if (e.getSource() == deleteRate) {
			DefaultTableModel dtm = (DefaultTableModel) jtRate.getModel();
			int selRow = jtRate.getSelectedRow();
			// int selCol = jt.getSelectedColumn();
			if (selRow < 0) {
				JOptionPane.showMessageDialog(this, "Please select Group");

			} else {

				int n = JOptionPane.showConfirmDialog(this, "Delete Seleced Rate?", "Confirm Delete",
						JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					String value = jtRate.getModel().getValueAt(selRow, 0).toString();

					boolean flag = dao.deleteRate(value);
					if (flag) {
						dtm.removeRow(selRow);
					} else {
						JOptionPane.showMessageDialog(this, "Failed! Please try again..");
					}

				}

			}

		}

	}

	/*
	 * public static void main(String args[]){ new
	 * StudentDetailsInfoGUI(null,null,null);
	 * 
	 * }
	 */

}
