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
		
		JPanel jsp1 = new JPanel(new BorderLayout());
	 	jsp1.setBackground(new Color(141, 31, 6));
	 	
	 	
	    ImageIcon image = new ImageIcon(this.getClass().getResource("/image/Image12_1.jpg"));
	    JLabel label = new JLabel("", image, JLabel.CENTER);
	    jsp1.add( label, BorderLayout.CENTER );
	    
	   
	 	
	    	    
	    
	    
        JPanel jsp2 = new JPanel();	
        jsp2.setBackground(Color.DARK_GRAY);
        JTextPane textPane = new JTextPane();
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
					
        
        jsp2.add(textPane);
        
        
        GridBagConstraints c = new GridBagConstraints();
        
        
        JPanel jsp3 = new JPanel(new GridBagLayout());
        jsp3.setBackground(new Color(135,206,250));  
        //jsp3.setBorder(BorderFactory.createLineBorder(Color.black));
        
        image = new ImageIcon(this.getClass().getResource("/image/login.jpg"));
        JLabel lblLogin=new JLabel("", image, JLabel.RIGHT);
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(1,1,1,1);
        lblLogin.setSize(150,150);
        jsp3.add(lblLogin, c);
        
        JPanel finalPanel = new JPanel();
        finalPanel.setBackground(new Color(135,206,250)); 
        
        
        JPanel jsp4 = new JPanel(new GridBagLayout());	
        jsp4.setBackground(new Color(135,206,250));  
      //  jsp4.setBorder(BorderFactory.createLineBorder(Color.black));
        Font f1=new Font("Serif",Font.BOLD,20);
		Font f2=new Font("Serif",Font.BOLD,20);
        
        lblUserId = new JLabel("Username");
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(1,1,1,1);
        lblUserId.setFont(f1);
        jsp4.add(lblUserId, c);

        txtUserId = new JTextField("",15);
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(20,20,20,20);
        jsp4.add(txtUserId,c);
        txtUserId.requestFocusInWindow();

        lblPsw = new JLabel("Password");
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(0,0,0,0);
        lblPsw.setFont(f1);
        jsp4.add(lblPsw,c);

        txtPsw = new JPasswordField("",15);
        c.gridx = 1;
        c.gridy = 2;
        c.insets = new Insets(5,5,5,5);
        jsp4.add(txtPsw,c);
        
        /*txtPsw.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    // something like...
                   //mTextField.getText();
                   // or...
                   //mButton.doClick();
                	System.out.println("hiii");
                }
            }

        });*/
        
        
        btnSubmit = new JButton("       Login        ");
        c.gridx = 1;
        c.gridy = 5;
        c.insets = new Insets(35,35,35,35);
        /*Image img;
		try {
			img = ImageIO.read(this.getClass().getResource("/image/legalNotice.png"));
			btnSubmit.setIcon(new ImageIcon(img));
			btnSubmit.setBorder(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        
        btnSubmit.setBackground(Color.white);
		btnSubmit.setOpaque(true);
		btnSubmit.setBorderPainted(false);
		btnSubmit.setFont(new Font("Britannic Bold", Font.PLAIN, 20));
        jsp4.add(btnSubmit,c);
        btnSubmit.addActionListener(this);
        getRootPane().setDefaultButton(btnSubmit);
        
        
        
        
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 5;
        c.gridheight = 5;
        c.insets = new Insets(5,5,5,5);
        finalPanel.add(jsp4,c);
        
        
        
        
        JSplitPane splitPaneLoginSub = new JSplitPane(JSplitPane.VERTICAL_SPLIT, 
                false, jsp3, finalPanel);
        splitPaneLoginSub.setDividerLocation(100 + splitPaneLoginSub.getInsets().top);
        splitPaneLoginSub.setDividerSize(0);
        
        
        
		JSplitPane splitPaneLogin = new JSplitPane(JSplitPane.VERTICAL_SPLIT, 
                false, jsp2, splitPaneLoginSub);
		splitPaneLogin.setDividerLocation(200 + splitPaneLogin.getInsets().top);
		splitPaneLogin.setDividerSize(0);
		
		
		
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, 
                false, jsp1, splitPaneLogin);
        splitPane.setDividerLocation(200 + splitPane.getInsets().top);
        splitPane.setDividerSize(0);
        splitPane.setOneTouchExpandable(false);	        
        getContentPane().add(splitPane);
        
        
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
	
	
	/*public static void main(String args[]){  
		new LoginGUI();  
		
	}*/

}
