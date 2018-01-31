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
import javax.swing.border.TitledBorder;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.basic.BasicTableUI;
import javax.swing.plaf.basic.BasicTableUI.MouseInputHandler;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

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
	JButton  btnMgroup,btnMstudents,btnMreport,btnMImportExport,btnMLogout;
	JMenuItem group,students,report;
	JMenu menu;
	String classId,className;
	
	
	StudentGUI(String classId,String className){
		
		this.classId=classId;
		this.className=className;
		
		
		setLayout(new BorderLayout());
		setContentPane(new JLabel(new ImageIcon(this.getClass().getResource("/image/black-back-ground.jpg"))));
		setLayout(new FlowLayout());
		
		JMenuBar mb=new JMenuBar();
		mb.setBackground(new Color(135,206,250));
        
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
				heading_lbl2.setText("<html><font  size=6><b>Take Flight Decoding and Reading <font color=white size=10>"+className+" </font> Rate Process Data Manager </b></html>");	

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
		        		    new Dimension(800,300)); 
		         
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
			       
			         JScrollPane scroller = new JScrollPane(jt, 
			                 JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
			  
			         
			         add( scroller); 
			         pack();
			         
			         
			         
			         	heading_lbl=new JLabel();
						heading_lbl.setBounds(270,380,500,120);
						heading_lbl.setText("<html><font color=red size=6><u><b>Select Group to display list or edit student List</b></u></html>");	

						// applying font on  heading Label
						heading_lbl.setFont(f);
						add(heading_lbl);
		         
						
						
						 btnSubmit = new JButton("Add New Student");
						 btnSubmit.setBounds(300,500,150,40);
				         btnSubmit.setBackground(Color.WHITE);
				         btnSubmit.setOpaque(true);
				         btnSubmit.setBorderPainted(false);
				         btnSubmit.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
				         add(btnSubmit);
				         btnSubmit.addActionListener(this);
				         
				         btnDelete = new JButton("Remove Student");
				         btnDelete.setBounds(500,500,150,40);
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
			this.setVisible(false);
			new GroupGUI();
			
		}
		if(e.getSource()==btnExit){
			System.exit(0);
			
		}
		
		if(e.getSource()==btnSubmit){
			dispose();
			new StudentAddGui(this.classId,this.className);
			
		}
		
		if(e.getSource()==btnMgroup){
			dispose();
			new GroupGUI();
			
			
		}
		if(e.getSource()==btnMstudents){
			dispose();
			new StudentGUI("", "");
		}
		
		if(e.getSource()==btnMImportExport){
			setVisible(false);
			new GroupStudImportExportGUI();
		}
		
		if(e.getSource()==btnMLogout){
			System.exit(0);
		}
		
		if(e.getSource()==btnDelete){
			DefaultTableModel dtm = (DefaultTableModel) jt.getModel();  
            int selRow = jt.getSelectedRow();
			if(selRow<0){
				JOptionPane.showMessageDialog(this,"Please select Student");
				 
				
			}else{
				
				int n = JOptionPane.showConfirmDialog(this
                        , "Delete Seleced Student?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION);
				 if (n == JOptionPane.YES_OPTION) {
					   String value = jt.getModel().getValueAt(selRow, 0).toString();
					   dao=new StudentOpr();
					   boolean flag=  dao.deleteStudent(value);
					   if(flag){
						   dtm.removeRow(selRow); 
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
