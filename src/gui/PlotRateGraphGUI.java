package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javafx.print.Printer;

import javax.swing.BorderFactory;
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
import javax.swing.table.TableCellRenderer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.axis.SubCategoryAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;

import com.sun.javafx.print.PrinterImpl;

import abstrac.StudentDAO;
import bean.StudentBean;
import bean.StudentDecoding;
import bean.StudentRate;
import manegement.StudentOpr;

public class PlotRateGraphGUI extends JFrame  implements ActionListener, Printable{

	
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
		
		System.out.println("PlotRateGraphGUI");
		
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
		setContentPane(new JLabel(new ImageIcon(this.getClass().getResource("/image/sky.png"))));
		setLayout(null);
		
		Font f1=FontClass.MuseoSans700(18); 
		Font f2=FontClass.MuseoSans500(20);
		
		
			lblStudent = new JLabel("Student  :  "+bean.getStudFirstName()+ " "+bean.getStudLastName() +"");
			lblStudent.setBounds(120,15,300,30); 
			lblStudent.setFont(f1);
			add(lblStudent);
			
			lblTeacher = new JLabel("Teacher  :  "+bean.getTeacher()+"");
			lblTeacher.setBounds(120,50,300,30); 
			lblTeacher.setFont(f1);
			add(lblTeacher);
			
			
			lblCurrentDate = new JLabel("Current Date  :  "+new SimpleDateFormat("MM/dd/yyyy").format(new Date())+"");
			lblCurrentDate.setBounds(620,45,300,40); 
			lblCurrentDate.setFont(f1);
			add(lblCurrentDate);
			
			
			
			/* P1 for first tab data  */
			 JPanel p1=new JPanel();//createContactPanel1();   // Call method for set the 1st tab frame contenct
			 p1.setBounds(120,100,750,410);    
			 p1.setBackground(new Color(255,255,255));  

			 
			  	final CategoryDataset dataset = createDataset();
		        final JFreeChart chart = createChart(dataset);
		        final ChartPanel chartPanel = new ChartPanel(chart);
		        chartPanel.setPreferredSize(new java.awt.Dimension(700, 400));
		        p1.add(chartPanel);
				add(p1);
				
				
				
				 model = new DefaultTableModel();
				 
				 jt=new JTable(); 
				 jt.setRowHeight(22);
				 
				 Font f3 = FontClass.MuseoSans500(15);
				 f3.deriveFont(Font.PLAIN, 15);
					
				 jt.setFont(f3);
				 jt.setDefaultEditor(Object.class, null);
				 jt.setPreferredSize(new java.awt.Dimension(700, 121)); 
				// jt.setPreferredSize(new Dimension(500, 300));
				 jt.setModel(model);
				
				 for(int j = 0 ; j<tblListText.size() ; j++){
					 model.addColumn("TExt"+j);
				 }
				
				
		       /*  model.addColumn("Date");
		         model.addColumn("Errors");	*/
		          
				 
				 
				 jt.setDefaultRenderer(Object.class, new DefaultTableCellRenderer()
				 {
				     @Override
				     public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
				     {
				         final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				         Font f3 = FontClass.MuseoSans700(15);
				         if(row==0 || column==0){
				        	 c.setBackground(new Color(188,221,238));
				        	 c.setFont(f3);
				        	 
				         }else{
				        	 c.setBackground(Color.WHITE);
				         }
				        super.setHorizontalAlignment(JLabel.CENTER);
				         return c;
				     }
				 });
		        
				/* DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		          centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		          centerRenderer.setBorder(BorderFactory.createCompoundBorder());
		          
		         for(int j = 0 ; j<tblListText.size() ; j++){
		        	 jt.getColumnModel().getColumn(j).setCellRenderer( centerRenderer );
				 }*/
		         
		         model.addRow(tblListText.toArray());
		         model.addRow(tblListCWPM.toArray());
		         model.addRow(tblListPriDate.toArray());
		         model.addRow(tblListPriErrors.toArray());
		         model.addRow(tblListPostDate.toArray());
		         model.addRow(tblListPostErrors.toArray());
		        
		        jt.setBackground(Color.black);
		         
		        // p1.add(jt);	
			     jt.setBounds(120,520,750,130);
			     add(jt);
		         
			
			
