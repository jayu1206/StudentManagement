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
		
		
		LoginDAO dao= new LoginOpr();
		bean= dao.getUserName();		
		
		setLayout(new BorderLayout());
		setContentPane(new JLabel(new ImageIcon(this.getClass().getResource("/image/black-back-ground.jpg"))));
		setLayout(null);
		
		Container c=getContentPane();  
		
				 /*---------------------------------- Creating JLabel for Heading Text ------------------------------------------- */
				 
				Font f=new Font("Serif", Font.ITALIC | Font.BOLD, 35);   // Creating font style and size for heading
	
				// step 3 : creating JLabel for Heading
						JLabel heading_lbl=new JLabel();
						heading_lbl.setBounds(400,15,300,60);
						heading_lbl.setText("<html><font color=white ><u><b>My Profile</b></u></html>");	
	
						// applying font on  heading Label
						heading_lbl.setFont(f);
						add(heading_lbl);
	
	/* ----------------------------------- Creating Global Font style for all components ------------------------------ */

						Font f1=new Font("Serif",Font.BOLD,25);
						Font f2=new Font("Serif",Font.BOLD,20);
						
						
						/* ----------------------------------- Creating components for Registration details ---------------------------------- */
						
						// Step 4 : Creating JLabel for Name
						
						lblFirstName=new JLabel("First Name : ");
						lblFirstName.setBounds(250,180,250,40);
						lblFirstName.setForeground(Color.white);
						lblFirstName.setFont(f1);
						add(lblFirstName);
				
						// Creating JTextField for Father's name
						txtFirstName=new JTextField();
						txtFirstName.setText(bean.getFirstName());
						txtFirstName.setEditable(false);
						txtFirstName.setBounds(500,190,180,30);
						add(txtFirstName);
		
						
						lblLastName=new JLabel("Last Name : ");
						lblLastName.setBounds(250,225,250,40);
						lblLastName.setForeground(Color.white);
						lblLastName.setFont(f1);
						add(lblLastName);
						
						txtLastName=new JTextField();
						txtLastName.setBounds(500,235,180,30);
						txtLastName.setText(bean.getLastName());
						txtLastName.setEditable(false);
						add(txtLastName);
						
						lblemail=new JLabel("Email : ");
						lblemail.setBounds(250,280,180,30);
						lblemail.setForeground(Color.white);
						lblemail.setFont(f1);
						add(lblemail);
						
						txtemail=new JTextField();
						txtemail.setBounds(500,280,180,30);
						txtemail.setText(bean.getEmail());
						txtemail.setEditable(false);
						add(txtemail);
						
						
						lblUserId = new JLabel("Username : ");
						lblUserId.setBounds(250,330,150,30); 
						lblUserId.setForeground(Color.white);
						lblUserId.setFont(f1);
						add(lblUserId);
						
						txtUserId=new JTextField();
						txtUserId.setBounds(500,330,180,30);
						txtUserId.setText(bean.getUserID());
						txtUserId.setEditable(false);
						add(txtUserId);
						
						
						//bG.add(changePsw);
						changePsw.setBounds(300, 400, 350, 40);
						changePsw.setBackground(Color.black);
						changePsw.setForeground(Color.white);
						changePsw.setFont(f1);
						changePsw.addActionListener(this);
						add(changePsw);
						
						lblPsw=new JLabel("New Password : ");
						lblPsw.setBounds(250,460,200,30);
						lblPsw.setForeground(Color.white);
						lblPsw.setFont(f1);
						add(lblPsw);
				
						// Creating JTextField for Father's name
						txtPsw=new JPasswordField();
						txtPsw.setEditable(false);
						txtPsw.setBounds(500,460,180,30);
						add(txtPsw);
						
						
						/*lblPhno=new JLabel("Phone No : ");
						lblPhno.setBounds(50,240,150,30);
						add(lblPhno);
						
						txtPhno=new JTextField();
						txtPhno.setBounds(180,240,180,30);
						add(txtPhno);*/
						
						
						btnSubmit =  new JButton("Submit");
						btnSubmit.setBounds(510,550,100,30);
						btnSubmit.setOpaque(true);
						btnSubmit.setBorderPainted(false);
						btnSubmit.setFont(new Font("Britannic Bold", Font.PLAIN, 20));
						 /*Image img;
							try {
								img = ImageIO.read(getClass().getResource("/image/submit_icon.png"));
								btnSubmit.setIcon(new ImageIcon(img));
								btnSubmit.setBorder(null);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}*/
						add(btnSubmit);
						btnSubmit.addActionListener(this);
						
						btnBack =  new JButton("Back");
						btnBack.setBounds(350,550,100,30);
						btnBack.setOpaque(true);
						btnBack.setBorderPainted(false);
						btnBack.setFont(new Font("Britannic Bold", Font.PLAIN, 20));
						/*try {
							img = ImageIO.read(getClass().getResource("/image/back.png"));
							btnBack.setIcon(new ImageIcon(img));
							btnBack.setBorder(null);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}*/
						add(btnBack);
						btnBack.addActionListener(this);
						
						
						
						/* ---------------------------------- Creating JFrame -------------------------------------------------------- */
						// Step 1 :  Creating a frame using JFrame class	
						setSize(1000,800);  
						centerFrame();
					    setTitle("Progress Monitor Data Manager");
					    setDefaultCloseOperation(javax.swing.
					            WindowConstants.DISPOSE_ON_CLOSE);
					    c.setLayout(null);    
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
							JOptionPane.showMessageDialog(this,"Password Change Successfully ");
							setVisible(false);
							new welcomeGUI();
						}
					}
					
					
					
					
				}
				
			}
			
				
			if(e.getSource()==btnBack){
				setVisible(false);
				new welcomeGUI();
			}
		
	}
	
	public static void main(String args[])
	{ 
		new MyProfileGUI();
	
	}
}
