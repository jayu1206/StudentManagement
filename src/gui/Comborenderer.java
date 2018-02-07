package gui;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class Comborenderer implements ListCellRenderer {

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
