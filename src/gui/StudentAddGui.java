package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import bean.GroupBean;
import bean.StudentBean;
import bean.adminBean;
import manegement.DateLabelFormatter;
import manegement.GroupOpr;
import manegement.LoginOpr;
import manegement.StudentOpr;
import abstrac.GroupDAO;
import abstrac.LoginDAO;
import abstrac.StudentDAO;

public class StudentAddGui extends JFrame implements ActionListener {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	JButton  btnMgroup,btnMstudents,btnMreport,btnMImportExport,btnMLogout,btnMmyProfile;
	JButton btnSubmit;
	JMenuItem group,students,report;
	JMenu menu;
	JComboBox petList;
	JLabel lblGroup,lblFirstname,lblgrade,lbldob,lblstdate,lblteacher,lblLastName,lblage,lblAegEg;
	JTextField txtFirstname,txtLastName,txtgrade,txtdob,txtstdate,txtteacher,txtage;
	JComboBox comboBox;
	String classId,className;
	JDatePickerImpl DOBdatePicker, STdatePicker ; ;
	
	StudentAddGui(String classId,String className){
		
		this.classId=classId;
		this.className=className;
		
		
		setLayout(new BorderLayout());
		setContentPane(new JLabel(new ImageIcon(this.getClass().getResource("/image/black-back-ground.jpg"))));
		setLayout(null);
		
		JMenuBar mb=new JMenuBar();
		mb.setBackground(new Color(135,206,250));
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
		
		
		Container c=getContentPane();  
		Font f=new Font("Serif", Font.ITALIC | Font.BOLD, 20);   // Creating font style and size for heading
		
		// step 3 : creating JLabel for Heading
		JLabel heading_lbl=new JLabel();
		heading_lbl.setBounds(300,15,500,40);
		heading_lbl.setText("<html><font color=white size=8><u><b>Provide Student details</b></u></html>");
		heading_lbl.setFont(f);
		
		add(heading_lbl);
		
		
		Font f1=new Font("Serif",Font.BOLD,30);
		Font f2=new Font("Serif",Font.BOLD,20);
		
		
	
		
			lblGroup = new JLabel("Class");
			lblGroup.setBounds(280,90,80,30); 
			lblGroup.setForeground(Color.white);
			lblGroup.setFont(f1);
			add(lblGroup);
			
			
			Vector model = new Vector();
			if(classId.length()>0){
				
				 model.addElement( new Item(Integer.parseInt(classId), className ) );
			}else{
				GroupDAO dao=new GroupOpr();
				ArrayList<GroupBean> list=dao.getAllGroups();
				
				
				for(GroupBean bean : list){
					 model.addElement( new Item(bean.getGroupID(), bean.getGroupName() ) );
					
				}
				
			}
			
		       
		        comboBox = new JComboBox( model );
		        comboBox.setBounds(450,90,200,30);
		        comboBox.setFont(f2);
		        comboBox.setRenderer( new ItemRenderer() );
		        comboBox.addActionListener( this );
		        add(comboBox);
			
		 	lblFirstname = new JLabel("Name");
		 	lblFirstname.setBounds(280,170,80,30); 
		 	lblFirstname.setForeground(Color.white);
		 	lblFirstname.setFont(f1);
			add(lblFirstname);
			
			
			txtFirstname = new JTextField();
			txtFirstname.setBounds(450,170,200,30); 
			txtFirstname.setFont(f2);
			add(txtFirstname);
			
			txtLastName = new JTextField();
			txtLastName.setBounds(660,170,200,30); 
			txtLastName.setFont(f2);
			add(txtLastName);
			
			JLabel labelFs=new JLabel("First Name");
			labelFs.setBounds(505,200,200,30); 
			labelFs.setFont(new Font("Serif",Font.PLAIN,15));
			labelFs.setForeground(Color.white);
			add(labelFs);
			
			labelFs=new JLabel("Last Name");
			labelFs.setBounds(705,200,200,30); 
			labelFs.setFont(new Font("Serif",Font.PLAIN,15));
			labelFs.setForeground(Color.white);
			add(labelFs);
			
			lblgrade = new JLabel("Grade");
			if (osname.contains("Mac")){
				lblgrade.setBounds(280,250,100,30); 
				}else{
					lblgrade.setBounds(280,250,80,30); 
				}
			
			lblgrade.setForeground(Color.white);
			lblgrade.setFont(f1);
			add(lblgrade);
			
			txtgrade = new JTextField();
			txtgrade.setBounds(450,250,200,30); 
			txtgrade.setFont(f2);
			add(txtgrade);
			
			lbldob = new JLabel("Date of birth");
			lbldob.setBounds(280,330,200,30); 
			lbldob.setForeground(Color.white);
			lbldob.setFont(f1);
			add(lbldob);
			
			Properties p = new Properties();
			p.put("text.today", "Today");
			p.put("text.month", "Month");
			p.put("text.year", "Year");
			
			UtilDateModel dtmodel = new UtilDateModel();
			dtmodel.setDate(2000, 1, 1);
			dtmodel.setSelected(true);
			JDatePanelImpl datePanel =new JDatePanelImpl(dtmodel, p);
			DOBdatePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
			DOBdatePicker.setBounds(450,330,200,30);
			add(DOBdatePicker);
			
			
		/*	txtdob = new JTextField();
			txtdob.setBounds(450,330,200,30); 
			txtdob.setFont(f2);
			add(txtdob);*/
			
			/*JLabel hint=new JLabel("DD-MM-YYYY");
			hint.setBounds(640,330,200,30); 
			hint.setForeground(Color.white);
			hint.setFont(f2);
			add(hint);*/
			
			
			lblstdate = new JLabel("Start Date");
			lblstdate.setBounds(280,410,200,30); 
			lblstdate.setForeground(Color.white);
			lblstdate.setFont(f1);
			add(lblstdate);
			
			UtilDateModel dtmodel2 = new UtilDateModel();
			//dtmodel2.setDate(2000, 1, 1);
			dtmodel2.setSelected(true);
			JDatePanelImpl datePanel2 =new JDatePanelImpl(dtmodel2, p);
			STdatePicker = new JDatePickerImpl(datePanel2, new DateLabelFormatter());
			STdatePicker.setBounds(450,410,200,30);
			add(STdatePicker);
			
		/*	txtstdate = new JTextField();
			txtstdate.setBounds(430,410,200,30); 
			txtstdate.setFont(f2);
			add(txtstdate);*/
			
			/*hint=new JLabel("DD-MM-YYYY");
			hint.setBounds(640,410,200,30); 
			hint.setForeground(Color.white);
			hint.setFont(f2);
			add(hint);*/
			
			LoginDAO dao= new LoginOpr();
			adminBean bean= dao.getUserName();
			
			
			lblteacher = new JLabel("Teacher");
			lblteacher.setBounds(280,490,200,30); 
			lblteacher.setForeground(Color.white);
			lblteacher.setFont(f1);
			add(lblteacher);
			
			txtteacher = new JTextField();
			txtteacher.setBounds(450,490,200,30); 
			txtteacher.setText(bean.getFirstName()+ " "+bean.getLastName());
			txtteacher.setEditable(false);
			txtteacher.setFont(f2);
			add(txtteacher);
			
			
			lblage = new JLabel("Age");
			lblage.setBounds(280,570,200,30); 
			lblage.setForeground(Color.white);
			lblage.setFont(f1);
			add(lblage);
			
			txtage = new JTextField();
			txtage.setBounds(450,570,200,30); 
			txtage.setFont(f2);
			add(txtage);
			
			lblAegEg=new JLabel("(eg : 7-9)");
			lblAegEg.setBounds(660,570,200,30); 
			lblAegEg.setForeground(Color.white);
			lblAegEg.setFont(f2);
			add(lblAegEg);
			
			btnSubmit = new JButton("Add");
			btnSubmit.setBounds(450,640,200,40); 
			btnSubmit.setBackground(Color.white);
			btnSubmit.setOpaque(true);
			btnSubmit.setBorderPainted(false);
			btnSubmit.setFont(new Font("Britannic Bold", Font.PLAIN, 20));
			//btnSubmit.setFont(f1);
			btnSubmit.addActionListener(this);
			add(btnSubmit);
			
			
			
		
		setSize(1000,800);
		centerFrame();
		setTitle("Progress Monitor Data Manager");
		WindowListener exitListener = new WindowAdapter() {

		    public void windowClosing(WindowEvent e) {
		        int confirm = JOptionPane.showOptionDialog(
		             null, "Are You Sure to Close It?", 
		             "Confirmation", JOptionPane.YES_NO_OPTION, 
		             JOptionPane.QUESTION_MESSAGE, null, null, null);
		        if (confirm == 0) {
		        	new StudentGUI(classId,className);
		        }
		    }
		};
		addWindowListener(exitListener);
		/*setDefaultCloseOperation(javax.swing.
	            WindowConstants.DISPOSE_ON_CLOSE);*/
		//setLayout(null);   
	//	setVisible(true); 
		
		 setLayout(null);
		 //c.setBackground(Color.black);  
			c.setBackground(new Color(135,206,250));  
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
	
	public static void main(String args[]){
		
		new StudentAddGui("","");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Pattern dateFrmtPtrn = Pattern.compile("(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-((19|20)\\d\\d)");
		if(e.getSource()==btnSubmit){
			
			if(txtFirstname.getText().length()==0){
				JOptionPane.showMessageDialog(this,"Please provide Student First Name");
			}
			else if(txtLastName.getText().length()==0){
				JOptionPane.showMessageDialog(this,"Please provide Student Last Name");
			}
			else if(txtgrade.getText().length()==0){
				JOptionPane.showMessageDialog(this,"Please provide Student Grade");
			}
			/*else if(txtdob.getText().length()==0){
				JOptionPane.showMessageDialog(this,"Please provide Student DOB");
			}
			else if(txtstdate.getText().length()==0){
				JOptionPane.showMessageDialog(this,"Please provide Start Date");
			}*/
			else if(txtteacher.getText().length()==0){
				JOptionPane.showMessageDialog(this,"Please provide Class Teacher");
			}
			else if(txtage.getText().length()==0){
				JOptionPane.showMessageDialog(this,"Please provide Class Age");
			}
			else{
				
				boolean flag=true;
				
				try{
		        	Integer.parseInt(txtgrade.getText());
		        }catch(NumberFormatException e1){
		        	JOptionPane.showMessageDialog(this,"Provide Grade in Number format");
		        	flag=false;
		        }
		        
		        if(txtgrade.getText().length() > 1){
		        	JOptionPane.showMessageDialog(this,"Provide Grade only in one digit");
		        	flag=false;
		        }
		        
		        
				/*Matcher mtch = dateFrmtPtrn.matcher(txtdob.getText());
		        if(!mtch.matches()){
		        	JOptionPane.showMessageDialog(this,"DOB Date Format invalid");
		        	flag=false;
		        }
		       
		        mtch = dateFrmtPtrn.matcher(txtstdate.getText());
		        if(!mtch.matches()){
		        	JOptionPane.showMessageDialog(this,"Start Date Format invalid");
		        	flag=false;
		       }*/
		        
		        if(flag){
		        	Item item = (Item)comboBox.getSelectedItem();
		        	//System.out.println( item.getId() + " : " + item.getDescription() );
		        	
		        	StudentBean bean=new StudentBean();
		        	bean.setGroupId(item.getId());
		        	bean.setStudFirstName(txtFirstname.getText());
		        	bean.setStudLastName(txtLastName.getText());
		        	bean.setGrade(Integer.parseInt(txtgrade.getText()));
		        	bean.setDob(DOBdatePicker.getJFormattedTextField().getText());
		        	bean.setStDate(STdatePicker.getJFormattedTextField().getText());
		        	bean.setTeacher(txtteacher.getText());
		        	bean.setAge(txtage.getText());
		        	
		        	StudentDAO dao=new StudentOpr();
		        	boolean done= dao.insertStudent(bean);
		        	if(done){
		        		JOptionPane.showMessageDialog(this,"Student Added Successfully");
		        		synchronized (this) {
			        		new StudentGUI(classId, className);
			        		this.setVisible(false);
		        		}
		        	}
		        	
		            
		        	
		        }
		        
				
			}
			
			
		}
		
		if(e.getSource()==btnMLogout){
			System.exit(0);
		}
		
		
		if(e.getSource()==  btnMstudents){
			synchronized (this) {
	    		new StudentGUI(classId, className);
	    		this.setVisible(false);
			}
		}
		
		if(e.getSource()==btnMgroup){
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
		
		
		if(e.getSource()==btnMImportExport){
			synchronized (this) {
				new GroupStudImportExportGUI(classId, className);
				this.setVisible(false);
			}
		}
		
		
	}

}

class ItemRenderer extends BasicComboBoxRenderer
{
    public Component getListCellRendererComponent(
        JList list, Object value, int index,
        boolean isSelected, boolean cellHasFocus)
    {
        super.getListCellRendererComponent(list, value, index,
            isSelected, cellHasFocus);

        if (value != null)
        {
            Item item = (Item)value;
            setText( item.getDescription().toUpperCase() );
        }

        if (index == -1)
        {
            Item item = (Item)value;
            if(item!=null){
            	setText( "" + item.getId() );
            }else{
            	setText( "" );
            }
            
        }


        return this;
    }
}

class Item
{
    private int id;
    private String description;

    public Item(int id, String description)
    {
        this.id = id;
        this.description = description;
    }

    public int getId()
    {
        return id;
    }

    public String getDescription()
    {
        return description;
    }

    public String toString()
    {
        return description;
    }
}

class ItemJCheckBox
{
    private int id;
    private JCheckBox jcheckbox;

   
    public ItemJCheckBox(int id, JCheckBox jcheckbox)
    {
        this.id = id;
        this.jcheckbox = jcheckbox;
    }

    public JCheckBox getJcheckbox() {
		return jcheckbox;
	}

	public int getId()
    {
        return id;
    }
    
}

