package gui;

import java.awt.*;
import java.awt.geom.*;

import javax.swing.*;

import java.awt.event.*;
 
public class RoundButton extends JButton {
 
	String label;
  public RoundButton(String label) {
    super(label);
    this.label = label;
    setBackground(Color.lightGray);
    setFocusable(false);
 
    /*
     These statements enlarge the button so that it 
     becomes a circle rather than an oval.
    */
    Dimension size = getPreferredSize();
    size.width = size.height = Math.max(size.width, size.height);
    setPreferredSize(size);
 
    /*
     This call causes the JButton not to paint the background.
     This allows us to paint a round background.
    */
    setContentAreaFilled(false);
  }
 
  @Override
  public Dimension getPreferredSize() {
      Font f = getFont();
      if (f != null) {
          FontMetrics fm = getFontMetrics(getFont());
          int max = Math.max(fm.stringWidth("100") + 40, fm.getHeight() + 40);
          return new Dimension(max, max);
      } else {
          return new Dimension(100, 100);
      }
  }
  
  @Override
  public Dimension getMinimumSize() {
      return new Dimension(100, 100);
  }
  
  protected void paintComponent(Graphics g) {
	  
      g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);

      // draw the perimeter of the button
      g.setColor(getBackground().darker().darker().darker());
      g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);

      // draw the label centered in the button
      Font f = getFont();
      if (f != null) {
          FontMetrics fm = getFontMetrics(getFont());
          g.setColor(getForeground());
         // g.drawString("100", getWidth() / 2 - fm.stringWidth("100") / 2, getHeight() / 2 + fm.getMaxDescent());
          
      }
 
    super.paintComponent(g);
  }
 
 /* protected void paintBorder(Graphics g) {
    g.setColor(Color.darkGray);
    g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
  }
 */
  // Hit detection.
  Shape shape;
 
  public boolean contains(int x, int y) {
	  int mx = getSize().width / 2;
      int my = getSize().height / 2;
      return (((mx - x) * (mx - x) + (my - y) * (my - y)) <= mx * mx);
  }
 
  public static void main(String[] args) {
 
    JFrame.setDefaultLookAndFeelDecorated(true);
    JFrame frame = new JFrame("Rounded Button Example");
    frame.setLayout(new FlowLayout());

    JButton b1 = new RoundButton("B1");
    b1.setIcon(new ImageIcon(RoundButton.class.getClass().getResource("/image/my profile OFF.png")));
    
    JButton b2 = new RoundButton("B2");
 
    frame.add(b1);
    frame.add(b2);
 
    frame.setSize(300, 150);
    frame.setVisible(true);
  }
}
