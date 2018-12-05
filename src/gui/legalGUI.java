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
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Visibility;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.sun.xml.internal.ws.org.objectweb.asm.Label;

import process.ProcessExe;
import manegement.LegalGuiOpr;
import abstrac.LegalGUIDAO;



public class legalGUI  extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	JButton btnAccept,btnNotAccept;
    JMenu menu;
    JRadioButton accept = new JRadioButton("     I Accept     ");
    JRadioButton notaccept = new JRadioButton("     I Do Not Accept     ");
    ButtonGroup bG = new ButtonGroup();
    
    
	public legalGUI(){
		
		setLayout(new BorderLayout());
		setContentPane(new JLabel(new ImageIcon(this.getClass().getResource("/image/background 1.png"))));
		setLayout(null);
		
		Font f=FontClass.MuseoSans500Italic(20); //new Font("Museo Sans", Font.ITALIC | Font.BOLD, 20);   // Creating font style and size for heading
		Font f2=FontClass.MuseoSans500(18);
		f2.deriveFont(Font.PLAIN);
		
		
		JLabel heading=new JLabel();//0, 170,1080,480
		heading.setBounds(25, 220,3080,60);
		heading.setFont(f);
		heading.setText("<html><body style=\"font: Arial; color: white; padding: 2px; padding-top: 2; padding-left: 356;padding-right: 356; background-color: rgb(193,39,35); \">"
        		+ "<center><h3>PROGRESS MONITOR DATA MANAGER SYSTEM </h3> </center></br>"
        		+ "<center><h5>GENERAL TERMS AND CONDITIONS </h5> </center></br>"
        		+ "</body></html>");	
		add(heading);
		
		
		// step 3 : creating JLabel for Heading
				JPanel textPanel=new JPanel();
				//textPanel.setBounds(0,0,1000,500);
						JLabel heading_lbl=new JLabel();
						//heading_lbl.setBackground(Color.DARK_GRAY);
						//heading_lbl.setBounds(5,0,1070,500);  bgcolor=#545454
						heading_lbl.setText("<html>"
								+ "<head> <title>Page Title</title> </head> <body bgcolor=rgb(242,242,242) style=\"padding: 7px;  \" > <font color=black>"
								
								//+ "<center><h3>GENERAL TERMS AND CONDITIONS</h3></center></br>"
								+ "<center><h3>IMPORTANT - THIS IS A CONTRACT</h3></center>"
								+ "<center><h3>CAREFULLY READ BEFORE USING THIS PRODUCT</h3></center>"
								+ "<p>BY DOWNLOADING THE STUDENT PROGRESS MONITOR APPLICATION, YOU ACKNOWLEDGE THAT YOU HAVE <br/>\n READ THIS AGREEMENT, THAT YOU UNDERSTAND IT,AND THAT YOU AGREE"
								+ "TO BE BOUND BY ITS TERMS AND <br/>\n CONDITIONS. IF YOU DO NOT AGREE TO THE TERMS AND CONDITIONS OF THIS AGREEMENT, PROMPTLY EXIT <br/>\n THE APPLICATION WITHOUT USING IT.</p>"

								+ "<br/>\n<center><h2>1. License Grant and Restrictions</h2></center>"
								
								+ "<p><u>“Texas Scottish Rite Hospital for Children</u> (“Owner”) grants you a non-exclusive, non-transferable, limited license to access <br/>\n and use the <u>“Student Progress Monitor” Application</u> (“Student Progress Monitor”),"
								+ "including the right to: (a) electronically <br/>\n  display and use the Student Progress Monitor;and (b) unless otherwise prohibited, store up to two copies of the Student <br/>\n  Progress Monitor in machine-readable form for one "
								+ "person’s exclusive use to support normal work activities. Any right not <br/>\n  expressly granted to you by this Agreement is hereby expressly reserved by Owner.</p>"
								
								+ "<br/>\n<p>You expressly agree: (1) not to alter, modify, remove or obscure any copyright notice or other notice contained in the Student <br/>\n Progress Monitor; (2) not to allow your user identification and password to be used by"
								+ "any other person to access the Student <br/>\n Progress Monitor; (3) not to use the Student Progress Monitor for the benefit of any other person or entity that is not an <br/>\n authorized user or licensee of the Student Progress "
								+ "Monitor;(4) not to use the Student Progress Monitor in any way that <br/>\n infringes the copyrights, confidentiality or proprietary rights of Owner, Owner’s third party suppliers, or students whose <br/>\n information is stored in"
								+ "the Student Progress Monitor; (5) not to directly or indirectly use or allow others to use the Student <br/>\n Progress Monitor or any information about them in the development of any product that is competitive with the"
								+ " Student <br/>\n Progress  Monitor; (6) to be bound by the terms of this Agreement; and (7) that Owner is the owner of all copyrights and <br/>\n intellectual property of Owner and that those rights are valid and enforceable by Owner.</p>"
								
								+ "<br/>\n<p>YOU FURTHER ACKNOWLEDGE AND AGREE THAT OWNER IS NOT RESPONSIBLE FOR THE SECURITY OR <br/>\n ACCURACY OF STUDENT INFORMATION AND TEST RESULT DATA (COLLECTIVELY,"
								+ "“DATA”). YOU, THEREFORE,<br/>\n ACKNOWLEDGE AND AGREE THAT YOU ARE SOLELY RESPONSIBLE FOR THE SECURITY, ACCURACY, AND <br/>\n PRIVACY OF DATA AS REQUIRED BY LAW, "
								+ "INCLUDING BUT NOT LIMITED TO YOUR STRICT COMPLIANCE WITH <br/>\n THE FAMILY EDUCATIONAL RIGHTS AND PRIVACY ACT (“FERPA”)..  </p>"
								
								+ "<br/>\n<center><h2>2. Ownership</h2></center>"
								
								+ "<p>You further agree and acknowledge that you have no ownership rights in the Student Progress Monitor or copies thereof.<br/>\n Rather, you have a limited license to use the Student Progress Monitor in accordance with this "
								+ "Agreement for so long as this <br/>\n Agreement remains in full force and effect. All right, title and interest (including copyrights and other intellectual property <br/>\n rights) in the Student Progress Monitor (in both print "
								+ "and machine-readable form) shall remain at all times with Owner or <br/>\n its third party suppliers. Any other use of the Student Progress Monitor by any person, business, corporation, government <br/>\n organization or any "
								+ "other entity is strictly forbidden and is a violation of this Agreement. While the creation, revision,<br/>\n duplication, or making of derivatives is strictly forbidden, in the case that you make any such new copyrightable"
								+ "matter, you <br/>\n hereby expressly and irrevocably assign those copyrights to Owner without compensation.</p>"
								
								+ "<br/>\n<center><h2>3. Access</h2></center>"
								
								+ "<p>Only authorized individuals may access and use the Student Progress Monitor. You may be required to change your password <br/>\n from time to time. You may not have access to the Student Progress Monitor at all times."
								+ "The Student Progress Monitor may <br/>\n be changed, added or withdrawn without notice by Owner at any time and at Owner’s sole discretion. </p>"
								
								+ "<br/>\n<center><h2>4. Limited Warranty</h2></center>"
								
								+ "<p>THE STUDENT PROGRESS MONITOR IS PROVIDED ON AN “AS IS”, “AS AVAILABLE” BASIS AND OWNER AND <br/>\n EACH THIRD PARTY SUPPLIER EXPRESSLY DISCLAIM ALL WARRANTIES OF ANY KIND,"
								+ "EITHER EXPRESSED OR <br/>\n IMPLIED, INCLUDING, WITHOUT LIMITATION, IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS <br/>\n FOR A PARTICULAR PURPOSE. OWNER DOES NOT WARRANT THAT "
								+ "STUDENT PROGRESS MONITOR WILL MEET <br/>\n ANY REQUIREMENTS OR NEEDS YOU MAY HAVE, OR THAT THE STUDENT PROGRESS MONITOR WILL THE <br/>\n OPERATE ERROR FREE, OR IN AN UNINTERRUPTED"
								+ "FASHION, OR THAT ANY DEFECTS OR ERRORS IN THE <br/>\n STUDENT PROGRESS MONITOR WILL BE CORRECTED, OR THAT THE STUDENT PROGRESS MONITOR ARE <br/>\n COMPATIBLE WITH ANY PARTICULAR"
								+ "PLATFORM. SOME JURISDICTIONS DO NOT ALLOW THE WAIVER OR <br/>\n EXCLUSION OF IMPLIED WARRANTIES SO THEY MAY NOT APPLY TO YOU. </p>"
								
								+ "<br/>\n<center><h2>5. Limitation of Liability</h2></center>"
								
								+ "<p>A “Covered Party” means: (a) Owner, its affiliates, and their officers, directors, trustees, employees, subcontractors, agents,<br/>\n successor or assigns; and (b) each third party supplier of Student Progress Monitor, its "
								+ "affiliates, and their officers, directors,<br/>\n employees, subcontractors, agents, successor or assigns. </p>"
								
								+ "<br/>\n<p>A Covered Party shall not be liable for any loss,injury, claim, liability or damage of any kind resulting in any way from:(i) any <br/>\nerrors in or omissions from the Student Progress Monitor ; (ii) the unavailability "
								+ "or interruption of the Student Progress <br/>\n Monitor or any features thereof; (iii) your use of the Student Progress Monitor (regardless of whether you received any <br/>\n assistance from a Covered Party in using the Student "
								+ " Progress Monitor); (iv) your use of any equipment in connection with <br/>\n the Student Progress Monitor; (v) the content of the Student Progress Monitor; or (vi) any delay or failure in performance <br/>\nbeyond the reasonable "
								+ "control of a Covered Party.</p>"
								
								+ "<br/>\n<p>IN NO EVENT WILL A COVERED PARTY BE LIABLE TO YOU OR ANY THIRD PARTY FOR ANY DIRECT, INDIRECT,<br/>\n INCIDENTAL, SPECIAL, OR CONSEQUENTIAL DAMAGES OF ANY KIND WHATSOEVER"
								+ "(INCLUDING, WITHOUT<br/>\n LIMITATION, ATTORNEYS FEES, INDIRECT, SPECIAL, PUNITIVE, OR EXEMPLARY DAMAGES FOR LOSS OF<br/>\n BUSINESS, LOSS OF PROFITS, BUSINESS INTERRUPTION, OR "
								+ "LOSS OF BUSINESS INFORMATION) IN ANY WAY<br/>\n DUE TO, RESULTING FROM, OR ARISING IN CONNECTION WITH THE STUDENT PROGRESS MONITOR OR THE <br/>\nFAILURE OF ANY COVERED PARTY TO"
								+ "PERFORM ITS OBLIGATIONS, REGARDLESS OF ANY NEGLIGENCE OF <br/>\nANY COVERED PARTY, EVEN IF THE COVERED PARTY HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH <br/>\nDAMAGES.</p>"
								
								+ "<br/>\n<p>A COVERED PARTY’S AGGREGATE LIABILITY WITH RESPECT TO ITS OBLIGATIONS UNDER THIS AGREEMENT,<br/>\n THE STUDENT PROGRESS MONITOR, OR OTHERWISE SHALL NOT EXCEED THE AMOUNT"
								+ "OF THE LICENSE FEE<br/>\n PAID BY YOU. </p>"
								
								
								+ "<br/>\n<center><h2>6. General</h2></center>"
								
								+ "<br/>\n<p>This Agreement is effective until it is terminated. Owner may suspend or discontinue providing the Student Progress Monitor<br/>\n to you without notice and pursue any other remedy legally available to it if you fail to comply"
								+ "with any of your obligations <br/>\nhereunder. All provisions relating to proprietary rights and non-disclosure shall survive the termination of this Agreement. </p>"
								
								
								+ "<br/>\n<p>This Agreement may be changed from time to time by written agreement or by notice from Owner. All notices and other <br/>\n communications hereunder shall be in writing or displayed electronically in the Student Progress Monitor."
								+ " Notices shall be<br/>\n deemed to have been properly given on the date first made available, if displayed in the Student Progress Monitor, or on<br/>\n the date received, if delivered in any other manner. Your access to the Student"
								+ "Progress Monitor may be terminated <br/>\n immediately upon notice to Owner if any change is unacceptable. Continued use of the Student Progress Monitor following <br/>\n any change constitutes acceptance of the change. You may"
								+ "not assign your rights or delegate your duties under this Agreement <br/>\n without the prior written consent of Owner.</p>"
								
								+ "<br/>\n<p>The failure of Owner or any third party supplier of the Student Progress Monitor to enforce any provision hereof shall not <br/>\n constitute or be construed as a waiver of such provision or of the right to enforce it at a"
								+ "later time. Each third party supplier<br/>\n of the Student Progress Monitor has the right to assert and enforce these provisions directly on its own behalf as a third party <br/>\n beneficiary.</p>"
								
								+ "<br/>\n<p>This Agreement shall constitute the entire Agreement between the parties hereto, and shall be construed, interpreted and  <br/>\n governed by the laws of the State of Texas without regard to conflicts of law provisions thereof."
								+ " The exclusive forum for any <br/>\n disputes arising out of or relating to this Agreement shall be an appropriate federal or state court sitting in Dallas County,<br/>\n Texas, USA. Except as otherwise provided herein, any waiver or"
								+ "modification of this Agreement shall only be effective if it is <br/>\n in writing and signed by both parties hereto. If any part of this Agreement is found invalid or unenforceable "
								+ "by a court of <br/>\n competent jurisdiction, the "
								+ "remainder of this Agreement shall be interpreted so as to reasonably effect the intention of the <br/>\n parties.</p>"
								
								+ "</body></html>");

						// applying font on  heading Label
						heading_lbl.setFont(f2);   // f2 for normal text and f for italic text 
						textPanel.add(heading_lbl);
						
						
						JScrollPane scroller = new JScrollPane(textPanel, 
				                 JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
						 scroller.setBounds(25, 280,1040,370);
				  
						add(scroller); 
						
						
						JPanel footerPanel=new JPanel();
						footerPanel.setBounds(25,650,1040,60);
						footerPanel.setBackground(new Color(204,204,204));
						
						ImageIcon image = new ImageIcon(this.getClass().getResource("/image/I_accept_button.png"));
						 btnAccept = new JButton(image);
					     btnAccept.setBounds(920,660,150,40);
					     btnAccept.setSize(150, 100);
						// btnAccept.setFont(new Font("Museo Sans", Font.BOLD, 15));
					     btnAccept.setBackground(new Color(204,204,204));
					     btnAccept.setBorderPainted(false);
					     btnAccept.setFocusable(false);
					     btnAccept.addActionListener(this);
					     footerPanel.add(btnAccept);
					     
					     
					     image = new ImageIcon(this.getClass().getResource("/image/do_not_accept_button.png"));
					     btnNotAccept = new JButton(image);
					     btnNotAccept.setSize(150, 100);
					     btnNotAccept.setBounds(920,660,150,40);
					     //btnNotAccept.setFont(new Font("Museo Sans", Font.BOLD, 15));
					     btnNotAccept.setBackground(new Color(204,204,204));
					     btnNotAccept.setBorderPainted(false);
					     btnNotAccept.setFocusable(false);
					     btnNotAccept.addActionListener(this);
					     footerPanel.add(btnNotAccept);
					     
					     
					     add(footerPanel);
				       // getContentPane().add(btnAccept);
		
	    	setSize(1100,800);  
			centerFrame();
		    setTitle("Progress Monitor Data Manager");
		    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	public void actionPerformed(ActionEvent e){
		// TODO Auto-generated method stub
		if(e.getSource()==btnAccept){
			LegalGuiOpr dao = new LegalGuiOpr();
			
//				JOptionPane.showMessageDialog(this,"Login GUI Calling");
				boolean flag =false;
				try{
					flag = dao.checkUser();
					
					if(flag){
//						JOptionPane.showMessageDialog(this,"true");
						synchronized (this) {
							new LoginGUI();
							this.setVisible(false);
						}
					}else{
						synchronized (this) {
							new RegistratonGUI();
							this.setVisible(false);
						}
					}
				}catch(Exception ee){
					//setVisible(false);
		    		   //   System.out.println("Call to GUI for ask DB User name and Password");      
		    		    //  new CheckUsernamePassGUI("");
		    		
				}	
				
		}
		
		
		if(e.getSource()==btnNotAccept){
			System.exit(0);
			
		}
		
	}

	public static void main(String args[]){  
//		new legalGUI();
//		new GroupGUI();
	ProcessExe objExe=new ProcessExe();
	objExe.checkMySqlSystem(0);
	
	}

	
	
	
}
