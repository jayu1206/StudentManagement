package gui;

import javax.swing.*;

import manegement.RegistrationOpr;
import abstrac.RegistrationDAO;
import bean.adminBean;

import java.awt.*;
import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegistratonGUI extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	JLabel lblUserId,lblPsw,lblFirstName,lblLastName,lblPhno,lblemail;
	JTextField txtUserId,txtFirstName,txtLastName,txtPhno,txtemail;
	JButton btnSubmit,btnBack;
	JPasswordField txtPsw;
	
	
	
	 
	// frame=new JFrame("Registration Form Example"); 
	
	public RegistratonGUI(){
		
		setLayout(new BorderLayout());
		setContentPane(new JLabel(new ImageIcon(this.getClass().getResource("/image/login background2.png"))));
		setLayout(null);
		
		Container c=getContentPane();  
		
				 /*---------------------------------- Creating JLabel for Heading Text ------------------------------------------- */
				 
		Font f=FontClass.MuseoSans700Italic(30);    // Creating font style and size for heading
	
				// step 3 : creating JLabel for Heading
						JLabel heading_lbl=new JLabel("Provide your details");
						heading_lbl.setBounds(360,160,300,60);
	
						// applying font on  heading Label
						heading_lbl.setFont(f);
						add(heading_lbl);
	
	/* ----------------------------------- Creating Global Font style for all components ------------------------------ */

						Font f1=FontClass.MuseoSans700(18); 
						Font f2=FontClass.MuseoSans500(20);
						
						
						/* ----------------------------------- Creating components for Registration details ---------------------------------- */
						
						// Step 4 : Creating JLabel for Name
						
						lblFirstName=new JLabel("First Name");
						lblFirstName.setBounds(350,240,250,40);
						lblFirstName.setFont(f1);
						add(lblFirstName);
				
						// Creating JTextField for Father's name
						txtFirstName=new JTextField();
						txtFirstName.setBounds(480,250,180,30);
						txtFirstName.setFont(f2);
						add(txtFirstName);
		
						
						lblLastName=new JLabel("Last Name");
						lblLastName.setBounds(350,285,250,40);
						lblLastName.setFont(f1);
						add(lblLastName);
						
						txtLastName=new JTextField();
						txtLastName.setBounds(480,295,180,30);
						txtLastName.setFont(f2);
						add(txtLastName);
						
						lblemail=new JLabel("Email");
						lblemail.setBounds(390,340,180,30);
						lblemail.setFont(f1);
						add(lblemail);
						
						txtemail=new JTextField();
						txtemail.setBounds(480,340,180,30);
						txtemail.setFont(f2);
						add(txtemail);
						
						
						lblUserId = new JLabel("Username");
						lblUserId.setBounds(350,390,150,30); 
						lblUserId.setFont(f1);
						add(lblUserId);
						
						txtUserId=new JTextField();
						txtUserId.setBounds(480,390,180,30);
						txtUserId.setFont(f2);
						add(txtUserId);
						
						lblPsw=new JLabel("Password");
						lblPsw.setBounds(355,440,150,30);
						lblPsw.setFont(f1);
						add(lblPsw);
				
						// Creating JTextField for Father's name
						txtPsw=new JPasswordField();
						txtPsw.setBounds(480,440,180,30);
						txtPsw.setFont(f2);
						add(txtPsw);
						
						
						
						
						/*lblPhno=new JLabel("Phone No : ");
						lblPhno.setBounds(50,240,150,30);
						add(lblPhno);
						
						txtPhno=new JTextField();
						txtPhno.setBounds(180,240,180,30);
						add(txtPhno);*/
						
						
						btnSubmit =  new JButton(new ImageIcon(this.getClass().getResource("/image/save record button.png")));
						btnSubmit.setBounds(550,520,80,50);
						btnSubmit.setOpaque(false);
						btnSubmit.setContentAreaFilled(false);
						btnSubmit.setBorderPainted(false);
						add(btnSubmit);
						btnSubmit.addActionListener(this);
						
						btnBack =  new JButton(new ImageIcon(this.getClass().getResource("/image/back.png")));
						btnBack.setBounds(390,520,120,50);
						btnBack.setOpaque(false);
						btnBack.setContentAreaFilled(false);
						btnBack.setBorderPainted(false);
						btnBack.addMouseListener(new java.awt.event.MouseAdapter()
					    {
					        public void mousePressed(java.awt.event.MouseEvent evt)
					        {
					        	btnBack.setBackground(new Color(255,255,255));
					        }
					    });
						add(btnBack);
						btnBack.addActionListener(this);
						
								/*/
			 
						// Step 6 : Creating JLabel for Gender
								// Setting Cursor for components
								Cursor cur=new Cursor(Cursor.HAND_CURSOR);		
			
								// Creating JRadioButton for the Male		
								male=new JRadioButton("Male");
								male.setBounds(180,160,70,30);
								male.setBackground(Color.yellow);
								male.setCursor(cur);
			
								// Creating JRadioButton for the Female
								female=new JRadioButton("Female");
								female.setBounds(280,160,80,30);
								female.setBackground(Color.yellow);
								female.setCursor(cur);
			
								// Creating ButtonGroup for the JRadioButtons
								ButtonGroup gender_grp=new ButtonGroup();
								gender_grp.add(male);    // adding male radio button in the ButtonGroup
								gender_grp.add(female);    // adding female radio button in the ButtonGroup

						// Step 7 : Creating JLabel for Date of Birth
								JLabel dob_lbl=new JLabel("Date of Birth : ");
								dob_lbl.setBounds(50,200,100,30);	
						
								// Creating JComboBox for the day
								String day_arr[]=new String[31];
								for(int i=1;i<=31;i++)
								day_arr[i-1]=Integer.toString(i);		
								day=new JComboBox(day_arr);
								day.setBounds(180,200,40,30);
			
								// Creating JComboBox for the month
								String month_arr[]={"Jan","Feb","March","April","May","June","July","Aug","Sept","Oct","Nov","Dec" };	
								month=new JComboBox(month_arr);
								month.setBounds(230,200,60,30);
			
								// Creating JComboBox for the year	
								String year_arr[]=new String[70];
								for(int i=1951;i<=2020;i++)
								year_arr[i-1951]=Integer.toString(i);
								year=new JComboBox(year_arr);
								year.setBounds(300,200,60,30);
			
						// Step 8 : Creating JLabel for the Address
								JLabel add_lbl=new JLabel("Address : ");
								add_lbl.setBounds(50,240,100,30);				
			
								// Creating JTextArea for the address
								add_txtArea= new JTextArea();
								add_txtArea.setBounds(180,240,180,100);
			
						// Step 9 :  Creating JLabel for the phone
								JLabel phone_lbl=new JLabel("Phone No. : ");
								phone_lbl.setBounds(50,350,100,30);
			
								// Creating JTextField for the phone
								phone_txt=new JTextField();
								phone_txt.setBounds(180,350,180,30);
			
						// Step 10 : Creating JLabel for the Email
								JLabel email_lbl=new JLabel("Email : ");
								email_lbl.setBounds(50,390,100,30);
								
								// Creating JTextField for the Email
								email_txt=new JTextField();
								email_txt.setBounds(180,390,180,30);					
					
					    // Step 11 : Creating JCheckBox for the license agreement		
								chkbox=new JCheckBox("I accept the terms and conditions");
								chkbox.setBounds(50,430,300,30);
								chkbox.setBackground(Color.yellow);
			
						// Step 12 : Creating JButton for submit the details
								submit_btn=new JButton("Submit");
								submit_btn.setBounds(180,500,120,40);
								submit_btn.setCursor(cur);  // Applying hand cursor on the button
								
								
								
								
								// Step 18 :  Adding ActionListener on submit button
								submit_btn.addActionListener(new ActionListener(){
									public void actionPerformed(ActionEvent event){
										submit_action(event);									
									}			
								});
									
								
						// Step 17 : Creating JTextArea for output
								output_txtArea=new JTextArea();
								output_txtArea.setBounds(380,80,260,320);
			
						// Step 13 : Applying Global Font on all the JLabels	
								name_lbl.setFont(f1);
								fname_lbl.setFont(f1);
								gender_lbl.setFont(f1);
								dob_lbl.setFont(f1);
								add_lbl.setFont(f1);
								phone_lbl.setFont(f1);
								email_lbl.setFont(f1);
					 
						// Step 14 : Applying Font on all JTextFields, JRadioButtons, JComboBox and JTextArea
								name_txt.setFont(f1);
								fname_txt.setFont(f1);
								male.setFont(f1);
								female.setFont(f1);
								add_txtArea.setFont(f1);
								phone_txt.setFont(f1);
								email_txt.setFont(f1);
								chkbox.setFont(f1);
								submit_btn.setFont(f1);
								output_txtArea.setFont(f1);
						 
						// Step 15 : Adding label components to the container 
								c.add(heading_lbl);	
								c.add(name_lbl);			
								c.add(fname_lbl);
								c.add(gender_lbl);
								c.add(male);
								c.add(female);
								c.add(dob_lbl);
								c.add(add_lbl);
								c.add(phone_lbl);
								c.add(email_lbl);
			
						// Step 16 : Adding JTextField, JTextArea, JComboBox, JCheckBox, JRadioButton to the container
								c.add(name_txt);
								c.add(name_txt);
								c.add(fname_txt);
								c.add(day);
								c.add(month);
								c.add(year);
								c.add(add_txtArea);
								c.add(phone_txt);
								c.add(email_txt);
								c.add(chkbox);
								c.add(submit_btn);
								c.add(output_txtArea);	
*/								
								
								
								
								
								/* ---------------------------------- Creating JFrame -------------------------------------------------------- */
								// Step 1 :  Creating a frame using JFrame class	
								setSize(1000,800);  
								centerFrame();
							    setTitle("Progress Monitor Data Manager");
							    setDefaultCloseOperation(javax.swing.
							            WindowConstants.DISPOSE_ON_CLOSE);
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
	
	RegistrationDAO dao=null;
	
	if(e.getSource()==btnSubmit){
		adminBean bean=new adminBean();
		bean.setUserID(txtUserId.getText());
		bean.setPsw(txtPsw.getText());
		bean.setFirstName(txtFirstName.getText());
		bean.setLastName(txtLastName.getText());
		bean.setEmail(txtemail.getText());
//		bean.setPhno(txtPhno.getText());
		
		/*String regexStr = "^(?:(?:\\+?1\\s*(?:[.-]\\s*)?)?(?:\\(\\s*([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9])\\s*\\)|([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9]))\\s*(?:[.-]\\s*)?)?([2-9]1[02-9]|[2-9][02-9]1|[2-9][02-9]{2})\\s*(?:[.-]\\s*)?([0-9]{4})(?:\\s*(?:#|x\\.?|ext\\.?|extension)\\s*(\\d+))?$";
		Pattern pattern = Pattern.compile(regexStr);
	    Matcher matcher = pattern.matcher(txtPhno.getText());*/
	    
	    /*if (!matcher.matches()) {
	    	JOptionPane.showMessageDialog(this,"Invalid Phone number");
	    	txtPhno.setText("");
	    } */
		
		
		
		
	    if(txtUserId.getText().length() <= 0){
	    	JOptionPane.showMessageDialog(this,"Please insert User Id");
	    	
	    	
	    }else if(txtPsw.getText().length() <= 0){
	    	JOptionPane.showMessageDialog(this,"Please insert password");
	    	
	    	
	    }else if(txtFirstName.getText().length() <= 0){
	    	JOptionPane.showMessageDialog(this,"Please insert First name");
	    	
	    	
	    }else if(txtLastName.getText().length() <= 0){
	    	JOptionPane.showMessageDialog(this,"Please insert Last name");
	    	
	    	
	    }else if(txtemail.getText().length() <= 0){
	    	JOptionPane.showMessageDialog(this,"Please insert Email Id");
	    	
	    	
	    }else{
	    	
	    	final Pattern VALID_EMAIL_ADDRESS_REGEX = 
				    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
			
			Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(txtemail.getText());
			
			if (!matcher.matches()) {
				JOptionPane.showMessageDialog(this,"Invalid Email Id");
			}else{
			  	dao = new RegistrationOpr();
				boolean flag= dao.checkUserAlreadyExist(txtUserId.getText());
				if(flag){
					JOptionPane.showMessageDialog(this,"UserId already exist.");
				}else{
					
					flag=false;
					flag =dao.insertUser(bean);
					
					if(flag){
						String success="Congratulations. Please login with username and password which you have submit.";
						JOptionPane.showMessageDialog(this,success);
						setVisible(false);
						new LoginGUI();
					}else{
						
						JOptionPane.showMessageDialog(this,"User created fail");
						txtUserId.setText("");
						txtPsw.setText("");
						txtFirstName.setText("");
						txtLastName.setText("");
						txtemail.setText("");
						
						
					}
			    	
					
				}
			}
			
	  

	    }
		
		
	} // close the  if(e.getSource()==btnSubmit)
	
	if(e.getSource()==btnBack){
		setVisible(false);
		new legalGUI();
	}
	
}
	public static void main(String args[])
	{ 
		new RegistratonGUI();
	
	}
	
	

}
