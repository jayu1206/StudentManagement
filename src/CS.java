import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class CS implements ActionListener {
    	
	JDialog dialog;
    

    	 public void actionPerformed(ActionEvent e) {
    	        JToggleButton button = (JToggleButton)e.getSource();
    	        Point p = button.getLocation();
    	        JFrame f = (JFrame)button.getTopLevelAncestor();
    	        SwingUtilities.convertPointToScreen(p, f.getContentPane());
    	        dialog.setLocation(p.x, p.y+button.getHeight());
    	        dialog.setVisible(button.isSelected());
    	    }
    	  
    	    private JPanel getContent(Frame f) {
    	        Object[] items = {
    	            "George", "Greta", "Jenny", "Anna", "Pieter", "Antonio", "Susan", "Tom"
    	        };
    	        SelectionManager manager = new SelectionManager();
    	        MultiRenderer renderer = new MultiRenderer(manager);
    	        manager.setNonSelectables("Greta", "Pieter");
    	        // list
    	        JList list = new JList(items);
    	        list.setBorder(BorderFactory.createLineBorder(Color.black));
    	        // you can ommit the manager and renderer and make multiple JList
    	        // selections by holding the control key down while selecting
    	        list.addListSelectionListener(manager);
    	        list.setCellRenderer(renderer);
    	        // toggle button
    	        JToggleButton button = new JToggleButton("select");
    	        button.addActionListener(this);
    	        Dimension d = list.getPreferredSize();
    	        int width = button.getPreferredSize().width;
    	        d.width = d.width < width ? width : d.width;
    	        // an option for appearance
    	        list.setPreferredSize(d);
    	        // dialog
    	        dialog = new JDialog(f, false);
    	        dialog.setUndecorated(true);
    	        dialog.getContentPane().add(list);
    	        dialog.pack();
    	        JPanel panel = new JPanel();
    	        panel.add(button);
    	        return panel;
    	    }
    	  
    	    public static void main(String[] args) {
    	        JFrame f = new JFrame();
    	        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	        f.getContentPane().add(new CS().getContent(f));
    	        f.setSize(300,145);
    	        f.setLocation(200,200);
    	        f.setVisible(true);
    	    }
    	}
    	  
    	class SelectionManager implements ListSelectionListener {
    	    List<Object> selectedItems  = new ArrayList<Object>();
    	    List<Object> nonSelectables = new ArrayList<Object>();
    	  
    	    public void valueChanged(ListSelectionEvent e) {
    	        if(!e.getValueIsAdjusting()) {
    	            Object value = ((JList)e.getSource()).getSelectedValue();
    	            // Toggle the selection state for value.
    	            if(selectedItems.contains(value)) {
    	                selectedItems.remove(value);
    	            } else if(!nonSelectables.contains(value)) {
    	                selectedItems.add(value);
    	            }
    	        }
    	    }
    	  
    	    public void setNonSelectables(Object... args) {
    	        for(int j = 0; j < args.length; j++) {
    	            nonSelectables.add(args[j]);
    	        }
    	    }
    	  
    	    public boolean isSelected(Object value) {
    	        return selectedItems.contains(value);
    	    }
    	}
    	  
    	/** Implementation copied from source code. */
    	class MultiRenderer extends DefaultListCellRenderer {
    	    SelectionManager selectionManager;
    	  
    	    public MultiRenderer(SelectionManager sm) {
    	        selectionManager = sm;
    	    }
    	  
    	    public Component getListCellRendererComponent(JList list,
    	                                                  Object value,
    	                                                  int index,
    	                                                  boolean isSelected,
    	                                                  boolean cellHasFocus) {
    	        setComponentOrientation(list.getComponentOrientation());
    	        if (selectionManager.isSelected(value)) {
    	            setBackground(list.getSelectionBackground());
    	            setForeground(list.getSelectionForeground());
    	        } else {
    	            setBackground(list.getBackground());
    	            setForeground(list.getForeground());
    	        }
    	  
    	        if (value instanceof Icon) {
    	            setIcon((Icon)value);
    	            setText("");
    	        } else {
    	            setIcon(null);
    	            setText((value == null) ? "" : value.toString());
    	        }
    	        setEnabled(list.isEnabled());
    	        setFont(list.getFont());
    	        setBorder((cellHasFocus) ? UIManager.getBorder("List.focusCellHighlightBorder")
    	                                 : noFocusBorder);
    	        return this;
    	    }
}
