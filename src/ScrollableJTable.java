import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScrollableJTable extends JFrame implements ActionListener{
  public static void main(String[] args) {
  new ScrollableJTable();
  }
  
  JButton btnSubmit,btnDelete,btnImportExport,btnBack,btnExit;
	JButton  btnMgroup,btnMstudents,btnMreport,btnMImportExport;
	JMenuItem group,students,report;
  public ScrollableJTable(){
	  
	  
	  setLayout(new BorderLayout());
		setContentPane(new JLabel(new ImageIcon(this.getClass().getResource("/image/black-back-ground.jpg"))));
		setLayout(new FlowLayout());
		
		JMenuBar mb=new JMenuBar();  
      
      btnMgroup = new JButton("Groups");
		btnMgroup.addActionListener(this);
		mb.add(btnMgroup);  
      setJMenuBar(mb);;
		
		btnMstudents = new JButton("Student");
		btnMstudents.addActionListener(this);
		mb.add(btnMstudents);  
      setJMenuBar(mb);
		
		btnMreport = new JButton("Reports");
		btnMreport.addActionListener(this);
		mb.add(btnMreport);  
       setJMenuBar(mb);
		
		btnMImportExport = new JButton("Import / Export");
		btnMImportExport.addActionListener(this);
		mb.add(btnMImportExport);  
      setJMenuBar(mb);
      
      
  //JFrame frame = new JFrame("Creating a Scrollable JTable!");
  JPanel panel = new JPanel();
  String data[][] = {{"001","vinod","Bihar","India","Biology","65","First"},
   {"002","Raju","ABC","Kanada","Geography","58","second"},
   {"003","Aman","Delhi","India","computer","98","Dictontion"},
   {"004","Ranjan","Bangloor","India","chemestry","90","Dictontion"},
   {"001","vinod","Bihar","India","Biology","65","First"},
   {"002","Raju","ABC","Kanada","Geography","58","second"},
   {"003","Aman","Delhi","India","computer","98","Dictontion"},
   {"004","Ranjan","Bangloor","India","chemestry","90","Dictontion"},
   {"001","vinod","Bihar","India","Biology","65","First"},
   {"002","Raju","ABC","Kanada","Geography","58","second"},
   {"003","Aman","Delhi","India","computer","98","Dictontion"},
   {"004","Ranjan","Bangloor","India","chemestry","90","Dictontion"},
   {"001","vinod","Bihar","India","Biology","65","First"},
   {"002","Raju","ABC","Kanada","Geography","58","second"},
   {"003","Aman","Delhi","India","computer","98","Dictontion"},
   {"004","Ranjan","Bangloor","India","chemestry","90","Dictontion"},
   {"001","vinod","Bihar","India","Biology","65","First"},
   {"002","Raju","ABC","Kanada","Geography","58","second"},
   {"003","Aman","Delhi","India","computer","98","Dictontion"},
   {"004","Ranjan","Bangloor","India","chemestry","90","Dictontion"},
   {"001","vinod","Bihar","India","Biology","65","First"},
   {"002","Raju","ABC","Kanada","Geography","58","second"},
   {"003","Aman","Delhi","India","computer","98","Dictontion"},
   {"004","Ranjan","Bangloor","India","chemestry","90","Dictontion"},
   {"001","vinod","Bihar","India","Biology","65","First"},
   {"002","Raju","ABC","Kanada","Geography","58","second"},
   {"003","Aman","Delhi","India","computer","98","Dictontion"},
   {"004","Ranjan","Bangloor","India","chemestry","90","Dictontion"},
   {"001","vinod","Bihar","India","Biology","65","First"},
   {"002","Raju","ABC","Kanada","Geography","58","second"},
   {"003","Aman","Delhi","India","computer","98","Dictontion"},
   {"004","Ranjan","Bangloor","India","chemestry","90","Dictontion"}};
  String col[] = {"Roll","Name","State","country","Math","Marks","Grade"};
  JTable table = new JTable(data,col);
  JTableHeader header = table.getTableHeader();
  table.setBounds(100,500,100,100);
  header.setBackground(Color.yellow);
  JScrollPane pane = new JScrollPane(table);
  table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
  panel.add(pane);
  add(panel);
  setSize(1000,800);
  centerFrame();
  //frame.setUndecorated(true);
  /*frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);*/
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
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}