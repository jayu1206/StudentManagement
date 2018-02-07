import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;

public class ComboCheckBox extends JFrame{


	@SuppressWarnings("unchecked")
	public ComboCheckBox(){
		
		setTitle("Check Box");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new FlowLayout());
		
		//Data source
		Vector v=new Vector();
		v.add("First");
		
		v.add(new JCheckBox("One", false));
		v.add(new JCheckBox("Two", false));
		v.add(new JCheckBox("Three", false));
		
		v.add("Second");
		v.add(new JCheckBox("Four", false));
		v.add(new JCheckBox("Five ", false));
		v.add(new JCheckBox("Six", false));
		
		getContentPane().add(new CustomComboCheck(v));
		
	}
	
	public static void main (String[] args){
		
		ComboCheckBox cb =new ComboCheckBox();
		cb.setSize(500, 350);
		cb.setVisible(true);
	}
	
	//Custom check box and combo
	class CustomComboCheck extends JComboBox{
		
		public CustomComboCheck(Vector v){
			super (v);
			
				// Set Renderer
				setRenderer(new Comborenderer());
			
				//Set Listener
				addActionListener(new ActionListener() {					
					@Override
					public void actionPerformed(ActionEvent ae) {
						// TODO Auto-generated method stub
						ourItemSelected();
						
					}
				});			
			}
		
			private void ourItemSelected (){			
				Object selected = getSelectedItem();
				if (selected instanceof JCheckBox){
					
					JCheckBox chk = (JCheckBox) selected;
					chk.setSelected(!chk.isSelected());
					repaint();
					
					//Get and Dispaly Last selcted items
					Object [] selection = chk.getSelectedObjects();
					if (selection != null){
						
						for (Object lastItem : selection){
							JOptionPane.showMessageDialog(null, lastItem.toString());
						}					
					}
				}
			}
		
	}
}

//Combo Renderer
class Comborenderer implements ListCellRenderer{

	private JLabel lebel;

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean selected,
			boolean focused) {
	
		System.out.println(list);
		if (value instanceof Component){			
			Component c = (Component) value;
			if (selected){
				c.setBackground(list.getSelectionBackground());
				c.setForeground(list.getSelectionForeground());
			}else{
				c.setBackground(list.getBackground());
				c.setForeground(list.getForeground());
			}
			return c;
		}else{
			
			if (lebel == null){
				lebel = new JLabel(value.toString());
			}else {
				lebel.setText(value.toString());
			}
			return lebel;
		}
		
	}
	
}
