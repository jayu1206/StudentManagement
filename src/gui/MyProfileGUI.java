package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
		
		Container c=getContentPane();  
		
				 /*---------------------------------- Creating JLabel for Heading Text ------------------------------------------- */
				 
				Font f=FontClass.MuseoSans700Italic(25);   // Creating font style and size for heading
	
				// step 3 : creating JLabel for Heading
						JLabel heading_lbl=new JLabel("My Profile");
						heading_lbl.setBounds(450,75,300,60);
						heading_lbl.setFont(f);
						add(heading_lbl);
	
	/* ----------------------------------- Creating Global Font style for all components ------------------------------ */

						Font f1=FontClass.MuseoSans700(18); 
						Font f2=FontClass.MuseoSans500(20);
						
						
						/* ----------------------------------- Creating components for Registration details ---------------------------------- */
						
						// Step 4 : Creating JLabel for Name
						
						lblFirstName=new JLabel("First Name");
						lblFirstName.setBounds(360,180,250,40);
						//lblFirstName.setForeground(Color.white);
						lblFirstName.setFont(f1);
						add(lblFirstName);
				
						// Creating JTextField for Father's name
						txtFirstName=new JTextField();
						txtFirstName.setText(bean.getFirstName());
						//txtFirstName.setEditable(false);
						txtFirstName.setBounds(480,190,180,30);
						add(txtFirstName);
		
						
						lblLastName=new JLabel("Last Name");
						lblLastName.setBounds(360,225,250,40);
						//lblLastName.setForeground(Color.white);
						lblLastName.setFont(f1);
						add(lblLastName);
						
						txtLastName=new JTextField();
						txtLastName.setBounds(480,235,180,30);
						txtLastName.setText(bean.getLastName());
						//txtLastName.setEditable(false);
						add(txtLastName);
						
						lblemail=new JLabel("Email");
						lblemail.setBounds(400,280,180,30);
						//lblemail.setForeground(Color.white);
						lblemail.setFont(f1);
						add(lblemail);
						
						txtemail=new JTextField();
						txtemail.setBounds(480,280,180,30);
						txtemail.setText(bean.getEmail());
						//txtemail.setEditable(false);
						add(txtemail);
						
						
						lblUserId = new JLabel("Username  ");
						lblUserId.setBounds(360,330,150,30); 
						//lblUserId.setForeground(Color.white);
						lblUserId.setFont(f1);
						add(lblUserId);
						
						txtUserId=new JTextField();
						txtUserId.setBounds(480,330,180,30);
						txtUserId.setText(bean.getUserID());
						//txtUserId.setEditable(false);
						add(txtUserId);
						
						
						//bG.add(changePsw);
						if (osname.contains("Mac")){
							changePsw.setBounds(350, 400, 330, 40);
						}else{
							changePsw.setBounds(350, 400, 310, 40);
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
						lblPsw.setBounds(320,460,200,30);
						//lblPsw.setForeground(Color.white);
						lblPsw.setFont(f1);
						add(lblPsw);
				
						// Creating JTextField for Father's name
						txtPsw=new JPasswordField();
						txtPsw.setEditable(false);
						txtPsw.setBounds(480,460,180,30);
						add(txtPsw);
						
						
						/*lblPhno=new JLabel("Phone No : ");
						lblPhno.setBounds(50,240,150,30);
						add(lblPhno);
						
						txtPhno=new JTextField();
						txtPhno.setBounds(180,240,180,30);
						add(txtPhno);*/
						
						
						btnSubmit =  new JButton(new ImageIcon(this.getClass().getResource("/image/save record button.png")));
						if (osname.contains("Mac")){
							btnSubmit.setBounds(530,550,100,50);
						}else{
							btnSubmit.setBounds(530,550,80,50);
						}
						btnSubmit.setOpaque(false);
						btnSubmit.setContentAreaFilled(false);
						btnSubmit.setBorderPainted(false);
						btnSubmit.addActionListener(this);
						add(btnSubmit);
						
						
						btnBack =  new JButton(new ImageIcon(this.getClass().getResource("/image/back2.png")));
						btnBack.setBounds(370,550,120,50);
						btnBack.setOpaque(false);
						btnBack.setContentAreaFilled(false);
						btnBack.setBorderPainted(false);
						btnBack.addActionListener(this);
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
