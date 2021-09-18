package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Properties;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import abstrac.GroupDAO;
import bean.GroupBean;
import manegement.DateLabelFormatter;
import manegement.GroupOpr;

public class UpdateGroupGUI extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	JLabel lblClass,lblStartDate;
	JTextField txtClass,txtStartDate;
	JButton btnSubmit,btnDT;
	JPasswordField txtPsw;
	JButton  btnMgroup,btnMstudents,btnMreport,btnMImportExport,btnMLogout,btnMmyProfile;
	JMenuItem group,students,report;
	JMenu menu;
	JDatePickerImpl datePicker ;
	GroupBean gBean;
	
	UpdateGroupGUI(GroupBean gBean){
		
		this.gBean = gBean;
		
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
		
		
		Container c=getContentPane();  
		Font f=FontClass.MuseoSans700Italic(25);
		
		
		// step 3 : creating JLabel for Heading
				JLabel heading_lbl=new JLabel("Provide Group Detail");
				heading_lbl.setBounds(390,60,500,40);
				//heading_lbl.setText("<html><font color=white size=8 ><u><b>Provide Group Detail</b></u></html>");	
				heading_lbl.setFont(f);
				
				add(heading_lbl);
				
				Font f1=FontClass.MuseoSans700(18); 
				Font f2=FontClass.MuseoSans500(20);
				
				
				lblClass = new JLabel("Class Name ");
				lblClass.setBounds(350,160,160,30); 
				//lblClass.setForeground(Color.white);
				lblClass.setFont(f1);
				add(lblClass);
				
				txtClass = new JTextField();
				txtClass.setBounds(470,161,200,30);  
				txtClass.setText(gBean.getGroupName());
				txtClass.setFont(f2);
				add(txtClass);
				
				
				lblStartDate = new JLabel("Start Date");
				lblStartDate.setBounds(360,230,160,30); 
				//lblStartDate.setForeground(Color.white);
				lblStartDate.setFont(f1);
				add(lblStartDate);
				
				
				
				
				Properties p = new Properties();
				p.put("text.today", "Today");
				p.put("text.month", "Month");
				p.put("text.year", "Year");
				
				UtilDateModel model = new UtilDateModel();
				//model.setDate(1990, 8, 24);
				model.setSelected(true);
				JDatePanelImpl datePanel =new JDatePanelImpl(model, p);
				datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
				datePicker.setBounds(470,230,200,30);
				add(datePicker);
				
				txtStartDate = new JTextField();
				txtStartDate.setBounds(470,230,200,30); 
				txtStartDate.setEditable(false);
				txtStartDate.setText(gBean.getStartDate()+"");
				txtStartDate.setFont(f2);
				add(txtStartDate);
				
				
				/*btnDT = new JButton("..hii");
				btnDT.setBounds(720,230,20,20);
				btnDT.setOpaque(true);
				btnDT.setBorderPainted(false);
				btnDT.setFont(new Font("Britannic Bold", Font.PLAIN, 20));
				add(btnDT);
				btnDT.addActionListener(this);
				*/
				btnSubmit = new JButton(new ImageIcon(this.getClass().getResource("/image/add record button.png")));
				btnSubmit.setBounds(450,310,80,60);
				btnSubmit.setOpaque(false);
				btnSubmit.setContentAreaFilled(false);
				btnSubmit.setBorderPainted(false);
				btnSubmit.setFocusable(false);
				btnSubmit.addActionListener(this);
				btnSubmit.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
				        	new GroupGUI();
				        }
				    }
				};
				
				addWindowListener(exitListener);
				setLayout(null); 
				c.setBackground(new Color(135,206,250));  
				setResizable(false);
			    //Below method is for responsive project window 
			    setLocationRelativeTo(null);
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
		
		if(e.getSource()==btnMgroup){
			dispose();
			new GroupGUI();
			
			
		}
		if(e.getSource()==btnMstudents){
			dispose();
			new StudentGUI("", "");
			
			
		}
		if(e.getSource()==btnMLogout){
			System.exit(0);
		}
		if(e.getSource()==btnMImportExport){
			setVisible(false);
			new GroupStudImportExportGUI("", "");
		}
		
		/*if(e.getSource()==datePicker){
			
			Date selectedDate = (Date) datePicker.getModel().getValue();
			SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
			//String dt=
			txtStartDate.setText(dateFormatter.format(selectedDate));
		}*/
		GroupDAO dao=null;
		if(e.getSource()==btnSubmit){
			if(txtClass.getText().length() == 0){
				JOptionPane.showMessageDialog(this,"Please insert Class Name");
				
			}
			else{
				dao=new GroupOpr();
				//GroupBean bean=new GroupBean();
				gBean.setGroupName(txtClass.getText());
				
				gBean.setStartDate(datePicker.getJFormattedTextField().getText());
				//boolean flag= dao.insertGroups(bean);
				boolean flag= dao.updateGroups(gBean);
				if(flag){
					JOptionPane.showMessageDialog(this,"Groups Updated");
					this.setVisible(false);
					new GroupGUI();
				}else{
					JOptionPane.showMessageDialog(this,"Groups not Update Please try again");
					txtClass.setText("");
					txtStartDate.setText("");
					
				}
				
				
			}
		}
		
		if(e.getSource() == btnMmyProfile){
			this.setVisible(false);
			new MyProfileGUI();
		}
		
	}
	
	public static void main(String args[])
	{ 
		//new UpdateGroupGUI();
	
	}

}
