package gui;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.management.MBeanAttributeInfo;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.Range;
import org.jfree.data.function.LineFunction2D;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.statistics.Regression;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.TextAnchor;

import abstrac.StudentDAO;
import bean.StudentBean;
import bean.StudentDecoding;
import bean.StudentRate;
import manegement.DateLabelFormatter;
import manegement.StudentOpr;

public class DecodePlotGraphGUI2 extends JFrame implements ActionListener,Printable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	StudentBean bean = new StudentBean();

	JButton btnSubmit, btnDelete, btnBack, btnExit;
	JButton btnContinue;
	JButton btnMgroup, btnMstudents, btnMreport, btnMImportExport, btnMLogout,btnMmyProfile;
	JMenuItem group, students, report;
	JMenu menu;
	String classId, className;
	JMenuItem delete, deleteRate;

	/* For 1st tab data */
	JLabel lblstudNo, lblFirstName, lblLastName, lblGrade, lblAge, lblteacher,lbldob,lblstdate;
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
	
	JLabel lblstudent,lblDataRange,lblAll,lblPlot,lblthrough;
	JTextField txtStudent;
	JRadioButton allRadio = new JRadioButton("   All");
    JRadioButton weekRadio = new JRadioButton("Weeks");
    JRadioButton indiStudDataRadio = new JRadioButton("Individual student data");
    JRadioButton studDataClsAvgRadio = new JRadioButton("Student data with class average");
    ButtonGroup bG = new ButtonGroup();
    ButtonGroup bG2 = new ButtonGroup();
	
	JLabel lblStudent,lblTeacher,lblCurrentDate;
	JButton btnPrint;
	String graphType = "";
	StudentDAO studDao = new StudentOpr();
	StudentBean studdata = null;
	
	JDatePickerImpl DOBdatePicker, STdatePicker ; 
	boolean saveReminderRate = false;
	JPanel p2;
	
	DecodePlotGraphGUI2(StudentBean bean, String classId, String className, String str, String txtBegin, String txtend){
		
		System.out.println("DecodePlotGraphGUI2");
		
		
		this.classId = classId;
		this.className = className;
		this.bean = bean;
		graphType = str;

		try {
			
			if(!txtBegin.isEmpty() && !txtend.isEmpty()){
				int beginTxt = Integer.parseInt(txtBegin);
				int endTxt = Integer.parseInt(txtend);
				if (endTxt >= beginTxt){
					System.out.println(beginTxt + " "+endTxt);
					StudentBean studBean = studDao.getStudentbyDecodingAndRatingByweek(bean.getId(), beginTxt, endTxt);
					this.bean = studBean;
				}else{
					JOptionPane.showMessageDialog(this,beginTxt+" is NOT less then "+endTxt );
				}				
			}					
          
        } catch (NumberFormatException e) {
            System.out.println("You've entered non-integer number");
            System.out.println("This caused " + e);
			JOptionPane.showMessageDialog(this,"Enter Number only (e.g 1 and 3)");

        }
		
		
		
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

		//Font f = new Font("Serif", Font.ITALIC | Font.BOLD, 25); // Creating
																	// font
																	// style and
																	// size for
																	// heading

		// step 3 : creating JLabel for Heading
		/*JLabel heading_lbl = new JLabel("Take Flight Decoding and Reading Rate Process Data Manager");
		heading_lbl.setBounds(180, 15, 1000, 50);
		heading_lbl.setForeground(Color.white);
		heading_lbl.setFont(f);
		add(heading_lbl);*/

		/* P1 for first tab data */
		JPanel p1 = createContactPanel1(this.bean); // Call method for set the
													// 1st tab frame contenct
		p1.setBounds(100, 100, 800, 600);
		p1.setBackground(new Color(65,127,159));

		/* P2 for first tab data */
		p2 = createContactPanel2(this.bean);
		p2.setBounds(100, 100, 800, 600);
		p2.setBackground(new Color(65,127,159));

		/* P3 for first tab data */
		JPanel p3 = createContactPanel3(this.bean);
		p3.setBounds(100, 100, 800, 600);
		p3.setBackground(new Color(65,127,159));

		
		JTabbedPane tp = new JTabbedPane();
		
		
		tp.setBounds(100, 50, 820, 547);
		tp.setFont(FontClass.MuseoSans300(20));
		tp.setForeground(Color.WHITE);
		tp.add("Student Info", p1);
		tp.add("Decoding", p2);
		tp.add("Reading Rate", p3);
		tp.setUI(new CustomTabbedPaneUI());
		tp.setBorder(null);
		
		int selectedIndex = tp.getSelectedIndex();
		tp.setSelectedIndex(tp.getTabCount()-2);


		add(tp);

		/*
		 * heading_lbl=new JLabel("Select record to edit");
		 * heading_lbl.setBounds(350,480,1000,50);
		 * heading_lbl.setForeground(Color.red); heading_lbl.setFont(f);
		 * heading_lbl.setFont(f); add(heading_lbl);
		 */
		 btnBack = new JButton(new ImageIcon(this.getClass().getResource("/image/back.png")));
		 btnBack.setBounds(100,600,120,40);
         btnBack.setOpaque(false);
         btnBack.setContentAreaFilled(false);
         btnBack.setBorderPainted(false);
         btnBack.setFocusable(false);
         btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
         add(btnBack);
         btnBack.addActionListener(this);
         
         
       /*  btnContinue = new JButton(new ImageIcon(this.getClass().getResource("/image/arrow right.png")));
         btnContinue.setBounds(800,600,120,40);
         btnContinue.setOpaque(false);
         btnContinue.setContentAreaFilled(false);
         btnContinue.setBorderPainted(false);
         btnContinue.setFocusable(false);
         btnContinue.setCursor(new Cursor(Cursor.HAND_CURSOR));
         add(btnContinue);
         btnContinue.addActionListener(this);*/
         
         btnPrint = new JButton(new ImageIcon(this.getClass().getResource("/image/print combo.png")));
         btnPrint.setBounds(800,600,120,40);
         btnPrint.setOpaque(false);
         btnPrint.setContentAreaFilled(false);
         btnPrint.setBorderPainted(false);
         btnPrint.setCursor(new Cursor(Cursor.HAND_CURSOR));
         btnPrint.setFocusable(false);
         add(btnPrint);
         //getContentPane().add(btnPrint);
         btnPrint.addActionListener(this);

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

		Font f1 = FontClass.MuseoSans700(15);
		f1.deriveFont(Font.PLAIN, 15);
		
		Font f3 = FontClass.MuseoSans500(15);
		f3.deriveFont(Font.PLAIN, 15);

		JPanel panelGeneral = new JPanel();
		panelGeneral.setLayout(null); // new Color(107,5,37)
		panelGeneral.setBackground(new Color(242,242,242));
		
		

		lblstudNo = new JLabel("Student Record Number");
		lblstudNo.setBounds(180, 40, 250, 30);
		//lblstudNo.setForeground(Color.white);
		lblstudNo.setFont(f1);
		panelGeneral.add(lblstudNo);

		txtstudNo = new JTextField();
		txtstudNo.setBounds(380, 40, 60, 30);
		txtstudNo.setFont(f3);
		txtstudNo.setEditable(false);
		txtstudNo.setText(studBean.getId() + "");
		panelGeneral.add(txtstudNo);

		lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(270, 90, 250, 30);
		//lblFirstName.setForeground(Color.white);
		lblFirstName.setFont(f1);
		panelGeneral.add(lblFirstName);

		txtFirstName = new JTextField();
		txtFirstName.setBounds(380, 90, 200, 30);
		txtFirstName.setText(studBean.getStudFirstName());
		txtFirstName.setFont(f3);
		panelGeneral.add(txtFirstName);

		lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(270, 130, 250, 30);
		//lblLastName.setForeground(Color.white);
		lblLastName.setFont(f1);
		panelGeneral.add(lblLastName);

		txtLastName = new JTextField();
		txtLastName.setBounds(380, 130, 200, 30);
		txtLastName.setText(studBean.getStudLastName());
		txtLastName.setFont(f3);
		panelGeneral.add(txtLastName);

		lblGrade = new JLabel("Grade");
		lblGrade.setBounds(300, 170, 250, 30);
		//lblGrade.setForeground(Color.white);
		lblGrade.setFont(f1);
		panelGeneral.add(lblGrade);

		txtGrade = new JTextField();
		txtGrade.setBounds(380, 170, 200, 30);
		txtGrade.setText(studBean.getGrade() + "");
		txtGrade.setFont(f3);
		panelGeneral.add(txtGrade);

		lblAge = new JLabel("Age");
		lblAge.setBounds(315, 210, 250, 30);
		//lblAge.setForeground(Color.white);
		lblAge.setFont(f1);
		panelGeneral.add(lblAge);

		txtAge = new JTextField();
		txtAge.setBounds(380, 210, 200, 30);
		txtAge.setText(studBean.getAge());
		txtAge.setFont(f3);
		panelGeneral.add(txtAge);

		lblteacher = new JLabel("Classroom Teacher");
		lblteacher.setBounds(210, 250, 250, 30);
		//lblteacher.setForeground(Color.white);
		lblteacher.setFont(f1);
		panelGeneral.add(lblteacher);

		txtTeacher = new JTextField();
		txtTeacher.setBounds(380, 250, 200, 30);
		txtTeacher.setText(studBean.getTeacher());
		txtTeacher.setFont(f3);
		panelGeneral.add(txtTeacher);
		
		lbldob = new JLabel("Date of birth");
		lbldob.setBounds(260,290,250, 30); 
		lbldob.setFont(f1);
		panelGeneral.add(lbldob);
		
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		
		
		SimpleDateFormat ddmmyyyy = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat mmddyyyy = new SimpleDateFormat("MM/dd/yyyy");
		Date dt = null;
		try {
			 dt = mmddyyyy.parse(studBean.getDob());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			try {
				dt = ddmmyyyy.parse(studBean.getStDate());
				String str = mmddyyyy.format(dt);
				dt = ddmmyyyy.parse(str);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}
			
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int year = cal.get(Calendar.YEAR);
		System.out.println(year);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		
		UtilDateModel dtmodel = new UtilDateModel();
		dtmodel.setDate(year, month, day);
		dtmodel.setSelected(true);
		JDatePanelImpl datePanel =new JDatePanelImpl(dtmodel, p);
		DOBdatePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		DOBdatePicker.setBounds(380, 290, 200, 30);
		DOBdatePicker.setFont(f3);
		panelGeneral.add(DOBdatePicker);
		
	
		lblstdate = new JLabel("Start Date");
		lblstdate.setBounds(280,330,250, 30); 
		lblstdate.setFont(f1);
		panelGeneral.add(lblstdate);
		
		
		try {
			 dt = mmddyyyy.parse(studBean.getStDate());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			try {
				dt = ddmmyyyy.parse(studBean.getStDate());
				String str = mmddyyyy.format(dt);
				dt = ddmmyyyy.parse(str);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}
			
			
		}
		
		cal = Calendar.getInstance();
		cal.setTime(dt);
		year = cal.get(Calendar.YEAR);
		//System.out.println(year);
		month = cal.get(Calendar.MONTH);
		day = cal.get(Calendar.DAY_OF_MONTH);
		
		UtilDateModel dtmodel2 = new UtilDateModel();
		dtmodel2.setDate(year, month, day);
		dtmodel2.setSelected(true);
		JDatePanelImpl datePanel2 =new JDatePanelImpl(dtmodel2, p);
		STdatePicker = new JDatePickerImpl(datePanel2, new DateLabelFormatter());
		STdatePicker.setBounds(380,330,200, 30);
		STdatePicker.setFont(f3);
		panelGeneral.add(STdatePicker);
		
		
		

		btnUpdateStudInfo = new JButton(new ImageIcon(this.getClass().getResource("/image/update button.png")));
		btnUpdateStudInfo.setBounds(350, 380, 130, 40);
		btnUpdateStudInfo.setOpaque(false);
		btnUpdateStudInfo.setContentAreaFilled(false);
		btnUpdateStudInfo.setBorderPainted(false);
		btnUpdateStudInfo.addActionListener(this);
		panelGeneral.add(btnUpdateStudInfo);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.add(panelGeneral);
		panelGeneral.setBounds(16, 16, 780, 477);

		panel.setPreferredSize(new Dimension(380, 620));

		return panel;
	}

	public JPanel createContactPanel2(StudentBean studBean) {

		JPanel panelGeneral = new JPanel();
		panelGeneral.setLayout(null); // new Color(107,5,37)
		panelGeneral.setBackground(new Color(242,242,242));
		
		Font f=FontClass.MuseoSans700(20);   // Creating font style and size for heading

		// step 3 : creating JLabel for Heading
				/*JLabel heading_lbl=new JLabel("Decoding Data Plot Option");
				heading_lbl.setBounds(250,20,600,20);
				heading_lbl.setFont(f);
				panelGeneral.add(heading_lbl);*/
				
				
				Font f1=FontClass.MuseoSans700(18); 
				Font f2=FontClass.MuseoSans500(20);
				Font f3=FontClass.MuseoSans300(15);
				
				
				/*	lblstudent = new JLabel("Class");
					lblstudent.setBounds(40,80,80,30); 
					lblstudent.setFont(f1);
					panelGeneral.add(lblstudent);
					
					txtStudent = new JTextField(bean.getStudFirstName() + " "+bean.getStudLastName());
					txtStudent.setBounds(120,80,200,30); 
					txtStudent.setEditable(false);
					txtStudent.setFont(f2);
					panelGeneral.add(txtStudent);*/
					
					
					lblStudent = new JLabel("Student  :  "+bean.getStudFirstName()+ " "+bean.getStudLastName() +"");
					lblStudent.setBounds(80,15,300,30); 
					lblStudent.setFont(f3);
					lblStudent.setForeground(new Color(65, 127, 159));
					panelGeneral.add(lblStudent);
					
					/*lblTeacher = new JLabel("Teacher  :  "+bean.getTeacher()+"");
					lblTeacher.setBounds(280,15,300,30); 
					lblTeacher.setForeground(new Color(65, 127, 159));
					lblTeacher.setFont(f3);
					panelGeneral.add(lblTeacher);*/
					
					
					lblCurrentDate = new JLabel("Current Date  :  "+new SimpleDateFormat("MM/dd/yyyy").format(new Date())+"");
					lblCurrentDate.setBounds(480,10,300,40); 
					lblCurrentDate.setFont(f3);
					lblCurrentDate.setForeground(new Color(65, 127, 159));
					panelGeneral.add(lblCurrentDate);
					
					/* P1 for first tab data  */
					 JPanel p1=new JPanel();//createContactPanel1();   // Call method for set the 1st tab frame contenct
					 p1.setBounds(0,48,800,400);    
					// p1.setBackground(new Color(242,242,242));  
					 //setContentPane(p1); //add(p1);
					 final XYDataset dataset = createDataset(this.bean);
				     final JFreeChart chart = createChart(dataset);
				    
				    // drawRegressionLine(chart,dataset);
				     
				        
				     // If we have an input parameter, predict the price and draw the new point
						/*if (args.length >= 1 && args[0] != null) {
							// Estimate the linear function given the input data
							double regressionParameters[] = Regression.getOLSRegression(
									dataset, 0);
							double x = Double.parseDouble(args[0]);

							// Prepare a line function using the found parameters
							LineFunction2D linefunction2d = new LineFunction2D(
									regressionParameters[0], regressionParameters[1]);
							// This is the estimated price
							double y = linefunction2d.getValue(x);

							drawInputPoint(x, y);
						}
				     
				     */
				     final ChartPanel chartPanel = new ChartPanel(chart);
				     chartPanel.setPreferredSize(new java.awt.Dimension(750, 400));
				     p1.add(chartPanel);
				        
				     panelGeneral.add(p1);
					
				
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.add(panelGeneral);
		panelGeneral.setBounds(16, 16, 780, 477);


		panel.setPreferredSize(new Dimension(380, 620));

		return panel;
	}

	public JPanel createContactPanel3(StudentBean studBean) {

		
		Font f=FontClass.MuseoSans700(20);
		
		Font f1 = FontClass.MuseoSans500Italic(20);
		//f1.deriveFont(Font.PLAIN, 15);
		
		Font f3 = FontClass.MuseoSans500(15);
		f3.deriveFont(Font.PLAIN, 15);

		JPanel panelGeneral = new JPanel();
		panelGeneral.setLayout(null); // new Color(107,5,37)
		panelGeneral.setBackground(new Color(242,242,242));

		JLabel heading_lbl=new JLabel("Take Flight Decoding and Reading Rate Progress Data Manager");
		heading_lbl.setBounds(100,10,600,20);
		heading_lbl.setFont(f);
		heading_lbl.setForeground(new Color(65, 127, 159));
		panelGeneral.add(heading_lbl);
		
		
		lblstudNo = new JLabel("   "+studBean.getStudFirstName() + " " + studBean.getStudLastName());
		lblstudNo.setBounds(20, 50, 250, 25);
		lblstudNo.setForeground(new Color(65, 127, 159));
		lblstudNo.setFont(f1);
		panelGeneral.add(lblstudNo);
		
		
		/* Table code start  */

		
		
		modelRate = new DefaultTableModel();

		jtRate = new JTable();
		jtRate.setRowHeight(30);

		jtRate.setFont(f3);

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
		
		SimpleDateFormat ddmmyyyy = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat mmddyyyy = new SimpleDateFormat("MM/dd/yyyy");
		
		for (StudentRate rateBean : studBean.getListRate()) {
			try {
				Date dt = ddmmyyyy.parse(rateBean.getDate());
				rateBean.setDate(mmddyyyy.format(dt));
				
				
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}
			modelRate.addRow(new Object[] { rateBean.getRateId(), rateBean.getWeek(), rateBean.getDate(),
					rateBean.getText(), rateBean.getTime(), rateBean.getCwpm(), rateBean.getErrors() });

		}

		Font f2 = FontClass.MuseoSans700(15);
		JTableHeader header = jtRate.getTableHeader();
		header.setBackground(new Color(188,221,238));
		header.setFont(f2);
		header.setForeground(Color.BLACK);
		header.setPreferredSize(new Dimension(100, 30));

		JScrollPane scroller = new JScrollPane(jtRate, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroller.setBounds(20, 80, 750, 180);
		panelGeneral.add(scroller);
		
		/*  end table    */
		
		btnAddRate = new JButton(new ImageIcon(this.getClass().getResource("/image/add record button2.png")));
		btnAddRate.setBounds(130, 270, 150, 130);
		btnAddRate.setBackground(new Color(242,242,242));
		btnAddRate.setOpaque(true);
		btnAddRate.setBorderPainted(false);
		btnAddRate.setContentAreaFilled(false);
		btnAddRate.setFocusable(false);
		btnAddRate.addActionListener(this);
		btnAddRate.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelGeneral.add(btnAddRate);

		btnSaveRate = new JButton(new ImageIcon(this.getClass().getResource("/image/save record button2.png")));
		btnSaveRate.setBounds(310, 270, 150, 130);
		btnSaveRate.setBackground(new Color(242,242,242));
		btnSaveRate.setOpaque(true);
		btnSaveRate.setBorderPainted(false);
		btnSaveRate.setContentAreaFilled(false);
		btnSaveRate.setFocusable(false);
		btnSaveRate.addActionListener(this);
		btnSaveRate.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelGeneral.add(btnSaveRate);

		btnPloatRate = new JButton(new ImageIcon(this.getClass().getResource("/image/plot data button2.png")));
		btnPloatRate.setBounds(490, 270, 150, 130);
		btnPloatRate.setBackground(new Color(242,242,242));
		btnPloatRate.setOpaque(true);
		btnPloatRate.setBorderPainted(false);
		btnPloatRate.setContentAreaFilled(false);
		btnPloatRate.setFocusable(false);
		btnPloatRate.addActionListener(this);
		btnPloatRate.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelGeneral.add(btnPloatRate);

		

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.add(panelGeneral);
		panelGeneral.setBounds(16, 16, 780, 477);

		/*
		 * panel.add(panelCreditCard); panelCreditCard.setBounds(10, 490, 370,
		 * 120);
		 */

		panel.setPreferredSize(new Dimension(380, 620));
		setResizable(false);

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
			saveReminderRate = true;
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

		if(e.getSource()== btnBack){
			if(saveReminderRate){
				JOptionPane.showMessageDialog(this, "Please save data before continuing");
			}else{
				synchronized (this) {
					new DecodePlotGUI2(bean, classId, className);
					setVisible(false);
				}
			}
			
		}
		
		if(e.getSource()==btnPrint){
			if(saveReminderRate){
				JOptionPane.showMessageDialog(this, "Please save data before continuinga");
			}else{
				PrinterJob printJob = PrinterJob.getPrinterJob();
				 printJob.setPrintable(this);
				 
				 
				 PageFormat preformat = printJob.defaultPage();
					preformat.setOrientation(PageFormat.LANDSCAPE);
					PageFormat postformat = printJob.pageDialog(preformat);
					printJob.setPrintable(this, postformat);
					
				 if(printJob.printDialog()){
					    try { 
					    	btnBack.setVisible(false);
					    	btnPrint.setVisible(false);
					    	//setVisible(false);
					    	printJob.print(); 
					    	
					    	btnBack.setVisible(true);
					    	btnPrint.setVisible(true);
					    	
					    } 
					    catch (Exception PrinterExeption
					    ) { }
					  }
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
		
		if(e.getSource() == btnContinue){
			if(saveReminderRate){
				JOptionPane.showMessageDialog(this, "Please save data before continuing");
			}else{
				if(allRadio.isSelected() && indiStudDataRadio.isSelected()){
					
					int size = bean.getListDecoding().size();
					if (size >= 3){
						synchronized (this) {
							new DecodePlotGraphGUI(bean,classId,className,"All", "","");
							setVisible(false);
						}
						
						
					}else{
						JOptionPane.showMessageDialog(this,"Please provide more data");
					}
		        
					
				}
				
				if(allRadio.isSelected() && studDataClsAvgRadio.isSelected()){
					synchronized (this) {
						new DecodePlotGraphGUI(bean,classId,className,"Avg","","");
						setVisible(false);
						
					}
				}
			}
			
			
			
			
			
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
					bean.setDob(DOBdatePicker.getJFormattedTextField().getText());
		        	bean.setStDate(STdatePicker.getJFormattedTextField().getText());

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

			if(saveReminderRate){
				JOptionPane.showMessageDialog(this, "Please save data before continuing");
			}else{
				synchronized (this) {
					StudentDAO studDao= new StudentOpr();
					bean=studDao.getAllStudentsWithDecod_Rate_Data(bean.getId());
					new PlotRateGUI2(bean, classId, className);
					this.dispose();
				}
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



	@Override
	public int print(Graphics gx, PageFormat pf, int page)
			throws PrinterException {
		// TODO Auto-generated method stub
		if (page>0){return NO_SUCH_PAGE;} //Only one page
		
		
		Graphics2D g = (Graphics2D)gx; //Cast to Graphics2D object
        pf.setOrientation(PageFormat.LANDSCAPE);
        g.translate((pf.getImageableX()), (pf.getImageableY())); //Match origins to imageable area
        
        Dimension size = this.getSize(); // component size
        double pageWidth = pf.getImageableWidth(); // Page width
        double pageHeight = pf.getImageableHeight(); // Page height
        
     
        // If the component is too wide or tall for the page, scale it down
        if (size.width > pageWidth) {
          double factor = pageWidth / size.width; // How much to scale
          factor = factor *1;
          g.scale(factor, factor); // Adjust coordinate system
          pageWidth /= factor; // Adjust page size up
          pageHeight /= factor;
        }
        if (size.height > pageHeight) { // Do the same thing for height
          double factor = pageHeight / size.height;
          factor = factor *1;
          g.scale(factor, factor);
          pageWidth /= factor;
          pageHeight /= factor;
        }
        
        g.translate((int) pf.getImageableX(), (int) pf.getImageableY());
        p2.paint(g);
        setVisible(false);
        this.print(g);
        setVisible(true);

        return PAGE_EXISTS; //Page exists (offsets start at zero!)

	}
	
	
	private void drawRegressionLine(JFreeChart chart, XYDataset inputData) {
		// Get the parameters 'a' and 'b' for an equation y = a + b * x,
		// fitted to the inputData using ordinary least squares regression.
		// a - regressionParameters[0], b - regressionParameters[1]
		double regressionParameters[] = Regression.getOLSRegression(inputData,
				0);

		// Prepare a line function using the found parameters
		LineFunction2D linefunction2d = new LineFunction2D(
				regressionParameters[0], regressionParameters[1]);

		// Creates a dataset by taking sample values from the line function
		XYDataset dataset = DatasetUtilities.sampleFunction2D(linefunction2d,
				0D, 30, 2, "Predicted Progress");

		// Draw the line dataset
		XYPlot xyplot = chart.getXYPlot();
		xyplot.setDataset(1, dataset);
		XYLineAndShapeRenderer xylineandshaperenderer = new XYLineAndShapeRenderer(
				true, false);
        
		 StandardXYToolTipGenerator ttG =
				    new StandardXYToolTipGenerator("{1},{2}",  NumberFormat.getInstance(),  NumberFormat.getInstance());
		 
		xylineandshaperenderer.setSeriesPaint(3, Color.YELLOW);
		xylineandshaperenderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator());
		xylineandshaperenderer.setBaseToolTipGenerator(ttG);
	
		xyplot.setRenderer(1, xylineandshaperenderer);
		
		  XYItemRenderer renderer1 = chart.getXYPlot().getRenderer();
		  renderer1.setBaseItemLabelsVisible(true);
		  renderer1.setBaseToolTipGenerator(new StandardXYToolTipGenerator());
		 
		  renderer1.setBaseToolTipGenerator(ttG);
		  
		  
		  xyplot.setRenderer(0,renderer1);
	
		
		
	}
	
	
private XYDataset createDataset(StudentBean bean) {
        
        final XYSeries series1 = new XYSeries("Student Score");
         
            for(StudentDecoding deco : bean.getListDecoding()){
            	
            	series1.add(deco.getWeek(), deco.getScore());
            }
            
            ArrayList regLineAB= getLinearRegressionLine(bean.getListDecoding());
            double beta1 = (double) regLineAB.get(0);
            double beta0 = (double) regLineAB.get(1);
            System.out.println("beta1 "+beta1);
            final XYSeries series2 = new XYSeries("Predicted Progress");
          
            for(StudentDecoding deco : bean.getListDecoding()){
                     //  System.out.println(" Count : "+ bean.getListDecoding().size());
            		double tempFinal = (  (beta1*deco.getWeek())+beta0  );
                    series2.add(deco.getWeek(),tempFinal);
            }
          
          
            
        XYSeries series3 = null;
		if(graphType.contains("Avg")){
			
			series3 = new XYSeries("Class Average");
			
			ArrayList<StudentBean> list = studDao.getAllStudents(classId);
			List ids = new ArrayList<>();
			for (StudentBean studbean : list) {

				ids.add(studbean.getId());
			}
			List avgList = studDao.getAvgofStud(ids);

			ArrayList regLineABAvg = getLinearRegressionLineforAvg(avgList);
			double betaA = (double) regLineABAvg.get(0);
			double betaB = (double) regLineABAvg.get(1);
			//System.out.println("beta1 " + beta1);
			
			for (StudentDecoding deco : bean.getListDecoding()) {
				// System.out.println(" Reg : "+ ( (beta1*deco.getWeek())+beta0));
				double regABAvg = ((betaA * deco.getWeek()) + betaB);
				series3.add(deco.getWeek(), regABAvg);
			}
        	
        }
        
        
      
        /* final XYSeries series3 = new XYSeries("Third");
        series3.add(3.0, 4.0);
        series3.add(4.0, 3.0);
        series3.add(5.0, 2.0);
        series3.add(6.0, 3.0);
        series3.add(7.0, 6.0);
        series3.add(8.0, 3.0);
        series3.add(9.0, 4.0);
        series3.add(10.0, 3.0);*/
		
		

        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        
        if (graphType.contains("Avg")){
        	dataset.addSeries(series3);
        }
        
        
                
        return dataset;
        
    }
    


	private ArrayList getLinearRegressionLineforAvg(List avgList) {
	

			ArrayList listOfAB=new ArrayList<>();
			int MAXN = 1000;
	        int n = 0;
	        double[] xTemp =new double[avgList.size()]; //{1,2,3,4};
	        double[] yTemp = new double[avgList.size()]; //{5,7,7,8};
	        
	        int i=0;
	        Double firstValue;
	        for (int l = 0; l < avgList.size(); l++) {
				  Double[] d = (Double[]) avgList.get(l);
				//  lst.add(new Double[]{Double.parseDouble(d[0].toString()), Double.parseDouble(d[1].toString())});
				  xTemp[i] =  Double.parseDouble(d[0].toString());
				  yTemp[i] =  Double.parseDouble(d[1].toString());
				  i++;
		      }
	   
//	        for (int j=0; j<avgList.size(); j++){
//	        	xTemp[i] =  
//	        	yTemp[i] =  
//	        	i++;
//	        }
	        
//	        for(StudentDecoding deco : avgList){
//	        	xTemp[i]=deco.getWeek();
//	        	yTemp[i] = deco.getScore();
//	        	i++;
//	        }
	        
	        
	        double[] x = new double[MAXN];
	        double[] y = new double[MAXN];

	        // first pass: read in data, compute xbar and ybar
	        double sumx = 0.0, sumy = 0.0, sumx2 = 0.0;
	       for(int j = 0 ; j<xTemp.length ; j++){
	            x[n] = xTemp[n];
	            y[n] = yTemp[n];
	            sumx  += x[n];
	            sumx2 += x[n] * x[n];
	            sumy  += y[n];
	            n++;
	        }
	        double xbar = sumx / n;
	        double ybar = sumy / n;

	        // second pass: compute summary statistics
	        double xxbar = 0.0, yybar = 0.0, xybar = 0.0;
	        for (int i1 = 0; i1 < n; i1++) {
	            xxbar += (x[i1] - xbar) * (x[i1] - xbar);
	            yybar += (y[i1] - ybar) * (y[i1] - ybar);
	            xybar += (x[i1] - xbar) * (y[i1] - ybar);
	        }
	        double beta1 = xybar / xxbar;
	        double beta0 = ybar - beta1 * xbar;

	        // print results
	        System.out.println("y   = " + beta1 + " * x + " + beta0);
	        
	        listOfAB.add(beta1);
	        listOfAB.add(beta0);
	        
			
			
			
		return listOfAB;
		
	}


	private ArrayList getLinearRegressionLine(
		ArrayList<StudentDecoding> listDecoding) {
	// TODO Auto-generated method stub
		ArrayList listOfAB=new ArrayList<>();
		int MAXN = 1000;
        int n = 0;
        double[] xTemp =new double[listDecoding.size()]; //{1,2,3,4};
        double[] yTemp = new double[listDecoding.size()]; //{5,7,7,8};
        
        int i=0;
        for(StudentDecoding deco : listDecoding){
        	xTemp[i]=deco.getWeek();
        	yTemp[i] = deco.getScore();
        	i++;
        }
        
        
        double[] x = new double[MAXN];
        double[] y = new double[MAXN];

        // first pass: read in data, compute xbar and ybar
        double sumx = 0.0, sumy = 0.0, sumx2 = 0.0;
       for(int j = 0 ; j<xTemp.length ; j++){
            x[n] = xTemp[n];
            y[n] = yTemp[n];
            sumx  += x[n];
            sumx2 += x[n] * x[n];
            sumy  += y[n];
            n++;
        }
        double xbar = sumx / n;
        double ybar = sumy / n;

        // second pass: compute summary statistics
        double xxbar = 0.0, yybar = 0.0, xybar = 0.0;
        for (int i1 = 0; i1 < n; i1++) {
            xxbar += (x[i1] - xbar) * (x[i1] - xbar);
            yybar += (y[i1] - ybar) * (y[i1] - ybar);
            xybar += (x[i1] - xbar) * (y[i1] - ybar);
        }
        double beta1 = xybar / xxbar;
        double beta0 = ybar - beta1 * xbar;

        // print results
        System.out.println("y   = " + beta1 + " * x + " + beta0);
        
        listOfAB.add(beta1);
        listOfAB.add(beta0);
        
		
		
		
	return listOfAB;
}


	/**
     * Creates a chart.
     * 
     * @param dataset  the data for the chart.
     * 
     * @return a chart.
     */
    private JFreeChart createChart(final XYDataset dataset) {
        
        // create the chart...
        final JFreeChart chart = ChartFactory.createXYLineChart(
            "Take Flight Student Progress:  Decoding Graph",      // chart title
            "Week",                      // x axis label
            "Number Correct",                      // y axis label
            dataset,                  // data
            PlotOrientation.VERTICAL,
            true,                     // include legend
            true,                     // tooltips
            false                     // urls
        );

        chart.setBackgroundPaint(new Color(242,242,242));
        chart.getTitle().setPaint(new Color(65,127,159));
        chart.getTitle().setHorizontalAlignment(HorizontalAlignment.CENTER);
        chart.getTitle().setFont(FontClass.MuseoSans900Italic(20));
        
        /*CategoryAxis categoryAxis = chart.getCategoryPlot().getDomainAxis();
	    categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);*/
        
        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinePaint(Color.black);
        plot.setRangeGridlinePaint(Color.black);
        plot.getRangeAxis().setLabelPaint(new Color(65,127,159));
		plot.getRangeAxis().setTickLabelPaint(new Color(65,127,159));
		plot.getRangeAxis().setLabelFont(FontClass.MuseoSans700(15));
		plot.getRangeAxis().setTickLabelFont(FontClass.MuseoSans700(15));
		
        
       
        
        NumberAxis xAxis = new NumberAxis("Week");
        /* X axis range set 0 to 50 with number tick meand dispaly to next number in x axis  */
       
        xAxis.setTickUnit(new NumberTickUnit(1));
        xAxis.setTickLabelFont(FontClass.MuseoSans700(12));
        xAxis.setLabelPaint(new Color(65,127,159));
        xAxis.setLabelFont(FontClass.MuseoSans700(15));
        xAxis.setTickLabelPaint(new Color(65,127,159));
        xAxis.setVerticalTickLabels(true);
        xAxis.setRange(0.0, 37.0);
        
        /* Code end  */
        plot.setDomainAxis(xAxis);
       
        
        
        
        //Range auto = plot.getRangeAxis().getRange();
        
        plot.getRangeAxis().setUpperBound(55.00);
        
       /* NumberAxis yAxis = new NumberAxis("Number Correct");
        yAxis.setTickUnit(new NumberTickUnit(10));
        plot.setDomainAxis(yAxis);*/
        
        
        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, false);
        renderer.setSeriesShapesVisible(1, true);
        renderer.setSeriesItemLabelsVisible(1, false);
        renderer.setBaseLinesVisible(true);
        renderer.setBaseItemLabelsVisible(Boolean.TRUE);
        
        renderer.setBaseItemLabelFont( FontClass.MuseoSans900(15));
        
        renderer.setBaseItemLabelGenerator((XYItemLabelGenerator) new StandardXYItemLabelGenerator());
        plot.setRenderer(renderer);
       
        LegendTitle legend = chart.getLegend();
        if (legend != null) {
    		legend.setPosition(RectangleEdge.TOP);
    		legend.setHorizontalAlignment(HorizontalAlignment.RIGHT);
    		legend.setItemFont(FontClass.MuseoSans900(15));
    	}
                
        return chart;
        
    }
	
	

}
