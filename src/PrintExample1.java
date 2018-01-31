import javax.swing.*; 
import java.awt.print.Printable; 
import java.awt.print.PrinterJob; 
import java.awt.print.PrinterException; 
import java.awt.print.PageFormat; 
import java.awt.*; 

public class PrintExample1 { 
	public static void main(String[] args) 
	{ 
		JFrame frame = new JFrame(); 
		final JPanel panel = new JPanel(); 
		JLabel label = new JLabel("This is an example of printing without displaying"); 
		frame.getContentPane().add(panel); 
		panel.add(label); 
		frame.pack(); 
		frame.setVisible(false); 
		PrinterJob job = PrinterJob.getPrinterJob(); 
		
		job.setPrintable(new Printable() { 
		
			public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) 
			throws PrinterException 
			{ 
				if (pageIndex >= 1) { 
					return Printable.NO_SUCH_PAGE; 
				} else { 
					((Graphics2D)graphics).translate(pageFormat.getImageableX(), pageFormat.getImageableY()); 
					panel.print(graphics); 
					return Printable.PAGE_EXISTS; 
				} 
			} 
		}); 
		try 
		{ 
			job.print(); 
		} 
		catch (PrinterException ex) 
		{ 
			ex.printStackTrace(); 
		} 
		System.exit(0); 
	} 
} 
