package gui;

import java.awt.BasicStroke;
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
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.Range;
import org.jfree.data.function.LineFunction2D;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.statistics.Regression;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.TextAnchor;

import abstrac.StudentDAO;
import bean.StudentBean;
import bean.StudentDecoding;
import manegement.StudentOpr;

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
	StudentDAO studDao = new StudentOpr();
	StudentBean studdata = null;
	DecodePlotGraphGUI(StudentBean bean, String classId, String className, String str, String txtBegin, String txtend){
		
		this.classId=classId;
		this.className=className;
		this.bean=bean;
		graphType = str;
		
		
		try {
			
			if(!txtBegin.isEmpty() && !txtend.isEmpty()){
				int beginTxt = Integer.parseInt(txtBegin);
				int endTxt = Integer.parseInt(txtend);
				if (endTxt >= beginTxt){
					System.out.println(beginTxt + " "+endTxt);
					StudentBean studBean = studDao.getStudentbyDecodingAndRatingByweek(bean.getId(), beginTxt, endTxt);
					this.bean = studBean;
				}else{
					JOptionPane.showMessageDialog(this,beginTxt+" is NOT less then "+endTxt );
				}				
			}					
          
        } catch (NumberFormatException e) {
            System.out.println("You've entered non-integer number");
            System.out.println("This caused " + e);
			JOptionPane.showMessageDialog(this,"Enter Number only (e.g 1 and 3)");

        }
		
		
		
		
		
		setLayout(new BorderLayout());
		setContentPane(new JLabel(new ImageIcon(this.getClass().getResource("/image/blue.jpg"))));
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
			
			
			lblCurrentDate = new JLabel("Current Date  :  "+new SimpleDateFormat("MM/dd/yyyy").format(new Date())+"");
			lblCurrentDate.setBounds(650,45,300,40); 
			lblCurrentDate.setForeground(Color.white);
			lblCurrentDate.setFont(f1);
			add(lblCurrentDate);
			
			
			
			/* P1 for first tab data  */
			 JPanel p1=new JPanel();//createContactPanel1();   // Call method for set the 1st tab frame contenct
			 p1.setBounds(60,100,850,600);    
			 p1.setBackground(Color.black);  
			 //setContentPane(p1); //add(p1);
			 final XYDataset dataset = createDataset(this.bean);
		     final JFreeChart chart = createChart(dataset);
		    // drawRegressionLine(chart,dataset);
		     
		        
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
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()== btnBack){
			synchronized (this) {
				new DecodePlotGUI(bean, classId, className);
				setVisible(false);
			}
		}
		if(e.getSource()==btnPrint){
			
			 PrinterJob printJob = PrinterJob.getPrinterJob();
			 printJob.setPrintable(this);
			 
			 PageFormat preformat = printJob.defaultPage();
				preformat.setOrientation(PageFormat.LANDSCAPE);
				PageFormat postformat = printJob.pageDialog(preformat);
				printJob.setPrintable(this, postformat);
				
			 if(printJob.printDialog()){
				    try { 
				    	btnBack.setVisible(false);
				    	btnPrint.setVisible(false);
				    	printJob.print(); 
				    	btnBack.setVisible(true);
				    	btnPrint.setVisible(true);
				    } 
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
        pf.setOrientation(PageFormat.LANDSCAPE);
        g.translate((pf.getImageableX()), (pf.getImageableY())); //Match origins to imageable area
        
        Dimension size = this.getSize(); // component size
        double pageWidth = pf.getImageableWidth(); // Page width
        double pageHeight = pf.getImageableHeight(); // Page height
        
     
        // If the component is too wide or tall for the page, scale it down
        if (size.width > pageWidth) {
          double factor = pageWidth / size.width; // How much to scale
          factor = factor *1;
          g.scale(factor, factor); // Adjust coordinate system
          pageWidth /= factor; // Adjust page size up
          pageHeight /= factor;
        }
        if (size.height > pageHeight) { // Do the same thing for height
          double factor = pageHeight / size.height;
          factor = factor *1;
          g.scale(factor, factor);
          pageWidth /= factor;
          pageHeight /= factor;
        }
        
        g.translate((int) pf.getImageableX(), (int) pf.getImageableY());
        
        this.print(g);
 

        return PAGE_EXISTS; //Page exists (offsets start at zero!)

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
				0D, 30, 2, "Estimated Progress");

		// Draw the line dataset
		XYPlot xyplot = chart.getXYPlot();
		xyplot.setDataset(1, dataset);
		XYLineAndShapeRenderer xylineandshaperenderer = new XYLineAndShapeRenderer(
				true, false);
        
		 StandardXYToolTipGenerator ttG =
				    new StandardXYToolTipGenerator("{1},{2}",  NumberFormat.getInstance(),  NumberFormat.getInstance());
		 
		xylineandshaperenderer.setSeriesPaint(3, Color.YELLOW);
		xylineandshaperenderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator());
		xylineandshaperenderer.setBaseToolTipGenerator(ttG);
	
		xyplot.setRenderer(1, xylineandshaperenderer);
		
		  XYItemRenderer renderer1 = chart.getXYPlot().getRenderer();
		  renderer1.setBaseItemLabelsVisible(true);
		  renderer1.setBaseToolTipGenerator(new StandardXYToolTipGenerator());
		 
		  renderer1.setBaseToolTipGenerator(ttG);
		  
		  
		  xyplot.setRenderer(0,renderer1);
	
		
		
	}
	
	
