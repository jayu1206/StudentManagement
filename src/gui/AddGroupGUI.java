package gui;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
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
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;

import bean.GroupBean;
import abstrac.GroupDAO;
import manegement.DatePicker;
import manegement.GroupOpr;

public class AddGroupGUI extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	JLabel lblClass,lblStartDate;
	JTextField txtClass,txtStartDate;
	JButton btnSubmit,btnDT;
	JPasswordField txtPsw;
	JButton  btnMgroup,btnMstudents,btnMreport,btnMImportExport,btnMLogout;
	JMenuItem group,students,report;
	JMenu menu;
	
	AddGroupGUI(){
		
		setLayout(new BorderLayout());
		setContentPane(new JLabel(new ImageIcon(this.getClass().getResource("/image/black-back-ground.jpg"))));
		setLayout(null);
		
		JMenuBar mb=new JMenuBar();
		mb.setBackground(new Color(135,206,250));
		mb.add(Box.createRigidArea(new Dimension(10,40)));

        btnMgroup = new JButton("Groups");
		btnMgroup.addActionListener(this);
		btnMgroup.setBackground(new Color(135,206,250));
		btnMgroup.setBorderPainted(false);
		mb.add(btnMgroup); 
		
        setJMenuBar(mb);
       
       
		
		btnMstudents = new JButton("Student");
		btnMstudents.addActionListener(this);
		btnMstudents.setBackground(new Color(135,206,250));
		btnMstudents.setBorderPainted(false);
		mb.add(btnMstudents);  
        setJMenuBar(mb);
		
		btnMreport = new JButton("Reports");
		btnMreport.addActionListener(this);
		btnMreport.setBackground(new Color(135,206,250));
		btnMreport.setBorderPainted(false);
		mb.add(btnMreport);  
        setJMenuBar(mb);
		
		btnMImportExport = new JButton("Import / Export");
		btnMImportExport.addActionListener(this);
		btnMImportExport.setBackground(new Color(135,206,250));
		btnMImportExport.setBorderPainted(false);
		mb.add(btnMImportExport);  
        setJMenuBar(mb);
        
        btnMLogout = new JButton("Logout");
        btnMLogout.addActionListener(this);
        btnMLogout.setBackground(new Color(107,5,37));
        btnMLogout.setForeground(Color.white);
        btnMLogout.setBorderPainted(false);
		mb.add(btnMLogout);  
        setJMenuBar(mb);
        
		setPreferredSize(new Dimension(1000, 800));
		setLocationRelativeTo(null);
		
		
		Container c=getContentPane();  
		Font f=new Font("Serif", Font.ITALIC | Font.BOLD, 20);   
		
		
		// step 3 : creating JLabel for Heading
				JLabel heading_lbl=new JLabel();
				heading_lbl.setBounds(320,15,500,40);
				heading_lbl.setText("<html><font color=white size=8 ><u><b>Provide Groups details</b></u></html>");	
				heading_lbl.setFont(f);
				
				add(heading_lbl);
				
				Font f1=new Font("Serif",Font.BOLD,30);
				Font f2=new Font("Serif",Font.BOLD,20);
				
				
				lblClass = new JLabel("Class Name ");
				lblClass.setBounds(300,150,160,30); 
				lblClass.setForeground(Color.white);
				lblClass.setFont(f1);
				add(lblClass);
				
				txtClass = new JTextField();
				txtClass.setBounds(500,151,200,30); 
				txtClass.setFont(f2);
				add(txtClass);
				
				
				lblStartDate = new JLabel("Start Date");
				lblStartDate.setBounds(300,230,160,30); 
				lblStartDate.setForeground(Color.white);
				lblStartDate.setFont(f1);
				add(lblStartDate);
				
				txtStartDate = new JTextField();
				txtStartDate.setBounds(500,230,200,30); 
				txtStartDate.setEditable(false);
				txtStartDate.setFont(f2);
				add(txtStartDate);
				
				btnDT = new JButton("..hii");
				btnDT.setBounds(720,230,20,20);
				btnDT.setOpaque(true);
				btnDT.setBorderPainted(false);
				btnDT.setFont(new Font("Britannic Bold", Font.PLAIN, 20));
				add(btnDT);
				btnDT.addActionListener(this);
				
				btnSubmit = new JButton("Add");
				btnSubmit.setBounds(380,310,250,40);
				btnSubmit.setOpaque(true);
				btnSubmit.setBorderPainted(false);
				btnSubmit.setFont(new Font("Britannic Bold", Font.PLAIN, 20));
				btnSubmit.addActionListener(this);
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
			new GroupStudImportExportGUI();
		}
		
		if(e.getSource()==btnDT){
			txtStartDate.setText(new DatePicker(this).setPickedDate());
		}
		GroupDAO dao=null;
		if(e.getSource()==btnSubmit){
			if(txtClass.getText().length() == 0){
				JOptionPane.showMessageDialog(this,"Please insert Class Name");
				
			}
			else if(txtStartDate.getText().length() == 0){
				JOptionPane.showMessageDialog(this,"Please Select Date");
			}else{
				dao=new GroupOpr();
				GroupBean bean=new GroupBean();
				bean.setGroupName(txtClass.getText());
				bean.setStartDate(txtStartDate.getText());
				boolean flag= dao.insertGroups(bean);
				if(flag){
					JOptionPane.showMessageDialog(this,"Groups Created");
					this.setVisible(false);
					new GroupGUI();
				}else{
					JOptionPane.showMessageDialog(this,"Groups not created Please try again");
					txtClass.setText("");
					txtStartDate.setText("");
					
				}
				
				
			}
		}
		
		
	}
	
	public static void main(String args[])
	{ 
		new AddGroupGUI();
	
	}

}
/*
@SuppressWarnings("serial")
class CustomeBorder extends AbstractBorder{
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y,
            int width, int height) {
        // TODO Auto-generated method stubs
        super.paintBorder(c, g, x, y, width, height);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setStroke(new BasicStroke(12));
        g2d.setColor(Color.blue);
        g2d.drawRoundRect(x, y, width - 1, height - 1, 25, 25);
    }   
}
*/

