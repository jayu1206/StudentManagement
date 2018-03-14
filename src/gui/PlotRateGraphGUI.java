package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javafx.print.Printer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.axis.SubCategoryAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.sun.javafx.print.PrinterImpl;

import abstrac.StudentDAO;
import bean.StudentBean;
import bean.StudentDecoding;
import bean.StudentRate;
import manegement.StudentOpr;

public class PlotRateGraphGUI extends JFrame implements ActionListener, Printable{

	
	String classId,className;
	JLabel lblStudent,lblTeacher,lblCurrentDate;
	JButton btnBack,btnPrint;
	StudentBean bean =new StudentBean();
	StudentDAO studDao = new StudentOpr();
	 List tblListText = new ArrayList<>();
	 List tblListCWPM = new ArrayList<>();
	 List tblListPostDate = new ArrayList<>();
	 List tblListPostErrors = new ArrayList<>();
	 List tblListPriDate = new ArrayList<>();
	 List tblListPriErrors = new ArrayList<>();
	 
	 DefaultTableModel model;
	 JTable jt;
	 HashSet<Object> setText=new HashSet<Object>();
	PlotRateGraphGUI(StudentBean bean, String classId, String className, String txtBegin, String txtEnd){
		

		
		this.classId=classId;
		this.className=className;
		this.bean=bean;
		
		if(!txtBegin.isEmpty() && !txtEnd.isEmpty()){
			
			try {
				
				int beginTxt = Integer.parseInt(txtBegin);
				int endTxt = Integer.parseInt(txtEnd);
				if (endTxt >= beginTxt){
					StudentBean studBean = studDao.getStudentbyDecodingAndRatingByText(bean.getId(), txtBegin, txtEnd);
					this.bean = studBean;
				}else{
					JOptionPane.showMessageDialog(this,beginTxt+" is not less then "+endTxt );
				}
              
            } catch (NumberFormatException e) {
                System.out.println("You've entered non-integer number");
                System.out.println("This caused " + e);
				JOptionPane.showMessageDialog(this,"Enter Number only (e.g 1 and 3)");

            }
			
			
			
			
		}
		
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
			 p1.setBounds(60,100,850,550);    
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
		        chartPanel.setPreferredSize(new java.awt.Dimension(700, 400));
		        p1.add(chartPanel);
				getContentPane().add(p1);
				
				
				
				 model = new DefaultTableModel();
				 
				 jt=new JTable(); 
				 jt.setRowHeight(20);
				 
				 jt.setFont(new Font("Times New Roman", Font.PLAIN, 18));
				 jt.setDefaultEditor(Object.class, null);
				 jt.setPreferredSize(new java.awt.Dimension(700, 121)); 
				// jt.setPreferredSize(new Dimension(500, 300));
				 jt.setModel(model);
				
				 for(int j = 0 ; j<tblListText.size() ; j++){
					 model.addColumn("TExt"+j);
				 }
				
				
		       /*  model.addColumn("Date");
		         model.addColumn("Errors");	*/
		          
		         
		         DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		         centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		         
		         for(int j = 0 ; j<tblListText.size() ; j++){
		        	 jt.getColumnModel().getColumn(j).setCellRenderer( centerRenderer );
				 }
		         /*jt.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		         jt.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
		         jt.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );*/
		         
		         
		         model.addRow(tblListText.toArray());
		         model.addRow(tblListCWPM.toArray());
		         model.addRow(tblListPriDate.toArray());
		         model.addRow(tblListPriErrors.toArray());
		         model.addRow(tblListPostDate.toArray());
		         model.addRow(tblListPostErrors.toArray());
		        
		         JTableHeader header= jt.getTableHeader();
			     header.setBackground(Color.yellow);
		         
		         p1.add(jt);	
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

		
	/*	final JFreeChart chart = ChartFactory.createStackedBarChart(
				  "Student Progress: Reading Rate ", "Category", "Correct Words Per Minute",
				  dataset, PlotOrientation.VERTICAL, true, true, false);

				  chart.setBackgroundPaint(new Color(249, 231, 236));

				  CategoryPlot plot = chart.getCategoryPlot();
				  plot.getRenderer().setSeriesPaint(0, new Color(0, 0, 255));
				  plot.getRenderer().setSeriesPaint(1, new Color(128, 0, 0));
				 
				  plot.getRenderer().setBaseItemLabelGenerator(
						    new StandardCategoryItemLabelGenerator(
						        "{2}", NumberFormat.getInstance()));
				  plot.getRenderer().setItemLabelsVisible(true);
				  return chart;
		*/
		
		
		
        final JFreeChart chart = ChartFactory.createStackedBarChart(
            "Student Progress: Reading Rate",  // chart title
            "Text",                  // domain axis label
            "Correct Words Per Minute",                     // range axis label
            dataset,                     // data
            PlotOrientation.VERTICAL,    // the plot orientation
            true,                        // legend
            true,                        // tooltips
            false                        // urls
        );
  
        
        CategoryPlot plot = chart.getCategoryPlot();
		  plot.getRenderer().setSeriesPaint(0, new Color(129, 218, 245));
		  plot.getRenderer().setSeriesPaint(1, new Color(250,88,88));
		  plot.getRenderer().setSeriesPaint(2, new Color(153, 255, 153));
		 // plot.getRenderer().setSeriesOutlinePaint(0, new Color(153, 255, 153));
		  plot.getRenderer().setBaseItemLabelGenerator(
				    new StandardCategoryItemLabelGenerator(
				        "{2}", NumberFormat.getInstance()));
		  plot.getRenderer().setItemLabelsVisible(true);
		  
		 SubCategoryAxis domainAxis = new SubCategoryAxis("");
	        domainAxis.setCategoryMargin(0.05);
	       // domainAxis.addSubCategory("1 - Text");
	        plot.setDomainAxis(domainAxis);
	        plot.setFixedLegendItems(createLegendItems());

        return chart;
        
    }


