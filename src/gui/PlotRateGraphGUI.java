package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.print.PrinterJob;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.SubCategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import bean.StudentBean;
import bean.StudentRate;

public class PlotRateGraphGUI extends JFrame implements ActionListener{

	
	String classId,className;
	JLabel lblStudent,lblTeacher,lblCurrentDate;
	JButton btnBack,btnPrint;
	StudentBean bean =new StudentBean();
	
	PlotRateGraphGUI(StudentBean bean, String classId, String className){
		

		
		this.classId=classId;
		this.className=className;
		this.bean=bean;
		
		setLayout(new BorderLayout());
		setContentPane(new JLabel(new ImageIcon(this.getClass().getResource("/image/black-back-ground.jpg"))));
		setLayout(null);
		
		Font f1=new Font("Serif",Font.BOLD,20);
		Font f2=new Font("Serif",Font.BOLD,20);
		
		
			lblStudent = new JLabel("Student  :  "+bean.getStudFirstName()+ " "+bean.getStudLastName() +"");
			lblStudent.setBounds(150,15,300,30); 
			lblStudent.setForeground(Color.white);
			lblStudent.setFont(f1);
			add(lblStudent);
			
			lblTeacher = new JLabel("Teacher  :  "+bean.getTeacher()+"");
			lblTeacher.setBounds(150,50,300,30); 
			lblTeacher.setForeground(Color.white);
			lblTeacher.setFont(f1);
			add(lblTeacher);
			
			
			lblCurrentDate = new JLabel("Current Date  :  "+new Date()+"");
			lblCurrentDate.setBounds(650,45,300,40); 
			lblCurrentDate.setForeground(Color.white);
			lblCurrentDate.setFont(f1);
			add(lblCurrentDate);
			
			
			
			/* P1 for first tab data  */
			 JPanel p1=new JPanel();//createContactPanel1();   // Call method for set the 1st tab frame contenct
			 p1.setBounds(60,100,850,600);    
			 p1.setBackground(Color.black);  
			 //setContentPane(p1); //add(p1);
		//	 final XYDataset dataset = createDataset(bean);
			 
			 
//			 String chartTitle = "";
//		     final JFreeChart barChart = ChartFactory.createBarChart(
//			         chartTitle,           
//			         "Category",            
//			         "Correct Words Per Minute",            
//			         createDataset(bean),          
//			         PlotOrientation.VERTICAL,           
//			         true, true, false);
			 
			  	final CategoryDataset dataset = createDataset();
		        final JFreeChart chart = createChart(dataset);
		        final ChartPanel chartPanel = new ChartPanel(chart);
		        chartPanel.setPreferredSize(new java.awt.Dimension(800, 500));
		        p1.add(chartPanel);
				getContentPane().add(p1);
				
//		     final  ChartPanel chartPanel = new ChartPanel( chart ); 
//		     chartPanel.setPreferredSize(new java.awt.Dimension( 700 , 500 ) );  
//		     p1.add(chartPanel);
//		     getContentPane().add(p1);
			 
//			 String chartTitle = "";
//			 JFreeChart barChart = ChartFactory.createBarChart(
//			         chartTitle,           
//			         "Category",            
//			         "Score",            
//			         createDataset(),          
//			         PlotOrientation.VERTICAL,           
//			         true, true, false);
//			 ChartPanel chartPanel = new ChartPanel( barChart );        
//		     chartPanel.setPreferredSize(new java.awt.Dimension( 700 , 500 ) );        
//		   //  setContentPane( chartPanel ); 		
//		     p1.add(chartPanel);
//			 getContentPane().add(p1);
//			
			
			 btnBack = new JButton("Back");
	         btnBack.setBounds(0,700,150,40);
	         btnBack.setBackground(Color.WHITE);
	         btnBack.setOpaque(true);
	         btnBack.setBorderPainted(false);
	         btnBack.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
	         add(btnBack);
	         getContentPane().add(btnBack);
	         btnBack.addActionListener(this);
	         
	         
	         btnPrint = new JButton("Print");
	         btnPrint.setBounds(840,700,150,40);
	         btnPrint.setBackground(Color.WHITE);
	         btnPrint.setOpaque(true);
	         btnPrint.setBorderPainted(false);
	         btnPrint.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
	         add(btnPrint);
	         getContentPane().add(btnPrint);
	         btnPrint.addActionListener(this);
		
		
		
		
		setSize(1000,800);
		centerFrame();
		setTitle("Progress Monitor Data Manager");
		/* setDefaultCloseOperation(javax.swing.
		       WindowConstants.DISPOSE_ON_CLOSE);*/
		WindowListener exitListener = new WindowAdapter() {
		
		   public void windowClosing(WindowEvent e) {
		   		System.exit(0);
		   }
		};
		addWindowListener(exitListener);
		// pack();
		setLayout(null);   
		setVisible(true); 
		
	
	}



	private JFreeChart createChart(CategoryDataset dataset) {

        final JFreeChart chart = ChartFactory.createStackedBarChart(
            "Student Progress: Reading Rate",  // chart title
            "Category",                  // domain axis label
            "Correct Words Per Minute",                     // range axis label
            dataset,                     // data
            PlotOrientation.VERTICAL,    // the plot orientation
            true,                        // legend
            true,                        // tooltips
            false                        // urls
        );
    
        
        SubCategoryAxis domainAxis = new SubCategoryAxis("Text / Month");
        domainAxis.setCategoryMargin(0.05);
        
        for(StudentRate rate : bean.getListRate()){
        	
        	domainAxis.addSubCategory(rate.getText()+"");
		}
       // domainAxis.addSubCategory("Product 1");
 //       domainAxis.addSubCategory("Product 2");
//        domainAxis.addSubCategory("Product 3");
        
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setDomainAxis(domainAxis);
        return chart;
        
    }


	private CategoryDataset createDataset() {
		
		
		 DefaultCategoryDataset result = new DefaultCategoryDataset();
		 
		 for(StudentRate rate : bean.getListRate()){
	        
			Date initDate;
			
			try {
				 initDate = new SimpleDateFormat("dd-MM-yyyy").parse(rate.getDate());
				 SimpleDateFormat formatter = new SimpleDateFormat("MM-yyyy");
				 String parsedDate = formatter.format(initDate);
				 result.addValue(rate.getCwpm(), rate.getText()+"", parsedDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	 
			 
		 }

         return result;
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
		
		if(e.getSource()== btnBack){
			setVisible(false);
			new PlotRateGUI(bean, classId, className);
		}
		if(e.getSource()==btnPrint){
			 PrinterJob printJob = PrinterJob.getPrinterJob();
			// printJob.setPrintable((btnPrint) e.getSource());
			 if(printJob.printDialog()){
				    try { printJob.print(); } 
				    catch (Exception PrinterExeption
				    ) { }
				  }
			
		}
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//new PlotRateGraphGUI();

	}

}
