package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.function.LineFunction2D;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.statistics.Regression;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;






import bean.StudentBean;
import bean.StudentDecoding;

public class DecodePlotGraphGUI extends JFrame implements ActionListener,Printable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	String classId,className;
	JLabel lblStudent,lblTeacher,lblCurrentDate;
	JButton btnBack,btnPrint;
	StudentBean bean=new StudentBean();
	String graphType = "";
	
	
	DecodePlotGraphGUI(StudentBean bean, String classId, String className, String str){
		
		this.classId=classId;
		this.className=className;
		this.bean=bean;
		graphType = str;
		
		
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
			 final XYDataset dataset = createDataset(bean);
		     final JFreeChart chart = createChart(dataset);
		     drawRegressionLine(chart,dataset);
				
		     // If we have an input parameter, predict the price and draw the new point
				/*if (args.length >= 1 && args[0] != null) {
					// Estimate the linear function given the input data
					double regressionParameters[] = Regression.getOLSRegression(
							dataset, 0);
					double x = Double.parseDouble(args[0]);

					// Prepare a line function using the found parameters
					LineFunction2D linefunction2d = new LineFunction2D(
							regressionParameters[0], regressionParameters[1]);
					// This is the estimated price
					double y = linefunction2d.getValue(x);

					drawInputPoint(x, y);
				}
		     
		     */
		     final ChartPanel chartPanel = new ChartPanel(chart);
		     chartPanel.setPreferredSize(new java.awt.Dimension(800, 550));
		     p1.add(chartPanel);
		        
			 getContentPane().add(p1);
			
			
			
			
			
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
			new DecodePlotGUI(bean, classId, className);
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
	
	private void drawRegressionLine(JFreeChart chart, XYDataset inputData) {
		// Get the parameters 'a' and 'b' for an equation y = a + b * x,
		// fitted to the inputData using ordinary least squares regression.
		// a - regressionParameters[0], b - regressionParameters[1]
		double regressionParameters[] = Regression.getOLSRegression(inputData,
				0);

		// Prepare a line function using the found parameters
		LineFunction2D linefunction2d = new LineFunction2D(
				regressionParameters[0], regressionParameters[1]);

		// Creates a dataset by taking sample values from the line function
		XYDataset dataset = DatasetUtilities.sampleFunction2D(linefunction2d,
				0D, 30, 2, "Plotted Dates : - through - ");

		// Draw the line dataset
		XYPlot xyplot = chart.getXYPlot();
		xyplot.setDataset(1, dataset);
		XYLineAndShapeRenderer xylineandshaperenderer = new XYLineAndShapeRenderer(
				true, false);
        
		xylineandshaperenderer.setSeriesPaint(3, Color.YELLOW);
		xyplot.setRenderer(1, xylineandshaperenderer);
	}
	
	
private XYDataset createDataset(StudentBean bean) {
        
        final XYSeries series1 = new XYSeries("");
        
      //  Set<StudentDecoding> set=new TreeSet<StudentDecoding>();
        for(StudentDecoding deco : bean.getListDecoding()){
        	
        		series1.add(deco.getWeek(), deco.getScore());
        }
        
        
        
          
        
        
        /*final XYSeries series2 = new XYSeries("Second");
        series2.add(1.0, 5.0);
        series2.add(2.0, 7.0);
        series2.add(3.0, 6.0);
        series2.add(4.0, 8.0);
        series2.add(5.0, 4.0);
        series2.add(6.0, 4.0);
        series2.add(7.0, 2.0);
        series2.add(8.0, 1.0);*/

        /* final XYSeries series3 = new XYSeries("Third");
        series3.add(3.0, 4.0);
        series3.add(4.0, 3.0);
        series3.add(5.0, 2.0);
        series3.add(6.0, 3.0);
        series3.add(7.0, 6.0);
        series3.add(8.0, 3.0);
        series3.add(9.0, 4.0);
        series3.add(10.0, 3.0);*/

        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
       //dataset.addSeries(series2);
       /* dataset.addSeries(series3);*/
                
        return dataset;
        
    }
    
    /**
     * Creates a chart.
     * 
     * @param dataset  the data for the chart.
     * 
     * @return a chart.
     */
    private JFreeChart createChart(final XYDataset dataset) {
        
        // create the chart...
        final JFreeChart chart = ChartFactory.createXYLineChart(
            "Student Progress :  Decoding",      // chart title
            "Week",                      // x axis label
            "Number Correct",                      // y axis label
            dataset,                  // data
            PlotOrientation.VERTICAL,
            true,                     // include legend
            true,                     // tooltips
            false                     // urls
        );

        chart.setBackgroundPaint(Color.white);

        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        
        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, true);
        renderer.setSeriesShapesVisible(1, false);
        plot.setRenderer(renderer);
         
        NumberAxis xAxis = new NumberAxis("Week");
        xAxis.setTickUnit(new NumberTickUnit(1));
        plot.setDomainAxis(xAxis);
        
                
        return chart;
        
    }
	
	/*public static void main(String args[]){
		new DecodePlotGraphGUI(null,"", "","");
		
	}*/


	@Override
	public int print( Graphics g, PageFormat pf, int pi)
			throws PrinterException {
		// TODO Auto-generated method stub
		System.out.println("mybutton print");
	    if (pi >= 1) {
	      return Printable.NO_SUCH_PAGE;
	  }

	  Graphics2D g2 = (Graphics2D) g;
	  g2.translate(
	  pf.getImageableX(), pf.getImageableY());
	  Font  f = new Font("Monospaced",Font.PLAIN,12);
	  System.out.println (f);
	 // g2.setFONT (f);
	  paint(g2);
	  return Printable.PAGE_EXISTS;
	}

}
