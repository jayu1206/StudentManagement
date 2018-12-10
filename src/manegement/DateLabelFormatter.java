package manegement;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;

import javax.swing.JFormattedTextField.AbstractFormatter;

public class DateLabelFormatter extends AbstractFormatter {

	private String datePattern = "MM/dd/yyyy";
	private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
	
	@Override
	public Object stringToValue(String text) throws ParseException {
		return dateFormatter.parseObject(text);
	}

	@Override
	public String valueToString(Object value) throws ParseException {
		if (value != null) {
			Calendar cal = (Calendar) value;
			return dateFormatter.format(cal.getTime());
		}
		
		return "";
	}
	
	public static void main(String args[]){
		// 01-02-2000
		SimpleDateFormat ddmmyyyy = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat mmddyyyy = new SimpleDateFormat("MM/dd/yyyy");
		
		try
		{
			Date dt = ddmmyyyy.parse("01-02-2000");
			System.out.println(mmddyyyy.format(dt));
			
		}
		catch(Exception e)
		{
			
   			e.printStackTrace();
		}
		
		
		
	}

}