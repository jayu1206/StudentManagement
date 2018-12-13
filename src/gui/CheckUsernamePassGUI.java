package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.OverlayLayout;

import abstrac.LegalGUIDAO;
import abstrac.LoginDAO;
import abstrac.ProcessDAO;
import manegement.LegalGuiOpr;
import manegement.LoginOpr;
import manegement.ProcessOpr;

public class CheckUsernamePassGUI extends JFrame implements ActionListener{
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel lblUsername,lblPsw;
	JTextField txtUserId;
	JButton btnSubmit;
	JPasswordField txtPsw;
	
	
	public CheckUsernamePassGUI(String msg) {
		// TODO Auto-generated constructor stub
		
		if(msg.length()>0){				
			//JOptionPane.showMessageDialog(this,!Wrong Please Try Again);	
			this.setVisible(false);
		}
		
		/*if(msg.length()>0 && msg.equals("!Wrong Please Try Again")){
			
		}*/
		
		setLayout(new BorderLayout());
		//setContentPane(new JLabel(new ImageIcon(this.getClass().getResource("/image/black-back-ground.jpg"))));
		setLayout(null);

		
		setPreferredSize(new Dimension(1000, 800));
		setLocationRelativeTo(null);
		
		
		Font f=new Font("Serif", Font.ITALIC | Font.BOLD, 35);   // Creating font style and size for heading

		// step 3 : creating JLabel for Heading
				JLabel heading_lbl=new JLabel("Provide Database Details");
				heading_lbl.setBounds(300,100,1000,50);
				heading_lbl.setForeground(Color.white);
				heading_lbl.setFont(f);
				add(heading_lbl);
				
				Font f1=new Font("Serif",Font.BOLD,30);
				Font f2=new Font("Serif",Font.BOLD,20);
				
				 	lblUsername = new JLabel("DB User Name : ");
				    lblUsername.setBounds(250,250,250,40); 
				    lblUsername.setForeground(Color.white);
				    lblUsername.setFont(f1);
					add(lblUsername);
					
					txtUserId=new JTextField();
					txtUserId.setBounds(500,255,180,30);
					txtUserId.setFont(f2);
					add(txtUserId);
					
					
					lblPsw=new JLabel("DB Password : ");
					lblPsw.setBounds(250,320,250,40);
					lblPsw.setForeground(Color.white);
					lblPsw.setFont(f1);
					add(lblPsw);

					// Creating JTextField for Father's name
					txtPsw=new JPasswordField();
					txtPsw.setBounds(500,325,180,30);
					txtPsw.setFont(f2);
					add(txtPsw);
					
					
					btnSubmit =  new JButton("Submit");
					btnSubmit.setBounds(350,420,200,30);
					btnSubmit.setBackground(Color.WHITE);
					btnSubmit.setOpaque(true);
					btnSubmit.setBorderPainted(false);
					btnSubmit.setFont(new Font("Britannic Bold", Font.PLAIN, 30));
					add(btnSubmit);
					btnSubmit.addActionListener(this);
					
					
					
					setSize(1000,800);  
					centerFrame();
				    setTitle("Progress Monitor Data Manager");
				    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		ProcessDAO proDAO = null;
		String USER = "";
	    String PASS ="";
		if(e.getSource()==btnSubmit){
			this.setVisible(false);
			proDAO = new ProcessOpr();
			FileInputStream in;
			try {
				
				USER = txtUserId.getText();
				PASS = txtPsw.getText();
				//setVisible(false); 
				boolean status= proDAO.addProperty(USER,PASS);
				System.out.println("hiiiiiiii status : "+status);
				if(status){
					
					InputStream in2 = this.getClass().getClassLoader().getResourceAsStream("./First.properties");
					Properties props = new Properties();
					props.load(in2);
					String dbcount = props.getProperty("DbCount");
					System.out.println("db count "+dbcount);
					if(Integer.parseInt(dbcount)==0){
						status = false;
						status = proDAO.runSQLFile();
					}
					
					System.out.println("After run the script file ....."+status);
					if(status){
						LegalGUIDAO dao2 = new LegalGuiOpr();
						boolean flag = false;
						flag = dao2.checkUser();
						
						if(flag){
//							JOptionPane.showMessageDialog(this,"true");
							setVisible(false);
								new LoginGUI();
						}else{
							setVisible(false);
							new RegistratonGUI();
						}
					}else{
						JOptionPane.showMessageDialog(this,"!Wrong Please Try Again");
						setVisible(false);
						new CheckUsernamePassGUI("");
					}
				}
				
			
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				
				//ex.printStackTrace();
			}
			
		}
		
	}
	
	
	public static void main(String args[]){
		
		new CheckUsernamePassGUI(" ");
	}
	
	


}
