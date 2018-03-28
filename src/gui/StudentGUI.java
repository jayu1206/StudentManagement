package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

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
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import manegement.GroupOpr;
import manegement.StudentOpr;
import abstrac.GroupDAO;
import abstrac.StudentDAO;
import bean.GroupBean;
import bean.StudentBean;

public class StudentGUI extends JFrame implements ActionListener {
	
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
		
		
		setLayout(new BorderLayout());
		setContentPane(new JLabel(new ImageIcon(this.getClass().getResource("/image/blue.jpg"))));
		setLayout(new FlowLayout());
		
		JMenuBar mb=new JMenuBar();
		mb.setBackground(new Color(225,39,38));
		mb.add(Box.createRigidArea(new Dimension(10,40)));

		String osname = System.getProperty("os.name");
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
		
        
		setPreferredSize(new Dimension(1000, 800));
		setLocationRelativeTo(null);
		
		
		Container c=getContentPane();  
		
		 /*---------------------------------- Creating JLabel for Heading Text ------------------------------------------- */
		 
		Font f=new Font("Serif", Font.ITALIC | Font.BOLD, 20);   // Creating font style and size for heading

		// step 3 : creating JLabel for Heading
				JLabel heading_lbl=new JLabel();
				heading_lbl.setBounds(210,15,50,30);
				heading_lbl.setText("<html><font color=white size=6><u><b>Take Flight Decoding and Reading Rate Process Data Manager</b></u></html>");	

				// applying font on  heading Label
				heading_lbl.setFont(f);
				add(heading_lbl);
				
				JLabel heading_lbl2=new JLabel();
				heading_lbl2.setBounds(210,140,1000,30);
				heading_lbl2.setText("<html><font  size=6 color=rgb(0,57,166)><b>Take Flight Decoding and Reading <font color=white size=10>"+className+" </font> Rate Process Data Manager </b></html>");	

				// applying font on  heading Label
				heading_lbl2.setFont(f);
				add(heading_lbl2);
				
				
				model = new DefaultTableModel();
				 
				 jt=new JTable(); 
				 jt.setRowHeight(30);
				 
				 
				 jt.setFont(new Font("Times New Roman", Font.PLAIN, 13));
			     
				 //jt.setBounds(500,250,500,100); 
				 jt.setModel(model);
				 model.addColumn("Id");
				 model.addColumn("Name");
		         model.addColumn("Grade");
		         model.addColumn("DOB");
		         model.addColumn("Start Date");
		        /* JPanel panel = new JPanel ();
			     panel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
			                                                            "Table Title",
			                                                            TitledBorder.CENTER,
			                                                            TitledBorder.TOP));
			      JLabel lbl=new JLabel("Class 2");
			      panel.setLocation(100, 10);
			      panel.add(lbl);
			      
		         add(panel);*/
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
			        
			        for(StudentBean bean : list ){
			        	 model.addRow(new Object[]{bean.getId(),bean.getStudFirstName()+" "+bean.getStudLastName(),bean.getGrade(),bean.getDob(),bean.getStDate()});
			        	 
			         }
			       JTableHeader header= jt.getTableHeader();
			       header.setBackground(Color.yellow);
			       
			       final TableRowSorter<TableModel> sorter;
			       sorter = new TableRowSorter<TableModel>(model);
			       jt.setRowSorter(sorter);
			       
			         JScrollPane scroller = new JScrollPane(jt, 
			                 JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
			  
			         
			         add( scroller); 
			         pack();
			         
			         JPanel pnl = new JPanel();
					 pnl.setBackground(Color.WHITE);
					 pnl.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
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
					    btnSetFE.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
					    pnl.add(btnSetFE);
					    pnl.setBounds(240,400,502,45);
					    add(pnl);
					    
					    
			         
			         	heading_lbl=new JLabel();
						heading_lbl.setBounds(270,400,500,120);
						heading_lbl.setText("<html><font color=red size=6><u><b>Select Group to display list or edit student List</b></u></html>");	

						// applying font on  heading Label
						heading_lbl.setFont(f);
						add(heading_lbl);
		         
						
						
						 btnSubmit = new JButton("Add New Student");
						 if (osname.contains("Mac")){
							 btnSubmit.setBounds(300,500,170,40);
							}else{
								btnSubmit.setBounds(300,500,150,40);
							}
						 
				         btnSubmit.setBackground(Color.WHITE);
				         btnSubmit.setOpaque(true);
				         btnSubmit.setBorderPainted(false);
				         btnSubmit.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
				         add(btnSubmit);
				         btnSubmit.addActionListener(this);
				         
				         btnDelete = new JButton("Remove Student");
				         if (osname.contains("Mac")){
				        	 btnDelete.setBounds(500,500,170,40);
							}else{
								btnDelete.setBounds(500,500,150,40);
							}
				         
				         btnDelete.setBackground(Color.WHITE);
				         btnDelete.setOpaque(true);
				         btnDelete.setBorderPainted(false);
				         btnDelete.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
				         add(btnDelete);
				         btnDelete.addActionListener(this);
				         
				         
				         btnBack = new JButton("Back");
				         btnBack.setBounds(0,600,150,40);
				         btnBack.setBackground(Color.WHITE);
				         btnBack.setOpaque(true);
				         btnBack.setBorderPainted(false);
				         btnBack.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
				         add(btnBack);
				         getContentPane().add(btnBack);
				         btnBack.addActionListener(this);
				         
				         
				         btnExit = new JButton("Exit");
				         btnExit.setBounds(840,600,150,40);
				         btnExit.setBackground(Color.WHITE);
				         btnExit.setOpaque(true);
				         btnExit.setBorderPainted(false);
				         btnExit.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
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
                        , "Delete Seleced Student?",
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
