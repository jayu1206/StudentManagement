package gui;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Visibility;
import java.io.IOException;
import java.sql.SQLException;

import javafx.scene.control.Alert;
import javafx.scene.text.TextAlignment;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import process.ProcessExe;
import manegement.LegalGuiOpr;
import abstrac.LegalGUIDAO;

import com.sun.javafx.font.Metrics;


public class legalGUI  extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	JTextArea legalContent;
	JCheckBox checkTerm;
	final JLabel label = new JLabel(); 
	JRadioButton accept = new JRadioButton("I Accept");
    JRadioButton notaccept = new JRadioButton("I Do Not Accept");
    ButtonGroup bG = new ButtonGroup();
    JButton btnAccept;
	
	
	public legalGUI(){
		
		
		
		 	JPanel jsp1 = new JPanel(new BorderLayout());
		 	jsp1.setBackground(new Color(141, 31, 6));
		 	
		    ImageIcon image = new ImageIcon(this.getClass().getResource("/image/Image12_1.jpg"));
		    JLabel label = new JLabel("", image, JLabel.CENTER);
		    jsp1.add( label, BorderLayout.CENTER );
		    
		 	
	        JPanel jsp2 = new JPanel(new GridLayout(2,1));	       
	        jsp2.setSize(jsp2.getPreferredSize());
	       
	        
	        JTextPane textPane = new JTextPane();
	        textPane.setBackground(Color.DARK_GRAY);
	        JScrollPane scrollPane = new JScrollPane(textPane);
	        textPane.setEditable(false);
	        textPane.setMargin(new Insets(scrollPane.getViewport().getHeight()
	                , 0, 0, 0));
	        textPane.setContentType("text/html");
	        textPane.setText("<html><body style=\"font: Arial; color: white; padding: 1px; padding-top: 0;\">"
	        		+ "<center> <h1>Legal Disclaimer</h1> </center></br>"
	        		+ "<center><h4>Texas Scottish Rite Hospital for Children and the authors of this software accept no responsibility for damages</h4> </center></br>"
	        		+ "<center><h4>Tresulting from the use of this product and make no warranty or representation, either expressed or implied, including</h4> </center></br>"
	        		+ "<center><h4>but not limited to, any implied warranty of merchantability or fitness for a particular purpose. This software is provided</h4></center> </br>"
	        		+ "<center><h4>“AS IS,”, and you, its user, assumes all risk when using it. Texas Scottish Rite Hospital for Children is not responsible</h4> </center></br>"
	        		+ "<center><h4>for the security of accuracy of student information and test result data (collectively “Data” ). You, the user, are</h4> </center></br>"
	        		+ "<center><h4>responsible for the security and accuracy of this Data as required by law.</h4> </center></br>"
	        		+ "</body></html>");
						
	        
	        jsp2.add(textPane);
	        
	        GridBagConstraints c = new GridBagConstraints();
	        JPanel jsp3 = new JPanel(new GridBagLayout());	
	        
	        bG.add(accept);
	        bG.add(notaccept);
	        accept.setSelected(true);
	        //notaccept.setSelected(true);
	       
	        c.gridx = 1;
	        c.gridy = 1;
	        c.insets = new Insets(1,1,1,1);
	        jsp3.add(accept);
	        
	        c.gridx = 0;
	        c.gridy = 1;
	        c.insets = new Insets(1,1,1,1);
	        jsp3.add(notaccept,c);
	        
	        
	        JPanel jsp4 = new JPanel();	
	        btnAccept=new JButton();  
	        Image img;
			try {
				img = ImageIO.read(getClass().getResource("/image/submit_icon.png"));
				btnAccept.setIcon(new ImageIcon(img));
				btnAccept.setBorder(null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        jsp4.add(btnAccept); 
	        btnAccept.addActionListener(this); 
	       
	        
	        JSplitPane splitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, 
	                false, jsp3, jsp4);
	        splitPane2.setDividerLocation(100 + splitPane2.getInsets().top);
	        splitPane2.setDividerSize(0);
	        
	        
	        
	        
	        jsp2.add(splitPane2,c);
			
			
	        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, 
	                false, jsp1, jsp2);
	        splitPane.setDividerLocation(200 + splitPane.getInsets().top);
	        splitPane.setDividerSize(0);
	        splitPane.setOneTouchExpandable(false);	        
	        getContentPane().add(splitPane);
	        
	        
	    	setSize(1100,800);  
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
	public void actionPerformed(ActionEvent e){
		// TODO Auto-generated method stub
		LegalGUIDAO dao = new LegalGuiOpr();
		if(accept.isSelected()){
			if(e.getSource()==btnAccept){
//				JOptionPane.showMessageDialog(this,"Login GUI Calling");
				boolean flag =false;
				try{
					flag = dao.checkUser();
					
					if(flag){
//						JOptionPane.showMessageDialog(this,"true");
						setVisible(false);
							new LoginGUI();
					}else{
						setVisible(false);
						new RegistratonGUI();
					}
				}catch(Exception ee){
					setVisible(false);
		    		      System.out.println("Call to GUI for ask DB User name and Password");      
		    		      new CheckUsernamePassGUI("DB User name and Password is wrong");
		    		
				}
				
				
				
				
			}
			
			
		}else{
			//JOptionPane.showMessageDialog(this,"Please accept legal notice");
			if(notaccept.isSelected()){
				if(e.getSource()==btnAccept){
					System.exit(0);
					
				}
				
			}
			
		}
		
		
		
		
		
	}

	public static void main(String args[]){  
		//new legalGUI();
		//new GroupGUI();
		ProcessExe objExe=new ProcessExe();
		objExe.checkMySqlSystem(0);
		
	}

	
	
	
}