	private LegendItemCollection createLegendItems() {
		  LegendItemCollection result = new LegendItemCollection();
	        LegendItem item1 = new LegendItem("1 Base Lin", new Color(129, 218, 245));
	        LegendItem item2 = new LegendItem("2 Gain / Los", new Color(250,88,88));
	        result.add(item1);
	        result.add(item2);
	        return result;
	}



	private CategoryDataset createDataset() {
		
		
	
		/* double[][] data = new double[bean.getListRate().size()][bean.getListRate().size()];
				 
				 {
				  {210, 300, 320, 265, 299, 200},
				  {200, 304, 201, 201, 340, 300},
				  };
		 
		 //int i = 0 ;
		 
		 for(int i =0 ; i<2 ; i++){
			 for(int j = 0 ; j<bean.getListRate().size() ; j++){
				 
			 }
			 
		 }
		 
		 for(StudentRate rate : bean.getListRate()){
		      // data[i][i] = rate.getCwpm(), rate.getText();
					
					 //result.addValue(rate.getCwpm(), rate.getText()+"", rate.getText()+"");
				 
				 
			 }
		 
				  return DatasetUtilities.createCategoryDataset(
				  "Team ", "Match", data);*/
		 
	
		 DefaultCategoryDataset result = new DefaultCategoryDataset();
		// int i = 1;
		 int tempCwpm = 0;
		   
		 tblListText.add("Text");
		 tblListCWPM.add("Change");
		 tblListPostDate.add("Post Date");
		 tblListPostErrors.add("Post Errors");
		 tblListPriDate.add("Pre Date");
		 tblListPriErrors.add("Pre Errors");

		 for(StudentRate rate : bean.getListRate()){
	        
			 
			 if(rate.getTime() == 2){
				 int finalcwpm = rate.getCwpm() - tempCwpm;
				 result.addValue(finalcwpm,rate.getTime()+"" ,rate.getText()+"");
				
				 tblListCWPM.add(finalcwpm);
				 tblListPostDate.add(rate.getDate());
				 tblListPostErrors.add(rate.getErrors());
				
			 }else{
				
				 tempCwpm= rate.getCwpm();
				 result.addValue(rate.getCwpm(),rate.getTime()+"" ,rate.getText()+"");
				 tblListPriDate.add(rate.getDate());
				 tblListPriErrors.add(rate.getErrors());
			 }
			 setText.add(rate.getText());			 
		//	i++;	 
			 
		 }
		 tblListText.addAll(setText);

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
			synchronized (this) {
				new PlotRateGUI(bean, classId, className);
				setVisible(false);
			}
		}
		if(e.getSource()==btnPrint){
			
			/*PrinterJob pjob = PrinterJob.getPrinterJob();
			PageFormat preformat = pjob.defaultPage();
			preformat.setOrientation(PageFormat.LANDSCAPE);
			PageFormat postformat = pjob.pageDialog(preformat);
			//If user does not hit cancel then print.
			if (preformat != postformat) {
			    //Set print component
			    pjob.setPrintable(this, postformat);
			    if (pjob.printDialog()) {
			        try {
						pjob.print();
					} catch (PrinterException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    }
			}*/
			
			 PrinterJob printJob = PrinterJob.getPrinterJob();
			 printJob.setPrintable(this);
			 
			 if(printJob.printDialog()){
				    try { printJob.print(); } 
				    catch (Exception PrinterExeption
				    ) { }
				  }
			
		}
	}
	


	@Override
	public int print(Graphics gx, PageFormat pf, int page)
			throws PrinterException {
		// TODO Auto-generated method stub
		if (page>0){return NO_SUCH_PAGE;} //Only one page
		
		
		            Graphics2D g = (Graphics2D)gx; //Cast to Graphics2D object
		            pf.setOrientation(PageFormat.PORTRAIT);
		            g.translate(pf.getImageableX(), pf.getImageableY()); //Match origins to imageable area
		            
		            Dimension size = this.getSize(); // component size
		            double pageWidth = pf.getImageableWidth(); // Page width
		            double pageHeight = pf.getImageableHeight(); // Page height
		            
		         
		            // If the component is too wide or tall for the page, scale it down
		            if (size.width > pageWidth) {
		              double factor = pageWidth / size.width; // How much to scale
		              g.scale(factor, factor); // Adjust coordinate system
		              pageWidth /= factor; // Adjust page size up
		              pageHeight /= factor;
		            }
		            if (size.height > pageHeight) { // Do the same thing for height
		              double factor = pageHeight / size.height;
		              g.scale(factor, factor);
		              pageWidth /= factor;
		              pageHeight /= factor;
		            }
		            
		            g.translate((int) pf.getImageableX(), (int) pf.getImageableY());
		            
		            this.print(g);
		     
		
		            return PAGE_EXISTS; //Page exists (offsets start at zero!)

	}

}
