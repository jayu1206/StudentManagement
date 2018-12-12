package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.TitledBorder;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.basic.BasicTableUI;
import javax.swing.plaf.basic.BasicTableUI.MouseInputHandler;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import manegement.GroupOpr;
import manegement.StudentOpr;
import abstrac.GroupDAO;
import abstrac.StudentDAO;
import bean.GroupBean;
import bean.StudentBean;

public class StudentGUI extends JFrame implements ActionListener{
	
	DefaultTableModel model ;
	JTable jt;
	JButton btnSubmit,btnDelete,btnBack,btnExit;
	JButton  btnMgroup,btnMstudents,btnMreport,btnMImportExport,btnMLogout,btnMmyProfile;
	JMenuItem group,students,report;
	JMenu menu;
	String classId,className;
	
	
	StudentGUI(String classId,String className){
		
		this.classId=classId;
		this.className=className;
		
		if(this.className.trim().length()== 0){
			
			this.className = "Listing All Students";		
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
        
		
		
		 /*---------------------------------- Creating JLabel for Heading Text ------------------------------------------- */
		 
		Font f=FontClass.MuseoSans700Italic(20);   // Creating font style and size for heading

		// step 3 : creating JLabel for Heading
				JLabel heading_lbl=new JLabel("Take Flight Decoding and Reading Rate Process Data Manager");
				heading_lbl.setBounds(210,15,600,30);
				heading_lbl.setFont(f);
				add(heading_lbl);
				
				JLabel heading_lbl2=new JLabel(this.className);
				heading_lbl2.setBounds(390,55,400,30);
				heading_lbl2.setFont(f);
				add(heading_lbl2);
				
				Font f3 = FontClass.MuseoSans500(15);
				f3.deriveFont(Font.PLAIN, 15);
				
				model = new DefaultTableModel();
				 
				 jt=new JTable(); 
				 jt.setRowHeight(30);
				 
				 
				 jt.setFont(f3);
			     
				 //jt.setBounds(500,250,500,100); 
				 jt.setModel(model);
				 model.addColumn("Id");
				 model.addColumn("Name");
		         model.addColumn("Grade");
		         model.addColumn("DOB");
		         model.addColumn("Start Date");
		        
		         DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		         centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		         
		         jt.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		         jt.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
		         jt.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
		         jt.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
		         jt.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
		       
		         jt.setPreferredScrollableViewportSize(
		        		    new Dimension(800,280)); 
		         
		         StudentDAO studDao= new StudentOpr();
		         
		         // double click on table row and open other window code
		         jt.setDefaultEditor(Object.class, null);
			       jt.setUI(new BasicTableUI() {
				    	// Create the mouse listener for the JTable.
				    	protected MouseInputListener createMouseInputListener() {
					    	return new MouseInputHandler() {
						    	// Display frame on double-click
						    	public void mouseClicked(MouseEvent e) {
							    	if (e.getClickCount()==2) {
							    		DefaultTableModel dtm = (DefaultTableModel) jt.getModel();  
							            int selRow = jt.getSelectedRow();
							            StudentBean bean=new StudentBean();
							            bean.setId(Integer.parseInt( jt.getModel().getValueAt(selRow, 0).toString() ));
							            bean=studDao.getAllStudentsWithDecod_Rate_Data(bean.getId());
							            
							            /*if(classId.length()>0){
							            	bean.setGroupId(Integer.parseInt(classId));
							            }
							            
							           // bean.setStudName(jt.getModel().getValueAt(selRow, 1).toString() );
							            bean.setGrade(Integer.parseInt( jt.getModel().getValueAt(selRow, 2).toString() ));
							            bean.setDob(jt.getModel().getValueAt(selRow, 3).toString());
							            bean.setStDate(jt.getModel().getValueAt(selRow, 4).toString());*/
							    		new StudentDetailsInfoGUI(bean,classId,className);
							    		dispose();
							    	}
						    	}
					    	};
				    	}
			    	});
		         
		         // finish the code of double click on row
			       
			        ArrayList<StudentBean> list=studDao.getAllStudents(classId);
			        SimpleDateFormat ddmmyyyy = new SimpleDateFormat("dd-MM-yyyy");
					SimpleDateFormat mmddyyyy = new SimpleDateFormat("MM/dd/yyyy");
					
			        for(StudentBean bean : list ){
			        	try {
							Date dt = ddmmyyyy.parse(bean.getDob());
							bean.setDob(mmddyyyy.format(dt));
							
							dt = ddmmyyyy.parse(bean.getStDate());
							bean.setStDate(mmddyyyy.format(dt));
							
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							//e1.printStackTrace();
						}
			        	 model.addRow(new Object[]{bean.getId(),bean.getStudFirstName()+" "+bean.getStudLastName(),bean.getGrade(),bean.getDob(),bean.getStDate()});
			        	 
			         }
			        
			       Font f2 = FontClass.MuseoSans700(15);
			       JTableHeader header= jt.getTableHeader();
			       header.setBackground(new Color(188,221,238));
			       header.setFont(f2);
				   header.setForeground(Color.BLACK);
				   header.setPreferredSize(new Dimension(100, 30));
			       
			       final TableRowSorter<TableModel> sorter;
			       sorter = new TableRowSorter<TableModel>(model);
			       jt.setRowSorter(sorter);
			       
			         JScrollPane scroller = new JScrollPane(jt, 
			                 JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
			  
			         
			         JPanel panelGeneral = new JPanel();
			         panelGeneral.setLayout(new FlowLayout());
			         panelGeneral.add(scroller);
			         panelGeneral.setBounds(100, 100, 820, 312);
			         
			         add(panelGeneral); 
			         
			         
			         JPanel pnl = new JPanel();
					 pnl.setBackground(Color.WHITE);
					 Font f4 = FontClass.MuseoSans500(20);
					 f4.deriveFont(Font.BOLD, 15);
					 pnl.setFont(f4);
					    pnl.add(new JLabel("Search : "));
					    final JTextField txtFE = new JTextField(20);
					    pnl.add(txtFE);
					    JButton btnSetFE = new JButton("Submit");
					    ActionListener al;
					    al = new ActionListener() {
					      public void actionPerformed(ActionEvent e) {
					        String expr = txtFE.getText();
					        sorter.setRowFilter(RowFilter.regexFilter(expr));
					        sorter.setSortKeys(null);
					        
					        
					      }
					    };
					    btnSetFE.addActionListener(al);
					   // btnSetFE.setBackground(Color.WHITE);
					    btnSetFE.setOpaque(true);
					    btnSetFE.setBorderPainted(false);
					    btnSetFE.setCursor(new Cursor(Cursor.HAND_CURSOR));
					    btnSetFE.setFont(f4);
					    pnl.add(btnSetFE);
					    pnl.setBounds(270,420,502,45);
					    add(pnl);
					    
					    heading_lbl=new JLabel("Double click on student to enter decoding or reading data");
						heading_lbl.setBounds(250,420,600,120);
						heading_lbl.setFont(f4);
						add(heading_lbl);
				
						btnSubmit = new JButton("Add New Student");
						 if (osname.contains("Mac")){
							 btnSubmit.setBounds(330,500,180,40);
							}else{
								btnSubmit.setBounds(330,500,160,40);
							}
						 
				         btnSubmit.setBackground(Color.WHITE);
				         btnSubmit.setOpaque(true);
				         btnSubmit.setBorderPainted(false);
				         btnSubmit.setFont(FontClass.MuseoSans900(15));
				         btnSubmit.setCursor(new Cursor(Cursor.HAND_CURSOR));
				         add(btnSubmit);
				         btnSubmit.addActionListener(this);
				         
				         btnDelete = new JButton("Remove Student");
				         if (osname.contains("Mac")){
				        	 btnDelete.setBounds(530,500,180,40);
							}else{
								btnDelete.setBounds(530,500,160,40);
							}
				         
				         btnDelete.setBackground(Color.WHITE);
				         btnDelete.setOpaque(true);
				         btnDelete.setBorderPainted(false);
				         btnDelete.setFont(FontClass.MuseoSans900(15));
				         btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
				         add(btnDelete);
				         btnDelete.addActionListener(this);
				         
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
				
				         
				
					
		setSize(1000,800);
		centerFrame();
		setTitle("Progress Monitor Data Manager");
		/* setDefaultCloseOperation(javax.swing.
		       WindowConstants.DISPOSE_ON_CLOSE);*/
		WindowListener exitListener = new WindowAdapter() {
		
		   public void windowClosing(WindowEvent e) {
		   	setVisible(false);
				new welcomeGUI();
		   }
		};
		addWindowListener(exitListener);
		// pack();
		setLayout(null);   
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
		
		StudentDAO dao=null;
		if(e.getSource()==btnBack){
			synchronized (this) {
				new GroupGUI();
				this.setVisible(false);
			}
			
		}
		if(e.getSource()==btnExit){
			System.exit(0);
			
		}
		
		if(e.getSource()==btnSubmit){
			synchronized (this) {
				new StudentAddGui(this.classId,this.className);
				this.dispose();
			}
			
		}
		
		if(e.getSource()==btnMgroup){
			synchronized (this) {
				new GroupGUI();
				this.dispose();
			}
			
		}
		if(e.getSource()==btnMstudents){
			synchronized (this) {
				new StudentGUI("", "");
				this.dispose();
			}
		}
		
		if(e.getSource()==btnMImportExport){
			synchronized (this) {
				new GroupStudImportExportGUI(classId, className);
				this.setVisible(false);
			}
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
		
		if(e.getSource()==btnDelete){
			DefaultTableModel dtm = (DefaultTableModel) jt.getModel();  
            int selRow = jt.getSelectedRow();
            int modelRow = jt.convertRowIndexToModel(selRow);
			if(selRow<0){
				JOptionPane.showMessageDialog(this,"Please select Student");
				 
				
			}else{
				
				int n = JOptionPane.showConfirmDialog(this
                        , "Delete Selected Student?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION);
				 if (n == JOptionPane.YES_OPTION) {
					   String value = jt.getModel().getValueAt(modelRow, 0).toString();
					   dao=new StudentOpr();
					   boolean flag=  dao.deleteStudent(value);
					   if(flag){
						   dtm.removeRow(modelRow); 
					   }else{
						   JOptionPane.showMessageDialog(this,"Failed! Please try again..");
					   }
		               
				 } 				
				
			}
			
			
		}
		
	}
	
	
	public static void main(String args[]){
		new StudentGUI("","");
		
	}
	
}
