package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.Visibility;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import manegement.LoginOpr;
import abstrac.LoginDAO;

public class LoginGUI extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel lblUserId,lblPsw;
	JTextField txtUserId;
	JPasswordField txtPsw;
	JButton btnSubmit;
	
	
	public LoginGUI(){
		
		setLayout(new BorderLayout());
		setContentPane(new JLabel(new ImageIcon(this.getClass().getResource("/image/login background.png"))));
		setLayout(null);
		
		
	
		
		/*JTextPane textPane = new JTextPane();
		textPane.setBounds(210,15,100,30);
        textPane.setBackground(Color.DARK_GRAY);
        JScrollPane scrollPane = new JScrollPane(textPane);
        textPane.setEditable(false);
        textPane.setMargin(new Insets(scrollPane.getViewport().getHeight()
                , 1, 1, 1));
        textPane.setContentType("text/html");
        textPane.setText("<html><body style=\"font: Arial; color: white; padding: 2px; padding-top: 2;\">"
        		+ "<center><h3>Take Flight Progress Monitoring Charts instrument was designed to provide diagnostic information about how </h3> </center></br>"
        		+ "<center><h3>student are progressing in learning the decoding concepts of Take Flight: A Comprehensive intervention for Student </h3> </center></br>"
        		+ "<center><h3>with Dyslexia. This is done by allowing repeated administrations of a set of decodable, real words that cover the </h3></center> </br>"
        		+ "<center><h3>range of basic phonics concepts covered in the first five books of the Take Flight curriculum</h3> </center></br>"
        		+ "</body></html>");
        
        add(textPane);*/
		Font f=FontClass.MuseoSans300(18); 
		
        JLabel heading_lbl=new JLabel();
		heading_lbl.setBounds(30,180,900,230);
		heading_lbl.setFont(f);
		heading_lbl.setText("<html><body style=\"font: Arial; color: white; padding: 2px; padding-top: 2; padding-left: 12;padding-right: 12; background-color: #1D3E8A; border-top: 10px solid rgb(139,39,35); \">"
        		+ "<center><h3>Take Flight Progress Monitoring Charts instrument was designed to provide diagnostic information about how </h3> </center></br>"
        		+ "<center><h3>student are progressing in learning the decoding concepts of Take Flight: A Comprehensive intervention for Student </h3> </center></br>"
        		+ "<center><h3>with Dyslexia. This is done by allowing repeated administrations of a set of decodable, real words that cover the </h3></center> </br>"
        		+ "<center><h3>range of basic phonics concepts covered in the first five books of the Take Flight curriculum</h3> </center></br>"
        		+ "</body></html>");	
		add(heading_lbl);
		
		
		
		lblUserId = new JLabel("Username");
		lblUserId.setBounds(280,450,100,30);
		lblUserId.setFont(f);
		add(lblUserId);
		
		txtUserId = new JTextField("",15);
		txtUserId.setBounds(400,450,200,30);
		txtUserId.setFont(f);
        txtUserId.requestFocusInWindow();
        add(txtUserId);
		
		
        lblPsw = new JLabel("Password");
        lblPsw.setBounds(280,500,100,30);
        lblPsw.setFont(f);
		add(lblPsw);
        
		txtPsw = new JPasswordField("",15);
		txtPsw.setBounds(400,500,200,30);
		txtPsw.setFont(f);
		txtPsw.requestFocusInWindow();
        add(txtPsw);
        
        
        ImageIcon image = new ImageIcon(this.getClass().getResource("/image/login button.png"));
        btnSubmit = new JButton(image);
        btnSubmit.setBounds(400,570,150,40);
        btnSubmit.setBorder(null);
        //btnSubmit.setFont(f);
        //btnSubmit.setBackground(new Color(188,221,238));
        btnSubmit.addActionListener(this);
       // btnSubmit.setBorder(new RoundedBorder(10));
        btnSubmit.requestFocusInWindow();
        getRootPane().setDefaultButton(btnSubmit);
        add(btnSubmit);
        
        
        
		
    	setSize(900,800);  
		centerFrame();
	    setTitle("Progress Monitor Data Manager");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setResizable(false);
		setVisible(true); 
		
		
	}
	public LoginGUI(String success) {
		// TODO Auto-generated constructor stub
		if(success.length() > 0){
			JOptionPane.showMessageDialog(this,success);
		}
		new LoginGUI();
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
		LoginDAO dao = null;
		if(e.getSource()==btnSubmit){
			dao=new LoginOpr();
			boolean flag = false;
			try{
				flag=dao.getAuthentication(txtUserId.getText().trim(),txtPsw.getText());
				if(flag){
					synchronized (this) {
						new welcomeGUI();
						this.setVisible(false); 
					}
				}else{
					
					JOptionPane.showMessageDialog(this,"Invalid user id and password");
				}
			}catch(Exception ee){
				synchronized (this) {
		  		      System.out.println("Call to GUI for ask DB User name and Password");      
		  		      new CheckUsernamePassGUI("DB User name and Password is wrong");
		  		    setVisible(false);
				}
			}
			
		}
		
		
		
		/* if (e.getKeyCode()==KeyEvent.VK_ENTER){
		        System.out.println("Hello");

		        JOptionPane.showMessageDialog(null , "You've Submitted the name " + nameInput.getText());
		    }
*/
		
		
	}
	
	
	public static void main(String args[]){  
		new LoginGUI();  
		
	}

}