			     btnBack = new JButton(new ImageIcon(this.getClass().getResource("/image/back.png")));
		         btnBack.setBounds(120,660,120,40);
		         btnBack.setOpaque(false);
		         btnBack.setContentAreaFilled(false);
		         btnBack.setBorderPainted(false);
		         btnBack.setFocusable(false);
		         btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
		         add(btnBack);
		         btnBack.addActionListener(this);
		         
		         
		         btnPrint = new JButton(new ImageIcon(this.getClass().getResource("/image/print combo.png")));
		         btnPrint.setBounds(750,660,120,40);
		         btnPrint.setOpaque(false);
		         btnPrint.setContentAreaFilled(false);
		         btnPrint.setBorderPainted(false);
		         btnPrint.setFocusable(false);
		         btnPrint.setCursor(new Cursor(Cursor.HAND_CURSOR));
		         add(btnPrint);
		         //getContentPane().add(btnPrint);
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
		
		
		
        final JFreeChart chart = ChartFactory.createBarChart(
            "Student Progress: Reading Rate",  // chart title
            "Text",                  // domain axis label
            "Correct Words Per Minute",                     // range axis label
            dataset,                     // data
            PlotOrientation.VERTICAL,    // the plot orientation
            true,                        // legend
            true,                        // tooltips
            false                        // urls
        );
  
        chart.getTitle().setPaint(new Color(65,127,159));
        chart.getTitle().setHorizontalAlignment(HorizontalAlignment.CENTER);
        
        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        
    	//Spaces between bars
        renderer.setItemMargin(0.03);
        
		  plot.getRenderer().setSeriesPaint(0, new Color(188, 221, 238));
		  plot.getRenderer().setSeriesPaint(1, new Color(205, 82, 87));
		  plot.getRenderer().setSeriesPaint(2, new Color(153, 255, 153));
		 // plot.getRenderer().setSeriesOutlinePaint(0, new Color(153, 255, 153));
		  plot.getRenderer().setBaseItemLabelFont( FontClass.MuseoSans900(15));
		 
		  
		  plot.getRenderer().setBaseItemLabelGenerator(
				    new StandardCategoryItemLabelGenerator(
				        "{2}", NumberFormat.getInstance()));
		  plot.getRenderer().setItemLabelsVisible(true);
		  
		 
		  
		  plot.getRangeAxis().setUpperBound(200.00);
		  plot.getRangeAxis().setLabelPaint(new Color(65,127,159));
		  plot.getRangeAxis().setTickLabelPaint(new Color(65,127,159));
		  plot.getRangeAxis().setLabelFont(FontClass.MuseoSans700(15));
		  plot.getRangeAxis().setTickLabelFont(FontClass.MuseoSans700(15));
		 // plot.getRangeAxis().setLowerBound(-50.00);
		 SubCategoryAxis domainAxis = new SubCategoryAxis("");
	        domainAxis.setCategoryMargin(0.05);
	        domainAxis.setTickLabelPaint(new Color(65,127,159));
	        domainAxis.setLabelPaint(new Color(65,127,159));
	        domainAxis.setLabelFont(FontClass.MuseoSans700(15));
	        domainAxis.setTickLabelFont(FontClass.MuseoSans700(15));
	        
	       // domainAxis.addSubCategory("1 - Text");
	        plot.getDomainAxis().setLabelFont(FontClass.MuseoSans700(20));
	        plot.setDomainAxis(domainAxis);
	        plot.setFixedLegendItems(createLegendItems());
	       
	        LegendTitle legend = chart.getLegend();
	        if (legend != null) {
	    		legend.setPosition(RectangleEdge.TOP);
	    		legend.setHorizontalAlignment(HorizontalAlignment.RIGHT);
	    		legend.setItemFont(FontClass.MuseoSans900(15));
	    	}
	        
	        
	        

        return chart;
        
    }


	private LegendItemCollection createLegendItems() {
		  
		  LegendItemCollection result = new LegendItemCollection();
	        LegendItem item1 = new LegendItem("1 Base Line", new Color(188, 221, 238));
	        LegendItem item2 = new LegendItem("2 Gain / Loss", new Color(205, 82, 87));
	        result.add(item1);
	        result.add(item2);
	       
	        return result;
	}



	private CategoryDataset createDataset() {
		
		
	
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
	        
			 result.addValue(rate.getCwpm(),rate.getTime()+"" ,rate.getText()+"");
			 if(rate.getTime() == 2){
				 int finalcwpm = rate.getCwpm() - tempCwpm;
				// result.addValue(finalcwpm,rate.getTime()+"" ,rate.getText()+"");
				
				 tblListCWPM.add(finalcwpm);
				 tblListPostDate.add(rate.getDate());
				 tblListPostErrors.add(rate.getErrors());
				
			 }else{
				
				 tempCwpm= rate.getCwpm();
				// result.addValue(rate.getCwpm(),rate.getTime()+"" ,rate.getText()+"");
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
				StudentDAO studDao= new StudentOpr();
				bean=studDao.getAllStudentsWithDecod_Rate_Data(bean.getId());
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


}
