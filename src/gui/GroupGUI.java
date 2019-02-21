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
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.basic.BasicTableUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import bean.GroupBean;
import manegement.GroupOpr;
import abstrac.GroupDAO;

public class GroupGUI extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	DefaultTableModel model ;
	JTable jt;
	JButton btnSubmit,btnDelete,btnImportExport,btnBack,btnExit;
	JButton  btnMgroup,btnMstudents,btnMreport,btnMImportExport,btnMLogout,btnMmyProfile;
	JMenuItem group,students,report;
	JMenu menu;
	JMenuItem edit;
	GroupGUI(){
		System.out.println("GroupGUI");
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
		
       
		Font f=FontClass.MuseoSans700Italic(25);   // Creating font style and size for heading
		
		JLabel heading_lbl=new JLabel("Take Flight Decoding and Reading Rate Progress Data Manager");
		heading_lbl.setBounds(150,15,800,30);
		heading_lbl.setFont(f);
		add(heading_lbl);
		
		/*heading_lbl=new JLabel("");
		heading_lbl.setBounds(350,40,600,30);
		heading_lbl.setFont(f);
		add(heading_lbl);*/
		
		
		//setLayout(new FlowLayout());
		
		
		Font f3 = FontClass.MuseoSans500(15);
		f3.deriveFont(Font.PLAIN, 15);
		
		model = new DefaultTableModel();
		 
		 jt=new JTable(); 
		 jt.setRowHeight(30);
		 
		 jt.setFont(f3);
	     
		 jt.setBounds(250,350,500,100); 
		// jt.setPreferredSize(new Dimension(500, 300));
		 jt.setModel(model);
		 //model.addRow(new Object[]{"class details"});
		
		model.addColumn("ID");
        model.addColumn("Class");
        model.addColumn("Start Date");
        
        //model.addRow(new Object[]{"Id1","Class","Start Date"});
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        
        jt.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        jt.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
        jt.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
        
        jt.setPreferredScrollableViewportSize(new Dimension(500,300)); 
        
     
      
      
       // jt.setPreferredScrollableViewportSize(jt.getPreferredSize());
      
      jt.setDefaultEditor(Object.class, null);
      jt.setUI(new BasicTableUI() {
	    	// Create the mouse listener for the JTable.
	    	protected MouseInputListener createMouseInputListener() {
		    	return new MouseInputHandler() {
			    	// Display frame on double-click
			    	public void mouseClicked(MouseEvent e) {
				    	if (e.getClickCount()==2) {
				    		//DefaultTableModel dtm = (DefaultTableModel) jt.getModel();  
				            int selRow = jt.getSelectedRow();
				            String id = jt.getModel().getValueAt(selRow, 0).toString();
				            String name = jt.getModel().getValueAt(selRow, 1).toString();
				    		new StudentGUI(id,name);
				    		dispose();
				    	}
			    	}
		    	};
	    	}
   	});
      
       edit = new JMenuItem("Edit");
		JPopupMenu popup = new JPopupMenu("Edit");
		popup.add(edit);

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

		edit.addActionListener(this);
		
      GroupDAO dao=new GroupOpr();
       ArrayList<GroupBean> list = dao.getAllGroups();
       SimpleDateFormat ddmmyyyy = new SimpleDateFormat("dd-MM-yyyy");
	   SimpleDateFormat mmddyyyy = new SimpleDateFormat("MM/dd/yyyy");
	   
       for(GroupBean bean : list ){
    	   try {
				Date dt = ddmmyyyy.parse(bean.getStartDate());
				bean.setStartDate(mmddyyyy.format(dt));
				
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}
       	 model.addRow(new Object[]{bean.getGroupID(),bean.getGroupName(),bean.getStartDate()});
       	 
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
        panelGeneral.setBounds(240, 100, 520, 330);
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
		        sorter.setRowFilter(RowFilter.regexFilter("(?i)"+expr));
		        sorter.setSortKeys(null);
		        
		        
		      }
		    };
		    btnSetFE.addActionListener(al);
		   // btnSetFE.setBackground(Color.WHITE);
		    btnSetFE.setOpaque(true);
		    btnSetFE.setBorderPainted(false);
		    btnSetFE.setFont(f4);
		    pnl.add(btnSetFE);
		    pnl.setBounds(240,435,520,45);
		    add(pnl);
       
		
		    heading_lbl=new JLabel("Double click on group to list or edit student data");
			heading_lbl.setBounds(280,430,500,130);
			heading_lbl.setFont(f4);
			add(heading_lbl);
		
		
			
			 btnSubmit = new JButton("Add New Group");
			 
	         if (osname.contains("Mac")){
	        	 btnSubmit.setBounds(240,520,170,40);
				}else{
					btnSubmit.setBounds(240,520,150,40);
				}
	         
	         btnSubmit.setBackground(Color.WHITE);
	         btnSubmit.setOpaque(true);
	         btnSubmit.setBorderPainted(false);
	         btnSubmit.setFont(FontClass.MuseoSans900(15));
	         btnSubmit.addActionListener(this);
	         btnSubmit.setCursor( Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	         add(btnSubmit);
	         
	         
	         btnDelete = new JButton("Remove Group");
	         btnDelete.setBounds(420,520,150,40);
	         btnDelete.setBackground(Color.WHITE);
	         btnDelete.setOpaque(true);
	         btnDelete.setBorderPainted(false);
	         btnDelete.setFont(FontClass.MuseoSans900(15));
	         btnDelete.setCursor( Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	         add(btnDelete);
	         btnDelete.addActionListener(this);
	         
	         btnImportExport = new JButton("Import/Export");
	         btnImportExport.setBounds(610,520,150,40);
	         btnImportExport.setBackground(Color.WHITE);
	         btnImportExport.setOpaque(true);
	         btnImportExport.setBorderPainted(false);
	         btnImportExport.setFont(FontClass.MuseoSans900(15));
	         btnImportExport.setCursor( Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	         add(btnImportExport);
	         getContentPane().add(btnImportExport);
	         btnImportExport.addActionListener(this);
	         
	         
	         btnBack = new JButton(new ImageIcon(this.getClass().getResource("/image/back.png")));
	         btnBack.setBounds(100,600,120,40);
	         btnBack.setOpaque(false);
	         btnBack.setContentAreaFilled(false);
	         btnBack.setBorderPainted(false);
	         btnBack.setFocusable(false);
	         btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
	         add(btnBack);
	         getContentPane().add(btnBack);
	         btnBack.addActionListener(this);
	         
	         
	         btnExit = new JButton(new ImageIcon(this.getClass().getResource("/image/Exit2.png")));
	         btnExit.setBounds(800,600,120,40);
	         btnExit.setFocusable(false);
	         btnExit.setOpaque(true);
	         btnExit.setBorderPainted(false);
	         btnExit.setCursor(new Cursor(Cursor.HAND_CURSOR));
	         add(btnExit);
	         getContentPane().add(btnExit);
	         btnExit.addActionListener(this);
		
		
		
		         
		
		setSize(1000,800);
		         //setPreferredSize(new Dimension(400, 400));
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
	   // c.setLayout(null); 
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

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		GroupDAO dao=null;
		if(e.getSource()==btnSubmit){
			synchronized (this) {
				new AddGroupGUI();
				dispose();
			}
			
			
		}
		
		if(e.getSource()==btnMLogout){
			System.exit(0);
		}
		if(e.getSource()==btnMImportExport || e.getSource()==btnImportExport){
			synchronized (this) {
				setVisible(false);
				new GroupStudImportExportGUI("", "");
			}
			
		}
		if(e.getSource()==btnMgroup){
			synchronized (this) {
				new GroupGUI();
				dispose();
			}
			
			
		}
		if(e.getSource()==btnMstudents){
			synchronized (this) {
				new StudentGUI("", "");
				dispose();
			}
			
			
		}
		
		if(e.getSource()==btnBack){
			synchronized (this) {
				new welcomeGUI();
				this.setVisible(false);
			}
			
		}
		if(e.getSource()==btnExit){
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
            int selRow =  jt.getSelectedRow();
            int modelRow = jt.convertRowIndexToModel(selRow);
           // int selCol = jt.getSelectedColumn();
			if(selRow<0){
				JOptionPane.showMessageDialog(this,"Please select Group");
				 
				
			}else{
				
				int n = JOptionPane.showConfirmDialog(this
                        , "Remove group and all it's students data?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION);
				 if (n == JOptionPane.YES_OPTION) {
					 
					   String value = jt.getModel().getValueAt(modelRow, 0).toString();
					   //String value2 = jt.getModel().getValueAt(modelRow, 0).toString();
					   dao=new GroupOpr();
					   boolean flag= dao.deleteGroups(value);
					   if(flag){
						   dtm.removeRow(modelRow); 
					   }else{
						   JOptionPane.showMessageDialog(this,"Failed! Please try again..");
					   }
		               
				 } 				
				
			}
			
			
		}
		
		if (e.getSource() == edit) {
			
			GroupDAO groupDAO = new GroupOpr();
			DefaultTableModel dtm = (DefaultTableModel) jt.getModel();
			int selRow = jt.getSelectedRow();
			String value = jt.getModel().getValueAt(selRow, 0).toString();
		
			GroupBean gBean =  groupDAO.getGroup(Integer.parseInt(value));
			synchronized (this) {
				new UpdateGroupGUI(gBean);
				this.setVisible(false);
			}
		}
		
		
	}
	
	private static class HeaderRenderer implements TableCellRenderer {
        private DefaultTableCellRenderer renderer;

        private HeaderRenderer(JTable table) {
            renderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        }

        @Override
        public Component getTableCellRendererComponent(JTable table,
                Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            JComponent comp = (JComponent) renderer.getTableCellRendererComponent(table, value, 
                    isSelected, hasFocus, row, column);
            Border originalBorder = comp.getBorder();
            MatteBorder border = new MatteBorder(1, 1, 1, 1, Color.blue);
	        comp.setBorder(border);
	        
            //comp.setBorder( 
            //        BorderFactory.createEmptyBorder(0, 20, 0, 0));
            return comp;
        }
    }
	
public static void main(String args[]){
		
		new GroupGUI();
	}

}
