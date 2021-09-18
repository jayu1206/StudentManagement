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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import manegement.LoginOpr;
import manegement.RegistrationOpr;
import abstrac.LoginDAO;
import abstrac.RegistrationDAO;
import bean.adminBean;

public class MyProfileGUI extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	JLabel lblUserId,lblPsw,lblFirstName,lblLastName,lblPhno,lblemail;
	JTextField txtUserId,txtFirstName,txtLastName,txtPhno,txtemail;
	JButton btnMgroup, btnMstudents, btnMreport, btnMImportExport, btnMLogout,btnMmyProfile;
	JButton btnSubmit,btnBack;
	JPasswordField txtPsw;
	JRadioButton changePsw = new JRadioButton("Click here to change password");
	ButtonGroup bG = new ButtonGroup();
	adminBean bean = null;
	
	
	MyProfileGUI(){
		String osname = System.getProperty("os.name");
		
		LoginDAO dao= new LoginOpr();
		bean= dao.getUserName();		
		
		setLayout(new BorderLayout());
		setContentPane(new JLabel(new ImageIcon(this.getClass().getResource("/image/sky.png"))));
		setLayout(null);
		
		
		JMenuBar mb = null;
		
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
		
				 /*---------------------------------- Creating JLabel for Heading Text ------------------------------------------- */
				 
				Font f=FontClass.MuseoSans700Italic(25);   // Creating font style and size for heading
	
				// step 3 : creating JLabel for Heading
						JLabel heading_lbl=new JLabel("My Profile");
						heading_lbl.setBounds(450,55,300,60);
						heading_lbl.setFont(f);
						add(heading_lbl);
	
	/* ----------------------------------- Creating Global Font style for all components ------------------------------ */

						Font f1=FontClass.MuseoSans700(18); 
						Font f2=FontClass.MuseoSans500(20);
						
						Font f3 = FontClass.MuseoSans500(15);
						f3.deriveFont(Font.PLAIN, 15);
						
						
						/* ----------------------------------- Creating components for Registration details ---------------------------------- */
						
						// Step 4 : Creating JLabel for Name
						
						lblFirstName=new JLabel("First Name");
						lblFirstName.setBounds(360,160,250,40);
						//lblFirstName.setForeground(Color.white);
						lblFirstName.setFont(f1);
						add(lblFirstName);
				
						// Creating JTextField for Father's name
						txtFirstName=new JTextField();
						txtFirstName.setText(bean.getFirstName());
						//txtFirstName.setEditable(false);
						txtFirstName.setBounds(480,170,180,30);
						txtFirstName.setFont(f3);
						add(txtFirstName);
		
						
						lblLastName=new JLabel("Last Name");
						lblLastName.setBounds(360,205,250,40);
						//lblLastName.setForeground(Color.white);
						lblLastName.setFont(f1);
						add(lblLastName);
						
						txtLastName=new JTextField();
						txtLastName.setBounds(480,215,180,30);
						txtLastName.setText(bean.getLastName());
						txtLastName.setFont(f3);
						//txtLastName.setEditable(false);
						add(txtLastName);
						
						lblemail=new JLabel("Email");
						lblemail.setBounds(400,270,180,30);
						//lblemail.setForeground(Color.white);
						lblemail.setFont(f1);
						add(lblemail);
						
						txtemail=new JTextField();
						txtemail.setBounds(480,270,180,30);
						txtemail.setText(bean.getEmail());
						txtemail.setFont(f3);
						//txtemail.setEditable(false);
						add(txtemail);
						
						
						lblUserId = new JLabel("Username  ");
						lblUserId.setBounds(360,310,150,30); 
						//lblUserId.setForeground(Color.white);
						lblUserId.setFont(f1);
						add(lblUserId);
						
						txtUserId=new JTextField();
						txtUserId.setBounds(480,310,180,30);
						txtUserId.setText(bean.getUserID());
						txtUserId.setFont(f3);
						//txtUserId.setEditable(false);
						add(txtUserId);
						
						
						//bG.add(changePsw);
						if (osname.contains("Mac")){
							changePsw.setBounds(350, 380, 330, 40);
						}else{
							changePsw.setBounds(350, 380, 310, 40);
						}
						
						//changePsw.setBackground(new Color(242,242,242));
						//changePsw.setForeground(Color.white)
						changePsw.setOpaque(false);
						changePsw.setContentAreaFilled(false);
						changePsw.setBorderPainted(false);
						changePsw.setFont(f1);
						changePsw.addActionListener(this);
						add(changePsw);
						
						lblPsw=new JLabel("New Password");
						lblPsw.setBounds(320,440,200,30);
						//lblPsw.setForeground(Color.white);
						lblPsw.setFont(f1);
						add(lblPsw);
				
						// Creating JTextField for Father's name
						txtPsw=new JPasswordField();
						txtPsw.setEditable(false);
						txtPsw.setBounds(480,440,180,30);
						add(txtPsw);
						
						
						/*lblPhno=new JLabel("Phone No : ");
						lblPhno.setBounds(50,240,150,30);
						add(lblPhno);
						
						txtPhno=new JTextField();
						txtPhno.setBounds(180,240,180,30);
						add(txtPhno);*/
						
						
						btnSubmit =  new JButton(new ImageIcon(this.getClass().getResource("/image/save record button.png")));
						if (osname.contains("Mac")){
							btnSubmit.setBounds(530,530,100,50);
						}else{
							btnSubmit.setBounds(530,530,80,50);
						}
						btnSubmit.setOpaque(false);
						btnSubmit.setContentAreaFilled(false);
						btnSubmit.setBorderPainted(false);
						btnSubmit.addActionListener(this);
						btnSubmit.setCursor(new Cursor(Cursor.HAND_CURSOR));
						add(btnSubmit);
						
						
						btnBack =  new JButton(new ImageIcon(this.getClass().getResource("/image/back2.png")));
						btnBack.setBounds(370,530,120,50);
						btnBack.setOpaque(false);
						btnBack.setContentAreaFilled(false);
						btnBack.setBorderPainted(false);
						btnBack.addActionListener(this);
						btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
						add(btnBack);
						
						
						
						
						/* ---------------------------------- Creating JFrame -------------------------------------------------------- */
						// Step 1 :  Creating a frame using JFrame class	
						setSize(1000,800);  
						centerFrame();
					    setTitle("Progress Monitor Data Manager");
					    setDefaultCloseOperation(javax.swing.
					            WindowConstants.EXIT_ON_CLOSE);
					    c.setLayout(null);    
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
			synchronized (this) {
				new GroupGUI();
				dispose();
			}
		}
		if(e.getSource() == btnMmyProfile){
			synchronized (this) {
				new MyProfileGUI();
				this.setVisible(false);
			}
		}
		if(e.getSource()==btnMstudents){
			synchronized (this) {
				new StudentGUI("", "");
				dispose();
			}
			
			
		}
		if(e.getSource()==btnMLogout){
			System.exit(0);
		}
		if(e.getSource()==btnMImportExport){
			synchronized (this) {
				new GroupStudImportExportGUI("", "");
				setVisible(false);
			}
		}
		
		
			if(changePsw.isSelected()){
				txtPsw.setEditable(true);
				txtFirstName.setEditable(true);
				txtLastName.setEditable(true);
				txtemail.setEditable(true);
				txtUserId.setEditable(true);
				//JOptionPane.showMessageDialog(this,"Failed! Please try again..");
			}else{
				txtPsw.setText("");
				txtPsw.setEditable(false);				
				txtFirstName.setEditable(false);
				txtLastName.setEditable(false);
				txtemail.setEditable(false);		
				
			}
		
			RegistrationDAO dao= new RegistrationOpr();
			
			if(e.getSource()==btnSubmit){
				if(changePsw.isSelected()){
					
					if(txtPsw.getText().length()==0){
						JOptionPane.showMessageDialog(this,"Please provide new password ");
						
					}else{
						bean.setPsw(txtPsw.getText());
						bean.setFirstName(txtFirstName.getText());
						bean.setLastName(txtLastName.getText());
						bean.setEmail(txtemail.getText());
						bean.setUserID(txtUserId.getText());
						boolean flag = dao.updateNewPassword(bean);
						if(flag){
							JOptionPane.showMessageDialog(this,"Profile Updated Successfully ");
							synchronized (this) {
								new welcomeGUI();
								this.setVisible(false);
							}
						}
					}
					
					
					
					
				}else{
					bean.setPsw(bean.getPsw());
					bean.setFirstName(txtFirstName.getText());
					bean.setLastName(txtLastName.getText());
					bean.setEmail(txtemail.getText());
					bean.setUserID(txtUserId.getText());
					boolean flag = dao.updateNewPassword(bean);
					if(flag){
						JOptionPane.showMessageDialog(this,"Profile Updated Successfully ");
						synchronized (this) {
							new welcomeGUI();
							this.setVisible(false);
						}
					}
					
				}
				
			}
			
				
			if(e.getSource()==btnBack){
				synchronized (this) {
					new welcomeGUI();
					this.setVisible(false);
				}
			}
		
	}
	
	/*public static void main(String args[])
	{ 
		new MyProfileGUI();
	
	}*/
}
