import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MultiSelectionDemo extends JFrame implements ListSelectionListener{

	  JList places;

	  public MultiSelectionDemo()
	  {
	    Container c = getContentPane(); 	
	    c.setLayout(new FlowLayout());
	    String names[] = {"Banglore", "Hyderabad", "Ooty", "Chennai", "Mumbai", "Delhi", "Kochi", "Darjeeling"};
	    places = new JList(names) ;                    // creating JList object; pass the array as parameter
	    places.setVisibleRowCount(5); 
			     
	    places.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
	    c.add(new JScrollPane(places));

	    places.addListSelectionListener(this);

	    setTitle("Practcing Multiple selection JList");
	    setSize(300,300);
	    setVisible(true);
	  }
	  public void valueChanged(ListSelectionEvent e)
	  {
	    String destinations = "";
	    Object obj[ ] = places.getSelectedValues();
	    for(int i = 0; i < obj.length; i++)
	    {
	      destinations += (String) obj[i];
	    }

	    JOptionPane.showMessageDialog( null, "You go: " + destinations,  "Learning Multiple Selections", JOptionPane.PLAIN_MESSAGE);
	  }
	  
	  public static void main( String args[ ] )
	  {
	    new MultiSelectionDemo();
	  }
}
