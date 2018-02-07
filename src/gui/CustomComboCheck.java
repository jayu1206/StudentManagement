package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class CustomComboCheck extends JComboBox {

	
	public CustomComboCheck(Vector v,ArrayList list){
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
						//list.add(lastItem.toString());
					}					
				}
			}
		}
	
}