private XYDataset createDataset(StudentBean bean) {
        
        final XYSeries series1 = new XYSeries("Student Score");
         
            for(StudentDecoding deco : bean.getListDecoding()){
            	
            	series1.add(deco.getWeek(), deco.getScore());
            }
            
            ArrayList regLineAB= getLinearRegressionLine(bean.getListDecoding());
            double beta1 = (double) regLineAB.get(0);
            double beta0 = (double) regLineAB.get(1);
            System.out.println("beta1 "+beta1);
            final XYSeries series2 = new XYSeries("Estimated Progress");
          
            for(StudentDecoding deco : bean.getListDecoding()){
                     //  System.out.println(" Count : "+ bean.getListDecoding().size());
            		double tempFinal = (  (beta1*deco.getWeek())+beta0  );
                    series2.add(deco.getWeek(),tempFinal);
            }
          
          
            
        XYSeries series3 = null;
		if(graphType.contains("Avg")){
			
			series3 = new XYSeries("Class Average");
			
			ArrayList<StudentBean> list = studDao.getAllStudents(classId);
			List ids = new ArrayList<>();
			for (StudentBean studbean : list) {

				ids.add(studbean.getId());
			}
			List avgList = studDao.getAvgofStud(ids);

			ArrayList regLineABAvg = getLinearRegressionLineforAvg(avgList);
			double betaA = (double) regLineABAvg.get(0);
			double betaB = (double) regLineABAvg.get(1);
			//System.out.println("beta1 " + beta1);
			
			for (StudentDecoding deco : bean.getListDecoding()) {
				// System.out.println(" Reg : "+ ( (beta1*deco.getWeek())+beta0));
				double regABAvg = ((betaA * deco.getWeek()) + betaB);
				series3.add(deco.getWeek(), regABAvg);
			}
        	
        }
        
        
      
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
        dataset.addSeries(series2);
        
        if (graphType.contains("Avg")){
        	dataset.addSeries(series3);
        }
        
        
                
        return dataset;
        
    }
    


	private ArrayList getLinearRegressionLineforAvg(List avgList) {
	

			ArrayList listOfAB=new ArrayList<>();
			int MAXN = 1000;
	        int n = 0;
	        double[] xTemp =new double[avgList.size()]; //{1,2,3,4};
	        double[] yTemp = new double[avgList.size()]; //{5,7,7,8};
	        
	        int i=0;
	        Double firstValue;
	        for (int l = 0; l < avgList.size(); l++) {
				  Double[] d = (Double[]) avgList.get(l);
				//  lst.add(new Double[]{Double.parseDouble(d[0].toString()), Double.parseDouble(d[1].toString())});
				  xTemp[i] =  Double.parseDouble(d[0].toString());
				  yTemp[i] =  Double.parseDouble(d[1].toString());
				  i++;
		      }
	   
//	        for (int j=0; j<avgList.size(); j++){
//	        	xTemp[i] =  
//	        	yTemp[i] =  
//	        	i++;
//	        }
	        
//	        for(StudentDecoding deco : avgList){
//	        	xTemp[i]=deco.getWeek();
//	        	yTemp[i] = deco.getScore();
//	        	i++;
//	        }
	        
	        
	        double[] x = new double[MAXN];
	        double[] y = new double[MAXN];

	        // first pass: read in data, compute xbar and ybar
	        double sumx = 0.0, sumy = 0.0, sumx2 = 0.0;
	       for(int j = 0 ; j<xTemp.length ; j++){
	            x[n] = xTemp[n];
	            y[n] = yTemp[n];
	            sumx  += x[n];
	            sumx2 += x[n] * x[n];
	            sumy  += y[n];
	            n++;
	        }
	        double xbar = sumx / n;
	        double ybar = sumy / n;

	        // second pass: compute summary statistics
	        double xxbar = 0.0, yybar = 0.0, xybar = 0.0;
	        for (int i1 = 0; i1 < n; i1++) {
	            xxbar += (x[i1] - xbar) * (x[i1] - xbar);
	            yybar += (y[i1] - ybar) * (y[i1] - ybar);
	            xybar += (x[i1] - xbar) * (y[i1] - ybar);
	        }
	        double beta1 = xybar / xxbar;
	        double beta0 = ybar - beta1 * xbar;

	        // print results
	        System.out.println("y   = " + beta1 + " * x + " + beta0);
	        
	        listOfAB.add(beta1);
	        listOfAB.add(beta0);
	        
			
			
			
		return listOfAB;
		
	}


	private ArrayList getLinearRegressionLine(
		ArrayList<StudentDecoding> listDecoding) {
	// TODO Auto-generated method stub
		ArrayList listOfAB=new ArrayList<>();
		int MAXN = 1000;
        int n = 0;
        double[] xTemp =new double[listDecoding.size()]; //{1,2,3,4};
        double[] yTemp = new double[listDecoding.size()]; //{5,7,7,8};
        
        int i=0;
        for(StudentDecoding deco : listDecoding){
        	xTemp[i]=deco.getWeek();
        	yTemp[i] = deco.getScore();
        	i++;
        }
        
        
        double[] x = new double[MAXN];
        double[] y = new double[MAXN];

        // first pass: read in data, compute xbar and ybar
        double sumx = 0.0, sumy = 0.0, sumx2 = 0.0;
       for(int j = 0 ; j<xTemp.length ; j++){
            x[n] = xTemp[n];
            y[n] = yTemp[n];
            sumx  += x[n];
            sumx2 += x[n] * x[n];
            sumy  += y[n];
            n++;
        }
        double xbar = sumx / n;
        double ybar = sumy / n;

        // second pass: compute summary statistics
        double xxbar = 0.0, yybar = 0.0, xybar = 0.0;
        for (int i1 = 0; i1 < n; i1++) {
            xxbar += (x[i1] - xbar) * (x[i1] - xbar);
            yybar += (y[i1] - ybar) * (y[i1] - ybar);
            xybar += (x[i1] - xbar) * (y[i1] - ybar);
        }
        double beta1 = xybar / xxbar;
        double beta0 = ybar - beta1 * xbar;

        // print results
        System.out.println("y   = " + beta1 + " * x + " + beta0);
        
        listOfAB.add(beta1);
        listOfAB.add(beta0);
        
		
		
		
	return listOfAB;
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
        
       
        
        NumberAxis xAxis = new NumberAxis("Week");
        /* X axis range set 0 to 50 with number tick meand dispaly to next number in x axis  */
       
        xAxis.setTickUnit(new NumberTickUnit(1));
      //  xAxis.setRange(0.0, 52.0);
        
        /* Code end  */
        plot.setDomainAxis(xAxis);
       
        
        
        
        //Range auto = plot.getRangeAxis().getRange();
        
     //   plot.getRangeAxis().setUpperBound(50.00);
        
       /* NumberAxis yAxis = new NumberAxis("Number Correct");
        yAxis.setTickUnit(new NumberTickUnit(10));
        plot.setDomainAxis(yAxis);*/
        
        
        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, false);
        renderer.setSeriesShapesVisible(1, true);
        renderer.setBaseLinesVisible(true);
        renderer.setBaseItemLabelsVisible(Boolean.TRUE);
        renderer.setBaseItemLabelFont( new Font("Calibri", Font.BOLD, 15));
        renderer.setBaseItemLabelGenerator((XYItemLabelGenerator) new StandardXYItemLabelGenerator());
        plot.setRenderer(renderer);
       
       
                
        return chart;
        
    }
	
	/*public static void main(String args[]){
		new DecodePlotGraphGUI(null,"", "","");
		
	}*/



}
